import java.util.Scanner;

public class AscendingPairsWithDifferenceOf2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the ending number: ");
        int end = scanner.nextInt();
        
        System.out.print("Enter the initial number: ");
        int start = scanner.nextInt();

        printAscendingPairsWithDifferenceOf2(start, end);
    }

    public static void printAscendingPairsWithDifferenceOf2(int start, int end) {
        if(end<=4)
            System.out.println("(" + 0 + ", " + end + ")");
        else
        {
            System.out.println("(" + 0 + ", " + 4 + ")");
            start=4;
            for (int i = start; i <= end; i += 3) {
                if (i + 2 <= end) {
                    System.out.println("(" + i + ", " + (i + 2) + ")");
                }
                if (i == (end-1)) {
                    System.out.println("(" + i + ", " + (i + 1) + ")");
                }
            }
        }
    }
}
