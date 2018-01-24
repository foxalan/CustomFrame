package com.example.alan.customframe.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.example.alan.customframe.recycler.MultipleRecyclerAdapter;
import com.example.latten_corn.Latte;

/**
 * Function :
 * Modify Date : 2018/1/24
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener{

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter = null;

    public RefreshHandler(SwipeRefreshLayout REFRESH_LAYOUT, RecyclerView RECYCLERVIEW, MultipleRecyclerAdapter mAdapter) {
        this.REFRESH_LAYOUT = REFRESH_LAYOUT;
        this.RECYCLERVIEW = RECYCLERVIEW;
        this.mAdapter = mAdapter;
    }

    @Override
    public void onRefresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行一些网络请求
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 1000);
    }

    /**
     * 简单工厂模式
     */

//    public static RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,
//                                        RecyclerView recyclerView, DataConverter converter) {
//        return new RefreshHandler(swipeRefreshLayout, recyclerView, converter, new PagingBean());
//    }



}
