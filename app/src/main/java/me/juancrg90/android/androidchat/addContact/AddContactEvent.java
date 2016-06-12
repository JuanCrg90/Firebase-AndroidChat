package me.juancrg90.android.androidchat.addContact;

/**
 * Created by JuanCrg90
 */
public class AddContactEvent {
    private boolean error = false;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }


}
