/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;



/**
 *
 * @author ABjergfelt
 */
public class User {
    
    private String username; 
    private String password; 
    private String role;
    private int pno;

    public User(String username, String password, String role, int pno) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.pno = pno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }
    
    
}
