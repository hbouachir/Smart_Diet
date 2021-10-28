/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

//import java.util.Date;

//import java.sql.Date;
import java.util.Date;

/**
 *
 * @author cyrine belhssan
 */
public class Personne {
     private int id;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String mail;
    private String password;
    private String adresse;
    private int referenceClient;
    private int numero;
    private String civilite;
    private String privilege;
    private String maladie;
    

    public Personne() {
    }

    public Personne(String nom, String prenom, String dateNaissance, String mail, String password, String adresse, int numero, String civilite) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.mail = mail;
        this.password = password;
        this.adresse = adresse;
        this.numero = numero;
        this.civilite = civilite;
    }
    
    

    public Personne(String nom, String prenom, String dateNaissance, String mail, String password, String privilege) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.mail = mail;
        this.password = password;
        this.privilege = privilege;
    }

    public Personne(String nom, String prenom, String dateNaissance, String mail, String password, String adresse, int numero, String civilite, String privilege) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.mail = mail;
        this.password = password;
        this.adresse = adresse;
        this.numero = numero;
        this.civilite = civilite;
        this.privilege = privilege;
    }
    
    

    public Personne(String nom, String prenom, String dateNaissance, String mail, String password, String adresse, int referenceClient, int numero, String civilite, String maladie) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.mail = mail;
        this.password = password;
        this.adresse = adresse;
        this.referenceClient = referenceClient;
        this.numero = numero;
        this.civilite = civilite;
        this.maladie = maladie;
    }

    public Personne(String nom, String prenom, String mail, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.password = password;
    }
    

    public Personne(int id, String nom, String prenom, String dateNaissance, String mail, String password, String adresse, int referenceClient, int numero, String civilite, String privilege, String maladie) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.mail = mail;
        this.password = password;
        this.adresse = adresse;
        this.referenceClient = referenceClient;
        this.numero = numero;
        this.civilite = civilite;
        this.privilege = privilege;
        this.maladie = maladie;
    }
    

    public Personne(int id, String nom, String prenom, String mail, String password, String adresse, int numero, String privilege) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.password = password;
        this.adresse = adresse;
        this.numero = numero;
        this.privilege = privilege;
    }
    

    public Personne(String nom, String prenom, String dateNaissance, String mail, String password, String adresse, String civilite, String privilege, String maladie) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.mail = mail;
        this.password = password;
        this.adresse = adresse;
        this.civilite = civilite;
        this.privilege = privilege;
        this.maladie = maladie;
    }
    

    public Personne(String nom, String prenom, String mail, String password, String privilege) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.password = password;
        this.privilege = privilege;
    }

    public Personne(String nom, String prenom, String mail, String password, String adresse, int numero, String privilege) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.password = password;
        this.adresse = adresse;
        this.numero = numero;
        this.privilege = privilege;
    }

    public Personne(String nom, String prenom, String dateNaissance, String mail, String password, String adresse, int referenceClient, int numero, String civilite, String privilege, String maladie) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.mail = mail;
        this.password = password;
        this.adresse = adresse;
        this.referenceClient = referenceClient;
        this.numero = numero;
        this.civilite = civilite;
        this.privilege = privilege;
        this.maladie = maladie;
    }

   

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    

    

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getReferenceClient() {
        return referenceClient;
    }

    public int getNumero() {
        return numero;
    }

    public String getCivilite() {
        return civilite;
    }

    public String getPrivilege() {
        return privilege;
    }

    public String getMaladie() {
        return maladie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setReferenceClient(int referenceClient) {
        this.referenceClient = referenceClient;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public void setMaladie(String maladie) {
        this.maladie = maladie;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", password=" + password + ", adresse=" + adresse + ", referenceClient=" + referenceClient + ", numero=" + numero + ", civilite=" + civilite + ", privilege=" + privilege + ", maladie=" + maladie + '}';
    }
    

    public String AfficherAdmin() {
        return "Admin{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", password=" + password + ", privilege=" + privilege +'}';
    } 
    public String AfficherNut() {
        return "Nut{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", password=" + password + ", adresse=" + adresse +  ", numero=" + numero + ", civilite=" + civilite + ", privilege=" + privilege + '}';
    }
}
