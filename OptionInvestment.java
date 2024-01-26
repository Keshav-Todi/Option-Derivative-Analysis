import java.util.Scanner;

public class OptionInvestment {
    double numberOfCalls=0.0, numberOfPuts=0.0,callcost=0.0,putcost=0.0;
    public void opi( double totalAmount, double ratio1, double ratio2, double callOptionPrice, double putOptionPrice)
    {
        Scanner scanner = new Scanner(System.in);
        /*
        System.out.print("Enter the total amount you will invest: ");
        double totalAmount = scanner.nextDouble();

        System.out.print("Enter the ratio 1 of call option to put option (e.g., 2 for 2:1 ratio): ");
        int ratio1 = scanner.nextInt();

        System.out.print("Enter the ratio 2 of call option to put option (e.g., 2 for 2:1 ratio): ");
        int ratio2 = scanner.nextInt();

        System.out.print("Enter the price of the call option: ");
        double callOptionPrice = scanner.nextDouble();

        System.out.print("Enter the price of the put option: ");
        double putOptionPrice = scanner.nextDouble();
         */

        // Calculate the total investment based on the total amount and the ratio
        double callInvestment = totalAmount* ratio1 / (ratio1 + ratio2);
        double putInvestment = totalAmount* ratio2 / (ratio1 + ratio2);
        numberOfCalls = (callInvestment / callOptionPrice);
        numberOfPuts = (putInvestment / putOptionPrice);
        callcost=callOptionPrice*numberOfCalls;
        putcost=putOptionPrice*numberOfPuts;

        /*
        System.out.println("\nInvestment Plan:");
        System.out.println("Total Amount: $" + totalAmount);
        System.out.println("Number of Call Options: " + numberOfCalls);
        System.out.println("Number of Put Options: " + numberOfPuts);
        System.out.println("Call cost " + callcost);
        System.out.println("Put cost " + putcost);
         */

        scanner.close();
    }
    double numberOf_f=0.0, numberOf_s=0.0,numberOf_t=0.0, numberOf_fr=0.0,f_cost=0.0,s_cost=0.0,t_cost=0.0,fr_cost=0.0;

    public void three_opi( double totalAmount, double ratio1, double ratio2, double ratio3, double firstOptionPrice, double secondOptionPrice, double thirdOptionPrice)
    {
        Scanner scanner = new Scanner(System.in);
        /*
        System.out.print("Enter the total amount you will invest: ");
        double totalAmount = scanner.nextDouble();

        System.out.print("Enter the ratio 1 of call option to put option (e.g., 2 for 2:1 ratio): ");
        int ratio1 = scanner.nextInt();

        System.out.print("Enter the ratio 2 of call option to put option (e.g., 2 for 2:1 ratio): ");
        int ratio2 = scanner.nextInt();

        System.out.print("Enter the price of the call option: ");
        double callOptionPrice = scanner.nextDouble();

        System.out.print("Enter the price of the put option: ");
        double putOptionPrice = scanner.nextDouble();
         */

        // Calculate the total investment based on the total amount and the ratio

        if(ratio1<0||ratio2<0||ratio3<0)
        {
            ratio1=Math.abs(ratio1);ratio2=Math.abs(ratio2);ratio3=Math.abs(ratio3);
            double nm_size= totalAmount / (firstOptionPrice*ratio1 + secondOptionPrice*ratio2 + thirdOptionPrice*ratio3);
            numberOf_f = ratio1*nm_size;
            numberOf_s = ratio2*nm_size;
            numberOf_t = ratio3*nm_size;
        }
        else
        {
            double firstInvestment = totalAmount* ratio1 / (ratio1 + ratio2+ratio3);
            double secondInvestment = totalAmount* ratio2 / (ratio1 + ratio2+ratio3);
            double thirdInvestment = totalAmount* ratio3 / (ratio1 + ratio2+ratio3);

            numberOf_f = (firstInvestment / firstOptionPrice);
            numberOf_s = (secondInvestment / secondOptionPrice);
            numberOf_t = (thirdInvestment / thirdOptionPrice);
        }

        f_cost=firstOptionPrice*numberOf_f;
        s_cost=secondOptionPrice*numberOf_s;
        t_cost=thirdOptionPrice*numberOf_t;
        
        //System.out.println("Num is "+numberOf_f+","+numberOf_s+","+numberOf_t);

        /*
        System.out.println("\nInvestment Plan:");
        System.out.println("Total Amount: $" + totalAmount);
        System.out.println("Number of Call Options: " + numberOfCalls);
        System.out.println("Number of Put Options: " + numberOfPuts);
        System.out.println("Call cost " + callcost);
        System.out.println("Put cost " + putcost);
         */

        scanner.close();
    }

    public void four_opi( double totalAmount, double ratio1, double ratio2, double ratio3, double ratio4 ,double firstOptionPrice, double secondOptionPrice, double thirdOptionPrice, double fourthOptionPrice )
    {
        Scanner scanner = new Scanner(System.in);
        /*
        System.out.print("Enter the total amount you will invest: ");
        double totalAmount = scanner.nextDouble();

        System.out.print("Enter the ratio 1 of call option to put option (e.g., 2 for 2:1 ratio): ");
        int ratio1 = scanner.nextInt();

        System.out.print("Enter the ratio 2 of call option to put option (e.g., 2 for 2:1 ratio): ");
        int ratio2 = scanner.nextInt();

        System.out.print("Enter the price of the call option: ");
        double callOptionPrice = scanner.nextDouble();

        System.out.print("Enter the price of the put option: ");
        double putOptionPrice = scanner.nextDouble();
         */

        // Calculate the total investment based on the total amount and the ratio
        if(ratio1<0||ratio2<0||ratio3<0||ratio4<0)
        {
            ratio1=Math.abs(ratio1);ratio2=Math.abs(ratio2);ratio3=Math.abs(ratio3);ratio4=Math.abs(ratio4);
            double nm_size= totalAmount / (firstOptionPrice*ratio1 + secondOptionPrice*ratio2 + thirdOptionPrice*ratio3+ fourthOptionPrice*ratio4);
            numberOf_f = ratio1*nm_size;
            numberOf_s = ratio2*nm_size;
            numberOf_t = ratio3*nm_size;
            numberOf_fr = ratio4*nm_size;
        }
        else
        {
            double firstInvestment = totalAmount* ratio1 / (ratio1 + ratio2+ratio3+ratio4);
            double secondInvestment = totalAmount* ratio2 / (ratio1 + ratio2+ratio3+ratio4);
            double thirdInvestment = totalAmount* ratio3 / (ratio1 + ratio2+ratio3+ratio4);
            double fourthInvestment = totalAmount* ratio4 / (ratio1 + ratio2+ratio3+ratio4);

            numberOf_f = (firstInvestment / firstOptionPrice);
            numberOf_s = (secondInvestment / secondOptionPrice);
            numberOf_t = (thirdInvestment / thirdOptionPrice);
            numberOf_fr = (fourthInvestment / fourthOptionPrice);
        }
        
        f_cost=firstOptionPrice*numberOf_f;
        s_cost=secondOptionPrice*numberOf_s;
        t_cost=thirdOptionPrice*numberOf_t;
        fr_cost=fourthOptionPrice*numberOf_fr;
        
        //System.out.println("Num is "+numberOf_f+","+numberOf_s+","+numberOf_t+","+numberOf_fr);

        /*
        System.out.println("\nInvestment Plan:");
        System.out.println("Total Amount: $" + totalAmount);
        System.out.println("Number of Call Options: " + numberOfCalls);
        System.out.println("Number of Put Options: " + numberOfPuts);
        System.out.println("Call cost " + callcost);
        System.out.println("Put cost " + putcost);
         */

        scanner.close();
    }
}
