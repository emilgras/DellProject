/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;



/**
 *
 * @author Frederik
 */
public class Campaign {

    private int kno;
    private String description;
    private String status;
    private String created_date;
    private String start_date;
    private String end_date;
    private float price;
    private int pno;
    private String name;
    private String cvr;

    public Campaign(String start_date, String end_date, float price, String description, int pno) {
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.price = price;
        this.pno = pno;
        }

    public Campaign(int kno, String description, String status, String created_date, String start_date, String end_date, float price, int pno, String name, String cvr) {
        this.kno = kno;
        this.description = description;
        this.status = status;
        this.created_date = created_date;
        this.start_date = start_date;
        this.end_date = end_date;
        this.price = price;
        this.pno = pno;
        this.name = name;
        this.cvr = cvr;
        }

    public int getKno() {
        return kno;
        }

    public void setKno(int kno) {
        this.kno = kno;
        }

    public String getDescription() {
        return description;
        }

    public String getStatus() {
        return status;
        }

    public String getCreated_date() {
        return created_date;
        }

    public String getStart_date() {
        return start_date;
        }

    public String getEnd_date() {
        return end_date;
        }

    public float getPrice() {
        return price;
        }

    public int getPno() {
        return pno;
        }

    public String getName() {
        return name;
        }

    public String getCvr() {
        return cvr;
        }

    }
