package me.juancrg90.android.androidchat.chat.events;

import me.juancrg90.android.androidchat.entities.ChatMessage;

/**
 * Created by JuanCrg90
 */
public class ChatEvent {
    private ChatMessage message;

    public ChatMessage getMessage() {
        return message;
    }

    public void setMessage(ChatMessage message) {
        this.message = message;
    }
}
