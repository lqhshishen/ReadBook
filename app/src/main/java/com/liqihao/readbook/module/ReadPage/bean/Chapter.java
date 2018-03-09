package com.liqihao.readbook.module.ReadPage.bean;

import com.liqihao.readbook.module.ReadPage.bean.Book;

import java.util.List;

/**
 * Created by liqihao on 2017/11/21.
 */

public class Chapter extends Book {
//    private String chapterName;
//    private int chapterBytePosition, chapterParagraphPsition;
//
//    public String getChapterName() {
//        return chapterName;
//    }
//
//    public void setChapterName(String chapterName) {
//        this.chapterName = chapterName;
//    }
//
//    public int getChapterBytePosition() {
//        return chapterBytePosition;
//    }
//
//    public void setChapterBytePosition(int chapterBytePosition) {
//        this.chapterBytePosition = chapterBytePosition;
//    }
//
//    public int getChapterParagraphPsition() {
//        return chapterParagraphPsition;
//    }
//
//    public void setChapterParagraphPsition(int chapterParagraphPsition) {
//        this.chapterParagraphPsition = chapterParagraphPsition;
//    }
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
        String bookname;
        String link;
        String chapter_name;
        String dateline;
        String status;
        String bid;
        String path;

        public String toString() {
            return "id:" + id + "bookname:" + bookname + "bookId" + bid + "path" + path;
        }

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

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getChapter_name() {
            return chapter_name;
        }

        public void setChapter_name(String chapter_name) {
            this.chapter_name = chapter_name;
        }

        public String getDateline() {
            return dateline;
        }

        public void setDateline(String dateline) {
            this.dateline = dateline;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getBid() {
            return bid;
        }

        public void setBid(String bid) {
            this.bid = bid;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
