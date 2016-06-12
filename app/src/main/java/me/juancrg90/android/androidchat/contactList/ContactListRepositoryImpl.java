package me.juancrg90.android.androidchat.contactList;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import me.juancrg90.android.androidchat.contactList.events.ContactListEvent;
import me.juancrg90.android.androidchat.domain.FirebaseHelper;
import me.juancrg90.android.androidchat.entities.User;
import me.juancrg90.android.androidchat.lib.EventBus;
import me.juancrg90.android.androidchat.lib.GreenRobotEventBus;

/**
 * Created by JuanCrg90
 */
public class ContactListRepositoryImpl implements ContactListRepository {
    private FirebaseHelper helper;
    private EventBus eventBus;
    private ChildEventListener contactEventListener;

    public ContactListRepositoryImpl() {
        this.eventBus = GreenRobotEventBus.getInstance();
        this.helper = FirebaseHelper.getInstance().getInstance();
    }

    @Override
    public void signOff() {
        helper.signoff();

    }

    @Override
    public String getCurrentUserEmail() {
        return helper.getAuthUserEmail();
    }

    @Override
    public void removeContact(String email) {
        String currentUserEmail = helper.getAuthUserEmail();
        helper.getOneContactReference(currentUserEmail, email).removeValue();
        helper.getOneContactReference(email, currentUserEmail).removeValue();

    }

    @Override
    public void destroyListener() {
        contactEventListener = null;

    }

    @Override
    public void subscribeToContactListEvent() {
        if(contactEventListener == null) {
            contactEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    handleContact(dataSnapshot, ContactListEvent.onContactAdded);

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    handleContact(dataSnapshot, ContactListEvent.onContactChanged);

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    handleContact(dataSnapshot, ContactListEvent.onContactRemoved);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            }

        }
        helper.getMyContactsReference().addChildEventListener(contactEventListener);

    }

    private void handleContact(DataSnapshot dataSnapshot, int type) {
        String email = dataSnapshot.getKey();
        email = email.replace("_", ".");
        boolean online = ((Boolean) dataSnapshot.getValue()).booleanValue();
        User user = new User();
        user.setEmail(email);
        user.setOnline(online);
        post(type, user);
    }

    private void post(int type, User user) {
        ContactListEvent event = new ContactListEvent()();
        event.setEventType(type);
        event.setUser(user);
        eventBus.post(event);


    }

    @Override
    public void unsubscribeToContactListEvent() {
        if(contactEventListener != null) {
            helper.getMyContactsReference().removeEventListener(contactEventListener);
        }

    }

    @Override
    public void changeConnectionStatus(boolean online) {

    }
}
