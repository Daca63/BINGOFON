/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import domen.Igra;
import domen.RezultatIgre;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ari
 */
public class DBBroker {

    public ArrayList<Object> vrati() {
        ArrayList<Object> lista = new ArrayList<>();
        String upit = "";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public boolean cuvajIzmeniBrisi() throws Exception {
        String upit = "";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);

            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean sacuvajRezultat(RezultatIgre rezultatIgre) throws SQLException {
        String upit = "INSERT INTO REZULTATIGRE (DatumVreme, ZadataKombinacija, "
                + "BrojPokusaja, Rezultat) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection()
                    .prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);

            ps.setTimestamp(1, new Timestamp(new Date().getTime()));
            ps.setString(2, rezultatIgre.getZadataKombinacija());
            ps.setInt(3, rezultatIgre.getBrojPokusaja());
            ps.setString(4, rezultatIgre.getRezultat());

            ps.executeUpdate();

            ResultSet tableKeys = ps.getGeneratedKeys();
            tableKeys.next();
            long rezultatIgreID = tableKeys.getLong(1);

            rezultatIgre.setRezultatIgreID(rezultatIgreID);

            if (sacuvajIgre(rezultatIgre)) {
                Konekcija.getInstance().getConnection().commit();
                return true;
            } else {
                Konekcija.getInstance().getConnection().rollback();
                return false;
            }

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean sacuvajIgre(RezultatIgre rezultatIgre) throws SQLException {
        String upit = "INSERT INTO IGRA VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);

            for (Igra igra : rezultatIgre.getIgre()) {
                System.out.println(rezultatIgre.getRezultatIgreID() + "\t " + igra.getRb());
                ps.setLong(1, rezultatIgre.getRezultatIgreID());
                ps.setInt(2, igra.getRb());
                ps.setString(3, igra.getKombinacija());
                ps.setInt(4, igra.getPogodjenihNaMestu());
                ps.setInt(5, igra.getPogodjenihNisuNaMestu());

                ps.addBatch();
            }

            ps.executeBatch();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<RezultatIgre> vratiRezultate() {
        ArrayList<RezultatIgre> lista = new ArrayList<>();
        String upit = "SELECT * FROM REZULTATIGRE";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                RezultatIgre ri = new RezultatIgre(rs.getLong(1), rs.getTimestamp(2),
                        rs.getString(3), rs.getInt(4), rs.getString(5), null);
                ri.setIgre(vratiIgre(ri));
                lista.add(ri);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    private ArrayList<Igra> vratiIgre(RezultatIgre ri) {
        ArrayList<Igra> lista = new ArrayList<>();
        String upit = "SELECT * FROM IGRA WHERE IGRAID = " + ri.getRezultatIgreID();
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                Igra i = new Igra(ri, rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
                lista.add(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

}
