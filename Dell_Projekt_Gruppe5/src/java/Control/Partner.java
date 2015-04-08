/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.sql.Date;

/**
 *
 * @author ABjergfelt
 */
public class Partner {
    private int pno;
    private String cvr; 
    private String name; 
    private Date date;

    public Partner(int pno, String cvr, String name, Date date) {
        this.pno = pno;
        this.cvr = cvr;
        this.name = name;
        this.date = date;
    }

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    public String getCvr() {
        return cvr;
    }

    public void setCvr(String cvr) {
        this.cvr = cvr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
}
