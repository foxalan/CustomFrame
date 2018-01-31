package com.example.alan.customframe.delegate.personal.list;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
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
        addItemType(ListItemType.ITEM_IMAGE, R.layout.item_list_image);
        addItemType(ListItemType.ITEM_SWITCH, R.layout.arrow_switch_layout);

    }

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .centerCrop();

    @Override
    protected void convert(BaseViewHolder holder, ListBean item) {
        switch (holder.getItemViewType()) {
            case ListItemType.ITEM_NORMAL:
                AppCompatTextView mTextViewContent = holder.getView(R.id.list_item_normal_content);
                String mContent = item.getContent();
                mTextViewContent.setText(mContent);
                break;
            case ListItemType.ITEM_AVATAR:
                AppCompatTextView mAvatarContent = holder.getView(R.id.list_item_avatar_content);
                AppCompatTextView mAvatarValue = holder.getView(R.id.list_item_avatar_value);
                String mContentAvatar = item.getContent();
                String mContentValue = item.getBranch();
                mAvatarContent.setText(mContentAvatar);
                mAvatarValue.setText(mContentValue);
                break;
            case ListItemType.ITEM_IMAGE:
                String mUrl = item.getUrl();
                Glide.with(mContext)
                        .load(mUrl)
                        .apply(OPTIONS)
                        .into((ImageView) holder.getView(R.id.img_arrow_image));
                break;
            case ListItemType.ITEM_SWITCH:
                holder.setText(R.id.tv_arrow_switch_text, item.getContent());
                final SwitchCompat switchCompat = holder.getView(R.id.list_item_switch);
                switchCompat.setChecked(true);
                switchCompat.setOnCheckedChangeListener(item.getOnCheckedChangeListener());
            default:
                break;
        }
    }
}
