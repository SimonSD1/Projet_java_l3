package up.mi.ssdjha.projet;

import java.util.Arrays;

public class Algorithme {
	/**
	 * resoud le probleme en utilisant l'algorithme naif proposé
	 **/
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
	
	/**
	 * calcul le score en comptant le nombre de ville sans borne
	 * @param une communaute d'agglomeration
	 **/
	private static int score(CommunauteAgglomeration communaute) {
		int nbSansBorne=0;
		for(Ville v : communaute.getVilles()) {
			if(v.getBorne()) {
				nbSansBorne++;
			}
		}
		
		return nbSansBorne;
	}
	
	/**
	 * resoud le probleme en utilisant l'algorithme moins naif proposé
	 **/
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
	
	public static int[] tri_degre(CommunauteAgglomeration communaute) {
        int[] degres = new int[communaute.getVilles().size() ];
        
        for (int i = 0; i <degres.length; i++) {
            for (int j = 0; j < degres.length; j++) {
            	Ville ville1 = communaute.getVilles().get(j);
            	Ville ville2 = communaute.getVilles().get(i);
                if (communaute.estVoisin(ville1, ville2)) {
                    degres[i]++;
                }
            }
        }

        Integer[] indices = new Integer[degres.length];
        for (int i = 0; i < degres.length; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (a,b) -> Integer.compare(degres[b],degres[a]));

        int[] trie = new int[degres.length];
        for (int i = 0; i < degres.length; i++) {
            trie[i] = indices[i];
        }

        return trie;

    }
	
	
	
	public static int min_couleur_possible(CommunauteAgglomeration communaute, int[] etiq, int s) {
        boolean[] valeuresPossibles = new boolean[communaute.getVilles().size()];
        Arrays.fill(valeuresPossibles, true);

        for (int i = 0; i < valeuresPossibles.length; i++) {
        	// si est voisin
        	Ville ville1 = communaute.getVilles().get(s);
        	Ville ville2 = communaute.getVilles().get(i);
            if (etiq[i] != -1 && communaute.estVoisin(ville1, ville2) ) {
                valeuresPossibles[etiq[i]] = false;
            }
        }

        for (int i = 0; i < valeuresPossibles.length; i++) {
            if (valeuresPossibles[i] == true) {
                return i;
            }
        }
        return valeuresPossibles.length;
    }
	
	public static int[] glouton(CommunauteAgglomeration communaute, int[] ordre) {
        int[] coloriage = new int[communaute.getVilles().size() ];
        Arrays.fill(coloriage, -1);

        for (int i = 0; i < ordre.length; i++) {
            int sommetAColorier = ordre[i];
            coloriage[sommetAColorier] = min_couleur_possible(communaute, coloriage, sommetAColorier);
        }
        return coloriage;
    }
	
	public static void welsh_powell(CommunauteAgglomeration communaute) {
        int[] ordre = tri_degre(communaute);

        int[] coloriage = glouton(communaute, ordre);
        
        for(int i=0; i<coloriage.length; i++) {
        	System.out.println(coloriage[i]);
        }
        
        int indice=0;
        for(Ville v : communaute.getVilles()) {
        	if(coloriage[indice]==1) {
        		v.setBorne(false);
        	}
        	else {
        		v.setBorne(true);
        	}
        	indice++;
        }
        
        
    }
}
