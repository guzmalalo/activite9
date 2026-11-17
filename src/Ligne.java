public class Ligne {
    private String numL;
    private Voie voieA;
    private Voie voieB;

    public Ligne(String numL) {
        this.numL = numL;

    }

    public void ajouterVoieA(Voie v) {
        voieA = v;
    }

    public void ajouterVoieB(Voie v) {
        voieB = v;
    }

    public String getNom() {
        return numL;
    }
}
