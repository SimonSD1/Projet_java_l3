package up.mi.ssdjha.projet;

import java.util.Vector;

import java.lang.Exception;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void printHelp(){
		System.out.println("Main.java [fileName | -h]\nSi fileName est donné, ouvre le fichier.\nSinon le programme demande à l'utilisateur ce qu'il veut faire");
	}

	public static void main(String[] args) {

		if (args.length == 0){
			InterfaceTextuelle.debuteInterface();
		}
		else if (args.length == 1){
			if (args[0].equals("-h")){
				printHelp();
			}
			else {
				InterfaceTextuelle.debuteInterface(args[0]);
			}
		}
		else {
			printHelp();
		}

	}

}
