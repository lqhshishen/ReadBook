package com.liqihao.readbook.module.Home.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liqihao.readbook.R;
import com.liqihao.readbook.base.AppActivity;
import com.liqihao.readbook.base.BaseActivity;
import com.liqihao.readbook.module.Home.adapter.HotSearchAdapter;
import com.liqihao.readbook.module.Home.adapter.SearchBookAdapter;
import com.liqihao.readbook.module.Home.bean.HotSearchBean;
import com.liqihao.readbook.module.Home.contract.SearchContract;
import com.liqihao.readbook.module.Home.presenter.SearchPresenter;
import com.liqihao.readbook.module.SearchDetail.bean.SearchBookBean;
import com.liqihao.readbook.module.SearchDetail.ui.SearchDetailActivity;
import com.liqihao.readbook.utils.SharedPreferencesUtil;
import com.liqihao.readbook.utils.ToastUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liqihao on 2017/12/28.
 */

public class SearchActivity extends AppActivity<SearchPresenter> implements SearchContract.View {

    @BindView(R.id.search_edtinputtel)
    EditText searchEdtinputtel;
    @BindView(R.id.search_hotRecycle)
    RecyclerView searchHotRecycle;
    @BindView(R.id.search_history)
    RecyclerView searchHistory;
    @BindView(R.id.search_clear)
    LinearLayout searchClear;



    @Override
    public void bindView() {
        ButterKnife.bind(this);
    }

    SearchBookAdapter adapter1;
    HotSearchAdapter adapter2;
    @Override
    public void initData() {
        searchHistory.setLayoutManager(new LinearLayoutManager(this));
        data = presenter.getHistory();
        adapter1 = new SearchBookAdapter(data,this);
        searchHistory.setAdapter(adapter1);
        searchHotRecycle.setLayoutManager(new GridLayoutManager(this,4));
        presenter.getHotSearch();

    }
//    List<HotSearchBean>hotData;
    List<String>data = new ArrayList<>();

    @Override
    public void onClick() {
        searchEdtinputtel.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean chongfu = true;
                /**
                 * 点击搜索时
                 */
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String result = searchEdtinputtel.getText().toString();
                    result.replaceAll(" ", "");
                    /**
                     * 判断是否重复
                     */
                    if (presenter.getHistory()!= null) {
                        for (int i = 0; i < presenter.getHistory().size(); i ++) {
                            if (presenter.getHistory().get(i).equals(result)) {
                                chongfu = false;
                            }
                        }
                    }
                    /**
                     * 当result不为空且chongfu为true时添加数据
                     */
                    if (!"".equals(result) && chongfu ) {
                        SharedPreferencesUtil.getInstance().putString("searchHistory",result + ";" + presenter.getHistoryString());
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        data.add(result);
                        adapter1.notifyDataSetChanged();
                        if (imm != null) {
                            imm.hideSoftInputFromWindow(searchEdtinputtel.getWindowToken(),0);
                        }
                        searchEdtinputtel.clearFocus();
                    }
                    goSearch(result);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_search;
    }



    @OnClick(R.id.search_clear)
    public void onViewClicked() {
        SharedPreferencesUtil.getInstance().removeAll();
        ToastUtils.showShort(this,"清理成功");
        if (data != null) {
            data.clear();
            adapter1.notifyDataSetChanged();
        }
    }

    public void goSearch(String key) {
        Intent intent=new Intent(this, SearchDetailActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("list",key);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onGetHotSearch(HotSearchBean hotSearchBean) {
        if ("0".equals(hotSearchBean.getCode())) {
            adapter2 = new HotSearchAdapter(hotSearchBean.getResult(),this);
            searchHotRecycle.setAdapter(adapter2);
        }
        else
            Log.e("onGetHotSearch",hotSearchBean.getMsg());
    }
}
