package com.example.alan.customframe.delegate.home.bottom;

import android.support.v7.widget.LinearLayoutCompat;

import com.example.alan.customframe.R;
import com.example.alan.customframe.delegate.LatteDelegate;

import butterknife.BindView;

/**
 * Function :
 * Modify Date : 2018/1/23
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class BaseBottomDelegate extends LatteDelegate {


    @BindView(R.id.bottom_bar)
    LinearLayoutCompat mBottomBar = null;

    @Override
    public Object getLayout() {
        return R.layout.delegate_bottom;
    }

    @Override
    public void onBindView() {

    }
}
