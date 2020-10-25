package com.example.pmsu_projekat;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pmsu_projekat.adapters.EmailAdapter;
import com.example.pmsu_projekat.network.PmsuService;
import com.example.pmsu_projekat.network.RetrofitClientInstance;
import com.example.pmsu_projekat.tools.Podaci;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import model.Email;
import model.Folder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pmsu_projekat.LoginActivity.ID;
import static com.example.pmsu_projekat.LoginActivity.SHARED_ID;
import static com.example.pmsu_projekat.LoginActivity.SHARED_USERNAME;
import static com.example.pmsu_projekat.LoginActivity.USERNAME;

public class EmailsActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, SharedPreferences.OnSharedPreferenceChangeListener {

    private List<Email> privremeniEmailList;
    private List<Folder> privremeniFolderList;
    private List<Email> privremeniEmail;
    private List<Folder> privremeniFolder;

    private List<Email> emailList;
    private ListView mEmails;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private AlertDialog dialog;

    private String refreshTime;
    private boolean autoRefresh;
    private String sortByDate;

    private SharedPreferences sharedPreferences;

    private String currentId;
    private String currentUser;

    private SharedPreferences.Editor editor;
    private long interval = 0;
    private Handler handler;

    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emails);
        handler = new Handler();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("Emails");

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_dehaze_white_36dp);
            actionBar.setHomeButtonEnabled(true);
        }

        sharedPreferences = getSharedPreferences(SHARED_ID, MODE_PRIVATE);
        currentId = sharedPreferences.getString(ID, "");

        sharedPreferences = getSharedPreferences(SHARED_USERNAME, MODE_PRIVATE);
        currentUser = sharedPreferences.getString(USERNAME, "");

        progressDoalog = new ProgressDialog(EmailsActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        mDrawerLayout = findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navMenu);
        navigationView.setNavigationItemSelectedListener(this);
        View mHeadView = navigationView.getHeaderView(0);
        RelativeLayout head = mHeadView.findViewById(R.id.drawer_header);
        TextView curr_user = mHeadView.findViewById(R.id.nav_name);
        curr_user.setText(currentUser);
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmailsActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        setupSharedPreferences();

//        OVO NAM NE TREBA VISE
//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        FloatingActionButton fab_emails = (FloatingActionButton)findViewById(R.id.fab_emails);
        fab_emails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CreateEmailActivity = new Intent(EmailsActivity.this, CreateEmailActivity.class);
                startActivity(CreateEmailActivity);
            }
        });

    }

    private void setupSharedPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

//    OVO NAM NE TREBA VISE
//    private void consultPreferences(){
//        refreshTime = sharedPreferences.getString(getString(R.string.list_refresh_key), "1");//1min
//        autoRefresh = sharedPreferences.getBoolean(getString(R.string.cb_refresh_key), true);
//        sortByDate = sharedPreferences.getString(getString(R.string.list_sort_by_date_key), "1");//newest to oldest
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_itemmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_refresh){
            Toast.makeText(EmailsActivity.this, "Refresh App", Toast.LENGTH_SHORT).show();
            dataLoader.run();
        }

        if(id == R.id.action_filter) {
            Toast.makeText(EmailsActivity.this, "Search...", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private class EmailsClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Email email = (Email) parent.getAdapter().getItem(position);

            email.setUnread(false);
            Intent intent = new Intent(EmailsActivity.this, EmailActivity.class);
            intent.putExtra("from", email.getFrom().getName());
            intent.putExtra("title", email.getTitle());
            intent.putExtra("message", email.getMessage());
            intent.putExtra("date", email.getDate());
            startActivity(intent);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPref, String key) {
        if (key.equals(getString(R.string.list_sort_by_date_key))) {
            emailSorter(sharedPref);
        }else if(key.equals(getString(R.string.cb_refresh_key))){
            if(sharedPref.getBoolean(key,true) == false){
                stopRepeatingTask();
            }else{
                startRepeatingTask();
            }
        }
    }

    public void emailSorter(SharedPreferences sharedPref){
        if(sharedPref.getString(getString(R.string.list_sort_by_date_key), "1").equals("1")){
            Collections.sort(emailList, Email.EmailDateComparatorInv);
            generateDataList();
        }else if(sharedPref.getString(getString(R.string.list_sort_by_date_key), "1").equals("2")){
            Collections.sort(emailList, Email.EmailDateComparator);
            generateDataList();
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

    Runnable dataLoader = new Runnable() {
        @Override
        public void run() {
            try {
                String syncTimeStr = sharedPreferences.getString(getString(R.string.list_refresh_key), "1");
                interval = TimeUnit.MINUTES.toMillis(Integer.parseInt(syncTimeStr));
                Toast toast = Toast.makeText(getApplicationContext(), "Syncing", Toast.LENGTH_SHORT);
                toast.show();

                PmsuService service = RetrofitClientInstance.getRetrofitInstance().create(PmsuService.class);
                Call<List<Email>> call = service.getEmailsByAccount(currentId);
                call.enqueue(new Callback<List<Email>>() {
                    @Override
                    public void onResponse(Call<List<Email>> call, Response<List<Email>> response) {
                        Log.d("REZ", "CODE: " + response.code() + "");
                        Log.d("REZ", response.body().size() + "");
                        progressDoalog.dismiss();
                        emailList = response.body();
                        emailSorter(sharedPreferences);
                        generateDataList();
                    }

                    @Override
                    public void onFailure(Call<List<Email>> call, Throwable t) {
                        progressDoalog.dismiss();
                        Toast.makeText(EmailsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                        Log.d("An error occurred: ", "Error " + t.getMessage());
                    }
                });
            } finally {
                if (sharedPreferences.getBoolean(getString(R.string.cb_refresh_key), true)) {
                    handler.postDelayed(dataLoader, interval);
                }
            }
        }
    };

    private void generateDataList() {
        mEmails = (ListView) findViewById(R.id.emailsList);
        EmailAdapter eAdapter = new EmailAdapter(this, emailList);
        mEmails.setOnItemClickListener(new EmailsClickListener());
        mEmails.setAdapter(eAdapter);
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
                            EmailsActivity.super.onBackPressed();
                        }
                    }).create().show();
        }
    }

    void startRepeatingTask() {
        dataLoader.run();
    }

    void stopRepeatingTask(){
        handler.removeCallbacks(dataLoader);
    }

    @Override
    protected void onStart() {
        super.onStart();
        startRepeatingTask();
        //stopRepeatingTask();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopRepeatingTask();
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
        stopRepeatingTask();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }
}
