package up.mi.ssdjha.projet;

public class RouteInexistanteException extends RuntimeException{
	private Ville ville1;
	private Ville ville2;

	/** Contructeur de route avec un message demandant si une route existe déjà 
	 * entre deux villes.**/

	public RouteInexistanteException(String message){
		super(message);
		this.ville1 = null;
		this.ville2 = null;
	}

	/** Contructeur de route entre deux villes déjà existante,
	 * avec un message demandant la confimation de cette création.**/
	
	public RouteInexistanteException(String message,Ville ville1, Ville ville2){
		super(message);
		this.ville1 = ville1;
		this.ville2 = ville2;
	}

	/**Méthode permettant d'appelé la 1ère ville.
	 * @return la ville 1**/
	
	public Ville getVille1(){
		return this.ville1;
	}

	/**Méthode permettant d'appelé la 2ème ville.
	 * @return la ville 2**/
	
	public Ville getVille2(){
		return this.ville2;
	}

	
	/**Méthode qui permet d'obtenir le nom de la 1ère ville.
	 * @return le nom de la ville 1**/
	
	public String getNomVille1(){
		if(this.ville1 != null){
			return this.ville1.getNom();
		}
		return "";
	}

	/**Méthode qui permet d'obtenir le nom de la 1ère ville.
	 * @return le nom de la ville 2**/
	
	public String getNomVille2(){
		if(this.ville2 != null){
			return this.ville2.getNom();
		}
		return "";
	}
}
