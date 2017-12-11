package com.example.alan.customframe.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Alan on 2017/12/11.
 * 标准的Activity
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (setNoTitle()) {
            initWindow();
        }
        //设置布局内容
        setContentView(getLayoutId());
        ////初始化黄油刀控件绑定框架
        unbinder = ButterKnife.bind(this);
        //设置控件
        initViews();
        //设置数据
        initData();
        //设置事件
        initEvent();

    }

    /**
     * 设置是否显示标题栏，默认为false
     *
     * @return
     */
    protected boolean setNoTitle() {
        return false;
    }

    /**
     * 设置标题栏
     */
    protected void initWindow() {
    }

    /**
     * 设置布局layout
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 设置控件
     */
    protected void initViews() {
    }

    /**
     * 设置数据
     */
    protected void initData() {
    }

    /**
     * 设置事件
     */
    protected void initEvent() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


}
