/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Publication;
import java.util.List;

/**
 *
 * @author Chikaaa
 */
public interface IPublicationService<T> {
    public void addPublication(T t);
    public void supprimerPublication(int id);
    public void modifierPublication(T ts);
    public List<T> afficherPublication();
    
    
}
