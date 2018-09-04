package com.liqihao.readbook.module.Home;

import com.liqihao.readbook.module.Home.contract.BookCityContract;
import com.liqihao.readbook.module.Home.contract.ClassificationContract;
import com.liqihao.readbook.module.Home.contract.CommunityContract;
import com.liqihao.readbook.module.Home.contract.RankContract;
import com.liqihao.readbook.module.Home.presenter.BookCityPresenter;
import com.liqihao.readbook.module.Home.ui.BookCityFragment;
import com.liqihao.readbook.module.Home.ui.ClassificationFragment;
import com.liqihao.readbook.module.Home.ui.CommunityFragment;
import com.liqihao.readbook.module.Home.ui.RankFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeFragmentsModule {

    /**
     * 书城
     */
    @Provides
    BookCityContract.View provideBookCity(BookCityFragment bookCityFragment){
        return bookCityFragment;
    }

    /**
     * 分类
     * @param fragment
     * @return
     */
    @Provides
    ClassificationContract.View provideClassification(ClassificationFragment fragment){
        return fragment;
    }

    /**
     * 社区
     * @param fragment
     * @return
     */
    @Provides
    CommunityContract.View provideCommunity(CommunityFragment fragment){
        return fragment;
    }

    /**
     * 排行榜
     * @param fragment
     * @return
     */
    @Provides
    RankContract.View provideRank(RankFragment fragment){
        return fragment;
    }
}
