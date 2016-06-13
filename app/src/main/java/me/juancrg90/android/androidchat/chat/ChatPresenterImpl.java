package me.juancrg90.android.androidchat.chat;

import me.juancrg90.android.androidchat.chat.events.ChatEvent;
import me.juancrg90.android.androidchat.chat.ui.ChatView;
import me.juancrg90.android.androidchat.entities.User;
import me.juancrg90.android.androidchat.lib.EventBus;
import me.juancrg90.android.androidchat.lib.GreenRobotEventBus;

/**
 * Created by JuanCrg90
 */
public class ChatPresenterImpl implements ChatPresenter {
    EventBus eventBus;
    ChatView view;
    ChatInteractor chatInteractor;
    ChatSessionInteractor sessionInteractor;

    public ChatPresenterImpl(ChatView view) {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.chatInteractor = new ChatInteractorImpl();
        this.sessionInteractor = new ChatSessionInteractorImpl();

    }

    @Override
    public void onPause() {
        chatInteractor.unsubscribe();
        sessionInteractor.changeConnectionStatus(User.OFFLINE);

    }

    @Override
    public void onResume() {
        chatInteractor.subscribe();
        sessionInteractor.changeConnectionStatus(User.ONLINE);

    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        chatInteractor.destroyListener();
        view = null;
    }

    @Override
    public void setChatRecipient(String recipient) {
        chatInteractor.setChatRecipient(recipient);

    }

    @Override
    public void sendMessage(String msg) {
        chatInteractor.sendMessage(msg);

    }

    @Override
    public void onEventMainThrread(ChatEvent event) {

        if(view != null) {
            view.onChatMessageReceived(event.getMessage());
        }

    }
}
