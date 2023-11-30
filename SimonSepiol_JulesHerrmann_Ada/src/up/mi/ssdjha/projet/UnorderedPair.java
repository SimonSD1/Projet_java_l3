package up.mi.ssdjha.projet;

import java.util.Set;
import java.util.HashSet;

public class UnorderedPair<T> {
     private final Set<T> set;
     private final T first;
     private final T second;

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
