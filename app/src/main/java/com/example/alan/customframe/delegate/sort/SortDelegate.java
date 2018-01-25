package com.example.alan.customframe.delegate.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.alan.customframe.R;
import com.example.alan.customframe.delegate.home.bottom.BaseBottomItemDelegate;

/**
 * Function :
 * Modify Date : 2018/1/23
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class SortDelegate extends BaseBottomItemDelegate {

    @Override
    public Object getLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView() {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }
}
