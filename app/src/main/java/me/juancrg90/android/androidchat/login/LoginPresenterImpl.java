package me.juancrg90.android.androidchat.login;

/**
 * Created by JuanCrg90
 */
public class LoginPresenterImpl implements LoginPresenter {
    LoginView loginView;
    LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
    }


    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void checkForAuthenticated() {
        if(loginView != null) {
            loginView.disableInputs();
            loginView.showProgress();
        }

        loginInteractor.checkSession();
    }

    @Override
    public void validateLogin(String email, String password) {
        if(loginView != null) {
            loginView.disableInputs();
            loginView.showProgress();
        }

        loginInteractor.doSignIn(email, password);

    }

    @Override
    public void registerNewUser(String email, String password) {
        if(loginView != null) {
            loginView.disableInputs();
            loginView.showProgress();
        }

        loginInteractor.doSignUp(email, password);
    }

    private void onSignInSuccess() {
        if(loginView != null) {
            loginView.navigateToMainScreen();
        }

    }

    private void onSignUpSuccess() {
        if(loginView != null) {
            loginView.newUserSuccess();
        }

    }

    private void onSignInError(String error) {
        if(loginView != null) {
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.loginError(error);
        }
    }

    private void onSignUpError(String error) {
        if(loginView != null) {
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.newUserError(error);
        }
    }
}
