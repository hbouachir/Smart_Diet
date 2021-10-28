
package Payments.entities;

import java.time.LocalDate;

public class Facture {
     private int idFacture; 
     private int idCommande;
      private String etatPayment;
              private String dateFacture;
              private String name;

    public Facture(int idFacture, int idCommande, String etatPayment, String dateFacture, String name) {
        this.idFacture = idFacture;
        this.idCommande = idCommande;
        this.etatPayment = etatPayment;
        this.dateFacture = dateFacture;
        this.name = name;
    }
     public Facture(String etatPayment, String dateFacture, String name) {
       
        this.etatPayment = etatPayment;
        this.dateFacture = dateFacture;
        this.name = name;
    }
     public Facture( ) {}

   

  

    public int getIdFacture() {
        return idFacture;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public String getEtatPayment() {
        return etatPayment;
    }

    public String getDateFacture() {
        return dateFacture;
    }

    public String getName() {
        return name;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public void setEtatPayment(String etatPayment) {
        this.etatPayment = etatPayment;
    }

    public void setDateFacture(String dateFacture) {
        this.dateFacture = dateFacture;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Facture{" + "idFacture=" + idFacture + ", idCommande=" + idCommande + ", etatPayment=" + etatPayment + ", dateFacture=" + dateFacture + ", name=" + name + '}';
    }
              
}
