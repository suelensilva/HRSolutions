import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SolutionSwapNodes {

    class BinaryTree {
        int i;
        BinaryTree left;
        BinaryTree right;
    }

    private BinaryTree binaryTree;

    void buildBinaryTree(int[][] indexes) {

        BinaryTree[] binaryTrees = new BinaryTree[indexes.length+1];
        binaryTree = new BinaryTree();
        binaryTree.i = 1;

        binaryTrees[0] = binaryTree;

        for(int i = 0; i < indexes.length; i++) {

            BinaryTree left = new BinaryTree();
            BinaryTree right = new BinaryTree();

            left.i = indexes[i][0];
            right.i = indexes[i][1];

            BinaryTree parent = binaryTrees[i];
            parent.left = left;
            parent.right = right;

            if(left.i > 0)
                binaryTrees[left.i-1] = left;

            if(right.i > 0)
                binaryTrees[right.i-1] = right;
        }
    }

    void swapLevel(BinaryTree binaryTree, int currentLevel, int k) {
        if(binaryTree.i != -1) {
            swapLevel(binaryTree.left, currentLevel+1, k);
            swapLevel(binaryTree.right, currentLevel+1, k);
            if(currentLevel % k == 0) {
                //System.out.println("level "+currentLevel+" swapping node "+binaryTree.i+" > "+binaryTree.left.i+" "+binaryTree.right.i);
                BinaryTree aux = binaryTree.left;
                binaryTree.left = binaryTree.right;
                binaryTree.right = aux;
            }

        }
    }

    void swapLevel(int k) {
        swapLevel(binaryTree, 1, k);
        //System.out.println("=====");
    }

    int idx;
    int[] result;

    void visitInOrderTraversal(BinaryTree tree) {
        if(tree.i != -1) {
            visitInOrderTraversal(tree.left);
            result[idx++] = tree.i;
            //System.out.print(tree.i+" ");
            visitInOrderTraversal(tree.right);
        }
        //System.out.println();
    }
    int[] getArrayInOrderTraversal(int n) {
        idx = 0;
        result = new int[n];
        visitInOrderTraversal(binaryTree);
        return result;
    }

    /*
     * Complete the swapNodes function below.
     */
    static int[][] swapNodes(int[][] indexes, int[] queries) {

        SolutionSwapNodes solution = new SolutionSwapNodes();
        solution.buildBinaryTree(indexes);
        //solution.visitInOrderTraversal(solution.binaryTree);

        int[][] result = new int[queries.length][];
        for(int i = 0; i < queries.length; i++) {
            solution.swapLevel(queries[i]);
            result[i] = solution.getArrayInOrderTraversal(indexes.length);
        }

        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(scanner.nextLine().trim());

        int[][] indexes = new int[n][2];

        for (int indexesRowItr = 0; indexesRowItr < n; indexesRowItr++) {
            String[] indexesRowItems = scanner.nextLine().split(" ");

            for (int indexesColumnItr = 0; indexesColumnItr < 2; indexesColumnItr++) {
                int indexesItem = Integer.parseInt(indexesRowItems[indexesColumnItr].trim());
                indexes[indexesRowItr][indexesColumnItr] = indexesItem;
            }
        }

        int queriesCount = Integer.parseInt(scanner.nextLine().trim());

        int[] queries = new int[queriesCount];

        for (int queriesItr = 0; queriesItr < queriesCount; queriesItr++) {
            int queriesItem = Integer.parseInt(scanner.nextLine().trim());
            queries[queriesItr] = queriesItem;
        }

        int[][] result = swapNodes(indexes, queries);

        for (int resultRowItr = 0; resultRowItr < result.length; resultRowItr++) {
            for (int resultColumnItr = 0; resultColumnItr < result[resultRowItr].length; resultColumnItr++) {
                bufferedWriter.write(String.valueOf(result[resultRowItr][resultColumnItr]));
                //System.out.print(String.valueOf(result[resultRowItr][resultColumnItr]));

                if (resultColumnItr != result[resultRowItr].length - 1) {
                    bufferedWriter.write(" ");
                    //System.out.print(" ");
                }
            }

            if (resultRowItr != result.length - 1) {
                bufferedWriter.write("\n");
                //System.out.println();
            }
        }

        bufferedWriter.newLine();
        //System.out.println();

        bufferedWriter.close();
    }
}
