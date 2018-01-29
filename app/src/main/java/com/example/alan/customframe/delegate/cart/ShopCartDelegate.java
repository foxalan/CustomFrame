package com.example.alan.customframe.delegate.cart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.alan.customframe.R;
import com.example.alan.customframe.delegate.home.bottom.BaseBottomItemDelegate;
import com.example.alan.customframe.latte.Latte;
import com.example.alan.customframe.net.RestClient;
import com.example.alan.customframe.net.callback.ISuccess;
import com.example.alan.customframe.recycler.MultipleItemEntity;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Function :
 * Modify Date : 2018/1/29
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class ShopCartDelegate extends BaseBottomItemDelegate implements ISelectedChangeListener {

    @BindView(R.id.tv_top_shop_cart_clear)
    AppCompatTextView mTextViewClear;
    @BindView(R.id.tv_top_shop_cart_remove_selected)
    AppCompatTextView mTextViewSelected;
    @BindView(R.id.icon_shop_cart_select_all)
    IconTextView mTextViewSelectAll;
    @BindView(R.id.tv_shop_cart_pay)
    AppCompatTextView mTextViewPay;
    @BindView(R.id.tv_shop_cart_total_price)
    AppCompatTextView mTextViewTotalPrice;
    @BindView(R.id.ryc_cart)
    RecyclerView mRecyclerView;

    @OnClick(R.id.icon_shop_cart_select_all)
    void onClickSelectAll() {
        final int tag = (int) mTextViewSelectAll.getTag();
        if (tag == 0) {
            mTextViewSelectAll.setTextColor
                    (ContextCompat.getColor(getContext(), R.color.app_main));
            mTextViewSelectAll.setTag(1);
            mAdapter.setIsSelectedAll(true);
            //更新RecyclerView的显示状态
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
            mTextViewTotalPrice.setText(String.valueOf(mTotalPrice));
        } else {
            mTextViewSelectAll.setTextColor(Color.GRAY);
            mTextViewSelectAll.setTag(0);
            mAdapter.setIsSelectedAll(false);
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
            mTextViewTotalPrice.setText(String.valueOf(0.00));
        }
    }

    private ShopCartAdapter mAdapter = null;

    private int mCurrentCount = 0;
    private int mTotalCount = 0;
    private double mTotalPrice = 0.00;

    @Override
    public Object getLayout() {
        return R.layout.delegate_cart;
    }

    @Override
    public void onBindView() {
        mTextViewSelectAll.setTag(1);
    }

    /**
     * 初始化购物车要的数据
     * @param savedInstanceState
     */
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        RestClient.builder()
                .url("shop_cart.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                       
                        initData(response);
                    }
                })
                .build()
                .get();
    }

    private void initData(String response) {
        final ArrayList<MultipleItemEntity> data =
                new CartDataConverter()
                        .setJsonData(response)
                        .convert();
        mAdapter = new ShopCartAdapter(data);
        mAdapter.setSelectedChangeListener(this);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mTotalPrice = mAdapter.getTotalPrice();
        mTextViewTotalPrice.setText(String.valueOf(mAdapter.getTotalPrice()));
        mTextViewSelectAll.setTextColor
                (ContextCompat.getColor(getContext(), R.color.app_main));
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getTotalPrice(final double totalPrice) {
        Latte.getHandler().post(new Runnable() {
            @Override
            public void run() {
                mTextViewTotalPrice.setText(String.valueOf(totalPrice));
            }
        });
    }

    @Override
    public void getLeftCheck(boolean isAllCheck) {
        if (isAllCheck){
            mTextViewSelectAll.setTextColor
                    (ContextCompat.getColor(getContext(), R.color.app_main));
        }else {
            mTextViewSelectAll.setTextColor(Color.GRAY);
        }
    }
}
