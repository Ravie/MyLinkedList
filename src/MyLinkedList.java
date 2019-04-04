import java.util.Collection;
import java.util.Iterator;

public class MyLinkedList<E> implements ILinkedList<E> {

    private int listSize = 0;

    @Override
    public void add(E element) {
        add(listSize, element);
        listSize++;
    }

    @Override
    public void add(int index, E element) {

        listSize++;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {

        return null;
    }

    @Override
    public int indexOf(E element) {
        return 0;
    }

    @Override
    public E remove(int index) {

        listSize--;
        return null;
    }

    @Override
    public E set(int index, E element) {

        return null;
    }

    @Override
    public int size() {
        return listSize;
    }

    @Override
    public E[] toArray() {
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
