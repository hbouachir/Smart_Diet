
package Payments.entities;

import java.time.LocalDate;

public class Payments {
    private  int idPayment;
 private  int  idFacture;//etr
private String numeroCompte;
private String password;
private String  dateExpiration;
       
public Payments() {
    
    }
public Payments (int idFacture, String numeroCompte, String password, String dateExpiration){
    
    this.idFacture = idFacture;
    this.numeroCompte = numeroCompte;
    this.password = password;
    this.dateExpiration=dateExpiration;
    //SimpleDateFormat sdf =SimpleDateFormat("E YYYY/MM/dd HH-mm-SS");
    //String dateExpiration= sdf.format(dateExpiration.getDate());
    

}

public Payments (String numeroCompte, String password, String dateExpiration){
   
   
    this.numeroCompte = numeroCompte;
    this.password = password;
    this.dateExpiration=dateExpiration;
    //SimpleDateFormat sdf =SimpleDateFormat("E YYYY/MM/dd HH-mm-SS");
    //String dateExpiration= sdf.format(dateExpiration.getDate());
    

}
public Payments (int idPayment,int idFacture,  String numeroCompte, String password, String dateExpiration){
     this.idFacture = idFacture;
    this.idPayment = idPayment;
    this.numeroCompte = numeroCompte;
    this.password = password;
    this.dateExpiration=dateExpiration;
    //SimpleDateFormat sdf =SimpleDateFormat("E YYYY/MM/dd HH-mm-SS");
    //String dateExpiration= sdf.format(dateExpiration.getDate());
    

}


  
    

        public int getIdPayment () {
		return idPayment ;
	}
           public void setIdPayment(int idPayment) {
		this.idPayment = idPayment;
	}
          
       
        
          public String getDateExpiration() {
		return  dateExpiration;
	}
          public void setDateExpiration(String dateExpiration) {
		this.dateExpiration = dateExpiration;
	}
          




	
           public int getIdFacture() {
		return idFacture;
	}
           		public void setIdFacture(int idFacture) {
			this.idFacture = idFacture;
		
                        }
        
           public String getNumeroCompte() {
		return numeroCompte;
	}
	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}
           public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
       
     
    
       @Override
	public String toString() {
		return "payment [idPayment =" + idPayment  + ", idFacture=" + idFacture + ",dateExpiration=" + dateExpiration + ", numeroCompte=" + numeroCompte + ", password=" + password + "]";
	}
    
}
