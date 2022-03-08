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
 */
public interface IReactionService<T> {
    public void addReaction(T t);
    public void supprimerReaction(int id);
    public void modifierReaction(T t);
    public List<T> afficherReactions();
}
