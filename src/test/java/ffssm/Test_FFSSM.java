package ffssm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Test_FFSSM {

    private Plongeur plongeur;
    private DiplomeDeMoniteur moniteur;
    private Club club;
    private Site site;

    @BeforeEach
    public void setUp() {
        plongeur = new Plongeur(2);
        moniteur = new DiplomeDeMoniteur(plongeur, 12345);
        club = new Club(moniteur, "Club Castres");
        club.setAdresse("Rue de la mer");
        club.setTelephone("0102030405");
        club.setList_plongees(new ArrayList<>());
        site = new Site("Atlantique", "Epave sous-marine");
    }

    @Test
    public void testLicenceValide() {
        Licence licence = new Licence(plongeur, "1", LocalDate.now().minusMonths(6), club);
        assertTrue(licence.estValide(LocalDate.now()), "Licence délivrée il y a 6 mois doit être valide");
    }

    @Test
    public void testLicenceExpiree() {
        Licence licence = new Licence(plongeur, "2", LocalDate.now().minusYears(2), club);
        assertFalse(licence.estValide(LocalDate.now()), "Licence délivrée il y a 2 ans doit être expirée");
    }

    @Test
    public void testPlongeeConforme() {
        Licence licence = new Licence(plongeur, "3", LocalDate.now().minusMonths(3), club);
        ArrayList<Licence> licences = new ArrayList<>();
        licences.add(licence);

        Plongee plongee = new Plongee(site, moniteur, LocalDate.now(), 20, 40, licences);
        assertTrue(plongee.estConforme(), "Plongée avec licence valide doit être conforme");
    }

    @Test
    public void testPlongeeNonConforme() {
        Licence licence = new Licence(plongeur, "4", LocalDate.now().minusYears(2), club);
        ArrayList<Licence> licences = new ArrayList<>();
        licences.add(licence);

        Plongee plongee = new Plongee(site, moniteur, LocalDate.now(), 20, 40, licences);
        assertFalse(plongee.estConforme(), "Plongée avec licence expirée doit être non conforme");
    }

    @Test
    public void testClubPlongeesNonConformes() {
        Licence licenceValide = new Licence(plongeur, "5", LocalDate.now().minusMonths(2), club);
        Licence licenceExpiree = new Licence(plongeur, "6", LocalDate.now().minusYears(2), club);

        Plongee plongeeConforme = new Plongee(site, moniteur, LocalDate.now(), 15, 30, new ArrayList<>());
        plongeeConforme.ajouteParticipant(licenceValide);

        Plongee plongeeNonConforme = new Plongee(site, moniteur, LocalDate.now(), 25, 50, new ArrayList<>());
        plongeeNonConforme.ajouteParticipant(licenceExpiree);

        club.organisePlongee(plongeeConforme);
        club.organisePlongee(plongeeNonConforme);

        Set<Plongee> nonConformes = club.plongeesNonConformes();
        assertEquals(1, nonConformes.size(), "Il doit y avoir une seule plongée non conforme");
        assertTrue(nonConformes.contains(plongeeNonConforme));
    }

    @Test
    public void testEmbaucheMoniteur() {
        LocalDate debut = LocalDate.of(2025, 1, 1);
        moniteur.nouvelleEmbauche(club, debut);

        assertEquals(club, moniteur.employeurActuel(), "Le moniteur doit avoir le club comme employeur actuel");
        assertEquals(1, moniteur.emplois().size(), "Le moniteur doit avoir une embauche enregistrée");

        Embauche embauche = moniteur.emplois().get(0);
        assertFalse(embauche.estTerminer(), "L'embauche ne doit pas être terminée");
        embauche.terminer(LocalDate.of(2025, 6, 1));
        assertTrue(embauche.estTerminer(), "L'embauche doit être terminée après appel à terminer()");
    }

    @Test
    public void testPlongeurDerniereLicence() {
        assertNull(plongeur.derniereLicence(), "Un plongeur sans licence doit retourner null");

        Licence licence = new Licence(plongeur, "8", LocalDate.now(), club);
        plongeur.liste_p_Licence.add(licence);

        assertEquals(licence, plongeur.derniereLicence(), "La dernière licence doit être celle ajoutée");
    }
}

