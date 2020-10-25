package com.example.pmsu_projekat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.pmsu_projekat.LoginActivity.PASSWORD;
import static com.example.pmsu_projekat.LoginActivity.SHARED_PASSWORD;
import static com.example.pmsu_projekat.LoginActivity.SHARED_USERNAME;
import static com.example.pmsu_projekat.LoginActivity.USERNAME;

public class ProfileActivity extends AppCompatActivity {
    Toolbar toolbar;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private String currentUser;
    private String currentPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TextView mUsername = (TextView) findViewById(R.id.tv_unesi_username);
        final TextView mPassword = (TextView) findViewById(R.id.tv_unesi_password);

        sharedPreferences = getSharedPreferences(SHARED_USERNAME, MODE_PRIVATE);
        currentUser = sharedPreferences.getString(USERNAME, "");
        mUsername.setText(currentUser);
        sharedPreferences = getSharedPreferences(SHARED_PASSWORD, MODE_PRIVATE);
        currentPass = sharedPreferences.getString(PASSWORD, "");
        mPassword.setText(currentPass);

        Button mUsernameDialog = (Button) findViewById(R.id.btn_edit_username);
        mUsernameDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ProfileActivity.this);
                final View mView = getLayoutInflater().inflate(R.layout.dialog_profile_username, null);
                final EditText mEditUsername = (EditText) mView.findViewById(R.id.et_username);
                Button mChange = (Button) mView.findViewById(R.id.btn_change_username);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                mChange.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!mEditUsername.getText().toString().isEmpty()) {
                            mUsername.setText(mEditUsername.getText());

                            sharedPreferences = getSharedPreferences(SHARED_USERNAME, Context.MODE_PRIVATE);
                            editor = sharedPreferences.edit();
                            editor.putString(USERNAME, mEditUsername.getText().toString());
                            editor.apply();
                            editor.commit();

                            Toast.makeText(ProfileActivity.this, "Successfully changed", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                        else {
                            Toast.makeText(ProfileActivity.this, "Please fill any empty fields", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        Button mPasswordDialog = (Button) findViewById(R.id.btn_edit_password);
        mPasswordDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ProfileActivity.this);
                final View mView = getLayoutInflater().inflate(R.layout.dialog_profile_password, null);
                final EditText mOldPassword = (EditText) mView.findViewById(R.id.et_old_password);
                final EditText mNewPassword = (EditText) mView.findViewById(R.id.et_new_password);
                final EditText mRepeatPassword = (EditText) mView.findViewById(R.id.et_repeat_password);
                Button mChange = (Button) mView.findViewById(R.id.btn_change_password);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                mChange.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!mOldPassword.getText().toString().isEmpty() && !mNewPassword.getText().toString().isEmpty()
                                && !mRepeatPassword.getText().toString().isEmpty() && mOldPassword.getText().toString().equals(mPassword.getText().toString()) && mNewPassword.getText().toString().equals(mRepeatPassword.getText().toString())) {
                            mPassword.setText(mNewPassword.getText());

                            sharedPreferences = getSharedPreferences(SHARED_PASSWORD, Context.MODE_PRIVATE);
                            editor = sharedPreferences.edit();
                            editor.putString(PASSWORD, mNewPassword.getText().toString());
                            editor.apply();
                            editor.commit();

                            Toast.makeText(ProfileActivity.this, "Successfully changed", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                        else {
                            Toast.makeText(ProfileActivity.this, "Please fill all fields with valid information", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        Button switchAcc = (Button) findViewById(R.id.btn_switch_acc);
        switchAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    /*TRENUTNO NAM NIJE POTREBAN OVAJ MENI JER NAM JE "LOGOUT" U DRAWERU

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_profile_toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_logout) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }*/

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
