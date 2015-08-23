package com.ayoprez.login;

/**
 * Created by AyoPrez on 16/05/15.
 */
public class User {

    private int Id_U;
    private String Name;
    private String Social_Id;

    public User(){}

    public User(int Id_U, String Social_Id){
        this.Id_U = Id_U;
        this.Social_Id = Social_Id;
    }

//    public User(int Id_U, String name, String Social_Id){
//        this.Id = Id_U;
//        this.Name = name;
//        this.Social_Id = Social_Id;
//    }

    public User(String name, int id_U){
        this.Id_U = id_U;
        this.Name = name;
    }

    public User(String name, String Social_Id){
        this.Name = name;
        this.Social_Id = Social_Id;
    }

    public int getId_U() {
        return Id_U;
    }

    public void setId_U(int id_U) {
        Id_U = id_U;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSocial_Id() {
        return Social_Id;
    }

    public void setSocial_Id(String social_Id) {
        Social_Id = social_Id;
    }
}