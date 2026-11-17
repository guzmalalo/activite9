import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // chargement du réseau
        ReseauMetro ratp=new ReseauMetro("troncons_voies.txt");

        // affichage
        System.out.println("Liste des stations :");
        //ratp.afficherStations();

        System.out.println();

        // recherche des itineraires
        // Stations
        Station placeItalie   = ratp.rechercherStation("Place d'Italie");
        Station bastille      = ratp.rechercherStation("Bastille");
        Station maisonBlanche = ratp.rechercherStation("Maison Blanche");
        Station jussieu       = ratp.rechercherStation("Jussieu");
        Station cambrone      = ratp.rechercherStation("Cambrone");

        // chercheur
        RechercheItineraire chercheur = new RechercheItineraire();

        // Place d'Italie -> Bastille
        System.out.println("Place d'Italie -> Bastille");
        Itineraire itItalieBastille = chercheur.rechercherItineraireBFS(placeItalie, bastille);
        itItalieBastille.afficher();
        System.out.println();

        // Maison Blanche -> Bastille
        System.out.println("Maison Blanche -> Bastille");
        Itineraire itBlancheBastille = chercheur.rechercherItineraireBFS(maisonBlanche, bastille);
        itBlancheBastille.afficher();
        System.out.println();

        // Maison Blanche -> Jussieu
        System.out.println("Maison Blanche -> Jussieu");
        Itineraire itBlancheJussieu = chercheur.rechercherItineraireBFS(maisonBlanche, jussieu);
        itBlancheJussieu.afficher();
        System.out.println();

        // Maison Blanche -> Cambrone
        System.out.println("Maison Blanche -> Cambrone");
        Itineraire itBlancheCambrone = chercheur.rechercherItineraireBFS(maisonBlanche, cambrone);
        itBlancheCambrone.afficher();
    }

}