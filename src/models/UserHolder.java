/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author TECHNOPC
 */
public class UserHolder {
        private static UserHolder instance;
        private User user;
 public static UserHolder getInstance()  {
        if (instance == null) {
            instance = new UserHolder();



        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
 
}
