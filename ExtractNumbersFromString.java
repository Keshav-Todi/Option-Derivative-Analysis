import java.util.ArrayList;
import java.util.Scanner;

public class ExtractNumbersFromString {
    int leng=0;
    public double[] inp() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string with numbers: ");
        String inputString = scanner.nextLine();
        scanner.close();

        double[] numbers = extractNumbers(inputString);
        leng=numbers.length;
        /*
        System.out.println("numbers length is "+numbers.length);
        System.out.println("Numbers extracted from the string: ");
        for (int i=0;i<numbers.length;i++) {
            System.out.println(numbers[i]);
        }
        */
        return numbers;
    }

    public static double[] extractNumbers(String inputString) {
        ArrayList<Double> numberList = new ArrayList<>();

        StringBuilder numberBuilder = new StringBuilder();
        boolean isDecimal = false;

        for (char ch : inputString.toCharArray()) {
            if (Character.isDigit(ch) || ch == '.') {
                numberBuilder.append(ch);
                if (ch == '.') {
                    isDecimal = true;
                }
            } else if (ch == ' ' && numberBuilder.length() > 0) {
                if (isDecimal) {
                    numberList.add(Double.parseDouble(numberBuilder.toString()));
                } else {
                    numberList.add(Double.valueOf(numberBuilder.toString()));
                }
                numberBuilder.setLength(0);
                isDecimal = false;
            }
        }

        // Check for any remaining number after the last space
        if (numberBuilder.length() > 0) {
            if (isDecimal) {
                numberList.add(Double.parseDouble(numberBuilder.toString()));
            } else {
                numberList.add(Double.valueOf(numberBuilder.toString()));
            }
        }

        double[] numbers = new double[numberList.size()];
        for (int i = 0; i < numberList.size(); i++) {
            numbers[i] = numberList.get(i);
        }

        return numbers;
    }
}
