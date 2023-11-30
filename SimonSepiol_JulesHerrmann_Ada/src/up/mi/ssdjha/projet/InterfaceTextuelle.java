package up.mi.ssdjha.projet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

public class InterfaceTextuelle {

	public static final String ALPHABET ="abcdefghijklmnopqrstuvwxyz";
	private static CommunauteAgglomeration commu;
	private static Scanner scan;
	
	
	/**
	 * Gere les differentes étapes de l'interface textuelle
	 **/
	public static void debuteInterface() {
		scan = new Scanner(System.in);
		commu = new CommunauteAgglomeration();
		int choix =0;
		do {
			System.out.println("Que voulez vous faire ?");
			System.out.println("1 : ajouter villes a la main");
			System.out.println("2 : lire un fichier");
			choix=scan.nextInt();

		}while(choix!=1 && choix!=2);
		
		switch(choix) {
		case 1 :
			int nbVilles=1;
			do {
				if(nbVilles<1) {
					System.out.println("nombre de villes trop petit");
				}
				System.out.println("Combien de villes y a-t-il ?");
				nbVilles = scan.nextInt();
				if(nbVilles>26){
					System.out.println("Le nombre de ville max. est 26, réessayez !");
				}
			}while(nbVilles<1 || nbVilles>26);
			
			
			
			
			ajouteVillesAlphabet(nbVilles);
			
			boucleCreationRoute();
			break;
			
		case 2:
			creeCommunauteParFichier(commu, scan);
		}
		
		
		
		boucleResolutionProbleme();
		
	}
	
	
	
	
	/**
	 * ajoute le nombre de ville demandé en les nommants d'apres l'alphabet
		 * @param CommunauteAgglomeration la communaute
		 * @param int nombre de ville a ajouter
	 **/
	public static void ajouteVillesAlphabet(int nbVilles) {
		for(int i=0; i< nbVilles; i++) {
			commu.ajoutVille(new Ville(( Character.toString(ALPHABET.charAt(i)) )));
		}
	}
	
	/**
	 * boucle de l'interface textuelle permettant de resoudre le probleme
		 * @param CommunauteAgglomeration la communaute
		 * @param int nombre de ville a ajouter
	 **/
	public static void boucleResolutionProbleme(){
		int choix=0;
		
		do {
			System.out.println(commu);
			Vector<Ville> villes = commu.getVilles();
			int indice=0;
			for(Ville v : villes) {
				System.out.println(v.getNom()+" "+indice);
				indice++;
			}
			
			
			do {
				System.out.println("Que voulez vous faire ?");
				System.out.println("1 : ajouter zone de recharge");
				System.out.println("2 : retirer zone de recharge");
				System.out.println("3 : resoudre par algorithme naif");
				System.out.println("4 : resoudre par algorithme moins naif");
				System.out.println("5 : enregistrer mon travail");

				System.out.println("6 : fin");
				choix=scan.nextInt();

			}while(choix<1 || choix >6);
			
			int numeroVille=0;
			Ville v;
			switch(choix) {
			
			case 1:
				
				
				
				do {
					if(numeroVille>indice || numeroVille<0) {
						System.out.println("ville inexistante");
					}
					System.out.println("quelle ville ?");
					numeroVille=scan.nextInt();
				}while(numeroVille>indice || numeroVille<0);
				
				
				v = commu.getVilles().get(numeroVille);
				if(v.getBorne()) {
					System.out.println("cette ville a déjà une borne");
				}
				else {
					v.setBorne(true);
				}
				break;
				
			case 2:
				
				do {
					if(numeroVille>indice || numeroVille<0) {
						System.out.println("ville inexistante");
					}
					System.out.println("quelle ville ?");
					numeroVille=scan.nextInt();
				}while(numeroVille>indice || numeroVille<0);
				
				
				v =commu.getVilles().get(numeroVille);
				
				if(v.getBorne()==false) {
					System.out.println("cette ville n'as pas de borne");
				}
				else if(commu.peutEtreEnleve(v)) {
					v.setBorne(false);
				}
				else {
					System.out.println("impossible a cause de "+commu.trouveVoisinNonValide(v));
				}
				break;
				
				
			case 3:
				Algorithme.resoudNaif(commu, 5);
				break;
				
			case 4:
				Algorithme.resoudMoinsNaif(commu, 5);
				break;

			case 5:
				saveCommunauteToFile();
				
			case 6:
				System.out.println(commu);

				scan.close();
				break;
			}
			
			
		}while(choix!=5);
	}
	
	/**
	 * boucle de l'interface textuelle permettant de creer les routes entres  les villes
		 * @param CommunauteAgglomeration la communaute
		 * @param int nombre de ville a ajouter
	 **/
	public static void boucleCreationRoute() {
		int choix=0;
		do {
			
			
			do {
				System.out.println("Que voulez vous faire ?");
				System.out.println("1 : ajouter une route, 2 : fin");
				choix = scan.nextInt();
				System.out.println("Entrez le chiffre proposée à droite :");

			}while(choix!=1 && choix!=2);
			
			switch(choix) {
			
			case 1:
				
				Vector<Ville> villes = commu.getVilles();
				int indice=0;
				for(Ville v : villes) {
					System.out.println(v.getNom()+" "+indice);
					indice++;
				}
				
				int ville1=0, ville2=0;
				
				do {
					if(ville1>indice || ville1<0) {
						System.out.println("ville inexistante");
					}
					System.out.println("ville de depart ?");
					ville1=scan.nextInt();
				}while(ville1>indice || ville1<0);
				
				boolean premiereDemande=true;
				do {
					if(ville2>indice || ville2<0 ) {
						System.out.println("ville inexistante");
					}
					if(premiereDemande==false && ville1==ville2) {
						System.out.println("ville identique a la premiere");
					}
					System.out.println("ville de d'arrive ?");

					ville2=scan.nextInt();
					premiereDemande=false;
				}while(ville2>indice || ville2<0 || ville2==ville1);
				
				
				
				commu.ajoutRoute(villes.get(ville1), villes.get(ville2));
				break;
				
			case 2:
				break;
			}
		}while (choix !=2);
	}
	/**
	 * demande le nom d'un fichier à l'utilisateur et enregistre la commu dans ce fichier
	 *
	 **/
	public static void saveCommunauteToFile() {
		System.out.println("Nom du fichier où enregistrer la communauté d'agglomération : ");
		String path = scan.nextLine();
		try {
			commu.saveToFile(path);
		}
		catch(IOException e){
			System.out.println("Erreur d'enregistrement : ");
			System.out.println(e.getMessage());
		}
	}
}


