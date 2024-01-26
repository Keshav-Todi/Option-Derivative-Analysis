import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class OptionChain {
    double ndll_gam[][];
    int atm_index_c=0,atm_index_p=0;
    public double[][] opch(double ndll[][],int i_l, double sspp) {
        double atmDelta = 0.5;
        double deltaChangeFactor = 1.17; // Exponential change factor
        double deltainvChangeFactor = 0.71; // Exponential change factor
        int numOptions = 10; // Number of options in the chain
        int len=0;
        //double sspp=29450;

        double[][] callOptionChain = new double[400][3];
        double[][] gammaOptionChain = new double[400][3];
        //double[][] putOptionChain = new double[numOptions][2];

        int atmIndex = 0;//numOptions / 2; // Index of the ATM option
        double delta = atmDelta;

        for (double i = -0.05; i <= 0.05; i+=0.005) {
            callOptionChain[len][1] = sspp * (1 + i);
            gammaOptionChain[len][1] = sspp * (1 + i);
            if(i>-0.003&&i<0.003)
                atmIndex=len;
            len++;
        }

        // Print the option chain
        /*System.out.println("\nOption Chain: atmIndex "+atmIndex);
        for (int i = 0; i < len; i++) {
        System.out.println("i "+i+" "+callOptionChain[i][2]);
        }
         */

        numOptions=len;

        /*
        // Calculate delta values for call options (ATM to ITM)
        for (int i = atmIndex - 1; i >= 0; i--) {
        delta *= deltaChangeFactor;
        if(delta>=1)
        delta=1;
        callOptionChain[i][0] = delta;
        gammaOptionChain[i][0] = 1-delta;
        //callOptionChain[i][1] = 100.0 - i * 10; // Example strike price, adjust as needed
        }
         */
        // Set ATM delta value for call options
        callOptionChain[atmIndex][0] = atmDelta;
        gammaOptionChain[atmIndex][0] = 1-atmDelta;
        //callOptionChain[atmIndex][1] = 100.0; // Example ATM strike price

        // Calculate delta values for call options (ATM to OTM)
        delta = atmDelta;
        for (int i = atmIndex + 1; i < numOptions; i++) {
            delta *= deltainvChangeFactor;
            if(delta<=0)
                delta=0;
            callOptionChain[i][0] = delta;
            gammaOptionChain[i][0] = delta;
            //callOptionChain[i][1] = 100.0 + (i - atmIndex) * 10; // Example strike price, adjust as needed

        }

        // (Wrong for right calc)Calculate delta values for call options (ATM to ITM)
        delta = atmDelta;
        for (int i = atmIndex - 1; i >= 0; i--) {
            delta *= deltainvChangeFactor;
            if(delta<=0)
                delta=0;
            callOptionChain[i][0] = delta;
            gammaOptionChain[i][0] = delta;
            //callOptionChain[i][1] = 100.0 - i * 10; // Example strike price, adjust as needed
        }

        // Calculate delta values for put options (ATM to OTM)
        delta = atmDelta;
        for (int i = atmIndex - 1; i >= 0; i--) {
            delta *= deltainvChangeFactor;
            if(delta<=0)
                delta=0;
            callOptionChain[i][2] = -delta; // Invert delta for put options
            gammaOptionChain[i][2] = delta;
            //putOptionChain[i][1] = 100.0 - i * 10; // Example strike price, adjust as needed

        }

        // Set ATM delta value for put options
        callOptionChain[atmIndex][2] = -atmDelta;
        gammaOptionChain[atmIndex][2] = 1-atmDelta;
        //putOptionChain[atmIndex][1] = 100.0; // Example ATM strike price

        // Calculate delta values for put options (ATM to ITM)
        delta = atmDelta;
        for (int i = atmIndex + 1; i < numOptions; i++) {
            delta *= deltainvChangeFactor;
            if(delta<=0)
                delta=0;
            callOptionChain[i][2] = -delta; // Invert delta for put options
            gammaOptionChain[i][2] = delta;
            //putOptionChain[i][1] = 100.0 + (i - atmIndex) * 10; // Example strike price, adjust as needed

        }
        /*
        // Calculate delta values for put options (ATM to ITM)
        delta = atmDelta;
        for (int i = atmIndex + 1; i < numOptions; i++) {
        delta *= deltaChangeFactor;
        if(delta>=1)
        delta=1;
        callOptionChain[i][2] = -delta; // Invert delta for put options
        gammaOptionChain[i][2] = 1-delta;
        //putOptionChain[i][1] = 100.0 + (i - atmIndex) * 10; // Example strike price, adjust as needed

        }
         */
        for (int i = 0; i < numOptions; i++) {
            gammaOptionChain[i][0]=Math.pow(gammaOptionChain[i][0],2)/70;
            gammaOptionChain[i][2]=Math.pow(gammaOptionChain[i][2],2)/70;
        }

        // Print the call option chain
        /*
        System.out.println("Call Option Chain:");
        for (int i = 0; i < numOptions; i++) {
        System.out.printf("%.2f %.2f %.2f\n",callOptionChain[i][0], callOptionChain[i][1], callOptionChain[i][2]);
        }
         */
        //double spp= 29516;
        //double ndll[][]={{291,29400,156.5},{238,29500,202},{192.5,29600,258}};
        //int i_l=ndll.length;

        /*
        for (int i = 0; i < numOptions; i++) {
        if(i==0||i==(numOptions-1))
        {

        }
        else
        {
        gammaOptionChain[i][0]=(callOptionChain[i-1][0]-callOptionChain[i+1][0])/(callOptionChain[i-1][1]-callOptionChain[i+1][1]);
        gammaOptionChain[i][2]=(callOptionChain[i-1][2]-callOptionChain[i+1][2])/(callOptionChain[i-1][1]-callOptionChain[i+1][1]);
        }
        gammaOptionChain[i][1]=callOptionChain[i][1];

        }
        gammaOptionChain[0][0]=gammaOptionChain[1][0];
        gammaOptionChain[0][2]=gammaOptionChain[1][2];
        gammaOptionChain[numOptions-1][0]=gammaOptionChain[numOptions-2][0];
        gammaOptionChain[numOptions-1][2]=gammaOptionChain[numOptions-2][2];
         */
        /*
        deltaChangeFactor = 1.17; // Exponential change factor
        deltainvChangeFactor = 0.71; // Exponential change factor

        double delta1 = gammaOptionChain[atmIndex][0];
        double delta2 = gammaOptionChain[atmIndex][2];

        // Calculate delta values for call options (ATM to ITM)
        for (int i = atmIndex - 1; i >= 0; i--) {
        delta1 *= deltaChangeFactor;
        if(delta>=1)
        delta=1;
        callOptionChain[i][0] = delta;
        //callOptionChain[i][1] = 100.0 - i * 10; // Example strike price, adjust as needed
        }

        // Set ATM delta value for call options
        callOptionChain[atmIndex][0] = atmDelta;
        //callOptionChain[atmIndex][1] = 100.0; // Example ATM strike price

        // Calculate delta values for call options (ATM to OTM)
        delta = atmDelta;
        for (int i = atmIndex + 1; i < numOptions; i++) {
        delta *= deltainvChangeFactor;
        if(delta<=0)
        delta=0;
        callOptionChain[i][0] = delta;
        //callOptionChain[i][1] = 100.0 + (i - atmIndex) * 10; // Example strike price, adjust as needed

        }

        // Calculate delta values for put options (ATM to OTM)
        delta = atmDelta;
        for (int i = atmIndex - 1; i >= 0; i--) {
        delta *= deltainvChangeFactor;
        if(delta<=0)
        delta=0;
        callOptionChain[i][2] = -delta; // Invert delta for put options
        //putOptionChain[i][1] = 100.0 - i * 10; // Example strike price, adjust as needed

        }

        // Set ATM delta value for put options
        callOptionChain[atmIndex][2] = -atmDelta;
        //putOptionChain[atmIndex][1] = 100.0; // Example ATM strike price

        // Calculate delta values for put options (ATM to ITM)
        delta = atmDelta;
        for (int i = atmIndex + 1; i < numOptions; i++) {
        delta *= deltaChangeFactor;
        if(delta>=1)
        delta=1;
        callOptionChain[i][2] = -delta; // Invert delta for put options
        //putOptionChain[i][1] = 100.0 + (i - atmIndex) * 10; // Example strike price, adjust as needed

        }

         */
        double ndll_del[][]= new double[i_l][5];
        ndll_gam= new double[i_l][7];

        for (int i = 0; i < i_l; i++) {

            for(int k1=0;k1<numOptions;k1++)
            {
                if(callOptionChain[k1][1]<=ndll[i][1])
                {
                    ndll_del[i][0]=callOptionChain[k1][0];
                    ndll_del[i][1]=ndll[i][0];
                    ndll_del[i][2]=ndll[i][1];
                    ndll_del[i][3]=ndll[i][2];
                    ndll_del[i][4]=callOptionChain[k1][2];

                    ndll_gam[i][0]=gammaOptionChain[k1][0];  
                    ndll_gam[i][1]=callOptionChain[k1][0];
                    ndll_gam[i][2]=ndll[i][0];
                    ndll_gam[i][3]=ndll[i][1];
                    ndll_gam[i][4]=ndll[i][2];
                    ndll_gam[i][5]=callOptionChain[k1][2];    
                    ndll_gam[i][6]=gammaOptionChain[k1][2];  

                    /*if(ndll_del[i][0]>0.4&&ndll_del[i][0]<0.6)//callOptionChain[k1][0]>0.4&&callOptionChain[k1][0]<0.6)
                    {
                    System.out.printf("Above i %d  ndll[i][1] %f  k1 %d ct[k1][0] %f ct[k1][1] %f ct[k1][2] %f\n"
                    ,i,ndll[i][1],k1,callOptionChain[k1][0],callOptionChain[k1][1],callOptionChain[k1][2]);  
                    atm_index=i;
                    }
                    else
                    System.out.printf("Upppp i %d  ndll[i][1] %f  k1 %d ct[k1][0] %f ct[k1][1] %f ct[k1][2] %f\n"
                    ,i,ndll[i][1],k1,callOptionChain[k1][0],callOptionChain[k1][1],callOptionChain[k1][2]);*/  
                    //break;
                }
                if(k1==0&&callOptionChain[k1][1]>=ndll[i][1])
                {
                    ndll_del[i][0]=callOptionChain[k1][0];
                    ndll_del[i][1]=ndll[i][0];
                    ndll_del[i][2]=ndll[i][1];
                    ndll_del[i][3]=ndll[i][2];
                    ndll_del[i][4]=callOptionChain[k1][2];

                    ndll_gam[i][0]=gammaOptionChain[k1][0];  
                    ndll_gam[i][1]=callOptionChain[k1][0];
                    ndll_gam[i][2]=ndll[i][0];
                    ndll_gam[i][3]=ndll[i][1];
                    ndll_gam[i][4]=ndll[i][2];
                    ndll_gam[i][5]=callOptionChain[k1][2];    
                    ndll_gam[i][6]=gammaOptionChain[k1][2];  

                    /*if(ndll_del[i][1]>0.4&&ndll_del[i][1]<0.6)//(callOptionChain[k1][0]>0.4&&callOptionChain[k1][0]<0.6)
                    {
                    System.out.printf("Below i %d  ndll[i][1] %f  k1 %d ct[k1][0] %f ct[k1][1] %f ct[k1][2] %f\n"
                    ,i,ndll[i][1],k1,callOptionChain[k1][0],callOptionChain[k1][1],callOptionChain[k1][2]);        
                    atm_index=i;
                    }
                    else
                    System.out.printf("Downn i %d  ndll[i][1] %f  k1 %d ct[k1][0] %f ct[k1][1] %f ct[k1][2] %f\n"
                    ,i,ndll[i][1],k1,callOptionChain[k1][0],callOptionChain[k1][1],callOptionChain[k1][2]);*/  

                    //System.out.println("Here callOptionChain "+callOptionChain[k1][1]+" ndll "+ndll[i][1]);
                    //break;
                }
            }

            if(ndll_del[i][0]>0.45&&ndll_del[i][0]<0.6)//callOptionChain[k1][0]>0.4&&callOptionChain[k1][0]<0.6)
            {
                //System.out.printf("Above i %d  ndll[i][1] %f  k1 %d ct[k1][0] %f ct[k1][1] %f ct[k1][2] %f\n"
                //,i,ndll[i][1],k1,callOptionChain[k1][0],callOptionChain[k1][1],callOptionChain[k1][2]);  
                atm_index_c=i;
            }
            if(ndll_del[i][4]<-0.45&&ndll_del[i][4]>-0.6)//callOptionChain[k1][0]>0.4&&callOptionChain[k1][0]<0.6)
            {
                //System.out.printf("Above i %d  ndll[i][1] %f  k1 %d ct[k1][0] %f ct[k1][1] %f ct[k1][2] %f\n"
                //,i,ndll[i][1],k1,callOptionChain[k1][0],callOptionChain[k1][1],callOptionChain[k1][2]);  
                atm_index_p=i;
            }
            ///System.out.printf("%.2f %.2f %.2f\n",callOptionChain[i][0], callOptionChain[i][1], callOptionChain[i][2]);
        }

        System.out.println("\nNormal Delta Option Chain:");
        for (int i = 0; i < numOptions; i++) {
            System.out.printf("%.2f %.2f %.2f\n",callOptionChain[i][0],callOptionChain[i][1],callOptionChain[i][2]);
        }
        System.out.println("\n");

        System.out.println("\nNormal Gamma Option Chain:");
        for (int i = 0; i < numOptions; i++) {
            System.out.printf("%.6f %.6f %.6f\n",gammaOptionChain[i][0],gammaOptionChain[i][1],gammaOptionChain[i][2]);
        }
        System.out.println("\n");

        System.out.println("\nDelta Option Chain:");
        for (int i = 0; i < i_l; i++) {
            System.out.printf("%.2f %.2f %.2f %.2f %.2f\n",ndll_del[i][0],ndll_del[i][1],ndll_del[i][2],ndll_del[i][3],ndll_del[i][4]);
        }
        System.out.println("\n");

        System.out.println("\nGamma Option Chain:");
        for (int i = 0; i < i_l; i++) {
            System.out.printf("%.6f %.6f %.6f %.6f %.6f %.6f %.6f\n",ndll_gam[i][0],ndll_gam[i][1],ndll_gam[i][2],ndll_gam[i][3],ndll_gam[i][4],ndll_gam[i][5],ndll_gam[i][6]);
        }
        System.out.println("\n");

        System.out.println("\nATM index c is");
        System.out.printf("%d \n",atm_index_c);
        System.out.println("\nATM index p is");
        System.out.printf("%d \n",atm_index_p);
        System.out.println("\n");

        return ndll_del;
    }

    public static void main()
    {
        OptionChain ob31=new OptionChain();
        //ob31.opcha(inum,7);
        int odd[]={5,-3,58,66,1};
        ob31.dataopt(29000,7,1,odd,2,10,5,10);
    }

    double[][] od_list;
    public void dataopt(double inum, int lt,double sp_gp, int od[],int inv, int tinv, int volt, int tvolt)
    {
        int len=od.length;
        for(int i=0;i<len;i++)
            System.out.print(od[i]+", ");
        System.out.println();

        od_list= new double[len][4];
        String[] od_s= new String[len];
        opcha(inum,lt,sp_gp,inv,tinv,volt,tvolt);//opcha(inum,lt,sp_gp,2,10,5,10);
        for(int i=0;i<len;i++)
        {
            if(od[i]>40)
            {
                int index=od[i]-60;
                index=lt-index;
                od_list[i][0]=ndll_gam[index][3];
                od_list[i][1]=ndll_gam[index][4];
                od_list[i][2]=ndll_gam[index][5];
                od_list[i][3]=ndll_gam[index][6];
                od_s[i]="P";
            }
            else
            {
                int index=lt+od[i];
                od_list[i][0]=ndll_gam[index][3];
                od_list[i][1]=ndll_gam[index][2];
                od_list[i][2]=ndll_gam[index][1];
                od_list[i][3]=ndll_gam[index][0];
                od_s[i]="C";
            }
        }

        for(int i=0;i<len;i++)
            System.out.printf("%s %d %.2f, %.2f, %f, %f\n",od_s[i],od[i],od_list[i][0],od_list[i][1],od_list[i][2],od_list[i][3]);
    }

    double ndl_op[][];
    public void opcha(double inpnum, int countt,double sp_gp,int inv, int tinv, int volt, int tvolt) {
        // Input number
        double inputNumber = inpnum;//29000;
        double set_para=0.5/100.0;
        if(inputNumber>10)
        {
            double dd=inputNumber*1/100.0;

            int lenn=(Integer.toString((int)inputNumber)).length();//(Integer.toString((int)dd)).length();
            String ss=((Integer.toString((int)inputNumber))).charAt(0)+"";//Integer.toString((int)dd)).charAt(0)+"";
            int nn=Integer.valueOf(ss);

            int nn1=nn*0+(int)Math.pow(10,lenn-3);
            nn1=(int)((double)nn1*sp_gp);
            System.out.println("dd "+dd+" lenn "+lenn+" ss "+ss+" nn "+nn+" nn1 "+nn1);

            double[] numOptionChain = new double[400];
            double nn_t=inputNumber;
            int ln=0,cntt=0;double per_change=3;double num_opt=countt;
            while(cntt<num_opt)//nn_t>inputNumber*(100-per_change)/100)
            {
                nn_t=nn_t-nn1;
                numOptionChain[ln++]=nn_t;cntt++;
            }
            int ln_d=ln;cntt=0;nn_t=inputNumber;
            numOptionChain[ln++]=nn_t;
            while(cntt<num_opt)//nn_t<inputNumber*(100+per_change)/100)
            {
                nn_t=nn_t+nn1;
                numOptionChain[ln++]=nn_t;cntt++;
            }
            for(int i=0;i<ln;i++)
                System.out.println(numOptionChain[i]);
            double[][] numOptChain = new double[ln][3];
            int lent=0;

            for(int i=ln_d-1;i>=0;i--)
            {numOptChain[lent][1]=numOptionChain[i];numOptChain[lent][0]=numOptionChain[i]/100;numOptChain[lent++][2]=numOptionChain[i]/100;}

            numOptChain[lent][1]=numOptionChain[ln_d];numOptChain[lent][0]=numOptionChain[ln_d]*set_para*sp_gp;numOptChain[lent++][2]=numOptionChain[ln_d]*set_para;

            for(int i=ln_d+1;i<ln;i++)
            {numOptChain[lent][1]=numOptionChain[i];numOptChain[lent][0]=numOptionChain[i]/100;numOptChain[lent++][2]=numOptionChain[i]/100;}

            System.out.println();
            for(int i=0;i<lent;i++)
            {
                for(int j=0;j<3;j++)
                    System.out.print(numOptChain[i][j]+" ");
                System.out.println();
            }

            double[][] ndll_delta=opch(numOptChain,lent,inputNumber);
            int ndl_del_len=lent;

            for(int i1=0;i1<lent;i1++)
            {
                calculateCallOptionPrice(numOptChain[ln_d][1]*2-ndll_gam[i1][3],numOptChain[ln_d][1], numOptChain[ln_d][1], numOptChain[ln_d][0],
                    ndll_gam[ln_d][1], ndll_gam[ln_d][0],numOptChain[ln_d][0]-Math.max(0, inputNumber - numOptChain[ln_d][1]),inv,tinv,volt,tvolt
                , numOptChain[ln_d][0]-Math.max(0, inputNumber - numOptChain[ln_d][1]),sp_gp);

                ndll_gam[i1][2]=newCallOptionPrice;

                calculatePutOptionPrice(numOptChain[ln_d][1]*2-ndll_gam[i1][3],numOptChain[ln_d][1], numOptChain[ln_d][1], numOptChain[ln_d][0],
                    ndll_gam[ln_d][5], ndll_gam[ln_d][6],numOptChain[ln_d][0]-Math.max(0, inputNumber - numOptChain[ln_d][1]),inv,tinv,volt,tvolt,
                    numOptChain[ln_d][0]-Math.max(0, inputNumber - numOptChain[ln_d][1]),sp_gp);

                ndll_gam[i1][4]=newPutOptionPrice;
            }

            System.out.println("\nNew Gamma Option Chain:");
            for (int i = 0; i < lent; i++) {
                System.out.printf("%.6f %.6f %.6f %.6f %.6f %.6f %.6f\n",ndll_gam[i][0],ndll_gam[i][1],ndll_gam[i][2],ndll_gam[i][3],ndll_gam[i][4],ndll_gam[i][5],ndll_gam[i][6]);
            }
            System.out.println("\n");

            /*
            System.out.println("\nNew Option Chain:");
            for (int i = 0; i < lent; i++) {
            System.out.printf("%.6f %.6f %.6f \n",ndll_gam[i][2],ndll_gam[i][3],ndll_gam[i][4]);
            }
             */
            System.out.println("\n");

        }
    }

    double newIntrinsicValue=0, newExtrinsicValue=0, newCallOptionPrice=0, newPutOptionPrice=0;
    public void calculateCallOptionPrice(double currentStockPrice, double callStrikePrice,double oldStockPrice, double oldcallprice
    , double delta_pr,double gamma_pr, double iniExtrinsicValue
    , double interval, double tot_interval,double volt, double tot_volt, double atm_ext, double sp_gp1) {
        // Calculate new intrinsic value (maximum of 0 and current stock price - call strike price)
        newIntrinsicValue=0; newExtrinsicValue=0; newCallOptionPrice=0;

        double pr_iniExtrinsicValue=iniExtrinsicValue;
        double curr_dif=0,pr_change=0;
        newIntrinsicValue = Math.max(0, currentStockPrice - callStrikePrice);
        atm_ext = atm_ext*(tot_interval-interval)/tot_interval;
        atm_ext = atm_ext*(volt)/(tot_volt/2);
        if(oldStockPrice!=currentStockPrice)
        {
            curr_dif=currentStockPrice-oldStockPrice;
            //pr_change=curr_dif*delta_pr+(1/2.0*Math.abs(Math.pow(curr_dif,2))*gamma_pr);
            //if(curr_dif<0)
            //  pr_change=curr_dif*delta_pr-(1/2.0*Math.abs(Math.pow(curr_dif,2))*gamma_pr);
            //newCallOptionPrice=oldcallprice+pr_change;
            if(curr_dif<=0)
                newCallOptionPrice=atm_ext*Math.pow(currentStockPrice/oldStockPrice,60/sp_gp1);
            if(curr_dif>0)
                newCallOptionPrice=newIntrinsicValue+atm_ext*Math.pow(oldStockPrice/currentStockPrice,70/sp_gp1);
            //newCallOptionPrice=Math.max(newCallOptionPrice, newIntrinsicValue);
            //pr_iniExtrinsicValue = newCallOptionPrice - newIntrinsicValue;
        }
        else
            newCallOptionPrice=atm_ext;//*sp_gp1;

        //newExtrinsicValue = pr_iniExtrinsicValue*(tot_interval-interval)/tot_interval;
        //if(newIntrinsicValue>0)
        //if(newIntrinsicValue/currentStockPrice*100<2)
        //  newExtrinsicValue = Math.max(0,newExtrinsicValue*(1-(newIntrinsicValue*100)/currentStockPrice));

        //newCallOptionPrice= newIntrinsicValue + newExtrinsicValue;

        /*System.out.println();
        System.out.printf("C %.2f %.2f %.2f %.2f %.2f %.2f %.2f %.2f (%.2f) %.2f %.2f (%.2f) (%.2f) (%.2f) \t\t\t",currentStockPrice,oldStockPrice,
        callStrikePrice,oldcallprice,delta_pr,interval, tot_interval, curr_dif, pr_change,iniExtrinsicValue, pr_iniExtrinsicValue, 
        newExtrinsicValue, newIntrinsicValue, newCallOptionPrice);
        System.out.println();*/

    }

    public void calculatePutOptionPrice(double currentStockPrice, double putStrikePrice, double oldStockPrice, double oldputprice
    , double delta_pr,double gamma_pr, double iniExtrinsicValue
    , double interval, double tot_interval,double volt, double tot_volt, double atm_ext, double sp_gp1) {
        // Calculate new intrinsic value (maximum of 0 and current stock price - call strike price)
        newIntrinsicValue=0; newExtrinsicValue=0; newCallOptionPrice=0;

        double pr_iniExtrinsicValue=iniExtrinsicValue;
        double curr_dif=0,pr_change=0;
        newIntrinsicValue = Math.max(0, putStrikePrice - currentStockPrice);
        atm_ext = atm_ext*(tot_interval-interval)/tot_interval;
        atm_ext = atm_ext*(volt)/(tot_volt/2);
        if(oldStockPrice!=currentStockPrice)
        {
            curr_dif=currentStockPrice-oldStockPrice;
            //pr_change=curr_dif*delta_pr-(1/2.0*Math.abs(Math.pow(curr_dif,2))*gamma_pr);
            //if(curr_dif<0)
            //  pr_change=curr_dif*delta_pr+(1/2.0*Math.abs(Math.pow(curr_dif,2))*gamma_pr);
            //newPutOptionPrice=oldputprice+pr_change;
            if(curr_dif>=0)
                newPutOptionPrice=atm_ext*Math.pow(oldStockPrice/currentStockPrice,60/sp_gp1);
            if(curr_dif<0)
                newPutOptionPrice=newIntrinsicValue+atm_ext*Math.pow(currentStockPrice/oldStockPrice,70/sp_gp1);
            //newPutOptionPrice=Math.max(newPutOptionPrice, newIntrinsicValue);
            //pr_iniExtrinsicValue = newPutOptionPrice - newIntrinsicValue;
        }
        else
            newPutOptionPrice=atm_ext;//*sp_gp1;

        //newExtrinsicValue = pr_iniExtrinsicValue*(tot_interval-interval)/tot_interval;
        //if(newIntrinsicValue>0)
        //if(newIntrinsicValue/currentStockPrice*100<2)
        //  newExtrinsicValue = Math.max(0,newExtrinsicValue*(1-(newIntrinsicValue*100)/currentStockPrice));

        //newPutOptionPrice= newIntrinsicValue + newExtrinsicValue;
        /*System.out.println();
        System.out.printf("P %.2f %.2f %.2f %.2f %.2f %.2f %.2f %.2f %.2f %.2f %.2f %.2f %.2f %.2f \t\t\t",currentStockPrice,oldStockPrice,
        putStrikePrice,oldputprice,delta_pr,interval, tot_interval, curr_dif, pr_change, iniExtrinsicValue, pr_iniExtrinsicValue, 
        newExtrinsicValue, newIntrinsicValue, newPutOptionPrice);
        System.out.println();*/

    }

    /*

    public void calculateCallOptionPrice(double currentStockPrice, double callStrikePrice,double oldStockPrice, double oldcallprice, double delta_pr,double gamma_pr, double iniExtrinsicValue, double interval, double tot_interval) {
    // Calculate new intrinsic value (maximum of 0 and current stock price - call strike price)
    newIntrinsicValue=0; newExtrinsicValue=0; newCallOptionPrice=0;

    double pr_iniExtrinsicValue=iniExtrinsicValue;
    double curr_dif=0,pr_change=0,i2=0, new_del=0;int contt=0;
    newIntrinsicValue = Math.max(0, currentStockPrice - callStrikePrice);
    if(oldStockPrice!=currentStockPrice)
    {
    curr_dif=currentStockPrice-oldStockPrice;
    pr_change=curr_dif*delta_pr+(1/2.0*Math.abs(Math.pow(curr_dif,2))*gamma_pr);
    if(curr_dif<0)
    pr_change=curr_dif*delta_pr-(1/2.0*Math.abs(Math.pow(curr_dif,2))*gamma_pr);
    newCallOptionPrice=oldcallprice+pr_change;

    if(newCallOptionPrice<0)
    newCallOptionPrice=0;
    newCallOptionPrice=Math.max(newCallOptionPrice, newIntrinsicValue);
    pr_iniExtrinsicValue = newCallOptionPrice - newIntrinsicValue;
    }

    // Calculate new time decay using the Black-Scholes formula for call option
    newExtrinsicValue = pr_iniExtrinsicValue*(tot_interval-interval)/tot_interval;
    if(newIntrinsicValue>0)
    //if(newIntrinsicValue/currentStockPrice*100<2)
    newExtrinsicValue = Math.max(0,newExtrinsicValue*(1-(newIntrinsicValue*100)/currentStockPrice));

    //  newExtrinsicValue = Math.min(Math.max(0,newExtrinsicValue-newIntrinsicValue),newExtrinsicValue*(1-(newIntrinsicValue*100)/currentStockPrice));//newExtrinsicValue = Math.max(0,newExtrinsicValue-newIntrinsicValue);
    //else
    //  newExtrinsicValue = 0;
    // Add new intrinsic value and new time decay to get the updated option price
    newCallOptionPrice= newIntrinsicValue + newExtrinsicValue;
    //newCallOptionPrice*=(100-11)/100;
    //System.out.printf("C %.2f %.2f %.2f %.2f %.2f %.2f %.2f %.2f %.2f %.2f %.2f %.2f %.2f %.2f \t\t\t",currentStockPrice,oldStockPrice,
    callStrikePrice,oldcallprice,delta_pr,interval, tot_interval, curr_dif, pr_change,iniExtrinsicValue, pr_iniExtrinsicValue, 
    newExtrinsicValue, newIntrinsicValue, newCallOptionPrice);
    }

    public void calculatePutOptionPrice(double currentStockPrice, double putStrikePrice, double oldStockPrice, double oldputprice, double delta_pr,double gamma_pr, double iniExtrinsicValue, double interval, double tot_interval) {
    // Calculate new intrinsic value (maximum of 0 and current stock price - call strike price)
    newIntrinsicValue=0; newExtrinsicValue=0; newCallOptionPrice=0;

    double pr_iniExtrinsicValue=iniExtrinsicValue;
    double curr_dif=0,pr_change=0;
    newIntrinsicValue = Math.max(0, putStrikePrice - currentStockPrice);
    if(oldStockPrice!=currentStockPrice)
    {
    curr_dif=currentStockPrice-oldStockPrice;
    pr_change=curr_dif*delta_pr-(1/2.0*Math.abs(Math.pow(curr_dif,2))*gamma_pr);
    if(curr_dif<0)
    pr_change=curr_dif*delta_pr+(1/2.0*Math.abs(Math.pow(curr_dif,2))*gamma_pr);
    newPutOptionPrice=oldputprice+pr_change;
    if(newPutOptionPrice<=0)
    newPutOptionPrice=0;
    newPutOptionPrice=Math.max(newPutOptionPrice, newIntrinsicValue);
    pr_iniExtrinsicValue = newPutOptionPrice - newIntrinsicValue;
    }

    // Calculate new time decay using the Black-Scholes formula for call option
    newExtrinsicValue = pr_iniExtrinsicValue*(tot_interval-interval)/tot_interval;
    if(newIntrinsicValue>0)
    //if(newIntrinsicValue/currentStockPrice*100<2)
    newExtrinsicValue = Math.max(0,newExtrinsicValue*(1-(newIntrinsicValue*100)/currentStockPrice));

    //newExtrinsicValue = Math.min(Math.max(0,newExtrinsicValue-newIntrinsicValue),newExtrinsicValue*(1-(newIntrinsicValue*100)/currentStockPrice));
    //else
    //  newExtrinsicValue = 0;

    // Add new intrinsic value and new time decay to get the updated option price
    newPutOptionPrice= newIntrinsicValue + newExtrinsicValue;
    //newPutOptionPrice*=(100-11)/100;
    //System.out.printf("P %.2f %.2f %.2f %.2f %.2f %.2f %.2f %.2f %.2f %.2f %.2f %.2f %.2f %.2f \t\t\t",currentStockPrice,oldStockPrice,
    putStrikePrice,oldputprice,delta_pr,interval, tot_interval, curr_dif, pr_change, iniExtrinsicValue, pr_iniExtrinsicValue, 
    newExtrinsicValue, newIntrinsicValue, newPutOptionPrice);
    }

     */
}
