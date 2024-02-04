/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Ari
 */
public class RezultatIgre implements Serializable {

    private long rezultatIgreID;
    private Date datumVreme;
    private String zadataKombinacija;
    private int brojPokusaja;
    private String rezultat;
    private ArrayList<Igra> igre;

    public RezultatIgre(long rezultatIgreID, Date datumVreme, String zadataKombinacija, int brojPokusaja, String rezultat, ArrayList<Igra> igre) {
        this.rezultatIgreID = rezultatIgreID;
        this.datumVreme = datumVreme;
        this.zadataKombinacija = zadataKombinacija;
        this.brojPokusaja = brojPokusaja;
        this.rezultat = rezultat;
        this.igre = igre;
    }

    public RezultatIgre() {
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public String getZadataKombinacija() {
        return zadataKombinacija;
    }

    public void setZadataKombinacija(String zadataKombinacija) {
        this.zadataKombinacija = zadataKombinacija;
    }

    public int getBrojPokusaja() {
        return brojPokusaja;
    }

    public void setBrojPokusaja(int brojPokusaja) {
        this.brojPokusaja = brojPokusaja;
    }

    public String getRezultat() {
        return rezultat;
    }

    public void setRezultat(String rezultat) {
        this.rezultat = rezultat;
    }

    public ArrayList<Igra> getIgre() {
        return igre;
    }

    public void setIgre(ArrayList<Igra> igre) {
        this.igre = igre;
    }

    public long getRezultatIgreID() {
        return rezultatIgreID;
    }

    public void setRezultatIgreID(long rezultatIgreID) {
        this.rezultatIgreID = rezultatIgreID;
    }

}
