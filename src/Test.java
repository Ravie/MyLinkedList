import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Long startTime, endTime;

        List<Integer> collection = new ArrayList<>();
        for(int i = 1; i < 10; i++) {
            collection.add(-i);
        }



        LinkedList<Integer> linkedList = new LinkedList<>();
        for(int i = 0; i < 10; i++) {
            linkedList.add(i);
        }
        startTime = System.nanoTime();
        linkedList.addAll(0, collection);
        endTime = System.nanoTime();
        System.out.println("LinkedList: " + (endTime - startTime) + " ns");
        System.out.println(linkedList.toString());

        startTime = System.nanoTime();
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        for(int i = 0; i < 10; i++) {
            myLinkedList.add(i);
        }
        startTime = System.nanoTime();
        myLinkedList.addAll(0, collection);
        endTime = System.nanoTime();
        System.out.println("MyLinkedList: " + (endTime - startTime) + " ns");
        System.out.println(myLinkedList.toString());



    }
}
