package me.juancrg90.android.androidchat.contactList;

import me.juancrg90.android.androidchat.contactList.events.ContactListEvent;

/**
 * Created by JuanCrg90
 */
public interface ContactListPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void onEventMainThread(ContactListEvent event);


}
