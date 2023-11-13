package up.mi.ssdjha.projet;

/**
 * Une Ville
 *
 **/
public class Ville {

	private String nom_ville;
	private boolean borne;

	//J'ai mis les attibuts en public pour l'instant au cas où on fait de l'héritage
	/**
	 * Constructeur de Ville avec la solution naive
	 *
		 * @param nom_ville nom de la ville
	 **/
	public Ville( String nom_ville) {
		this.nom_ville = nom_ville;
		this.borne = true;
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
	public boolean getBorne() {
		return this.borne;
	}
	/**
	 * ajoute ou enlève une borne à la ville
	 * @param boolean true pour donner une borne à la ville, false pour enlever la borne à la ville
	 **/
	public void setBorne(boolean borne) {
		this.borne = borne;
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
