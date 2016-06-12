package me.juancrg90.android.androidchat.entities;

import java.util.Map;

/**
 * Created by JuanCrg90
 */
public class User {
    private String email;
    private boolean online;
    private Map<String, Boolean> contacts;
    public static final boolean ONLINE = true;
    public static final boolean OFFLINE= false;


    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public Map<String, Boolean> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String, Boolean> contacts) {
        this.contacts = contacts;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equal = false;

        if(obj instanceof User) {
            User recipe = (User)obj;
            equal = this.email.equals(recipe.getEmail());
        }

        return equal;
    }

}
