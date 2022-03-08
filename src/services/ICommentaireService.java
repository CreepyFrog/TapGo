/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author Chikaaa
 * @param <T>
 */
public interface ICommentaireService <T>{
  public void addCommentaire(T t);
    public void supprimerCommentaire(int id);
    public void modifierCommentaire(T t);
    public List<T> afficherCommentaire();  
}
