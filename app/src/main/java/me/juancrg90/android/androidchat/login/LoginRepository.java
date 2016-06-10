package me.juancrg90.android.androidchat.login;

/**
 * Created by JuanCrg90
 */
public interface LoginRepository {
    void signUp(String email, String password);
    void signIn(String email, String password);
    void checkSession();
}
