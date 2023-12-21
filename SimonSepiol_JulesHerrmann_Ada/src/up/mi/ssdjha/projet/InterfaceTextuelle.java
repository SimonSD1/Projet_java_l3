package up.mi.ssdjha.projet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

/**
 * Simple name,function pair for option menus
 **/
class Action {
	public String name;
	public Runnable function;

	public Action(String name, Runnable function){
		this.name = name;
		this.function = function;
	}
}

public class InterfaceTextuelle {

	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	private static CommunauteAgglomeration commu;
	private static Scanner scan;
	private static boolean utilisateurVeutContinuer;
	private static boolean utilisateurVeutContinuerCreationRoute;
	private static int nbVilles;


	/**
	 * Short Description
	 *
	 **/
	private static void printOptionsAndCall(Action[] actionArray) {
		int choix = 0;
		do {
			System.out.println("Que voulez vous faire ?");
			for (int i = 0; i<actionArray.length; i++){
				System.out.println(""+(i+1)+" : "+actionArray[i].name);
			}
			choix = scan.nextInt();

		} while (choix <=0 || choix > actionArray.length);
		actionArray[choix-1].function.run();
	}

	/**
	 * Gere les differentes étapes de l'interface textuelle
	 **/
	public static void debuteInterface() {
		scan = new Scanner(System.in);
		commu = new CommunauteAgglomeration();

		printOptionsAndCall(new Action[] {
			new Action("Ajouter villes à la main",
				()->{
					recupereNombreVilles();
					ajouteVillesAlphabet();
					boucleCreationRoute();
			}),
			new Action("Lire un fichier",
				()->{
					creeCommunauteParFichier();
			})});

		boucleResolutionProbleme();
	}
	public static void debuteInterface(String nomFichier) {
		scan = new Scanner(System.in);
		commu = new CommunauteAgglomeration();

		try {
			commu = CommunauteAgglomeration.createFromFile(nomFichier);
		} catch (SyntaxErrorException | IOException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		boucleResolutionProbleme();
	}

	public static void creeCommunauteParFichier() {
		boolean reussi = false;
		String nomFichier;

		do {
			System.out.println("nom du fichier ?");
			nomFichier = scan.next();
			try {
				commu = CommunauteAgglomeration.createFromFile(nomFichier);
				reussi = true;
			} catch (SyntaxErrorException | IOException e) {
				System.err.println(e.getMessage());
			}

		} while (reussi != true);
	}

	public static void recupereNombreVilles() {
		nbVilles = 1;
		do {
			if (nbVilles < 1) {
				System.out.println("nombre de villes trop petit");
			}
			System.out.println("Combien de villes y a-t-il ?");
			nbVilles = scan.nextInt();
			if (nbVilles > 26) {
				System.out.println("Le nombre de ville max. est 26, réessayez !");
			}
		} while (nbVilles < 1 || nbVilles > 26);

	}

	/**
	 * ajoute le nombre de ville demandé en les nommants d'apres l'alphabet
	 **/
	public static void ajouteVillesAlphabet() {
		for (int i = 0; i < nbVilles; i++) {
			commu.ajoutVille(new Ville((Character.toString(ALPHABET.charAt(i)))));
		}
	}

	/**
	 * boucle de l'interface textuelle permettant de resoudre le probleme
	 **/
	public static void boucleResolutionProbleme() {
		int choix = 0;
		utilisateurVeutContinuer = true;

		while (utilisateurVeutContinuer) {
			System.out.println(commu);

			printOptionsAndCall(new Action[] {
				new Action("Ajouter zone de recharge",
					()->{
						ajouteZoneDeRecharge();
				}),
				new Action("Retirer zone de recharge",
					()->{
						retirerZoneDeRecharge();
				}),
				new Action("Résoudre par algorithme naïf",
					()->{
						Algorithme.resoudNaif(commu, 2*commu.getNombreVilles());
				}),
				new Action("Résoudre par algorithme moins naïf",
					()->{
						Algorithme.resoudMoinsNaif(commu, 2*commu.getNombreVilles());
				}),
				new Action("Résoudre par Welsh et powell",
					()->{
						Algorithme.welsh_powell(commu);
				}),
				new Action("Enregistrer mon travail",
					()->{
						saveCommunauteToFile();
				}),
				new Action("Exporter .dot file de la communauté d'aggomérations",
					()->{
						saveCommunauteToDotFile();
				}),
				new Action("Terminer le programme",
					()->{
						cleanAndPreparetoEnd();
				})
			});

		} 
	}

	/**
	 * prépare la fermeture du programme : ferme les flux etc...
	 **/
	public static void cleanAndPreparetoEnd() {
		System.out.println(commu);
		scan.close();
		utilisateurVeutContinuer = false;
	}
	/**
	 * affiche toutes les villes avec un numéro et demande à l'utilisateur de choisir une ville
	 * @return la ville choisie par l'utilisateur
	 **/
	public static Ville choixVille() {
		Vector<Ville> villes = commu.getVilles();
		int indice = 0;
		System.out.println("Les villes sont identifiés par les nombres suivants :");
		for (Ville v : villes) {
			System.out.println(v.getNom() + " " + indice);
			indice++;
		}

		int numeroVille = 0;
		do {
			if (numeroVille > commu.getNombreVilles()-1 || numeroVille < 0) {
				System.out.println("Aucune ville n'a ce numéro");
			}
			System.out.println("Entrez le nombre correspondant à la ville de votre choix");
			try{
				numeroVille = scan.nextInt();
			}catch(InputMismatchException e){
				scan.nextLine(); // flush the scanner of the invalid value
				numeroVille = -1;
			}
		} while (numeroVille > commu.getNombreVilles()-1 || numeroVille < 0);

		return villes.get(numeroVille);
	}

	/**
	 * Interface textuelle demandant à l'utilisateur les infos pour retirer une zone de recharge
	 **/
	public static void retirerZoneDeRecharge() {
		Ville v = choixVille();

		if (v.getBorne() == false) {
			System.out.println("Cette ville n'as pas de borne");
		} else if (commu.peutEtreEnleve(v)) {
			v.setBorne(false);
		} else {
			System.out.println("Opération impossible. La ville suivante dépend de cette borne : " + commu.trouveVoisinNonValide(v));

		}
	}

	/**
	 * Interface textuelle demandant à l'utilisateur les infos pour ajouter une zone de recharge
	 **/
	public static void ajouteZoneDeRecharge() {
		Ville v = choixVille();

		if (v.getBorne()) {
			System.out.println("Cette ville a déjà une borne");
		} else {
			v.setBorne(true);
		}

	}
	/**
	 * Interface textuelle demandant à l'utilisateur les infos pour créer une route
	 **/
	public static void creerRoute() {
		boolean villesIdentiques = false;
		Ville ville1;
		Ville ville2;
		
		System.out.println("Choisissez la première ville :");
		ville1 = choixVille();
		do {
			if (villesIdentiques){
				System.out.println("Cette ville est identique à la première");
			}
			System.out.println("Choisissez la seconde ville :");
			ville2 = choixVille();
			villesIdentiques = (ville1 == ville2);
		} while(villesIdentiques);

		commu.ajoutRoute(ville1,ville2);
	}

	/**
	 * boucle de l'interface textuelle permettant de creer les routes entres les
	 * villes
	 * 
	 * @param CommunauteAgglomeration la communaute
	 * @param int                     nombre de ville a ajouter
	 **/
	public static void boucleCreationRoute() {
		utilisateurVeutContinuerCreationRoute = true;

		while (utilisateurVeutContinuerCreationRoute){
			System.out.println(commu);
			printOptionsAndCall(new Action[] {
				new Action("Ajouter une route",
					()->{
						creerRoute();
				}),
				new Action("Aller au menu principal",
					()->{
						utilisateurVeutContinuerCreationRoute = false;
				})
			});
		}
	}

	/**
	 * demande le nom d'un fichier à l'utilisateur et enregistre la commu dans ce
	 * fichier
	 *
	 **/
	public static void saveCommunauteToFile() {
		System.out.println("Nom du fichier où enregistrer la communauté d'agglomération : ");
		if (scan.hasNextLine()) {
			scan.nextLine();
		}
		String path = scan.nextLine();
		try {
			commu.saveToFile(path);
		} catch (IOException e) {
			System.out.println("Erreur d'enregistrement : ");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * demande le nom d'un fichier à l'utilisateur et enregistre la commu dans ce
	 * fichier au format dot de GraphViz
	 *
	 **/
	public static void saveCommunauteToDotFile() {
		System.out.println("Nom du fichier où enregistrer la communauté d'agglomération : ");
		if (scan.hasNextLine()) {
			scan.nextLine();
		}
		String path = scan.nextLine();
		try {
			commu.saveToDotFile(path);
		} catch (IOException e) {
			System.out.println("Erreur d'enregistrement : ");
			System.out.println(e.getMessage());
		}
	}
}
