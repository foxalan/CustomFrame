package com.example.alan.customframe.delegate.personal;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.alan.customframe.R;
import com.example.alan.customframe.delegate.home.bottom.BaseBottomItemDelegate;
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

public class PersonalDelegate extends BaseBottomItemDelegate {

    @BindView(R.id.rv_personal_setting)
    RecyclerView mRecyclerView;
    List<ListBean> data;


    @Override
    public Object getLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onBindView() {
        initData();
        setRecyclerView();

    }

    private void initData() {
        ListBean address = new ListBean.Builder()
                .setId(1)
                .setItemType(ListItemType.ITEM_NORMAL)
                .setText("收货地址")
                .build();

        ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setText("系统设置")
                .build();

        data = new ArrayList<>();
        data.clear();
        data.add(address);
        data.add(system);
    }

    private void setRecyclerView() {

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
    }
}
