package me.juancrg90.android.androidchat.contactList.ui;

import me.juancrg90.android.androidchat.entities.User;

/**
 * Created by JuanCrg90
 */
public interface ContactListView {
    void onContactAdded(User user);
    void onContactChanged(User user);
    void onContactRemoved(User user);
}
