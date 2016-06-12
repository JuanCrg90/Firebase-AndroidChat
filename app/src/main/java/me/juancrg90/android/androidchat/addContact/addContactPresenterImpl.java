package me.juancrg90.android.androidchat.addContact;

import org.greenrobot.eventbus.Subscribe;

import me.juancrg90.android.androidchat.addContact.AddContactEvent;
import me.juancrg90.android.androidchat.addContact.AddContactPresenter;
import me.juancrg90.android.androidchat.addContact.ui.AddContactView;
import me.juancrg90.android.androidchat.lib.EventBus;
import me.juancrg90.android.androidchat.lib.GreenRobotEventBus;

/**
 * Created by JuanCrg90
 */
public class addContactPresenterImpl implements AddContactPresenter {
    private EventBus eventBus;
    private AddContactView view;
    private AddContactInteractor interactor;

    public addContactPresenterImpl(AddContactView view) {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.interactor = new AddContactInteractorImpl();

    }

    @Override
    public void onShow() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Override
    public void addContact(String email) {
        if(view != null){
            view.hideInput();
            view.showProgress();
        }

        interactor.execute(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(AddContactEvent event) {

        if(view != null){
            view.hideProgress();
            view.showInput();

            if(event.isError()) {
                view.contactNotAdded();
            } else {
                view.contactAdded();
            }
        }
    }
}
