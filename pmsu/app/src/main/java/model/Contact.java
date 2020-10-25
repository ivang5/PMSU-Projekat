package model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Contact {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("display_name")
    private String display_name;
    @SerializedName("email")
    private String email;
    @SerializedName("note")
    private String note;
    @SerializedName("avatar")
    private Photo avatar;

    public Contact(Integer id, String name, String lastName, String display_name, String email, String note, Photo avatar) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.display_name = display_name;
        this.email = email;
        this.note = note;
        this.avatar = avatar;
    }

    public Contact() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Photo getAvatar() {
        return avatar;
    }

    public void setAvatar(Photo avatar) {
        this.avatar = avatar;
    }

    public String getEmails() {
        return email;
    }

    public void setEmails(String email) {
        this.email = email;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return  "id: " + id + "\n"  +
                "email: " + email + "\n" +
                "name: " + name + "\n" +
                "lastName" + lastName + "\n"+
                "display_name" + display_name + "\n";
    }
}
