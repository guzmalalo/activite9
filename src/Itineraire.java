import java.util.LinkedList;

public class Itineraire {
    private Station depart;
    private Station destination;
    private LinkedList<TronconVoie> chemin;

    public Itineraire(Station depart, Station destination) {
        this.depart = depart;
        this.destination = destination;
    }

    public void setChemin(LinkedList<TronconVoie> c) {
        this.chemin = c;
    }

    public void afficher() {
        System.out.println(chemin.size() + " station(s)");
        int nc = 0;
        Ligne l = chemin.getFirst().getLigne();
        System.out.print(l.getNom() + " : ");
        for (TronconVoie a : chemin) {
            System.out.print(a.getDepart().getNom() + " - ");
            if (a.getLigne() != l) {
                System.out.println();
                System.out.println("Changement " + l.getNom() + " ---> " + a.getLigne().getNom());
                l = a.getLigne();
                System.out.print(l.getNom() + " : ");
                nc++;
            }
        }
        System.out.print(chemin.getLast().getArrivee().getNom());
        System.out.println();
        System.out.println(nc + " changement(s)");
    }
}
