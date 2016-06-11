package me.juancrg90.android.androidchat.login;

import android.util.Log;

import me.juancrg90.android.androidchat.domain.FirebaseHelper;

/**
 * Created by garu on 6/10/16.
 */
public class LoginRepositoryImpl implements  LoginRepository {

    private FirebaseHelper helper;

    public LoginRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
    }

    @Override
    public void signUp(String email, String password) {
        Log.e("LoginRepositoryImpl", "signUp");
    }

    @Override
    public void signIn(String email, String password) {
        Log.e("LoginRepositoryImpl", "signIn");

    }

    @Override
    public void checkSession() {
        Log.e("LoginRepositoryImpl", "Check Session");
    }
}
