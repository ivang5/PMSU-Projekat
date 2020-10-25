package com.example.pmsu_projekat;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.pmsu_projekat.adapters.ContactAdapter;
import com.example.pmsu_projekat.network.PmsuService;
import com.example.pmsu_projekat.network.RetrofitClientInstance;
import com.example.pmsu_projekat.tools.Podaci;

import java.util.List;

import model.Contact;
import model.Email;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pmsu_projekat.LoginActivity.ID;
import static com.example.pmsu_projekat.LoginActivity.SHARED_ID;

public class ContactsActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    private List<Contact> contactList;
    private DrawerLayout mDrawerLayout;
    private ListView mContacts;
    private ActionBarDrawerToggle mToggle;

    private AlertDialog dialog;

    private SharedPreferences sharedPreferences;

    private String currentId;

    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("Contacts");

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_dehaze_white_36dp);
            actionBar.setHomeButtonEnabled(true);
        }

        sharedPreferences = getSharedPreferences(SHARED_ID, MODE_PRIVATE);
        currentId = sharedPreferences.getString(ID, "");

        progressDoalog = new ProgressDialog(ContactsActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        PmsuService service = RetrofitClientInstance.getRetrofitInstance().create(PmsuService.class);
        Call<List<Contact>> call = service.getContactsByAccount(currentId);
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                Log.d("REZ", "CODE: " + response.code() + "");
                progressDoalog.dismiss();
                contactList = response.body();
                generateDataList();
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(ContactsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                Log.d("An error occurred: ", "Error " + t.getMessage());
            }
        });

        mDrawerLayout = findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.navMenu);
        navigationView.setNavigationItemSelectedListener(this);
        View mHeadView = navigationView.getHeaderView(0);
        RelativeLayout head = mHeadView.findViewById(R.id.drawer_header);
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactsActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        FloatingActionButton fab_contacts = (FloatingActionButton)findViewById(R.id.fab_contacts);
        fab_contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CreateContactIntent = new Intent(ContactsActivity.this, CreateContactActivity.class);
                startActivity(CreateContactIntent);
            }
        });
    }

    /*OVO NAM NE TREBA JER ZA "CREATE NEW CONTACT" KORISTIMO FAB!

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_contacts_toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_create_contact){
            Intent createContact = new Intent(this, CreateContactActivity.class);
            startActivity(createContact);
        }
        return super.onOptionsItemSelected(item);
    }*/

    private class ContactsClickListener implements ListView.OnItemClickListener {
        @Override
        public  void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Contact contact = (Contact) parent.getAdapter().getItem(position);

            Intent intent = new Intent(ContactsActivity.this, ContactActivity.class);
            intent.putExtra("email", contact.getEmails());
            intent.putExtra("name", contact.getName());
            intent.putExtra("lastName", contact.getLastName());
            startActivity(intent);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.drawer_mails){
            Intent intent = new Intent(this,EmailsActivity.class);
            startActivity(intent);
            finish();

        } else if(id == R.id.drawer_contacts){
            Intent intent = new Intent(this, ContactsActivity.class);
            startActivity(intent);
            finish();

        }else if(id == R.id.drawer_folder){
            Intent intent = new Intent(this, FoldersActivity.class);
            startActivity(intent);
            finish();

        }else if (id == R.id.drawer_settings){
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);

        }else if (id == R.id.drawer_logout){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void generateDataList() {
        mContacts = (ListView) findViewById(R.id.contactsList);
        ContactAdapter cAdapter = new ContactAdapter(this, contactList);
        mContacts.setOnItemClickListener(new ContactsClickListener());
        mContacts.setAdapter(cAdapter);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Exit")
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton(R.string.no, null)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.clear();
                            editor.commit();
                            ContactsActivity.super.onBackPressed();
                        }
                    }).create().show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
