package up.mi.ssdjha.projet;

import java.lang.Exception;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		System.out.println(args[0]);
		CommunauteAgglomeration a;
		try {
			a = CommunauteAgglomeration.createFromFile(args[0]);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}

}
