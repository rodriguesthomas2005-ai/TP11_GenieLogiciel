/**
 * @(#) Plongee.java
 */
package ffssm;

import java.time.LocalDate;
import java.util.ArrayList;

public class Plongee {

	public Site lieu;

	public DiplomeDeMoniteur chefDePalanquee;

	public LocalDate date;

	public int profondeur;

	public int duree;

	public ArrayList<Licence> liste_licence;

	public Plongee(Site lieu, DiplomeDeMoniteur chefDePalanquee, LocalDate date, int profondeur, int duree) {
		this.lieu = lieu;
		this.chefDePalanquee = chefDePalanquee;
		this.date = date;
		this.profondeur = profondeur;
		this.duree = duree;
	}

	public Plongee(Site lieu, DiplomeDeMoniteur chefDePalanquee, LocalDate date, int profondeur, int duree, ArrayList<Licence> liste_licence) {
		this.lieu = lieu;
		this.chefDePalanquee = chefDePalanquee;
		this.date = date;
		this.profondeur = profondeur;
		this.duree = duree;
		this.liste_licence=liste_licence;
	}

	public void ajouteParticipant(Licence l) {
		liste_licence.add(l);
	}

	/**
	 * Détermine si la plongée est conforme. 
	 * Une plongée est conforme si tous les plongeurs de la palanquée ont une
	 * licence valide à la date de la plongée
	 * @return vrai si la plongée est conforme
	 */
	public boolean estConforme() {
		for (Licence l: liste_licence){
			if (!l.estValide(date)){
				return false;
			}
		}
		return true;
	}

}
