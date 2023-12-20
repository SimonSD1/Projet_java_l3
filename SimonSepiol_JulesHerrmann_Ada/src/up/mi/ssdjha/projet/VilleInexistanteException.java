package up.mi.ssdjha.projet;

public class VilleInexistanteException extends RuntimeException{
	private Ville ville;

	/** Constructeur pour ville qu'on entre en paramètre. **/
	public VilleInexistanteException(String message,Ville ville){
		super(message);
		this.ville = ville;
	}
	
	/** Constructeur pour ville non existante. **/
	public VilleInexistanteException(String message){
		super(message);
		this.ville = null;
	}

	/**Méthode qui sert à appelé la ville maintenant existante.
	 * 
	 * @return la ville.
	 **/
	
	public Ville getVilleInexistante(){
		return this.ville;
	}

	/**Méthode qui sert à précisé le nom la ville 
	 * 
	 *@return le nom de la ville. **/
	
	public String getNomVilleInexistante(){
		if(this.ville != null){
			return this.ville.getNom();
		}
		return "";
	}
}
