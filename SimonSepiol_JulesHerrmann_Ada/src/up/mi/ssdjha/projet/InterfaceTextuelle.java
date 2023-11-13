package up.mi.ssdjha.projet;

import java.util.Scanner;
import java.util.Vector;

public class InterfaceTextuelle {

	public static final String ALPHABET ="abcdefghijklmnopqrstuvwxyz";
	
	public static void debuteInterface() {
	
		Scanner scan = new Scanner(System.in);
		System.out.println("Combien de villes y a-t-il ?");
		
		int nbVilles = scan.nextInt();
		
		CommunauteAgglomeration commu = new CommunauteAgglomeration();
		
		ajouteVillesAlphabet(commu,nbVilles);
		
		boucleCreationRoute(scan, commu);
		
		boucleResolutionProbleme(commu, scan);
		
	}
	
	
	public static void ajouteVillesAlphabet(CommunauteAgglomeration commu, int nbVilles) {
		for(int i=0; i< nbVilles; i++) {
			commu.ajoutVille(new Ville(( Character.toString(ALPHABET.charAt(i)) )));
		}
	}
	
	public static void boucleResolutionProbleme(CommunauteAgglomeration commu , Scanner scan){
		int choix=0;
		
		do {
			System.out.println(commu);
			Vector<Ville> villes = commu.getVilles();
			int indice=0;
			for(Ville v : villes) {
				System.out.println(v.getNom()+" "+indice);
				indice++;
			}
			System.out.println("Que voulez vous faire ?");
			System.out.println("1 : ajouter zone de recharge");
			System.out.println("2 : retirer zone de recharge");
			System.out.println("3 : fin");
			
			choix=scan.nextInt();
			
			int numeroVille;
			Ville v;
			switch(choix) {
			
			case 1:
				System.out.println("quelle ville ?");
				numeroVille=scan.nextInt();
				v = commu.getVilles().get(numeroVille);
				if(v.getBorne()) {
					System.out.println("cette ville a deja une borne");
				}
				else {
					v.setBorne(true);
				}
				break;
				
			case 2:
				System.out.println("quelle ville ?");
				numeroVille = scan.nextInt();
				v =commu.getVilles().get(numeroVille);
				
				if(v.getBorne()==false) {
					System.out.println("cette ville n'as pas de borne");
				}
				else if(commu.verifieContrainteAccessibilite(v)) {
					v.setBorne(false);
				}
				else {
					System.out.println("impossible car "+commu.trouveVoisinNonValide(v) +" ne respecteront pas la contrainte");
				}
				break;
				
			case 3:
				System.out.println(commu);

				scan.close();
			}
			
			
		}while(choix!=3);
	}
	
	public static void boucleCreationRoute(Scanner scan, CommunauteAgglomeration commu) {
		int choix=0;
		do {
			System.out.println("Que voulez vous faire ?");
			System.out.println("1 : ajouter une route, 2 : fin");
			
			choix = scan.nextInt();
			
			switch(choix) {
			case 1:
				
				Vector<Ville> villes = commu.getVilles();
				int indice=0;
				for(Ville v : villes) {
					System.out.println(v.getNom()+" "+indice);
					indice++;
				}
				
				int ville1, ville2;
				System.out.println("ville de depart ?");
				ville1=scan.nextInt();
				System.out.println("ville d'arrive ?");
				ville2=scan.nextInt();
				
				
				
				commu.ajoutRoute(villes.get(ville1), villes.get(ville2));
				break;
				
			case 2:
				break;
			}
		}while (choix !=2);
	}
}


