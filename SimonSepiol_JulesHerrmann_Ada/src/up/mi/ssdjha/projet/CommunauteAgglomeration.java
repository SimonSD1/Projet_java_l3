package up.mi.ssdjha.projet;

import java.util.HashMap;
import java.util.Vector;
import java.util.Collection;
import java.util.HashSet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.StringBuilder;

import java.lang.IllegalArgumentException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Un graphe représentant des villes reliés par des routes
 *
 **/
public class CommunauteAgglomeration {

	private HashMap<Ville, Vector<Ville>> g;
	private HashMap<String, Ville> nameToVille;

	/**
	 * Constructeur de CommunauteAgglomeration
	 *
	 **/
	public CommunauteAgglomeration() {
		this.g = new HashMap<Ville, Vector<Ville>>();
		this.nameToVille = new HashMap<String, Ville>();
	}

	/**
	 * Constructeur de CommunauteAgglomeration
	 * 
	 * @param villes_col les villes de l'agglomération
	 **/
	public CommunauteAgglomeration(Collection<Ville> villes_col) {
		this.g = new HashMap<Ville, Vector<Ville>>();
		for (Ville ville : villes_col) {
			this.g.putIfAbsent(ville, new Vector<Ville>());
			this.nameToVille.putIfAbsent(ville.getNom(), ville);
		}
	}

	/**
	 * Ajoute une nouvelle ville sans route Ne fait rien si la ville existe déjà
	 * 
	 * @param ville la ville à ajouter
	 **/
	public void ajoutVille(Ville ville) {
		this.g.putIfAbsent(ville, new Vector<Ville>());
		this.nameToVille.putIfAbsent(ville.getNom(), ville);
	}

	/**
	 * Ajoute une nouvelle route entre deux villes
	 * 
	 * @param ville1 1ere ville
	 * @param ville2 1nde ville
	 * @throws VilleInexistanteException une des villes n'existe de pas dans
	 *                                   l'agglomération
	 **/
	public void ajoutRoute(Ville ville1, Ville ville2) throws VilleInexistanteException {
		Vector<Ville> voisinsVille1 = this.g.get(ville1);
		Vector<Ville> voisinsVille2 = this.g.get(ville2);
		if (voisinsVille1 == null) {
			throw new VilleInexistanteException("La ville 1 n'existe pas", ville1);
		}
		if (voisinsVille2 == null) {
			throw new VilleInexistanteException("La ville 2 n'existe pas", ville2);
		}
		if (!voisinsVille1.contains(ville2)) {
			voisinsVille1.add(ville2);
		}
		if (!voisinsVille2.contains(ville1)) {
			voisinsVille2.add(ville1);
		}
	}

	/**
	 * Ajoute une nouvelle route entre deux villes Crée de nouvelles villes si elles
	 * n'existent pas déjà
	 * 
	 * @param ville1 1ere ville
	 * @param ville2 1nde ville
	 **/
	public void ajoutRouteEtVilles(Ville ville1, Ville ville2) {
		Vector<Ville> voisinsVille1 = this.g.getOrDefault(ville1, new Vector<Ville>());
		this.nameToVille.putIfAbsent(ville1.getNom(), ville1);
		Vector<Ville> voisinsVille2 = this.g.getOrDefault(ville2, new Vector<Ville>());
		this.nameToVille.putIfAbsent(ville2.getNom(), ville2);

		if (!voisinsVille1.contains(ville2)) {
			voisinsVille1.add(ville2);
		}
		if (!voisinsVille2.contains(ville1)) {
			voisinsVille2.add(ville1);
		}
	}

	/**
	 * Supprime la route entre deux villes
	 * 
	 * @param Ville 1ere ville
	 * @param VIlle 1nde ville
	 * @throws VilleInexistanteException une des villes n'existe de pas dans
	 *                                   l'agglomération
	 **/
	public void SuppressionRoute(Ville ville1, Ville ville2) throws VilleInexistanteException {
		Vector<Ville> voisinsVille1 = this.g.get(ville1);
		Vector<Ville> voisinsVille2 = this.g.get(ville2);
		if (voisinsVille1 == null) {
			throw new VilleInexistanteException("La ville 1 n'existe pas", ville1);
		}
		if (voisinsVille2 == null) {
			throw new VilleInexistanteException("La ville 2 n'existe pas", ville2);
		}
		if (!voisinsVille1.contains(ville2)) {
			voisinsVille1.add(ville2);
		}
		if (!voisinsVille2.contains(ville1)) {
			voisinsVille2.add(ville1);
		}
	}

	/**
	 * Vérifie la contrainte d'accessibilité de l'agglomération *@return La liste de
	 * ville ne vérifiant pas la contrainte d'accessibilité
	 **/
	public Vector<Ville> getListeVilleRespectePasContrainteAccessibilite() {
		Vector<Ville> villeNonValide = new Vector<Ville>();

		for (Ville ville : this.g.keySet()) {
			if (!verifieContrainteAccessibilite(ville)) {
				villeNonValide.add(ville);
			}
		}
		return villeNonValide;
	}

	/**
	 * Vérifie la contrainte d'accessibilité de l'agglomération *@return true si la
	 * ville respecte la contrainte, false sinon
	 **/
	public boolean getRepecteContrainteAccessibilite() {

		for (Ville ville : this.g.keySet()) {
			if (!verifieContrainteAccessibilite(ville)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * permet de si une ville verifie la contrainte d'accessibilité
	 * 
	 * @param Ville la ville dont on veut verifier le respect de la contrainte
	 * @return un boolean vrai si elle respecte
	 **/
	public boolean verifieContrainteAccessibilite(Ville v) {
		if (v.getBorne()) {
			return true;
		}

		for (Ville voisin : this.g.get(v)) {
			if (voisin.getBorne()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return true si on peut enlever sa borne à la ville sans violer la contrainte
	 *         d'accessibilité, false sinon
	 **/
	public boolean peutEtreEnleve(Ville v) {
		for (Ville voisin : this.g.get(v)) {
			if (voisin.getBorne()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * permet de trouver les voisins qui ne respecte pas la contrainte si on enleve
	 * la ville
	 * 
	 * @param v la ville dont on veut trouver les voisins non valides
	 * @return un string contenant les nom des villes non valide
	 **/
	public String trouveVoisinNonValide(Ville v) {
		if (g.get(v).size() == 0) {
			return v.getNom();
		}

		StringBuilder sb = new StringBuilder();
		v.setBorne(false);
		for (Ville voisin : this.g.get(v)) {
			boolean respecte = false;

			if (!verifieContrainteAccessibilite(voisin)) {
				sb.append(voisin.getNom());
			}
		}
		v.setBorne(true);
		return sb.toString();
	}

	/**
	 * @return le nombre de villes dans la communauté d'agglomération
	 **/
	public int getNombreVilles() {
		return g.size();
	}

	/**
	 * @return villes la liste de toutes les villes de l'agglomération
	 **/
	public Vector<Ville> getVilles() {
		Vector<Ville> villes = new Vector<Ville>();
		for (Ville ville : this.g.keySet()) {
			villes.add(ville);
		}
		return villes;
	}

	/**
	 * @return true si ville1 et ville2 sont voisines, false sinon
	 **/
	public boolean estVoisin(Ville ville1, Ville ville2) {
		if (g.get(ville1).contains(ville2)) {
			return true;
		}
		return false;
	}

	/**
	 * permet d'afficher la liste des villes, leurs voisin et si elle a une borne
	 **/
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Etat de la communaute d'agglomeration : \n");
		for (Ville key : g.keySet()) {
			sb.append("\n Nom = ");
			sb.append(key.getNom());
			sb.append("; possede une borne = ");
			sb.append(key.getBorne());
			sb.append("; Voisin(s) = ");
			for (Ville v : g.get(key)) {
				sb.append(v.getNom());
				sb.append(" ");
			}
		}

		return sb.toString();
	}

	/**
	 * @param nomVille nom d'une ville
	 * @throws VilleInexistanteException si aucune ville ne porte se nom
	 * @return la ville portant ce nom
	 **/
	public Ville getVilleFromName(String nomVille) throws VilleInexistanteException {
		Ville ville = this.nameToVille.get(nomVille);
		if (ville == null) {
			throw new VilleInexistanteException("Aucune ville ne s'appelle " + nomVille);
		}
		return ville;
	}

	/**
	 * Charge une communauté d'agglomération d'un fichier
	 * 
	 * @param file_name addresse du fichier à charger
	 * @throws SyntaxErrorException la syntaxe du fichier est invalide
	 **/
	public static CommunauteAgglomeration createFromFile(String file_name)
			throws FileNotFoundException, SyntaxErrorException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(file_name));
		CommunauteAgglomeration c = new CommunauteAgglomeration();
		Pattern pattern_ville = Pattern.compile("ville\\(([^()\\s]*)\\).");
		Pattern pattern_route = Pattern.compile("route\\(([^()\\s]*),([^()\\s]*)\\).");
		Pattern pattern_recharge = Pattern.compile("recharge\\(([^()\\s]*)\\).");

		String line = null;
		int lineNb = 0;
		while ((line = br.readLine()) != null) {
			lineNb++;
			Matcher matcher_ville = pattern_ville.matcher(line);
			Matcher matcher_route = pattern_route.matcher(line);
			Matcher matcher_recharge = pattern_recharge.matcher(line);
			if (line.startsWith("#")) {
				continue;
			}
			if (matcher_ville.matches()) {
				c.ajoutVille(new Ville(matcher_ville.group(1), false));
			} else if (matcher_route.matches()) {
				try {
					c.ajoutRoute(c.getVilleFromName(matcher_route.group(1)),
							c.getVilleFromName(matcher_route.group(2)));
				} catch (VilleInexistanteException e) {
					throw new SyntaxErrorException(e.getMessage(), lineNb, line);
				}
			} else if (matcher_recharge.matches()) {
				try {
					Ville ville = c.getVilleFromName(matcher_recharge.group(1));
					ville.setBorne(true);
				} catch (VilleInexistanteException e) {
					throw new SyntaxErrorException(e.getMessage(), lineNb, line);
				}
			} else {
				throw new SyntaxErrorException("Ligne non reconnue dans le fichier", lineNb, line);
			}
		}
		return c;
	}

	/**
	 * enregistre la communauté d'agglomération dans un fichier
	 * 
	 * @param file_name chemin du fichier de sortie
	 * @throws IOException si il y a un problème pour écrire dans le fichier
	 **/
	public void saveToFile(String file_name) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(file_name));
		HashSet<UnorderedPair<Ville>> routesNonRedondance = new HashSet<UnorderedPair<Ville>>();
		HashSet<Ville> bornes = new HashSet<Ville>();
		StringBuilder sb = new StringBuilder();

		for (Ville ville : this.g.keySet()) {
			sb.setLength(0); // clear the string Builder;
			sb.append("ville(");
			sb.append(ville.getNom());
			sb.append(").\n");

			bw.write(sb.toString());

			if (ville.getBorne()) {
				bornes.add(ville);
			}

			for (Ville voisin : this.g.get(ville)) {
				routesNonRedondance.add(new UnorderedPair<Ville>(ville, voisin));
			}
		}
		for (UnorderedPair<Ville> pairVilleRoute : routesNonRedondance) {
			sb.setLength(0);
			sb.append("route(");
			sb.append(pairVilleRoute.getFirst().getNom());
			sb.append(",");
			sb.append(pairVilleRoute.getSecond().getNom());
			sb.append(").\n");
			bw.write(sb.toString());
		}
		for (Ville villeAvecRecharge : bornes) {
			sb.setLength(0);
			sb.append("recharge(");
			sb.append(villeAvecRecharge.getNom());
			sb.append(").\n");
			bw.write(sb.toString());
		}
		bw.close();
	}
	/**
	 * enregistre la communauté d'agglomération dans un fichier au format dot de GraphViz
	 * 
	 * @param file_name chemin du fichier de sortie
	 * @throws IOException si il y a un problème pour écrire dans le fichier
	 **/
	public void saveToDotFile(String file_name) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(file_name));
		HashSet<UnorderedPair<Ville>> routesNonRedondance = new HashSet<UnorderedPair<Ville>>();
		HashSet<Ville> bornes = new HashSet<Ville>();
		StringBuilder sb = new StringBuilder();

		bw.write("graph G {\n");


		for (Ville ville : this.g.keySet()) {
			sb.setLength(0); // clear the string Builder;
			sb.append(ville.getNom());
			sb.append(" [label=\"");
			sb.append(ville.getNom());
			if (ville.getBorne()) {
				sb.append("\\nBorne");
			}
			sb.append("\"]\n");

			bw.write(sb.toString());

			for (Ville voisin : this.g.get(ville)) {
				routesNonRedondance.add(new UnorderedPair<Ville>(ville, voisin));
			}
		}
		for (UnorderedPair<Ville> pairVilleRoute : routesNonRedondance) {
			sb.setLength(0);
			sb.append(pairVilleRoute.getFirst().getNom());
			sb.append(" -- ");
			sb.append(pairVilleRoute.getSecond().getNom());
			sb.append("\n");
			bw.write(sb.toString());
		}
		bw.write("}");
		bw.close();
	}
}
