import java.io.*;
import java.util.Scanner;

public class FileReadWriteExample {

    public static void main(String[] args) {
        FileReadWriteExample obrw1=new FileReadWriteExample();
        obrw1.mainn();
    }

    public void mainn() {
        // Specify the file path
        String filePath = "OptionDefaultdata.txt";

        // Read data from the file
        String[] data = readFile(filePath);

        // Display the current data
        System.out.println("Current Data: " + arrayToString(data));

        // Allow the user to enter new values
        String[] newData = getUserInput();

        // Choose the writing mode: 0 - Clear previous data, 1 - Append new data
        int writingMode = 0; // Change this value as needed

        // Save the updated values to the file
        writeFile(filePath, newData, writingMode);

        // Display the updated data
        String[] updatedData = readFile(filePath);
        System.out.println("Updated Data: " + arrayToString(updatedData));
    }

    String[] readFile(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            StringBuilder content = new StringBuilder();
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }
            return content.toString().split("\n");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            return new String[0]; // Return an empty array in case of an error
        }
    }

    String[] getUserInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter new values (enter 'done' to finish):");
            StringBuilder input = new StringBuilder();
            String line;
            while (!(line = scanner.nextLine()).equalsIgnoreCase("done")) {
                input.append(line).append("\n");
            }
            return input.toString().split("\n");
        }
    }

    void writeFile(String filePath, String[] newData, int writingMode) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, writingMode == 1))) {
            for (String value : newData) {
                writer.println(value);
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filePath);
        }
    }

    private String arrayToString(String[] array) {
        StringBuilder result = new StringBuilder("[");
        for (String value : array) {
            result.append(value).append(", ");
        }
        if (array.length > 0) {
            result.setLength(result.length() - 2); // Remove the trailing comma and space
        }
        result.append("]");
        return result.toString();
    }
}
