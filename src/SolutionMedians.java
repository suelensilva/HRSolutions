import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class SolutionMedians {

    static void addElement(int element, PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers) {
        if(lowers.size() == 0 || element < lowers.peek()) {
            lowers.add(element);
        } else {
            highers.add(element);
        }
    }

    static void balancePriorityQueues(PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers) {
        PriorityQueue<Integer> smallest = lowers.size() > highers.size() ? highers : lowers;
        PriorityQueue<Integer> biggest = lowers.size() > highers.size() ? lowers : highers;

        if(biggest.size() - smallest.size() >= 2) {
            smallest.add(biggest.poll());
        }
    }

    static double getMedian(PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers) {
        if(lowers.size() != highers.size()) {
            PriorityQueue<Integer> biggest = lowers.size() < highers.size() ? highers : lowers;
            return biggest.peek();
        } else {
            double el1 = lowers.peek();
            double el2 = highers.peek();

            return (el1 + el2) / 2.0;
        }
    }

    static double[] runningMedian(int[] a) {

        double[] result = new double[a.length];

        PriorityQueue<Integer> lowers = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return  -1 * o1.compareTo(o2);
            }
        });
        PriorityQueue<Integer> highers = new PriorityQueue<>();

        for(int i = 0; i < a.length; i++) {
            int elem = a[i];
            addElement(elem, lowers, highers);
            balancePriorityQueues(lowers, highers);
            result[i] = getMedian(lowers, highers);
        }

        return result;
    }

    /*
     * Complete the runningMedian function below.
     */
    /*static double[] runningMedian(int[] a) {

        *//*
         * Write your code here.
         *//*
        double[] result = new double[a.length];

        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };

        ArrayList<Integer> sortedArray = new ArrayList<>();

        for(int i = 0; i < a.length; i++) {
            sortedArray.add(a[i]);
            sortedArray.sort(comparator);

            if(i % 2 == 1) {
                result[i] = (sortedArray.get(i/2) + sortedArray.get((i/2)+1)) / 2.0;
            } else {
                result[i] = sortedArray.get(i/2);
            }
        }

        return result;
    }*/

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int aCount = Integer.parseInt(scanner.nextLine().trim());

        int[] a = new int[aCount];

        for (int aItr = 0; aItr < aCount; aItr++) {
            int aItem = Integer.parseInt(scanner.nextLine().trim());
            a[aItr] = aItem;
        }

        double[] result = runningMedian(a);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            bufferedWriter.write(String.valueOf(result[resultItr]));
            //System.out.print(String.valueOf(result[resultItr]));

            if (resultItr != result.length - 1) {
                bufferedWriter.write("\n");
                //System.out.println();
            }
        }

        bufferedWriter.newLine();
        //System.out.println();

        bufferedWriter.close();
    }
}
