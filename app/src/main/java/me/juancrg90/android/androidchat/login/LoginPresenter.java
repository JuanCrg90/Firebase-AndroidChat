package me.juancrg90.android.androidchat.login;

import me.juancrg90.android.androidchat.login.events.LoginEvent;

/**
 * Created by JuanCrg90
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
    void onEventMainThread(LoginEvent event);
}