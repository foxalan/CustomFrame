package com.example.alan.customframe.delegate.personal.profile;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.alan.customframe.R;
import com.example.alan.customframe.delegate.LatteDelegate;
import com.example.alan.customframe.delegate.personal.list.ListAdapter;
import com.example.alan.customframe.delegate.personal.list.ListBean;
import com.example.alan.customframe.delegate.personal.list.ListItemType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Function :
 * Modify Date : 2018/1/30
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class UserProfileDelegate extends LatteDelegate {

    @BindView(R.id.rv_user_profile)
    RecyclerView mRecyclerView;


    private List<ListBean> data;

    @Override
    public Object getLayout() {
        return R.layout.delegate_user_profile;
    }

    @Override
    public void onBindView() {
        initData();
        setRecyclerView();
    }

    private void initData() {

        final ListBean image = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_IMAGE)
                .setId(1)
                .setImageUrl("http://i9.qhimg.com/t017d891ca365ef60b5.jpg")
                .build();

        final ListBean name = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setText("姓名")
                .setValue("未设置姓名")
                .build();

        final ListBean gender = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(3)
                .setText("性别")
                .setValue("未设置性别")
                .build();

        final ListBean birth = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(4)
                .setText("生日")
                .setValue("未设置生日")
                .build();

        data = new ArrayList<>();
        data.add(image);
        data.add(name);
        data.add(gender);
        data.add(birth);
    }

    private void setRecyclerView() {
        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
    //   mRecyclerView.addOnItemTouchListener(new UserProfileClickListener(this));
    }
}
