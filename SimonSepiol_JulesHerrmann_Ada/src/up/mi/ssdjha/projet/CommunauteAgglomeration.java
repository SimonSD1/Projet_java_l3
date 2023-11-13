package up.mi.ssdjha.projet;

import java.util.HashMap;
import java.util.Vector;
import java.util.Collection;

import java.lang.IllegalArgumentException;
import java.io.FileNotFoundException;

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
	/**
	 * Constructeur de CommunauteAgglomeration
		 * @param Collection<Ville> les villes de l'agglomération
	 **/
	public CommunauteAgglomeration(Collection<Ville> villes_col) {
		this.g = new HashMap<Ville,Vector<Ville>>();
		for (Ville ville : villes_col){
			this.g.putIfAbsent(ville,new Vector<Ville>());
		}
	}
	/**
	 * Ajoute une nouvelle ville sans route
	 * Ne fait rien si la ville existe déjà
		 * @param Ville la ville à ajouter
	 **/
	public void ajoutVille(Ville ville) {
		this.g.putIfAbsent(ville,new Vector<Ville>());
	}
	/**
	 * Ajoute une nouvelle route entre deux villes
		 * @param Ville 1ere ville
		 * @param VIlle 1nde ville 
		 * @throws VilleInexistanteException une des villes n'existe de pas dans l'agglomération
	 **/
	public void ajoutRoute(Ville ville1, Ville ville2) throws VilleInexistanteException{
		Vector<Ville> voisinsVille1 = this.g.get(ville1);
		Vector<Ville> voisinsVille2 = this.g.get(ville2);
		if(voisinsVille1 == null){
			throw new VilleInexistanteException("La ville 1 n'existe pas",ville1);
		}
		if(voisinsVille2 == null){
			throw new VilleInexistanteException("La ville 2 n'existe pas",ville2);
		}
		if(!voisinsVille1.contains(ville2)){
			voisinsVille1.add(ville2);
		}
		if(!voisinsVille2.contains(ville1)){
			voisinsVille2.add(ville1);
		}
	}
	/**
	 * Ajoute une nouvelle route entre deux villes
	 * Crée de nouvelles villes si elles n'existent pas déjà
		 * @param Ville 1ere ville
		 * @param VIlle 1nde ville 
	 **/
	public void ajoutRouteEtVilles(Ville ville1, Ville ville2) {
		Vector<Ville> voisinsVille1 = this.g.getOrDefault(ville1,new Vector<Ville>());
		Vector<Ville> voisinsVille2 = this.g.getOrDefault(ville2,new Vector<Ville>());

		if(!voisinsVille1.contains(ville2)){
			voisinsVille1.add(ville2);
		}
		if(!voisinsVille2.contains(ville1)){
			voisinsVille2.add(ville1);
		}
	}
	/**
	 * Supprime la route entre deux villes
		 * @param Ville 1ere ville
		 * @param VIlle 1nde ville 
		 * @throws VilleInexistanteException une des villes n'existe de pas dans l'agglomération
	 **/
	public void SuppressionRoute(Ville ville1, Ville ville2) throws VilleInexistanteException{
		Vector<Ville> voisinsVille1 = this.g.get(ville1);
		Vector<Ville> voisinsVille2 = this.g.get(ville2);
		if(voisinsVille1 == null){
			throw new VilleInexistanteException("La ville 1 n'existe pas",ville1);
		}
		if(voisinsVille2 == null){
			throw new VilleInexistanteException("La ville 2 n'existe pas",ville2);
		}
		if(!voisinsVille1.contains(ville2)){
			voisinsVille1.add(ville2);
		}
		if(!voisinsVille2.contains(ville1)){
			voisinsVille2.add(ville1);
		}
	}
	/**
	 * Vérifie la contrainte d'accessibilité des bornes
	 *	*@return List<Ville> La liste de ville ne vérifiant pas la contrainte d'accessibilité
	 **/
	public void verifieContrainteAccessibilite() {
		Vector<Ville> villeNonValide = new Vector<Ville>();

		for(Ville ville : this.g.keySet()){
			if (ville.possedeBorne()){
				continue;
			}
			for (Ville voisin : this.g.get(ville)){
				if (voisin.possedeBorne()){
					continue;
				}
			}
			villeNonValide.add(ville);
		}
	}
	/**
	 * @return Vector<Ville> la liste de toutes les villes de l'agglomération
	 **/
	public Vector<Ville> getVilles() {
		Vector<Ville> villes = new Vector<Ville>();
		for (Ville ville : this.g.keySet()){
			villes.add(ville);
		}
		return villes;
	}
		
	/**
	 * Short Description
	 *
	 * @throws 
	 **/
	//public static CommunauteAgglomeration createFromFile(String file_name)throws FileNotFoundException, SyntaxErrorException{
	//}
}
