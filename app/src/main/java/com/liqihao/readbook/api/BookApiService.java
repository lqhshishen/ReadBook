package com.liqihao.readbook.api;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by liqihao on 2018/1/10.
 */

public interface BookApiService {

    @POST("v1/User/mobileBind")
    Observable<TestBean> getMsg(@Body RequestBody requestBody);
}
