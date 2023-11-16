package up.mi.ssdjha.projet;

/**
 * Une Ville
 *
 **/
public class Ville {

	private String nom_ville;
	private boolean borne;

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
	 * Constructeur de Ville avec la solution naive
	 *
		 * @param nom_ville nom de la ville
		 * @param borne true si la ville possède une borne false sinon
	 **/
	public Ville( String nom_ville, boolean borne) {
		this.nom_ville = nom_ville;
		this.borne = borne;
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
}
