/**
 * @(#) Moniteur.java
 */
package ffssm;

import java.time.LocalDate;
import java.util.ArrayList;

public class DiplomeDeMoniteur{

    private final int numeroDiplome;
    private final Plongeur possesseur;
    private ArrayList<Embauche> list_embauche = new ArrayList<>();

    public DiplomeDeMoniteur(Plongeur possesseur, int numeroDiplome) {
        this.numeroDiplome = numeroDiplome;
        this.possesseur = possesseur;

    }

    /**
     * Si ce moniteur n'a pas d'embauche, ou si sa dernière embauche est terminée,
     * ce moniteur n'a pas d'employeur.
     * @return l'employeur actuel de ce moniteur ou null s'il n'en a pas
     */
    public Club employeurActuel() {
        if(list_embauche.isEmpty()){
            return null;
        }
        else{
            return list_embauche.get(list_embauche.size()-1).getClub();
        }
    }
    
    /**
     * Enregistrer une nouvelle embauche pour cet employeur
     * @param employeur le club employeur
     * @param debutNouvelle la date de début de l'embauche
     */
    public void nouvelleEmbauche(Club employeur, LocalDate debutNouvelle) { 
        Embauche newEmbauche = new Embauche (debutNouvelle, this, employeur);
        list_embauche.add (newEmbauche);
    }

    public ArrayList<Embauche> emplois() {
        return list_embauche;
    }

}
