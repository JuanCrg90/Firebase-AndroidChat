package me.juancrg90.android.androidchat.chat;

/**
 * Created by JuanCrg90
 */
public interface ChatRepository {
    void sendMessage(String msg);
    void setChatRecipient(String recipient);

    void subscribe();
    void unsubscribe();
    void destroyListener();
    void changeConnectionStatus(boolean online);
}
