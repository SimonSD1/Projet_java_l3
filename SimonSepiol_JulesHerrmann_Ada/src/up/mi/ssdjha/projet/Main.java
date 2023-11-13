package up.mi.ssdjha.projet;

import java.util.Vector;

import java.lang.Exception;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		System.out.println(args[0]);
		CommunauteAgglomeration a;
		try {
			a = CommunauteAgglomeration.createFromFile(args[0]);
			Vector<Ville> villesNonValide = a.getListeVilleRespectePasContrainteAccessibilite();
			if (villesNonValide.size() == 0){
				System.out.println("La ville vérifie la contrainte d'accessibilité !!!");
			}
			else {
				System.out.println("Le ville ne vérifie pas la contrainte d'accessibilité :(");
				System.out.println("Les villes non accessibles sont :");
				for (Ville ville : villesNonValide){
					System.out.println(ville.getNom());
				}
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}

}
