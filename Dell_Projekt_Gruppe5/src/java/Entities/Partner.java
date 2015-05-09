/*
 * Frederik og Anders har arbejdet i den her klasse
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author ABjergfelt
 */
public class Partner {

    private String name;
    private String cvr;
    private String country;
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

    public Partner(String name, String cvr, String country, int pno) {
        this.name = name;
        this.cvr = cvr;
        this.country = country;
        this.pno = pno;
        }

    public String getUsername() {
        return username;
        }

    public String getPassword() {
        return password;
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

    public String getCvr() {
        return cvr;
        }

    public String getCountry() {
        return country;
        }
    }
