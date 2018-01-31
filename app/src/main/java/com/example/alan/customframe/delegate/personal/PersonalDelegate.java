package com.example.alan.customframe.delegate.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.alan.customframe.R;
import com.example.alan.customframe.delegate.home.bottom.BaseBottomItemDelegate;
import com.example.alan.customframe.delegate.personal.address.AddressDelegate;
import com.example.alan.customframe.delegate.personal.list.ListAdapter;
import com.example.alan.customframe.delegate.personal.list.ListBean;
import com.example.alan.customframe.delegate.personal.list.ListItemType;
import com.example.alan.customframe.delegate.personal.order.OrderListDelegate;
import com.example.alan.customframe.delegate.personal.profile.UserProfileDelegate;
import com.example.alan.customframe.delegate.personal.setting.SettingDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

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
    @BindView(R.id.tv_all_order)
    TextView mTextAllOrder;
    @BindView(R.id.img_user_avatar)
    CircleImageView mCircleImageView;

    public static final String ORDER_TYPE = "ORDER_TYPE";
    private Bundle mArgs = null;

    @OnClick({R.id.tv_all_order, R.id.img_user_avatar})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_all_order:
                mArgs.putString(ORDER_TYPE, "all");
                startOrderListByType();
                break;
            case R.id.img_user_avatar:
                getParentDelegate().getSupportDelegate().start(new UserProfileDelegate());
                break;
            default:
                break;
        }
    }

    private void startOrderListByType() {
        final OrderListDelegate delegate = new OrderListDelegate();
        delegate.setArguments(mArgs);
        getParentDelegate().getSupportDelegate().start(delegate);
    }


    private List<ListBean> data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArgs = new Bundle();
    }

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
                .setDelegate(new AddressDelegate())
                .build();

        ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setDelegate(new SettingDelegate())
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
        mRecyclerView.addOnItemTouchListener(new PersonalClickListener(this));
    }
}
