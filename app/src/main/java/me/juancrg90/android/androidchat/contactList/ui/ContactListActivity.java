package me.juancrg90.android.androidchat.contactList.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.juancrg90.android.androidchat.R;
import me.juancrg90.android.androidchat.contactList.ContactListPresenter;
import me.juancrg90.android.androidchat.contactList.ui.adapters.ContactListAdapter;
import me.juancrg90.android.androidchat.contactList.ui.adapters.OnItemClickListener;
import me.juancrg90.android.androidchat.entities.User;
import me.juancrg90.android.androidchat.lib.GlideImageLoader;
import me.juancrg90.android.androidchat.lib.ImageLoader;

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

        //presenter.onCreate();

        setupToolbar();


    }

    private void setupRecyclerView() {
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewContacts.setAdapter(adapter);
    }

    private void setupAdapter() {
        ImageLoader loader = new GlideImageLoader(this.getApplicationContext());
        User user = new User();
        user.setOnline(false);
        user.setEmail("juancrg90@gmail.com");


        //adapter = new ContactListAdapter(new ArrayList<User>(), loader, this);
        adapter = new ContactListAdapter(Arrays.asList(new User[]{user}), loader, this);
    }

    private void setupToolbar() {
        //toolbar.setTitle(presenter.getCurrentUserEmail());
        toolbar.setTitle("JuanCrg90");
        setSupportActionBar(toolbar);
    }
/*
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
*/

    @OnClick(R.id.fab)
    public void addContact(){

    }

    @Override
    public void onContactAdded(User user) {

    }

    @Override
    public void onContactChanged(User user) {

    }

    @Override
    public void onContactRemoved(User user) {

    }

    @Override
    public void onItemClick(User user) {

    }

    @Override
    public void onItemLongClick(User user) {

    }
}
