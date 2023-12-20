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

	public T getFirst(){
		return first;
	}
	public T getSecond(){
		return second;
	}

	public boolean equals(Object o) {
		if (getClass() != o.getClass()) return false;
		UnorderedPair b = (UnorderedPair) o;
		return (this.first.equals(b.getFirst()) && this.second.equals(b.getSecond())) || (this.second.equals(b.getFirst()) && this.first.equals(b.getSecond()));
	}

	public int hashCode() {
		return this.first.hashCode() + this.second.hashCode();
	}
}
