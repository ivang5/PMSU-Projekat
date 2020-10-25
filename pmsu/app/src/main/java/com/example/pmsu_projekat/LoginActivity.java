package com.example.pmsu_projekat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pmsu_projekat.network.ApiUtils;
import com.example.pmsu_projekat.network.PmsuService;
import com.example.pmsu_projekat.network.RetrofitClientInstance;

import java.lang.reflect.Array;
import java.util.ArrayList;

import model.Account;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText loginPassword;
    EditText loginUsername;
    Button btnLogin;

    boolean isLogged;
    public String Username;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public static final String SHARED_USERNAME = "username";
    public static final String SHARED_PASSWORD = "password";
    public static final String SHARED_ID = "id";
    public static final String USERNAME = "user";
    public static final String PASSWORD = "pass";
    public static final String ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPassword = (EditText) findViewById(R.id.loginPassword);
        loginUsername = (EditText) findViewById(R.id.loginUsername);
        btnLogin = (Button)findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                login(v);
            }
        });
    }

    private void login(final View v){

        final String u = loginUsername.getText().toString();
        final String p = loginPassword.getText().toString();

        PmsuService service = RetrofitClientInstance.getRetrofitInstance().create(PmsuService.class);
        Call<ArrayList<Account>> call = service.getAllAccounts();
        call.enqueue(new Callback<ArrayList<Account>>() {
            @Override
            public void onResponse(Call<ArrayList<Account>> call, Response<ArrayList<Account>> response) {
                Log.d("REZ", "CODE: " + response.code() + "");
                Log.d("REZ", response.body().size() + "");
                ArrayList<Account> accounts = response.body();

                for(Account a: accounts){
                    if(a.getUsername().equals(u) && a.getPassword().equals(p)){
                        isLogged = true;
                        Username = a.getUsername();

                        sharedPreferences = getSharedPreferences(SHARED_USERNAME, Context.MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor.putString(USERNAME, a.getUsername());
                        editor.apply();
                        editor.commit();

                        sharedPreferences = getSharedPreferences(SHARED_PASSWORD, Context.MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor.putString(PASSWORD, a.getPassword());
                        editor.apply();
                        editor.commit();

                        sharedPreferences = getSharedPreferences(SHARED_ID, Context.MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor.putString(ID, a.getId());
                        editor.apply();
                        editor.commit();

                        Intent emails = new Intent(LoginActivity.this, EmailsActivity.class);
                        startActivity(emails);
                        finish();
                    }
                }
                if(!isLogged){
                    Toast.makeText(LoginActivity.this, "Username or password are not valid", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Account>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("An error occurred: ", "Error " + t.getMessage());
            }
        });

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
