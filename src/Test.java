import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Test {
    private static final int NUM_REPEAT = 1;
    private static final int COL_SIZE = 10000000;

    private static void testCol(LinkedList list) {
        List<Integer> collection = new ArrayList<>();
        for(int i = 1; i < COL_SIZE; i++) {
            collection.add(-i);
        }

        Long startTime, endTime;
        startTime = System.nanoTime();
        for(int i = 0; i < NUM_REPEAT; i++) {
            list.addAll(0, collection);
        }
        endTime = System.nanoTime();
        System.out.println("LinkedList: " + (endTime - startTime)/NUM_REPEAT + " ns");

    }

    private static void testMyCol(MyLinkedList list) {
        List<Integer> collection = new ArrayList<>();
        for(int i = 1; i < COL_SIZE; i++) {
            collection.add(-i);
        }

        Long startTime, endTime;
        startTime = System.nanoTime();
        for(int i = 0; i < NUM_REPEAT; i++) {
            list.addAll(0, collection);
        }
        endTime = System.nanoTime();
        System.out.println("MyLinkedList: " + (endTime - startTime)/NUM_REPEAT + " ns");

    }

    public static void main(String[] args) {

        System.out.println("Тестирование:");
        System.out.println("1) LinkedList");
        System.out.println("2) MyLinkedList");
        Scanner s = new Scanner(System.in);
        int colIndex = 0;
        while(colIndex != -1) {
            colIndex = s.nextInt();

            LinkedList<Integer> linkedList = new LinkedList<>();
            MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
            switch(colIndex) {
                case 1:
                    testCol(linkedList);
                    break;
                case 2:
                    testMyCol(myLinkedList);
                    break;
            }
        }
    }
}
