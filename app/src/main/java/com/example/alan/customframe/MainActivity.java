package com.example.alan.customframe;

import com.example.alan.customframe.activity.ProxyActivity;
import com.example.alan.customframe.delegate.LatteDelegate;
import com.example.alan.customframe.delegate.launcher.AdvertisementDelegate;

public class MainActivity extends ProxyActivity {



    @Override
    public LatteDelegate setRootDelegate() {
        return new AdvertisementDelegate();
    }




}
