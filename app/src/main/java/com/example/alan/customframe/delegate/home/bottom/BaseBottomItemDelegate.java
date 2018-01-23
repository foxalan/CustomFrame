package com.example.alan.customframe.delegate.home.bottom;

import android.widget.Toast;

import com.example.alan.customframe.R;
import com.example.alan.customframe.delegate.LatteDelegate;
import com.example.latten_corn.Latte;


/**
 * @author Alan 
 */

public abstract class BaseBottomItemDelegate extends LatteDelegate {
    /**
     * 再点一次退出程序时间设置
     */
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" + Latte.getApplicationContext().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
