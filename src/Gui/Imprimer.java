
package Gui;

import Payments.entities.Facture;
import java.text.MessageFormat;
import javafx.scene.control.TableView;
import javax.swing.JOptionPane;
import javax.swing.JTable;


public class Imprimer {
       public static void imprimer(JTable jt , String titre){
        MessageFormat entete = new MessageFormat(titre);
        MessageFormat pied = new MessageFormat("Page (0, number, integer");
        try{
            jt.print(JTable.PrintMode.FIT_WIDTH, entete, pied);
        }catch (Exception e ){
        JOptionPane.showMessageDialog(null, "erreur "+e, "impression", JOptionPane.ERROR_MESSAGE);}
    }

    static void imprimer(TableView<Facture> tabFacture, String titre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
