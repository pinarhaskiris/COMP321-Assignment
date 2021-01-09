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

    public static String third_regex(String text){
        String new_text = replace(text, "\\'a", " and");
        new_text = replace(new_text, "\\'o", " or");
        return new_text;
    }

    public static String sixth_regex(String text){

        Pattern pat = Pattern.compile("((\\w+ with).*)+",
                Pattern.CASE_INSENSITIVE);
        Matcher m = pat.matcher(text);

        String str = null;

        if (m.find()) {
            str = m.group();
            System.out.println(str);
        }

        try {
            File myObj = new File("feelings.txt");
            Scanner myReader = new Scanner(myObj);

            Pattern pat_small = Pattern.compile("^" + String.valueOf(str.charAt(0)) + ".*");

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Matcher m_small = pat_small.matcher(data);

                if (m_small.find()) {
                    System.out.println("***********" + data);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return null;
    }

    public static String fifth_regex(String text){
        Pattern pat = Pattern.compile("\\b(\\w+)$",
                Pattern.CASE_INSENSITIVE);
        Matcher m = pat.matcher(text);

        String new_text_fin = null;
        if (m.find()) {
            String new_text = m.group(1).replaceAll(".(.)?", "$1");
            new_text_fin = m.replaceAll(new_text);
        }

        return new_text_fin;
    }

    public static String fourth_regex(String text){
        Pattern pat = Pattern.compile("(^[A-Z]\\w+\\W\\w\\s)(\\w)+",
                Pattern.CASE_INSENSITIVE);
        Matcher m = pat.matcher(text);

        String new_text_fin = null;
        if (m.find()) {
            String matched_str = m.group();
            String[] arr = matched_str.split(" ");

            if (arr[1].length() % 2 == 0) {
                // if even -> swap last and first two letters
                String new_text = arr[1].replaceAll("(\\w\\w)(\\w+)(\\w\\w)", "$3$2$1");
                new_text_fin = m.replaceAll(arr[0] + " " + new_text);
            }
            else {
                // swap first and last letters
                String new_text = arr[1].replaceAll("(\\w)(\\w+)(\\w)", "$3$2$1");
                new_text_fin = m.replaceAll(arr[0] + " " + new_text);
            }
        }
        return new_text_fin;
    }

    public static void main(String[] args) {
        try {
            File myObj = new File("original.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String first_reg = first_regex(data);
                String second_reg = second_regex(first_reg);
                String third_reg = third_regex(second_reg);
                String fourth_reg = fourth_regex(third_reg);
                String fifth_reg = fifth_regex(fourth_reg);
                String sixth_reg = sixth_regex(fifth_reg);
                System.out.println(sixth_reg);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}