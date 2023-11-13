package up.mi.ssdjha.projet;

public class SyntaxErrorException extends RuntimeException{
	private boolean lineKnown;
	private int lineNumber;
	private String line;
	public SyntaxErrorException(String message,int lineNb, String line){
		super("Syntax error on line "+lineNb+" : "+line+"\n"+message);
		this.lineKnown = true;
		this.lineNumber = lineNb;
		this.line = line;
	}
	public SyntaxErrorException(String message){
		super(message);
		this.lineKnown = false;
		this.lineNumber = -1;
		this.line = null;
	}
	public boolean lineIsKnown(){
		return this.lineKnown;
	}
	public String getLine(){
		return this.line;
	}
	public int getLineNumber(){
		return this.lineNumber;
	}
}
