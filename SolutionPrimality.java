import java.util.Scanner;

public class SolutionPrimality {

    private static boolean checkPrimality(int n) {

        if(n == 1) {
            return false;
        }

        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i ++) {
            if (n % i == 0) {
                return false;
            }
        }
        
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int p = in.nextInt();
        for(int a0 = 0; a0 < p; a0++){
            int n = in.nextInt();

            if(checkPrimality(n)) {
                System.out.println("Prime");
            } else {
                System.out.println("Not prime");
            }
        }
    }
}
