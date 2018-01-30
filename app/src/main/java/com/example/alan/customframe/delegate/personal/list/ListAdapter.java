package com.example.alan.customframe.delegate.personal.list;

import android.support.v7.widget.AppCompatTextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.alan.customframe.R;

import java.util.List;

/**
 * Function :
 * Modify Date : 2018/1/30
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ListAdapter(List<ListBean> data) {
        super(data);

        addItemType(ListItemType.ITEM_NORMAL, R.layout.item_list_normal);
        addItemType(ListItemType.ITEM_AVATAR, R.layout.item_list_avatar);
    }

    @Override
    protected void convert(BaseViewHolder holder, ListBean item) {
        switch (holder.getItemViewType()) {
            case ListItemType.ITEM_NORMAL:
                AppCompatTextView mTextViewContent = holder.getView(R.id.list_item_normal_content);
                String mContent = item.getContent();
                mTextViewContent.setText(mContent);
                break;
            case ListItemType.ITEM_AVATAR:

                break;
            default:
                break;
        }
    }
}
