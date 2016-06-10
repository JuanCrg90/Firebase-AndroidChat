package me.juancrg90.android.androidchat.login;

/**
 * Created by JuanCrg90
 */
public interface LoginInteractor {
    void checkSession();
    void doSignUp(String email, String password);
    void doSignIn(String email, String password);
}
