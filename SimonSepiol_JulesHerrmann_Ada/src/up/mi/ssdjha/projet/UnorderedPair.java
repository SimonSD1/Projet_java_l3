package up.mi.ssdjha.projet;

import java.util.Set;
import java.util.HashSet;

/**
 * Classe pour contenir un couple non orienté de telle sorte que 
 * UnorderedPair(A,B).equals(UnorderedPair(B,A) = true
 * UnorderedPair(A,B).hashCode() == UnorderedPair(B,A).hashCode()
 *
 **/
public class UnorderedPair<T> {
	private T first;
	private T second;

	public UnorderedPair(T a, T b) {
		first = a;
		second = b;
	}

	/** Méthode qui permet de revoie avec
	 * @return le 1er élement du couple.**/
	
	public T getFirst(){
		return first;
	}

	/** Méthode qui permet de renvoie avec
	 * @return le 2ème élément du couple.**/
	
	public T getSecond(){
		return second;
	}

	/**Méthode qui
	 * @return true si le couple passé en paramètre est égale au couple**/
	
	public boolean equals(Object o) {
		if (getClass() != o.getClass()) return false;
		UnorderedPair b = (UnorderedPair) o;
		return (this.first.equals(b.getFirst()) && this.second.equals(b.getSecond())) || (this.second.equals(b.getFirst()) && this.first.equals(b.getSecond()));
	}

	
	/**Méthode qui permet de faire comprendre à la machine que, par exemple
	 *  "UnorderedPair(A,B).hashCode() == UnorderedPair(B,A).hashCode()"
	 *  
	 *   @return l'égalité entre les deux couples. 
	 *   
	 *   Si ils sont égaux, alors ils retourneront le même chiffre.
	 *   Si ils ne sont pas égaux, on en déduit que les couples sont différents. **/
	
	public int hashCode() {
		return this.first.hashCode() + this.second.hashCode();
	}
}
