package me.juancrg90.android.androidchat.chat.ui;

import me.juancrg90.android.androidchat.entities.ChatMessage;

/**
 * Created by JuanCrg90
 */
public interface ChatView {
    void onChatMessageReceived(ChatMessage msg);
}
