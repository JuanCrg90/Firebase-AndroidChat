package me.juancrg90.android.androidchat.addContact;

import me.juancrg90.android.androidchat.addContact.AddContactEvent;
import me.juancrg90.android.androidchat.addContact.AddContactPresenter;
import me.juancrg90.android.androidchat.addContact.ui.AddContactView;

/**
 * Created by JuanCrg90
 */
public class addContactPresenterImpl implements AddContactPresenter {
    private AddContactView view;

    public addContactPresenterImpl(AddContactView view) {
        this.view = view;
    }

    @Override
    public void onShow() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void addContact(String email) {

    }

    @Override
    public void onEventMainThread(AddContactEvent event) {

    }
}
