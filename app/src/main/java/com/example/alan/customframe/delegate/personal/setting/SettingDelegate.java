package com.example.alan.customframe.delegate.personal.setting;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.alan.customframe.R;
import com.example.alan.customframe.delegate.LatteDelegate;
import com.example.alan.customframe.delegate.personal.address.AddressDelegate;
import com.example.alan.customframe.delegate.personal.list.ListAdapter;
import com.example.alan.customframe.delegate.personal.list.ListBean;
import com.example.alan.customframe.delegate.personal.list.ListItemType;
import com.example.alan.customframe.util.callback.CallbackManager;
import com.example.alan.customframe.util.callback.CallbackType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Function :
 * Modify Date : 2018/1/31
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class SettingDelegate extends LatteDelegate {

    @BindView(R.id.rv_settings)
    RecyclerView mRecyclerView;


    @Override
    public Object getLayout() {
        return R.layout.delegate_settings;
    }

    @Override
    public void onBindView() {
        initData();
    }

    private void initData() {
        final ListBean push = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_SWITCH)
                .setId(1)
                .setDelegate(new AddressDelegate())
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @SuppressWarnings("unchecked")
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            CallbackManager.getInstance().getCallback(CallbackType.TAG_OPEN_PUSH).executeCallback(null);
                            Toast.makeText(getContext(), "打开推送", Toast.LENGTH_SHORT).show();
                        } else {
                            CallbackManager.getInstance().getCallback(CallbackType.TAG_STOP_PUSH).executeCallback(null);
                            Toast.makeText(getContext(), "关闭推送", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setText("消息推送")
                .build();

        final ListBean about = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
//                .setDelegate(new AboutDelegate())
                .setText("关于")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(push);
        data.add(about);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
    //    mRecyclerView.addOnItemTouchListener(new SettingsClickListener(this));
    }
}
