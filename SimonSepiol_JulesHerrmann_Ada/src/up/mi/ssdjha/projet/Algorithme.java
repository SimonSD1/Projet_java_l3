package up.mi.ssdjha.projet;

public class Algorithme {
	
	public static void resoudNaif(CommunauteAgglomeration communaute, int k) {
		for(int i=0; i<k; i++) {
			int numeroVille = (int) (Math.random()*communaute.getVilles().size());
			Ville v = communaute.getVilles().get(numeroVille);
			if(v.getBorne() && communaute.peutEtreEnleve(v)) {
				v.setBorne(false);
			}
			else {
				v.setBorne(true);
			}
		}
	}
	
	private static int score(CommunauteAgglomeration communaute) {
		int nbSansBorne=0;
		for(Ville v : communaute.getVilles()) {
			if(v.getBorne()) {
				nbSansBorne++;
			}
		}
		
		return nbSansBorne;
	}
	
	public static void resoudMoinsNaif(CommunauteAgglomeration communaute, int k) {
		int i=0;
		int scoreCourant=score(communaute);
		while(i<k) {
			
			int numeroVille = (int) (Math.random()*communaute.getVilles().size());
			Ville v = communaute.getVilles().get(numeroVille);
			if(v.getBorne() && communaute.peutEtreEnleve(v)) {
				v.setBorne(false);
			}
			else {
				v.setBorne(true);
			}
			
			if(score(communaute)<scoreCourant) {
				i=0;
				scoreCourant=score(communaute);
			}
			else {
				i++;
			}
		}
	}
}
