package plus_minus;

import java.util.Scanner;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the plusMinus function below.
    static void plusMinus(int[] arr) {
        float plus = 0, minus = 0, zero = 0;
        for (int i : arr) {
            if (i > 0) plus++;
            else if (i < 0) minus++;
            else zero++;
        }
        int length = arr.length;
        System.out.printf("%6f", plus / length);
        System.out.printf("%6f", minus / length);
        System.out.printf("%6f", zero / length);
    }

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        plusMinus(arr);

        scanner.close();
    }
}
