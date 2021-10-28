/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import entities.Personne;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.exit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import utils.Data_B;

/**
 *
 * @author cyrine belhssan
 */
public class userservice implements services <Personne>{
    Connection conn;
    public static Personne usercon = new Personne();
    public userservice(){
        conn= Data_B.getInstance().getConnection();
    }

     public int FindUser(String mail, String pass,String role) throws SQLException {
        int re = 0;
        String sql = "select * from personne where mail = ? and password = ? and privilege = ?";
        try {
             //String sql = "select * from personne where mail=? and password=? and privilege=?";
          //Statement  st = conn.createStatement();
          PreparedStatement st = conn.prepareStatement(sql); 
          st.setString(1, mail);
          st.setString(2, pass);
          st.setString(3, role);
         //   rst = (ResultSet) st.executeQuery(sql);
          //  ResultSet rst = st.executeQuery(sql);
            ResultSet rst = st.executeQuery();
           if (rst.next()) {
//JOptionPane.showMessageDialog(null, "données correct");
Personne p = new Personne(rst.getInt("id"), rst.getString("nom"), rst.getString("prenom"), rst.getString("dateNaissance"), rst.getString("mail"), rst.getString("password"), rst.getString("adresse"), rst.getInt("referenceClient"), rst.getInt("numero"), rst.getString("civilite"), rst.getString("privilege"), rst.getString("maladie"));
                    usercon=p;
                    
                    re = 1;  }       
/*  if (rst.getString("mail").equals(mail) && rst.getString("password").equals(pass)) {
                    Personne p = new Personne(rst.getInt("id"), rst.getString("nom"), rst.getString("prenom"), rst.getString("dateNaissance"), rst.getString("mail"), rst.getString("password"), rst.getString("adresse"), rst.getInt("referenceClient"), rst.getInt("numero"), rst.getString("civilite"), rst.getString("privilege"), rst.getString("maladie"));
                    usercon=p;
                    
                    re = 1;
                }*/ 
           else 
                    re = 0;
               
            
        } catch (SQLException ex) {
            Logger.getLogger(Data_B.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }

        return re;
    }
     

    @Override
    public void insertUser(Personne p) {
        String req ="INSERT INTO `personne`(`nom`,`prenom`,`dateNaissance`,`mail`,`password`,`adresse`,`referenceClient`,`numero`,`civilite`,`privilege`,`maladie`)"
                +"values (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pt = conn.prepareStatement(req);
            
            pt.setString(1,p.getNom());
             pt.setString(2,p.getPrenom());
             pt.setString(3, p.getDateNaissance());
              pt.setString(4,p.getMail());
               pt.setString(5,p.getPassword());
             pt.setString(6,p.getAdresse());
              pt.setInt(7,p.getReferenceClient());
              pt.setInt(8,p.getNumero());
               pt.setString(9,p.getCivilite());
             pt.setString(10,p.getPrivilege());
              pt.setString(11,p.getMaladie());
              pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @Override
    public void insertAdmin(Personne p) {
        String req ="INSERT INTO `personne`(`nom`,`prenom`,`dateNaissance`,`mail`,`password`,`adresse`,`numero`,`civilite`,`privilege`)"
                +"values (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pt = conn.prepareStatement(req);
            
            pt.setString(1,p.getNom());
             pt.setString(2,p.getPrenom());
                pt.setString(3, p.getDateNaissance());
              pt.setString(4,p.getMail());
               pt.setString(5,p.getPassword());
           pt.setString(6,p.getAdresse());
           //   pt.setInt(6,p.getReferenceClient());
              pt.setInt(7,p.getNumero());
              pt.setString(8,p.getCivilite());
            pt.setString(9,p.getPrivilege());
           //   pt.setString(5,p.getMaladie());
              pt.executeUpdate();
              // JOptionPane.showMessageDialog(null,"adddd");
        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    @Override
    public void insertAutre(Personne p) {
       
         String req ="INSERT INTO `personne`(`nom`,`prenom`,`mail`,`password`,`adresse`,`numero`,`privilege`)"
                +"values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pt = conn.prepareStatement(req);
            
            pt.setString(1,p.getNom());
             pt.setString(2,p.getPrenom());
              pt.setString(3,p.getMail());
               pt.setString(4,p.getPassword());
             pt.setString(5,p.getAdresse());
           //   pt.setInt(6,p.getReferenceClient());
             pt.setInt(6,p.getNumero());
            //  pt.setString(7,p.getCivilite());
             pt.setString(7,p.getPrivilege());
             // pt.setString(10,p.getMaladie());
              pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

@Override
    public Personne getPersonne(int id) {
         Personne p = new Personne();
            
        try {
             p.setId(id);
        String req = "SELECT * FROM  personne WHERE id="+id;
   
          Statement  ps = conn.createStatement();
         
                
			ResultSet rs = ps.executeQuery(req);
			while (rs.next()) {
				
				p.setId(rs.getInt("id"));
                                p.setNom(rs.getString("nom"));
				p.setPrenom(rs.getString("prenom"));
                                p.setDateNaissance(rs.getString("dateNaissance"));
                                p.setMail(rs.getString("mail"));
                                p.setPassword(rs.getString("password"));
                                p.setAdresse(rs.getString("adresse"));
                                p.setReferenceClient(rs.getInt("referenceClient"));
                                p.setNumero(rs.getInt("numero"));
                                p.setCivilite(rs.getString("civilite"));
                                p.setPrivilege(rs.getString("privilege"));
                                p.setMaladie(rs.getString("maladie"));
                        }

        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public Personne upDatePersonne(Personne p) {
       
           try {
             // p.setId(id);
           String req= "UPDATE personne SET nom=?,prenom=?,dateNaissance=?,mail=?,password=?,adresse=?,referenceClient=?,numero=?,civilite=?,maladie=?WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
            ps.setString(3, p.getDateNaissance());
            ps.setString(4, p.getMail());
             ps.setString(5, p.getPassword());
               ps.setString(6, p.getAdresse());
            ps.setInt(7, p.getReferenceClient());
            ps.setInt(8, p.getNumero());
             ps.setString(9, p.getCivilite());
           // ps.setString(10, p.getPrivilege());
            ps.setString(10, p.getMaladie());
            ps.setInt(11,userservice.usercon.getId());
            ps.executeUpdate();
          //  ps.close();
          
        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
  return p;
    }
    
    public Personne upDateu(Personne p,int d) {

          try {
              p.setId(d);
           String req= "UPDATE personne SET nom=?,prenom=?,dateNaissance=?,mail=?,password=?,adresse=?,referenceClient=?,numero=?,civilite=?,privilege=? ,maladie=? WHERE id="+d;
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
            ps.setString(3, p.getDateNaissance());
            ps.setString(4, p.getMail());
             ps.setString(5, p.getPassword());
               ps.setString(6, p.getAdresse());
            ps.setInt(7, p.getReferenceClient());
            ps.setInt(8, p.getNumero());
             ps.setString(9, p.getCivilite());
            ps.setString(10, p.getPrivilege());
            ps.setString(11, p.getMaladie());
          //  ps.setInt(11,userservice.usercon.getId());
            ps.executeUpdate();
          
        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
  return p;
    }
    
    ////////////
     public Personne upDatenc(Personne p,int d) {

          try {
              p.setId(d);
           String req= "UPDATE personne SET nom=?,prenom=?,dateNaissance=?,mail=?,password=?,adresse=?,numero=?,civilite=?,privilege=? WHERE id="+d;
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
            ps.setString(3, p.getDateNaissance());
            ps.setString(4, p.getMail());
             ps.setString(5, p.getPassword());
               ps.setString(6, p.getAdresse());
         //   ps.setInt(7, p.getReferenceClient());
            ps.setInt(7, p.getNumero());
             ps.setString(8, p.getCivilite());
            ps.setString(9, p.getPrivilege());
           // ps.setString(11, p.getMaladie());
          //  ps.setInt(11,userservice.usercon.getId());
            ps.executeUpdate();
          
        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
  return p;
    }
    ///////////
      public Personne upDatNuC(Personne p) {

          try {
            //  p.setId(d);
           String req= "UPDATE personne SET nom=?,prenom=?,dateNaissance=?,mail=?,password=?,adresse=?,numero=?,civilite=? WHERE id="+userservice.usercon.getId();
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
            ps.setString(3, p.getDateNaissance());
            ps.setString(4, p.getMail());
             ps.setString(5, p.getPassword());
               ps.setString(6, p.getAdresse());
         //   ps.setInt(7, p.getReferenceClient());
            ps.setInt(7, p.getNumero());
             ps.setString(8, p.getCivilite());
            //ps.setString(9, p.getPrivilege());
           // ps.setString(11, p.getMaladie());
          //  ps.setInt(11,userservice.usercon.getId());
            ps.executeUpdate();
          
        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
  return p;
    }
     /////

    @Override
    public void deletePersonne(int id) {

          try {
            String req="DELETE FROM personne WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     @Override
   public List<Personne> findAll() {
           List<Personne> listP =new ArrayList<>();
        String req ="SELECT * FROM personne ";
        try {
            Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(req);
             while(rs.next()){
                 Personne p = new Personne();
                /* p.setId(re.getInt(1));
                 p.setPrenom(re.getString("prenom"));
                 p.setNom(re.getString("nom"));
                 p.setMail(re.getString("email"));*/
                 
                                p.setId(rs.getInt(1));
                                p.setNom(rs.getString("nom"));
				p.setPrenom(rs.getString("prenom"));
                                p.setDateNaissance(rs.getString("dateNaissance"));
                                p.setMail(rs.getString("mail"));
                                p.setPassword(rs.getString("password"));
                                p.setAdresse(rs.getString("adresse"));
                                p.setReferenceClient(rs.getInt("referenceClient"));
                                p.setNumero(rs.getInt("numero"));
                                p.setCivilite(rs.getString("civilite"));
                                p.setPrivilege(rs.getString("privilege"));
                                p.setMaladie(rs.getString("maladie"));
                 listP.add(p);
             }
        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
     return listP;
    }
    
     public ObservableList<Personne>findAllFXAdmin() {
          ObservableList<Personne>  listP =FXCollections.observableArrayList();
        String req ="SELECT * FROM personne ";
        try {
            Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(req);
             while(rs.next()){
                 Personne p = new Personne();
                /* p.setId(re.getInt(1));
                 p.setPrenom(re.getString("prenom"));
                 p.setNom(re.getString("nom"));
                 p.setMail(re.getString("email"));*/
                 
                                p.setId(rs.getInt(1));
                                p.setNom(rs.getString("nom"));
				p.setPrenom(rs.getString("prenom"));
                                p.setDateNaissance(rs.getString("dateNaissance"));
                                p.setMail(rs.getString("mail"));
                                p.setPassword(rs.getString("password"));
                                p.setAdresse(rs.getString("adresse"));
                                p.setReferenceClient(rs.getInt("referenceClient"));
                                p.setNumero(rs.getInt("numero"));
                                p.setCivilite(rs.getString("civilite"));
                                p.setPrivilege(rs.getString("privilege"));
                                p.setMaladie(rs.getString("maladie"));
                 listP.add(p);
             }
        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
     return listP;
    }

 
   public  ObservableList<Personne> findAllFX() {
           ObservableList<Personne> lp =FXCollections.observableArrayList();
        String req ="SELECT * FROM personne where id= "+userservice.usercon.getId();
        try {
            Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(req);
             while(rs.next()){
                 Personne p = new Personne();
                /* p.setId(re.getInt(1));
                 p.setPrenom(re.getString("prenom"));
                 p.setNom(re.getString("nom"));
                 p.setMail(re.getString("email"));*/
                 
                               p.setId(userservice.usercon.getId());
                                p.setNom(rs.getString("nom"));
				p.setPrenom(rs.getString("prenom"));
                                p.setDateNaissance(rs.getString("dateNaissance"));
                                p.setMail(rs.getString("mail"));
                                p.setPassword(rs.getString("password"));
                                p.setAdresse(rs.getString("adresse"));
                              //  p.setReferenceClient(rs.getInt("referenceClient"));
                                p.setNumero(rs.getInt("numero"));
                                p.setCivilite(rs.getString("civilite"));
                                //p.setPrivilege(rs.getString("privilege"));
                              //  p.setMaladie(rs.getString("maladie"));
                 lp.add(p);
             }
        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
     return lp;
    }
    @Override
    public List<Personne> findPersonneParN(String n) {
 List<Personne> lp= new ArrayList<Personne>();
	        String req ="SELECT * FROM personne where nom like ? ";
              
	       try {
			PreparedStatement ps= conn.prepareStatement(req);
			  ps.setString(1, "%"+n+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
		 Personne p = new Personne();
                                p.setId(rs.getInt(1));
                                p.setNom(rs.getString("nom"));
				p.setPrenom(rs.getString("prenom"));
                                p.setDateNaissance(rs.getString("dateNaissance"));
                                p.setMail(rs.getString("mail"));
                                p.setPassword(rs.getString("password"));
                                p.setAdresse(rs.getString("adresse"));
                                p.setReferenceClient(rs.getInt("referenceClient"));
                                p.setNumero(rs.getInt("numero"));
                                p.setCivilite(rs.getString("civilite"));
                                p.setPrivilege(rs.getString("privilege"));
                                p.setMaladie(rs.getString("maladie"));
                 lp.add(p);								
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

			return lp;
    }

    @Override
    public List<Personne> findParId(int d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   public ObservableList<Personne> findAllFx() {
          ObservableList<Personne> listP =FXCollections.observableArrayList();
        String req ="SELECT * FROM personne ";
        try {
            Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(req);
             while(rs.next()){
                 Personne p = new Personne();
                /* p.setId(re.getInt(1));
                 p.setPrenom(re.getString("prenom"));
                 p.setNom(re.getString("nom"));
                 p.setMail(re.getString("email"));*/
                 
                                p.setId(rs.getInt(1));
                                p.setNom(rs.getString("nom"));
				p.setPrenom(rs.getString("prenom"));
                                p.setDateNaissance(rs.getString("dateNaissance"));
                                p.setMail(rs.getString("mail"));
                                p.setPassword(rs.getString("password"));
                                p.setAdresse(rs.getString("adresse"));
                                p.setReferenceClient(rs.getInt("referenceClient"));
                                p.setNumero(rs.getInt("numero"));
                                p.setCivilite(rs.getString("civilite"));
                                p.setPrivilege(rs.getString("privilege"));
                                p.setMaladie(rs.getString("maladie"));
                 listP.add(p);
             }
        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
     return listP;
   }
    @Override
    public void insertSuivi(Personne object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Personne upDateS(Personne object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   public ObservableList<Personne> findPersonneParNFX(String n) {
 ObservableList<Personne> lp =FXCollections.observableArrayList();
	        String req ="SELECT * FROM personne where  privilege like ? ";
               // and privilege='client'
              
	       try {
			PreparedStatement ps= conn.prepareStatement(req);
			  ps.setString(1, "%"+n+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
		 Personne p = new Personne();
                                p.setId(rs.getInt(1));
                                p.setNom(rs.getString("nom"));
				p.setPrenom(rs.getString("prenom"));
                                p.setDateNaissance(rs.getString("dateNaissance"));
                                p.setMail(rs.getString("mail"));
                                p.setPassword(rs.getString("password"));
                                p.setAdresse(rs.getString("adresse"));
                                p.setReferenceClient(rs.getInt("referenceClient"));
                                p.setNumero(rs.getInt("numero"));
                                p.setCivilite(rs.getString("civilite"));
                                p.setPrivilege(rs.getString("privilege"));
                                p.setMaladie(rs.getString("maladie"));
                 lp.add(p);								
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

			return lp;
    }
   public ObservableList<Personne> findPersonneParNFXNu(String n) {
 ObservableList<Personne> lp =FXCollections.observableArrayList();
	        String req ="SELECT * FROM personne where  privilege LIKE ?";
               // and privilege='client'
              
	       try {
			PreparedStatement ps= conn.prepareStatement(req);
			 ps.setString(1, "%"+n+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
		 Personne p = new Personne();
                                p.setId(rs.getInt(1));
                                p.setNom(rs.getString("nom"));
				p.setPrenom(rs.getString("prenom"));
                                p.setDateNaissance(rs.getString("dateNaissance"));
                                p.setMail(rs.getString("mail"));
                                p.setPassword(rs.getString("password"));
                                p.setAdresse(rs.getString("adresse"));
                                p.setReferenceClient(rs.getInt("referenceClient"));
                                p.setNumero(rs.getInt("numero"));
                                p.setCivilite(rs.getString("civilite"));
                                p.setPrivilege(rs.getString("privilege"));
                                p.setMaladie(rs.getString("maladie"));
                 lp.add(p);								
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

			return lp;
    }
   
    public Personne upDatePersonneT(Personne p, int d) {
       
           try {
             // p.setId(id);
           String req= "UPDATE personne SET nom=?,prenom=?,dateNaissance=?,mail=?,password=?,adresse=?,referenece=?,numero=?,civilite=?,privilege=?, maladie=? where id="+d;
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
           ps.setString(3, p.getDateNaissance());
            ps.setString(4, p.getMail());
             ps.setString(5, p.getPassword());
               ps.setString(6, p.getAdresse());
            ps.setInt(7, p.getReferenceClient());
            ps.setInt(8, p.getNumero());
            ps.setString(9, p.getCivilite());
            ps.setString(10, p.getPrivilege());
           ps.setString(11, p.getMaladie());
           // ps.setInt(8,p.getId());
            ps.executeUpdate();
          //  ps.close();
          
        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
  return p;
    }
   
    public ObservableList<Personne> findClientParNFX(String n) {
 ObservableList<Personne> lp =FXCollections.observableArrayList();
	        String req ="SELECT * FROM personne where privilege like ? ";
              
	       try {
			PreparedStatement ps= conn.prepareStatement(req);
			  ps.setString(1, "%"+n+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
		 Personne p = new Personne();
                                p.setId(rs.getInt(1));
                                p.setNom(rs.getString("nom"));
				p.setPrenom(rs.getString("prenom"));
                                p.setDateNaissance(rs.getString("dateNaissance"));
                                p.setMail(rs.getString("mail"));
                                p.setPassword(rs.getString("password"));
                                p.setAdresse(rs.getString("adresse"));
                                p.setReferenceClient(rs.getInt("referenceClient"));
                                p.setNumero(rs.getInt("numero"));
                                p.setCivilite(rs.getString("civilite"));
                                p.setPrivilege(rs.getString("privilege"));
                                p.setMaladie(rs.getString("maladie"));
                 lp.add(p);								
			}
				
		} catch (SQLException e) {
			
			//e.printStackTrace();
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
		}

			return lp;
    }
    /////
      public ObservableList<Personne> findClientParRefNu(int n) {
 ObservableList<Personne> lp =FXCollections.observableArrayList();
	        String req ="SELECT * FROM personne where referenceClient= "+n;
              
	       try {
			PreparedStatement ps= conn.prepareStatement(req);
			 // ps.setString(1, "%"+n+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
		 Personne p = new Personne();
                                p.setId(rs.getInt(1));
                                p.setNom(rs.getString("nom"));
				p.setPrenom(rs.getString("prenom"));
                                p.setDateNaissance(rs.getString("dateNaissance"));
                                p.setMail(rs.getString("mail"));
                                p.setPassword(rs.getString("password"));
                                p.setAdresse(rs.getString("adresse"));
                                p.setReferenceClient(rs.getInt("referenceClient"));
                                p.setNumero(rs.getInt("numero"));
                                p.setCivilite(rs.getString("civilite"));
                                p.setPrivilege(rs.getString("privilege"));
                                p.setMaladie(rs.getString("maladie"));
                 lp.add(p);								
			}
				
		} catch (SQLException e) {
			
			//e.printStackTrace();
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
		}

			return lp;
    }
   
     public ObservableList<Personne> findClientParNFXML() {
 ObservableList<Personne> lp =FXCollections.observableArrayList();
  Personne p = new Personne();
	        String req ="SELECT * FROM personne ";
              
	       try {
			PreparedStatement ps= conn.prepareStatement(req);
			 // ps.setString(1, "%"+n+"%");
			ResultSet rs = ps.executeQuery();
			//while (rs.next()) {
		
                               // p.setId(rs.getInt(1));
                                p.setNom(userservice.usercon.getNom());
                                p.setPrenom(userservice.usercon.getPrenom());
                                p.setDateNaissance(userservice.usercon.getDateNaissance());
                                p.setMail(userservice.usercon.getMail());
                                p.setPassword(userservice.usercon.getPassword());
                                p.setAdresse(userservice.usercon.getAdresse());
                                p.setReferenceClient(userservice.usercon.getReferenceClient());
                                p.setNumero(userservice.usercon.getNumero());
                                p.setCivilite(userservice.usercon.getCivilite());
                                p.setMaladie(userservice.usercon.getMaladie());
                               // p.setNom(userservice.usercon.getNom());
				/*p.setPrenom(rs.getString("prenom"));
                                p.setDateNaissance(rs.getString("dateNaissance"));
                                p.setMail(rs.getString("mail"));
                                p.setPassword(rs.getString("password"));
                                p.setAdresse(rs.getString("adresse"));
                                p.setReferenceClient(rs.getInt("referenceClient"));
                                p.setNumero(rs.getInt("numero"));
                                p.setCivilite(rs.getString("civilite"));
                                p.setPrivilege(rs.getString("privilege"));
                                p.setMaladie(rs.getString("maladie"));*/
                lp.add(p);								
			//}
				
		} catch (SQLException e) {
			
			//e.printStackTrace();
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
		}

			return lp;
    }
    
}
