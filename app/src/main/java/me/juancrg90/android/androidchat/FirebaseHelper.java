package me.juancrg90.android.androidchat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by JuanCrg90
 */
public class FirebaseHelper {
    private DatabaseReference databaseReference;

    private final static String SEPARATOR = "__";
    private final static String CHATS_PATH = "chats";
    private final static String USERS_PATH = "users";
    private final static String CONTACTS_PATH = "contacts";

    private static class SingletonHolder {
        private static final FirebaseHelper INSTANCE = new FirebaseHelper();
    }

    public static FirebaseHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public FirebaseHelper() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getDataReference() {
        return databaseReference;
    }

    public String getAuthUserEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = null;

        if(user != null) {
            email = user.getEmail();
        }

        return email;
    }

    public DatabaseReference getUserReference(String email) {
        DatabaseReference userReference = null;

        if(email != null) {
            String emailKey = email.replace(".", "_");
            userReference = databaseReference.getRoot().child(USERS_PATH).child(emailKey);
        }

        return  userReference;
    }

    public  DatabaseReference getMyUserReference() {
        return getUserReference(getAuthUserEmail());
    }

    public DatabaseReference getContactsReference(String email) {
        return getUserReference(email).child(CONTACTS_PATH);
    }

    public DatabaseReference getMyContactsReference() {
        return getContactsReference(getAuthUserEmail());
    }

    public DatabaseReference getOneContactReference(String mainEmail, String childEmail) {
        String childKey = childEmail.replace(".", "_");
        return getUserReference(mainEmail).child(CONTACTS_PATH).child(childKey);
    }

    public DatabaseReference getChatReference(String receiver) {
        String keySender = getAuthUserEmail().replace(".", "_");
        String keyReceiver = receiver.replace(".", "_");

        String keyChat = keySender + SEPARATOR + KeyReceiver;

        if(keySender.compareTo(keyReceiver) > 0) {
            keyChat = keyReceiver + SEPARATOR + keySender;
        }

        return databaseReference.getRoot().child(CHATS_PATH).child(keyChat);
    }

    public void changeUserConnectionStatus(boolean online) {
        if(getMyUserReference() != null) {
            Map<String, Object> updates = new HashMap<String, Object>();
            updates.put("online", online);
            getMyUserReference().updateChildren(updates);
            notifyContactsOfConnectionChange(boolean online);
        }
    }

    public void notifyContactsOfConnectionChange(Object online) {
        notifyContactsOfConnectionChange(online, false);
    }

    public void signoff() {
        notifyContactsOfConnectionChange(false, true);
    }


    private void notifyContactsOfConnectionChange(final Object online, final boolean signoff) {
        final String myEmail = getAuthUserEmail();

        getMyContactsReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String email = child.getKey();
                     DatabaseReference reference  = getContactsReference(email, myEmail );
                    reference.setValue(online);
                }
                if(signoff) {
                    FirebaseAuth.getInstance().signOut();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
