import java.util.Scanner;

public class BreakevenCalculator {
    public static void main(String[] args) {
        // Example input array
        double[][] optionData = {
            {42400, 553},    
            {42500, 483}, 
            {42600, 418}, 
            {42700, 353}, 
            {42800, 295}, 
            {42900, 252}, 
            {43000, 207}, 
            {43100, 167}, 
            {43200, 136}, 
            {43300, 116}, 
                // Add more data as needed
        };

        double cur_pr=42859;
        
        double cur_pr_rn=((int)(cur_pr/100.0))*100;
        
        OptionChain ob32=new OptionChain();
        ob32.opcha(cur_pr_rn,10,2,2, 10, 5, 10);//ob32.opcha(inum,leng,sp_gpp,inv, tinv, volt, tvolt);

        int lengt=ob32.ndll_gam.length;
        double ndl_dd[][]=new double[lengt][3];

        double ndll_delta[][]=new double[lengt][5];
        int ndl_del_len=lengt;

        double ndll_gam[][]= new double[lengt][7];
        
        optionData=new double[lengt][2];

        for (int i1=0;i1<lengt;i1++)
        {
            ndll_gam[i1][0]=ob32.ndll_gam[i1][0];
            ndll_gam[i1][1]=ob32.ndll_gam[i1][1];
            ndll_gam[i1][2]=ob32.ndll_gam[i1][2];
            ndll_gam[i1][3]=ob32.ndll_gam[i1][3];
            ndll_gam[i1][4]=ob32.ndll_gam[i1][4];
            ndll_gam[i1][5]=ob32.ndll_gam[i1][5];
            ndll_gam[i1][6]=ob32.ndll_gam[i1][6];

            ndll_delta[i1][0]=ndll_gam[i1][1];
            ndll_delta[i1][1]=ndll_gam[i1][2];
            ndll_delta[i1][2]=ndll_gam[i1][3];
            ndll_delta[i1][3]=ndll_gam[i1][4];
            ndll_delta[i1][4]=ndll_gam[i1][5];

            ndl_dd[i1][0]=ob32.ndll_gam[i1][2];
            ndl_dd[i1][1]=ob32.ndll_gam[i1][3];
            ndl_dd[i1][2]=ob32.ndll_gam[i1][4];
            
            optionData[i1][0]=ob32.ndll_gam[i1][3];
            optionData[i1][1]=ob32.ndll_gam[i1][2];
        }

        int atm_ind_c=ob32.atm_index_c;
        int atm_ind_p=ob32.atm_index_c;
        double atm_extr_c=ndll_delta[atm_ind_c][1];
        double atm_extr_p=ndll_delta[atm_ind_p][3];

        System.out.println("\nNew ndl Chain:");
        for (int i = 0; i < lengt; i++) {
            System.out.printf("%.6f %.6f %.6f \n",ndl_dd[i][0],ndl_dd[i][1],ndl_dd[i][2]);
        }
        System.out.println("\n");
        
        double[][] breakevenData = calculateBreakeven(optionData);

        // Display the results
        System.out.println("StrikePrice\tOptionPrice\tRealext\t\t\tBreakeven\tdif\tdiftodip\tdiftodip1");
        for (int i = 0; i < breakevenData.length; i++) {
            double diff=breakevenData[i][1]-cur_pr;
            double diftodip=diff*optionData[i][1]/100;
            double iniCExtrinsicValue = optionData[i][1]-Math.max(0, cur_pr - breakevenData[i][0]);
            double diftodip1=diff*iniCExtrinsicValue/100;
            System.out.printf("%.2f\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f\n",
                    breakevenData[i][0], optionData[i][1],iniCExtrinsicValue,  breakevenData[i][1],diff,diftodip, diftodip1);
        }
    }

    public static void mainn(int nummm)
    {
        int numm=nummm;
        int num1=numm*2,num2=numm*4,num3=numm*6;
        int numtot=num1+num2+num3;
        int nummar=numtot*2;
        
        int num11=numm*3,num12=numm*6,num13=numm*9;
        int numtot1=num11+num12+num13;
        int nummar1=numtot1*2;
        System.out.printf("\n%d\n%d, %d, %d\n%d\n%d",numm,num1,num2,num3
        ,numtot,nummar);
        System.out.printf("\n%d, %d, %d\n%d\n%d",num11,num12,num13
        ,numtot1,nummar1);
    }
    // Function to calculate breakeven for each option
    public static double[][] calculateBreakeven(double[][] optionData) {
        int numRows = optionData.length;
        double[][] breakevenData = new double[numRows][2];

        for (int i = 0; i < numRows; i++) {
            double strikePrice = optionData[i][0];
            double callOptionPrice = optionData[i][1];

            // Calculate breakeven as the sum of strike price and call option price
            double breakeven = strikePrice + callOptionPrice;

            // Store the results in the new array
            breakevenData[i][0] = strikePrice;
            breakevenData[i][1] = breakeven;
        }

        return breakevenData;
    }
}
