package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Account {
    @SerializedName("id")
    @Expose
    private Integer id;


    @SerializedName("smtpAddress")
    @Expose
    private String smtpAddress;


    @SerializedName("smtpPort")
    @Expose
    private Integer smtpPort;


    @SerializedName("username")
    @Expose
    private String username;


    @SerializedName("password")
    @Expose
    private String password;


    @SerializedName("emails")
    @Expose
    private List<Email> emails;


    @SerializedName("inServerType")
    @Expose
    private Integer inServerType;


    @SerializedName("inServerAddress")
    @Expose
    private String inServerAddress;


    @SerializedName("inServerPort")
    @Expose
    private Integer inServerPort;


    @SerializedName("display_name")
    @Expose
    private String display_name;


    @SerializedName("folders")
    @Expose
    private ArrayList<Folder> folders;


    public Account(int id, String smtpAddress, int smtpPort, String username, String password,
                   ArrayList<Email> emails, Integer inServerType, String inServerAddress, Integer inServerPort,
                   String display_name, ArrayList<Folder> folders) {
        this.id = id;
        this.smtpAddress = smtpAddress;
        this.smtpPort = smtpPort;
        this.username = username;
        this.password = password;
        this.emails = emails;
        this.inServerType = inServerType;
        this.inServerAddress = inServerAddress;
        this.inServerPort = inServerPort;
        this.display_name = display_name;
        this.folders = folders;
    }

    public Account() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSmtpAddress() {
        return smtpAddress;
    }

    public void setSmtpAddress(String smtpAddress) {
        this.smtpAddress = smtpAddress;
    }

    public Integer getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(Integer smtpPort) {
        this.smtpPort = smtpPort;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public Integer getInServerType() {
        return inServerType;
    }

    public void setInServerType(Integer inServerType) {
        this.inServerType = inServerType;
    }

    public String getInServerAddress() {
        return inServerAddress;
    }

    public void setInServerAddress(String inServerAddress) {
        this.inServerAddress = inServerAddress;
    }

    public Integer getInServerPort() {
        return inServerPort;
    }

    public void setInServerPort(Integer inServerPort) {
        this.inServerPort = inServerPort;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public ArrayList<Folder> getFolders() {
        return folders;
    }

    public void setFolders(ArrayList<Folder> folders) {
        this.folders = folders;
    }

    @Override
    public String toString() {
        return "Account" +
                "id=" + id +
                ", smtp='" + smtpAddress + '\'' +
                ", username='" + username +
                ", password='" + password + '\'' +
                ", emails= " + emails;
    }
}
