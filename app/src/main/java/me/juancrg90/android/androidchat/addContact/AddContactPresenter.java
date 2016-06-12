package me.juancrg90.android.androidchat.addContact;

/**
 * Created by JuanCrg90
 */
public interface AddContactPresenter {
    void onShow();
    void onDestroy();

    void addContact(String email);
    void onEventMainThread(AddContactEvent  event);
}
