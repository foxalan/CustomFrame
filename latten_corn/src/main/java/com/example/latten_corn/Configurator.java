package com.example.latten_corn;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Alan on 2017/12/8.
 */

public class Configurator {

    private static final HashMap<String, Object> LATTE_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    public final HashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    public Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    /**
     * 内部类的单例模式
     */
    private static class ViewHolder {
        static final Configurator INSTANCE = new Configurator();
    }

    //初始化字体图标
    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }


    public static Configurator getInstance() {
        return ViewHolder.INSTANCE;
    }

    //设置完成
    public final void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);

    }

    //添加数据
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    //添加字体图标
    public final Configurator withIconFont(IconFontDescriptor iconFont) {
        ICONS.add(iconFont);
        return this;
    }


    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready , call configure");
        }
    }

    @SuppressWarnings("unchecked")
    public final <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }

}
