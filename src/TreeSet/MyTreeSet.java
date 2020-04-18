package TreeSet;

import java.util.Iterator;
import java.util.Set;

public interface MyTreeSet<E>{
    int size();
    boolean isEmpty();
    boolean contains(E o);
    Iterator<E> iterator();
    boolean insert(E e);
    boolean remove(E o);
    void printTree();
    E findMin();
    E findMax();
}
