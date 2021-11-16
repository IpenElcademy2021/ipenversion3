package com.example.javafxco1;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.*;



public class TVMembers {

    private  String Id;
    private  String Visa;
    private  String Nom;
    private  String Prenom;
    private  String Job;
    private  String Password;
    private  String phoneNumber;

    public TVMembers(String id, String visa, String nom, String prenom, String job, String password, String phoneNumber) {
        Id = id;
        Visa = visa;
        Nom = nom;
        Prenom = prenom;
        Job = job;
        Password = password;
        this.phoneNumber = phoneNumber;
    }

    public TVMembers() {
    }

    public TVMembers(String id, String visa) {
        this.Id = id;
        this.Visa = visa;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getVisa() {
        return Visa;
    }

    public void setVisa(String visa) {
        Visa = visa;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getJob() {
        return Job;
    }

    public void setJob(String job) {
        Job = job;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "TVMembers{" +
                "Id='" + Id + '\'' +
                ", Visa='" + Visa + '\'' +
                ", Nom='" + Nom + '\'' +
                ", Prenom='" + Prenom + '\'' +
                ", Job='" + Job + '\'' +
                ", Password='" + Password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
