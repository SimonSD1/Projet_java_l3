package up.mi.ssdjha.projet;

import java.util.Set;
import java.util.HashSet;

/**
 * Classe pour contenir une paire non orienté de telle sorte que 
 * UnorderedPair(A,B).equals(UnorderedPair(B,A) = true
 * UnorderedPair(A,B).hashCode() == UnorderedPair(B,A).hashCode()
 *
 * ne marche par pour l'instant :/
 **/
public class UnorderedPair<T> {
	private T first;
	private T second;

	public UnorderedPair(T a, T b) {
		first = a;
		second = b;
	}

	/** Méthode qui permet de retourner avec
	 * @return la 1ère paire.**/
	
	public T getFirst(){
		return first;
	}

	/** Méthode qui permet de retourner avec
	 * @return la 2ème paire.**/
	
	public T getSecond(){
		return second;
	}

	/**Méthode qui
	 * @return true si la pair passer en paramètre est égale à la paire
	 * qu'on essaye de créer.**/
	
	public boolean equals(Object o) {
		if (getClass() != o.getClass()) return false;
		UnorderedPair b = (UnorderedPair) o;
		return (this.first.equals(b.getFirst()) && this.second.equals(b.getSecond())) || (this.second.equals(b.getFirst()) && this.first.equals(b.getSecond()));
	}

	
	/**Méthode qui permet de faire comprendre à la machine que, par exemple
	 *  "UnorderedPair(A,B).hashCode() == UnorderedPair(B,A).hashCode()"
	 *  
	 *   @return l'égalité entre les deux paires. 
	 *   
	 *   Si ils sont égaux, alors ils retourneront le même chiffre.
	 *   Si ils ne sont pas égaux, alors les objets sont différents. **/
	
	public int hashCode() {
		return this.first.hashCode() + this.second.hashCode();
	}
}
