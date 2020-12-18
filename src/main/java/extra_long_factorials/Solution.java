package extra_long_factorials;

import java.math.BigInteger;
import java.util.Scanner;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the extraLongFactorials function below.
    static void extraLongFactorials(int n) {
        BigInteger bigInteger = BigInteger.valueOf(1);
        for (int i = n; i >= 2; i--) {
            bigInteger = bigInteger.multiply(BigInteger.valueOf(i));
        }
        System.out.println(bigInteger);
    }

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        extraLongFactorials(n);

        scanner.close();
    }
}
