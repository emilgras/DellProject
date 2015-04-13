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
    String oprettelse_dato;
    String start_dato;
    String slut_dato;
    float pris;
    int pno;
    String navn;
    String cvr;

    public Campaign(int kno, String beskrivelse, String status, String oprettelse_dato, String start_dato, String slut_dato, float pris, int pno) {
        this.kno = kno;
        this.beskrivelse = beskrivelse;
        this.status = status;
        this.oprettelse_dato = oprettelse_dato;
        this.start_dato = start_dato;
        this.slut_dato = slut_dato;
        this.pris = pris;
        this.pno = pno;
    }

    public Campaign(String start_dato, String slut_dato, float pris, String beskrivelse) {
        this.beskrivelse = beskrivelse;
        this.start_dato = start_dato;
        this.slut_dato = slut_dato;
        this.pris = pris;
    }

    public Campaign(int kno, String beskrivelse, String status, String oprettelse_dato, String start_dato, String slut_dato, float pris, int pno, String navn, String cvr) {
        this.kno = kno;
        this.beskrivelse = beskrivelse;
        this.status = status;
        this.oprettelse_dato = oprettelse_dato;
        this.start_dato = start_dato;
        this.slut_dato = slut_dato;
        this.pris = pris;
        this.pno = pno;
        this.navn = navn;
        this.cvr = cvr;
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

    public String getOprettelse_dato() {
        return oprettelse_dato;
    }

    public void setOprettelse_dato(String oprettelse_dato) {
        this.oprettelse_dato = oprettelse_dato;
    }

    public String getStart_dato() {
        return start_dato;
    }

    public void setStart_dato(String start_dato) {
        this.start_dato = start_dato;
    }

    public String getSlut_dato() {
        return slut_dato;
    }

    public void setSlut_dato(String slut_dato) {
        this.slut_dato = slut_dato;
    }

    public float getPris() {
        return pris;
    }

    public void setPris(float pris) {
        this.pris = pris;
    }

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getCvr() {
        return cvr;
    }

    public void setCvr(String cvr) {
        this.cvr = cvr;
    }

    @Override
    public String toString() {
        return "Campaign{" + "kno=" + kno + ", beskrivelse=" + beskrivelse + ", status=" + status + ", oprettelse_dato=" + oprettelse_dato + ", start_dato=" + start_dato + ", slut_dato=" + slut_dato + ", pris=" + pris + ", pno=" + pno + ", navn=" + navn + ", cvr=" + cvr + '}';
    }
    
    
    
}