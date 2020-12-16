package time_conversion;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution {

    private static final Scanner scan = new Scanner(System.in);

    /*
     * Complete the timeConversion function below.
     */
    static String timeConversion(String s) {
        /*
         * Write your code here.
         */
        // hh:mm:ssAM
        boolean isAm = s.substring(8).equals("AM");
        int hour = Integer.parseInt(s.substring(0, 2));
        hour = isAm ?
                (hour >= 12 ? hour - 12 : hour) :
                (hour < 12 ? hour + 12 : hour);
        return String.format("%02d%s", hour, s.substring(2, 8));
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scan.nextLine();

        String result = timeConversion(s);

        bw.write(result);
        bw.newLine();

        bw.close();
    }
}
