/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

/**
 *
 * @author Shahriar Ivan
 */
public class User {
    private String name;
    private String pass;

    public User() {
        ;
    }
    
    public void setUser(String _name, String _pass) {
        name = _name;
        pass = _pass;
    }
    
    public String get_name(){
        return name;
    }
    
    public String get_pass(){
        return pass;
    }
}
