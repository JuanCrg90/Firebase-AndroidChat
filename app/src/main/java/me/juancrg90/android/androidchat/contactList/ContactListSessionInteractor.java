package me.juancrg90.android.androidchat.contactList;

/**
 * Created by JuanCrg90
 */
public interface ContactListSessionInteractor {
    void signoff();
    String getCurrentUserEmail();
    void changeConnectionStatus(boolean online);
}
