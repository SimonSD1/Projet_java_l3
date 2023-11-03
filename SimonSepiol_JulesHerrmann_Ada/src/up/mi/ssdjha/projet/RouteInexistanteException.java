package up.mi.ssdjha.projet;

public class RouteInexistanteException extends RuntimeException{
	private Ville ville1;
	private Ville ville2;

	public RouteInexistanteException(String message){
		super(message);
		this.ville1 = null;
		this.ville2 = null;
	}
	public RouteInexistanteException(String message,Ville ville1, Ville ville2){
		super(message);
		this.ville1 = ville1;
		this.ville2 = ville2;
	}
	public Ville getVille1(){
		return this.ville1;
	}
	public Ville getVille2(){
		return this.ville2;
	}
	public String getNomVille1(){
		if(this.ville1 != null){
			return this.ville1.getNom();
		}
		return "";
	}
	public String getNomVille2(){
		if(this.ville2 != null){
			return this.ville2.getNom();
		}
		return "";
	}
}
