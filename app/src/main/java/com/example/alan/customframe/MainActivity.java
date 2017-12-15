package com.example.alan.customframe;

import com.example.alan.customframe.activity.ProxyActivity;
import com.example.alan.customframe.delegate.LatteDelegate;
import com.example.alan.customframe.delegate.TestDelegate;

public class MainActivity extends ProxyActivity {



    @Override
    public LatteDelegate setRootDelegate() {
        return TestDelegate.getInstance();
    }




}
