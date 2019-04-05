import java.util.Collection;
import java.util.Iterator;

public class MyLinkedList<E> implements ILinkedList<E> {

    private int listSize = 0;
    private Node<E> first;
    private Node<E> last;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private boolean isCorrectIndex(int index) {
        if(index > -1 && index <= listSize)
            return true;
        return false;
    }

    public MyLinkedList() {
    }

    public MyLinkedList(Collection<? extends E> c) {
        this();
        addAll(c);
    }

    public boolean addAll(Collection<? extends E> c) { return addAll(listSize, c); }

    public boolean addAll(int index, Collection<? extends E> c) {
        if(!isCorrectIndex(index))
            throw new ArrayIndexOutOfBoundsException();

        Object[] array = c.toArray();
        int arraySize = array.length;
        if (arraySize == 0)
            return false;

        // поиск места вставки коллекции в связный список
        Node<E> startIndex, finalIndex;
        if (index == listSize) {
            finalIndex = null;
            startIndex = last;
        } else {
            finalIndex = findInsertLocation(index);
            startIndex = finalIndex.prev;
        }

        // построение связей между элементами коллекции
        // выносим проверку из цикла для повышения производительности
        for (int i = 1; i < arraySize; i++) {
            @SuppressWarnings("unchecked") E e = (E) array[i];
            Node<E> newNode = new Node<>(startIndex, e, null);
            if (startIndex == null)
                first = newNode;
            else
                startIndex.next = newNode;
            startIndex = newNode;
        }

        // связывание конца вставляемой коллекции со связным списком
        if (finalIndex == null) {
            last = startIndex;
        } else {
            startIndex.next = finalIndex;
            finalIndex.prev = startIndex;
        }

        listSize += arraySize;
        return true;
    }

    private Node<E> findInsertLocation(int index) {
        // assert isElementIndex(index);

        if (index < (listSize >> 1)) {
            Node<E> current = first;
            for (int i = 0; i < index; i++)
                current = current.next;
            return current;
        } else {
            Node<E> current = last;
            for (int i = listSize - 1; i > index; i--)
                current = current.prev;
            return current;
        }
    }

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
