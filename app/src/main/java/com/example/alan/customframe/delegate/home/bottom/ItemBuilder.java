package com.example.alan.customframe.delegate.home.bottom;

import java.util.LinkedHashMap;

/**
 * Function :
 * Modify Date : 2018/1/23
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class ItemBuilder {
    private final LinkedHashMap<BottomItemBean,BaseBottomItemDelegate> ITEMS = new LinkedHashMap<>();


    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomItemBean bean, BaseBottomItemDelegate delegate) {
        ITEMS.put(bean, delegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomItemBean, BaseBottomItemDelegate> items) {
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomItemBean, BaseBottomItemDelegate> build() {
        return ITEMS;
    }
}
