package com.liqihao.readbook.Content;

/**
 * Created by liqihao on 2017/11/21.
 */

public class Chapter extends Book{
    private String chapterName;
    private int chapterBytePosition, chapterParagraphPsition;

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public int getChapterBytePosition() {
        return chapterBytePosition;
    }

    public void setChapterBytePosition(int chapterBytePosition) {
        this.chapterBytePosition = chapterBytePosition;
    }

    public int getChapterParagraphPsition() {
        return chapterParagraphPsition;
    }

    public void setChapterParagraphPsition(int chapterParagraphPsition) {
        this.chapterParagraphPsition = chapterParagraphPsition;
    }
}
