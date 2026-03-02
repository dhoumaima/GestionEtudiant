package Model;

import java.awt.*;
import java.util.ArrayList;

public class Profile {
    String nom,prenom,pseudo;
    ArrayList<Langue> langues;
    int annee;
    String cycle;

    public Profile(String nom, String prenom, String pseudo) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.cycle = "";
        this.annee = 1;
        this.langues = new ArrayList<>();
    }
    public Profile() {
        this.nom = "";
        this.prenom = "";
        this.pseudo = "";
        this.cycle="";
        this.annee=1;
        this.langues=new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }



    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public ArrayList<Langue> getLangues() {
        return langues;
    }

    public void setLangues(ArrayList<Langue> langues) {
        this.langues = langues;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", langues=" + langues +
                ", annee=" + annee +
                ", cycle='" + cycle + '\'' +
                '}';
    }
}
