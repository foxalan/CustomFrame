package com.example.alan.customframe.delegate.cart;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.alan.customframe.R;
import com.example.alan.customframe.latte.Latte;
import com.example.alan.customframe.recycler.MultipleFields;
import com.example.alan.customframe.recycler.MultipleItemEntity;
import com.example.alan.customframe.recycler.MultipleRecyclerAdapter;
import com.example.alan.customframe.recycler.MultipleViewHolder;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

/**
 * Function :
 * Modify Date : 2018/1/29
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class ShopCartAdapter extends MultipleRecyclerAdapter {
    private ISelectedChangeListener iSelectedChangeListener;

    private boolean mIsSelectedAll = true;

    public void setIsSelectedAll(boolean mIsSelectedAll) {
        this.mIsSelectedAll = mIsSelectedAll;
    }

    public void setSelectedChangeListener(ISelectedChangeListener iSelectedChangeListener) {
        this.iSelectedChangeListener = iSelectedChangeListener;
    }

    private double mTotalPrice = 0.00;
    private List<MultipleItemEntity> currentItemList;

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    public double getTotalPrice() {
        return mTotalPrice;
    }

    public void setmTotalPrice(double mTotalPrice) {
        this.mTotalPrice = mTotalPrice;
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */


    public ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        this.currentItemList = data;
        //初始化总价
        for (MultipleItemEntity entity : data) {
            final double price = entity.getField(ShopCartItemFields.PRICE);
            final int count = entity.getField(ShopCartItemFields.COUNT);
            final double total = price * count;
            mTotalPrice = mTotalPrice + total;
        }

        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }

    private double reComputeTotalPrice() {
        mTotalPrice = 0.00;
        for (MultipleItemEntity entity : currentItemList) {
            if (entity.getField(ShopCartItemFields.IS_SELECTED)) {

                final double price = entity.getField(ShopCartItemFields.PRICE);
                final int count = entity.getField(ShopCartItemFields.COUNT);
                final double total = price * count;
                mTotalPrice = mTotalPrice + total;
                Log.e("tang",price+"==="+count);
            }
        }
        return mTotalPrice;
    }


    @Override
    protected void convert(MultipleViewHolder holder, final MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ShopCartItemType.SHOP_CART_ITEM:

                //先取出所有值
                final int id = entity.getField(MultipleFields.ID);
                final String thumb = entity.getField(MultipleFields.IMAGE_URL);
                final String title = entity.getField(ShopCartItemFields.TITLE);
                final String desc = entity.getField(ShopCartItemFields.DESC);
                final int count = entity.getField(ShopCartItemFields.COUNT);
                final double price = entity.getField(ShopCartItemFields.PRICE);
                // 取出所以控件
                final AppCompatImageView imgThumb = holder.getView(R.id.image_item_shop_cart);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_cart_count);
                final IconTextView iconIsSelected = holder.getView(R.id.icon_item_shop_cart);
                //赋值
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvPrice.setText(String.valueOf(price));
                tvCount.setText(String.valueOf(count));
                Glide.with(mContext)
                        .load(thumb)
                        .apply(OPTIONS)
                        .into(imgThumb);
                //获取控件状态，是否被选中
                //在左侧勾勾渲染之前改变全选与否状态
                entity.setField(ShopCartItemFields.IS_SELECTED, mIsSelectedAll);
                final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
                //根据数据状态显示左侧勾勾
                if (isSelected) {
                    iconIsSelected.setTextColor
                            (ContextCompat.getColor(Latte.getApplicationContext(), R.color.app_main));
                } else {
                    iconIsSelected.setTextColor(Color.GRAY);

                }
                //设置点击事件

                //添加左侧勾勾点击事件
                iconIsSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final boolean currentSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
                        if (currentSelected) {
                            iconIsSelected.setTextColor(Color.GRAY);
                            entity.setField(ShopCartItemFields.IS_SELECTED, false);
                        } else {
                            iconIsSelected.setTextColor
                                    (ContextCompat.getColor(Latte.getApplicationContext(), R.color.app_main));
                            entity.setField(ShopCartItemFields.IS_SELECTED, true);
                        }
                        changeCurrentPrice();
                        iSelectedChangeListener.getLeftCheck(checkAllIsSelected());
                    }
                });

                //添加加减事件
                iconMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentCount = entity.getField(ShopCartItemFields.COUNT);

                        if (Integer.parseInt(tvCount.getText().toString()) > 1) {
                            int count = currentCount - 1;
                            entity.setField(ShopCartItemFields.COUNT, count);
                            tvCount.setText(String.valueOf(count));
                            changeCurrentPrice();
                        }
                    }
                });

                iconPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentCount = entity.getField(ShopCartItemFields.COUNT);
                        entity.setField(ShopCartItemFields.COUNT, currentCount + 1);
                        tvCount.setText(String.valueOf(currentCount+1));
                        changeCurrentPrice();
                    }
                });


                break;
            default:
                break;
        }
    }

    private void changeCurrentPrice(){
        if (iSelectedChangeListener != null) {
            iSelectedChangeListener.getTotalPrice(reComputeTotalPrice());
        }
    }

    private boolean checkAllIsSelected() {
        for (MultipleItemEntity entity : currentItemList) {
            if (entity.getField(ShopCartItemFields.IS_SELECTED)) {
            }else {
                return false;
            }
        }

        return true;
    }
}
