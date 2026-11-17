public class TronconVoie {
    private Station depart;
    private Station arrivee;
    private Voie v;

    public TronconVoie(Station depart, Station arrivee, Voie v) {
        this.depart = depart;
        this.arrivee = arrivee;
        this.v = v;
    }

    public Station getDepart() {
        return depart;
    }

    public Station getArrivee() {
        return arrivee;
    }

    public String getNomLigne() {
        return v.getNomLigne();
    }

    public Ligne getLigne() {
        return v.getLigne();
    }

    public String getDestination() {
        return v.getTerminus();
    }

    public void afficher() {
        System.out.println(depart.getNom() + " " + arrivee.getNom() + " ligne " + v.getNomLigne() + " direction " + v.getTerminus());
    }
}
