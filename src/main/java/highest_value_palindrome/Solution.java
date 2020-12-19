package highest_value_palindrome;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the highestValuePalindrome function below.
    static String highestValuePalindrome(String s, int n, int k) {
        char[] chars = s.toCharArray();
        int nonMatches = 0;
        for (int i = 0, j = chars.length - 1; i < j; i++, j--) if (chars[i] != chars[j]) nonMatches++;
        if (nonMatches > k) return "-1";

        int changeBoth = k - nonMatches;
        int left = 0;
        int right = n - 1;
        for (; left <= right; left++, right--) {
            char max = (char) Math.max(chars[left], chars[right]);
            if (chars[left] != chars[right]) {
                if (max != '9' && changeBoth - 1 >= 0) {
                    chars[left] = '9';
                    chars[right] = '9';
                    changeBoth--;
                } else {
                    chars[left] = max;
                    chars[right] = max;
                }
            } else {
                if (max != '9' && changeBoth - 2 >= 0) {
                    chars[left] = '9';
                    chars[right] = '9';
                    changeBoth -= 2;
                }
            }
        }
        if (changeBoth != 0 && left - 1 == right + 1) chars[left - 1] = '9';
        return new String(chars);
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        String s = scanner.nextLine();

        String result = highestValuePalindrome(s, n, k);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
