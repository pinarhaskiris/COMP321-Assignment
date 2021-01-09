import java.util.regex.*;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

class Testing {

    public static String replace(String text, String reg, String rep){
        Pattern pat = Pattern.compile(reg,
                Pattern.CASE_INSENSITIVE);
        Matcher m = pat.matcher(text);
        String new_text = m.replaceAll(rep);
        return new_text;
    }

    public static String first_regex(String text){
        String new_text = replace(text, "0", "a");
        new_text = replace(new_text, "5", "e");
        new_text = replace(new_text, "3", "i");
        new_text = replace(new_text, "7", "o");
        new_text = replace(new_text, "9", "u");
        return new_text;
    }

    public static String second_regex(String text){
        String new_text = replace(text, ":\\)", "u");
        new_text = replace(new_text, "\\):", "t");
        new_text = replace(new_text, "XD", "and");
        new_text = replace(new_text, "==", "h");
        return new_text;
    }

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
                String first_reg = first_regex(data);
                String second_reg = second_regex(first_reg);
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