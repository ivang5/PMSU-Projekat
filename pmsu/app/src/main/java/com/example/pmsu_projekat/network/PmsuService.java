package com.example.pmsu_projekat.network;

import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Contact;
import model.Email;
import model.Folder;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PmsuService {

    @GET("emails/{accountId}")
    Call<List<Email>> getEmailsByAccount(@Path("accountId") String accountId);

    @GET("contacts/{accountId}")
    Call<List<Contact>> getContactsByAccount(@Path("accountId") String accountId);

    @GET("folders/{accountId}")
    Call<List<Folder>> getFoldersByAccount(@Path("accountId") String accountId);

    @GET("accounts")
    Call<ArrayList<Account>> getAllAccounts();

//    @GET("login/{username}/{password}")
//    Call<Account> login(@Path("username") String username,
//                        @Path("password") String password);
}
