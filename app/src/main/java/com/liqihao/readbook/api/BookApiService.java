package com.liqihao.readbook.api;

import com.liqihao.readbook.module.Classification.bean.ClassicItemBean;
import com.liqihao.readbook.module.Home.bean.ClassificationBean;
import com.liqihao.readbook.module.Login.bean.MobileReg;
import com.liqihao.readbook.module.Login.bean.SetPasswordBean;

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


}
