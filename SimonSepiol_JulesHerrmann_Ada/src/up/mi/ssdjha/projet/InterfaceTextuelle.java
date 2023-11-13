package up.mi.ssdjha.projet;

import java.util.Scanner;

public class InterfaceTextuelle {

	public static final String ALPHABET ="abcdefghijklmnopqrstuvwxyz";
	
	public static void debuteInterface() {
	
		Scanner scan = new Scanner(System.in);
		System.out.println("Combien de villes y a-t-il ?");
		
		int nbVilles = scan.nextInt();
		
		CommunauteAgglomeration commu = new CommunauteAgglomeration();
		
		ajouteVillesAlphabet(commu,nbVilles);
		
		boucleInterface(scan, commu);
		
	}
	
	
	public static void ajouteVillesAlphabet(CommunauteAgglomeration commu, int nbVilles) {
		for(int i=0; i< nbVilles; i++) {
			commu.ajoutVille(new Ville(( Character.toString(ALPHABET.charAt(i)) )));
		}
	}
	
	public static void boucleInterface(Scanner scan, CommunauteAgglomeration commu) {
		int choix=0;
		do {
			System.out.println("Que voulez vous faire ?");
			System.out.println("1 : ajouter une route, 2 : fin");
			
			choix = scan.nextInt();
			
			switch(choix) {
			case 1:
				
				String ville1, ville2;
				System.out.println("ville de depart ?");
				ville1=scan.next();
				System.out.println("ville d'arrive ?");
				ville2=scan.next();
				
				System.out.println("ajoute ville "+ville1+" "+ville2);
				
				break;
				
			case 2:
				scan.close();
				break;
			}
		}while (choix !=2);
		
		
	}
}


