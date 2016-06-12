package me.juancrg90.android.androidchat.contactList.ui.adapters;

import me.juancrg90.android.androidchat.entities.User;

/**
 * Created by JuanCrg90
 */
public interface OnItemClickListener {
    void onItemClick(User user);
    void onItemLongClick(User user);
}
