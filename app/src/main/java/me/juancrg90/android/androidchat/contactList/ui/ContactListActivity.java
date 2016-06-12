package me.juancrg90.android.androidchat.contactList.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.juancrg90.android.androidchat.R;
import me.juancrg90.android.androidchat.addContact.ui.AddContactFragment;
import me.juancrg90.android.androidchat.chat.ChatActivity;
import me.juancrg90.android.androidchat.contactList.ContactListPresenter;
import me.juancrg90.android.androidchat.contactList.ContactListPresenterImpl;
import me.juancrg90.android.androidchat.contactList.ui.adapters.ContactListAdapter;
import me.juancrg90.android.androidchat.contactList.ui.adapters.OnItemClickListener;
import me.juancrg90.android.androidchat.entities.User;
import me.juancrg90.android.androidchat.lib.GlideImageLoader;
import me.juancrg90.android.androidchat.lib.ImageLoader;
import me.juancrg90.android.androidchat.login.ui.LoginActivity;

public class ContactListActivity extends AppCompatActivity implements ContactListView, OnItemClickListener{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerViewContacts)
    RecyclerView recyclerViewContacts;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    private ContactListAdapter adapter;
    private ContactListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        ButterKnife.bind(this);
        
        setupAdapter();
        setupRecyclerView();

        presenter = new ContactListPresenterImpl(this);
        presenter.onCreate();

        setupToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contactlist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_logout) {
            presenter.signOff();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP
            | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView() {
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewContacts.setAdapter(adapter);
    }

    private void setupAdapter() {
        ImageLoader loader = new GlideImageLoader(this.getApplicationContext());
        adapter = new ContactListAdapter(new ArrayList<User>(), loader, this);
    }

    private void setupToolbar() {
        toolbar.setTitle(presenter.getCurrentUserEmail());
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }


    @OnClick(R.id.fab)
    public void addContact() {
        new AddContactFragment().show(getSupportFragmentManager(), getString(R.string.addcontact_message_title));
    }

    @Override
    public void onContactAdded(User user) {
        adapter.add(user);
    }

    @Override
    public void onContactChanged(User user) {
        adapter.update(user);

    }

    @Override
    public void onContactRemoved(User user) {
        adapter.remove(user);
    }

    @Override
    public void onItemClick(User user) {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(ChatActivity.EMAIL_KEY, user.getEmail());
        intent.putExtra(ChatActivity.ONLINE_KEY, user.isOnline());
        startActivity(intent);

    }

    @Override
    public void onItemLongClick(User user) {
        presenter.removeContact(user.getEmail());
    }
}
