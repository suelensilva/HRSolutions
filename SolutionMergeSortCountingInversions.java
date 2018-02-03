import java.util.Scanner;

/**
 * Solution for the 'Counting Inversions: Merge Sort' challenge
 * https://www.hackerrank.com/challenges/ctci-merge-sort/
 */
public class SolutionMergeSortCountingInversions {

    // Custom version of merge sort that return the total inversions
    // needed to sort the array
    private static long mergeSortCountingInversions(int[] array) {
        int[] helper = new int[array.length];
        return mergeSortCountingInversions(array, helper, 0, array.length-1);
    }

    private static long mergeSortCountingInversions(int[] array, int[] helper, int leftStart, int rightEnd) {
        long inversions = 0;
        if(leftStart < rightEnd) {
            int middle = (leftStart + rightEnd) / 2;
            inversions += mergeSortCountingInversions(array, helper, leftStart, middle);
            inversions += mergeSortCountingInversions(array, helper, middle +1, rightEnd);
            inversions += mergeCountingInversions(array, helper, leftStart, middle, rightEnd);
        }
        return inversions;
    }

    private static long mergeCountingInversions(int[] array, int[] helper, int leftStart, int middle, int rightEnd) {

        // The inversions will be calculated by the difference between the index
        // of the left half and their new position in the sorted array
        long inversions = 0;

        System.arraycopy(array, leftStart, helper, leftStart, rightEnd + 1 - leftStart);

        int helperLeft = leftStart;
        int helperRight = middle + 1;

        for(int i = leftStart; i <= rightEnd; i++) {
            if(helperLeft <= middle && helperRight <= rightEnd) {
                if (helper[helperLeft] <= helper[helperRight]) {
                    inversions += (i - helperLeft);
                    array[i] = helper[helperLeft];
                    helperLeft++;
                } else {
                    array[i] = helper[helperRight];
                    helperRight++;
                }
            } else if (helperLeft <= middle) {
                inversions += (i - helperLeft);
                array[i] = helper[helperLeft];
                helperLeft++;
            } else {
                array[i] = helper[helperRight];
                helperRight++;
            }
        }

        return inversions;
    }

    private static long countInversions(int[] arr) {
        return mergeSortCountingInversions(arr);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            int n = in.nextInt();
            int[] arr = new int[n];
            for (int arr_i = 0; arr_i < n; arr_i++) {
                arr[arr_i] = in.nextInt();
            }
            long result = countInversions(arr);
            System.out.println(result);
        }
        in.close();
    }
}
