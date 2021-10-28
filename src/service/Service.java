/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author HAMZA
 * @param <T>
 */
public interface Service<T> {
    void insert(T object);
    void update(T object,int id);
    void delete(int id);
    
        
    ObservableList<T> findByUserId(int id);

    ObservableList<T> findAll();

    
}
