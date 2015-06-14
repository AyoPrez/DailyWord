package com.ayoprez.login;

/**
 * Created by AyoPrez on 16/05/15.
 */
public class User {

    private int Id;
    private String Name;
    private String Mail;
    private String Pass;

    public User(){}

    public User(int id, String name, String mail, String pass){
        this.Id = id;
        this.Name = name;
        this.Mail = mail;
        this.Pass = pass;
    }

    public User(int id, String mail, String pass){
        this.Id = id;
        this.Mail = mail;
        this.Pass = pass;
    }

    public User(String mail, String pass){
        this.Mail = mail;
        this.Pass = pass;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }
}