import java.util.ArrayList;

public class Voie {
    private String nom;
    private ArrayList<TronconVoie> l_troncons;
    private Ligne l;

    public Voie(String nom, Ligne l) {
        this.nom = nom;
        this.l = l;
        l_troncons = new ArrayList<TronconVoie>();
    }

    public String getTerminus() {
        return l_troncons.getLast().getArrivee().getNom();
    }

    public String getNomLigne() {
        return l.getNom();
    }

    public Ligne getLigne() {
        return l;
    }

    public void ajouterTroncon(TronconVoie t) {
        l_troncons.add(t);
    }
}
