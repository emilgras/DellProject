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
    String partnerNavn;
    float pris;
    
    public Budget(int kno, String pn, float pris){
        this.kno = kno;
        this.partnerNavn = pn;
        this.pris = pris;
    }

    public int getKno() {
        return kno;
    }

    public void setKno(int kno) {
        this.kno = kno;
    }

    public String getPartnerNavn() {
        return partnerNavn;
    }

    public void setPartnerNavn(String PartnerNavn) {
        this.partnerNavn = PartnerNavn;
    }

    public float getPris() {
        return pris;
    }

    public void setPris(float pris) {
        this.pris = pris;
    }

    @Override
    public String toString() {
        return "Budget{" + "kno=" + kno + ", partnerNavn=" + partnerNavn + ", pris=" + pris + '}';
    }
    
    
}
