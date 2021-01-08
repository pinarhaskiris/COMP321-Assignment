import java.util.regex.*;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

class Testing {

    /* FIRST REGEX RULE:
    * decrypts the names
    * replace
    * 0 -> a, 5 -> e, 3 -> i, 7 -> o, 9 -> u */

    public static String zero_to_a(String text){
        Pattern pat = Pattern.compile("0",
                Pattern.CASE_INSENSITIVE);
        Matcher m = pat.matcher(text);
        String new_text = m.replaceAll("a");
        return new_text;
    }

    public static String five_to_e(String text){
        Pattern pat = Pattern.compile("5",
                Pattern.CASE_INSENSITIVE);
        Matcher m = pat.matcher(text);
        String new_text = m.replaceAll("e");
        return new_text;
    }

    public static String three_to_i(String text){
        Pattern pat = Pattern.compile("3",
                Pattern.CASE_INSENSITIVE);
        Matcher m = pat.matcher(text);
        String new_text = m.replaceAll("i");
        return new_text;
    }

    public static String seven_to_o(String text){
        Pattern pat = Pattern.compile("7",
                Pattern.CASE_INSENSITIVE);
        Matcher m = pat.matcher(text);
        String new_text = m.replaceAll("o");
        return new_text;
    }

    public static String nine_to_u(String text){
        Pattern pat = Pattern.compile("9",
                Pattern.CASE_INSENSITIVE);
        Matcher m = pat.matcher(text);
        String new_text = m.replaceAll("u");
        return new_text;
    }

    /* SECOND REGEX RULE:
    * decrypts for the entire file
    * replace
    * :) -> u
    * ): -> t
    * XD -> and
    * == -> h
     */

    public static String smiley_to_u(String text){
        Pattern pat = Pattern.compile(":\\)",
                Pattern.CASE_INSENSITIVE);
        Matcher m = pat.matcher(text);
        String new_text = m.replaceAll("u");
        return new_text;
    }

    public static String sad_face_to_t(String text){
        Pattern pat = Pattern.compile("\\):",
                Pattern.CASE_INSENSITIVE);
        Matcher m = pat.matcher(text);
        String new_text = m.replaceAll("t");
        return new_text;
    }

    public static String XD_to_and(String text){
        Pattern pat = Pattern.compile("XD",
                Pattern.CASE_INSENSITIVE);
        Matcher m = pat.matcher(text);
        String new_text = m.replaceAll("and");
        return new_text;
    }

    public static String equal_to_h(String text){
        Pattern pat = Pattern.compile("==",
                Pattern.CASE_INSENSITIVE);
        Matcher m = pat.matcher(text);
        String new_text = m.replaceAll("h");
        return new_text;
    }

    /* THIRD REGEX RULE:
     * replace
     * 'o -> or
     * 'a -> and
     */

    public static String conj_dec(String text){
        Pattern pat = Pattern.compile("\\'a|\\'o",
                Pattern.CASE_INSENSITIVE);
        Matcher m = pat.matcher(text);

        String new_text = null;
        if (m.equals("'a")) {
            new_text = m.replaceAll(" and");
        }
        else {
            new_text = m.replaceAll(" or");
        }
        return new_text;
    }

    public static void main(String[] args) {
        try {
            File myObj = new File("original.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String first_reg = nine_to_u(seven_to_o(three_to_i(five_to_e(zero_to_a(data)))));
                String second_reg = equal_to_h(XD_to_and(sad_face_to_t(smiley_to_u(first_reg))));
                String third_reg = conj_dec(second_reg);
                System.out.println(second_reg);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}