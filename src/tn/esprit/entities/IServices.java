/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.util.List;

/**
 *
 * @author ASUS
 */
public interface IServices<T> {
    void ajouter(T entity);
    void supprimer(T entity);
    void modifier(T entity);
    List<T> afficher();
}
