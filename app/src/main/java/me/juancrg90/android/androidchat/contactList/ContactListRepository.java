package me.juancrg90.android.androidchat.contactList;

/**
 * Created by JuanCrg90
 */
public interface ContactListRepository {
    void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void destroyListener();
    void subscribeToContactListEvent();
    void unsubscribeToContactListEvent();
    void changeConnectionStatus(boolean online);

}
