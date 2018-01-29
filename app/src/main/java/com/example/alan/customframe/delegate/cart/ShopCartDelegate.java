package com.example.alan.customframe.delegate.cart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.alan.customframe.R;
import com.example.alan.customframe.delegate.home.bottom.BaseBottomItemDelegate;
import com.example.alan.customframe.latte.Latte;
import com.example.alan.customframe.net.RestClient;
import com.example.alan.customframe.net.callback.ISuccess;
import com.example.alan.customframe.recycler.MultipleItemEntity;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.stub_no_item)
    ViewStubCompat mStubNoItem = null;
    @BindView(R.id.ll_money_pay)
    LinearLayoutCompat mLinearLayout;


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
            for (MultipleItemEntity entity : mAdapter.getData()) {
                entity.setField(ShopCartItemFields.IS_SELECTED, true);
            }
        } else {
            mTextViewSelectAll.setTextColor(Color.GRAY);
            mTextViewSelectAll.setTag(0);
            mAdapter.setIsSelectedAll(false);
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());

            for (MultipleItemEntity entity : mAdapter.getData()) {
                entity.setField(ShopCartItemFields.IS_SELECTED, false);
            }

        }

        mTextViewTotalPrice.setText(String.valueOf(getTotalPrice()));

    }

    @OnClick({R.id.tv_top_shop_cart_clear, R.id.tv_top_shop_cart_remove_selected})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_top_shop_cart_clear:
                if (mAdapter.getData().size()!=0){

                    mAdapter.getData().clear();
                    mAdapter.notifyDataSetChanged();
                    mTextViewTotalPrice.setText(String.valueOf(0.00));
                    checkItemCount();
                }
                break;
            case R.id.tv_top_shop_cart_remove_selected:
                //todo
                final List<MultipleItemEntity> data = mAdapter.getData();
                //要删除的数据
                final List<MultipleItemEntity> deleteEntities = new ArrayList<>();
                for (MultipleItemEntity entity : data) {
                    final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
                    if (isSelected) {
                        deleteEntities.add(entity);
                    }
                }

                for (int i = (deleteEntities.size() - 1);i >= 0; i--) {
                    MultipleItemEntity entity = deleteEntities.get(i);
                    int removePosition = i;
                    final int entityPosition = entity.getField(ShopCartItemFields.POSITION);
                    mAdapter.remove(removePosition);
                }

                mAdapter.notifyDataSetChanged();
                checkItemCount();
                mTextViewTotalPrice.setText(String.valueOf(getTotalPrice()));
                break;
            default:
                break;
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
     *
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
        mTotalPrice = totalPrice;
        Latte.getHandler().post(new Runnable() {
            @Override
            public void run() {
                mTextViewTotalPrice.setText(String.valueOf(totalPrice));
            }
        });
    }

    @Override
    public void getLeftCheck(boolean isAllCheck) {
        if (isAllCheck) {
            mTextViewSelectAll.setTextColor
                    (ContextCompat.getColor(getContext(), R.color.app_main));
        } else {
            mTextViewSelectAll.setTextColor(Color.GRAY);
        }
    }

    @SuppressWarnings("RestrictedApi")
    private void checkItemCount() {
        final int count = mAdapter.getItemCount();
        if (count == 0) {
            final View stubView = mStubNoItem.inflate();
            final AppCompatTextView tvToBuy =
                    (AppCompatTextView) stubView.findViewById(R.id.tv_stub_to_buy);
            tvToBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "你该购物啦！", Toast.LENGTH_SHORT).show();
                }
            });
            mRecyclerView.setVisibility(View.GONE);
            mLinearLayout.setVisibility(View.GONE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            mLinearLayout.setVisibility(View.VISIBLE);
        }
    }

    private double getTotalPrice() {

        double currentPrice = 0;
        for (MultipleItemEntity entity : mAdapter.getData()) {
            if (entity.getField(ShopCartItemFields.IS_SELECTED)) {

                final double price = entity.getField(ShopCartItemFields.PRICE);
                final int count = entity.getField(ShopCartItemFields.COUNT);
                final double total = price * count;
                Log.e("tang", price + "=====" + count);
                currentPrice = currentPrice + total;
                Log.e("tang", price + "===" + count);
            }
        }

        mTotalPrice = currentPrice;

        return mTotalPrice;
    }
}
