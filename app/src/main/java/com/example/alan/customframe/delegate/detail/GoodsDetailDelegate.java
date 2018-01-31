package com.example.alan.customframe.delegate.detail;

import android.os.Bundle;

import com.example.alan.customframe.R;
import com.example.alan.customframe.delegate.LatteDelegate;

/**
 * Function :
 * Modify Date : 2018/1/31
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class GoodsDetailDelegate extends LatteDelegate {

    private static final String ARG_GOODS_ID = "ARG_GOODS_ID";

    public static GoodsDetailDelegate create(int goodsId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_GOODS_ID, goodsId);
        final GoodsDetailDelegate delegate = new GoodsDetailDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object getLayout() {
        return R.layout.delegate_good_detail;
    }

    @Override
    public void onBindView() {

    }
}
