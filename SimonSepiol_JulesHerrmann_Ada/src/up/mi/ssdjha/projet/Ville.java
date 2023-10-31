package up.mi.ssdjha.projet;

public class Ville {

  	public int n; // Nombre de villes (26 max)
	public String nom_villes;
	//public String villes[];

	//J'ai mis les attibuts en public pour l'instant au cas où on fait de l'héritage
	
	public Ville(int n, String nom_villes) {
		this.n = n;
		this.nom_villes = nom_villes;
	}
	
	public int nombreVille(int e) {
		System.out.printf("Entrez le nombre de ville(s) que vous voulez");
		ajoutVille(e);
		return e;
	}
	
	public boolean ajoutVille(int m) {
		
		if(m>0 && m<26) {
			System.out.printf("Vous pouvez ajouter cette ville");
			return true;
		}
		
		else {
			do {
				System.out.printf("Entrer le nombre de ville(s) que vous voulez créer (max 26)");
			}while(m<26);
			
			return false;
			
		}		
		

}
