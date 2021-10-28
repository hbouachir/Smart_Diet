/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

//import entities.Personne;
import entities.Personne;
import java.util.List;

/**
 *
 * @author cyrine belhssan
 * @param <T>
 */
public interface services <T>{
    public void insertUser (T object);
     public void insertSuivi (T object);
    public void insertAdmin(T object);
    public void insertAutre(T object) ;
   public T getPersonne(int id);
   public T upDatePersonne(T object);
    public T upDateS(T object);
    void deletePersonne (int id);
     public List<T> findAll ();
    public List<T> findPersonneParN(String n);
    public List<T> findParId(int d);
}
