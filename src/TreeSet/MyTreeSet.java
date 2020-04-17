package TreeSet;

import java.util.Iterator;

public interface MyTreeSet<E>{
    int size();
    boolean isEmpty();
    boolean contains(Object o);
    Iterator<E> iterator();
    boolean insert(E e);
    boolean remove(Object o);
    void printTree();
}
