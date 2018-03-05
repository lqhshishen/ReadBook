package com.liqihao.readbook.module.Book.contract;

import com.liqihao.readbook.base.BaseView;
import com.liqihao.readbook.module.Book.presenter.WholeContentPresenter;
import com.liqihao.readbook.module.ReadPage.bean.Chapter;

/**
 * Created by liqihao on 2017/12/28.
 */

public interface WholeContentContract {
    interface view extends BaseView<WholeContentPresenter> {
        void onGetChapter(Chapter chapter);

    }
    interface presenter {
        void getChapterList(String id);
    }
}
