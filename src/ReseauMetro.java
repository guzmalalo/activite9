import java.io.File;
import java.io.IOException;
import java.util.*;

public class ReseauMetro {
    /*les stations et les lignes sont stockées dans des ?
    clés = ? */
    private HashMap<String, Station> l_stations;
    private HashMap<String, Ligne> l_lignes;

    /* Méthode (constructeur) de chargement du graphe du métro */
    public ReseauMetro(String ficName) throws IOException {
        l_stations = new HashMap<String, Station>();
        l_lignes = new HashMap<String, Ligne>();

        //lecture du fichier des tronçons
        File fichier = new File(ficName);
        Scanner scanner = new Scanner(fichier);
        String ligne;
        String[] parties; // tokens
        ligne = scanner.nextLine();

        // nombre de lignes 16
        int nL = Integer.parseInt(ligne);

        //pour chaque ligne
        for (int i = 0; i < nL; i++) {
            String numL = scanner.nextLine(); // Mi
            Ligne l = new Ligne(numL); // Nouvelle ligne

            // pour chaque voie (A ou B donc 2)
            for (int j = 0; j < 2; j++) {
                ligne = scanner.nextLine();
                parties = ligne.split(";"); // tokens

                // Ex: [0] A  [1] 24
                String nomVoie = parties[0];
                int nT = Integer.parseInt(parties[1]);

                // creation de la voie
                Voie v = new Voie(nomVoie, l);

                //pour chaque tronçon
                for (int k = 0; k < nT; k++) {
                    ligne = scanner.nextLine();

                    // Ex: La Defense;Esplanade de La Defense
                    parties = ligne.split(";");

                    String nomArret1 = parties[0];
                    String nomArret2 = parties[1];

                    // rechercher ou créer la station de départ
                    Station s1 = l_stations.get(nomArret1);
                    if (s1 == null) {
                        // la station n'existe pas
                        s1 = new Station(nomArret1);
                        l_stations.put(nomArret1, s1);
                    }

                    // rechercher ou créer la station d'arrivée
                    Station s2 = l_stations.get(nomArret2);
                    if (s2 == null) {
                        s2 = new Station(nomArret2);
                        l_stations.put(nomArret2, s2);
                    }

                    // creation du tronçon (arc)
                    TronconVoie t = new TronconVoie(s1, s2, v);

                    // ajout du tronçon à la station de départ
                    // l'arc sort de s1, liste d'adjecence
                    s1.ajouterTroncon(t);

                    // ajout du tronçon à la voie
                    v.ajouterTroncon(t);
                }

                // ajout de la voie à la ligne
                if (j == 0) l.ajouterVoieA(v);
                if (j == 1) l.ajouterVoieB(v);

            }
        }
        scanner.close();
    }

    public Station rechercherStation(String nom) {
        return l_stations.get(nom);
    }

    public void afficherStations() {
        System.out.println("nb stations = " + l_stations.size());
        for (Station s : l_stations.values()) {
            s.afficher();
        }
    }


}
