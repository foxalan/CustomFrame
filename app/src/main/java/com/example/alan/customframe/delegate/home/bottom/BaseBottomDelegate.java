package com.example.alan.customframe.delegate.home.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.alan.customframe.R;
import com.example.alan.customframe.delegate.LatteDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;

/**
 * Function :
 * Modify Date : 2018/1/23
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener {

    private List<BottomItemBean> mBottomItemBeans = new ArrayList<>();
    private List<BaseBottomItemDelegate> mBaseBottomItemDelegates = new ArrayList<>();
    private final LinkedHashMap<BottomItemBean, BaseBottomItemDelegate> ITEMS = new LinkedHashMap<>();

    @BindView(R.id.bottom_bar)
    LinearLayoutCompat mBottomBar = null;

    private int mCurrentDelegate = 0;
    private int mIndexDelegate = 0;

    /**
     * 设置数据
     *
     * @param builder
     * @return
     */
    public abstract LinkedHashMap<BottomItemBean, BaseBottomItemDelegate> setItems(ItemBuilder builder);

    /**
     * 设置点击颜色
     *
     * @return
     */
    @ColorInt
    public abstract int setClickColor();

    /**
     * 设置当前Delegate
     *
     * @return
     */
    public abstract int setIndexDelegate();

    private int mClickedColor = Color.RED;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * 分配数据
         */
        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomItemBean, BaseBottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);
        for (Map.Entry<BottomItemBean, BaseBottomItemDelegate> item : ITEMS.entrySet()) {
            final BottomItemBean key = item.getKey();
            final BaseBottomItemDelegate value = item.getValue();
            mBottomItemBeans.add(key);
            mBaseBottomItemDelegates.add(value);
        }
        /**
         * 设置颜色
         */

        if (setClickColor() != 0) {
            mClickedColor = setClickColor();
        }
        //当前的Delegate
        mIndexDelegate = setIndexDelegate();
    }

    @Override
    public Object getLayout() {
        return R.layout.delegate_bottom;
    }

    @Override
    public void onBindView() {
        int size = ITEMS.size();
        for (int i = 0; i < ITEMS.size(); i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
            RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            IconTextView iconTextView = (IconTextView) item.getChildAt(0);
            AppCompatTextView compatTextView = (AppCompatTextView) item.getChildAt(1);
            BottomItemBean bean = mBottomItemBeans.get(i);
            //初始化数据
            iconTextView.setText(bean.getICON());
            compatTextView.setText(bean.getTITLE());
            //设置点击事件
            item.setTag(i);
            item.setOnClickListener(this);

            if (i == mIndexDelegate) {
                iconTextView.setTextColor(mClickedColor);
                compatTextView.setTextColor(mClickedColor);
            }
        }

        final ISupportFragment[] delegateArray = mBaseBottomItemDelegates.toArray(new ISupportFragment[size]);
        getSupportDelegate().loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexDelegate, delegateArray);
    }

    @Override
    public void onClick(View v) {
        final int tag = (int) v.getTag();
        resetColor();
        final RelativeLayout item = (RelativeLayout) v;
        final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        itemIcon.setTextColor(mClickedColor);
        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(mClickedColor);
        getSupportDelegate().showHideFragment(mBaseBottomItemDelegates.get(tag), mBaseBottomItemDelegates.get(mCurrentDelegate));
        //注意先后顺序
        mCurrentDelegate = tag;
    }

    /**
     * 重置颜色
     */
    private void resetColor() {
        final int count = mBottomBar.getChildCount();
        for (int i = 0; i < count; i++) {
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            itemIcon.setTextColor(Color.GRAY);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            itemTitle.setTextColor(Color.GRAY);
        }
    }
}
