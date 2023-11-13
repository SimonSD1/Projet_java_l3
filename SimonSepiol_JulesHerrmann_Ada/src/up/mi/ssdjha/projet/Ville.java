package up.mi.ssdjha.projet;

/**
 * Une Ville
 *
 **/
public class Ville {

	private String nom_ville;
	boolean borne;

	//J'ai mis les attibuts en public pour l'instant au cas où on fait de l'héritage
	/**
	 * Constructeur de Ville
	 *
		 * @param n numéro de la ville
		 * @param nom_ville nom de la ville
	 **/
	public Ville( String nom_ville) {
		this.nom_ville = nom_ville;
		this.borne = false;
	}
	/**
	 * @return le nom de la ville
	 **/
	public String getNom() {
		return this.nom_ville;
	}
	/**
	 * @return True si la ville possède une borne
	 **/
	public boolean possedeBorne() {
		return this.borne;
	}
	
	public int nombreVille(int e) {
		System.out.printf("Entrez le nombre de ville(s) que vous voulez");
		ajoutVille(e);
		return e;
	}
	
	public boolean ajoutVille(int m) {
		
		if(m>0 && m<26) {
			System.out.printf("Vous pouvez ajouter cette ville");
			return true;
		}
		
		else {
			do {
				System.out.printf("Entrer le nombre de ville(s) que vous voulez créer (max 26)");
			}while(m<26);
			
			return false;
			
		}
	}
}
