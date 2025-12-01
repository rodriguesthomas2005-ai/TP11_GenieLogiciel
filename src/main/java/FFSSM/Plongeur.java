package ffssm;

import java.time.LocalDate;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

public class Plongeur {
    @Getter @Setter
	public int niveau;

    public ArrayList<Licence> liste_p_Licence = new ArrayList<>();

    public Plongeur(int niveau) {
        this.niveau = niveau;
    }

    //methodes
    public void ajouteLicence(String numero, LocalDate delivrance, Club club){
        Licence newLicence = new Licence(this, numero, delivrance, club);
    }

    public Licence derniereLicence(){
        if (liste_p_Licence.isEmpty()){
            return null;
        }
        return liste_p_Licence.get(liste_p_Licence.size()-1);
    }




    
}
