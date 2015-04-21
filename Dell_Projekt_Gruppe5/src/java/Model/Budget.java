/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Frederik
 */
public class Budget {
    int kno;
    String PartnerNavn;
    float pris;
    
    public Budget(int kno, String pn, float pris){
        this.kno = kno;
        this.PartnerNavn = pn;
        this.pris = pris;
    }

    public int getKno() {
        return kno;
    }

    public void setKno(int kno) {
        this.kno = kno;
    }

    public String getPartnerNavn() {
        return PartnerNavn;
    }

    public void setPartnerNavn(String PartnerNavn) {
        this.PartnerNavn = PartnerNavn;
    }

    public float getPris() {
        return pris;
    }

    public void setPris(float pris) {
        this.pris = pris;
    }
    
}
