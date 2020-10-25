package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Email {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    private List<String> to;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("edate")
    @Expose
    private Date edate;
    @SerializedName("cc")
    @Expose
    private List<String> cc;
    @SerializedName("bcc")
    @Expose
    private List<String> bcc;
    @SerializedName("tags")
    @Expose
    private List<Tag> tags;
    @SerializedName("attachments")
    @Expose
    private List<Attachment> attachments;
    @SerializedName("unread")
    @Expose
    private boolean unread;


    public Email() {
    }


    public Email(Integer id, String from, List<String> to, String title, String message, Date edate, List<String> cc,
                 List<String> bcc, List<Tag> tags, List<Attachment> attachments, Boolean unread) {
        super();
        this.id = id;
        this.title = title;
        this.message = message;
        this.edate = edate;
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.tags = tags;
        this.attachments = attachments;
        this.unread = unread;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    public List<String> getCc() {
        return cc;
    }

    public void setCc(List<String> cc) {
        this.cc = cc;
    }

    public List<String> getBcc() {
        return bcc;
    }

    public void setBcc(List<String> bcc) {
        this.bcc = bcc;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public boolean isUnread() {
        return unread;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
    }

    public static Comparator<Email> EmailDateComparator = new Comparator<Email>() {
        public int compare(Email a, Email b) {
            return a.getEdate().compareTo(b.getEdate());
        }
    };

    public static Comparator<Email> EmailDateComparatorInv = new Comparator<Email>() {
        public int compare(Email a, Email b) {
            return b.getEdate().compareTo(a.getEdate());
        }
    };

    @Override
    public String toString() {
        return  "id: " + id + "\n" +
                "from: " + from + "\n"  +
                "to: " + to + "\n" +
                "title: " + title + "\n" +
                "message: " + message + "\n" +
                "date: " + edate + "\n" +
                "cc: " + cc + "\n" +
                "bcc: " + bcc + "\n" +
                "tags" + tags + "\n" +
                "attachments" + attachments;
    }
}
