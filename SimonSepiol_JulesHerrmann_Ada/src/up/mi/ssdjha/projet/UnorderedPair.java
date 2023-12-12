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
	private Set<T> set;
	private T first;
	private T second;

	public UnorderedPair(T a, T b) {
		set = new HashSet<T>();
		set.add(a);
		set.add(b);
		first = a;
		second = b;
	}

	public T getFirst(){
		return first;
	}
	public T getSecond(){
		return second;
	}

	public boolean equals(Object b) {
		return set.equals(b);
	}

	public int hashCode() {
		return set.hashCode();
	}
}
