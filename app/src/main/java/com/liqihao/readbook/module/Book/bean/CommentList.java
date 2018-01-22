package com.liqihao.readbook.module.Book.bean;

import com.liqihao.readbook.module.User.bean.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by liqihao on 2018/1/22.
 */

public class CommentList {

    /**
     * {
     "code": "0",
     "msg": "success",
     "result": {
     "data": [
     {
     "id": "1",
     "dateline": "1511420720",
     "nid": "9",
     "grade": "5",
     "content": "这本书贼好看",
     "uid": "100000",
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
     }
     ],
     "total": "1"
     }
     }
     */
    private String code;
    private String msg;

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

    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result {
        List<Data>data;
        String total;

        public List<Data> getData() {
            return data;
        }

        public void setData(List<Data> data) {
            this.data = data;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }
        public class Data {
            String id;
            String dateline;
            String nid;
            String grade;
            String content;
            String uid;
            User user;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDateline() {
                return dateline;
            }

            public void setDateline(String dateline) {
                this.dateline = dateline;
            }

            public String getNid() {
                return nid;
            }

            public void setNid(String nid) {
                this.nid = nid;
            }

            public String getGrade() {
                return grade;
            }

            public void setGrade(String grade) {
                this.grade = grade;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public User getUser() {
                return user;
            }

            public void setUser(User user) {
                this.user = user;
            }
        }
    }


}
