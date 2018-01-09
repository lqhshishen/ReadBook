package com.liqihao.readbook.module.Home.presenter;

import com.liqihao.readbook.R;
import com.liqihao.readbook.base.BasePresenter;
import com.liqihao.readbook.module.Home.bean.CommunityBean;
import com.liqihao.readbook.module.Home.contract.CommunityContract;
import com.liqihao.readbook.module.Home.ui.CommunityFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqihao on 2017/12/27.
 */

public class CommunityPresenter extends BasePresenter<CommunityFragment> implements CommunityContract.Presenter{

    public List<CommunityBean>data;
    @Override
    public void getData() {
        data = new ArrayList<>();
        CommunityBean communityBean1 = new CommunityBean();
        communityBean1.setAuthor("刘慈欣");
        communityBean1.setBookImg(R.mipmap.site);
        communityBean1.setBookMsg("他单枪匹马，把中国科幻提升到");
        communityBean1.setBookName("三体（全三册）");
        communityBean1.setForwardNum(66);
        communityBean1.setLike(55);
        communityBean1.setRepostNum(99);
        communityBean1.setUserImg(R.mipmap.cat);
        communityBean1.setUserName("用户名");
        communityBean1.setTime("1万年前");
        communityBean1.setMsg("非常不错的故事，原作没看过，但我想以漫画的形式来描绘，一定比原著更添色彩。整体色调偏暖，黄昏和清晨的场景较多" +
                "，人物和形象设定和周边环境也很不错，适合80后怀旧。");
        communityBean1.setType(2);


        CommunityBean communityBean2 = new CommunityBean();
        communityBean2.setUserImg(R.mipmap.cat);
        communityBean2.setUserName("甲乙丙丁");
        communityBean2.setTime("10年前");
        communityBean2.setMsg("非常不错的故事，原作没看过，但我想以漫画的形式来描绘，一定比原著更添色彩。整体色调偏暖，黄昏和清晨的场景较多" +
                "，人物和形象设定和周边环境也很不错，适合80后怀旧。");
        communityBean2.setImg(R.mipmap.site);
        communityBean2.setLike(33);
        communityBean2.setForwardNum(42);
        communityBean2.setRepostNum(56);
        communityBean2.setType(1);

        data.add(communityBean1);
        data.add(communityBean2);
    }
}
