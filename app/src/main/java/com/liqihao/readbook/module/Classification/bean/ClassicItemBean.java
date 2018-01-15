package com.liqihao.readbook.module.Classification.bean;

import java.util.List;

/**
 * Created by liqihao on 2018/1/8.
 */

public class ClassicItemBean {
    /**
     *  "code": "0",
     "msg": "success",
     "result": [
     {
     "id": "57",
     "bookname": "都市逍遥客",
     "url": "http://www.ybdu.com/xiaoshuo/2/2602/",
     "classify": "都市小说",
     "classify_id": "3",
     "wordcount": "",
     "status": "1",
     "author": "随缘·珍重",
     "brief": "小孩实在是没有想到，这个没人敢招惹的家伙，居然会这么好说话，声音不但低了下来，也越发地颤抖起来。\r\n　　“飞哥，我是想问问你，我怎么才能、怎么才能不让那些城管没收我的三轮呢？” 平淡生活的强者，但他总是要遇到不平淡的事。",
     "icon": "15112504952602s.jpg",
     "updatetime": "2010-08-27 08:52",
     "dateline": "1511250495"
     }
     */


    private String code;
    private String msg;
    private List<ResultBean>result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public class ResultBean {
        /**
         *         {
         "id": "1",
         "bookname": "官场如剧场：川戏",
         "url": "http://www.ybdu.com/xiaoshuo/17/17267/",
         "classify": "都市小说",
         "classify_id": "3",
         "wordcount": "",
         "status": "1",
         "author": "林啸",
         "brief": "程海平从西南音乐学院钢琴系毕业时，毅然谢绝了恩师邹逸天教授极力让他留校任教的挽留，回到故乡成为一名中学音乐老师。事业受挫，初恋失意。在心灵备受煎熬的苦闷彷徨中，他做过歌舞厅老板，度过了一段风流沉沦、行尸走肉般的荒唐时光。命运的戏剧性转折让他走上仕途。借风使力，历经坎坷，程海平实现了从一名乡镇干部到市委常委、秘书长的不寻常跨越。\r\n　　随着程海平及周围其他人物身份职位的变迁沉浮，20世纪60年代至今西南地区城市乡村的政治生态和社会生活俨如一幅场景广阔的历史画卷鲜明生动地展现在我们眼前。小说以川剧流行的四川、重庆、贵州、云南、湖北等省市为地域背景，在极具地方特色的浓厚文化氛围中，描绘了不同阶层的人物命运及其婚姻家庭，塑造了程海平、郑江、陶岚、吴小芹、张亚龙、洪阿发等众多复杂而鲜活的人物形象。题材丰富多彩、情节跌宕离奇、文笔娴熟细腻是这部作品的显著特色。",
         "icon": "151090442517267s.jpg",
         "updatetime": "2016-02-26 12:14",
         "dateline": ""
         }
         */
        String id;
        String bookname;
        String url;
        String classify;
        String classify_id;
        String wordcount;
        String status;
        String author;
        String brief;
        String icon;
        String updatetime;
        String dateline;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBookname() {
            return bookname;
        }

        public void setBookname(String bookname) {
            this.bookname = bookname;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getClassify() {
            return classify;
        }

        public void setClassify(String classify) {
            this.classify = classify;
        }

        public String getClassify_id() {
            return classify_id;
        }

        public void setClassify_id(String classify_id) {
            this.classify_id = classify_id;
        }

        public String getWordcount() {
            return wordcount;
        }

        public void setWordcount(String wordcount) {
            this.wordcount = wordcount;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getDateline() {
            return dateline;
        }

        public void setDateline(String dateline) {
            this.dateline = dateline;
        }
    }


}
