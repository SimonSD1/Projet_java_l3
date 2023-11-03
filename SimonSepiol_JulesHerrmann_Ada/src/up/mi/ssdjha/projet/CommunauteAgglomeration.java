package up.mi.ssdjha.projet;

import java.util.HashMap;
import java.util.Vector;

/**
 * Un graphe représentant des villes reliés par des routes
 *
 **/
public class CommunauteAgglomeration {

	private HashMap<Ville,Vector<Ville>> g;

	/**
	 * Constructeur de CommunauteAgglomeration
	 *
	 **/
	public CommunauteAgglomeration() {
		this.g = new HashMap<Ville,Vector<Ville>>();
	}
}
