package up.mi.ssdjha.projet;

public class SyntaxErrorException extends RuntimeException{
	private boolean lineKnown;
	private int lineNumber;
	private String line;

	/**Constructeur de la classe SyntaxErrorException, avec initialisation
	 * des paramètres.**/
	
	public SyntaxErrorException(String message,int lineNb, String line){
		super("Syntax error on line "+lineNb+" : "+line+"\n"+message);
		this.lineKnown = true;
		this.lineNumber = lineNb;
		this.line = line;
	}

	/** Ici, on considère qu'il y a une erreur à l'avance, et
	 * le message affiché non le confimera. D'où le "false" de lineKnown,
	 * le "-1" de lineNumber, et le "null" de line.**/
	
	public SyntaxErrorException(String message){
		super(message);
		this.lineKnown = false;
		this.lineNumber = -1;
		this.line = null;
	}

	/**
	 * @return True si la ligne est connu.
	 **/
	
	public boolean lineIsKnown(){
		return this.lineKnown;
	}

	/**
	 * @return la ligne en chaîne de caractères.
	 **/
	
	public String getLine(){
		return this.line;
	}

	/**
	 * @return le numéro de ligne.
	 **/
	public int getLineNumber(){
		return this.lineNumber;
	}
}
