package com.example.alan.customframe.delegate.cart;

import android.support.v7.widget.AppCompatTextView;

import com.example.alan.customframe.R;
import com.example.alan.customframe.delegate.home.bottom.BaseBottomItemDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;

/**
 * Function :
 * Modify Date : 2018/1/29
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class ShopCartDelegate extends BaseBottomItemDelegate {

    @BindView(R.id.tv_top_shop_cart_clear)
    AppCompatTextView mTextViewClear;
    @BindView(R.id.tv_top_shop_cart_remove_selected)
    AppCompatTextView mTextViewSelected;
    @BindView(R.id.icon_shop_cart_select_all)
    IconTextView mTextViewSelectAll;
    @BindView(R.id.tv_shop_cart_pay)
    AppCompatTextView mTextViewPay;




    @Override
    public Object getLayout() {
        return R.layout.delegate_cart;
    }

    @Override
    public void onBindView() {

    }
}
