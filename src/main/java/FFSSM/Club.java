/**
 * @(#) Club.java
 */
package ffssm;

import java.util.ArrayList;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

public class Club {

    @Getter @Setter
    public DiplomeDeMoniteur president;

    @Getter @Setter
    public String nom;

    @Getter @Setter
    public String adresse;

    @Getter @Setter
    public String telephone;

    @Getter @Setter
    public ArrayList<Plongee> list_plongees;

    public Club(DiplomeDeMoniteur president, String nom) {
        this.president = president;
        this.nom = nom;
    }

    /**
     * Calcule l'ensemble des plongées non conformes organisées par ce club.
     * Une plongée est conforme si tous les plongeurs de la palanquée ont une licence
     * valide à la date de la plongée
     * @return l'ensemble des plongées non conformes
     */
    public Set<Plongee> plongeesNonConformes() {
        ArrayList<Plongee> plongees_noncoforme = new ArrayList<>();
        for (Plongee plong: list_plongees){
            if(!plong.estConforme()){
                plongees_noncoforme.add(plong);
            }
        }
        return Set.copyOf(plongees_noncoforme);
    }

    /**
     * Enregistre une nouvelle plongée organisée par ce club
     * @param p la nouvelle plongée
     */
    public void organisePlongee(Plongee p) {
        list_plongees.add(p);
    }


    @Override
    public String toString() {
        return "Club{" + "président=" + president + ", nom=" + nom + ", adresse=" + adresse + ", telephone=" + telephone + '}';
    }
}
