/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import baza.DBBroker;
import domen.Igra;
import domen.RezultatIgre;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ari
 */
public class Kontroler {

    private static Kontroler instance;
    private DBBroker dbb;
    private int[] dobitnaKombinacijaBrojeva = new int[4];
    private RezultatIgre rezultatIgre = new RezultatIgre(0, null,
            null, 0, "Izgubio", new ArrayList<>());
    private int rbIgre = 0;

    private Kontroler() {
        dbb = new DBBroker();
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public int[] getDobitnaKombinacijaBrojeva() {
        return dobitnaKombinacijaBrojeva;
    }

    public void setDobitnaKombinacijaBrojeva(int[] dobitnaKombinacijaBrojeva) {
        this.dobitnaKombinacijaBrojeva = dobitnaKombinacijaBrojeva;
    }

    public Igra vratiRezultat(int[] brojevi) {
        rezultatIgre.setBrojPokusaja(brojevi[4]);

        int pogodjeniNaMestu = 0;  
        int pogodjeniNisuNaMestu = 0;
        boolean[] nizPogodjenNaMjestu = new boolean[dobitnaKombinacijaBrojeva.length];
        boolean[] nizPogodjenNaPogresnomMjestu = new boolean[dobitnaKombinacijaBrojeva.length];

        for (int i = 0; i < dobitnaKombinacijaBrojeva.length; i++) {
            if (brojevi[i] == dobitnaKombinacijaBrojeva[i]) {
                pogodjeniNaMestu++;
                nizPogodjenNaMjestu[i] = true;
            }
        }

        for (int i = 0; i < dobitnaKombinacijaBrojeva.length; i++) {
            if (!nizPogodjenNaMjestu[i]) {
                for (int j = 0; j < dobitnaKombinacijaBrojeva.length; j++) {
                    if (dobitnaKombinacijaBrojeva[i] == brojevi[j] && !nizPogodjenNaMjestu[j]
                            && !nizPogodjenNaPogresnomMjestu[j]) {
                        pogodjeniNisuNaMestu++;
                        nizPogodjenNaPogresnomMjestu[j] = true;
                        break;
                    }
                }
            }
        }

        Igra ig = new Igra(null, ++rbIgre, brojevi[0] + "-" + brojevi[1] + "-" + brojevi[2] + "-" + brojevi[3],
                pogodjeniNaMestu, pogodjeniNisuNaMestu);
        rezultatIgre.getIgre().add(ig);

        if (ig.getPogodjenihNaMestu() == 4) {
            rezultatIgre.setRezultat("Pobedio");
        }

        return ig;
    }

    public boolean sacuvajRezultat() {
        try {

            if (rezultatIgre.getIgre().isEmpty()) {
                return false;
            }

            rezultatIgre.setZadataKombinacija(dobitnaKombinacijaBrojeva[0] + "-"
                    + dobitnaKombinacijaBrojeva[1]
                    + "-" + dobitnaKombinacijaBrojeva[2] + "-"
                    + dobitnaKombinacijaBrojeva[3]);

            return dbb.sacuvajRezultat(rezultatIgre);

        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<RezultatIgre> vratiRezultate() {
        return dbb.vratiRezultate();
    }

//    public Igra vratiRezultat(int[] brojevi) {
//
//        rezultatIgre.setBrojPokusaja(brojevi[4]); // ovde sam setovao broj pokusaja
//
//        int pogodjeniNaMestu = 0;
//        int pogodjeniNisuNaMestu = 0;
//
//        // OVO NADALJE JE NESREDJEN I NEDOVRSEN JOS UVEK, KAD BUDEM IMAO VREMENA, SMISLICU
//        // BOLJE RESENJE, ALI MOGUCE JE I DA GA NEMA, OVO NE BISTE RADILI NA ISPITU NAJVEROVATNIJE
//        
//        if (brojevi[0] == dobitnaKombinacijaBrojeva[0]) {
//            pogodjeniNaMestu++;
//            if (brojevi[0] == brojevi[1]) {
//                pogodjeniNisuNaMestu--;
//            }
//            if (brojevi[0] == brojevi[2]) {
//                pogodjeniNisuNaMestu--;
//            }
//            if (brojevi[0] == brojevi[3]) {
//                pogodjeniNisuNaMestu--;
//            }
//        } else {
//            if (brojevi[0] == dobitnaKombinacijaBrojeva[1]) {
//                pogodjeniNisuNaMestu++;
//            } else if (brojevi[0] == dobitnaKombinacijaBrojeva[2]) {
//                pogodjeniNisuNaMestu++;
//            } else if (brojevi[0] == dobitnaKombinacijaBrojeva[3]) {
//                pogodjeniNisuNaMestu++;
//            }
//        }
//
//        if (brojevi[1] == dobitnaKombinacijaBrojeva[1]) {
//            pogodjeniNaMestu++;
//            if (brojevi[1] == brojevi[0]) {
//                pogodjeniNisuNaMestu--;
//            }
//            if (brojevi[1] == brojevi[2]) {
//                pogodjeniNisuNaMestu--;
//            }
//            if (brojevi[1] == brojevi[3]) {
//                pogodjeniNisuNaMestu--;
//            }
//        } else {
//            if (brojevi[1] == dobitnaKombinacijaBrojeva[0]) {
//                pogodjeniNisuNaMestu++;
//            } else if (brojevi[1] == dobitnaKombinacijaBrojeva[2]) {
//                pogodjeniNisuNaMestu++;
//            } else if (brojevi[1] == dobitnaKombinacijaBrojeva[3]) {
//                pogodjeniNisuNaMestu++;
//            }
//        }
//
//        if (brojevi[2] == dobitnaKombinacijaBrojeva[2]) {
//            pogodjeniNaMestu++;
//            if (brojevi[2] == brojevi[0]) {
//                pogodjeniNisuNaMestu--;
//            }
//            if (brojevi[2] == brojevi[1]) {
//                pogodjeniNisuNaMestu--;
//            }
//            if (brojevi[2] == brojevi[3]) {
//                pogodjeniNisuNaMestu--;
//            }
//        } else {
//            if (brojevi[2] == dobitnaKombinacijaBrojeva[0]) {
//                pogodjeniNisuNaMestu++;
//            } else if (brojevi[2] == dobitnaKombinacijaBrojeva[1]) {
//                pogodjeniNisuNaMestu++;
//            } else if (brojevi[2] == dobitnaKombinacijaBrojeva[3]) {
//                pogodjeniNisuNaMestu++;
//            }
//        }
//
//        if (brojevi[3] == dobitnaKombinacijaBrojeva[3]) {
//            pogodjeniNaMestu++;
//            if (brojevi[3] == brojevi[0]) {
//                pogodjeniNisuNaMestu--;
//            }
//            if (brojevi[3] == brojevi[1]) {
//                pogodjeniNisuNaMestu--;
//            }
//            if (brojevi[3] == brojevi[2]) {
//                pogodjeniNisuNaMestu--;
//            }
//        } else {
//            if (brojevi[3] == dobitnaKombinacijaBrojeva[0]) {
//                pogodjeniNisuNaMestu++;
//            } else if (brojevi[3] == dobitnaKombinacijaBrojeva[1]) {
//                pogodjeniNisuNaMestu++;
//            } else if (brojevi[3] == dobitnaKombinacijaBrojeva[2]) {
//                pogodjeniNisuNaMestu++;
//            }
//        }
//
//        if (pogodjeniNaMestu == 4) {
//            rezultatIgre.setRezultat("Pobedio");
//        }
//
//        if (pogodjeniNisuNaMestu < 0) {
//            pogodjeniNisuNaMestu = 0;
//        }
//
//        Igra igra = new Igra(null, ++rbIgre,
//                brojevi[0] + "-" + brojevi[1] + "-" + brojevi[2] + "-" + brojevi[3],
//                pogodjeniNaMestu, pogodjeniNisuNaMestu);
//
//        rezultatIgre.getIgre().add(igra);
//
//        return igra;
//
//    }
}
