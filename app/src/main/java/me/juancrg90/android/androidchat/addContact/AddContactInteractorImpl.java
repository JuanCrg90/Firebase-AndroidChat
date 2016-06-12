package me.juancrg90.android.androidchat.addContact;

/**
 * Created by JuanCrg90
 */
public class AddContactInteractorImpl implements AddContactInteractor {
    AddContactRepository repository;

    public AddContactInteractorImpl() {
        this.repository = new AddContactRepositoryImpl();
    }

    @Override
    public void execute(String email) {
        repository.addContact(email);
    }
}
