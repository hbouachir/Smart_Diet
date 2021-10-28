/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author Nour
 * @param <T>
 */
public interface Service<T> {
    void ajouterRdv (T objet);
    void update (T objet);
    void delete (T objet);
    List<T> findAll();
}
