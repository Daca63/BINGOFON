/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;

/**
 *
 * @author Ari
 */
public class Igra implements Serializable {
    
    private RezultatIgre rezultatIgre;
    private int rb;
    private String kombinacija;
    private int pogodjenihNaMestu;
    private int pogodjenihNisuNaMestu;

    public Igra(RezultatIgre rezultatIgre, int rb, String kombinacija, int pogodjenihNaMestu, int pogodjenihNisuNaMestu) {
        this.rezultatIgre = rezultatIgre;
        this.rb = rb;
        this.kombinacija = kombinacija;
        this.pogodjenihNaMestu = pogodjenihNaMestu;
        this.pogodjenihNisuNaMestu = pogodjenihNisuNaMestu;
    }

    public Igra() {
    }

    public String getKombinacija() {
        return kombinacija;
    }

    public void setKombinacija(String kombinacija) {
        this.kombinacija = kombinacija;
    }

    public int getPogodjenihNaMestu() {
        return pogodjenihNaMestu;
    }

    public void setPogodjenihNaMestu(int pogodjenihNaMestu) {
        this.pogodjenihNaMestu = pogodjenihNaMestu;
    }

    public int getPogodjenihNisuNaMestu() {
        return pogodjenihNisuNaMestu;
    }

    public void setPogodjenihNisuNaMestu(int pogodjenihNisuNaMestu) {
        this.pogodjenihNisuNaMestu = pogodjenihNisuNaMestu;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public RezultatIgre getRezultatIgre() {
        return rezultatIgre;
    }

    public void setRezultatIgre(RezultatIgre rezultatIgre) {
        this.rezultatIgre = rezultatIgre;
    }

}
