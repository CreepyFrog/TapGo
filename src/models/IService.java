/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;

/**
 *
 * @author ASUS
 */
public interface IService<T> {
    void ajouter(T entity);
    void supprimer(T entity);
    void modifier(T entity);
    List<T> afficher();
    T find(int id);
    T find(String name);
}
