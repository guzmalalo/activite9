import java.util.ArrayList;

public class Station {
    private String nom;
    private ArrayList<TronconVoie> l_troncons; // liste d'adjacence

    public Station(String nom) {
        this.nom = nom;
        this.l_troncons = new ArrayList<TronconVoie>();
    }

    public void ajouterTroncon(TronconVoie arc) {
        l_troncons.add(arc);
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<TronconVoie> getListeTroncons() {
        return l_troncons;
    }

    public void afficher() {
        System.out.print(nom + " : ");
        for (TronconVoie t : l_troncons) {
            System.out.print(t.getNomLigne() + " " + t.getDestination() + " ; ");
        }
        System.out.println();
    }
}
