package me.juancrg90.android.androidchat.login;

/**
 * Created by JuanCrg90
 */
public interface LoginPresenter {
    void onDestroy();
    void checkForAuthenticatedUser();
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
}