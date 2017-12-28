package com.example.alan.customframe.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;



public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;

    private View rootView;
    private boolean isVisible = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false);
        } else {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        }

        return rootView;
    }

    /**
     * 设置布局ID
     *
     * @return
     */
    public abstract int getLayoutId();


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化控件绑定
        unbinder = ButterKnife.bind(view);
        //初始化数据
        initData();

    }

    /**
     * 设置数据
     */
    protected void initData() {

    }

    /**
     * Fragment的懒加载
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInVisible();
        }
    }

    /**
     * fragment显示时才加载数据
     */
    protected void onVisible() {
        lazyLoad();
    }

    /**
     * fragment懒加载方法
     */
    protected void lazyLoad() {
    }

    /**
     * fragment不显示时的操作
     */
    protected void onInVisible() {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
    }
}
