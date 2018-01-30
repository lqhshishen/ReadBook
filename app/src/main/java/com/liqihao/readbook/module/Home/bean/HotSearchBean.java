package com.liqihao.readbook.module.Home.bean;

/**
 * Created by liqihao on 2018/1/26.
 */

import java.util.List;

/**
 * {
 "code": "0",
 "msg": "success",
 "result": [
 {
 "id": "1",
 "nid": "1",
 "name": "官场如剧场：川戏"
 }
 ]
 }
 */
public class HotSearchBean {
    private String code;
    private String msg;
    private List<Result>result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public class Result {
        String id;
        String nid;
        String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNid() {
            return nid;
        }

        public void setNid(String nid) {
            this.nid = nid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
