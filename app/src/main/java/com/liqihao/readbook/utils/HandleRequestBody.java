package com.liqihao.readbook.utils;

import org.json.JSONObject;

import okhttp3.RequestBody;

public class HandleRequestBody {

    public static RequestBody instance(JSONObject jsonObject1){
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8")
                ,jsonObject1.toString());
    }

}
