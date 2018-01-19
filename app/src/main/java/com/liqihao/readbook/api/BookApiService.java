package com.liqihao.readbook.api;

import com.liqihao.readbook.module.Book.bean.AddBookshelfBean;
import com.liqihao.readbook.module.Book.bean.AddComment;
import com.liqihao.readbook.module.Classification.bean.ClassicItemBean;
import com.liqihao.readbook.module.Home.bean.ClassificationBean;
import com.liqihao.readbook.module.Login.bean.MobileLoginBean;
import com.liqihao.readbook.module.Login.bean.MobileReg;
import com.liqihao.readbook.module.Login.bean.SendCodeBean;
import com.liqihao.readbook.module.Login.bean.SetPasswordBean;
import com.liqihao.readbook.module.User.bean.MyBookList;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by liqihao on 2018/1/10.
 */

public interface BookApiService {
    /**
     *绑定手机
     * @param requestBody
     * @return
     */
    @POST("v1/User/mobileBind")
    Observable<TestBean> getMsg(@Body RequestBody requestBody);

    /**
     *获取分类列表
     * @param requestBody
     * @return
     */
    @POST("v1/Category/getList")
    Observable<ClassificationBean>getList(@Body RequestBody requestBody);

    /**
     * 设置密码
     * @param requestBody
     * @return
     */
    @POST("v1/User/updatePass")
    Observable<SetPasswordBean>upDatePassword(@Body RequestBody requestBody);

    /**
     *手机用户注册
     * @param requestBody
     * @return
     */
    @POST("v1/User/mobileReg")
    Observable<MobileReg>mobileReg(@Body RequestBody requestBody);

    /**
     * 获取分类下小说列表接口
     * @param requestBody
     * @return
     */
    @POST("v1/Category/getBookList")
    Observable<ClassicItemBean>getClassifyBookList(@Body RequestBody requestBody);

    /**
     * 发送手机验证码
     * @param requestBody
     * @return
     */
    @POST("v1/User/sendCode")
    Observable<SendCodeBean>sendCode(@Body RequestBody requestBody);

    /**
     * 手机用户登录
     * @param requestBody
     * @return
     */
    @POST("v1/User/mobileLogin")
    Observable<MobileLoginBean>login(@Body RequestBody requestBody);

    /**
     *我的书架(收藏)
     * @param requestBody
     * @return
     */
    @POST("v1/User/myBookshelf")
    Observable<MyBookList>getMyBookList(@Body RequestBody requestBody);

    /**
     * 添加到书架(收藏)
     * @param requestBody
     * @return
     */
    @POST("v1/User/addBookshelf")
    Observable<AddBookshelfBean>addToBookshelf(@Body RequestBody requestBody);

    /**
     * 添加评论
     * @param requestBody
     * @return
     */
    @POST("v1/Comment/addComment")
    Observable<AddComment>addComment(@Body RequestBody requestBody);
}
