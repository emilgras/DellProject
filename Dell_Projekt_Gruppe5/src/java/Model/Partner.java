/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author ABjergfelt
 */
public class Partner {


   
    private String name;
    private String role;

    private String cvr;
    private String country;
    private Date date;
    private String username;
    private String password;
    private int pno;


    public Partner(String username, String password, String name, String cvr, String country) {
        this.name = name;
        this.cvr = cvr;
        this.username = username;
        this.password = password;
        this.country = country;
    }
    
    public Partner(String name, String CVR, Date date){
        this.name = name;
        this.cvr = CVR;
        this.date = date;
    }

    public Partner(String name, String cvr, String country, String username) {
        this.name = name;
        this.cvr = cvr;
        this.country = country;
        this.username = username;
    }

    
    
    

    public Partner() {
    }
    
    public Partner(String name, String cvr, String country){
        this.name = name;
        this.cvr = cvr;
        this.country = country;
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getCvr() {
        return cvr;
    }

    public void setCvr(String cvr) {
        this.cvr = cvr;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    
}
