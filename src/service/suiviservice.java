/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Personne;
import entities.Suivi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import utils.Data_B;

/**
 *
 * @author cyrine belhssan
 */
public class suiviservice implements services<Suivi>{
      Connection conn;
      userservice us;
    public suiviservice(){
        conn= Data_B.getInstance().getConnection();
      //  System.out.print( userservice.usercon.toString());
    }
//privilege = 'client' and 
//((SELECT id AS idUser FROM personne WHERE  referenceClient='"+ref+"'),'"+da+"',?,?,?,?,?,?,?)
    @Override
    public void insertSuivi(Suivi p) {
         String req ="INSERT INTO `suivi`(`dateSuivi`,`taille`,`poid`,`glycemie`,`petitDej`,`repas`,`diner`,`notes`,`idUser`)"
                +"values (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pt = conn.prepareStatement(req);
            
        //    pt.setInt(1,p.getIdSuivi());
          //   pt.setString(1,p.getDateSuivi());
             pt.setString(1, new Date().toString());
             pt.setFloat(2, p.getTaille());
             pt.setFloat(3, p.getPoid());
             pt.setFloat(4, p.getGlycemie());
             pt.setString(5,p.getPetitDej());
             pt.setString(6,p.getRepas());
             pt.setString(7,p.getDiner());
             pt.setString(8,p.getNotes());
             pt.setInt(9, userservice.usercon.getId());
             pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public void insertSuiviFX(Suivi p) {
         String req ="INSERT INTO `suivi`(`idUser`,`dateSuivi`,`taille`,`poid`,`glycemie`,`petitDej`,`repas`,`diner`,`notes`)"
                +"values (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pt = conn.prepareStatement(req);
            
         //   pt.setInt(1,p.getIdUser());
           pt.setString(2,p.getDateSuivi());
             pt.setFloat(3, p.getTaille());
             pt.setFloat(4, p.getPoid());
              pt.setFloat(5, p.getGlycemie());
             pt.setString(6,p.getPetitDej());
               pt.setString(7,p.getRepas());
             pt.setString(8,p.getDiner());
              pt.setString(9,p.getNotes());
              pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void insertAdmin(Suivi object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertAutre(Suivi object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Suivi getPersonne(int id) {
         Suivi p = new Suivi();
            
        try {
             p.setIdSuivi(id);
        String req = "SELECT * FROM  suivi WHERE idSuivi="+id;
   
          Statement  ps = conn.createStatement();
         
                
			ResultSet rs = ps.executeQuery(req);
			while (rs.next()) {
				
		p.setIdSuivi(rs.getInt(1));
                  //  p.setIdUser( rs.getInt("idUser"));
	          p.setDateSuivi(rs.getString("dateSuivi"));
                   p.setTaille(rs.getFloat("taille"));
                    p.setPoid( rs.getFloat("poid"));
                      p.setGlycemie(rs.getFloat("glycemie"));
	          p.setPetitDej(rs.getString("petitDej"));
                   p.setRepas(rs.getString("repas"));
                    p.setDiner(rs.getString("diner"));
                     p.setNotes(rs.getString("notes"));
                        }

        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public Suivi upDatePersonne(Suivi object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletePersonne(int id) {
try {
            String req="DELETE FROM suivi WHERE idSuivi= ?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void deletePersonneFX(String d) {
try {
            String req="DELETE FROM suivi WHERE notes= ?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, d);
			ps.executeUpdate();
			ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   // @Override
    public List<Object> findAllO() {
 
      List<Suivi> lp= new ArrayList<Suivi>();
         List<Personne> lp1= new ArrayList<Personne>();
	 List<Object> list = new ArrayList<Object>();   
         //String req ="SELECT *FROM suivi ";
               String req = "SELECT * FROM  suivi,personne WHERE suivi.idUser=personne.id ";
          try {
             Statement  st = conn.createStatement();
          
             ResultSet rs = st.executeQuery(req);
                
           
			while (rs.next()) {
	 Suivi p = new Suivi();
         Personne p1 = new Personne();
                    p.setIdSuivi(rs.getInt(1));
                //    p.setP( rs.get("idUser"));
	          p.setDateSuivi(rs.getString("dateSuivi"));
                   p.setTaille(rs.getFloat("taille"));
                    p.setPoid( rs.getFloat("poid"));
                      p.setGlycemie(rs.getFloat("glycemie"));
	          p.setPetitDej(rs.getString("petitDej"));
                   p.setRepas(rs.getString("repas"));
                    p.setDiner(rs.getString("diner"));
                     p.setNotes(rs.getString("notes"));
                     
                      p1.setId(rs.getInt("id"));
                                p1.setNom(rs.getString("nom"));
				p1.setPrenom(rs.getString("prenom"));
                                p1.setDateNaissance(rs.getString("dateNaissance"));
                                p1.setMail(rs.getString("mail"));
                                p1.setPassword(rs.getString("password"));
                                p1.setAdresse(rs.getString("adresse"));
                                p1.setReferenceClient(rs.getInt("referenceClient"));
                                p1.setNumero(rs.getInt("numero"));
                                p1.setCivilite(rs.getString("civilite"));
                                p1.setPrivilege(rs.getString("privilege"));
                                p1.setMaladie(rs.getString("maladie"));
                     //p1.setReferenceClient(rs.getInt("referenceClient"));
              
                 lp.add(p);
                 lp1.add(p1);
                list.addAll(lp1);
                list.addAll(lp);
                        }
                  } catch (SQLException ex) {
              Logger.getLogger(suiviservice.class.getName()).log(Level.SEVERE, null, ex);
         
			}
			return list;
    }

 
    @Override
    public List<Suivi> findParId(int d) {
          List<Suivi> lesEnreg= new ArrayList<Suivi>();
          Suivi p1 = new Suivi();
       
              //    p1.setIdUser(d);
	        String req = "SELECT * FROM suivi s where s.idUser="+userservice.usercon.getId();
	   //idSuivi,dateSuivi
                 

          try {
             Statement  st = conn.createStatement();
          
             ResultSet rs = st.executeQuery(req);
                
           
	     while (rs.next()) {
                 Suivi s= new Suivi();
                      s.setIdSuivi(rs.getInt("idSuivi"));
                      s.setDateSuivi(rs.getString("dateSuivi"));
                      
                 Personne p= new Personne();
                 p = userservice.usercon;
                 s.setP(p);
                 lesEnreg.add(s);
                      
                      //.setDateSuivi(code_categorie);
            //      p.setTaille(designation);
//                    p.setPoid( rs.getFloat("poid"));
//                      p.setGlycemie(rs.getFloat("glycemie"));
//	          p.setPetitDej(rs.getString("petitDej"));
//                   p.setRepas(rs.getString("repas"));
//                    p.setDiner(rs.getString("diner"));
//                     p.setNotes(rs.getString("notes"));
//Statement state = param.conn.createStatement();
//				ResultSet rs = state.executeQuery("SELECT * FROM article");
	// Personne p5 =new Personne();
				/*	int code_article = rs.getInt("idSuivi");
					int code_utilisateur = rs.getInt("idUser");
                                Personne p5 =new Personne(us.getPersonne(code_utilisateur).toString());
					String code_categorie = rs.getString("dateSuivi");
					float designation = rs.getFloat("taille");
					float date_creation = rs.getFloat("poid");
					float quantite = rs.getFloat("glycemie");
				/*	double prix_unitaire = rs.getInt("prix_unitaire");
					boolean taxable = rs.getBoolean("taxable");*/
                                
					//lesEnreg.add(
                                       // Suivi p=  new Suivi(code_article,p5,code_categorie,designation,date_creation,quantite);*/
	/*Suivi p = new Suivi();
    Personne p2 =new Personne();
       int idP=rs.getInt("idUser");
       System.err.println(idP);
       p2= us.getPersonne(idP);
      p.setP(p2);
        
                    p.setIdSuivi(rs.getInt(1));
                          System.out.println(us.getPersonne(idP).toString());
                           System.err.println(p2.toString());*/
                      //  p.setP(d);
	       //   p.setDateSuivi(rs.getString("dateSuivi"));
           //        p.setDateSuivi(code_categorie);
            //      p.setTaille(designation);
//                    p.setPoid( rs.getFloat("poid"));
//                      p.setGlycemie(rs.getFloat("glycemie"));
//	          p.setPetitDej(rs.getString("petitDej"));
//                   p.setRepas(rs.getString("repas"));
//                    p.setDiner(rs.getString("diner"));
//                     p.setNotes(rs.getString("notes"));
                        //    lesEnreg.add(p);
//System.out.println(p.getP().getNom());
//System.out.println(p.getP().getInt()));*/


                        }
                  } catch (SQLException ex) {
              Logger.getLogger(suiviservice.class.getName()).log(Level.SEVERE, null, ex);
         
			}
			return lesEnreg;
    }

    @Override
    public List<Suivi> findPersonneParN(String n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertUser(Suivi object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Override
    public Suivi upDateSs(Suivi p,int d) {

          try {
              p.setIdSuivi(d);
           String req= "UPDATE suivi SET dateSuivi=? ,taille=?,poid=?,glycemie=?,petitDej=?,repas=?,diner=?,notes=? where idSuivi="+d;
                  // + " where idUser="+userservice.usercon.getId();
            PreparedStatement pt = conn.prepareStatement(req);
         //   pt.setInt(1,p.getIdUser());
             pt.setString(1,p.getDateSuivi());
             pt.setFloat(2, p.getTaille());
             pt.setFloat(3, p.getPoid());
              pt.setFloat(4, p.getGlycemie());
             pt.setString(5,p.getPetitDej());
               pt.setString(6,p.getRepas());
             pt.setString(7,p.getDiner());
              pt.setString(8,p.getNotes());
           //    pt.setInt(9,p.getIdSuivi());
              pt.executeUpdate();
            pt.executeUpdate();
          //  ps.close();
          
        } catch (SQLException ex) {
            Logger.getLogger(suiviservice.class.getName()).log(Level.SEVERE, null, ex);
        }
  return p;
    }

    @Override
    public List<Suivi> findAll() {
          List<Suivi> lp= new ArrayList<Suivi>();
            
         String req ="SELECT *FROM suivi ";
              
          try {
             Statement  st = conn.createStatement();
          
             ResultSet rs = st.executeQuery(req);
                
           
			while (rs.next()) {
	 Suivi p = new Suivi();
        
                    p.setIdSuivi(rs.getInt(1));
                  //  p.setIdUser( rs.getInt("idUser"));
	          p.setDateSuivi(rs.getString("dateSuivi"));
                   p.setTaille(rs.getFloat("taille"));
                    p.setPoid( rs.getFloat("poid"));
                      p.setGlycemie(rs.getFloat("glycemie"));
	          p.setPetitDej(rs.getString("petitDej"));
                   p.setRepas(rs.getString("repas"));
                    p.setDiner(rs.getString("diner"));
                     p.setNotes(rs.getString("notes"));
                     
                
              
                 lp.add(p);
               
                        }
                  } catch (SQLException ex) {
              Logger.getLogger(suiviservice.class.getName()).log(Level.SEVERE, null, ex);
         
			}
			return lp;
    }
    
      public ObservableList<Object> findAllSC() {
 
      ObservableList<Suivi> lp= FXCollections.observableArrayList();
        ObservableList<Personne> lp1= FXCollections.observableArrayList();
	 ObservableList<Object> list = FXCollections.observableArrayList();  
         //String req ="SELECT *FROM suivi ";
               String req = "SELECT * FROM  suivi,personne WHERE personne.id=suivi.idUser ";
               
                 
          try {
             Statement  st = conn.createStatement();
          
             ResultSet rs = st.executeQuery(req);
           
                               
			while (rs.next()) {
	 Suivi p = new Suivi();
        Personne p1 = new Personne();
         //Personne p1 = new Personne();   
                 p1.setId(rs.getInt("id"));
                             /*   p1.setNom(rs.getString("nom"));
				p1.setPrenom(rs.getString("prenom"));
                                p1.setDateNaissance(rs.getString("dateNaissance"));
                                p1.setMail(rs.getString("mail"));
                                p1.setPassword(rs.getString("password"));
                                p1.setAdresse(rs.getString("adresse"));
                                p1.setReferenceClient(rs.getInt("referenceClient"));
                                p1.setNumero(rs.getInt("numero"));
                                p1.setCivilite(rs.getString("civilite"));
                                p1.setPrivilege(rs.getString("privilege"));
                                p1.setMaladie(rs.getString("maladie"));*/
                    p.setIdSuivi(rs.getInt(1));
                   // p.setIdUser( rs.getInt("idUser"));
	          p.setDateSuivi(rs.getString("dateSuivi"));
                   p.setTaille(rs.getFloat("taille"));
                    p.setPoid( rs.getFloat("poid"));
                      p.setGlycemie(rs.getFloat("glycemie"));
	          p.setPetitDej(rs.getString("petitDej"));
                   p.setRepas(rs.getString("repas"));
                    p.setDiner(rs.getString("diner"));
                     p.setNotes(rs.getString("notes"));
                     
                     
                     //p1.setReferenceClient(rs.getInt("referenceClient"));
              
                 lp.add(p);
                lp1.add(p1);
                list.addAll(lp1);
               list.addAll(lp);
                        }
                        //  lp.add(p);
             //   lp1.add(p1);
                      //  lp1.add(p1);
                         // list.addAll(lp1);
                           //list.addAll(lp);
                  } catch (SQLException ex) {
              Logger.getLogger(suiviservice.class.getName()).log(Level.SEVERE, null, ex);
         
			}
			return list;
    }
    
       public ObservableList<Suivi> findAllFX() {
         ObservableList<Suivi> lp= FXCollections.observableArrayList();
            
         String req ="SELECT *FROM suivi ";
              
          try {
             Statement  st = conn.createStatement();
          
             ResultSet rs = st.executeQuery(req);
                
           
			while (rs.next()) {
	 Suivi p = new Suivi();
        
                    p.setIdSuivi(rs.getInt(1));
                    Personne c= new Personne();
               //  c = userservice.usercon;
                // int idUser 
                // p.setP(c);
                  p.setIdUser( rs.getInt("idUser"));
              //    p.setP( userservice.usercon);
                  p.setIdSuivi(rs.getInt("idSuivi"));
	          p.setDateSuivi(rs.getString("dateSuivi"));
                   p.setTaille(rs.getFloat("taille"));
                    p.setPoid( rs.getFloat("poid"));
                      p.setGlycemie(rs.getFloat("glycemie"));
	          p.setPetitDej(rs.getString("petitDej"));
                   p.setRepas(rs.getString("repas"));
                    p.setDiner(rs.getString("diner"));
                     p.setNotes(rs.getString("notes"));
                     
                
              
                 lp.add(p);
               
                        }
                  } catch (SQLException ex) {
              Logger.getLogger(suiviservice.class.getName()).log(Level.SEVERE, null, ex);
         
			}
			return lp;
    }
       
        public ObservableList<Suivi> findParIdFX(int d) {
          ObservableList<Suivi> lp= FXCollections.observableArrayList();
          Suivi p1 = new Suivi();
        
               //   p1.setIdUser(d);
	        String req ="SELECT *FROM suivi where idUser="+d;
	   //idSuivi,dateSuivi
                 

          try {
             Statement  st = conn.createStatement();
          
             ResultSet rs = st.executeQuery(req);
                
           
			while (rs.next()) {
	 Suivi p = new Suivi();
                    p.setIdSuivi(rs.getInt(1));
                // p.setP( userservice.usercon);
                 p.setIdUser(rs.getInt("idUser"));
	          p.setDateSuivi(rs.getString("dateSuivi"));
                   p.setTaille(rs.getFloat("taille"));
                    p.setPoid( rs.getFloat("poid"));
                      p.setGlycemie(rs.getFloat("glycemie"));
	          p.setPetitDej(rs.getString("petitDej"));
                   p.setRepas(rs.getString("repas"));
                    p.setDiner(rs.getString("diner"));
                     p.setNotes(rs.getString("notes"));
              
                 lp.add(p);
                        }
                  } catch (SQLException ex) {
              Logger.getLogger(suiviservice.class.getName()).log(Level.SEVERE, null, ex);
         
			}
			return lp;
    }

         public ObservableList<Suivi> findParIdFXT() {
          ObservableList<Suivi> lp= FXCollections.observableArrayList();
          Suivi p1 = new Suivi();
        
               //   p1.setIdUser(d);
	        String req ="SELECT *FROM suivi where idUser="+userservice.usercon.getId();
	   //idSuivi,dateSuivi
                 

          try {
             Statement  st = conn.createStatement();
          
             ResultSet rs = st.executeQuery(req);
                
           
			while (rs.next()) {
	 Suivi p = new Suivi();
                    p.setIdSuivi(rs.getInt(1));
                 //   p.setIdUser(rs.getInt(2));
                // p.setP( userservice.usercon);
	          p.setDateSuivi(rs.getString("dateSuivi"));
                   p.setTaille(rs.getFloat("taille"));
                    p.setPoid( rs.getFloat("poid"));
                      p.setGlycemie(rs.getFloat("glycemie"));
	          p.setPetitDej(rs.getString("petitDej"));
                   p.setRepas(rs.getString("repas"));
                    p.setDiner(rs.getString("diner"));
                     p.setNotes(rs.getString("notes"));
              
                 lp.add(p);
                        }
                  } catch (SQLException ex) {
              Logger.getLogger(suiviservice.class.getName()).log(Level.SEVERE, null, ex);
         
			}
			return lp;}
        
  /*  @Override
    public List<Suivi> findParId(int d, Suivi object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    @Override
    public Suivi upDateS(Suivi object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
}
