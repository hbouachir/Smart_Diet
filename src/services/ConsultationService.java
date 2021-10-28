/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import utils.MyDB;

/**
 *
 * @author Nour
 */
public class ConsultationService {
    private Connection connection;
    private PreparedStatement ste;

    public ConsultationService() {
        connection = MyDB.getInstance().getConnection();
    }
        
    
        
        
        
        
}
