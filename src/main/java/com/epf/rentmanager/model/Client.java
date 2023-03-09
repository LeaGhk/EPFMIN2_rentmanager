package com.epf.rentmanager.model;

import java.time.LocalDate;
import java.util.Objects;

public class Client {
    private long id;
    private String nom;
    private String prenom;
    private LocalDate naissance;
    private String email;

    public Client(long id, String nom, String prenom, LocalDate dateNaissance, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = dateNaissance;
        this.email = email;
    }

    public Client() {    }
    public Client(String nom, String prenom, String email){
        this.nom = nom;
        this.prenom = prenom;
//        this.naissance = dateNaissance;
        this.email = email;
    }

    public Client(String nom, String prenom, LocalDate dateNaissance, String email){
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = dateNaissance;
        this.email = email;
    }

    public long getId() {return id;}
    public String getNom() {return nom;}
    public String getPrenom() {return prenom;}
    public LocalDate getNaissance() {return naissance;}
    public String getEmail() {return email;}

    public void setId(long id) {this.id = id;}
    public void setNom(String nom) {this.nom = nom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}
    public void setNaissance(LocalDate naissance) {this.naissance = naissance;}
    public void setEmail(String email) {this.email = email;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && nom.equals(client.nom) && prenom.equals(client.prenom) && naissance.equals(client.naissance) && email.equals(client.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, prenom, naissance, email);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + naissance +
                ", email='" + email + '\'' +
                '}';
    }



}
