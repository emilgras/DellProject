/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author Frederik
 */
public class Campaign {

    private int kno;
    private String beskrivelse;
    private String status;
    private String oprettelse_dato;
    private String start_dato;
    private String slut_dato;
    private float pris;
    private int pno;
    private String navn;
    private String cvr;

    public Campaign(String start_dato, String slut_dato, float pris, String beskrivelse, int pno) {
        this.beskrivelse = beskrivelse;
        this.start_dato = start_dato;
        this.slut_dato = slut_dato;
        this.pris = pris;
        this.pno = pno;
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

    public String getStatus() {
        return status;
        }

    public String getOprettelse_dato() {
        return oprettelse_dato;
        }

    public String getStart_dato() {
        return start_dato;
        }

    public String getSlut_dato() {
        return slut_dato;
        }

    public float getPris() {
        return pris;
        }

    public int getPno() {
        return pno;
        }

    public String getNavn() {
        return navn;
        }

    public String getCvr() {
        return cvr;
        }

    }
