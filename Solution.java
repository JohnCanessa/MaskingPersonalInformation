import java.util.Scanner;

/**
 * 831. Masking Personal Information
 * https://leetcode.com/problems/masking-personal-information/
 */
public class Solution {


    /**
     * Mask PPI on email address.
     */
    static String maskEMail(String S) {

        // **** sanity checks ****
        if ((S.length() < 8) || !S.contains("@") || !S.contains("."))
            return "";

        // **** convert to lower case ****
        S = S.toLowerCase();

        // **** '@' split ****
        String[] atSplit = S.split("@");

        // **** ****
        if (atSplit[0].length() < 2)
            return "";

        // **** '.' split ****
        String[] dotSplit = atSplit[1].split("\\.");

        // **** ****
        if ((dotSplit[0].length() < 2) || (dotSplit[1].length() < 2))
            return "";

        // **** build anonymized string ****
        S = atSplit[0].charAt(0) + "*****" + atSplit[0].charAt(atSplit[0].length() - 1);
        S += "@";
        S += dotSplit[0];
        S += ".";
        S += dotSplit[1];

        // **** returned anonymized string ****
        return S;
    }


    /**
     * Mask PPI on phone number.
     * A phone number is represented as: a + b + c + d
     */
    static String maskPhone(String S) {

        // **** extract digits only ****
        S = S.replaceAll("\\D+", "");

        // **** check length ****
        if ((S.length() < 10) || (S.length() > 13))
            return "";

        // **** extract d component ****
        String d = S.substring(S.length() - 4, S.length());

        // **** ****
        switch (S.length()) {
            case 10:
                S = "***-***-" + d;
            break;

            case 11:
                S = "+*-" + "***-***-" + d;
            break;

            case 12:
                S = "+**-" + "***-***-" + d;
            break;

            case 13:
                S = "+***-" + "***-***-" + d;
            break;

            default:
                return "";
        }

        // **** returned anonymized string ****
        return S;
    }


    /**
     * Please see requirements on the LeetCode web page.
     * Runtime: 8 ms, faster than 54.44% of Java online submissions.
     * Memory Usage: 37.9 MB, less than 63.91% of Java online submissions.
     */
    static String maskPII(String S) {
        if (S.contains("@"))
            return maskEMail(S);
        else
            return maskPhone(S);
    }


    /**
     * Test scaffolding
     */
    public static void main(String[] args) {
        
        // **** open scanner ****
        Scanner sc = new Scanner(System.in);

        // **** read string ****
        String S = sc.nextLine().trim();

        // ???? ????
        System.out.println("main <<< S ==>" + S + "<==");

        // **** close scanner ****
        sc.close();

        // **** process and display the result ****
        System.out.println("main <<< maskPPI ==>" + maskPII(S) + "<==");
    }
}