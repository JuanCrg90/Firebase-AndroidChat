package me.juancrg90.android.androidchat.lib;

/**
 * Created by JuanCrg90
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);

}
