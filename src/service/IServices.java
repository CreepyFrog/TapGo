package service;

import java.util.List;

/**
 *
 * @author trabe
 */
public interface IServices<T> {
    public void ajouter(T t);
    public void supprimer(T t);
    public void modifier(T t);
    public List<T> afficher();
}
