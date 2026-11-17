import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // chargement du réseau
        ReseauMetro ratp=new ReseauMetro("troncons_voies.txt");

        // affichage
        System.out.println("Liste des stations :");
        ratp.afficherStations();

        System.out.println();

    }
}