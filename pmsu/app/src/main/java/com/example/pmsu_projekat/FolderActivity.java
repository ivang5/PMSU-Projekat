package com.example.pmsu_projekat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pmsu_projekat.adapters.FoldersAdapter;
import com.example.pmsu_projekat.network.PmsuService;
import com.example.pmsu_projekat.network.RetrofitClientInstance;
import com.example.pmsu_projekat.tools.Podaci;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import model.Email;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pmsu_projekat.LoginActivity.ID;
import static com.example.pmsu_projekat.LoginActivity.SHARED_ID;

public class FolderActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private List<Email> emailList;
    private List<Email> folderEmails;
    private ListView mEmails;
    Toolbar toolbar;

    private AlertDialog dialog;

    private SharedPreferences sharedPreferences;
    private long interval = 0;
    private Handler handler;

    private String currentId;

    ProgressDialog progressDoalog;

    private String folderName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);
        handler = new Handler();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Folder activity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDoalog = new ProgressDialog(FolderActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        sharedPreferences = getSharedPreferences(SHARED_ID, MODE_PRIVATE);
        currentId = sharedPreferences.getString(ID, "");

        setupSharedPreferences();

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                folderName = null;
            } else {
                folderName = extras.getString("name");
            }
        } else {
            folderName = (String) savedInstanceState.getSerializable("name");
        }
    }

    private void setupSharedPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_folder_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_edit){
            Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private class EmailsClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Email email = (Email) parent.getAdapter().getItem(position);

            Intent intent = new Intent(FolderActivity.this, EmailActivity.class);
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
            Collections.sort(folderEmails, Email.EmailDateComparatorInv);
            generateDataList();
        }else if(sharedPref.getString(getString(R.string.list_sort_by_date_key), "1").equals("2")){
            Collections.sort(folderEmails, Email.EmailDateComparator);
            generateDataList();
        }
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

                        folderEmails = new ArrayList<>();
                        emailList = response.body();
                        assert emailList != null;
                        for(Email e: emailList) {
                            if (e.getFolder().getName().equals(folderName)) {
                                folderEmails.add(e);
                            }
                        }
                        Log.d("REZ", "CODE: " + response.code() + "");
                        Log.d("REZ", response.body().size() + "");
                        progressDoalog.dismiss();
                        emailSorter(sharedPreferences);
                        generateDataList();
                    }

                    @Override
                    public void onFailure(Call<List<Email>> call, Throwable t) {
                        progressDoalog.dismiss();
                        Toast.makeText(FolderActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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
        FoldersAdapter eAdapter = new FoldersAdapter(this, folderEmails);
        mEmails.setOnItemClickListener(new EmailsClickListener());
        mEmails.setAdapter(eAdapter);
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
