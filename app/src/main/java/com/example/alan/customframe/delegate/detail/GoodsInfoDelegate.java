package com.example.alan.customframe.delegate.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.alan.customframe.R;
import com.example.alan.customframe.delegate.LatteDelegate;

import butterknife.BindView;

/**
 * Created by 傅令杰
 */

public class GoodsInfoDelegate extends LatteDelegate {

    @BindView(R.id.tv_goods_info_title)
    AppCompatTextView mGoodsInfoTitle = null;
    @BindView(R.id.tv_goods_info_desc)
    AppCompatTextView mGoodsInfoDesc = null;
    @BindView(R.id.tv_goods_info_price)
    AppCompatTextView mGoodsInfoPrice = null;

    private static final String ARG_GOODS_DATA = "ARG_GOODS_DATA";
    private JSONObject mData = null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        final String goodsData = args.getString(ARG_GOODS_DATA);
        mData = JSON.parseObject(goodsData);
    }

    public static GoodsInfoDelegate create(String goodsInfo) {
        final Bundle args = new Bundle();
        args.putString(ARG_GOODS_DATA, goodsInfo);
        final GoodsInfoDelegate delegate = new GoodsInfoDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object getLayout() {
        return R.layout.delegate_goods_info;
    }

    @Override
    public void onBindView() {
        final String name = mData.getString("name");
        final String desc = mData.getString("description");
        final double price = mData.getDouble("price");
        mGoodsInfoTitle.setText(name);
        mGoodsInfoDesc.setText(desc);
        mGoodsInfoPrice.setText(String.valueOf(price));
    }
}
