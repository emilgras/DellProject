/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
/**
 *
 * @author Frederik
 */
public class Campaign {
    int kno;
    String beskrivelse;
    String status;
    Date oprettelse_dato;
    String start_dato;
    String slut_dato;
    float pris;
    int pno;

    public Campaign(int kno, String beskrivelse, String status, Date oprettelse_dato, Date start_dato, Date slut_dato, int pris, int pno) {
        this.kno = kno;
        this.beskrivelse = beskrivelse;
        this.status = status;
        this.oprettelse_dato = oprettelse_dato;
        //this.start_dato = start_dato;
        //this.slut_dato = slut_dato;
        this.pris = pris;
        this.pno = pno;
    }

    public Campaign(String start_dato, String slut_dato, float pris, String beskrivelse) {
        this.beskrivelse = beskrivelse;
        this.start_dato = start_dato;
        this.slut_dato = slut_dato;
        this.pris = pris;
    }
    
    
    
    public Campaign(Campaign camp){
        this.kno = camp.getKno();
        this.beskrivelse = camp.getBeskrivelse();
        this.status = camp.getStatus();
        this.oprettelse_dato = camp.getOprettelse_dato();
        //this.start_dato = camp.getStart_dato();
        //this.slut_dato = camp.getSlut_dato();
        //this.pris = camp.getPris();
        this.pno = camp.getPno();
    }

    public int getKno() {
        return kno;
    }

    public void setKno(int kno) {
        this.kno = kno;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOprettelse_dato() {
        return oprettelse_dato;
    }

    public void setOprettelse_dato(Date oprettelse_dato) {
        this.oprettelse_dato = oprettelse_dato;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }
    
    
    
}
