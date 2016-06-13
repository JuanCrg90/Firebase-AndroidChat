package me.juancrg90.android.androidchat.chat;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import me.juancrg90.android.androidchat.chat.events.ChatEvent;
import me.juancrg90.android.androidchat.domain.FirebaseHelper;
import me.juancrg90.android.androidchat.entities.ChatMessage;
import me.juancrg90.android.androidchat.lib.EventBus;
import me.juancrg90.android.androidchat.lib.GreenRobotEventBus;

/**
 * Created by JuanCrg90
 */
public class ChatRepositoryImpl implements ChatRepository {
    private String recipient;
    private FirebaseHelper helper;
    private EventBus eventBus;
    private ChildEventListener chatEventListener;

    public ChatRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
        this.eventBus = GreenRobotEventBus.getInstance();

    }

    @Override
    public void sendMessage(String msg) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender(helper.getAuthUserEmail());
        chatMessage.setMsg(msg);

        DatabaseReference chatReference = helper.getChatReference(recipient);
        chatReference.push().setValue(chatMessage);
    }

    @Override
    public void setChatRecipient(String recipient) {
        this.recipient= recipient;

    }

    @Override
    public void subscribe() {
        if(chatEventListener == null) {
            chatEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    ChatMessage chatMessage = dataSnapshot.getValue(ChatMessage.class);
                    String msgSender = chatMessage.getSender();
                    chatMessage.setSentByMe(msgSender.equals(helper.getAuthUserEmail()));

                    ChatEvent chatEvent = new ChatEvent();
                    chatEvent.setMessage(chatMessage);
                    eventBus.post(chatEvent);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

        }
        helper.getChatReference(recipient).addChildEventListener(chatEventListener);



    }

    @Override
    public void unsubscribe() {
        if(chatEventListener != null) {
            helper.getChatReference(recipient).removeEventListener(chatEventListener);
        }

    }

    @Override
    public void destroyListener() {
        chatEventListener = null;

    }

    @Override
    public void changeConnectionStatus(boolean online) {
        helper.changeUserConnectionStatus(online);

    }
}
