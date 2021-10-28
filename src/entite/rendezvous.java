/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;


/**
 *
 * @author Nour
 */
public class rendezvous {
    private int idRdv;
    private int idPatient; //cle etranger comment je le faire
    private String dateRdv;
    private String heure;
    
public rendezvous(){
    
}   

    public rendezvous(int idPatient, String dateRdv, String heure) {
        this.idPatient = idPatient;
        this.dateRdv = dateRdv;
        this.heure = heure;
    }


public rendezvous(String dateRdv, String heure){
   this.dateRdv = dateRdv;
   this.heure = heure; 
}

    public rendezvous(int idRdv, int idPatient, String dateRdv, String heure) {
        this.idRdv = idRdv;
        this.dateRdv = dateRdv;
        this.heure = heure;
    }



 public int getidRdv(){
     return idRdv;
 }   
 public void setidRdv( int idRdv){
     this.idRdv = idRdv;
 }
  
  public int getidPtient(){
     return idPatient;
 }   
 public void setidPatient( int idPatient){
     this.idPatient = idPatient;
 }

    public String getDateRdv() {
        return dateRdv;
    }

    public void setDateRdv(String dateRdv) {
        this.dateRdv = dateRdv;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }
 

    @Override
    public String toString() {
        return "rendezvous{" + "idRdv=" + idRdv + ", idPatient=" + idPatient + ", dateRdv=" + dateRdv + ", heure=" + heure + '}';
    }
 
 
    
    
}
