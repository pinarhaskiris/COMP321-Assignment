import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.*;

import java.util.HashMap;

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

        Pattern pat = Pattern.compile("\\w+ with",
                Pattern.CASE_INSENSITIVE);
        Matcher m = pat.matcher(text);

        String str = null;

        if (m.find()) {
            str = m.group();
        }

        String new_text = text;
        Pattern pat_small = null;

        if (str != null) {
            pat_small = Pattern.compile("^" + str.charAt(0) + ".*");
        }

        try {
            File myObj = new File("feelings.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine() && str != null) {
                String data = myReader.nextLine();

                Matcher m_small = pat_small.matcher(data);

                if (m_small.find()) {
                    new_text = m.replaceFirst(m_small.group());
                }
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return new_text;
    }

    public static String fifth_regex(String text){
        String new_text_fin = text;
        if (text != null) {
            Pattern pat = Pattern.compile("\\b(\\w+)$",
                    Pattern.CASE_INSENSITIVE);
            Matcher m = pat.matcher(text);


            if (m.find()) {
                String new_text = m.group(1).replaceAll("w(.)?", "$1");
                new_text_fin = m.replaceAll(new_text);
            }
        }

        return new_text_fin;
    }

    public static String fourth_regex(String text){
        Pattern pat = Pattern.compile("(^[A-Z]\\w+\\W\\w\\s)(\\w)+",
                Pattern.CASE_INSENSITIVE);
        Matcher m = pat.matcher(text);

        String new_text_fin = text;
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

    public static String repeat_sixth_reg(String text){
        if (text != sixth_regex(text))
            while (text.contains("with")) {
                text = sixth_regex(text);
            }
            return text;
    }

    public static HashMap convert_rules_to_array() throws FileNotFoundException {
        HashMap<String, List<String[]>> rules = new HashMap<String, List<String[]>>();
        rules.put("S", new ArrayList<String[]>());
        rules.get("S").add(new String[]{"N"});
        rules.get("S").add(new String[]{"S's B A"});

        rules.put("A", new ArrayList<String[]>());
        rules.get("A").add(new String[]{"V B"});
        rules.get("A").add(new String[]{"V B C B"});

        rules.put("V", new ArrayList<String[]>());
        rules.put("B", new ArrayList<String[]>());
        rules.put("N", new ArrayList<String[]>());

        File myObj = new File("feelings.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            rules.get("V").add(new String[]{data});
        }


        File myObj2 = new File("relatives.txt");
        myReader = new Scanner(myObj2);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            rules.get("B").add(new String[]{data});
        }


        File myObj3 = new File("names.txt");
        myReader = new Scanner(myObj3);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            rules.get("N").add(new String[]{data});
        }


        rules.put("C", new ArrayList<String[]>());
        rules.get("C").add(new String[]{"and"});
        rules.get("C").add(new String[]{"or"});

        myReader.close();
        return rules;
    }

    public static String replace_advanced(String text, List<String[]> list, String rep){
        String new_text = text;
        for (String[] element: list) {
            for(String mini_element: element){
                if (text.contains(mini_element))
                    new_text = replace(text, mini_element, rep);
            }
        }
        return new_text;
    }


    public static String first_step_back(String text) throws IOException {

        HashMap<String, List<String[]>> rules = convert_rules_to_array();

        /* for Name */
        List<String[]> names = rules.get("N");
        String name_conv = replace_advanced(text, names, "N");

        /* for Relatives */
        List<String[]> relatives = rules.get("B");
        String relative_conv = replace_advanced(name_conv, relatives, "B");

        /* for Feelings */
        List<String[]> feelings = rules.get("V");
        String feelings_conv = replace_advanced(relative_conv, feelings, "V");

        /* for Conj */
        List<String[]> conj = rules.get("C");
        String conj_conv = replace_advanced(feelings_conv, conj, "C");

        if (name_conv != text || relative_conv != name_conv || feelings_conv != relative_conv || conj_conv != feelings_conv)
            conj_conv = first_step_back(conj_conv);

        return conj_conv;

    }

    public static String second_step_back(String text) throws FileNotFoundException {
        HashMap<String, List<String[]>> rules = convert_rules_to_array();

        /* for A */
        List<String[]> a_rules = rules.get("A");
        String a_conv = replace_advanced(text, a_rules, "A");

        if (text.contains("V B"))
            a_conv = second_step_back(a_conv);

        return a_conv;
    }

    public static String third_step_back(String text) throws FileNotFoundException {
        HashMap<String, List<String[]>> rules = convert_rules_to_array();

        /* for S */
        List<String[]> a_rules = rules.get("S");
        String s_conv = replace_advanced(text, a_rules, "S");

        if (text.contains("B A") || text.equals("N"))
            s_conv = third_step_back(s_conv);

        return s_conv;
    }

    public static Boolean CFG(String text) throws IOException {
        String str = (third_step_back(second_step_back(first_step_back(text))));

        if (str.equals("S"))
            return true;
        else
            return false;
    }

    public static void org_to_int() throws IOException {
        File original = new File("original.txt");
        Scanner myReader = new Scanner(original);

        File intermediate = new File("intermediate.txt");
        FileWriter fr_int = new FileWriter(intermediate, true);
        BufferedWriter br_int = new BufferedWriter(fr_int);

        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String first_reg = first_regex(data);
            String second_reg = second_regex(first_reg);
            String third_reg = third_regex(second_reg);
            String fourth_reg = fourth_regex(third_reg);
            String fifth_reg = fifth_regex(fourth_reg);
            String sixth_reg = sixth_regex(fifth_reg);
            br_int.write(repeat_sixth_reg(sixth_reg) + "\n");
        }

        br_int.close();
        fr_int.close();
        myReader.close();
    }

    public static void int_to_out() throws IOException {
        File intermediate = new File("intermediate.txt");
        Scanner myReader = new Scanner(intermediate);

        File output = new File("output.txt");
        FileWriter fr_out = new FileWriter(output, true);
        BufferedWriter br_out = new BufferedWriter(fr_out);

        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            Boolean is_ok = CFG(data);
            if (is_ok)
                br_out.write(data + "\n");
        }

        myReader.close();
        br_out.close();
        fr_out.close();
    }

    public static void main(String[] args) throws IOException {
       org_to_int();
       int_to_out();
    }
}