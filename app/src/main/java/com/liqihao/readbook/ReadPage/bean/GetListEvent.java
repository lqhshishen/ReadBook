package com.liqihao.readbook.ReadPage.bean;

import com.liqihao.readbook.Content.bean.Chapter;

import java.util.List;

/**
 * Created by liqihao on 2017/11/23.
 */

public class GetListEvent {
    List<Chapter>list;

    public GetListEvent(List<Chapter> list) {
        this.list = list;
    }

    public List<Chapter> getList() {
        return list;
    }

    public void setList(List<Chapter> list) {
        this.list = list;
    }
}
