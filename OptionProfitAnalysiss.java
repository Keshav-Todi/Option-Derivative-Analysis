import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

public class OptionProfitAnalysiss {
    static double x[],y[]; 
    static double firstBreakeven=0, LastBreakeven=0, min=0;
    public static void mai(double cs, double cp, double ps, double pp, double sp, int cb, int pb) {
        //Scanner scanner = new Scanner(System.in);
        double a[][]=new double[1000][6];int l=0,j=0;
        x=new double[1000]; y=new double[1000];
        double callBreakeven=0, putBreakeven=0;
        // Input for Call Option 
        System.out.print("Enter the Call Option Strike Price: "); double callStrikePrice = cs;//1825;//scanner.nextDouble(); 
        System.out.print("Enter the Call Option Price: "); double callOptionPrice = cp;//29.9;//scanner.nextDouble(); 
        // Input for Put Option 
        System.out.print("Enter the Put Option Strike Price: "); double putStrikePrice = ps;//1875;//scanner.nextDouble(); 
        System.out.print("Enter the Put Option Price: "); double putOptionPrice = pp;//31.8;//scanner.nextDouble(); 
        System.out.print("Enter the Spot Price:\n "); double spotPrice = sp;//1848;//scanner.nextDouble();

        double callOptionsBought = cb,putoptionsbought=pb; // Number of options bought

        double minPriceAtExpiryPercentage = -0.10; // Minimum price at expiry percentage (-2%)
        double maxPriceAtExpiryPercentage = 0.10; // Maximum price at expiry percentage (2%)
        double priceStepPercentage = 0.0025; // Price at expiry step size (0.5%)

        // Loop through the range of price at expiry
        //for (double priceAtExpiryPercentage = minPriceAtExpiryPercentage; priceAtExpiryPercentage <= maxPriceAtExpiryPercentage; priceAtExpiryPercentage += priceStepPercentage) {
        //double priceAtExpiry = spotPrice * (1 + priceAtExpiryPercentage);
        //{
        //a[l][j++]= priceAtExpiryPercentage*100;

        for (double priceAtExpiry = spotPrice*(1+minPriceAtExpiryPercentage); priceAtExpiry <= spotPrice*(1+maxPriceAtExpiryPercentage); priceAtExpiry += 1) 
        {

            double priceAtExpiryPercentage=(1-spotPrice/priceAtExpiry)*100;
            a[l][j++]= priceAtExpiryPercentage;
            a[l][j++]= spotPrice;
            a[l][j++]= priceAtExpiry; x[l]=priceAtExpiry;

            double callNetProfit;
            // Calculate net profit for a call option
            if(callStrikePrice<=priceAtExpiry)
                callNetProfit = (priceAtExpiry - callStrikePrice - callOptionPrice) * callOptionsBought;
            else
                callNetProfit = -1*(callOptionPrice * callOptionsBought);

            callBreakeven = callStrikePrice+callOptionPrice;
            putBreakeven = putStrikePrice-putOptionPrice;

            double combinedSmallBreakeven;

            //if((putStrikePrice-putOptionPrice)>=callStrikePrice)
            //{comninedSmallBreakeven=putStrikePrice-putOptionPrice;}

            // Calculate profit percentage for a call option
            double callProfitPercentage = (callNetProfit / (callOptionPrice * callOptionsBought)) * 100;

            // Calculate probability percentage for a call option
            double callProbabilityPercentage = ((priceAtExpiry - callStrikePrice) / (priceAtExpiry)) * 100;

            System.out.println();System.out.println();
            /*System.out.println("\n\n\n----- Price at Expiry: " + priceAtExpiry + " (Percentage: " + priceAtExpiryPercentage * 100 + "%) Spot Price "+spotPrice+" ----- Call, "+callStrikePrice+", "+callOptionPrice+"  ; Put, "+putStrikePrice+", "+putOptionPrice);
            System.out.println("\n----- Call breakeven "+callBreakeven+" , Put breakeven "+putBreakeven+" -----\n");
            System.out.println("\n----- Call Option Analysis -----\n");
            System.out.println("Call Option Net Profit: $" + callNetProfit);
            System.out.println("Call Option Profit Percentage: " + callProfitPercentage + "%");
            System.out.println("Call Option Probability Percentage: " + callProbabilityPercentage + "%");
             */    
            double putNetProfit;
            // Calculate net profit for a put option
            if(putStrikePrice>=priceAtExpiry)
                putNetProfit = (putStrikePrice - priceAtExpiry - putOptionPrice) * putoptionsbought;
            else
                putNetProfit = -1*(putOptionPrice * putoptionsbought);

            // Calculate profit percentage for a put option
            double putProfitPercentage = (putNetProfit / (putOptionPrice * putoptionsbought)) * 100;

            // Calculate probability percentage for a put option
            double putProbabilityPercentage = ((spotPrice - putStrikePrice) / (putStrikePrice - priceAtExpiry)) * 100;

            /*System.out.println("\n----- Put Option Analysis -----\n");
            System.out.println("Put Option Net Profit: $" + putNetProfit);
            System.out.println("Put Option Profit Percentage: " + putProfitPercentage + "%");
            System.out.println("Put Option Probability Percentage: " + putProbabilityPercentage + "%");
             */
            // Calculate net profit for combined position (call + put)
            double callNetProfitCombined = callNetProfit;
            double putNetProfitCombined = putNetProfit;
            double combinedNetProfit = callNetProfitCombined + putNetProfitCombined;

            // Calculate profit percentage for combined position (call + put)
            double callProfitPercentageCombined = (callNetProfitCombined / (callOptionPrice * callOptionsBought)) * 100;
            double putProfitPercentageCombined = (putNetProfitCombined / (putOptionPrice * putoptionsbought)) * 100;
            double combinedProfitPercentage = (combinedNetProfit / (callOptionPrice*callOptionsBought + putOptionPrice*putoptionsbought)) * 100;

            a[l][j++]= callNetProfit;
            a[l][j++]= putNetProfit;
            a[l][j++]= combinedNetProfit; y[l]=combinedNetProfit;

            /*System.out.println("\n----- Combined Position (Call + Put) Analysis -----\n");
            System.out.println("Combined Position Net Profit: $" + combinedNetProfit);
            System.out.println("Combined Position Profit Percentage: " + combinedProfitPercentage + "%");
            System.out.println("\n\n");
             */
            l++;j=0;
        }

        firstBreakeven=0; LastBreakeven=0;
        for(int i=0;i<l;i++)
        {
            if(i!=0){
                if(a[i-1][5]>0&&a[i][5]<0)
                    firstBreakeven=a[i-1][2];
                if(a[i-1][5]<0&&a[i][5]>0)
                    LastBreakeven=a[i][2];
            }
        }

        min = a[0][5];
        for (int i = 1; i < a.length; i++) {
            if (a[i][5] < min) {
                min = a[i][5];
            }
        }

        /*System.out.println("\n\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("C%.2f , %.2f\n",callStrikePrice,callOptionPrice);
        System.out.printf("P%.2f , %.2f\n",putStrikePrice,putOptionPrice);
        System.out.printf("Breakeven %.2f , %.2f\n",firstBreakeven, LastBreakeven);
        System.out.printf("Min %.2f\n",min);
         */
        /*for(int i=0;i<l;i++)
        {
        if(i!=0){
        if((a[i-1][5]>0&&a[i][5]<0)||(a[i-1][5]<0&&a[i][5]>0))
        System.out.println("=========================================================================================================================================================================================================");
        else
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
        System.out.printf("C%.2f , %.2f\t",callStrikePrice,callOptionPrice);
        System.out.printf("P%.2f , %.2f\t",putStrikePrice,putOptionPrice);

        System.out.printf("C%.2f, P%.2f\t",callBreakeven,putBreakeven);

        for(int k=0;k<3;k++)
        {
        System.out.printf("%.2f ,\t",a[i][k]);   

        }

        for(int k=3;k<6;k++)
        {

        System.out.printf(" | %.2f | \t",a[i][k]);
        }
        System.out.println();   
        }*/
        /*System.out.printf("C%.2f , %.2f\n",callStrikePrice,callOptionPrice);
        System.out.printf("P%.2f , %.2f\n",putStrikePrice,putOptionPrice);
        System.out.printf("Breakeven %.2f , %.2f\n",firstBreakeven, LastBreakeven);
        System.out.printf("Min %.2f\n",min);
        System.out.println();*/
        //scanner.close();
    }

    public static void main() {
        mai(1825,36.8,1875,30.3,1850,1,1);
    }

    public static void man(int i) {
        Scanner sc =new Scanner(System.in);
        int numberofratio=3;
        double dd[][]=new double[i][5];
        double dda[][][]=new double[i][7][numberofratio];
        for (int i1=0;i1<i;i1++)
        {
            for(int j=0;j<5;j++)
            {
                System.out.println("");
                dd[i1][j]=sc.nextDouble();
            }
            System.out.println("Next");

        }
        for(int j1=0;j1<numberofratio;j1++)
        {
            for (int i1=0;i1<i;i1++)
            {
                mai(dd[i1][0],dd[i1][1],dd[i1][2],dd[i1][3],dd[i1][4],1,1);
                dda[i1][0][j1]=dd[i1][0];
                dda[i1][1][j1]=dd[i1][1];
                dda[i1][2][j1]=dd[i1][2];
                dda[i1][3][j1]=dd[i1][3];
                dda[i1][4][j1]=firstBreakeven;
                dda[i1][5][j1]=LastBreakeven;
                dda[i1][6][j1]=min;

            }
        }
        for (int j1=0;j1<numberofratio;j1++)
        {
            for (int i1=0;i1<i;i1++)
            {
                for(int j=0;j<7;j++)
                {
                    System.out.printf("%.2f , ",dda[i1][j][j1]);
                }
                System.out.println();

            }
        }
    }

    public static void mak() {
        Scanner sc =new Scanner(System.in);
        System.out.println("Enter 9999 to stop");
        int i=0,j=0;
        int numberofratio=3;
        double nd[][]=new double[1000][3];
        double dd[][]=new double[1000][5];
        double dda[][][]=new double[1000][7][numberofratio];
        double difda[][][]=new double[1000][8][numberofratio];
        double dipda[][][]=new double[1000][8][numberofratio];
        System.out.println("Enter Spot Price");
        double spp=sc.nextDouble();
        for(int i1=0;i1<10;i1++)//while(true)
        {
            for(j=0;j<3;j++)
            {
                nd[i][j]=sc.nextDouble();  
                //if(nd[i][j]==9999.0)
                //  break;
            }
            //if(nd[i][j]==9999.0)
            //  break;
            i++;
        }
        int len=0;
        for (int i1=0;i1<i;i1++)
        {
            for(int i2=0;i2<i1;i2++)    
            {
                dd[len][0]=nd[i1][1];
                dd[len][1]=nd[i1][0];
                dd[len][2]=nd[i2][1];
                dd[len][3]=nd[i2][2];
                dd[len][4]=spp;
                len++;
            }
            for(int i2=i1;i2<i;i2++)    
            {
                dd[len][0]=nd[i1][1];
                dd[len][1]=nd[i1][0];
                dd[len][2]=nd[i2][1];
                dd[len][3]=nd[i2][2];
                dd[len][4]=spp;
                len++;
            }
        }
        System.out.println("len is "+len);
        System.out.println("dd is ");
        for (int i1=0;i1<len;i1++)
        {
            for(j=0;j<5;j++)
            {
                System.out.printf("%.2f , ",dd[i1][j]);
            }
            System.out.println();
        }
        int lenn=0;
        int ratio[][]=new int[numberofratio][2];
        ratio[0][0]=1;ratio[0][1]=1;
        ratio[1][0]=1;ratio[1][1]=2;
        ratio[2][0]=2;ratio[2][1]=1;
        for(int j1=0;j1<numberofratio;j1++)
        {
            for (int i1=0;i1<len;i1++)
            {

                mai(dd[i1][0],dd[i1][1],dd[i1][2],dd[i1][3],dd[i1][4],ratio[j1][0],ratio[j1][1]);
                dda[i1][0][j1]=dd[i1][0];
                dda[i1][1][j1]=dd[i1][1];
                dda[i1][2][j1]=dd[i1][2];
                dda[i1][3][j1]=dd[i1][3];
                dda[i1][4][j1]=firstBreakeven;
                dda[i1][5][j1]=LastBreakeven;
                dda[i1][6][j1]=min;
                lenn++;
            }
        }
        System.out.println("len is "+len);
        System.out.println("lenn is "+lenn);

        for(int j1=0;j1<numberofratio;j1++)
        {
            for (int i1=0;i1<len;i1++)
            {
                for(j=0;j<7;j++)
                {
                    System.out.printf("%.2f , ",dda[i1][j][j1]);
                }
                System.out.println();

            }
        }
        double dif[]=new double[lenn];double dip[]=new double[lenn];int kk=0;
        for(int j1=0;j1<numberofratio;j1++)
        {
            for (int i1=0;i1<len;i1++)
            {
                dif[kk]=dda[i1][4][j1]-dda[i1][5][j1];//dda[i1][4][j1]-dda[i1][5][j1];
                if(dif[kk]>0)
                    dif[kk]*=(-1);
                dip[kk]=dda[i1][6][j1];
                kk++;
            }
        }   

        System.out.println("dif is ");
        for(int j1=0;j1<kk;j1++)
        {
            System.out.printf("index : "+j1+" %.2f , ",dif[j1]);
            System.out.println();
        }

        System.out.println("dip is ");
        for(int j1=0;j1<kk;j1++)
        {
            System.out.printf("index : "+j1+" %.2f , ",dip[j1]);
            System.out.println();
        }
        /////for indice code

        System.out.println("\n DDA real is");
        for(int j1=0;j1<numberofratio;j1++)
        {
            for (int i1=0;i1<len;i1++)
            {
                for(j=0;j<7;j++)
                {
                    System.out.printf("%.2f , ",dda[i1][j][j1]);
                }
                System.out.println();

            }
        }
        int n = dif.length;
        for (int i1 = 0; i1 < n - 1; i1++) {
            int maxIndex = i1;
            for (int j1=i1+1; j1 < n; j1++) {
                if (dif[j1] > dif[maxIndex]) {
                    maxIndex = j1;
                }
            }
            // Swap the found minimum element with the element at index i
            double temp = dif[i1];//System.out.println("Putting dif[i1] in temp "+dif[i1]+" i1 "+i1);
            dif[i1] = dif[maxIndex]; //System.out.println("Putting dif[i1] in dif[maxIndex] "+dif[maxIndex]+" maxindex "+maxIndex); 
            dif[maxIndex] = temp;//System.out.println("Putting temp in dif[maxIndex]"+ dif[maxIndex]+" maxindex "+maxIndex);

            for(int j1=0;j1<numberofratio;j1++)
            {
                for(j=0;j<7;j++)
                {
                    temp = dda[i1][j][j1];//System.out.println("Putting dda[i1][j][j1] in temp "+dda[i1][j][j1]+" i1 "+i1+" j "+j+" j1 "+j1+" temp "+temp);
                    dda[i1][j][j1] = dda[maxIndex][j][j1];//System.out.println("Putting dda[maxIndex][j][j1] in dda[i1][j][j1] "+dda[maxIndex][j][j1]+" maxindex "+maxIndex+" j "+j+" j1 "+j1);
                    dda[maxIndex][j][j1] = temp;//System.out.println("Putting temp"+temp+" in dda[maxIndex][j][j1] "+dda[maxIndex][j][j1]+" maxIndex "+maxIndex+" j "+j+" j1 "+j1);
                }
            }
        }
        System.out.println("\n DDA at less breakeven is");
        for(int j1=0;j1<numberofratio;j1++)
        {
            for (int i1=0;i1<len;i1++)
            {
                for(j=0;j<7;j++)
                {
                    System.out.printf("%.2f , ",dda[i1][j][j1]);
                }
                System.out.println();

            }
        }

        n = dip.length;
        for (int i1 = 0; i1 < n - 1; i1++) {
            int minIndex = i1;
            for (int j1=i1+1; j1 < n; j1++) {
                if (dif[j1] < dif[minIndex]) {
                    minIndex = j1;
                }
            }
            // Swap the found minimum element with the element at index i
            double temp = dip[i1]; //System.out.println("Putting dif[i1] in temp "+dif[i1]+" i1 "+i1);
            dif[i1] = dip[minIndex]; //System.out.println("Putting dif[i1] in dif[minIndex] "+dif[minIndex]+" minindex "+minIndex); 
            dip[minIndex] = temp;//System.out.println("Putting temp in dif[minIndex]"+ dif[minIndex]+" minindex "+minIndex);

            for(int j1=0;j1<numberofratio;j1++)
            {
                for(j=0;j<7;j++)
                {
                    temp = dda[i1][j][j1]; //System.out.println("Putting dda[i1][j][j1] in temp "+dda[i1][j][j1]+" i1 "+i1+" j "+j+" j1 "+j1);
                    dda[i1][j][j1] = dda[minIndex][j][j1];//System.out.println("Putting dda[minIndex][j][j1] in dda[i1][j][j1] "+dda[minIndex][j][j1]+" minIndex "+minIndex+" j "+j+" j1 "+j1);
                    dda[minIndex][j][j1] = temp;///System.out.println("Putting temp in dda[minIndex][j][j1] "+dda[minIndex][j][j1]+" minIndex "+minIndex+" j "+j+" j1 "+j1);
                }
            }
        }
        System.out.println("\n DDA at dip is");
        for(int j1=0;j1<numberofratio;j1++)
        {
            for (int i1=0;i1<len;i1++)
            {
                for(j=0;j<7;j++)
                {
                    System.out.printf("%.2f , ",dda[i1][j][j1]);
                }
                System.out.println();

            }
        }
    }
//1821, 15.42,1827, 21.62,1855, 48.73
//1828, 15.40,1835, 21.55,1855, 41.30
//1836, 15.37,1842, 21.48,1855, 33.88
//1843, 15.35,1849, 21.41, 1855, 26.47
//1849, 15.33,1855, 20.90. 1855, 20.90
//1854, 15.32,1861, 15.33, 1856, 15.33
//1854, 15.30,1868, 7.92, 1862, 7.92
//1854, 15.27,1875, 0.50, 1870, 0.50
//1854, 15.25,1883, -6.93, 1877, -6.93
/*
 782
 25800
 99.5
 618
 26000
 144.5
 465
 26200
 196
 330
 26400
 269
 241
 26600
 376
 175.5
 26800
 514
 125
 27000
 670
 94.5
 27200
 848
 *///26452   
/*
    1750
    111.7
    1950
    103.7
    1853.2
    1800
    58.1
    1925
    76.4
    1852.6
    1825
    41.4
    1900
    51.3
    1852.9
    1850
    15.2
    1875
    28.1
    1852.5
    1875
    5.2
    1850
    12.5
    1853.1
     */
    /*
    114.7
    1750
    0.8
    64.7
    1800
    2.3
    39.6
    1825
    5.1
    13.4
    1850
    13.1
    4.3
    1875
    29.1
    1.6
    1900
    51.8
    0.9
    1925
    77.5
    0.5
    1950
    106.3
    0.4
    1975
    128.8
    0.1
    2000
    160.8
     */
    /*
    105
    1750
    0.6
    62
    1800
    1.3
    36
    1825
    2.9
    13.3
    1850
    10.1
    4.2
    1875
    26.3
    1.9
    1900
    51.6
    1.1
    1925
    74.5
    0.6
    1950
    102.8
    0.5
    1975
    128.4
    0.1
    2000
    155.9
     */
    /*
    174.2
    1700
    0.3
    113.2
    1750
    0.2
    67.1
    1800
    1
    36.8
    1825
    2.1
    17.2
    1850
    6.2
    3.8
    1875
    19.9
    1.2
    1900
    51.4
    0.7
    1925
    68.7
    0.4
    1950
    100.5
    0.1
    2000
    147.3
     */
    /*
    174.2
    1700
    0.1
    109.4
    1750
    0.1
    69.8
    1800
    0.1
    49
    1825
    0.3
    9.1
    1850
    2.7
    1
    1875
    23.4
    0.3
    1900
    55.4
    0.1
    1925
    81.3
    0.1
    1950
    100.5
    0.1
    2000
    149.6
     */
    /*
    122.5
    1750
    0.8
    66.1
    1800
    2.2
    34.9
    1825
    5.4
    17.7
    1850
    13.2
    7.4
    1875
    28.4
    3
    1900
    50.3
    1.4
    1925
    74.5
    0.8
    1950
    102.3
    0.5
    2000
    149.3
    0.1
    2050
    200.5
     */

    /*
    102.7
    1750
    1
    70
    1800
    3.6
    33
    1825
    7
    16.5
    1850
    15.5
    6.6
    1875
    30.9
    2.8
    1900
    54
    1.2
    1925
    78.4
    0.6
    1950
    111.9
    0.4
    2000
    155
    0.2
    2050
    208.5
     */
    /*
    1315
    28000
    25
    750
    28500
    55
    345
    29000
    165
    120
    29500
    440
    70
    29750
    665
    45
    30000
    900
    30
    30250
    1295
    20
    30500
    1545
    10
    31000
    1955
    5
    31500
    2425
     */
    /*
    30.8
    210
    0.2
    24.8
    215
    0.2
    19.3
    220
    0.3
    14
    225
    0.4
    8.1
    230
    0.7
    4.2
    235
    1.7
    1.5
    240
    3.9
    0.6
    245
    8.4
    0.3
    250
    13.7
    0.2
    260
    24.5
     */
    /*
    8.73
    0.62
    0.85
    4.14
    0.68
    2.22
    2.160
    0.72
    4.260
    1.140
    0.76
    7.27
    0.67
    0.8
    23
    0.43
    0.84
    21
    0.36
    0.86
    22
    0.19
    0.9
    21
    0.21
    0.94
    26.993
    0.05
    1
    33.4
    0.20
    1.06
    40.04
    0.040
    1.12
    45.84
     */
    /*
    111.3
    1750
    0.9
    68.8
    1800
    3
    33.9
    1825
    6.4
    16.6
    1850
    14.3
    6.4
    1875
    29.5
    2.7
    1900
    52.6
    1.2
    1925
    76.9
    0.6
    1950
    111.9
    0.4
    2000
    155
    0.2
    2050
    208.5
     */
    /*
    124.9
    1750
    0.1
    84.3
    1800
    0.1
    57.5
    1825
    0.1
    30
    1850
    0.5
    1.8
    1875
    0.9
    0.1
    1900
    25.1
    0.7
    1925
    68.1
    0.1
    1950
    90.1
    0.1
    2000
    144.8
    0.1
    2050
    200.6
     */
    /*
    899.5
    1000
    0.1
    693.3
    1200
    0.1
    487.2
    1400
    0.1
    409.8
    1500
    0.3
    299.4
    1600
    0.2
    314.3
    1650
    0.4
    178.2
    1700
    0.3
    127.3
    1750
    0.4
    74.7
    1800
    1.4
    26.3
    1850
    5.3
    4.3
    1900
    44
    1
    1950
    97.2
    0.6
    2000
    149.8
    0.2
    2050
    210.3
    0.1
    2100
    330
    0.1
    2200
    362.2
    0.1
    2300
    495.4
    0.1
    2400
    579.6
    0.1
    2500
    687.5
    0.1
    2600
    744.7
    0.1
    3000
    1237
    0.1
    3400
    1551
    0.1
    3600
    1786
     *///1870
    /*
    11835
    18000
    5
    9935
    20000
    5
    7685
    22000
    5
    5660
    24000
    5
    4625
    25000
    5
    3650
    26000
    5
    2665
    27000
    5
    1530
    28000
    10
    550
    29000
    35
    170
    29500
    165
    45
    30000
    545
    15
    31000
    1665
    5
    32000
    2710
    5
    33000
    3675
    5
    24000
    4735
    5
    36000
    6810
    5
    40000
    11110
    5
    46000
    17385
    5
    50000
    21490
     */
    /*
    901.5
    1000
    0.1
    694.8
    1200
    0.1
    491.1
    1400
    0.1
    405.1
    1500
    0.1
    299.5
    1600
    0.2
    242.4
    1650
    0.1
    181.5
    1700
    0.1
    132.9
    1750
    0.3
    89.5
    1800
    0.6
    31.6
    1850
    3.9
    1
    1900
    48
    0.6
    1950
    86.6
    0.6
    2000
    143.4
    0.1
    2050
    197.6
    0.1
    2100
    253.5
    0.1
    2200
    345.9
    0.1
    2300
    472.7
    0.1
    2400
    561.6
    0.1
    2500
    650.2
    0.1
    2600
    744.8
    0.1
    3000
    1174
    0.1
    3400
    1551
    0.1
    3600
    1774

     */
    /*
     241.7
     1700
     0.5
     118.7
     1750
     0.7
     92.6
     1800
     1.5
     41.6
     1825
     3
     20.5
     1850
     7.5
     8.2
     1875
     19.9
     2.6
     1900
     62.2
     1.4
     1925
     66.9
     0.9
     1950
     102.6
     0.2
     2000
     144.7
     */
    /*
     187.5
     1700
     0.1
     122.9
     1750
     0.1
     77.5
     1800
     1
     44
     1825
     1.7
     20.9
     1850
     4.1
     6.8
     1875
     15.3
     1.9
     1900
     42
     0.8
     1925
     61.8
     0.7
     1950
     98.1
     0.2
     2000
     140.4
     *///1866
     /*
      121.2
      1750
      0.2
      99
      1775
      0.8
      67
      1800
      1.2
      44.7
      1825
      1.9
      21.1
      1850
      4.9
      6.8
      1875
      15.1
      2
      1900
      37.1
      1
      1925
      61.4
      0.6
      1950
      88.3
      0.2
      2000
      138.4
      *///1866
      /*
       107.9
       1760
       0.2
       87.6
       1780
       0.3
       67.5
       1800
       0.6
       47.8
       1820
       1.19
       29.10
       1840
       2.79
       13.5
       1860
       8.19
       5.09
       1880
       19.7
       1.69
       1900
       36.6
       0.6
       1920
       55.8
       0.30
       1940
       76
       0.3
       1960
       96.2
       0.2
       1980
       116.5
       *///1865
       /*
        131.3
        1750
        0.2
        104.6
        1775
        0.4
        82.7
        1800
        0.8
        54.9
        1825
        1.4
        26.4
        1850
        3.4
        6.8
        1875
        11.2
        1.8
        1900
        39.7
        1.0
        1925
        63
        0.6
        1950
        82.9
        0.2
        2000
        139.2
        *///1871
        /*
         112.9
         1760
         0.20
         92.7
         1780
         0.2
         72.4
         1800
         0.3
         52.5
         1820
         0.6
         33.1
         1840
         1.6
         16
         1860
         4.99
         5.8
         1880
         14.9
         1.69
         1900
         31.10
         0.6
         1920
         50.4
         0.3
         1940
         70.3
         0.2
         1960
         90.5
         0.2
         1980
         110.8
         *///1871
         /*
         135.9
         1700
         0.1
         85.1
         1750
         0.7
         44.2
         1800
         2.9
         17.4
         1825
         9.2
         4
         1850
         23.9
         1.1
         1875
         49.2
         0.6
         1900
         72.3
         0.5
         1925
         99.4
         0.1
         1950
         145.4
         0.1
         2000
         180.2
         *///1832
         /*
          94
          1740
          0.3
          73.5
          1760
          0.6
          53.8
          1780
          1.29
          44.6
          1790
          1.5
          35.4
          1800
          3.09
          26.5
          1810
          4.89
          18.5
          1820
          7.69
          12.5
          1830
          11.5
          8.09
          1840
          17
          4.89
          1850
          23.5
          2.79
          1860
          32.3
          1.59
          1870
          41.3
          0.89
          1880
          51.6
          0.4
          1900
          71.5
          0.3
          1920
          91.5
          0.2
          1940
          112
          0.2
          1960
          132.3
          0.2
          1980
          152.6
          *///1830
          /*
           244
           1600
           0.6
           194.3
           1650
           1.3
           144.9
           1700
           2.3
           93.7
           1750
           4.1
           47.9
           1800
           9.7
           14.8
           1850
           27.3
           3.4
           1900
           69.7
           1.3
           1950
           136.6
           0.8
           2000
           172.3
           0.6
           2050
           230.2
           0.3
           2100
           279.7
           0.3
           2200
           384.6
           0.3
           2400
           604.6
           0.3
           2600
           806.6
           *///1837.3
           /*
            2000
            28000
            10
            1175
            28500
            15
            1155
            28750
            25
            685
            29000
            40
            470
            29250
            75
            295
            29500
            155
            165
            29750
            285
            95
            30000
            465
            40
            30500
            950
            20
            31000
            1455
            *///29626
            /*
             125
             1750
             0.4
             72.3
             1800
             1.9
             47.1
             1825
             4.1
             17.3
             1850
             10.2
             6.4
             1875
             26
             3.3
             1900
             90
             1.5
             1925
             87.6
             0.8
             1950
             115.9
             0.1
             2000
             172.6
             0.1
             2050
             228.2
             *///1856 3 bin
            /*
             267.2
             1600
             0.2
             219.9
             1650
             0.4
             170.2
             1700
             0.9
             128
             1750
             2
             73.2
             1800
             4.9
             23.7
             1850
             16.7
             6
             1900
             50.2
             1.6
             1950
             136.6
             1
             2000
             171.5
             0.7
             2050
             227.8
             2100
             280
             0.3
             2100
             280
             0.3
             2200
             360.1
             2400
             569.3
             0.3
             2600
             788
             *///1855 4 bin
             /*
              113.2
              1750
              3
              65.1
              1800
              7.3
              44.1
              1825
              12.2
              27.6
              1850
              20.9
              15.2
              1875
              34.6
              8.4
              1900
              56
              5.2
              1925
              78.6
              3.1
              1950
              103.2
              1.5
              2000
              154
              0.8
              2050
              205.7
              *///1855.3 5 bin
              /*
               260.2
               1600
               0.2
               209.6
               1650
               0.3
               159.2
               1700
               0.6
               119.8
               1740
                1.8
                110.2
                1750
                2.2
                100.4
                1760
                2.5
                81.1
                1780
                3.3
                62.2
                1800
                4.7
                44.7
                1820
                7.5
                29.4
                1840
                12.6
                22.4
                1850
                16.4
                17.3
                1860
                21.1
                9.9
                1880
                34
                5.5
                1900
                49.9
                3.3
                1920
                68
                2.1
                1940
                87.2
                1.7
                1950
                96.9
                1.6
                1960
                106.9
                1.1
                2000
                146.8
                0.4
                2050
                196.7
                0.3
                2100
                247.3
                0.2
                2150
                298
                0.2
                2200
                348.5
                0.2
                2250
                399.2
                0.2
                2300
                450.2
                0.2
                2350
                500.9
                0.2
                2400
                551.6
                0.2
                2450
                602.4
               *///1856 4 del
               /*
                90.9
                1750
                0.6
                37.6
                1800
                2.8
                16.8
                1825
                6.9
                5
                1850
                20.7
                1.5
                1875
                46.8
                0.8
                1900
                71.7
                0.6
                1925
                96.2
                0.2
                1950
                122.3
                0.1
                2000
                177.8
                0.1
                2050
                232.9
                *///1834
                /*
                242.6
                1600
                0.1
                194.2
                1650
                0.2
                142.3
                1700
                0.4
                88.9
                1750
                1.1
                42.1
                1800
                4.7
                7.4
                1850
                21.7
                1.2
                1900
                69.7
                0.3
                1950
                136.6
                0.2
                2000
                170.8
                0.2
                2050
                227
                0.2
                2100
                275.7
                0.2
                2200
                386.4
                0.3
                2400
                594.5
                0.1
                2600
                805.7
                *///1836
                /*
                 5
                 15
                 9
                 12
                 23
                 9
                 22
                 4.5
                 3.3
                 2.2
                 4.1
                 5.14
                 16
                 10
                 17
                 7.69
                 33
                 */
                /*
                 91.3
                 1750
                 1.4
                 41.2
                 1800
                 1.9
                 20.7
                 1825
                 5.3
                 6.2
                 1850
                 18.2
                 1.9
                 1875
                 44.5
                 1
                 1900
                 76.8
                 1.3
                 1925
                 90.3
                 1.1
                 1950
                 116
                 0.9
                 2000
                 170.6
                 0.9
                 2050
                 223.2
                 *///1838
                 /*
                  260.9
                  1600
                  1.2
                  164.7
                  1700
                  2.3
                  115.5
                  1750
                  4.5
                  72.1
                  1800
                  10.5
                  26.3
                  1850
                  27.5
                  10.5
                  1900
                  61.2
                  4.9
                  1950
                  126.5
                  2.7
                  2000
                  162.7
                  1.5
                  2050
                  211.7
                  1
                  2100
                  266.1
                  0.6
                  2200
                  366.5
                  0.4
                  2300
                  475.9
                  *///1846
                  /*
                   506
                   28800
                   17.5
                   319
                   29000
                   34.5
                   167.5
                   29200
                   84.5
                   112.5
                   29300
                   132
                   74.5
                   29400
                   194.5
                   49.5
                   29500
                   271
                   34.5
                   29600
                   359
                   25
                   29700
                   452
                   19.5
                   29800
                   548
                   16.5
                   29900
                   645
                   14.5
                   30000
                   744
                   *///29269
                   /*
                    663
                    29200
                    31.5
                    496
                    29400
                    53.5
                    353
                    29600
                    102.5
                    220
                    29800
                    194.5
                    143
                    30000
                    318
                    95
                    30200
                    500
                    62.5
                    30400
                    655
                    *///29813
                    /*
                     591
                     29000
                     52
                     430
                     29200
                     91
                     291
                     29400
                     156.5
                     238
                     29500
                     202
                     192.5
                     29600
                     258
                     126
                     29800
                     406
                     81.5
                     30000
                     569
                     52.5
                     30200
                     742
                     *///29516
                     /*
                      396
                      29000
                      9
                      301
                      29100
                      20.5
                      212
                      29200
                      32
                      132.2
                      29300
                      59.5
                      71.7
                      29400
                      100
                      49.5
                      29500
                      174
                      30
                      29600
                      243
                      21.5
                      29700
                      348
                      *///29374
                      /*
                       805
                       28600
                       15.5
                       611
                       28800
                       24.5
                       430
                       29000
                       45.5
                       270
                       29200
                       88
                       151.5
                       29400
                       170.5
                       78.5
                       29600
                       299
                       30
                       29800
                       465
                       24.5
                       30000
                       651
                       *///29375
                       /*
                        824
                        28600
                        34
                        640
                        28800
                        52
                        470
                        29000
                        83
                        318
                        29200
                        137
                        201
                        29400
                        222
                        125
                        29600
                        348
                        80
                        29800
                        506
                        55
                        30000
                        682
                        *///29370
                        /*
                         351
                         29000
                         19.5
                         260
                         29100
                         30.5
                         180.5
                         29200
                         52.5
                         117
                         29300
                         90.5
                         73
                         29400
                         147.5
                         44
                         29500
                         222
                         31.5
                         29600
                         309
                         20
                         29700
                         400
                         *///29324
                         /*
                          770
                          28600
                          45
                          589
                          28800
                          60
                          427
                          29000
                          111
                          284
                          29200
                          191
                          183
                          29400
                          339
                          139
                          29500
                          417
                          119
                          29600
                          491
                          *///29230
}
/*
 
 if(lenc>0)
                System.out.printf(" | %.2f | \t",a[i][3]);
            if(lenp>0)
                System.out.printf(" | %.2f | \t",a[i][4]);
            if(lenl>0)
                System.out.printf(" | %.2f | \t",a[i][6]);
            if(lens>0)
                System.out.printf(" | %.2f | \t",a[i][7]);
            if(lenm>0)
                System.out.printf(" | %.2f | \t",a[i][8]);
            if(lenn>0)
                System.out.printf(" | %.2f | \t",a[i][9]); 
  
 for(int i2=0;i2<lenc;i2++)
                System.out.printf("C%.2f ,",callBreakeven[i2]);
            for(int i2=0;i2<lenp;i2++)
                System.out.printf("P%.2f",putBreakeven[i2]);
            for(int i2=0;i2<lenl;i2++)
                System.out.printf("L%.2f",LongBreakeven[i2]);
            for(int i2=0;i2<lens;i2++)
                System.out.printf("S%.2f",ShortBreakeven[i2]);
            for(int i2=0;i2<lenm;i2++)
                System.out.printf("M%.2f ,",callshortBreakeven[i2]);
            for(int i2=0;i2<lenn;i2++)
                System.out.printf("N%.2f ,",putshortBreakeven[i2]);
            System.out.printf("\t"); 

 if(-1000>a[i][5])
                    cnp_l=14;
                else if(-400>a[i][5])
                    cnp_l=13;
                else if(-100>a[i][5])
                    cnp_l=12;
                else if(-40>a[i][5])
                    cnp_l=11;
                else if(-10>a[i][5])
                    cnp_l=10;
                else if(-4>a[i][5])
                    cnp_l=9;
                else if(-1>a[i][5])
                    cnp_l=8;
                else if(0>a[i][5])
                    cnp_l=7;
                else if(1>a[i][5])
                    cnp_l=6;
                else if(4>a[i][5])
                    cnp_l=5;
                else if(10>a[i][5])
                    cnp_l=4;
                else if(40>a[i][5])
                    cnp_l=3;
                else if(100>a[i][5])
                    cnp_l=2;
                else if(400>a[i][5])
                    cnp_l=1;
                else if(1000>a[i][5])
                    cnp_l=0;
 
 
        else if(a[i][5]>-1)
        {
        if(cnp>a[i][5]) 
        cnp_l+=4;
        else
        {
        cnp_l+=4;
        cnp_l++;
        }
        }
         */
        
        /*for(int i=0;i<l;i++)
        {
            
            if(a[i][0]>0.025)
                break;
            System.out.println("At i "+i+" l "+l+" a[i][0] is "+a[i][0]+" pe "+pe);
            if(pe>a[i][0])
            {
                //System.out.println("I am in");
                if(-1000>a[i][5])
                    cnp_l=16;
                else if(-400>a[i][5])
                    cnp_l=15;
                else if(-100>a[i][5])
                    cnp_l=14;
                else if(-40>a[i][5])
                    cnp_l=13;
                else if(-10>a[i][5])
                    cnp_l=12;
                else if(-4>a[i][5])
                    cnp_l=11;
                else if(-1>a[i][5])
                    cnp_l=10;
                else if(-0.4>a[i][5])
                    cnp_l=9;
                else if(0>a[i][5])
                    cnp_l=8;
                else if(0.4>a[i][5])
                    cnp_l=7;
                else if(1>a[i][5])
                    cnp_l=6;
                else if(4>a[i][5])
                    cnp_l=5;
                else if(10>a[i][5])
                    cnp_l=4;
                else if(40>a[i][5])
                    cnp_l=3;
                else if(100>a[i][5])
                    cnp_l=2;
                else if(400>a[i][5])
                    cnp_l=1;
                else if(1000>a[i][5])
                    cnp_l=0;
                System.out.println("(In) a[i][5] "+a[i][5]+" cnp_l "+cnp_l);
                //pe+=0.0025;
            }
            else
            {
                dt[cnp_l][pe_l]="*";
                System.out.println("(Out) storing cnp_l "+cnp_l+" pe_l "+pe_l+"  dt[cnp_l][pe_l] "+dt[cnp_l][pe_l]);
                if(pe>-0.0005&&pe<0.0005)
                m_pe_l=pe_l;
                pe+=0.0007;
                pe_l++;
            }
        }*/  

// Create an array to store the indices
/*     Integer[] indices = new Integer[dif.length];
for (int i1 = 0; i1 < indices.length; i1++) {
indices[i1] = i1;
}

// Sort the indices array based on the values of the original array (in descending order)
Arrays.sort(indices, (a, b) -> Double.compare(dif[b], dif[a]));

// Print the sorted array
System.out.println("Original array in descending order:");
for (int i1 = 0; i1 < dif.length; i1++) {
System.out.println(dif[indices[i1]] + " ");
}

// Print the sorted indices
System.out.println("\nIndices of the sorted array in descending order:");
for (int index : indices) {
System.out.println(index + " ");
}
System.out.println("\nNEW DDA in terms of less breakeven");
for(int j1=0;j1<numberofratio;j1++)
{
for (int index : indices)
{
System.out.println("Index is "+index);
for(j=0;j<7;j++)
{
System.out.printf("%.2f , ",dda[index][j][j1]);System.out.print("("+dda[index][j][j1]+")");
}
System.out.println();
}
}
System.out.println("\nold DDA for breakeven is ");

for(int j1=0;j1<numberofratio;j1++)
{
for (int i1=0;i1<len;i1++)
{
for(j=0;j<7;j++)
{
System.out.printf("%.2f , ",dda[i1][j][j1]);
}
System.out.println();
}
}
 */
