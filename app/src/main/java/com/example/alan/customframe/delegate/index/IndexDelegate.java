package com.example.alan.customframe.delegate.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.alan.customframe.R;
import com.example.alan.customframe.delegate.home.HomeDelegate;
import com.example.alan.customframe.delegate.home.bottom.BaseBottomItemDelegate;
import com.example.alan.customframe.refresh.RefreshHandler;

import butterknife.BindView;

/**
 * Function :
 * Modify Date : 2018/1/23
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class IndexDelegate extends BaseBottomItemDelegate {
    @BindView(R.id.srl_index)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.ryc_index)
    RecyclerView mRecyclerView;
    @BindView(R.id.tl_index)
    Toolbar mToolbar;

    private RefreshHandler mRefreshHandler;

    @Override
    public Object getLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView() {
        mRefreshHandler = RefreshHandler.create(mSwipeRefreshLayout,mRecyclerView,new IndexDataConverter());
    }

    private void initRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mSwipeRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("index.php");
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);

        final HomeDelegate ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
//        mRecyclerView.addItemDecoration
//                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
//        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
//        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
    }






}
