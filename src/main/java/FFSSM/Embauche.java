package ffssm;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

public class Embauche {
    @Getter
    private final LocalDate debut;
    @Getter @Setter
    private LocalDate fin;
    
    private Club employeur;
    @Getter @Setter
    private DiplomeDeMoniteur employe;

    public Embauche(LocalDate debut, DiplomeDeMoniteur employe, Club employeur) {
        this.debut = debut;
        this.employe=employe;
        this.employeur=employeur;
    }

    public Club getClub(){
        return employeur;
    }
    public void terminer(LocalDate dateFin){
        this.fin=dateFin;
    }

    public boolean estTerminer(){
        return fin!=null;
    }

    
}
