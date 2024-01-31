import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        System.out.println(calc(input));
        in.close();
//        while(true){
//            String input = in.nextLine();
//            System.out.println(calc(input));
//        }
    }

    public static String calc(String input) throws Exception {
        input = input.replace(" ", "");
        String[] inputNumbers = input.split("[+\\-*/]");
        if (inputNumbers.length != 2){
            throw new Exception("Invalid input. Must be 2 operands");
        } else {
            String sign = input.substring(inputNumbers[0].length(), inputNumbers[0].length()+1);
            boolean isRoman = inputNumbers[0].matches("[IVX]+");
            int a, b;
            if (isRoman){
                a = romanToArabic(inputNumbers[0]);
                b = romanToArabic(inputNumbers[1]);
            } else {
                a = Integer.parseInt(inputNumbers[0]);
                b = Integer.parseInt(inputNumbers[1]);
            }
            if (a < 1 || a > 10 || b < 1 || b > 10) {
                throw new Exception("Number out of range.");
            }
            int result = switch (sign) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                case "/" -> a / b;
                default -> throw new Exception("Invalid mathematical operation.");
            };
            if (isRoman){
                return arabicToRoman(result);
            } else {
                return Integer.toString(result);
            }
        }
    }

    static String arabicToRoman(int num) throws Exception {
        if (num >= 1 && num <=100){
            String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
            String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
            int u = num % 10;
            int t = num / 10;
            return tens[t]+units[u];
        } else {
            throw new Exception("Failed to convert to Roman.");
        }
    }

    static int romanToArabic(String num) throws Exception {
        String[] romeNumbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        for (int i = 0; i < romeNumbers.length; i++){
            if (num.equals(romeNumbers[i])) {
                return i + 1;
            }
        }
        throw new Exception("Roman number is invalid or out of range.");
    }
}
