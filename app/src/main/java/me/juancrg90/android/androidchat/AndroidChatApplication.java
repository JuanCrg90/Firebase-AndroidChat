package me.juancrg90.android.androidchat;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by JuanCrg90
 */
public class AndroidChatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
    }

    private void setupFirebase() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
