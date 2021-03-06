package com.liqihao.readbook.api;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import com.liqihao.readbook.app.App;
import com.liqihao.readbook.base.BaseModle;
import com.liqihao.readbook.contents.Constant;
import com.liqihao.readbook.module.Book.bean.AddBookshelfBean;
import com.liqihao.readbook.module.Book.bean.AddComment;
import com.liqihao.readbook.module.Book.bean.CommentList;
import com.liqihao.readbook.module.Classification.bean.ClassicItemBean;
import com.liqihao.readbook.module.Home.bean.ClassificationBean;
import com.liqihao.readbook.module.Home.bean.HotSearchBean;
import com.liqihao.readbook.module.Login.bean.MobileLoginBean;
import com.liqihao.readbook.module.Login.bean.MobileReg;
import com.liqihao.readbook.module.Login.bean.SendCodeBean;
import com.liqihao.readbook.module.Login.bean.UMLoginBean;
import com.liqihao.readbook.module.ReadPage.bean.Chapter;
import com.liqihao.readbook.module.ReadPage.bean.ChapterDetailBean;
import com.liqihao.readbook.module.SearchDetail.bean.SearchBookBean;
import com.liqihao.readbook.module.User.bean.MyBookList;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liqihao on 2018/1/10.
 */

public class BookApi {

    public static BookApi instance;

    private BookApiService service;

    private String auth;

    private Context mContext;

    private JSONObject jsonObject = new JSONObject();

    private Retrofit retrofit;

    public BookApiService getService() {
        return service;
    }

    @Inject
    public BookApi(Context context){
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(BookApiService.class);
        mContext = App.AppContext;
        TelephonyManager TelephonyMgr = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        PackageManager pm = App.AppContext.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(App.AppContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String deviceid = TelephonyMgr.getDeviceId();
        String key = "h32nfow45e";
        String timestamp = Long.toString(System.currentTimeMillis());
        assert pi != null;
        String version = pi.versionName;
        String os = "1";
        String sign = md5(md5(timestamp + key + version));

        try {
            jsonObject.put("sign", sign);
            jsonObject.put("key", key);
            jsonObject.put("deviceid", deviceid);
            jsonObject.put("os", os);
            jsonObject.put("version", version);
            jsonObject.put("timestamp", timestamp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public BookApi() {
    }

    public static BookApi getInstance(Context context) {
        if (instance == null)
            instance = new BookApi(context);
        return instance;
    }

    private RequestBody handleBody(JSONObject jsonObject1) {
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8")
                ,jsonObject1.toString());
    }

    public Observable<TestBean> getMsg (String tel,String auth,String vCode) {
        try {
            jsonObject.put("auth",auth);
            jsonObject.put("mobile",tel);
            jsonObject.put("vcode",vCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return service.getMsg(handleBody(jsonObject));
    }

    public Observable<ClassificationBean>getList() {
        return service.getList(handleBody(jsonObject));
    }

    public Observable<SendCodeBean>senCode(String mobile) {
        try {
            jsonObject.put("mobile",mobile);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return service.sendCode(handleBody(jsonObject));
    }

    public Observable CommonOb(BookApiService ob){
        return ob.addComment(handleBody(jsonObject));
    }


    public Observable<MobileReg>mobileRegist(String mobile,String password,String vcode) {
        try {
            jsonObject.put("mobile",mobile);
            jsonObject.put("pass",password);
            jsonObject.put("vcode",vcode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  service.mobileReg(handleBody(jsonObject));
    }

    public Observable<ClassicItemBean>getClassifyBookList(String page,String id) {
        try {
            jsonObject.put("page",page);
            jsonObject.put("id",id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return service.getClassifyBookList(handleBody(jsonObject));
    }

    public Observable<MobileLoginBean>login(String mobile,String pass) {

        try {
            jsonObject.put("pass",pass);
            jsonObject.put("mobile",mobile);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return service.login(handleBody(jsonObject));
    }

    public Observable<MyBookList>getMyBookList(String auth,String page) {

        try {
            jsonObject.put("page",page);
            jsonObject.put("auth",auth);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return service.getMyBookList(handleBody(jsonObject));
    }

    public Observable<AddBookshelfBean>addToBookshelf(String id,String auth) {
        try {
            jsonObject.put("auth",auth);
            jsonObject.put("id",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return service.addToBookshelf(handleBody(jsonObject));
    }

    public Observable<AddComment>addComment(String auth,String content,String id,String grade) {
        try {
            jsonObject.put("grade",grade);
            jsonObject.put("auth",auth);
            jsonObject.put("content",content);
            jsonObject.put("id",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return service.addComment(handleBody(jsonObject));
    }

    public Observable<CommentList>ShowCommentList(String id,String page) {
        try {
            jsonObject.put("page",page);
            jsonObject.put("id",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return service.showCommentList(handleBody(jsonObject));
    }

    public Observable<UMLoginBean>UMLogin(String username,String openid,String province,String gender,String header) {
        try {
            jsonObject.put("username",username);
            jsonObject.put("openid",openid);
            jsonObject.put("province",province);
            jsonObject.put("gender",gender);
            jsonObject.put("header",header);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return service.UMLogin(handleBody(jsonObject));
    }

    public Observable<HotSearchBean>hotSearch() {
        return service.hotSearch(handleBody(jsonObject));
    }

    public Observable<SearchBookBean>searchBook(String key) {
        try {
            jsonObject.put("key",key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return service.searchBook(handleBody(jsonObject));
    }

    public Observable<Chapter>getChapterList(String id) {
        try {
            jsonObject.put("id",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return service.getChapterList(handleBody(jsonObject));
    }

    public Observable<AddBookshelfBean>deleteBookshelf(String id,String auth){
        try {
            jsonObject.put("id",id);
            jsonObject.put("auth",auth);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return service.deleteBookshelf(handleBody(jsonObject));
    }

    public Observable<ChapterDetailBean>getChapterDetail(String nid,String id){
        try {
            jsonObject.put("nid",nid);
            jsonObject.put("id",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return service.getChapterDetail(handleBody(jsonObject));
    }









    public static String md5(String string) {//MD5加密
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
}

