package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Folder {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("emails")
    @Expose
    private List<Email> emails;
    @SerializedName("rules")
    @Expose
    private List<Rule> rules;
    @SerializedName("folders")
    @Expose
    private List<Folder> folders;

    public Folder(Integer id, String name, List<Email> emails, List<Rule> rules, List<Folder> folders) {
        this.id = id;
        this.name = name;
        this.emails = emails;
        this.rules = rules;
        this.folders = folders;
    }

    public Folder() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public List<Folder> getFolders() {
        return folders;
    }

    public void setFolders(List<Folder> folders) {
        this.folders = folders;
    }

    @Override
    public String toString() {
        return  "id: " + id + "\n"  +
                "name: " + name + "\n" +
                "rule: " + rules + "\n" +
                "folders: " + folders;
    }
}
