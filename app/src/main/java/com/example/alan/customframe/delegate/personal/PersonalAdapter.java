package com.example.alan.customframe.delegate.personal;

import com.example.alan.customframe.recycler.MultipleItemEntity;
import com.example.alan.customframe.recycler.MultipleRecyclerAdapter;
import com.example.alan.customframe.recycler.MultipleViewHolder;

import java.util.List;

/**
 * Function :
 * Modify Date : 2018/1/30
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class PersonalAdapter extends MultipleRecyclerAdapter {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public PersonalAdapter(List<MultipleItemEntity> data) {
        super(data);
    }

    @Override
    protected void convert(MultipleViewHolder helper, MultipleItemEntity item) {
        super.convert(helper, item);
    }
}
