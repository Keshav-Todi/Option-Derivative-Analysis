public class ArrayConverter {
    // Convert double[][] to String[]
    public static String[] convertToStringArray(double[][] inputArray) {
        String[] stringArray = new String[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            StringBuilder rowStringBuilder = new StringBuilder();
            for (int j = 0; j < inputArray[i].length; j++) {
                if (j > 0) {
                    rowStringBuilder.append(" "); // Separate values with space
                }
                rowStringBuilder.append(inputArray[i][j]);
            }
            stringArray[i] = rowStringBuilder.toString();
        }
        return stringArray;
    }

    // Convert String[] back to double[][]
    public static double[][] convertToDoubleArray(String[] inputArray) {
        double[][] doubleArray = new double[inputArray.length][];
        for (int i = 0; i < inputArray.length; i++) {
            String[] values = inputArray[i].split(" ");
            doubleArray[i] = new double[values.length];
            for (int j = 0; j < values.length; j++) {
                doubleArray[i][j] = Double.parseDouble(values[j]);
            }
        }
        return doubleArray;
    }

    public static void main(String[] args) {
        // Example double[][] array
        double[][] originalArray = {
            {1.0, 2.0, 3.0},
            {4.0, 5.0, 6.0},
            {7.0, 8.0, 9.0}
        };

        //ArrayConverter ob37=new ArrayConverter();
        // Convert double[][] to String[]
        String[] stringArray = convertToStringArray(originalArray);

        // Output the String[] content
        for (String row : stringArray) {
            System.out.println(row);
        }
System.out.println("\nyo\n");
        // Convert String[] back to double[][]
        double[][] convertedArray = convertToDoubleArray(stringArray);

        // Output the double[][] content
        for (double[] row : convertedArray) {
            for (double val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
