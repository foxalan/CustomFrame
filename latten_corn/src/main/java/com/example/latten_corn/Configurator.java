package com.example.latten_corn;

import java.util.WeakHashMap;

/**
 * Created by Alan on 2017/12/8.
 */

public class Configurator {

    private static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();

    public final WeakHashMap<String, Object> getLatteConfigs() {
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

    public static Configurator getInstance() {
        return ViewHolder.INSTANCE;
    }

    //设置完成
    public final void configure() {

        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);

    }

    //添加数据
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }


    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready , call configure");
        }
    }

    @SuppressWarnings("unchecked")
    public final  <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }

}
