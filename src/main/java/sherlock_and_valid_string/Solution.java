package sherlock_and_valid_string;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the isValid function below.
    static String isValid(String s) {
        int[] alphabets = new int[26];
        for (char c : s.toCharArray()) {
            alphabets[c - 'a']++;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int criteria = 0, index = 0;
        for (int i = 0; i < alphabets.length; i++) {
            int alphabet = alphabets[i];
            if (alphabet != 0) {
                map.put(alphabet, 1);
                criteria = alphabet;
                index = i;
                break;
            }
        }
        for (int i = index + 1, alphabetCount = alphabets[i]; i < alphabets.length && alphabetCount != 0; i++) {
            if (!map.containsKey(alphabetCount)) {
                map.put(alphabetCount, 1);
                continue;
            }
            if (Math.abs(criteria - alphabetCount) > 1) return "NO";
            map.put(alphabetCount, map.get(alphabetCount) + 1);
        }
        if (map.size() == 1) return "YES";
        boolean over = map.values().stream().allMatch(i -> i > 1);
        if (over) return "NO";
        return map.size() == 2 ? "YES" : "NO";
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
