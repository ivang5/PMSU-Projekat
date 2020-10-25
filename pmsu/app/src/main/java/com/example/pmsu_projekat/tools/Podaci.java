package com.example.pmsu_projekat.tools;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Account;
import model.Attachment;
import model.Contact;
import model.Email;
import model.Folder;
import model.Photo;
import model.Rule;
import model.Tag;


public class Podaci {

//    private static ArrayList<Contact> get_contacts = new ArrayList<>(Podaci.getContacts());
//    private static ArrayList<Email> get_emails = new ArrayList<>(Podaci.getEmails());
//
//    public static ArrayList<Contact> getContacts() {
//        ArrayList<Contact> contacts = new ArrayList<>();
//        Contact c1 = new Contact(1, "joe@mail.com", "Joe", "Johnas", new Photo(), "format", "note");
//        Contact c2 = new Contact(2, "frank@mail.com", "Frank", "Frankenstein", new Photo(), "format", "note");
//        Contact c3 = new Contact(3, "joack@mail.com", "Jack", "Chan", new Photo(), "format", "note");
//        Contact c4 = new Contact(3, "john@mail.com", "John", "Jones", new Photo(), "format", "note");
//        Contact c5 = new Contact(3, "sam@mail.com", "Sam", "Samington", new Photo(), "format", "note");
//        Contact c6 = new Contact(3, "clara@mail.com", "Clara", "Clarson", new Photo(), "format", "note");
//
//        contacts.add(c1);
//        contacts.add(c2);
//        contacts.add(c3);
//        contacts.add(c4);
//        contacts.add(c5);
//        contacts.add(c6);
//
//        return contacts;
//
//    }
//
//    static Contact contact1 = getContacts().get(0);
//    static Contact contact2 = getContacts().get(1);
//    static Contact contact3 = getContacts().get(2);
//    static Contact contact4 = getContacts().get(3);
//    static Contact contact5 = getContacts().get(4);
//    static Contact contact6 = getContacts().get(5);
//
//    public static ArrayList<Email> getEmails() {
//        ArrayList<Email> emails = new ArrayList<>();
//        Email email1 = new Email(0, contact1, new ArrayList<Contact>(), "Something", "Something happened!", new Date(), new Photo(), new ArrayList<Contact>(), new ArrayList<Contact>(), Podaci.getFolders().get(0), new ArrayList<Tag>(), new ArrayList<Attachment>(), new Account(), true);
//        Email email2 = new Email(1, contact2, new ArrayList<Contact>(), "Nothing", "Nothing happened!", new Date(), new Photo(),new ArrayList<Contact>(), new ArrayList<Contact>(), Podaci.getFolders().get(1), new ArrayList<Tag>(), new ArrayList<Attachment>(), new Account(), true);
//        Email email3 = new Email(2, contact3, new ArrayList<Contact>(), "Project", "Project was made!", new Date(), new Photo(), new ArrayList<Contact>(), new ArrayList<Contact>(), Podaci.getFolders().get(2), new ArrayList<Tag>(), new ArrayList<Attachment>(), new Account(), true);
//        Email email4 = new Email(2, contact4, new ArrayList<Contact>(), "Let's go", "We can do this!", new Date(), new Photo(), new ArrayList<Contact>(), new ArrayList<Contact>(), Podaci.getFolders().get(0), new ArrayList<Tag>(), new ArrayList<Attachment>(), new Account(), true);
//        Email email5 = new Email(2, contact5, new ArrayList<Contact>(), "Important", "No, it's not important!", new Date(), new Photo(), new ArrayList<Contact>(), new ArrayList<Contact>(), Podaci.getFolders().get(1), new ArrayList<Tag>(), new ArrayList<Attachment>(), new Account(),true);
//        Email email6 = new Email(2, contact6, new ArrayList<Contact>(), "Android", "Let's make an app!", new Date(), new Photo(), new ArrayList<Contact>(), new ArrayList<Contact>(), Podaci.getFolders().get(2), new ArrayList<Tag>(), new ArrayList<Attachment>(), new Account(), true);
//
//
//        emails.add(email1);
//        emails.add(email2);
//        emails.add(email3);
//        emails.add(email4);
//        emails.add(email5);
//        emails.add(email6);
//
//        return emails;
//
//    }
//
//    static Email email1 = getEmails().get(0);
//    static Email email2 = getEmails().get(1);
//    static Email email3 = getEmails().get(2);
//    public static ArrayList<Email> emails1 = new ArrayList<>(getEmails());
//    public static ArrayList<Email> primary = new ArrayList<>(getPrimary());
//    public static ArrayList<Email> social = new ArrayList<>(getSocial());
//    public static ArrayList<Email> promotions = new ArrayList<>(getPromotions());
//
//    public static ArrayList<Folder> getFolders() {
//        ArrayList<Folder> folders = new ArrayList<>();
//        Folder folder1 = new Folder(1, "Primary", new Photo(), primary,  new ArrayList<Rule>(), new Folder(), new Account(), new ArrayList<Folder>());
//        Folder folder2 = new Folder(2, "Social", new Photo(), social,  new ArrayList<Rule>(), new Folder(), new Account(), new ArrayList<Folder>());
//        Folder folder3 = new Folder(3, "Promotions", new Photo(), promotions,  new ArrayList<Rule>(), new Folder(), new Account(), new ArrayList<Folder>());
//
//        folders.add(folder1);
//        folders.add(folder2);
//        folders.add(folder3);
//
//        return folders;
//    }
//
//    public static ArrayList<Email> getPrimary() {
//        ArrayList<Email> primary = new ArrayList<>();
//        for (int i = 0; i < emails1.size(); i++) {
//            if (emails1.get(i).getFolder().getName() == "Primary") {
//                primary.add(emails1.get(i));
//            }
//        }
//        return primary;
//    }
//
//    public static ArrayList<Email> getSocial() {
//        ArrayList<Email> social = new ArrayList<>();
//        for (int i = 0; i < emails1.size(); i++) {
//            if (emails1.get(i).getFolder().getName() == "Social") {
//                social.add(emails1.get(i));
//            }
//        }
//        return social;
//    }
//
//    public static ArrayList<Email> getPromotions() {
//        ArrayList<Email> promotions = new ArrayList<>();
//        for (int i = 0; i < emails1.size(); i++) {
//            if (emails1.get(i).getFolder().getName() == "Promotions") {
//                promotions.add(emails1.get(i));
//            }
//        }
//        return promotions;
//    }
}
