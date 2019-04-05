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

    private void isCorrectPosition(int index) {
        if(index < 0 || index > listSize)
            throw new ArrayIndexOutOfBoundsException();
    }

    private void isCorrectIndex(int index) {
        if(index < 0 || index >= listSize)
            throw new ArrayIndexOutOfBoundsException();
    }

    public MyLinkedList() {
    }

    public MyLinkedList(Collection<? extends E> c) {
        this();
        addAll(c);
    }

    public boolean addAll(Collection<? extends E> c) { return addAll(listSize, c); }

    public boolean addAll(int index, Collection<? extends E> c) {
        isCorrectPosition(index);

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
        @SuppressWarnings("unchecked") E e0 = (E) array[0];
        Node<E> newNode = new Node<>(startIndex, e0, null);
        if (startIndex == null)
            first = newNode;
        else
            startIndex.next = newNode;
        startIndex = newNode;
        for (int i = 1; i < arraySize; i++) {
            @SuppressWarnings("unchecked") E e = (E) array[i];
            newNode = new Node<>(startIndex, e, null);
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
        Node<E> tmp = last;
        Node<E> newNode = new Node<>(tmp, element, null);
        last = newNode;
        if (tmp == null)
            first = newNode;
        else
            tmp.next = newNode;
        listSize++;
    }

    @Override
    public void add(int index, E element) {
        isCorrectPosition(index);

        Node<E> tmp = findInsertLocation(index);
        Node<E> cur = tmp.prev;
        Node<E> newNode = new Node<>(cur, element, tmp);
        tmp.prev = newNode;

        if (cur == null)
            first = newNode;
        else
            cur.next = newNode;
        listSize++;
    }

    @Override
    public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        listSize = 0;
    }

    @Override
    public E get(int index) {
        isCorrectIndex(index);

        return null;
    }

    @Override
    public int indexOf(E element) {
        int index = 0;
        for (Node<E> curNode = first; curNode != null; curNode = curNode.next) {
            if (element.equals(curNode.item))
                return index;
            index++;
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        isCorrectIndex(index);

        listSize--;
        return null;
    }

    @Override
    public E set(int index, E element) {
        isCorrectIndex(index);

        return null;
    }

    @Override
    public int size() {
        return listSize;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < listSize)
            a = (T[])java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), listSize);
        int i = 0;
        Object[] result = a;
        for (Node<E> x = first; x != null; x = x.next)
            result[i++] = x.item;

        if (a.length > listSize)
            a[listSize] = null;

        return a;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
