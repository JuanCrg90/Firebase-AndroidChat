package me.juancrg90.android.androidchat.contactList;

/**
 * Created by JuanCrg90
 */
public interface ContactListInteractor {
    void subscribe();
    void unsubscribe();
    void destroyListener();
    void removeContact(String email);
}
