package com.liqihao.readbook.module.User.bean;

/**
 * Created by liqihao on 2018/1/18.
 */

public class User {
    /**
     * {
     "user": {
     "id": "100000",
     "username": "不知今夕是何年",
     "openid": "5F61FE51E22E84987FEC7A2887D7C2D7",
     "province": "安徽",
     "city": "宿州",
     "dateline": "",
     "gender": "男",
     "header": "http://q.qlogo.cn/qqapp/1105886594/5F61FE51E22E84987FEC7A2887D7C2D7/100",
     "mobile": "13776505322",
     "vcode": ""
     }
     */
    private String id;
    private String username;
    private String openid;
    private String city;
    private String dateline;
    private String gender;
    private String header;
    private String mobile;
    private String vcode;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }
}
