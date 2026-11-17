import java.util.HashMap;
import java.util.LinkedList;

public class RechercheItineraire {
    public Itineraire rechercherItineraireBFS(Station stationDepart, Station stationArrivee) {
        Itineraire it = new Itineraire(stationDepart, stationArrivee);

        /*Pour mémoriser les stations découvertes
        On utilise une Map avec comme clé le nom de la station
        et comme valeur l'arc par laquelle a été atteinte.*/
        HashMap<String, TronconVoie> l_decouvertes = new HashMap<String, TronconVoie>();
        l_decouvertes.put(stationDepart.getNom(), null);

        /*La file des arcs (tronçons) en attente d'exploration
        sera implémentée par une liste chainée
        à double ancrage avec insertion en fin et suppression en tête*/
        LinkedList<TronconVoie> file = new LinkedList<TronconVoie>();

        /*Insérer dans la file les arcs sortants du sommet de départ*/
        for (TronconVoie a : stationDepart.getListeTroncons()) {
            file.addLast(a); // fifo
        }

        // BFS
        while (!file.isEmpty()) {
            TronconVoie a = file.getFirst(); // first in
            file.removeFirst(); // piocher la tête, premier arc à explorer

            /*si l'arrivée de l'arc n'a pas été découverte
            (elle n'est pas présente dans la Map)*/
            if (l_decouvertes.get(a.getArrivee().getNom()) == null) {
                //on l'ajoute dans la Map
                l_decouvertes.put(a.getArrivee().getNom(), a);

                //si c'est la destination finale, on arrête le parcours
                if (a.getArrivee().getNom().compareTo(stationArrivee.getNom()) == 0) {
                    break;
                }

                //sinon on ajoute tous les arcs sortants de l'arrivée dans la file d'attente
                for (TronconVoie succ : a.getArrivee().getListeTroncons()) {
                    file.addLast(succ); // fifo
                }
            }

        }

        // reconstitution du chemin trouvé
        LinkedList<TronconVoie> chemin = new LinkedList<TronconVoie>();

        // on rajoute le troncon par lequel la station d'arrivée a été decouverte.
        chemin.addFirst(l_decouvertes.get(stationArrivee.getNom()));

        // on recupere la station de depart de ce troncon
        String stationPrecedente = l_decouvertes.get(stationArrivee.getNom()).getDepart().getNom();

        while (stationPrecedente.compareTo(stationDepart.getNom()) != 0) {
            chemin.addFirst(l_decouvertes.get(stationPrecedente));
            stationPrecedente = l_decouvertes.get(stationPrecedente).getDepart().getNom();
        }

        it.setChemin(chemin);
        return it;
    }


    public Itineraire rechercherItineraireBFS_preferentiel(Station stationDepart, Station stationArrivee) {
        Itineraire it = new Itineraire(stationDepart, stationArrivee);
        /*Pour mémoriserles stations découvertes
        on utilise une Map avec comme clé le nom de la station
        et comme valeur l'arc parlequel elle a été atteinte*/
        HashMap<String, TronconVoie> l_decouvertes = new HashMap<String, TronconVoie>();
        l_decouvertes.put(stationDepart.getNom(), null);
        /*La file des arcs en attente d'exploration
        sera implémentée par une liste chainée
        à double ancrage avec insertion en fin et suppression en tête*/
        LinkedList<TronconVoie> file = new LinkedList<TronconVoie>();
        /*Insérer dans la file les arcs sortants du sommet de départ*/
        for (TronconVoie a : stationDepart.getListeTroncons()) {
            file.addLast(a);
        }
        while (!file.isEmpty()) {
            TronconVoie a = file.getFirst();
            file.removeFirst();
            /*si l'arrivée de l'arc n'a pas été découverte
            (elle n'est pas présente dans la Map)*/
            if (l_decouvertes.get(a.getArrivee().getNom()) == null) {
                //on l'ajoute dans la Map
                l_decouvertes.put(a.getArrivee().getNom(), a);
                //si c'est la destination finale, on arrête le parcours
                if (a.getArrivee().getNom().compareTo(stationArrivee.getNom()) == 0) {
                    break;
                }
                /*sinon on ordonne tous les arcs sortants de l'arrivée dans la file d'attente
                au debut : les tronçons ne necéssitant pas de changement
                à la fin : les tronçons necéssitant un changement*/
                LinkedList<TronconVoie> l_succ = new LinkedList<TronconVoie>();
                for (TronconVoie succ : a.getArrivee().getListeTroncons()) {
                    if (a.getNomLigne().compareTo(succ.getNomLigne()) == 0) l_succ.addFirst(succ);
                    else l_succ.addLast(succ);
                }
                //on ajoute les successeurs ordonnés dans la file
                for (TronconVoie succ : l_succ) {
                    file.addLast(succ);
                }
            }

        }
        //reconstitution du chemin trouvé
        LinkedList<TronconVoie> chemin = new LinkedList<TronconVoie>();
        chemin.addFirst(l_decouvertes.get(stationArrivee.getNom()));
        String stationPrecedente = l_decouvertes.get(stationArrivee.getNom()).getDepart().getNom();
        while (stationPrecedente.compareTo(stationDepart.getNom()) != 0) {
            chemin.addFirst(l_decouvertes.get(stationPrecedente));
            stationPrecedente = l_decouvertes.get(stationPrecedente).getDepart().getNom();
        }
        ;
        it.setChemin(chemin);
        return it;
    }

    public Itineraire rechercherItineraireBFS_DFS(Station stationDepart, Station stationArrivee) {
        Itineraire it = new Itineraire(stationDepart, stationArrivee);
        /*Pour mémoriserles stations découvertes
        on utilise une Map avec comme clé le nom de la station
        et comme valeur l'arc parlequel elle a été atteinte*/
        HashMap<String, TronconVoie> l_decouvertes = new HashMap<String, TronconVoie>();
        l_decouvertes.put(stationDepart.getNom(), null);
        /*La file des arcs en attente d'exploration
        sera implémentée par une liste chainée
        à double ancrage avec insertion en fin et suppression en tête*/
        LinkedList<TronconVoie> file = new LinkedList<TronconVoie>();
        /*Insérer dans la file les arcs sortants du sommet de départ*/
        for (TronconVoie a : stationDepart.getListeTroncons()) {
            file.addLast(a);
        }
        while (!file.isEmpty()) {
            TronconVoie a = file.getFirst();
            file.removeFirst();
            /*si l'arrivée de l'arc n'a pas été découverte
            (elle n'est pas présente dans la Map)*/
            if (l_decouvertes.get(a.getArrivee().getNom()) == null) {
                //on l'ajoute dans la Map
                l_decouvertes.put(a.getArrivee().getNom(), a);
                //si c'est la destination finale, on arrête le parcours
                if (a.getArrivee().getNom().compareTo(stationArrivee.getNom()) == 0) {
                    break;
                }
                /*sinon on ajoute les arcs sortants de l'arrivée dans la file d'attente
                en tête de file : les tronçons ne necéssitant pas de changement
                en fin de file : les tronçons necéssitant un changement*/
                for (TronconVoie succ : a.getArrivee().getListeTroncons()) {
                    if (a.getNomLigne().compareTo(succ.getNomLigne()) == 0) file.addFirst(succ);
                    else file.addLast(succ);
                }
            }

        }
        //reconstitution du chemin trouvé
        LinkedList<TronconVoie> chemin = new LinkedList<TronconVoie>();
        chemin.addFirst(l_decouvertes.get(stationArrivee.getNom()));
        String stationPrecedente = l_decouvertes.get(stationArrivee.getNom()).getDepart().getNom();
        while (stationPrecedente.compareTo(stationDepart.getNom()) != 0) {
            chemin.addFirst(l_decouvertes.get(stationPrecedente));
            stationPrecedente = l_decouvertes.get(stationPrecedente).getDepart().getNom();
        }
        ;
        it.setChemin(chemin);
        return it;
    }
}
