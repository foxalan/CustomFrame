package com.example.alan.customframe.delegate.home;

import com.example.alan.customframe.delegate.discover.DiscoverDelegate;
import com.example.alan.customframe.delegate.home.bottom.BaseBottomDelegate;
import com.example.alan.customframe.delegate.home.bottom.BaseBottomItemDelegate;
import com.example.alan.customframe.delegate.home.bottom.BottomItemBean;
import com.example.alan.customframe.delegate.home.bottom.ItemBuilder;
import com.example.alan.customframe.delegate.index.IndexDelegate;
import com.example.alan.customframe.delegate.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Function :
 * Modify Date : 2018/1/23
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class HomeDelegate extends BaseBottomDelegate {

    @Override
    public LinkedHashMap<BottomItemBean, BaseBottomItemDelegate> setItems(ItemBuilder builder) {

        final LinkedHashMap<BottomItemBean, BaseBottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomItemBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomItemBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomItemBean("{fa-compass}","发现"),new DiscoverDelegate());
        return builder.addItems(items).build();

    }

    @Override
    public int setClickColor() {
        return 0;
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }
}
