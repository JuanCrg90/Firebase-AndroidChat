package me.juancrg90.android.androidchat.chat;

import me.juancrg90.android.androidchat.chat.events.ChatEvent;

/**
 * Created by JuanCrg90
 */
public interface ChatPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void setChatRecipient(String recipient);
    void sendMessage(String msg);
    void onEventMainThrread(ChatEvent event);

}
