package me.juancrg90.android.androidchat.addContact.ui;

/**
 * Created by JuanCrg90
 */
public interface AddContactView {
    void showInput();
    void hideInput();
    void showProgress();
    void hideProgress();

    void contactAdded();
    void contactNotAdded();
}
