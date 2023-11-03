package up.mi.ssdjha.projet;

public class VilleInexistanteException extends RuntimeException{
	private Ville ville;
	public VilleInexistanteException(String message,Ville ville){
		super(message);
		this.ville = ville;
	}
	public VilleInexistanteException(String message){
		super(message);
		this.ville = null;
	}
	public Ville getVilleInexistante(){
		return this.ville;
	}
	public String getNomVilleInexistante(){
		if(this.ville != null){
			return this.ville.getNom();
		}
		return "";
	}
}
