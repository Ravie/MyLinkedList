import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class JCFTest {
    private static final int NUM_REPEAT = 10000;
    private static final int COEF_FOR_SET = 1;
    private static final int COEF_FOR_MAP = 1;

    private static void testList(AbstractList<String> abstractList, String logFile) {
        long startTime, endTime;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(logFile, true))) {
            System.out.println(abstractList.getClass() + " Тестирование производительности основных операций (в среднем на " + NUM_REPEAT + " прогонов)");
            bw.write(abstractList.getClass() + " Тестирование производительности основных операций (в среднем на " + NUM_REPEAT + " прогонов) ");
            bw.newLine();
            startTime = System.nanoTime();
            for (int i = 1; i < NUM_REPEAT; i++)
                abstractList.add(0, "TEST" + i);
            endTime = System.nanoTime();
            System.out.println("вставка начального элемента: " + (double) (endTime - startTime) / NUM_REPEAT + " нс");
            bw.write("вставка начального элемента: " + (double) (endTime - startTime) / NUM_REPEAT + " нс");
            bw.newLine();

            startTime = System.nanoTime();
            for (int i = 1; i < NUM_REPEAT; i++)
                abstractList.add(abstractList.size() / 2, "TEST" + i);
            endTime = System.nanoTime();
            System.out.println("вставка элемента в середину: " + (double) (endTime - startTime) / NUM_REPEAT + " нс");
            bw.write("вставка элемента из середины: " + (double) (endTime - startTime) / NUM_REPEAT + " нс");
            bw.newLine();

            startTime = System.nanoTime();
            for (int i = 1; i < NUM_REPEAT; i++)
                abstractList.add("TEST" + i);
            endTime = System.nanoTime();
            System.out.println("вставка конечного элемента: " + (double) (endTime - startTime) / NUM_REPEAT + " нс");
            bw.write("вставка конечного элемента: " + (double) (endTime - startTime) / NUM_REPEAT + " нс");
            bw.newLine();

            startTime = System.nanoTime();
            for (int i = 1; i < NUM_REPEAT; i++)
                abstractList.remove(0);
            endTime = System.nanoTime();
            System.out.println("удаление начального элемента: " + (double) (endTime - startTime) / NUM_REPEAT + " нс");
            bw.write("удаление начального элемента: " + (double) (endTime - startTime) / NUM_REPEAT + " нс");
            bw.newLine();

            startTime = System.nanoTime();
            for (int i = 1; i < NUM_REPEAT; i++)
                abstractList.remove(abstractList.size() / 2);
            endTime = System.nanoTime();
            System.out.println("удаление элемента из середины: " + (double) (endTime - startTime) / NUM_REPEAT + " нс");
            bw.write("удаление элемента из середины: " + (double) (endTime - startTime) / NUM_REPEAT + " нс");
            bw.newLine();

            startTime = System.nanoTime();
            for (int i = 1; i < NUM_REPEAT; i++)
                abstractList.remove(abstractList.size() - 1);
            endTime = System.nanoTime();
            System.out.println("удаление конечного элемента: " + (double) (endTime - startTime) / NUM_REPEAT + " нс");
            bw.write("удаление конечного элемента: " + (double) (endTime - startTime) / NUM_REPEAT + " нс");
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testSet(AbstractSet<String> abstractSet, String logFile) {
        long startTime, endTime;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(logFile, true))) {
            System.out.println(abstractSet.getClass() + " Тестирование производительности основных операций (в среднем на " + (NUM_REPEAT * COEF_FOR_SET) + " прогонов)");
            bw.write(abstractSet.getClass() + " Тестирование производительности основных операций (в среднем на " + (NUM_REPEAT * COEF_FOR_SET) + " прогонов) ");
            bw.newLine();

            Random rnd = new Random();
            startTime = System.nanoTime();
            for (int i = 1; i < (NUM_REPEAT * COEF_FOR_SET); i++)
                abstractSet.add((char)(rnd.nextInt(128)+128)+"_TEST_"+(char)(rnd.nextInt(128)+128));
            endTime = System.nanoTime();
            System.out.println("добавление элемента: " + (double) (endTime - startTime) / (NUM_REPEAT * COEF_FOR_SET) + " нс");
            bw.write("добавление элемента: " + (double) (endTime - startTime) / (NUM_REPEAT * COEF_FOR_SET) + " нс");
            bw.newLine();

            startTime = System.nanoTime();
            for (int i = 1; i < (NUM_REPEAT * COEF_FOR_SET); i++)
                abstractSet.contains((char)(rnd.nextInt(128)+128)+"_TEST_"+(char)(rnd.nextInt(128)+128));
            endTime = System.nanoTime();
            System.out.println("поиск элемента: " + (double) (endTime - startTime) / (NUM_REPEAT * COEF_FOR_SET) + " нс");
            bw.write("поиск элемента: " + (double) (endTime - startTime) / (NUM_REPEAT * COEF_FOR_SET) + " нс");
            bw.newLine();

            startTime = System.nanoTime();
            for (int i = 1; i < (NUM_REPEAT * COEF_FOR_SET); i++)
                abstractSet.remove((char)(rnd.nextInt(128)+128)+"_TEST_"+(char)(rnd.nextInt(128)+128));
            endTime = System.nanoTime();
            System.out.println("удаление элемента: " + (double) (endTime - startTime) / (NUM_REPEAT * COEF_FOR_SET) + " нс");
            bw.write("удаление элемента: " + (double) (endTime - startTime) / (NUM_REPEAT * COEF_FOR_SET) + " нс");
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testMap(AbstractMap<Integer, String> abstractMap, String logFile) {
        long startTime, endTime;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(logFile, true))) {
            System.out.println(abstractMap.getClass() + " Тестирование производительности основных операций (в среднем на " + (NUM_REPEAT * COEF_FOR_MAP) + " прогонов)");
            bw.write(abstractMap.getClass() + " Тестирование производительности основных операций (в среднем на " + (NUM_REPEAT * COEF_FOR_MAP) + " прогонов) ");
            bw.newLine();

            Random rnd = new Random();
            startTime = System.nanoTime();
            for (int i = 1; i < (NUM_REPEAT * COEF_FOR_MAP); i++) {
                String val = ((char)(rnd.nextInt(128)+128)+"_TEST_"+(char)(rnd.nextInt(128)+128));
                abstractMap.put(val.hashCode(), val);
            }
            endTime = System.nanoTime();
            System.out.println("добавление элемента: " + (double) (endTime - startTime) / (NUM_REPEAT * COEF_FOR_MAP) + " нс");
            bw.write("добавление элемента: " + (double) (endTime - startTime) / (NUM_REPEAT * COEF_FOR_MAP) + " нс");
            bw.newLine();

            startTime = System.nanoTime();
            for (int i = 1; i < (NUM_REPEAT * COEF_FOR_MAP); i++)
                abstractMap.get(((char)(rnd.nextInt(128)+128)+"_TEST_"+(char)(rnd.nextInt(128)+128)).hashCode());
            endTime = System.nanoTime();
            System.out.println("поиск элемента: " + (double) (endTime - startTime) / (NUM_REPEAT * COEF_FOR_MAP) + " нс");
            bw.write("поиск элемента: " + (double) (endTime - startTime) / (NUM_REPEAT * COEF_FOR_MAP) + " нс");
            bw.newLine();

            startTime = System.nanoTime();
            for (int i = 1; i < (NUM_REPEAT * COEF_FOR_MAP); i++) {
                String val = ((char)(rnd.nextInt(128)+128)+"_TEST_"+(char)(rnd.nextInt(128)+128));
                abstractMap.remove(val.hashCode(), val);
            }
            endTime = System.nanoTime();
            System.out.println("удаление элемента: " + (double) (endTime - startTime) / (NUM_REPEAT * COEF_FOR_MAP) + " нс");
            bw.write("удаление элемента: " + (double) (endTime - startTime) / (NUM_REPEAT * COEF_FOR_MAP) + " нс");
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String sourceFile = Paths.get(".").toAbsolutePath().normalize().toString()+"/Patch.txt";
        String currentLine;
        String delimiter = " ";
        AbstractList<String> words = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(sourceFile))) {
            while ((currentLine = br.readLine()) != null) {
                words.addAll(Arrays.asList(currentLine.split(delimiter)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String logFile = Paths.get(".").toAbsolutePath().normalize().toString()+"/Results.txt";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(logFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        testList(words,logFile);
        testList(new LinkedList<>(words),logFile);
        testSet(new HashSet<>(words), logFile);
        testSet(new LinkedHashSet<>(words), logFile);
        testSet(new TreeSet<>(words), logFile);

        Map<Integer, String> mapFromSet = new HashMap<>();
        for(int i=0;i<words.size();i++)
        {
            mapFromSet.put(words.get(i).hashCode(), words.get(i));
        }
        testMap(new HashMap<>(mapFromSet), logFile);
        testMap(new LinkedHashMap<>(mapFromSet), logFile);
        testMap(new TreeMap<>(mapFromSet), logFile);
    }
}
