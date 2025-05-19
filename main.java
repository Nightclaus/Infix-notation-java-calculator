//import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Main {
    // Constructor
    static Scanner potato = new Scanner(System.in);
    static String[] constructor(String phrase) {return phrase.split("(?=[\\(])|(?<=[\\)])");}
    static String numbers = "1234567890";

    // Cleaner
    static String clean(String arg) {return arg.replaceAll("[\\[\\] \\(\\),]", "");}

    // Equator - Multiplication and Division
    static String interal_MD_equator(String equation) {
        List<String> output = new ArrayList<>();
        String[] iterableEquation = equation.split("(?=[+\\-*/])|(?<=[+*/])"); // Removed \\- to ensure that the - in *- and /- is preseverd
        for (int index = 0; index < iterableEquation.length; index++) {
            String token = iterableEquation[index];
            if ("*/".contains(token)) { // Instead of doing token.contain("*"), check if token is in "*/" to do both
                double prevToken = Double.parseDouble(output.remove(output.size() - 1)); // parseDouble is more efficent than valueOf
                double nextToken = Double.parseDouble(iterableEquation[++index]);
                double result = token.equals("*") ? prevToken * nextToken : prevToken / nextToken;
                output.add(String.valueOf(result));
            } else {
                output.add(token);
            }
        }
        return String.join("", output); // Better than output.toString(); cos it avoids the [] and ,
    }

    // Equator - Addition and Subtraction
    static Double internal_AS_Equator(String equation) { // Need to fix, currently does not accept ++ +- -- which needs a manual solution.
        equation = clean(equation);
        String[] iterableEquation = equation.split("(?=[+\\-])");
        Double output = 0.0;
        for (String strNumber : iterableEquation) {output += Double.valueOf(strNumber);}
        return output;
    }

    //Equator - Main Equator
    static Double equator(String equation) {
        equation = clean(equation); // "-" means range, so \\- means literal or the "-" symbol
        return internal_AS_Equator(interal_MD_equator(equation));
    }

    // Bracket Equator
    static Double bracketEquator(String equation) {
        String finalEquation = "";
        do { // Streams are like a for loop
            String[] iterableEquation = constructor(equation);
            List<String> arrayEquation = new ArrayList<>(Arrays.asList(iterableEquation)); // Arrays.asList(iterableEquation) is "warped" into the array to ensure that the List does not have a set size. This is not assigning it, but instead warping it
            arrayEquation.replaceAll(term -> (term.contains("(") && term.contains(")")) ? String.valueOf(equator(term)) : term);
            finalEquation = clean(arrayEquation.toString());
        } while (finalEquation.contains("(") || finalEquation.contains(")"));
        return equator(clean(finalEquation)); // Final run for all non bracket members
    }

    // New function, introduce multiplication signs between ")(" or "i(" or ")i"
    static String explicitMultiplication(String equation) {
        StringBuilder result = new StringBuilder();
        char lastChar = ' ';
        equation = equation.replace(" ", "");

        for (char item : equation.toCharArray()) {
            if ((lastChar == ')' && item == '(') || 
                (numbers.contains(String.valueOf(lastChar)) && item == '(') || 
                (lastChar == ')' && numbers.contains(String.valueOf(item)))) {
                result.append('*');
            }
            result.append(item);
            lastChar = item;
        }
        return result.toString();
    }

    public static void main(String[] args) { // Failed 5/5(5-6) 
        //String equation = "8.0+(12*7+(4+8*2)/4)-6*(5+5)+4"; // 12+((4+5)*(1284-1182)) ; "8.0+(12*7+(4+8*2)/4)-6*(5+5)+4"
        System.out.println("----- Enter an equation -----");
        String equation = potato.nextLine();
        System.out.printf("Ans is %s\n", bracketEquator(explicitMultiplication(equation)));
        System.out.println("-----     - Done -      -----");
    }
}