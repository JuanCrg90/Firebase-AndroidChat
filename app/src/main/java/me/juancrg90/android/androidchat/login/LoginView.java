package me.juancrg90.android.androidchat.login;

/**
 * Created by JuanCrg90
 */
public interface LoginView {
    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();

    void handleSignUp();
    void handleSignIn();

    void navigateToMainScreen();
    void loginError(String error);

    void newUsersSuccess();
    void newUsersError(String error);

}
