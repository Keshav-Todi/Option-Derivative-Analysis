import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.*;

public class OptionProfitAnalysis {
    double ndll_delta[][],ndll_gam[][]; ;int ndl_del_len=0,atm_ind_c=0,atm_ind_p=0;
    double atm_extr_c=0,atm_extr_p=0;
    double newIntrinsicValue=0, newExtrinsicValue=0, newCallOptionPrice=0, newPutOptionPrice=0;
    public void calculateCallOptionPrice(double currentStockPrice, double callStrikePrice,double oldStockPrice, double oldcallprice
    , double delta_pr,double gamma_pr, double iniExtrinsicValue, double interval, double tot_interval,double volt, double tot_volt) {
        // Calculate new intrinsic value (maximum of 0 and current stock price - call strike price)
        newIntrinsicValue=0; newExtrinsicValue=0; newCallOptionPrice=0;

        double pr_iniExtrinsicValue=iniExtrinsicValue;
        newExtrinsicValue=iniExtrinsicValue;
        double curr_dif=0,pr_change=0;
        //double temp_new;
        newIntrinsicValue = Math.max(0, currentStockPrice - callStrikePrice);
        //atm_extr_c=iniExtrinsicValue;
        //atm_extr_c = atm_extr_c*(tot_interval-interval)/tot_interval;
        //atm_extr_c = atm_extr_c*(volt)/(tot_volt/2);
        if(oldStockPrice!=currentStockPrice)
        {
            curr_dif=currentStockPrice-oldStockPrice;
            pr_change=curr_dif*delta_pr;//+(1/2.0*Math.abs(Math.pow(curr_dif,2))*gamma_pr);
            if(curr_dif<0)
                pr_change=curr_dif*delta_pr;//-(1/2.0*Math.abs(Math.pow(curr_dif,2))*gamma_pr);
            newCallOptionPrice=oldcallprice+pr_change;
            //if(callStrikePrice>currentStockPrice)
            //{   
            //  iniExtrinsicValue=iniExtrinsicValue*Math.pow(currentStockPrice/oldStockPrice,20);
            //  newCallOptionPrice=iniExtrinsicValue;
            //}
            //else
            //{
            //  iniExtrinsicValue=iniExtrinsicValue*Math.pow(oldStockPrice/currentStockPrice,20);
            // newCallOptionPrice=newIntrinsicValue+iniExtrinsicValue;
            //}

            //if(curr_dif<=0)
            //  newCallOptionPrice=iniExtrinsicValue;
            //if(curr_dif>0)
            //  newCallOptionPrice=newIntrinsicValue+iniExtrinsicValue;
            newCallOptionPrice=Math.max(newCallOptionPrice, newIntrinsicValue);
        }
        else
            newCallOptionPrice=iniExtrinsicValue;

        newExtrinsicValue=newCallOptionPrice-newIntrinsicValue;
        newExtrinsicValue = newExtrinsicValue*(tot_interval-interval)/tot_interval;
        //newExtrinsicValue = newExtrinsicValue*(volt)/(tot_volt/2);
        newCallOptionPrice=newExtrinsicValue+newIntrinsicValue;

    }

    public void calculatePutOptionPrice(double currentStockPrice, double putStrikePrice, double oldStockPrice, double oldputprice
    , double delta_pr,double gamma_pr, double iniExtrinsicValue, double interval, double tot_interval,double volt, double tot_volt) {
        // Calculate new intrinsic value (maximum of 0 and current stock price - call strike price)
        newIntrinsicValue=0; newExtrinsicValue=0; newCallOptionPrice=0;

        double pr_iniExtrinsicValue=iniExtrinsicValue;
        newExtrinsicValue=iniExtrinsicValue;
        double curr_dif=0,pr_change=0;
        newIntrinsicValue = Math.max(0, putStrikePrice - currentStockPrice);
        //atm_extr_p=iniExtrinsicValue;
        //atm_extr_p = atm_extr_p*(tot_interval-interval)/tot_interval;
        //atm_extr_p = atm_extr_p*(volt)/(tot_volt/2);
        if(putStrikePrice!=currentStockPrice)
        {
            curr_dif=currentStockPrice-oldStockPrice;
            pr_change=curr_dif*delta_pr;//-(1/2.0*Math.abs(Math.pow(curr_dif,2))*gamma_pr);
            if(curr_dif<0)
                pr_change=curr_dif*delta_pr;//+(1/2.0*Math.abs(Math.pow(curr_dif,2))*gamma_pr);
            newPutOptionPrice=oldputprice+pr_change;
            /*
            if(putStrikePrice<currentStockPrice)
            {
            iniExtrinsicValue=iniExtrinsicValue*Math.pow(oldStockPrice/currentStockPrice,60);newPutOptionPrice=iniExtrinsicValue;
            }
            else
            {
            iniExtrinsicValue=iniExtrinsicValue*Math.pow(currentStockPrice/oldStockPrice,60);newPutOptionPrice=newIntrinsicValue+iniExtrinsicValue;
            }
             */
            //if(curr_dif>=0)
            //   newPutOptionPrice=iniExtrinsicValue;
            //if(curr_dif<0)
            //  newPutOptionPrice=newIntrinsicValue+iniExtrinsicValue;
            newPutOptionPrice=Math.max(newPutOptionPrice, newIntrinsicValue);

        }
        else
            newPutOptionPrice=iniExtrinsicValue;

        newExtrinsicValue=newPutOptionPrice-newIntrinsicValue;
        newExtrinsicValue = newExtrinsicValue*(tot_interval-interval)/tot_interval;
        //newExtrinsicValue = newExtrinsicValue*(volt)/(tot_volt/2);
        newPutOptionPrice=newExtrinsicValue+newIntrinsicValue;

        //System.out.println();
        //System.out.printf("P %.2f %.2f %.2f %.2f %.2f %.2f %.2f %.2f \t\t\t",currentStockPrice,oldStockPrice,
        //  putStrikePrice,oldputprice, curr_dif, atm_extr_p, newIntrinsicValue, newPutOptionPrice);
        //System.out.println();

    }
    int newop_l=0,leen=0;
    public double[][] optioncaller(double ndll[][], int i_l, double spp) {
        double CPrice = 40.3; // Current stock price
        double PPrice = 22.4; // Current stock price
        double currentprice=1834;
        double callStrikePrice = 1800; // Call option strike price
        double putStrikePrice = 1850; // Put option strike price
        double iniCExtrinsicValue = 0;
        double iniPExtrinsicValue = 0;// Initial extrinsic value (arbitrary value for illustration)
        double tot_interval = 10; // Total intervals
        double newPrice = 0; // Current interval
        double an[][][][]=new double[(int)tot_interval+1][100][100][8];int j=0,k=0;leen=0;newop_l=0;
        double minper=0,maxper=0;
        double newop[][]=new double[10000][3];

        iniCExtrinsicValue = CPrice-Math.max(0, currentprice - callStrikePrice);
        iniPExtrinsicValue = PPrice-Math.max(0,putStrikePrice - currentprice);

        int change_chk=2;
        double deltaa_c=0.0,deltaa_p=0.0,gamma_c=0.0,gamma_p=0.0;

        //double spp= 29516;
        //double ndll[][]={{291,29400,156.5},{238,29500,202},{192.5,29600,258}};
        //int i_l=ndll.length;

        OptionChain ob2=new OptionChain();
        double ndll_del[][]=ob2.opch(ndll,i_l,spp);
        int ndl_del_len1=i_l;

        ndll_gam= new double[ndl_del_len1][7];

        for (int i1 = 0; i1 < ndl_del_len1; i1+=1)
        {
            ndll_gam[i1][0]=ob2.ndll_gam[i1][0];
            ndll_gam[i1][1]=100000;
            ndll_gam[i1][2]=100000;
            ndll_gam[i1][3]=100000;
            ndll_gam[i1][4]=100000;
            ndll_gam[i1][5]=100000;
            ndll_gam[i1][6]=ob2.ndll_gam[i1][6];
        }

        ///*

        for (double i = 0; i <= tot_interval; i+=1) {
            for (int i1 = 0; i1 < i_l; i1+=1) {
                j=0;
                if(change_chk==1)
                {
                    minper=-0.05*i/tot_interval;
                    maxper=0.05*i/tot_interval;
                }
                if(change_chk==2)
                {
                    minper=-0.01;
                    maxper=0.01;
                }
                //System.out.println("At i "+i+" minper "+minper+" maxper "+maxper);
                for (double PercentageChange = minper; PercentageChange <= maxper; PercentageChange += 0.005) {
                    k=0;

                    //System.out.println("Perchng is "+PercentageChange+" minper "+minper+" maxper "+maxper);

                    //System.out.println("\ni "+i+" i1 "+i1);

                    currentprice=spp;
                    newPrice = currentprice * (1 + PercentageChange); //System.out.println("new price "+newPrice);
                    CPrice = ndll_del[i1][1];//System.out.println("CPrice "+CPrice);
                    PPrice = ndll_del[i1][3];//System.out.println("PPrice "+PPrice);
                    callStrikePrice = ndll_del[i1][2];//System.out.println("csprice "+callStrikePrice);
                    putStrikePrice = ndll_del[i1][2];//System.out.println("psprice "+putStrikePrice);
                    iniCExtrinsicValue = CPrice-Math.max(0, currentprice - callStrikePrice);//System.out.println(" iniCExt "+iniCExtrinsicValue);
                    iniPExtrinsicValue = PPrice-Math.max(0,putStrikePrice - currentprice);//System.out.println(" iniPExt "+iniPExtrinsicValue);
                    deltaa_c=ndll_del[i1][0];
                    deltaa_p=ndll_del[i1][4];
                    gamma_c=ndll_gam[i1][0];
                    gamma_p=ndll_gam[i1][6];
                    // Call the functions to calculate option details
                    //System.out.println();//System.out.println("new np "+newPrice);

                    calculateCallOptionPrice(newPrice, callStrikePrice, currentprice, CPrice, deltaa_c, gamma_c, iniCExtrinsicValue, i, tot_interval,5,10);           //calculateCallOptionPrice(newPrice, callStrikePrice, iniCExtrinsicValue, i, tot_interval);

                    //an[leen][i1][j][k++]=newIntrinsicValue;                                                //System.out.println("new intr "+newIntrinsicValue);
                    //an[leen][i1][j][k++]=newExtrinsicValue;                                                //System.out.println("new extr "+newExtrinsicValue);
                    an[leen][i1][j][k++]=newCallOptionPrice;   newop[newop_l][0]=newCallOptionPrice;       //System.out.println("new cp "+newCallOptionPrice);

                    an[leen][i1][j][k++]=newPrice;             newop[newop_l][1]=ndll[i1][1];

                    calculatePutOptionPrice(newPrice, putStrikePrice, currentprice, PPrice, deltaa_p, gamma_p, iniPExtrinsicValue, i, tot_interval,5,10);

                    //an[leen][i1][j][k++]=newIntrinsicValue;                                                //System.out.println("new intr "+newIntrinsicValue);
                    //an[leen][i1][j][k++]=newExtrinsicValue;                                                //System.out.println("new extr "+newExtrinsicValue);
                    an[leen][i1][j][k++]=newPutOptionPrice;    newop[newop_l][2]=newPutOptionPrice;        //System.out.println("new pp "+newPutOptionPrice);

                    an[leen][i1][j][k++]=ndll_del[i1][1];
                    an[leen][i1][j][k++]=ndll_del[i1][2];
                    an[leen][i1][j][k++]=ndll_del[i1][3];

                    j++;newop_l++;
                }
                //System.out.println();
            }
            leen++;//System.out.println("\n");
        }
        // Print option details
        /*
        for(int i=0;i<leen;i++)
        {
        for (int i1 = 0; i1 < i_l; i1+=1) 
        {
        for(int j1=0;j1<j;j1++)
        {
        //for(int k1=0;k1<k;k1++)
        //{
        System.out.printf("| %.2f %.2f %.2f | ",an[i][i1][j1][0],an[i][i1][j1][1],an[i][i1][j1][2]);
        //}
        //System.out.println();
        }
        System.out.print("\t");
        }
        System.out.println();
        }
         */
        /*
        System.out.println("\n");int j1=0;
        for (double i = 0; i <= tot_interval; i+=1) {
        for (int i1 = 0; i1 < i_l; i1+=1) {
        for (double PercentageChange = minper; PercentageChange <= maxper; PercentageChange += 0.005) {

        for(int k1=0;k1<3;k1++)
        {
        System.out.printf("%.2f \t",newop[j1][k1]);
        }
        System.out.println();j1++;
        }
        System.out.println("\n");
        }
        System.out.println();
        }
         */
        return newop;
    }
    double callNetProfit=0, callBreakeven[];
    int cnt_c=0,cnt_p=0,cnt_l=0,cnt_s=0,cnt_m=0, cnt_n=0;
    private   void calculateCallNetProfit(double priceAtExpiry, double callStrikePrice, double callOptionPrice, double callOptionsBought) 
    {
        callNetProfit=0;
        // Calculate net profit for a call option
        if(callStrikePrice<=priceAtExpiry)
            callNetProfit = (priceAtExpiry - callStrikePrice - callOptionPrice) * callOptionsBought;
        else
            callNetProfit = -1*(callOptionPrice * callOptionsBought);

        callBreakeven[cnt_c++] = callStrikePrice+callOptionPrice;
    }

    double putNetProfit=0, putBreakeven[];
    private   void calculateputNetProfit(double priceAtExpiry, double putStrikePrice, double putOptionPrice, double putOptionsBought) {
        putNetProfit=0;
        // Calculate net profit for a call option
        if(putStrikePrice>=priceAtExpiry)
            putNetProfit = (putStrikePrice - priceAtExpiry - putOptionPrice) * putOptionsBought;
        else
            putNetProfit = -1*(putOptionPrice * putOptionsBought);

        putBreakeven[cnt_p++] = putStrikePrice-putOptionPrice;
    }
    double LongNetProfit=0, LongBreakeven[], long_perm_prof=0;
    private   void calculateLongNetProfit(double priceAtExpiry, double LongStartPrice, double LongBought, double typ, double ext_pr, double ext_sl) {
        LongNetProfit=0;
        if((typ==0)||((typ==1)&&(priceAtExpiry>=LongStartPrice))||((typ==4)&&(priceAtExpiry<LongStartPrice))||((typ==5)&&(priceAtExpiry>=LongStartPrice)))
            LongNetProfit = (priceAtExpiry - LongStartPrice) * LongBought;
        else if((typ==2)&&(priceAtExpiry>=LongStartPrice)&&(priceAtExpiry<=ext_pr))
            LongNetProfit = (priceAtExpiry - LongStartPrice) * LongBought;
        else if((typ==2)&&(priceAtExpiry>=LongStartPrice)&&(priceAtExpiry>=ext_pr))
            LongNetProfit = (ext_pr - LongStartPrice) * LongBought;  
        else if(((typ==5)||(typ==3))&&(priceAtExpiry<LongStartPrice)&&(priceAtExpiry>=ext_sl))
            LongNetProfit = (priceAtExpiry - LongStartPrice) * LongBought;
        else if(((typ==5)||(typ==3))&&(priceAtExpiry<LongStartPrice)&&(priceAtExpiry<=ext_sl))
            LongNetProfit = (ext_sl - LongStartPrice) * LongBought;  
        else if(((typ==4)||(typ==3))&&(priceAtExpiry>=LongStartPrice)&&(priceAtExpiry<=ext_pr))
            LongNetProfit = (priceAtExpiry - LongStartPrice) * LongBought;
        else if(((typ==4)||(typ==3))&&(priceAtExpiry>=LongStartPrice)&&(priceAtExpiry>=ext_pr))
            LongNetProfit = (ext_pr - LongStartPrice) * LongBought;  

        LongBreakeven[cnt_l++] = LongStartPrice;
    }
    double ShortNetProfit=0, ShortBreakeven[];
    private   void calculateShortNetProfit(double priceAtExpiry, double ShortStartPrice, double ShortBought, double typ, double ext_pr, double ext_sl) {
        ShortNetProfit=0;
        if((typ==0)||((typ==1)&&(priceAtExpiry<=ShortStartPrice))||((typ==4)&&(priceAtExpiry>ShortStartPrice))||((typ==5)&&(priceAtExpiry<=ShortStartPrice)))
            ShortNetProfit = -1*(priceAtExpiry - ShortStartPrice) * ShortBought;
        else if((typ==2)&&(priceAtExpiry<=ShortStartPrice)&&(priceAtExpiry>=ext_pr))
            ShortNetProfit = -1*(priceAtExpiry - ShortStartPrice) * ShortBought;
        else if((typ==2)&&(priceAtExpiry<=ShortStartPrice)&&(priceAtExpiry<=ext_pr))
            ShortNetProfit = -1*(ext_pr - ShortStartPrice) * ShortBought;  
        else if(((typ==5)||(typ==3))&&(priceAtExpiry>ShortStartPrice)&&(priceAtExpiry<=ext_sl))
            ShortNetProfit = -1*(priceAtExpiry - ShortStartPrice) * ShortBought;
        else if(((typ==5)||(typ==3))&&(priceAtExpiry>ShortStartPrice)&&(priceAtExpiry>=ext_sl))
            ShortNetProfit = -1*(ext_sl - ShortStartPrice) * ShortBought;  
        else if(((typ==4)||(typ==3))&&(priceAtExpiry<=ShortStartPrice)&&(priceAtExpiry>=ext_pr))
            ShortNetProfit = -1*(priceAtExpiry - ShortStartPrice) * ShortBought;
        else if(((typ==4)||(typ==3))&&(priceAtExpiry<=ShortStartPrice)&&(priceAtExpiry<=ext_pr))
            ShortNetProfit = -1*(ext_pr - ShortStartPrice) * ShortBought;  
        ShortBreakeven[cnt_s++] = ShortStartPrice;
    }

    double callshortProfit=0, callshortBreakeven[];
    private   void calculateCallshortProfit(double priceAtExpiry, double callStrikePrice, double callOptionPrice, double callOptionsBought) 
    {
        callshortProfit=0;
        // Calculate net profit for a call option
        if(callStrikePrice>=priceAtExpiry)
            callshortProfit = (callOptionPrice) * callOptionsBought;
        else
            callshortProfit = -1*(priceAtExpiry - callStrikePrice - callOptionPrice) * callOptionsBought;

        //System.out.println("csp "+callStrikePrice+" cop "+callOptionPrice+" cnt_m "+cnt_m);
        callshortBreakeven[cnt_m++] = callStrikePrice+callOptionPrice;
    }
    double putshortProfit=0, putshortBreakeven[];
    private   void calculateputshortProfit(double priceAtExpiry, double putStrikePrice, double putOptionPrice, double putOptionsBought) {
        putshortProfit=0;
        // Calculate net profit for a call option
        if(putStrikePrice<=priceAtExpiry)
            putshortProfit = (putOptionPrice * putOptionsBought);
        else
            putshortProfit = -1*(putStrikePrice - priceAtExpiry - putOptionPrice) * putOptionsBought;

        putshortBreakeven[cnt_n++] = putStrikePrice-putOptionPrice;
    }

    int pe_l=0,cnp_l=0, m_pe_l=0;
    double x[],y[]; 
    double firstBreakeven=0, LastBreakeven=0, min=0,max=0,md_min=0,md_max=0, tot_extr=0,short_tot_extr=0
    , num_pos=0,num_mid_pos=0,profbyper=0, cust_min=0,num_pos_nr=0, conty1=0, conty2=0;
    double[][] breakevenss=new double[100][3];
    int  num_brk=0;
    double prof_tott=0,pos_prof_tott=0,theta_rat=0,volt_rat=0;
    double secombinedNetProfit=0, tot_c=0, tot_p=0, tot_m=0, tot_n=0, tot_cr=0;
    double profit1=0.0, profit2=0.0,profit3=0.0, profit4=0.0,profit5=0.0, profit6=0.0,profit7=0.0, profit8=0.0,profit9=0.0
    , profit10=0.0, profit11=0.0, prof_ind1=0.0, prof_ind2=0.0, prof_ind3=0.0, prof_ind4=0.0, prof_ind5=0.0, prof_ind6=0.0, prof_ind7=0.0
    , prof_ind8=0.0, prof_ind9=0.0, prof_ind10=0.0, prof_ind11=0.0;
    double seprofit1=0.0, seprofit2=0.0,seprofit3=0.0, seprofit4=0.0, seprofit5=0.0, seprofit6=0.0, seprofit7=0.0, seprofit8=0.0, seprofit9=0.0
    , seprofit10=0.0, seprofit11=0.0;
    String s_dt,x_data,y_data,y1_data, tm_x_data, tm_y_data[]=new String[500];String[] ns;
    double far_prof1=0,far_prof2=0, tr_fees=0,tr_f_per=20.0/100.0;
    double xValues[]={-3, -2, -1, 0, 1, 2, 3},yValues[]={-4, -3, -2, -1, 0, 1, 4},y1Values[] = {3, 2, 1, 0, -1, -2, -3},
    tm_xValues[]={-3, -2, -1, 0, 1, 2, 3},tm_yValues[]={-4, -3, -2, -1, 0, 1, 4};int tm_l=0;
    double cnpp[]={1000,400,100,40,10,1,0,-1,-10,-40,-100,-400,-1000};
    double newcallsp[]=new double[2];double newputsp[]=new double[2];
    double newcallshortsp[]=new double[2];double newputshortsp[]=new double[2];
    ScatterPlotExample example1 = new ScatterPlotExample();
    public   void mai(double cs[][],int lenc, double ps[][],int lenp, double ls[][],int lenl ,double ss[][],int lens ,double ms[][],int lenm 
    , double ns[][],int lenn ,double sp, int pnntt, double tt_amt, double x_time[],double y_time[]) {
        //Scanner scanner = new Scanner(System.in);

        int intervall=0, tot_intervall=10;
        int voltt=5, tot_voltt=10;
        tot_c=0;tot_p=0;tot_m=0;tot_n=0;tot_cr=0;

        //System.out.println("\n ndl_del_len is "+ndl_del_len);
        //(callsp[i4][1]-Math.max(0, dd1[i1][8] - callsp[i4][0]));putsp[i4][1]-Math.max(0,  putsp[i4][0] - dd1[i1][8]));
        if(ndl_del_len!=0)
        {

            for(int i2=0;i2<lenc;i2++)
            {
                for(int i3=0;i3<ndl_del_len;i3++)
                {//System.out.println(" i2 "+i2+" lenc "+lenc+" i3 "+i3+" ndl_del_len "+ndl_del_len+" ndll_delta[i3][2] "+ndll_delta[i3][2]+" cs[i2][0] "+cs[i2][0]);
                    if(ndll_delta[i3][2]==cs[i2][0])
                    {
                        cs[i2][3]=ndll_delta[i3][0];
                        cs[i2][4]=ndll_gam[i3][0];
                    }
                }
                tot_c=tot_c-(cs[i2][1]-Math.max(0, sp - cs[i2][0]))*cs[i2][2];
                tot_cr=tot_cr-(cs[i2][1]-Math.max(0, sp - cs[i2][0]))*cs[i2][2];
            }

            for(int i2=0;i2<lenp;i2++)
            {
                for(int i3=0;i3<ndl_del_len;i3++)
                {//System.out.println(" i2 "+i2+" lenp "+lenp+" i3 "+i3+" ndl_del_len "+ndl_del_len+" ndll_delta[i3][2] "+ndll_delta[i3][2]+" ps[i2][0] "+ps[i2][0]);
                    if(ndll_delta[i3][2]==ps[i2][0])
                    {
                        ps[i2][3]=ndll_delta[i3][4];
                        ps[i2][4]=ndll_gam[i3][6];
                    }
                }
                tot_p=tot_p-(ps[i2][1]-Math.max(0, ps[i2][0] - sp))*ps[i2][2];
                tot_cr=tot_cr-(ps[i2][1]-Math.max(0, ps[i2][0] - sp))*ps[i2][2];
            }

            for(int i2=0;i2<lenm;i2++)
            {
                for(int i3=0;i3<ndl_del_len;i3++)
                {//System.out.println(" i2 "+i2+" lenm "+lenm+" i3 "+i3+" ndl_del_len "+ndl_del_len+" ndll_delta[i3][2] "+ndll_delta[i3][2]+" ms[i2][0] "+ms[i2][0]);
                    if(ndll_delta[i3][2]==ms[i2][0])
                    {
                        ms[i2][3]=ndll_delta[i3][0];
                        ms[i2][4]=ndll_gam[i3][0];
                    }
                }
                tot_m=tot_m+(ms[i2][1]-Math.max(0, sp - ms[i2][0]))*ms[i2][2];
                tot_cr=tot_cr+(ms[i2][1]-Math.max(0, sp - ms[i2][0]))*ms[i2][2];
            }

            for(int i2=0;i2<lenn;i2++)
            {
                for(int i3=0;i3<ndl_del_len;i3++)
                {//System.out.println(" i2 "+i2+" lenn "+lenn+" i3 "+i3+" ndl_del_len "+ndl_del_len+" ndll_delta[i3][2] "+ndll_delta[i3][2]+" ns[i2][0] "+ns[i2][0]);
                    if(ndll_delta[i3][2]==ns[i2][0])
                    {
                        ns[i2][3]=ndll_delta[i3][4];
                        ns[i2][4]=ndll_gam[i3][6];
                    }
                }
                tot_n=tot_n+(ns[i2][1]-Math.max(0, ns[i2][0] - sp))*ns[i2][2];
                tot_cr=tot_cr+(ns[i2][1]-Math.max(0, ns[i2][0] - sp))*ns[i2][2];
            }

        }

        /*
        System.out.println("\nI received :");
        for(int i2=0;i2<lenc;i2++)
        System.out.printf("C%.2f , %.2f , %.2f , %.2f ",cs[i2][0],cs[i2][1],cs[i2][2],cs[i2][3]);
        System.out.printf("|"+lenc+"|\t");
        for(int i2=0;i2<lenp;i2++)
        System.out.printf("P%.2f , %.2f , %.2f , %.2f ",ps[i2][0],ps[i2][1],ps[i2][2],ps[i2][3]);
        System.out.printf("|"+lenp+"|\t");
        for(int i2=0;i2<lenl;i2++)
        System.out.printf("L%.2f , %.2f , %.2f ",ls[i2][0],ls[i2][1],ls[i2][2]);
        System.out.printf("|"+lenl+"|\t");
        for(int i2=0;i2<lens;i2++)
        System.out.printf("S%.2f , %.2f , %.2f ",ss[i2][0],ss[i2][1],ss[i2][2]);
        System.out.printf("|"+lens+"|\t");
        for(int i2=0;i2<lenm;i2++)
        System.out.printf("M%.2f , %.2f , %.2f , %.2f ",ms[i2][0],ms[i2][1],ms[i2][2],ms[i2][3]);
        System.out.printf("|"+lenm+"|\t");
        for(int i2=0;i2<lenn;i2++)
        System.out.printf("N%.2f , %.2f , %.2f , %.2f ",ns[i2][0],ns[i2][1],ns[i2][2],ns[i2][3]);
        System.out.printf("|"+lenn+"|\t");
        System.out.printf(sp+" ");
        System.out.println();
         */

        //System.exit(0);

        double a[][]=new double[1000][60];int l=0,j=0,cntt00=0,cntt0=0,cntt=0;

        x=new double[1000]; y=new double[1000];

        cnt_c=0;cnt_p=0;cnt_l=0;cnt_s=0;cnt_m=0;cnt_n=0;
        callBreakeven=new double[1000]; putBreakeven=new double[1000]; LongBreakeven=new double[1000]; ShortBreakeven=new double[1000];
        callshortBreakeven=new double[1000];putshortBreakeven=new double[1000];
        // Input for Call Option 
        //System.out.print("Enter the Call Option Strike Price: ");
        double callStrikePrice = cs[0][0];//1825;//scanner.nextDouble(); 
        //System.out.print("Enter the Call Option Price: "); 
        double callOptionPrice = cs[0][1];//29.9;//scanner.nextDouble(); 
        // Input for Put Option 
        //System.out.print("Enter the Put Option Strike Price: ");
        double putStrikePrice = ps[0][0];//1875;//scanner.nextDouble(); 
        //System.out.print("Enter the Put Option Price: "); 
        double putOptionPrice = ps[0][1];//31.8;//scanner.nextDouble(); 
        //System.out.print("Enter the Spot Price:\n ");
        double spotPrice = sp;//1848;//scanner.nextDouble();

        double callOptionsBought = cs[0][2],putoptionsbought= ps[0][2]; // Number of options bought

        double minPriceAtExpiryPercentage = -0.1; //-0.1; Minimum price at expiry percentage (-2%)
        double maxPriceAtExpiryPercentage = 0.1;//0.1; // Maximum price at expiry percentage (2%)
        double priceStepPercentage = 0.002; // Price at expiry step size (0.05%)
        double profit_index1=-0.02, profit_index2=0.02;
        int cnt2=0; tr_fees=0;
        int tntt0=0,tntt1=0,tntt2=0; double priceAtTime=0;

        //System.out.println("inside  option main");
        if(x_time.length>0)
        {
            //System.out.println("inside x_time ");
            //System.out.println("\n x_time.length is "+x_time.length);
            for(int i4=0;i4<x_time.length;i4++)
            {
                priceAtTime=y_time[i4];
                intervall=(int)x_time[i4]; tot_intervall=(int)x_time[x_time.length-1];
                //System.out.println("\nIn price at time inter "+intervall+" tot_inter "+tot_intervall);
                double callsellProfitCombined = 0;
                double putsellProfitCombined = 0;
                double LongNetProfitCombined = 0;tot_extr=0;short_tot_extr=0;
                double ShortNetProfitCombined = 0;
                double callshortsellProfitCombined = 0;
                double putshortsellProfitCombined = 0;
                secombinedNetProfit=0;

                j=0;tntt0=10;tntt1=30;tntt2=50;
                for(int i2=0;i2<lenc;i2++)
                {
                    calculateCallOptionPrice(priceAtTime, cs[i2][0],sp, cs[i2][1], cs[i2][3], cs[i2][4], cs[i2][1]-Math.max(0, sp - cs[i2][0]),intervall, tot_intervall,voltt,tot_voltt);
                    callsellProfitCombined += (newCallOptionPrice-cs[i2][1])*cs[i2][2];//a[l][11]=newCallOptionPrice;//System.out.println("newcallsp[i2] "+newcallsp[i2]);
                    a[l][tntt0++]= callsellProfitCombined;a[l][tntt1++]= newCallOptionPrice;
                    tot_extr+=newExtrinsicValue*cs[i2][2];//(cs[i2][1]-Math.max(0, sp - cs[i2][0]))*cs[i2][2];
                }

                for(int i2=0;i2<lenp;i2++)
                {
                    calculatePutOptionPrice(priceAtTime, ps[i2][0], sp, ps[i2][1], ps[i2][3],ps[i2][4], ps[i2][1]-Math.max(0, ps[i2][0]- sp),intervall, tot_intervall,voltt,tot_voltt);
                    putsellProfitCombined += (newPutOptionPrice-ps[i2][1])*ps[i2][2];//a[l][12]=newPutOptionPrice;
                    a[l][tntt0++]= putsellProfitCombined;a[l][tntt1++]=newPutOptionPrice;//System.out.println("newputsp[i2] "+newputsp[i2]);
                    tot_extr+=newExtrinsicValue*ps[i2][2];//(ps[i2][1]-Math.max(0, ps[i2][0] - sp))*ps[i2][2];
                }

                for(int i2=0;i2<lenm;i2++)
                {
                    calculateCallOptionPrice(priceAtTime, ms[i2][0], sp, ms[i2][1], ms[i2][3],ms[i2][4],ms[i2][1]-Math.max(0, sp - ms[i2][0]),intervall, tot_intervall,voltt,tot_voltt);
                    callshortsellProfitCombined += (ms[i2][1]-newCallOptionPrice)*ms[i2][2];
                    a[l][tntt0++]= callshortsellProfitCombined;a[l][tntt1++]=newCallOptionPrice; //System.out.println("newcallshortsp[i2] "+newcallshortsp[i2]);
                    short_tot_extr+=newExtrinsicValue*ps[i2][2];//(ms[i2][1]-Math.max(0, sp - ms[i2][0]))*ms[i2][2];
                }

                for(int i2=0;i2<lenn;i2++)
                {
                    calculatePutOptionPrice(priceAtTime, ns[i2][0], sp, ns[i2][1], ns[i2][3],ns[i2][4], ns[i2][1]-Math.max(0, ns[i2][0]- sp),intervall, tot_intervall,voltt,tot_voltt);
                    putshortsellProfitCombined += (ns[i2][1]-newPutOptionPrice)*ns[i2][2];
                    a[l][tntt0++]= putshortsellProfitCombined;a[l][tntt1++]=newPutOptionPrice; //System.out.println("newputshortsp[i2] "+newputshortsp[i2]);
                    short_tot_extr+=newExtrinsicValue*ns[i2][2];//(ns[i2][1]-Math.max(0, ns[i2][0] - sp))*ns[i2][2];
                }

                for(int i2=0;i2<lenl;i2++)
                {
                    calculateLongNetProfit(priceAtTime,ls[i2][0], ls[i2][1], ls[i2][2], ls[i2][3], ls[i2][4]);
                    LongNetProfitCombined += LongNetProfit;
                    a[l][tntt0++]= LongNetProfit;
                }

                for(int i2=0;i2<lens;i2++)
                {
                    calculateShortNetProfit(priceAtTime,ss[i2][0], ss[i2][1], ss[i2][2], ss[i2][3], ss[i2][4]);
                    ShortNetProfitCombined += ShortNetProfit;
                    a[l][tntt0++]= ShortNetProfit;
                }

                secombinedNetProfit = callsellProfitCombined + putsellProfitCombined + LongNetProfitCombined + ShortNetProfitCombined
                +callshortsellProfitCombined+putshortsellProfitCombined;

                double ini_price=y_time[0];
                double priceper=(priceAtTime-ini_price)/ini_price*100.0;
                a[l][j++]= (double)intervall/(double)tot_intervall;//priceAtTime;
                a[l][j++]= priceper;//putNetProfitCombined;
                a[l][j++]= ini_price;//combinedNetProfit; 
                a[l][j++]= priceAtTime;//callNetProfitCombined;
                a[l][j++]= 2;//putNetProfitCombined;
                a[l][j++]= secombinedNetProfit; 
                a[l][j++]= 4;//LongNetProfitCombined;
                a[l][j++]= 5;//ShortNetProfitCombined;
                a[l][j++]= 6;//callshortProfitCombined;
                a[l][j++]= 7;//putshortProfitCombined;
                y[l]=8;//combinedNetProfit;

                a[l][tntt2++]= callsellProfitCombined;
                a[l][tntt2++]= putsellProfitCombined;
                a[l][tntt2++]= callshortsellProfitCombined;
                a[l][tntt2++]= putshortsellProfitCombined;
                a[l][tntt2++]= secombinedNetProfit;

                l++;j=0;
            }
            //System.out.println("\n afterx_time loop ");

            tm_xValues = new double[l];//{-3, -2, -1, 0, 1, 2, 3};
            //double[] 
            tm_yValues = new double[l];//{-4, -3, -2, -1, 0, 1, 4};
            //System.out.println("\n check status 6 ");

            for(int i=0;i<l;i++)
            {
                tm_xValues[i]=a[i][0];
                tm_yValues[i]=a[i][5];
            }
            //System.out.println("\n check status 7 ");
            tm_x_data=convertToStringArr(tm_xValues, l);System.out.println("\n check status 7.5 ");
            //ob30.pnt(tm_y_data);
            //System.out.println("\n check status 8 ");

            //String tm_temp=tm_x_data;
            tm_y_data[tm_l++]=convertToStringArr(tm_yValues, l);

            System.out.println("\ntm_l is "+tm_l);
            //ob30.pnt(tm_xValues);
            //ob30.pnt(tm_yValues);
            //System.out.println("\n"+tm_x_data);
            //ob30.pnt(tm_y_data,1);

            double level_y[]=new double[0];
            example1.updateChartData(tm_xValues, new double[][]{tm_yValues}, 0, level_y, 0, new String[]{"Combined Trend 1"});

            if(pnntt==1)
            {
                System.out.println("\n\n---------------------------------------------------------------------------------------------------------------------------\n\n\n");
                for(int i=0;i<l;i++)
                {

                    for(int i2=0;i2<lenc;i2++)
                        System.out.printf("C%.2f , %.2f , %.2f\t",cs[i2][0],cs[i2][1],cs[i2][2],cs[i2][3],cs[i2][4]);
                    for(int i2=0;i2<lenp;i2++)
                        System.out.printf("P%.2f , %.2f , %.2f\t",ps[i2][0],ps[i2][1],ps[i2][2],ps[i2][3],ps[i2][4]);
                    for(int i2=0;i2<lenl;i2++)
                        System.out.printf("L%.2f , %.2f, %.2f \t",ls[i2][0],ls[i2][1],ls[i2][2]);
                    for(int i2=0;i2<lens;i2++)
                        System.out.printf("S%.2f , %.2f, %.2f \t",ss[i2][0],ss[i2][1],ss[i2][2]);
                    for(int i2=0;i2<lenm;i2++)
                        System.out.printf("M%.2f , %.2f , %.2f\t",ms[i2][0],ms[i2][1],ms[i2][2],ms[i2][3],ms[i2][4]);
                    for(int i2=0;i2<lenn;i2++)
                        System.out.printf("N%.2f , %.2f , %.2f\t",ns[i2][0],ns[i2][1],ns[i2][2],ms[i2][3],ms[i2][4]);

                    for(int k=0;k<3;k++)
                    {
                        System.out.printf("%.2f ,\t",a[i][k]);   

                    }

                    for(int i2=10;i2<10+lenc;i2++)
                        System.out.printf("| %.2f |\t",a[i][i2]);
                    for(int i2=10+lenc;i2<10+lenc+lenp;i2++)
                        System.out.printf("| %.2f |\t",a[i][i2]);
                    for(int i2=10+lenc+lenp;i2<10+lenc+lenp+lenl;i2++)
                        System.out.printf("| %.2f |\t",a[i][i2]);
                    for(int i2=10+lenc+lenp+lenl;i2<10+lenc+lenp+lenl+lens;i2++)
                        System.out.printf("| %.2f |\t",a[i][i2]);
                    for(int i2=10+lenc+lenp+lenl+lens;i2<10+lenc+lenp+lenl+lens+lenm;i2++)
                        System.out.printf("| %.2f |\t",a[i][i2]);
                    for(int i2=10+lenc+lenp+lenl+lens+lenm;i2<10+lenc+lenp+lenl+lens+lenm+lenn;i2++)
                        System.out.printf("| %.2f |\t",a[i][i2]);

                    System.out.printf(" | %.2f | \t",a[i][5]);    
                    for(int i2=30;i2<30+lenc;i2++)
                        System.out.printf("C%.2f(%.2f)(%.2f) ,",a[i][i2], Math.max(0, a[i][2] - cs[i2-30][0]), a[i][i2] - Math.max(0, a[i][2] - cs[i2-30][0]));
                    for(int i2=30+lenc;i2<30+lenc+lenp;i2++)
                        System.out.printf("P%.2f(%.2f)(%.2f) ,",a[i][i2], Math.max(0, ps[i2-30-lenc][0] - a[i][2]), a[i][i2] - Math.max(0, ps[i2-30-lenc][0] - a[i][2]));
                    for(int i2=30+lenc+lenp;i2<30+lenc+lenp+lenm;i2++)
                        System.out.printf("M%.2f(%.2f)(%.2f) ,",a[i][i2], Math.max(0, a[i][2] - ms[i2-30-lenc-lenp][0]), a[i][i2] - Math.max(0, a[i][2] - ms[i2-30-lenc-lenp][0]));
                    for(int i2=30+lenc+lenp+lenm;i2<30+lenc+lenp+lenm+lenn;i2++)
                        System.out.printf("N%.2f(%.2f)(%.2f) ,",a[i][i2], Math.max(0, ns[i2-30-lenc-lenp-lenm][0] -  a[i][2]), a[i][i2] - Math.max(0, ns[i2-30-lenc-lenp-lenm][0] -  a[i][2]));

                    for(int i2=30;i2<30+lenc;i2++)
                        System.out.printf(" | %.2f |\t",((a[i][i2]-cs[i2-30][1])*cs[i2-30][2]));
                    for(int i2=30+lenc;i2<30+lenc+lenp;i2++)
                        System.out.printf(" | %.2f |\t",((a[i][i2]-ps[i2-30-lenc][1])*ps[i2-30-lenc][2]));
                    for(int i2=30+lenc+lenp;i2<30+lenc+lenp+lenm;i2++)
                        System.out.printf(" | %.2f |\t",((ms[i2-30-lenc-lenp][1]-a[i][i2])*ms[i2-30-lenc-lenp][2]));
                    for(int i2=30+lenc+lenp+lenm;i2<30+lenc+lenp+lenm+lenn;i2++)
                        System.out.printf(" | %.2f |\t",((ns[i2-30-lenc-lenp-lenm][1]-a[i][i2])*ns[i2-30-lenc-lenp-lenm][2]));     

                    System.out.printf("T| %.2f |\t",a[i][54]); 

                    System.out.printf("| ");
                    if(lenc>0)
                        System.out.printf("C %.2f, ",a[i][50]);
                    if(lenp>0)
                        System.out.printf("P %.2f, ",a[i][51]);
                    if(lenm>0)
                        System.out.printf("M %.2f, ",a[i][52]);
                    if(lenn>0)
                        System.out.printf("N %.2f, ",a[i][53]); 
                    System.out.printf(" |");

                    System.out.println();

                }
            }

        }
        else
        {
            // Loop through the range of price at expiry
            for (double priceAtExpiryPercentage = minPriceAtExpiryPercentage; priceAtExpiryPercentage <= maxPriceAtExpiryPercentage; priceAtExpiryPercentage += priceStepPercentage) {

                if(cnt2==1)
                {    
                    priceAtExpiryPercentage = minPriceAtExpiryPercentage+ priceStepPercentage;
                    cnt2++;
                }
                if(cnt2==0)
                {
                    priceAtExpiryPercentage=-0.5;
                    cnt2++;
                }
                else if((priceAtExpiryPercentage+priceStepPercentage)>maxPriceAtExpiryPercentage)
                {
                    priceAtExpiryPercentage=0.5;
                    cnt2++;
                }

                double priceAtExpiry = spotPrice * (1 + priceAtExpiryPercentage);
                //a[l][j++]= priceAtExpiryPercentage*100;

                //for (double priceAtExpiry = spotPrice*(1+minPriceAtExpiryPercentage); priceAtExpiry <= spotPrice*(1+maxPriceAtExpiryPercentage); priceAtExpiry += 1) 
                //{

                //  double priceAtExpiryPercentage=(1-spotPrice/priceAtExpiry)*100;
                a[l][j++]= priceAtExpiryPercentage;
                a[l][j++]= spotPrice;
                a[l][j++]= priceAtExpiry; x[l]=priceAtExpiry;

                double callNetProfitCombined = 0;double callsellProfitCombined = 0;
                double putNetProfitCombined = 0;double putsellProfitCombined = 0;
                double LongNetProfitCombined = 0;tot_extr=0;short_tot_extr=0;
                double ShortNetProfitCombined = 0;
                double callshortProfitCombined = 0;double callshortsellProfitCombined = 0;
                double putshortProfitCombined = 0;double putshortsellProfitCombined = 0;
                secombinedNetProfit=0;

                /*double callNetProfit;
                // Calculate net profit for a call option
                if(callStrikePrice<=priceAtExpiry)
                callNetProfit = (priceAtExpiry - callStrikePrice - callOptionPrice) * callOptionsBought;
                else
                callNetProfit = -1*(callOptionPrice * callOptionsBought);

                callBreakeven = callStrikePrice+callOptionPrice;*/
                cntt0=10;cntt=30;
                for(int i2=0;i2<lenc;i2++)
                {
                    calculateCallNetProfit(priceAtExpiry,cs[i2][0], cs[i2][1], cs[i2][2]);
                    tr_fees += cs[i2][1]*cs[i2][2]*tr_f_per;
                    callNetProfitCombined += callNetProfit;// - cs[i2][1]*cs[i2][2]*tr_f_per;
                    calculateCallOptionPrice(priceAtExpiry, cs[i2][0],sp, cs[i2][1], cs[i2][3], cs[i2][4], cs[i2][1]-Math.max(0, sp - cs[i2][0]),intervall, tot_intervall,voltt,tot_voltt);
                    a[l][cntt0++]= callNetProfit;a[l][cntt++]= newCallOptionPrice;//System.out.println("newcallsp[i2] "+newcallsp[i2]);
                    callsellProfitCombined += (newCallOptionPrice-cs[i2][1])*cs[i2][2];//a[l][11]=newCallOptionPrice;
                    tot_extr+=newExtrinsicValue*cs[i2][2];//(cs[i2][1]-Math.max(0, sp - cs[i2][0]))*cs[i2][2];
                }
                //putBreakeven = putStrikePrice-putOptionPrice;`
                for(int i2=0;i2<lenp;i2++)
                {
                    calculateputNetProfit(priceAtExpiry,ps[i2][0], ps[i2][1], ps[i2][2]);
                    tr_fees += ps[i2][1]*ps[i2][2]*tr_f_per;
                    putNetProfitCombined += putNetProfit;// - ps[i2][1]*ps[i2][2]*tr_f_per;;
                    calculatePutOptionPrice(priceAtExpiry, ps[i2][0], sp, ps[i2][1], ps[i2][3],ps[i2][4], ps[i2][1]-Math.max(0, ps[i2][0]- sp),intervall, tot_intervall,voltt,tot_voltt);
                    a[l][cntt0++]= putNetProfit;a[l][cntt++]=newPutOptionPrice;//System.out.println("newputsp[i2] "+newputsp[i2]);
                    putsellProfitCombined += (newPutOptionPrice-ps[i2][1])*ps[i2][2];//a[l][12]=newPutOptionPrice;
                    tot_extr+=newExtrinsicValue*ps[i2][2];//(ps[i2][1]-Math.max(0, ps[i2][0] - sp))*ps[i2][2];
                }
                double combinedSmallBreakeven;
                // Calculate profit percentage for a call option
                double callProfitPercentage = (callNetProfit / (callOptionPrice * callOptionsBought)) * 100;

                // Calculate probability percentage for a call option
                double callProbabilityPercentage = ((priceAtExpiry - callStrikePrice) / (priceAtExpiry)) * 100;

                //if((putStrikePrice-putOptionPrice)>=callStrikePrice)
                //{comninedSmallBreakeven=putStrikePrice-putOptionPrice;}

                //System.out.println();System.out.println();
                /*System.out.println("\n\n\n----- Price at Expiry: " + priceAtExpiry + " (Percentage: " + priceAtExpiryPercentage * 100 + "%) Spot Price "+spotPrice+" ----- Call, "+callStrikePrice+", "+callOptionPrice+"  ; Put, "+putStrikePrice+", "+putOptionPrice);
                System.out.println("\n----- Call breakeven "+callBreakeven+" , Put breakeven "+putBreakeven+" -----\n");
                System.out.println("\n----- Call Option Analysis -----\n");
                System.out.println("Call Option Net Profit: $" + callNetProfit);
                System.out.println("Call Option Profit Percentage: " + callProfitPercentage + "%");
                System.out.println("Call Option Probability Percentage: " + callProbabilityPercentage + "%");
                 */   
                /*
                double putNetProfit;
                // Calculate net profit for a put option
                if(putStrikePrice>=priceAtExpiry)
                putNetProfit = (putStrikePrice - priceAtExpiry - putOptionPrice) * putoptionsbought;
                else
                putNetProfit = -1*(putOptionPrice * putoptionsbought);
                 */

                // Calculate profit percentage for a put option
                double putProfitPercentage = (putNetProfit / (putOptionPrice * putoptionsbought)) * 100;

                // Calculate probability percentage for a put option
                double putProbabilityPercentage = ((spotPrice - putStrikePrice) / (putStrikePrice - priceAtExpiry)) * 100;

                /*System.out.println("\n----- Put Option Analysis -----\n");
                System.out.println("Put Option Net Profit: $" + putNetProfit);
                System.out.println("Put Option Profit Percentage: " + putProfitPercentage + "%");
                System.out.println("Put Option Probability Percentage: " + putProbabilityPercentage + "%");
                 */

                for(int i2=0;i2<lenl;i2++)
                {
                    calculateLongNetProfit(priceAtExpiry,ls[i2][0], ls[i2][1], ls[i2][2], ls[i2][3], ls[i2][4]);
                    LongNetProfitCombined += LongNetProfit;
                    a[l][cntt0++]= LongNetProfit;
                }

                for(int i2=0;i2<lens;i2++)
                {
                    calculateShortNetProfit(priceAtExpiry,ss[i2][0], ss[i2][1], ss[i2][2], ss[i2][3], ss[i2][4]);
                    ShortNetProfitCombined += ShortNetProfit;
                    a[l][cntt0++]= ShortNetProfit;
                }

                for(int i2=0;i2<lenm;i2++)
                {
                    calculateCallshortProfit(priceAtExpiry,ms[i2][0], ms[i2][1], ms[i2][2]);
                    tr_fees += ms[i2][1]*ms[i2][2]*tr_f_per;
                    callshortProfitCombined += callshortProfit;// - ms[i2][1]*ms[i2][2]*tr_f_per;
                    calculateCallOptionPrice(priceAtExpiry, ms[i2][0], sp, ms[i2][1], ms[i2][3],ms[i2][4],ms[i2][1]-Math.max(0, sp - ms[i2][0]),intervall, tot_intervall,voltt,tot_voltt);
                    a[l][cntt0++]= callshortProfit;a[l][cntt++]=newCallOptionPrice; //System.out.println("newcallshortsp[i2] "+newcallshortsp[i2]);
                    callshortsellProfitCombined += (ms[i2][1]-newCallOptionPrice)*ms[i2][2];
                    short_tot_extr+=newExtrinsicValue*ps[i2][2];//(ms[i2][1]-Math.max(0, sp - ms[i2][0]))*ms[i2][2];
                }

                for(int i2=0;i2<lenn;i2++)
                {
                    calculateputshortProfit(priceAtExpiry,ns[i2][0], ns[i2][1], ns[i2][2]);
                    tr_fees += ns[i2][1]*ns[i2][2]*tr_f_per;
                    putshortProfitCombined += putshortProfit;// - ns[i2][1]*ns[i2][2]*tr_f_per;
                    calculatePutOptionPrice(priceAtExpiry, ns[i2][0], sp, ns[i2][1], ns[i2][3],ns[i2][4], ns[i2][1]-Math.max(0, ns[i2][0]- sp),intervall, tot_intervall,voltt,tot_voltt);
                    a[l][cntt0++]= putshortProfit;a[l][cntt++]=newPutOptionPrice; //System.out.println("newputshortsp[i2] "+newputshortsp[i2]);
                    putshortsellProfitCombined += (ns[i2][1]-newPutOptionPrice)*ns[i2][2];
                    short_tot_extr+=newExtrinsicValue*ns[i2][2];//(ns[i2][1]-Math.max(0, ns[i2][0] - sp))*ns[i2][2];
                }

                // Calculate net profit for combined position (call + put)

                double combinedNetProfit = callNetProfitCombined + putNetProfitCombined + LongNetProfitCombined + ShortNetProfitCombined
                    +callshortProfitCombined+putshortProfitCombined;
                secombinedNetProfit = callsellProfitCombined+putsellProfitCombined+callshortsellProfitCombined+putshortsellProfitCombined;

                // Calculate profit percentage for combined position (call + put)
                double callProfitPercentageCombined = (callNetProfitCombined / (callOptionPrice * callOptionsBought)) * 100;
                double putProfitPercentageCombined = (putNetProfitCombined / (putOptionPrice * putoptionsbought)) * 100;
                double combinedProfitPercentage = (combinedNetProfit / (callOptionPrice*callOptionsBought + putOptionPrice*putoptionsbought)) * 100;

                a[l][j++]= callNetProfitCombined;
                a[l][j++]= putNetProfitCombined;
                a[l][j++]= combinedNetProfit; y[l]=combinedNetProfit;
                a[l][j++]= LongNetProfitCombined;
                a[l][j++]= ShortNetProfitCombined;
                a[l][j++]= callshortProfitCombined;
                a[l][j++]= putshortProfitCombined;
                cntt00=50;
                a[l][cntt00++]= callsellProfitCombined;
                a[l][cntt00++]= putsellProfitCombined;
                a[l][cntt00++]= callshortsellProfitCombined;
                a[l][cntt00++]= putshortsellProfitCombined;
                a[l][cntt00++]= secombinedNetProfit;

                if(cnt2==1)
                    far_prof1=combinedNetProfit;
                if(cnt2==3)
                    far_prof2=combinedNetProfit;
                //a[l][j++]= tot_extr;
                //a[l][j++]= secombinedNetProfit;

                //-0.045,-0.03,-0.015,0,0.015,0.03,0.045
                if(-0.075>priceAtExpiryPercentage)//(profit_index1>priceAtExpiryPercentage)
                {profit10=combinedNetProfit;seprofit10=secombinedNetProfit;prof_ind10=priceAtExpiry;}
                if(-0.045>priceAtExpiryPercentage)//(profit_index1>priceAtExpiryPercentage)
                {profit1=combinedNetProfit;seprofit1=secombinedNetProfit;prof_ind1=priceAtExpiry;}
                if(-0.03>priceAtExpiryPercentage)
                {profit3=combinedNetProfit;seprofit3=secombinedNetProfit;prof_ind3=priceAtExpiry;}
                if(-0.015>priceAtExpiryPercentage)
                {profit6=combinedNetProfit;seprofit6=secombinedNetProfit;prof_ind6=priceAtExpiry;}
                if(-0.005>priceAtExpiryPercentage)
                {profit8=combinedNetProfit;seprofit8=secombinedNetProfit;prof_ind8=priceAtExpiry;}
                if(0.000>priceAtExpiryPercentage||0.001>priceAtExpiryPercentage)
                {profit4=combinedNetProfit;seprofit4=secombinedNetProfit;prof_ind4=priceAtExpiry;}
                if(0.005>priceAtExpiryPercentage)
                {profit9=combinedNetProfit;seprofit9=secombinedNetProfit;prof_ind9=priceAtExpiry;}
                if(0.015>priceAtExpiryPercentage)
                {profit7=combinedNetProfit;seprofit7=secombinedNetProfit;prof_ind7=priceAtExpiry;}
                if(0.03>priceAtExpiryPercentage)
                {profit5=combinedNetProfit;seprofit5=secombinedNetProfit;prof_ind5=priceAtExpiry;}
                if(0.045>priceAtExpiryPercentage)//(profit_index2>priceAtExpiryPercentage)
                {profit2=combinedNetProfit;seprofit2=secombinedNetProfit;prof_ind2=priceAtExpiry;}
                if(0.075>priceAtExpiryPercentage)//(profit_index1>priceAtExpiryPercentage)
                {profit11=combinedNetProfit;seprofit11=secombinedNetProfit;prof_ind11=priceAtExpiry;}
                //far_prof1, profit1, profit3, profit6, profit8, profit4, profit9, profit7, profit5, profit2, far_prof2
                /*System.out.println("\n----- Combined Position (Call + Put) Analysis -----\n");
                System.out.println("Combined Position Net Profit: $" + combinedNetProfit);
                System.out.println("Combined Position Profit Percentage: " + combinedProfitPercentage + "%");
                System.out.println("\n\n");
                 */
                l++;j=0;
            }

            firstBreakeven=0; LastBreakeven=0;double minus=-1;

            String dt[][]=new String[30][100];
            for(int i=0;i<30;i++)
                for(int j1=0;j1<100;j1++)
                    dt[i][j1]=" ";

            //double pe=-0.025;
            pe_l=0;cnp_l=0; m_pe_l=0;num_pos=0;num_mid_pos=0;profbyper=0;
            prof_tott=0;pos_prof_tott=0;num_pos_nr=0;conty1=0;conty2=0;
            double conty_chk=0;
            if(tt_amt>0)
                conty_chk=tt_amt*4/100.0;
            else
                conty_chk=0;
            breakevenss=new double[100][3];
            num_brk=0;
            double signn=0;
            //num_pos,profbyper,prof_tott,pos_prof_tott

            min = a[0][5];
            max = a[0][5];

            md_min = a[1][5];
            md_max = a[1][5];
            if(a[1][5]<0)
            {
                signn=0;
                breakevenss[num_brk][0]=signn;
                breakevenss[num_brk][1]=a[1][2];
            }
            else
            {
                signn=1;
                breakevenss[num_brk][0]=signn;
                breakevenss[num_brk][1]=a[1][2];
            }
            cust_min=a[(int)(l/2.0)][5];
            for (int i = 0; i < l; i++) {
                if(i!=0){
                    if(a[i-1][5]>0&&a[i][5]<0)
                        firstBreakeven=a[i-1][2];
                    if(a[i-1][5]<0&&a[i][5]>0)
                        LastBreakeven=a[i][2];
                    if(a[i][5]>0)
                        minus=1;
                }
                if(a[i][5]>0)
                    minus=1;
                if(a[i][5]>0)
                    num_pos++;

                if(i>0&&i<(l-1))
                {
                    if(signn==0&&a[i][5]>0)   
                    {
                        signn=1;
                        breakevenss[num_brk++][2]=a[i-1][2];
                        breakevenss[num_brk][0]=signn;
                        breakevenss[num_brk][1]=a[i][2];
                    }
                    else if(signn==1&&a[i][5]<0)
                    {
                        signn=0;
                        breakevenss[num_brk++][2]=a[i-1][2];
                        breakevenss[num_brk][0]=signn;
                        breakevenss[num_brk][1]=a[i][2];
                    }
                    else
                        breakevenss[num_brk][2]=a[i][2];

                    if (a[i][5] < md_min) {
                        md_min = a[i][5];
                    }

                    if (a[i][5] > md_max) {
                        md_max = a[i][5];
                    }

                    prof_tott+=a[i][5];
                    if(a[i][5]>0)
                        pos_prof_tott+=a[i][5];

                    if(a[i][0]!=0)
                        profbyper+=a[i][5]/Math.pow(Math.abs(a[i][0]*10),10);

                    if(a[i][5]>0&&a[i][0]!=0)
                        num_pos_nr+=1/Math.pow(Math.abs(a[i][0]*100),40)-Math.abs(a[i][0]*100);
                    if(a[i][5]<0&&a[i][0]!=0)
                        num_pos_nr-=1/Math.pow(Math.abs(a[i][0]*100),40)-Math.abs(a[i][0]*100);

                    if(a[i][0]<0)
                    {
                        if(a[i][5]>conty_chk)
                            conty1++;
                        else
                            conty1=-1;
                    }
                    if(a[i][0]>0)
                    {
                        if(a[i][5]>conty_chk&&conty2!=-2)
                            conty2++;
                        else
                            conty2=-2;
                    }
                    //if(a[i][0]>-0.0015&&a[i][0]<0.0015&&a[i][5]<0)
                    //  profbyper=-1000000;

                }

                if (a[i][5] < min) {
                    min = a[i][5];
                }

                if (a[i][5] > max) {
                    max = a[i][5];
                }

                if(a[i][0]<=0.025&&a[i][0]>=-0.025)
                    if (a[i][5] < cust_min) {
                        cust_min = a[i][5];
                    }

                if(a[i][0]<=0.025&&a[i][0]>=-0.025)
                {

                    if(a[i][5]>0)
                        num_mid_pos++;

                    for(int k1=cnpp.length-1;k1>=0;k1--)
                    {
                        if(cnpp[k1]>a[i][5])
                        {
                            cnp_l=k1;
                            break;
                        }
                        if(k1==0&&cnpp[k1]<a[i][5])
                        {
                            cnp_l=k1;
                            break;
                        }
                    }
                    //System.out.println(" cnp_l "+cnp_l);
                    //pe+=0.0025;

                    dt[cnp_l][pe_l]="*";
                    //System.out.println(" a[i][5] "+a[i][5]+" storing cnp_l "+cnp_l+" pe_l "+pe_l+"  dt[cnp_l][pe_l] "+dt[cnp_l][pe_l]);
                    if(a[i][0]>-0.0005&&a[i][0]<0.0005)
                        m_pe_l=pe_l;
                    pe_l++;
                }

            }
            if(minus==-1)
            {firstBreakeven=-1112; LastBreakeven=-9998;}
            if(a[0][5]<0)
            {firstBreakeven=-1111;}
            if(a[l-1][5]<0)
            {LastBreakeven=-9999;}

            //System.out.println("final pe_l is"+pe_l+" m_pe_l "+m_pe_l);

            s_dt="";
            //System.out.println("\n");
            for(int i=0;i<12;i++)
            {
                //System.out.print("\n{ ");
                s_dt+="+";
                for(int j1=0;j1<pe_l;j1++)
                {
                    if(j1==m_pe_l&&dt[i][j1].charAt(0)!='*')
                    {//System.out.print("|");
                        s_dt+="|";}
                    else if(j1==m_pe_l&&dt[i][j1].charAt(0)=='*')
                    {//System.out.print("!");
                        s_dt+="!";}
                    else if(j1!=m_pe_l&&dt[i][j1].charAt(0)!='*'&&i==7)
                    {//System.out.print("-");
                        s_dt+="-";}
                    else if(j1!=m_pe_l&&dt[i][j1].charAt(0)=='*'&&i==7)
                    {//System.out.print("=");
                        s_dt+="=";}
                    else
                    {//System.out.print(dt[i][j1]);
                        s_dt+=dt[i][j1];}
                }

            }
            dt=null;

            //System.out.println("\n");

            /*
            System.out.println(s_dt);

            for(int i=0;i<s_dt.length();i++)
            {
            if(s_dt.charAt(i)=='+')
            System.out.print("\n{ ");
            else
            System.out.print(s_dt.charAt(i));
            }
             */
            // Example data for x and y values, including negative values
            //double[] 
            xValues = new double[l-2];//{-3, -2, -1, 0, 1, 2, 3};
            //double[] 
            yValues = new double[l-2];//{-4, -3, -2, -1, 0, 1, 4};
            y1Values = new double[l-2];

            for(int i=1;i<l-1;i++)
            {
                xValues[i-1]=a[i][0];
                yValues[i-1]=a[i][5];
                y1Values[i-1]=a[i][54];
            }
            x_data=convertToStringArr(xValues, l-2);
            y_data=convertToStringArr(yValues, l-2);
            y1_data=convertToStringArr(y1Values, l-2);
            ///*

            if(pnntt==1)
            {
                System.out.println("\n\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("C%.2f , %.2f\n",callStrikePrice,callOptionPrice);
                System.out.printf("P%.2f , %.2f\n",putStrikePrice,putOptionPrice);
                System.out.printf("Breakeven %.2f , %.2f\n",firstBreakeven, LastBreakeven);
                System.out.printf("Min %.2f\n",min);
                System.out.println("\n\n---------------------------------------------------------------------------------------------------------------------------\n\n\n");
                for(int i=0;i<l;i++)
                {

                    if(i!=0){
                        if((a[i-1][5]>0&&a[i][5]<0)||(a[i-1][5]<0&&a[i][5]>0))
                            System.out.println("=======================================================================================================================================================================================================================================================================================================================================================================================================================================================");
                        else
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    }
                    for(int i2=0;i2<lenc;i2++)
                        System.out.printf("C%.2f , %.2f , %.2f\t",cs[i2][0],cs[i2][1],cs[i2][2],cs[i2][3],cs[i2][4]);
                    for(int i2=0;i2<lenp;i2++)
                        System.out.printf("P%.2f , %.2f , %.2f\t",ps[i2][0],ps[i2][1],ps[i2][2],ps[i2][3],ps[i2][4]);
                    for(int i2=0;i2<lenl;i2++)
                        System.out.printf("L%.2f , %.2f, %.2f \t",ls[i2][0],ls[i2][1],ls[i2][2]);
                    for(int i2=0;i2<lens;i2++)
                        System.out.printf("S%.2f , %.2f, %.2f \t",ss[i2][0],ss[i2][1],ss[i2][2]);
                    for(int i2=0;i2<lenm;i2++)
                        System.out.printf("M%.2f , %.2f , %.2f\t",ms[i2][0],ms[i2][1],ms[i2][2],ms[i2][3],ms[i2][4]);
                    for(int i2=0;i2<lenn;i2++)
                        System.out.printf("N%.2f , %.2f , %.2f\t",ns[i2][0],ns[i2][1],ns[i2][2],ms[i2][3],ms[i2][4]);

                    for(int k=0;k<3;k++)
                    {
                        System.out.printf("%.2f ,\t",a[i][k]);   

                    }

                    for(int i2=10;i2<10+lenc;i2++)
                        System.out.printf("| %.2f |\t",a[i][i2]);
                    for(int i2=10+lenc;i2<10+lenc+lenp;i2++)
                        System.out.printf("| %.2f |\t",a[i][i2]);
                    for(int i2=10+lenc+lenp;i2<10+lenc+lenp+lenl;i2++)
                        System.out.printf("| %.2f |\t",a[i][i2]);
                    for(int i2=10+lenc+lenp+lenl;i2<10+lenc+lenp+lenl+lens;i2++)
                        System.out.printf("| %.2f |\t",a[i][i2]);
                    for(int i2=10+lenc+lenp+lenl+lens;i2<10+lenc+lenp+lenl+lens+lenm;i2++)
                        System.out.printf("| %.2f |\t",a[i][i2]);
                    for(int i2=10+lenc+lenp+lenl+lens+lenm;i2<10+lenc+lenp+lenl+lens+lenm+lenn;i2++)
                        System.out.printf("| %.2f |\t",a[i][i2]);

                    System.out.printf(" | %.2f | \t",a[i][5]);    
                    for(int i2=30;i2<30+lenc;i2++)
                        System.out.printf("C%.2f(%.2f)(%.2f) ,",a[i][i2], Math.max(0, a[i][2] - cs[i2-30][0]), a[i][i2] - Math.max(0, a[i][2] - cs[i2-30][0]));
                    for(int i2=30+lenc;i2<30+lenc+lenp;i2++)
                        System.out.printf("P%.2f(%.2f)(%.2f) ,",a[i][i2], Math.max(0, ps[i2-30-lenc][0] - a[i][2]), a[i][i2] - Math.max(0, ps[i2-30-lenc][0] - a[i][2]));
                    for(int i2=30+lenc+lenp;i2<30+lenc+lenp+lenm;i2++)
                        System.out.printf("M%.2f(%.2f)(%.2f) ,",a[i][i2], Math.max(0, a[i][2] - ms[i2-30-lenc-lenp][0]), a[i][i2] - Math.max(0, a[i][2] - ms[i2-30-lenc-lenp][0]));
                    for(int i2=30+lenc+lenp+lenm;i2<30+lenc+lenp+lenm+lenn;i2++)
                        System.out.printf("N%.2f(%.2f)(%.2f) ,",a[i][i2], Math.max(0, ns[i2-30-lenc-lenp-lenm][0] -  a[i][2]), a[i][i2] - Math.max(0, ns[i2-30-lenc-lenp-lenm][0] -  a[i][2]));

                    for(int i2=30;i2<30+lenc;i2++)
                        System.out.printf(" | %.2f |\t",((a[i][i2]-cs[i2-30][1])*cs[i2-30][2]));
                    for(int i2=30+lenc;i2<30+lenc+lenp;i2++)
                        System.out.printf(" | %.2f |\t",((a[i][i2]-ps[i2-30-lenc][1])*ps[i2-30-lenc][2]));
                    for(int i2=30+lenc+lenp;i2<30+lenc+lenp+lenm;i2++)
                        System.out.printf(" | %.2f |\t",((ms[i2-30-lenc-lenp][1]-a[i][i2])*ms[i2-30-lenc-lenp][2]));
                    for(int i2=30+lenc+lenp+lenm;i2<30+lenc+lenp+lenm+lenn;i2++)
                        System.out.printf(" | %.2f |\t",((ns[i2-30-lenc-lenp-lenm][1]-a[i][i2])*ns[i2-30-lenc-lenp-lenm][2]));     

                    System.out.printf("T| %.2f |\t",a[i][54]); 

                    System.out.printf("| ");
                    if(lenc>0)
                        System.out.printf("C %.2f, ",a[i][50]);
                    if(lenp>0)
                        System.out.printf("P %.2f, ",a[i][51]);
                    if(lenm>0)
                        System.out.printf("M %.2f, ",a[i][52]);
                    if(lenn>0)
                        System.out.printf("N %.2f, ",a[i][53]); 
                    System.out.printf(" |");

                    System.out.println();

                }

                System.out.println("\nBreakeven is ");
                for(int i2=0;i2<=num_brk;i2++)
                    System.out.printf("( %.2f, %.2f, %.2f ) ",breakevenss[i2][0],breakevenss[i2][1],breakevenss[i2][2]);
                System.out.printf("\n %.1f | %.1f, %.1f, %.1f, %.1f || %.1f || %.1f, %.1f, %.1f, %.1f | %.1f\n"
                ,far_prof1, profit1, profit3, profit6, profit8, profit4, profit9, profit7, profit5, profit2, far_prof2);
                System.out.printf("Min %.2f , Max %.2f\n",md_min,md_max);
                double risk_rewa=md_max/Math.abs(md_min);double risk_rewa1=max/Math.abs(min);
                System.out.printf("md_rr: %.2f , rr: %.2f\n",risk_rewa,risk_rewa1);
                System.out.printf("Far Min %.2f\n",min);
                if(lenc>0)
                    System.out.printf("Tot_C %.2f ",tot_c);
                if(lenp>0)
                    System.out.printf("Tot_P %.2f ",tot_p);
                if(lenm>0)
                    System.out.printf("Tot_M %.2f ",tot_m);
                if(lenn>0)
                    System.out.printf("Tot_N %.2f ",tot_n); 
                System.out.printf("\nTot_Cr %.2f\n",tot_cr);
                System.out.println("x data: "+x_data);
                System.out.println("y_data: "+y_data);
                System.out.println("y1_data: "+y1_data);
                System.out.println();

                /*
                // Create an instance of ScatterPlotExample and pass xValues and yValues
                ScatterPlotExample scatterPlot = new ScatterPlotExample("Scatter Plot Example", xValues, yValues);
                scatterPlot.setSize(800, 600);
                scatterPlot.setLocationRelativeTo(null);
                scatterPlot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                scatterPlot.setVisible(true);
                 */
            }
            //*/
            //System.exit(0);
            //scanner.close();
        }
    }

    MarketSimulator ob97=new MarketSimulator();
    TxtFileOp ob61=new TxtFileOp();
    double mk_dyn[][]=new double[10][10];

    public static void main()
    {
        OptionProfitAnalysis ob1=new OptionProfitAnalysis();
        String opt_sss[]={"36000","6","2","0","10","5","10","N5", "P6"};//,"M1"};//"N1","P2"};size
        double opt_sz[]={2};
        ob1.maiin(opt_sss,opt_sz,0,1,12,8,1);
    }

    public void maiin(String opt_ss[], double opt_sz[],int prntt,int simu,int numofdays,int nummofsim,int oldsim) {
        /*double css[][]={{1750,118,1},{1925,1.8,1}};double pss[][]={{1800,2.3,1},{1950,108.9,1}};
        mai(css,2,pss,2,1858.0);*///;mai(1825,36.8,1875,30.3,1850,0,1);
        //double lss[][]={{1834,1,1},{1890,2,0}};double sss[][]={{1838,1,0},{1870,1,0}};mss[][]={{28600,44,1,0.5,0.005}
        /*
        double css[][]={{28600,165,1,0.15,0.0015},{28800,40,1,0.5,0.005}};double pss[][]={{27900,145,1,0.3,0.00325},{27600,25,1,0.5,0.005}};
        double mss[][]={{28600,44,1,0.5,0.005},{1900,1,1,0.5,0.005}};double nss[][]={{27400,35,1,0.5,0.005},{1850,3.9,32.22,0.5,0.005}};
        double lss[][]={{mss[0][0]*99.3/100,2,5,1864,1800},{mss[0][0]*100.0/100,2,5,1864,1800},{mss[0][0]*100.7/100,2,5,1864,1800}};
        double sss[][]={{nss[0][0]*100.7/100,2,5,1800,1885},{nss[0][0]*100.0/100,1,5,1800,1885},{nss[0][0]*99.3/100,1,5,1800,1885}};
        lss[0][4]=lss[0][0]*99.7/100;lss[1][4]=lss[1][0]*99.7/100;lss[2][4]=lss[2][0]*99.7/100;
        sss[0][4]=sss[0][0]*100.3/100;sss[1][4]=sss[1][0]*100.3/100;sss[2][4]=sss[2][0]*100.3/100;
         */

        int opt_nn[]=new int[opt_ss.length-7];int ln=0;
        for(int i45=6;i45<opt_ss.length;i45++)
        {
            if(opt_ss[i45].charAt(0)=='C'||opt_ss[i45].charAt(0)=='M')
            {
                opt_nn[ln++]=Integer.valueOf(opt_ss[i45].substring(1,opt_ss[i45].length()));
            }
            if(opt_ss[i45].charAt(0)=='P'||opt_ss[i45].charAt(0)=='N')
            {
                opt_nn[ln++]=Integer.valueOf(opt_ss[i45].substring(1,opt_ss[i45].length()))+60;
            }
        }

        OptionChain ob31=new OptionChain();
        ob31.dataopt(Double.valueOf(opt_ss[0]),Integer.valueOf(opt_ss[1])
        ,Double.valueOf(opt_ss[2]),opt_nn,Integer.valueOf(opt_ss[3]),Integer.valueOf(opt_ss[4])
        ,Integer.valueOf(opt_ss[5]),Integer.valueOf(opt_ss[6]));

        double css[][]=new double[10][5];
        double pss[][]=new double[10][5];
        double mss[][]=new double[10][5];
        double nss[][]=new double[10][5];
        double lss[][]=new double[10][5];
        double sss[][]=new double[10][5];

        int lennc=0,lennp=0,lennm=0,lennn=0;
        if(opt_sz.length!=opt_ss.length-7)
        {
            opt_sz=new double[opt_ss.length-7];
            for(int i33=0;i33<opt_ss.length-7;i33++)
                opt_sz[i33]=1;
        }

        for(int i45=7;i45<opt_ss.length;i45++)
        {
            int i35=i45-7;
            if(opt_ss[i45].charAt(0)=='C')
            {
                css[lennc][0]=ob31.od_list[i35][0];
                css[lennc][1]=ob31.od_list[i35][1];
                css[lennc][2]=opt_sz[i35];//1;
                css[lennc][3]=ob31.od_list[i35][2];
                css[lennc++][4]=ob31.od_list[i35][3];
            }
            if(opt_ss[i45].charAt(0)=='P')
            {
                pss[lennp][0]=ob31.od_list[i35][0];
                pss[lennp][1]=ob31.od_list[i35][1];
                pss[lennp][2]=opt_sz[i35];//1;
                pss[lennp][3]=ob31.od_list[i35][2];
                pss[lennp++][4]=ob31.od_list[i35][3];
            }
            if(opt_ss[i45].charAt(0)=='M')
            {
                mss[lennm][0]=ob31.od_list[i35][0];
                mss[lennm][1]=ob31.od_list[i35][1];
                mss[lennm][2]=opt_sz[i35];//1;
                mss[lennm][3]=ob31.od_list[i35][2];
                mss[lennm++][4]=ob31.od_list[i35][3];
            }
            if(opt_ss[i45].charAt(0)=='N')
            {
                nss[lennn][0]=ob31.od_list[i35][0];
                nss[lennn][1]=ob31.od_list[i35][1];
                nss[lennn][2]=opt_sz[i35];//1;
                nss[lennn][3]=ob31.od_list[i35][2];
                nss[lennn++][4]=ob31.od_list[i35][3];
            }
        }

        for(int i45=0;i45<lennc;i45++)
        {
            System.out.print("C "+css[i45][0]+", ");System.out.print(css[i45][1]+", ");
            System.out.print(css[i45][2]+", ");System.out.print(css[i45][3]+", ");
            System.out.print(css[i45][4]+", ");System.out.println();
        }
        for(int i45=0;i45<lennp;i45++)
        {
            System.out.print("P "+pss[i45][0]+", ");System.out.print(pss[i45][1]+", ");
            System.out.print(pss[i45][2]+", ");System.out.print(pss[i45][3]+", ");
            System.out.print(pss[i45][4]+", ");System.out.println();
        }
        for(int i45=0;i45<lennm;i45++)
        {
            System.out.print("M "+mss[i45][0]+", ");System.out.print(mss[i45][1]+", ");
            System.out.print(mss[i45][2]+", ");System.out.print(mss[i45][3]+", ");
            System.out.print(mss[i45][4]+", ");System.out.println();
        }
        for(int i45=0;i45<lennn;i45++)
        {
            System.out.print("N "+nss[i45][0]+", ");System.out.print(nss[i45][1]+", ");
            System.out.print(nss[i45][2]+", ");System.out.print(nss[i45][3]+", ");
            System.out.print(nss[i45][4]+", ");System.out.println();
        }

        int lengt=ob31.ndll_gam.length;

        ndll_delta=new double[lengt][5];
        ndl_del_len=lengt;

        ndll_gam= new double[lengt][7];

        for (int i1=0;i1<lengt;i1++)
        {
            ndll_gam[i1][0]=ob31.ndll_gam[i1][0];
            ndll_gam[i1][1]=ob31.ndll_gam[i1][1];
            ndll_gam[i1][2]=ob31.ndll_gam[i1][2];
            ndll_gam[i1][3]=ob31.ndll_gam[i1][3];
            ndll_gam[i1][4]=ob31.ndll_gam[i1][4];
            ndll_gam[i1][5]=ob31.ndll_gam[i1][5];
            ndll_gam[i1][6]=ob31.ndll_gam[i1][6];

            ndll_delta[i1][0]=ndll_gam[i1][1];
            ndll_delta[i1][1]=ndll_gam[i1][2];
            ndll_delta[i1][2]=ndll_gam[i1][3];
            ndll_delta[i1][3]=ndll_gam[i1][4];
            ndll_delta[i1][4]=ndll_gam[i1][5];
        }

        atm_ind_c=ob31.atm_index_c;
        atm_ind_p=ob31.atm_index_c;
        atm_extr_c=ndll_delta[atm_ind_c][1];
        atm_extr_p=ndll_delta[atm_ind_p][3];
        //OptionProfitAnalysis ob1=new OptionProfitAnalysis();
        double xx_time[]=new double[10],yy_time[]=new double[10];
        if(simu==1)
        {
            //ob97=new MarketSimulator();
            tm_l=0;
            Scanner sc =new Scanner(System.in);
            String ss_n="";System.out.println("mid num of days "+numofdays);

            String stt="marketdatafl",stt1="marketdatafl1",stt2="marketresultfl",stt3="marketresultfl1";
            String mk_data[][]=new String[10][10];
            String mk_data_x[]=new String[10];
            double mk_data_d[][]=new double[10][10];
            double mk_data_x_d[]=new double[10];
            if(oldsim==1)
            {
                System.out.println("\n in old sim ");
                ob61.rd_two(stt,0);
                mk_data=ob61.new_aa;
                ob30.pnt(mk_data);

                ob61.rd(stt1,0);
                mk_data_x=ob61.new_a;
                ob30.pnt(mk_data_x);

                nummofsim=mk_data.length;

                mk_data_d=ob30.copy_to_double(mk_data);
                mk_data_x_d=ob30.copy_to_double(mk_data_x);
            }
            double yy_time_d[][]=new double[nummofsim][100000];
            tm_y_data=new String[nummofsim];
            //while(ss_n.equals("done")==false)
            //{

            if(oldsim==1)
            {
                System.out.println("\n in old sim 2");
                for(int i2=0;i2<nummofsim;i2++)
                {

                    xx_time=mk_data_x_d;
                    yy_time=mk_data_d[i2];
                    yy_time_d[i2]=yy_time;
                    mai(css,lennc,pss,lennp,lss,0,sss,0,mss,lennm,nss,lennn,Integer.valueOf(opt_ss[0]),prntt,-1,xx_time,yy_time);

                }
            }
            else
                for(int i2=0;i2<nummofsim;i2++)
                {
                    ob97.simulateMarketForAllDays(Double.valueOf(opt_ss[0]),1,numofdays);//ob.mai(Double.valueOf(opt_ss[0]),0);
                    ns=ob97.ns;
                    //System.out.println("back in maiin");
                    //for(int i1=0;i1<ob.n_l;i1++)
                    //{
                    //ob.mai(Double.valueOf(opt_ss[0]),i1);
                    xx_time=ob97.x;
                    yy_time=ob97.cumulativeY;//ob.ys[i1];
                    yy_time_d[i2]=yy_time;
                    mai(css,lennc,pss,lennp,lss,0,sss,0,mss,lennm,nss,lennn,Integer.valueOf(opt_ss[0]),prntt,-1,xx_time,yy_time);
                    //System.out.println("back in maiin 2");

                    //}
                }

            yy_time_d=ob30.shorti(yy_time_d,nummofsim,yy_time.length);
            String yy_time_s[][]=ob30.copy_to_str(yy_time_d);
            ob61.wrt(yy_time_s,nummofsim,stt);

            String xx_time_s[]=ob30.copy_to_str(xx_time);
            ob61.wrt(xx_time_s,stt1);

            System.out.println("\n yy_time_d : ");
            ob30.pnt(yy_time_d);
            ob30.pnt(yy_time_s);

            tm_y_data=ob30.shorti(tm_y_data,tm_l);
            double yy_data_d[][]=convertToDoubleArray(tm_y_data);
            double xx_data_d[]=convertToDoubleArray(tm_x_data);

            String yy_data_s[][]=ob30.copy_to_str(yy_data_d);
            ob61.wrt(yy_data_s,tm_l,stt2);

            String xx_data_s[]=ob30.copy_to_str(xx_data_d);
            ob61.wrt(xx_data_s,stt3);

            System.out.println("\n yy_data_d : ");
            ob30.pnt(yy_data_d);
            ob30.pnt(yy_data_s);

            OptionStrategyAnalyzer obb=new OptionStrategyAnalyzer();
            obb.maain(yy_data_d,xx_data_d);
            mk_dyn=obb.ar;

            // ss_n=sc.nextLine();
            //}
        }
        else
        {
            xx_time=new double[0];
            yy_time=new double[0];
            mai(css,lennc,pss,lennp,lss,0,sss,0,mss,lennm,nss,lennn,Integer.valueOf(opt_ss[0]),prntt,-1,xx_time,yy_time);
        }
        //mai(css,lennc,pss,lennp,lss,0,sss,0,mss,lennm,nss,lennn,Integer.valueOf(opt_ss[0]),prntt,-1,xx_time,yy_time);
    }

    public   void man(int i) {
        Scanner sc =new Scanner(System.in);
        int ratio_num=8;
        int rn[][]=new int[ratio_num][2];
        rn[0][0]=1;rn[0][1]=1;
        rn[1][0]=1;rn[1][1]=2;
        rn[2][0]=1;rn[2][1]=3;
        rn[3][0]=1;rn[3][1]=4;
        rn[4][0]=2;rn[4][1]=3;
        rn[5][0]=2;rn[5][1]=1;
        rn[6][0]=3;rn[6][1]=1;
        rn[7][0]=4;rn[7][1]=2;
        rn[8][0]=3;rn[8][1]=2;
        double dd[][]=new double[i][5];
        double dda[][]=new double[i][9];
        for (int i1=0;i1<i;i1++)
        {
            for(int j=0;j<5;j++)
            {
                System.out.println("");
                dd[i1][j]=sc.nextDouble();
            }
            System.out.println("Next");

        }
        for (int i1=0;i1<i;i1++)
        {
            for (int j1=0;j1<ratio_num;j1++)
            {
                //mai(dd[i1][0],dd[i1][1],dd[i1][2],dd[i1][3],dd[i1][4],rn[j1][0],rn[j1][1]);
                dda[i1][0]=dd[i1][0];
                dda[i1][1]=dd[i1][1];
                dda[i1][2]=rn[j1][0];
                dda[i1][3]=dd[i1][2];
                dda[i1][4]=dd[i1][3];
                dda[i1][5]=rn[j1][1];
                dda[i1][6]=firstBreakeven;
                dda[i1][7]=LastBreakeven;
                dda[i1][8]=min;
            }
        }
        for (int i1=0;i1<i;i1++)
        {
            for(int j=0;j<9;j++)
            {
                System.out.printf("%.2f , ",dda[i1][j]);
            }
            System.out.println();

        }

    }
    double tot_amt=0;//, spp=0, rn[][];
    double len_dda_chk=0;
    //  int ratio_num=0;\

    public void new_ratt(double inum, int rat_n, double tot_amtt,int mdd_arr[], double ml,int opc_l,double sp_gpp, double ln_dda_chk, int goo
    ,int inv1, int tinv1, int volt1, int tvolt1)
    {
        /*
        double inum=29000;
        double spp=inum;
        int ratio_num=4;
        tot_amt=200;
         */
        stp_nw=0;
        spta_l=0;
        lenn30=0;

        md_arr=mdd_arr;
        max_loss=-1*ml*tot_amtt;
        //max_prof=-1*ml1*tot_amtt;
        len_dda_chk=ln_dda_chk;

        double spp=inum;
        int ratio_num=rat_n;
        tot_amt=tot_amtt;

        int leng=opc_l;
        //int inv=0, tinv=10, volt=5, tvolt=10;
        int inv=inv1, tinv=tinv1, volt=volt1, tvolt=tvolt1;

        OptionChain ob32=new OptionChain();
        ob32.opcha(inum,leng,sp_gpp,inv, tinv, volt, tvolt);

        int lengt=ob32.ndll_gam.length;
        double ndl_dd[][]=new double[lengt][3];

        ndll_delta=new double[lengt][5];
        ndl_del_len=lengt;

        ndll_gam= new double[lengt][7];

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
        }

        atm_ind_c=ob32.atm_index_c;
        atm_ind_p=ob32.atm_index_c;
        atm_extr_c=ndll_delta[atm_ind_c][1];
        atm_extr_p=ndll_delta[atm_ind_p][3];

        System.out.println("\nNew ndl Chain:");
        for (int i = 0; i < lengt; i++) {
            System.out.printf("%.6f %.6f %.6f \n",ndl_dd[i][0],ndl_dd[i][1],ndl_dd[i][2]);
        }
        System.out.println("\n");

        if(goo==1)
            maka(ratio_num, 0, tot_amt, spp, ndl_dd,lengt);

    }

    public void ratt()
    {

        Scanner sc =new Scanner(System.in);
        //System.out.println("Enter 9999 to stop");
        System.out.println("Enter ratio ");
        int ratio_num=sc.nextInt();

        System.out.println("Enter amount");
        tot_amt=sc.nextDouble();
        System.out.println("Enter Spot Price");
        double spp=sc.nextDouble();

        int i=0,j=0,nd_l=1;
        double ndd[]=new double[100000];
        double nd[][]=new double[1000000][3];
        ExtractNumbersFromString ob9=new ExtractNumbersFromString();
        while(nd_l%3!=0||nd_l==0)

        {
            ndd=ob9.inp();
            nd_l=ob9.leng;
            //System.out.println("\nreceived nd_l is "+nd_l+"\n");
            //for(j=0;j<nd_l;j++)
            //  System.out.println(ndd[j]+" ");

        }
        System.out.println("nd_l is "+nd_l+"\n");
        int i7=0;
        for(int i1=0;i1<((int)(nd_l/3));i1++)//while(true)
        {
            for(j=0;j<3;j++)
            {
                nd[i1][j]=ndd[i7];//sc.nextDouble();  
                //if(nd[i][j]==9999.0)
                //  break;
                System.out.print(nd[i1][j]+" ");i7++;
            }
            //if(nd[i][j]==9999.0)
            //  break;
            i++;System.out.println();
        }
        //System.exit(0);

        OptionChain ob3=new OptionChain();
        ndll_delta=ob3.opch(nd,i,spp);
        ndl_del_len=i;

        atm_ind_c=ob3.atm_index_c;
        atm_ind_p=ob3.atm_index_c;
        atm_extr_c=ndll_delta[atm_ind_c][1];
        atm_extr_p=ndll_delta[atm_ind_p][3];

        ndll_gam= new double[ndl_del_len][7];
        for (int i1 = 0; i1 < ndl_del_len; i1+=1)
        {
            ndll_gam[i1][0]=ob3.ndll_gam[i1][0];
            ndll_gam[i1][1]=100000;
            ndll_gam[i1][2]=100000;
            ndll_gam[i1][3]=100000;
            ndll_gam[i1][4]=100000;
            ndll_gam[i1][5]=100000;
            ndll_gam[i1][6]=ob3.ndll_gam[i1][6];
        }

        //System.out.println("\nMain Gamma Option Chain:");
        //for (int i1 = 0; i1 < ndl_del_len; i1++) {
        //  System.out.printf("%.6f %.6f %.6f %.6f %.6f %.6f %.6f\n",ndll_gam[i1][0],ndll_gam[i1][1],ndll_gam[i1][2],ndll_gam[i1][3],ndll_gam[i1][4],ndll_gam[i1][5],ndll_gam[i1][6]);
        //}
        //System.out.println("\n");

        maka(ratio_num, 0, tot_amt, spp, nd,i);

        //nd=optioncaller(nd,i,spp);
        //int newnd_l=newop_l;
        /*int start=0, end=0;
        if(ratio_num<=4)
        {       
        end=ratio_num;//System.out.println("(" + 0 + ", " + end + ")");
        maka(end, start, tot_amt, spp, nd,i);
        }
        else
        {
        end=4;start=0;//System.out.println("(" + 0 + ", " + 4 + ")");
        maka(end,start, tot_amt, spp, nd,i);
        end=ratio_num;start=4;
        for (int i3 = start; i3 <= end; i3 += 3) {
        if (i3 + 2 <= end) {
        maka(i3+2,i3, tot_amt, spp, nd,i);//System.out.println("(" + i + ", " + (i + 2) + ")");
        }
        if (i3 == (end-1)) {
        maka(i3+1,i3, tot_amt, spp, nd,i);//System.out.println("(" + i + ", " + (i + 1) + ")");
        }
        }
        }*/

    }
    array_length_short ob30=new array_length_short();
    int lengt=0, colt=60;
    double max_loss=-40,max_loss1=-40, max_prof_per=5.0/100.0, min_prof=20;
    String d_dt[]=new String[1000000];
    String d_dts[]=new String[1000000];
    String x_dt[]=new String[1000000];
    String y_dt[]=new String[1000000];
    String y1_dt[]=new String[1000000];
    String d_dt31[]=new String[10000];
    String d_dts31[]=new String[10000];
    String x_dt31[]=new String[10000];
    String y_dt31[]=new String[10000];
    int md_arr[]={10,11,12};
    /*
    String ss_md_ar[]={“0 CCP CPP”,“1 CPL CPS”,“2 PCL PCS”,“3 CCPL CPPS CPPL CPPS”
    ,“4 CCPP”,“5 CM NP”,“6 MC PN”,“7 CMNP”,“8 MCPN”,“9 MNL MNS”,“10 MCL MCS”
    “11 NPL NPS”,“12 MPL MPS”,“13 CNL CNS”,“14 NL NS ML MS”,“15 C P M N”,“16 MN”
    ,“17 CCM PPN”,“18 CMP CNP”,“19 MMC NNP”,“20 MCN MPN”,“21 CCN MPP”,“22 MMP CNN”,”23 CN MP”};
     */

    int pnts[][]={{36,1},{51,1},{52,1},{53,1},{54,1},{55,1},{56,1},{57,1},{38,1},{28,1},{39,1},{44,1},{45,1},{46,1},{47,1},{37,1},{48,1},{49,1},{50,1}};//new int[20][2];

    int pnts_len=pnts.length;
    //36,38,41,28,39,42
    //MergeDDAArrays ob25=new MergeDDAArrays();
    //MergeDDAArrays ob26=new MergeDDAArrays();
    public   void maka(int end, int start, double tot_amt, double spp, double nd[][],int i) {
        System.out.println("end is " + end + " start is " + start);
        int t_len=0;

        //max_loss=-400/100.0*tot_amt;
        max_loss1=-40/100.0*tot_amt;
        min_prof=5/100.0*tot_amt;
        UniqueRatios_Three op65=new UniqueRatios_Three();
        //double rn[][]=op65.urr_t(16,0);

        double rn1[][]=op65.urr_t(end, start);
        int ratio_num1=op65.ur_t_index;
        System.out.println("ratio_num1 is "+ratio_num1);

        if(end>=4)
            end=4;

        int end1=end;
        if(md_arr.length>=4)
            end1=2;

        double rn2[][]=op65.urr_f(end1,start);
        int ratio_num2=op65.ur_t_index;
        System.out.println("ratio_num2 is "+ratio_num2);

        double rn3[][]=op65.urr_o(end,start);
        int ratio_num3=op65.ur_t_index;
        System.out.println("ratio_num3 is "+ratio_num3);

        double rn4[][]=op65.urr_t(end,1);
        int ratio_num4=op65.ur_t_index;
        System.out.println("ratio_num4 is "+ratio_num4);

        double rn5[][]=op65.urr_two(end,0);
        int ratio_num5=op65.ur_t_index;
        System.out.println("ratio_num5 is "+ratio_num5);

        double rn6[][]=op65.urr_only_one(end,0);
        int ratio_num6=op65.ur_t_index;
        System.out.println("ratio_num6 is "+ratio_num6);

        double rn7[][]=op65.urr_f(1,1);
        int ratio_num7=op65.ur_t_index;
        System.out.println("ratio_num7 is "+ratio_num7);

        double dda1[][],dda2[][],dda3[][], dda4[][], dda5[][], dda6[][],dda_temp[][], dif1[][], dif2[][], dif3[][], dif4[][], dif5[][], dif6[][];
        int lenn1=0,lenn2=0,lenn3=0, lenn4=0, lenn5=0, lenn6=0;
        int i1=0;
        String dt_dda3[],dt_ss[],x_ss[],y_ss[],y1_ss[];

        dda3=new double[10][10];
        dt_dda3=new String[10];
        dt_ss=new String[10];
        x_ss=new String[10];
        y_ss=new String[10];
        y1_ss=new String[10];
        /*
        dda1=mak(nd,i,rn1,ratio_num1,tot_amt,spp,0);
        lenn1=lengt;
        for(int i1=0;i1<lenn1;i1+=512400)//while(true)
        {
        if((lenn1-i1)>=512400)
        t_len=i1+512400;
        else 
        t_len=lenn1;i1=0;
        System.out.println("Printing from i1 "+i1+" till t_len "+t_len+" lenn1 "+lenn1);
        dif1=st_prt(dda1,i1,t_len,spp);
        st_prt_two(dda1,dif1,pnts,pnts_len,i1,t_len,spp);
        }
        dda=null;
         */
        /*
        pnts_len=8;
        pnts[0][0]=36;pnts[0][1]=1;
        pnts[1][0]=38;pnts[1][1]=1;
        pnts[2][0]=28;pnts[2][1]=1;
        pnts[3][0]=39;pnts[3][1]=1;
        pnts[4][0]=44;pnts[4][1]=1;
        pnts[5][0]=45;pnts[5][1]=1;
        pnts[6][0]=46;pnts[6][1]=1;
        pnts[7][0]=47;pnts[7][1]=1;
         */
        double[][][] dda_md_rn=new double[31][10000][10];
        int md_rn[][]=new int[31][2];

        //int md_arr[]={0,1,2,5,6,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};
        //int md_arr[]={5,6,15};
        //int md_arr[]={0,4};
        //int md_arr[]={21,22};
        //int md_arr[]={17};
        int md_rn_i=0,md_rn_len=md_arr.length;

        dda_md_rn[0]=rn1;md_rn[0][0]=ratio_num1;md_rn[0][1]=0;
        dda_md_rn[1]=rn3;md_rn[1][0]=ratio_num3;md_rn[1][1]=3;
        dda_md_rn[2]=rn4;md_rn[2][0]=ratio_num4;md_rn[2][1]=3;
        dda_md_rn[3]=rn2;md_rn[3][0]=ratio_num2;md_rn[3][1]=2;//
        dda_md_rn[4]=rn2;md_rn[4][0]=ratio_num2;md_rn[4][1]=1;//
        dda_md_rn[5]=rn5;md_rn[5][0]=ratio_num5;md_rn[5][1]=6;
        dda_md_rn[6]=rn5;md_rn[6][0]=ratio_num5;md_rn[6][1]=7;
        dda_md_rn[7]=rn2;md_rn[7][0]=ratio_num2;md_rn[7][1]=8;//
        dda_md_rn[8]=rn2;md_rn[8][0]=ratio_num2;md_rn[8][1]=9;//
        dda_md_rn[9]=rn4;md_rn[9][0]=ratio_num4;md_rn[9][1]=10;
        dda_md_rn[10]=rn4;md_rn[10][0]=ratio_num4;md_rn[10][1]=11;
        dda_md_rn[11]=rn4;md_rn[11][0]=ratio_num4;md_rn[11][1]=12;
        dda_md_rn[12]=rn4;md_rn[12][0]=ratio_num4;md_rn[12][1]=13;
        dda_md_rn[13]=rn4;md_rn[13][0]=ratio_num4;md_rn[13][1]=14;
        dda_md_rn[14]=rn5;md_rn[14][0]=ratio_num5;md_rn[14][1]=15;
        dda_md_rn[15]=rn6;md_rn[15][0]=ratio_num6;md_rn[15][1]=16;
        dda_md_rn[16]=rn5;md_rn[16][0]=ratio_num5;md_rn[16][1]=17;
        dda_md_rn[17]=rn4;md_rn[17][0]=ratio_num4;md_rn[17][1]=18;
        dda_md_rn[18]=rn4;md_rn[18][0]=ratio_num4;md_rn[18][1]=19;
        dda_md_rn[19]=rn4;md_rn[19][0]=ratio_num4;md_rn[19][1]=20;
        dda_md_rn[20]=rn4;md_rn[20][0]=ratio_num4;md_rn[20][1]=21;
        dda_md_rn[21]=rn4;md_rn[21][0]=ratio_num4;md_rn[21][1]=22;
        dda_md_rn[22]=rn4;md_rn[22][0]=ratio_num4;md_rn[22][1]=23;
        dda_md_rn[23]=rn5;md_rn[23][0]=ratio_num5;md_rn[23][1]=24;
        dda_md_rn[24]=rn7;md_rn[24][0]=ratio_num7;md_rn[24][1]=8;
        dda_md_rn[25]=rn7;md_rn[25][0]=ratio_num7;md_rn[25][1]=9;
        dda_md_rn[26]=rn6;md_rn[26][0]=ratio_num6;md_rn[26][1]=25;
        dda_md_rn[27]=rn6;md_rn[27][0]=ratio_num6;md_rn[27][1]=26;
        dda_md_rn[28]=rn6;md_rn[28][0]=ratio_num6;md_rn[28][1]=27;
        dda_md_rn[29]=rn6;md_rn[29][0]=ratio_num6;md_rn[29][1]=28;

        double a_tot_amt[]={tot_amt};//,tot_amt*10/100.0,tot_amt*150/100.0};

        for(int i6=0;i6<a_tot_amt.length;i6++)
        {
            if(stp_nw==1)
                break;
            System.out.print(" i6 : "+i6+",");   
            for(int i55=md_rn_i;i55<md_rn_len;i55++)
            {
                int i5=md_arr[i55];
                if(i6==0)
                {
                    System.out.println("dda_md_rn from i6 "+i6+" , i5 "+i5+" till md_rn_len "+md_rn_len);   
                    dda1=mak(nd,i,dda_md_rn[i5],md_rn[i5][0],a_tot_amt[i6],spp,md_rn[i5][1], 1);
                }
                else
                { 
                    System.out.print(i5+", ");  
                    dda1=mak(nd,i,dda_md_rn[i5],md_rn[i5][0],a_tot_amt[i6],spp,md_rn[i5][1], 0);
                }
                if(stp_nw==1)
                    break;
                lenn1=lengt;
                t_len=lenn1;i1=0;
                //System.out.println("Printing from i1 "+i1+" till t_len "+t_len+" lenn1 "+lenn1);
                //dif1=st_prt(dda1,i1,t_len,spp);
                //st_prt_two(dda1,dif1,d_dt,0,0,pnts,pnts_len,i1,t_len,spp);

                dda3=mergeArrayss(dda3,dda1,lenn3,lenn1,colt);
                dt_dda3=s_mergeArrayss(dt_dda3,d_dt,lenn3,lenn1);
                dt_ss=s_mergeArrayss(dt_ss,d_dts,lenn3,lenn1);
                x_ss=s_mergeArrayss(x_ss,x_dt,lenn3,lenn1);
                y_ss=s_mergeArrayss(y_ss,y_dt,lenn3,lenn1);
                y1_ss=s_mergeArrayss(y1_ss,y1_dt,lenn3,lenn1);
                lenn3=lenn3+lenn1;

                /*
                System.out.println("\n changing lenn3 is "+lenn3);
                for(int i52=0;i52<lenn3;i52++)
                {
                System.out.println(i52+" x: "+x_ss[i52]);
                System.out.println(i52+" y: "+y_ss[i52]);
                }
                System.out.println("\n");
                 */

                //dda=null;
                //d_dt=null;
            }
            if(stp_nw==1)
                break;

            /*
            System.out.println("\nlenn3 is "+lenn3);
            for(int i5=0;i5<lenn3;i5++)
            {
            System.out.println(i5+" x: "+x_ss[i5]);
            System.out.println(i5+" y: "+y_ss[i5]);
            }
            System.out.println("\n");
             */
            if(i6==0||i6==(a_tot_amt.length-1))
            {
                t_len=lenn3; i1=0;
                System.out.println("Printing from i1 "+i1+" till t_len "+t_len+" lenn3 "+lenn3);
                /*
                for(int i5=0;i5<pnts_len;i5++)
                {
                st_prt(dda3,i1,t_len,pnts[i5][0],spp);
                st_prt_two(dda3,dt_dda3,dt_ss,0,1,pnts[i5][0],pnts[i5][1],i1,t_len,spp);
                //dif1=null;
                }
                 */
                for(int i5=0;i5<pnts_len;i5++)
                {
                    st_prt(dda3,i1,t_len,pnts[i5][0],spp);
                    st_prt_two(dda3,dt_dda3,dt_ss,x_ss,y_ss,y1_ss,1,0,pnts[i5][0],pnts[i5][1],i1,t_len,spp);
                    //dif1=null;
                }

            }
        }

        /*
        System.out.println("\nst_ar: ");
        for(int i3=0;i3<spta_l;i3++)
        {
        for(int i5=0;i5<num_to_prt;i5++)
        {
        System.out.println(st_ar[i3][i5]);
        }
        System.out.println("\n");
        }
         */
        /*
        System.out.println("\n x,y, y1 st_ar: ");
        for(int i3=0;i3<spta_l;i3++)
        {
        for(int i5=0;i5<num_to_prt;i5++)
        {
        System.out.println("x: "+x_ar[i3][i5]);
        System.out.println("y: "+y_ar[i3][i5]);
        System.out.println("y1: "+y1_ar[i3][i5]);
        }
        System.out.println("\n");
        }
         */
    }
    double dd1[][]=new double[1000000][9];
    double dda[][]=new double[1000000][colt];
    double dda31[][]=new double[10000][colt];
    int num_to_prt=20;
    String st_ar[][] = new String[60][num_to_prt];
    String x_ar[][] = new String[60][num_to_prt];
    String y_ar[][] = new String[60][num_to_prt];
    String y1_ar[][] = new String[60][num_to_prt];
    //int st_ar_ldif[] = new int[60];
    int stp_nw=0;
    int lenn30=0,lenn31=0;
    public   double[][] mak(double nd[][],int i,double rn[][], int ratio_num, double tot_amt, double spp, int md, int pntt) {
        //int i=0;
        int j=0;

        //max_loss=-300/100.0*tot_amt;
        //max_loss1=-40/100.0*tot_amt;

        //System.out.println("For md "+md);

        /*UniqueRatios op55=new UniqueRatios();
        double rn1[][]=op55.ur(1);
        int ratio_num1=op55.cnt1;*/
        //colt=31;

        double dd[][]=new double[1000000][5];//dda=new double[1000000][colt];
        //d_dt=new String[1000000];

        /*
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
         */
        int len1=0,ini_pair=0;
        //System.out.println("i is "+i);
        if(md==0||md==2||md==1||md==8||md==9||md==22||md==23)
            for(int i5=-2;i5<0;i5++)    
            {
                for (int i1=0;i1<i;i1++)
                {
                    for(int i2=i1+1;i2<i;i2++)    
                    {
                        for (int i3=0;i3<i;i3++)
                        {
                            if(md==0||md==22||md==23)
                            {
                                dd1[len1][0]=nd[i1][1];
                                dd1[len1][1]=nd[i1][0];
                                dd1[len1][2]=nd[i2][1];
                                dd1[len1][3]=nd[i2][0];
                                dd1[len1][4]=nd[i3][1];
                                dd1[len1][5]=nd[i3][2];
                                dd1[len1][6]=-9999;
                                dd1[len1][7]=-9999;
                                dd1[len1][8]=spp;
                                len1++;
                                if(i1==0)
                                    ini_pair++;
                            }
                            else if(md==2)
                            {

                                dd1[len1][0]=nd[i1][1];
                                dd1[len1][1]=nd[i1][0];
                                dd1[len1][2]=nd[i2][1];
                                dd1[len1][3]=nd[i2][0];
                                dd1[len1][4]=nd[i3][1];
                                dd1[len1][5]=nd[i3][2];
                                dd1[len1][6]=i5;//-9999;
                                dd1[len1][7]=i5;//-9999;
                                dd1[len1][8]=spp;
                                len1++;
                                if(i1==0)
                                    ini_pair++;

                            }
                            else if(md==1||md==8||md==9)
                            {
                                for(int i4=i3+1;i4<i;i4++)    
                                {
                                    dd1[len1][0]=nd[i1][1];
                                    dd1[len1][1]=nd[i1][0];
                                    dd1[len1][2]=nd[i2][1];
                                    dd1[len1][3]=nd[i2][0];
                                    dd1[len1][4]=nd[i3][1];
                                    dd1[len1][5]=nd[i3][2];
                                    dd1[len1][6]=nd[i4][1];//-9999;
                                    dd1[len1][7]=nd[i4][2];//-9999;
                                    dd1[len1][8]=spp;
                                    len1++;
                                    if(i1==0)
                                        ini_pair++;
                                }
                            }
                        }
                    }
                }
                if(md!=2)
                    break;
            }
        if(md==0||md==2||md==22||md==23)
        {
            for(int i5=-2;i5<0;i5++)    
            {
                for (int i1=0;i1<i;i1++)
                {
                    for(int i2=i1+1;i2<i;i2++)    
                    {
                        for (int i3=0;i3<i;i3++)
                        {
                            if(md==0||md==22||md==23)
                            {
                                dd1[len1][0]=nd[i3][1];
                                dd1[len1][1]=nd[i3][0];
                                dd1[len1][2]=-9999;
                                dd1[len1][3]=-9999;
                                dd1[len1][4]=nd[i1][1];
                                dd1[len1][5]=nd[i1][2];
                                dd1[len1][6]=nd[i2][1];
                                dd1[len1][7]=nd[i2][2];
                                dd1[len1][8]=spp;
                                len1++;
                            }
                            else if(md==2)
                            {

                                dd1[len1][0]=nd[i3][1];
                                dd1[len1][1]=nd[i3][0];
                                dd1[len1][2]=i5;
                                dd1[len1][3]=i5;
                                dd1[len1][4]=nd[i1][1];
                                dd1[len1][5]=nd[i1][2];
                                dd1[len1][6]=nd[i2][1];
                                dd1[len1][7]=nd[i2][2];
                                dd1[len1][8]=spp;
                                len1++;

                            }
                        }
                    }
                }
                if(md!=2)
                    break;
            }
        }

        if(md==3||md==10||md==13||md==14||md==17)
        {
            for(int i5=-2;i5<0;i5++)    
            {
                for (int i1=0;i1<i;i1++)
                {
                    for(int i2=0;i2<i1;i2++)    
                    {
                        dd1[len1][0]=nd[i1][1];
                        dd1[len1][1]=nd[i1][0];
                        dd1[len1][2]=i5;
                        dd1[len1][3]=i5;
                        dd1[len1][4]=nd[i2][1];
                        dd1[len1][5]=nd[i2][2];
                        dd1[len1][6]=-9999;
                        dd1[len1][7]=-9999;
                        dd1[len1][8]=spp;
                        len1++;
                        if(i1==0)
                            ini_pair++;
                    }
                    for(int i2=i1;i2<i;i2++)    
                    {
                        dd1[len1][0]=nd[i1][1];
                        dd1[len1][1]=nd[i1][0];
                        dd1[len1][2]=i5;
                        dd1[len1][3]=i5;
                        dd1[len1][4]=nd[i2][1];
                        dd1[len1][5]=nd[i2][2];
                        dd1[len1][6]=-9999;
                        dd1[len1][7]=-9999;
                        dd1[len1][8]=spp;
                        len1++;
                        if(i1==0)
                            ini_pair++;
                    }
                }
                if(md==17)
                    break;
            }
        }

        if(md==11||md==12)
        {
            for(int i5=-2;i5<0;i5++)    
            {
                for (int i1=0;i1<i;i1++)
                {
                    for(int i2=0;i2<i1;i2++)    
                    {
                        if(md==11)
                        {
                            dd1[len1][0]=nd[i1][1];
                            dd1[len1][1]=nd[i1][0];
                            dd1[len1][2]=i5;
                            dd1[len1][3]=i5;
                            dd1[len1][4]=nd[i2][1];
                            dd1[len1][5]=nd[i2][0];
                            dd1[len1][6]=-9999;
                            dd1[len1][7]=-9999;
                            dd1[len1][8]=spp;
                        }
                        else if(md==12)
                        {
                            dd1[len1][0]=nd[i1][1];
                            dd1[len1][1]=nd[i1][2];
                            dd1[len1][2]=i5;
                            dd1[len1][3]=i5;
                            dd1[len1][4]=nd[i2][1];
                            dd1[len1][5]=nd[i2][2];
                            dd1[len1][6]=-9999;
                            dd1[len1][7]=-9999;
                            dd1[len1][8]=spp;
                        }

                        len1++;
                        if(i1==0)
                            ini_pair++;
                    }
                    for(int i2=i1;i2<i;i2++)    
                    {
                        if(md==11)
                        {
                            dd1[len1][0]=nd[i1][1];
                            dd1[len1][1]=nd[i1][0];
                            dd1[len1][2]=i5;
                            dd1[len1][3]=i5;
                            dd1[len1][4]=nd[i2][1];
                            dd1[len1][5]=nd[i2][0];
                            dd1[len1][6]=-9999;
                            dd1[len1][7]=-9999;
                            dd1[len1][8]=spp;
                        }
                        else if(md==12)
                        {
                            dd1[len1][0]=nd[i1][1];
                            dd1[len1][1]=nd[i1][2];
                            dd1[len1][2]=i5;
                            dd1[len1][3]=i5;
                            dd1[len1][4]=nd[i2][1];
                            dd1[len1][5]=nd[i2][2];
                            dd1[len1][6]=-9999;
                            dd1[len1][7]=-9999;
                            dd1[len1][8]=spp;
                        }
                        len1++;
                        if(i1==0)
                            ini_pair++;
                    }
                }
            }
        }

        if(md==6||md==7)
        {
            for (int i3=0;i3<2;i3++)
            {
                for (int i1=0;i1<i;i1++)
                {
                    if(i3==0)
                        for(int i2=i1+1;i2<i;i2++)    
                        {
                            dd1[len1][0]=nd[i1][1];
                            dd1[len1][1]=nd[i1][0];
                            dd1[len1][2]=nd[i2][1];
                            dd1[len1][3]=nd[i2][0];
                            dd1[len1][4]=-9999;
                            dd1[len1][5]=-9999;
                            dd1[len1][6]=-9999;
                            dd1[len1][7]=-9999;
                            dd1[len1][8]=spp;
                            len1++;
                        }
                    if(i3==1)
                        for(int i2=i1+1;i2<i;i2++)    
                        {
                            dd1[len1][0]=-9999;
                            dd1[len1][1]=-9999;
                            dd1[len1][2]=-9999;
                            dd1[len1][3]=-9999;
                            dd1[len1][4]=nd[i1][1];
                            dd1[len1][5]=nd[i1][2];
                            dd1[len1][6]=nd[i2][1];
                            dd1[len1][7]=nd[i2][2];
                            dd1[len1][8]=spp;
                            len1++;
                        }        

                }
            }
        }

        if(md==24)
        {
            for (int i3=0;i3<2;i3++)
            {
                for (int i1=0;i1<i;i1++)
                {
                    if(i3==0)
                        for(int i2=0;i2<i;i2++)    
                        {
                            dd1[len1][0]=nd[i1][1];
                            dd1[len1][1]=nd[i1][0];
                            dd1[len1][2]=nd[i2][1];
                            dd1[len1][3]=nd[i2][2];
                            dd1[len1][4]=-9999;
                            dd1[len1][5]=-9999;
                            dd1[len1][6]=-9999;
                            dd1[len1][7]=-9999;
                            dd1[len1][8]=spp;
                            len1++;
                        }
                    if(i3==1)
                        for(int i2=0;i2<i;i2++)    
                        {
                            dd1[len1][0]=-9999;
                            dd1[len1][1]=-9999;
                            dd1[len1][2]=-9999;
                            dd1[len1][3]=-9999;
                            dd1[len1][4]=nd[i1][1];
                            dd1[len1][5]=nd[i1][0];
                            dd1[len1][6]=nd[i2][1];
                            dd1[len1][7]=nd[i2][2];
                            dd1[len1][8]=spp;
                            len1++;
                        }    
                }
            }
        }

        if(md==15)
        {
            for(int i5=-2;i5<0;i5++)    
            {
                for (int i1=0;i1<i;i1++)
                {
                    dd1[len1][0]=nd[i1][1];
                    dd1[len1][1]=nd[i1][0];
                    dd1[len1][2]=i5;
                    dd1[len1][3]=i5;
                    dd1[len1][4]=-9999;
                    dd1[len1][5]=-9999;
                    dd1[len1][6]=-9999;
                    dd1[len1][7]=-9999;
                    dd1[len1][8]=spp;
                    len1++;
                }
                for (int i1=0;i1<i;i1++)
                {
                    dd1[len1][0]=-9999;
                    dd1[len1][1]=-9999;
                    dd1[len1][2]=-9999;
                    dd1[len1][3]=-9999;
                    dd1[len1][4]=nd[i1][1];
                    dd1[len1][5]=nd[i1][2];
                    dd1[len1][6]=i5;
                    dd1[len1][7]=i5;
                    dd1[len1][8]=spp;
                    len1++;
                }
            }
        }

        if(md==16)
        {
            for (int i1=0;i1<i;i1++)
            {
                dd1[len1][0]=nd[i1][1];
                dd1[len1][1]=nd[i1][0];
                dd1[len1][2]=-9999;
                dd1[len1][3]=-9999;
                dd1[len1][4]=-9999;
                dd1[len1][5]=-9999;
                dd1[len1][6]=-9999;
                dd1[len1][7]=-9999;
                dd1[len1][8]=spp;
                len1++;
            }
            for (int i1=0;i1<i;i1++)
            {
                dd1[len1][0]=-9999;
                dd1[len1][1]=-9999;
                dd1[len1][2]=nd[i1][1];
                dd1[len1][3]=nd[i1][2];
                dd1[len1][4]=-9999;
                dd1[len1][5]=-9999;
                dd1[len1][6]=-9999;
                dd1[len1][7]=-9999;
                dd1[len1][8]=spp;
                len1++;
            }
            for (int i1=0;i1<i;i1++)
            {
                dd1[len1][0]=-9999;
                dd1[len1][1]=-9999;
                dd1[len1][2]=-9999;
                dd1[len1][3]=-9999;
                dd1[len1][4]=nd[i1][1];
                dd1[len1][5]=nd[i1][0];
                dd1[len1][6]=-9999;
                dd1[len1][7]=-9999;
                dd1[len1][8]=spp;
                len1++;
            }
            for (int i1=0;i1<i;i1++)
            {
                dd1[len1][0]=-9999;
                dd1[len1][1]=-9999;
                dd1[len1][2]=-9999;
                dd1[len1][3]=-9999;
                dd1[len1][4]=-9999;
                dd1[len1][5]=-9999;
                dd1[len1][6]=nd[i1][1];
                dd1[len1][7]=nd[i1][2];
                dd1[len1][8]=spp;
                len1++;
            }

        }

        if(md==18||md==20)
            for (int i1=0;i1<i;i1++)
            {
                for(int i2=i1+1;i2<i;i2++)    
                {
                    for (int i3=0;i3<i;i3++)
                    {
                        dd1[len1][0]=nd[i1][1];
                        dd1[len1][1]=nd[i1][0];
                        dd1[len1][2]=nd[i2][1];
                        dd1[len1][3]=nd[i2][0];
                        dd1[len1][4]=nd[i3][1];
                        dd1[len1][5]=nd[i3][0];
                        dd1[len1][6]=-9999;
                        dd1[len1][7]=-9999;
                        dd1[len1][8]=spp;
                        len1++;
                        if(i1==0)
                            ini_pair++;
                    }
                    for (int i3=0;i3<i;i3++)
                    {
                        dd1[len1][0]=-9999;
                        dd1[len1][1]=-9999;
                        dd1[len1][2]=nd[i1][1];
                        dd1[len1][3]=nd[i1][2];
                        dd1[len1][4]=nd[i2][1];
                        dd1[len1][5]=nd[i2][2];
                        dd1[len1][6]=nd[i3][1];
                        dd1[len1][7]=nd[i3][2];
                        dd1[len1][8]=spp;
                        len1++;
                        if(i1==0)
                            ini_pair++;
                    }

                }
            }

        if(md==19||md==21)
        {
            for (int i1=0;i1<i;i1++)
            {
                for(int i2=0;i2<i;i2++)    
                {
                    for (int i3=0;i3<i;i3++)
                    {
                        dd1[len1][0]=nd[i1][1];
                        dd1[len1][1]=nd[i1][0];
                        dd1[len1][2]=-9999;
                        dd1[len1][3]=-9999;
                        dd1[len1][4]=nd[i2][1];
                        dd1[len1][5]=nd[i2][0];
                        dd1[len1][6]=nd[i3][1];
                        dd1[len1][7]=nd[i3][2];
                        dd1[len1][8]=spp;
                        len1++;
                        if(i1==0)
                            ini_pair++;
                    }
                    for (int i3=0;i3<i;i3++)
                    {
                        dd1[len1][0]=nd[i1][1];
                        dd1[len1][1]=nd[i1][0];
                        dd1[len1][2]=nd[i2][1];
                        dd1[len1][3]=nd[i2][2];
                        dd1[len1][4]=-9999;
                        dd1[len1][5]=-9999;
                        dd1[len1][6]=nd[i3][1];
                        dd1[len1][7]=nd[i3][2];
                        dd1[len1][8]=spp;
                        len1++;
                        if(i1==0)
                            ini_pair++;
                    }
                }
            }
        }

        if(md==25||md==27)
        {
            for (int i1=0;i1<i;i1++)
            {
                dd1[len1][0]=nd[i1][1];
                dd1[len1][1]=nd[i1][0];
                dd1[len1][2]=-9999;
                dd1[len1][3]=-9999;
                dd1[len1][4]=-9999;
                dd1[len1][5]=-9999;
                dd1[len1][6]=-9999;
                dd1[len1][7]=-9999;
                dd1[len1][8]=spp;
                len1++;
            }
        }
        if(md==26||md==28)
        {
            for (int i1=0;i1<i;i1++)
            {
                dd1[len1][0]=-9999;
                dd1[len1][1]=-9999;
                dd1[len1][2]=nd[i1][1];
                dd1[len1][3]=nd[i1][2];
                dd1[len1][4]=-9999;
                dd1[len1][5]=-9999;
                dd1[len1][6]=-9999;
                dd1[len1][7]=-9999;
                dd1[len1][8]=spp;
                len1++;
            }
        }

        //System.out.println("hello ");
        if(pntt==1)
            System.out.println("dd1 len1 is "+len1);
        //System.out.println("dd1 is ");
        ///*
        /*System.out.println("bye ");
        for (int i1=0;i1<len1;i1++)
        {
        System.out.printf(" i1 "+i1+" l1 "+len1+" | ");
        for(j=0;j<9;j++)
        {
        System.out.printf("%.2f , ",dd1[i1][j]);
        }
        System.out.println();
        }
         */
        //System.exit(0);
        /*
        System.out.println("hey len is "+len);
        System.out.println("hey dd is ");
        for (int i1=0;i1<len;i1++)
        {
        for(j=0;j<5;j++)
        {
        System.out.printf("%.2f , ",dd[i1][j]);
        }
        System.out.println();
        }
         */
        //System.exit(0);
        //int lenn=0;
        int  lenn=0;

        /*option 3 calulcation with call, put, call/putshort - 
        call,call,callshort; put,put,putshort; call,put,callshort; call,put,putshort;
        callshort,callshort,call; putshort,putshort,put; callshort,putshort,call; callshort,putshort,put;
        call,call,putshort; put,put,callshort;
        callshort,callshort,put; putshort,putshort,call;
         */
        //double tot_amt=5.0;
        String sdt="";
        double rat_wy=0;

        OptionInvestment op=new OptionInvestment();
        if(pntt==1)
            System.out.println("Calc for md "+md);
        //for (int j3=1;j3<=10;j3++)
        //{
        if(pntt==1)
            System.out.print("\nfor inv volt ");

        int inv_volt_arr[][]={{0,5},{0,3},{0,8},{6,3},{6,8}};//{{0,5},{6,5},{0,8},{6,8},{0,3},{6,3}};
        int inv_volt_arr_len=1,tot_interval=10,tot_volt=10,iinterval=0,vvolt=0,i4=0;
        double t1=0,t2=0,t3=0,t4=0;
        double xx_time[]=new double[0];
        double yy_time[]=new double[0];

        for(int j3=0;j3<inv_volt_arr_len;j3++)
        {

            iinterval=inv_volt_arr[j3][0];
            vvolt=inv_volt_arr[j3][1];
            if(pntt==1)
                System.out.print("rsn, ");//ratio set number
            for (int j1=0;j1<ratio_num+1;j1++)
            {
                //if(j1%100==0&&pntt==1)
                //System.out.print(j1+", ");
                if(j1==ratio_num&&ratio_num!=0)
                {
                    j1=ratio_num-1;
                    rn[j1][0]=-1;
                    rn[j1][1]=-1;
                    rn[j1][2]=-1;
                    rn[j1][3]=-1;
                    rat_wy=1;
                }
                else
                {
                    rat_wy=0;
                }
                for (int i1=0;i1<len1;i1++)
                {
                    //System.out.print("|"+i1+"|");
                    /*
                    t1=dd1[i1][1];
                    t2=dd1[i1][3];
                    t3=dd1[i1][5];
                    t4=dd1[i1][7];

                    if(dd1[i1][1]!=-9999)
                    dd1[i1][1] = dd1[i1][1]*(tot_interval-iinterval)/tot_interval*(vvolt)/(tot_volt/2.0);
                    if(dd1[i1][3]!=-9999)
                    dd1[i1][3] = dd1[i1][3]*(tot_interval-iinterval)/tot_interval*(vvolt)/(tot_volt/2.0);
                    if(dd1[i1][5]!=-9999)
                    dd1[i1][5] = dd1[i1][5]*(tot_interval-iinterval)/tot_interval*(vvolt)/(tot_volt/2.0);
                    if(dd1[i1][7]!=-9999)
                    dd1[i1][7] = dd1[i1][7]*(tot_interval-iinterval)/tot_interval*(vvolt)/(tot_volt/2.0);
                     */

                    double rn0=rn[j1][0];
                    double rn1=rn[j1][1];
                    double rn2=rn[j1][2];
                    double rn3=rn[j1][3];

                    if((dd1[i1][3]==-9999)&&(rn[j1][2]==0)&&md==0)
                    {
                        /*//System.out.println("Not counting ");
                        for(j=0;j<8;j++)
                        {
                        //System.out.printf("%.2f , ",dd1[i1][j]);
                        }
                        //System.out.printf("R "+rn[j1][0]+" "+rn[j1][1]+" "+rn[j1][2]);
                        //System.out.println();*/
                    }
                    else if((dd1[i1][7]==-9999)&&(rn[j1][0]==0)&&(i1>=ini_pair)&&md==0)
                    {
                        /*//System.out.println("Not counting ");
                        for(j=0;j<8;j++)
                        {
                        //System.out.printf("%.2f , ",dd1[i1][j]);
                        }
                        //System.out.printf("R "+rn[j1][0]+" "+rn[j1][1]+" "+rn[j1][2]);
                        //System.out.println();*/
                    }
                    else if((dd1[i1][7]==-9999)&&(dd1[i1][2]==-2)&&(rn[j1][0]==0)&&(i1>=ini_pair)&&md==3)
                    {
                    }
                    else if((dd1[i1][7]==-9999)&&(dd1[i1][2]==-1)&&(rn[j1][0]==0)&&(i1>=ini_pair)&&md==3)
                    {
                    }
                    else
                    {
                        /*op.opi(tot_amt,rn[j1][0],rn[j1][1], dd[i1][1], dd[i1][3]);
                        double num_C=op.numberOfCalls;
                        double num_P=op.numberOfPuts;
                         */

                        double callsp[][]=new double[2][5],putsp[][]=new double[2][5], longsp[][]=new double[2][5], shortsp[][]=new double[2][5];
                        double callshortsp[][]=new double[2][5],putshortsp[][]=new double[2][5];
                        int lencc=0,lenpp=0, lenll=0, lenss=0, lenmm=0, lennn=0;
                        double num_f=0;
                        double num_s=0;
                        double num_t=0;
                        double num_fr=0;
                        longsp[0][2]=0;longsp[0][3]=0;longsp[1][2]=0;longsp[1][3]=0;longsp[0][4]=0;longsp[1][4]=0;
                        shortsp[0][2]=0;shortsp[0][3]=0;shortsp[1][2]=0;shortsp[1][3]=0;shortsp[0][4]=0;shortsp[1][4]=0;

                        if(md==0)
                        {
                            if(dd1[i1][7]==-9999)
                            {op.three_opi(tot_amt,rn[j1][0],rn[j1][1],rn[j1][2],dd1[i1][1], dd1[i1][3],dd1[i1][5]);rn3=0;}
                            else
                            {op.three_opi(tot_amt,rn[j1][0],rn[j1][1],rn[j1][2],dd1[i1][1], dd1[i1][5],dd1[i1][7]);rn1=0;rn2=rn[j1][1];rn3=rn[j1][2];}

                            num_f=op.numberOf_f;
                            num_s=op.numberOf_s;
                            num_t=op.numberOf_t;
                            num_fr=0;
                            rn[j1][3]=0;//rn3=0;

                            if(dd1[i1][7]==-9999)
                            {
                                callsp[0][0]=dd1[i1][0];callsp[0][1]=dd1[i1][1];callsp[0][2]=num_f;
                                callsp[1][0]=dd1[i1][2];callsp[1][1]=dd1[i1][3];callsp[1][2]=num_s;
                                putsp[0][0]=dd1[i1][4];putsp[0][1]=dd1[i1][5];putsp[0][2]=num_t;
                                lencc=2;lenpp=1;sdt="CCP";
                                if(num_f==0){sdt="CP";}if(num_s==0){sdt="CP";}if(num_t==0){sdt="CC";}
                            }
                            else
                            {
                                callsp[0][0]=dd1[i1][0];callsp[0][1]=dd1[i1][1];callsp[0][2]=num_f;
                                putsp[0][0]=dd1[i1][4];putsp[0][1]=dd1[i1][5];putsp[0][2]=num_s;
                                putsp[1][0]=dd1[i1][6];putsp[1][1]=dd1[i1][7];putsp[1][2]=num_t;
                                lencc=1;lenpp=2;sdt="CPP";
                                if(num_f==0){sdt="PP";}if(num_s==0){sdt="CP";}if(num_t==0){sdt="CP";}
                            }
                        }
                        else if(md==2)
                        {
                            if(dd1[i1][7]==-2||dd1[i1][7]==-1)
                                op.four_opi(tot_amt,rn[j1][0],rn[j1][1],rn[j1][2], rn[j1][3],dd1[i1][1], dd1[i1][3] ,dd1[i1][5],dd1[i1][8]/(10.0*9.0));
                            else
                                op.four_opi(tot_amt,rn[j1][0],rn[j1][1],rn[j1][2], rn[j1][3],dd1[i1][1], dd1[i1][8]/(10.0*9.0) ,dd1[i1][5],dd1[i1][7]);

                            num_f=op.numberOf_f;
                            num_s=op.numberOf_s;
                            num_t=op.numberOf_t;
                            num_fr=op.numberOf_fr;

                            if(dd1[i1][7]==-2)
                            {
                                callsp[0][0]=dd1[i1][0];callsp[0][1]=dd1[i1][1];callsp[0][2]=num_f;
                                callsp[1][0]=dd1[i1][2];callsp[1][1]=dd1[i1][3];callsp[1][2]=num_s;
                                putsp[0][0]=dd1[i1][4];putsp[0][1]=dd1[i1][5];putsp[0][2]=num_t;
                                longsp[0][0]=dd1[i1][8];longsp[0][1]=num_fr;
                                lencc=2;lenpp=1;lenll=1;lenss=0;sdt="CCPL";
                            }
                            else if(dd1[i1][7]==-1)
                            {
                                callsp[0][0]=dd1[i1][0];callsp[0][1]=dd1[i1][1];callsp[0][2]=num_f;
                                callsp[1][0]=dd1[i1][2];callsp[1][1]=dd1[i1][3];callsp[1][2]=num_s;
                                putsp[0][0]=dd1[i1][4];putsp[0][1]=dd1[i1][5];putsp[0][2]=num_t;
                                shortsp[0][0]=dd1[i1][8];shortsp[0][1]=num_fr;
                                lencc=2;lenpp=1;lenll=0;lenss=1;sdt="CCPS";
                            }
                            else if(dd1[i1][3]==-2)
                            {
                                callsp[0][0]=dd1[i1][0];callsp[0][1]=dd1[i1][1];callsp[0][2]=num_f;
                                putsp[0][0]=dd1[i1][4];putsp[0][1]=dd1[i1][5];putsp[0][2]=num_t;
                                putsp[1][0]=dd1[i1][6];putsp[1][1]=dd1[i1][7];putsp[1][2]=num_fr;
                                longsp[0][0]=dd1[i1][8];longsp[0][1]=num_s;
                                lencc=1;lenpp=2;lenll=1;lenss=0;sdt="CPPL";
                            }
                            else
                            {
                                callsp[0][0]=dd1[i1][0];callsp[0][1]=dd1[i1][1];callsp[0][2]=num_f;
                                putsp[0][0]=dd1[i1][4];putsp[0][1]=dd1[i1][5];putsp[0][2]=num_t;
                                putsp[1][0]=dd1[i1][6];putsp[1][1]=dd1[i1][7];putsp[1][2]=num_fr;
                                shortsp[0][0]=dd1[i1][8];shortsp[0][1]=num_s;
                                lencc=1;lenpp=2;lenll=0;lenss=1;sdt="CPPS";
                            }
                        }
                        else if(md==3)
                        {
                            op.three_opi(tot_amt,rn[j1][0],rn[j1][1],rn[j1][2],dd1[i1][1], dd1[i1][8]/(10.0*9.0),dd1[i1][5]);
                            num_f=op.numberOf_f;
                            num_s=op.numberOf_s;
                            num_t=op.numberOf_t;
                            num_fr=0;
                            rn[j1][3]=0;rn3=0;

                            //if(rn[j1][0]==3&&rn[j1][1]==1&&rn[j1][2]==8&&dd1[i1][0]==1850&&dd1[i1][2]==-1&&dd1[i1][4]==1825)
                            //{
                            //  System.out.println("num : "+num_f+" , "+num_s+" , "+num_t+" , ");
                            //}
                            if(dd1[i1][3]==-2)
                            {
                                callsp[0][0]=dd1[i1][0];callsp[0][1]=dd1[i1][1];callsp[0][2]=num_f;
                                putsp[0][0]=dd1[i1][4];putsp[0][1]=dd1[i1][5];putsp[0][2]=num_t;
                                longsp[0][0]=dd1[i1][8];longsp[0][1]=num_s;
                                lencc=1;lenpp=1;lenll=1;lenss=0;sdt="CPL";
                            }
                            else
                            {
                                callsp[0][0]=dd1[i1][0];callsp[0][1]=dd1[i1][1];callsp[0][2]=num_f;
                                putsp[0][0]=dd1[i1][4];putsp[0][1]=dd1[i1][5];putsp[0][2]=num_t;
                                shortsp[0][0]=dd1[i1][8];shortsp[0][1]=num_s;
                                lencc=1;lenpp=1;lenll=0;lenss=1;sdt="CPS";
                            }

                        }
                        else if(md==1)
                        {
                            op.four_opi(tot_amt,rn[j1][0],rn[j1][1],rn[j1][2], rn[j1][3],dd1[i1][1], dd1[i1][3] ,dd1[i1][5],dd1[i1][7]);

                            num_f=op.numberOf_f;
                            num_s=op.numberOf_s;
                            num_t=op.numberOf_t;
                            num_fr=op.numberOf_fr;

                            callsp[0][0]=dd1[i1][0];callsp[0][1]=dd1[i1][1];callsp[0][2]=num_f;
                            callsp[1][0]=dd1[i1][2];callsp[1][1]=dd1[i1][3];callsp[1][2]=num_s;
                            putsp[0][0]=dd1[i1][4];putsp[0][1]=dd1[i1][5];putsp[0][2]=num_t;
                            putsp[1][0]=dd1[i1][6];putsp[1][1]=dd1[i1][7];putsp[1][2]=num_fr;
                            lencc=2;lenpp=2;sdt="CCPP";

                        }
                        else if(md==6)
                        {
                            if(dd1[i1][7]==-9999)
                                op.three_opi(tot_amt,rn[j1][0],rn[j1][1],0,dd1[i1][1], dd1[i1][3], 100);
                            else
                                op.three_opi(tot_amt,rn[j1][0],rn[j1][1],0,dd1[i1][5], dd1[i1][7],100);
                            num_f=op.numberOf_f;
                            num_s=op.numberOf_s;
                            num_t=0;
                            num_fr=0;

                            if(dd1[i1][7]==-9999)
                            {
                                callsp[0][0]=dd1[i1][0];callsp[0][1]=dd1[i1][1];callsp[0][2]=num_f;
                                callshortsp[0][0]=dd1[i1][2];callshortsp[0][1]=dd1[i1][3];callshortsp[0][2]=num_s;
                                lencc=1;lenpp=0;lenll=0;lenss=0;lenmm=1;lennn=0;
                                rn0=rn[j1][0];rn1=rn[j1][1];rn2=0;rn3=0;sdt="CM";
                            }
                            else if(dd1[i1][3]==-9999)
                            {
                                putshortsp[0][0]=dd1[i1][4];putshortsp[0][1]=dd1[i1][5];putshortsp[0][2]=num_f;
                                putsp[0][0]=dd1[i1][6];putsp[0][1]=dd1[i1][7];putsp[0][2]=num_s;
                                //System.out.println("1putsp is "+putsp[0][0]+","+putsp[0][1]+","+putsp[0][2]);
                                lencc=0;lenpp=1;lenll=0;lenss=0;lenmm=0;lennn=1;
                                rn0=0;rn1=0;rn2=rn[j1][0];rn3=rn[j1][1];sdt="NP";
                            }

                            //if(rn0==3&&rn1==4&&rn2==0&&rn3==0&&dd1[i1][0]==30000&&dd1[i1][2]==30400&&dd1[i1][4]==-9999&&dd1[i1][6]==-9999&&md==6)
                            //{
                            //  System.out.println("num : "+num_f+" , "+num_s+" , "+num_t+" , "+num_fr);
                            //}

                        }
                        else if(md==7)
                        {
                            if(dd1[i1][7]==-9999)
                                op.three_opi(tot_amt,rn[j1][0],rn[j1][1],0,dd1[i1][1], dd1[i1][3], 100);
                            else
                                op.three_opi(tot_amt,rn[j1][0],rn[j1][1],0,dd1[i1][5], dd1[i1][7],100);
                            num_f=op.numberOf_f;
                            num_s=op.numberOf_s;
                            num_t=0;
                            num_fr=0;

                            if(dd1[i1][7]==-9999)
                            {
                                callshortsp[0][0]=dd1[i1][0];callshortsp[0][1]=dd1[i1][1];callshortsp[0][2]=num_f;
                                callsp[0][0]=dd1[i1][2];callsp[0][1]=dd1[i1][3];callsp[0][2]=num_s;
                                lencc=1;lenpp=0;lenll=0;lenss=0;lenmm=1;lennn=0;
                                rn0=rn[j1][0];rn1=rn[j1][1];rn2=0;rn3=0;sdt="MC";
                            }
                            else if(dd1[i1][3]==-9999)
                            {
                                putsp[0][0]=dd1[i1][4];putsp[0][1]=dd1[i1][5];putsp[0][2]=num_f;
                                putshortsp[0][0]=dd1[i1][6];putshortsp[0][1]=dd1[i1][7];putshortsp[0][2]=num_s;
                                lencc=0;lenpp=1;lenll=0;lenss=0;lenmm=0;lennn=1;
                                rn0=0;rn1=0;rn2=rn[j1][0];rn3=rn[j1][1];sdt="PN";
                            }

                        }
                        else if(md==8)
                        {
                            op.four_opi(tot_amt,rn[j1][0],rn[j1][1],rn[j1][2], rn[j1][3],dd1[i1][1], dd1[i1][3] ,dd1[i1][5],dd1[i1][7]);

                            num_f=op.numberOf_f;
                            num_s=op.numberOf_s;
                            num_t=op.numberOf_t;
                            num_fr=op.numberOf_fr;

                            callsp[0][0]=dd1[i1][0];callsp[0][1]=dd1[i1][1];callsp[0][2]=num_f;
                            callshortsp[0][0]=dd1[i1][2];callshortsp[0][1]=dd1[i1][3];callshortsp[0][2]=num_s;
                            putshortsp[0][0]=dd1[i1][4];putshortsp[0][1]=dd1[i1][5];putshortsp[0][2]=num_t;
                            putsp[0][0]=dd1[i1][6];putsp[0][1]=dd1[i1][7];putsp[0][2]=num_fr;
                            lencc=1;lenpp=1;lenmm=1;lennn=1;sdt="CMNP";

                        }
                        else if(md==9)
                        {
                            op.four_opi(tot_amt,rn[j1][0],rn[j1][1],rn[j1][2], rn[j1][3],dd1[i1][1], dd1[i1][3] ,dd1[i1][5],dd1[i1][7]);

                            num_f=op.numberOf_f;
                            num_s=op.numberOf_s;
                            num_t=op.numberOf_t;
                            num_fr=op.numberOf_fr;

                            //if(rn0==1&&rn1==1&&rn2==3&&rn3==3&&dd1[i1][0]==28800&&dd1[i1][2]==29000&&dd1[i1][4]==29700&&dd1[i1][6]==29800&&md==9)
                            //{
                            //  System.out.println("num : "+num_f+" , "+num_s+" , "+num_t+" , "+num_fr);
                            //}

                            callshortsp[0][0]=dd1[i1][0];callshortsp[0][1]=dd1[i1][1];callshortsp[0][2]=num_f;
                            callsp[0][0]=dd1[i1][2];callsp[0][1]=dd1[i1][3];callsp[0][2]=num_s;
                            putsp[0][0]=dd1[i1][4];putsp[0][1]=dd1[i1][5];putsp[0][2]=num_t;
                            putshortsp[0][0]=dd1[i1][6];putshortsp[0][1]=dd1[i1][7];putshortsp[0][2]=num_fr;
                            lencc=1;lenpp=1;lenmm=1;lennn=1;sdt="MCPN";

                        }
                        else if(md==10)
                        {
                            op.three_opi(tot_amt,rn[j1][0],rn[j1][1],rn[j1][2],dd1[i1][1], dd1[i1][8]/(10.0*9.0),dd1[i1][5]);
                            num_f=op.numberOf_f;
                            num_s=op.numberOf_s;
                            num_t=op.numberOf_t;
                            num_fr=0;
                            rn[j1][3]=0;rn3=0;

                            //if(rn0==0&&rn1==0&&rn2==1&&rn3==4&&dd1[i1][0]==-9999&&dd1[i1][2]==-9999&&dd1[i1][4]==28800&&dd1[i1][6]==29000&&md==6)
                            //{
                            //  System.out.println("num : "+num_f+" , "+num_s+" , "+num_t+" , ");
                            //}
                            if(dd1[i1][3]==-2)
                            {
                                callshortsp[0][0]=dd1[i1][0];callshortsp[0][1]=dd1[i1][1];callshortsp[0][2]=num_f;
                                putshortsp[0][0]=dd1[i1][4];putshortsp[0][1]=dd1[i1][5];putshortsp[0][2]=num_t;
                                longsp[0][0]=dd1[i1][8];longsp[0][1]=num_s;
                                lenmm=1;lennn=1;lenll=1;lenss=0;sdt="MNL";
                            }
                            else
                            {
                                callshortsp[0][0]=dd1[i1][0];callshortsp[0][1]=dd1[i1][1];callshortsp[0][2]=num_f;
                                putshortsp[0][0]=dd1[i1][4];putshortsp[0][1]=dd1[i1][5];putshortsp[0][2]=num_t;
                                shortsp[0][0]=dd1[i1][8];shortsp[0][1]=num_s;
                                lenmm=1;lennn=1;lenll=0;lenss=1;sdt="MNS";
                            }

                        }

                        else if(md==11)
                        {
                            op.three_opi(tot_amt,rn[j1][0],rn[j1][1],rn[j1][2],dd1[i1][1], dd1[i1][8]/(10.0*9.0),dd1[i1][5]);
                            num_f=op.numberOf_f;
                            num_s=op.numberOf_s;
                            num_t=op.numberOf_t;
                            num_fr=0;
                            rn[j1][3]=0;rn3=0;

                            //if(rn0==0&&rn1==3&&rn2==1&&rn3==0&&dd1[i1][0]==29200&&dd1[i1][2]==-1&&dd1[i1][4]==29200&&dd1[i1][6]==-9999&&md==11)
                            //{
                            //  System.out.println("num : "+num_f+" , "+num_s+" , "+num_t+" , ");
                            //}
                            if(dd1[i1][3]==-2)
                            {
                                callshortsp[0][0]=dd1[i1][0];callshortsp[0][1]=dd1[i1][1];callshortsp[0][2]=num_f;
                                callsp[0][0]=dd1[i1][4];callsp[0][1]=dd1[i1][5];callsp[0][2]=num_t;
                                longsp[0][0]=dd1[i1][8];longsp[0][1]=num_s;
                                lenmm=1;lennn=0;lenll=1;lenss=0;lencc=1;sdt="MCL";
                            }
                            else
                            {
                                callshortsp[0][0]=dd1[i1][0];callshortsp[0][1]=dd1[i1][1];callshortsp[0][2]=num_f;
                                callsp[0][0]=dd1[i1][4];callsp[0][1]=dd1[i1][5];callsp[0][2]=num_t;
                                shortsp[0][0]=dd1[i1][8];shortsp[0][1]=num_s;
                                lenmm=1;lennn=0;lenll=0;lenss=1;lencc=1;sdt="MCS";
                            }

                        }
                        else if(md==12)
                        {
                            op.three_opi(tot_amt,rn[j1][0],rn[j1][1],rn[j1][2],dd1[i1][1], dd1[i1][8]/(10.0*9.0),dd1[i1][5]);
                            num_f=op.numberOf_f;
                            num_s=op.numberOf_s;
                            num_t=op.numberOf_t;
                            num_fr=0;
                            rn[j1][3]=0;rn3=0;

                            //if(rn0==0&&rn1==0&&rn2==1&&rn3==4&&dd1[i1][0]==-9999&&dd1[i1][2]==-9999&&dd1[i1][4]==28800&&dd1[i1][6]==29000&&md==6)
                            //{
                            //  System.out.println("num : "+num_f+" , "+num_s+" , "+num_t+" , ");
                            //}
                            if(dd1[i1][3]==-2)
                            {
                                putshortsp[0][0]=dd1[i1][0];putshortsp[0][1]=dd1[i1][1];putshortsp[0][2]=num_f;
                                putsp[0][0]=dd1[i1][4];putsp[0][1]=dd1[i1][5];putsp[0][2]=num_t;
                                longsp[0][0]=dd1[i1][8];longsp[0][1]=num_s;
                                lenmm=1;lennn=0;lenll=1;lenss=0;lenpp=1;sdt="NPL";
                            }
                            else
                            {
                                putshortsp[0][0]=dd1[i1][0];putshortsp[0][1]=dd1[i1][1];putshortsp[0][2]=num_f;
                                putsp[0][0]=dd1[i1][4];putsp[0][1]=dd1[i1][5];putsp[0][2]=num_t;
                                shortsp[0][0]=dd1[i1][8];shortsp[0][1]=num_s;
                                lenmm=1;lennn=0;lenll=0;lenss=1;lenpp=1;sdt="NPS";
                            }

                        }
                        else if(md==13)
                        {
                            op.three_opi(tot_amt,rn[j1][0],rn[j1][1],rn[j1][2],dd1[i1][1], dd1[i1][8]/(10.0*9.0),dd1[i1][5]);
                            num_f=op.numberOf_f;
                            num_s=op.numberOf_s;
                            num_t=op.numberOf_t;
                            num_fr=0;
                            rn[j1][3]=0;rn3=0;

                            //if(rn0==0&&rn1==0&&rn2==1&&rn3==4&&dd1[i1][0]==-9999&&dd1[i1][2]==-9999&&dd1[i1][4]==28800&&dd1[i1][6]==29000&&md==6)
                            //{
                            //  System.out.println("num : "+num_f+" , "+num_s+" , "+num_t+" , ");
                            //}
                            if(dd1[i1][3]==-2)
                            {
                                callshortsp[0][0]=dd1[i1][0];callshortsp[0][1]=dd1[i1][1];callshortsp[0][2]=num_f;
                                putsp[0][0]=dd1[i1][4];putsp[0][1]=dd1[i1][5];putsp[0][2]=num_t;
                                longsp[0][0]=dd1[i1][8];longsp[0][1]=num_s;
                                lenmm=1;lennn=0;lenll=1;lenss=0;lenpp=1;sdt="MPL";
                            }
                            else
                            {
                                callshortsp[0][0]=dd1[i1][0];callshortsp[0][1]=dd1[i1][1];callshortsp[0][2]=num_f;
                                putsp[0][0]=dd1[i1][4];putsp[0][1]=dd1[i1][5];putsp[0][2]=num_t;
                                shortsp[0][0]=dd1[i1][8];shortsp[0][1]=num_s;
                                lenmm=1;lennn=0;lenll=0;lenss=1;lenpp=1;sdt="MPS";
                            }

                        }
                        else if(md==14)
                        {
                            op.three_opi(tot_amt,rn[j1][0],rn[j1][1],rn[j1][2],dd1[i1][1], dd1[i1][8]/(10.0*9.0),dd1[i1][5]);
                            num_f=op.numberOf_f;
                            num_s=op.numberOf_s;
                            num_t=op.numberOf_t;
                            num_fr=0;
                            rn[j1][3]=0;rn3=0;

                            //if(rn0==0&&rn1==0&&rn2==1&&rn3==4&&dd1[i1][0]==-9999&&dd1[i1][2]==-9999&&dd1[i1][4]==28800&&dd1[i1][6]==29000&&md==6)
                            //{
                            //  System.out.println("num : "+num_f+" , "+num_s+" , "+num_t+" , ");
                            //}
                            if(dd1[i1][3]==-2)
                            {
                                callsp[0][0]=dd1[i1][0];callsp[0][1]=dd1[i1][1];callsp[0][2]=num_f;
                                putshortsp[0][0]=dd1[i1][4];putshortsp[0][1]=dd1[i1][5];putshortsp[0][2]=num_t;
                                longsp[0][0]=dd1[i1][8];longsp[0][1]=num_s;
                                lenmm=1;lennn=0;lenll=1;lenss=0;lencc=1;sdt="CNL";
                            }
                            else
                            {
                                callsp[0][0]=dd1[i1][0];callsp[0][1]=dd1[i1][1];callsp[0][2]=num_f;
                                putshortsp[0][0]=dd1[i1][4];putshortsp[0][1]=dd1[i1][5];putshortsp[0][2]=num_t;
                                shortsp[0][0]=dd1[i1][8];shortsp[0][1]=num_s;
                                lenmm=1;lennn=0;lenll=0;lenss=1;lencc=1;sdt="CNS";
                            }

                        }
                        else if(md==15)
                        {
                            if(dd1[i1][7]==-9999)
                                op.three_opi(tot_amt,rn[j1][0],rn[j1][1],0,dd1[i1][1], dd1[i1][8]/(10.0*9.0), 100);
                            else
                                op.three_opi(tot_amt,rn[j1][0],rn[j1][1],0,dd1[i1][5], dd1[i1][8]/(10.0*9.0),100);
                            num_f=op.numberOf_f;
                            num_s=op.numberOf_s;
                            num_t=0;
                            num_fr=0;

                            if(dd1[i1][7]==-2)
                            {
                                putshortsp[0][0]=dd1[i1][4];putshortsp[0][1]=dd1[i1][5];putshortsp[0][2]=num_f;
                                longsp[0][0]=dd1[i1][8];longsp[0][1]=num_s;
                                lenmm=0;lennn=1;lenll=1;lenss=0;rn0=rn[j1][0];rn0=0;rn1=0;rn2=rn[j1][0];rn3=rn[j1][1];sdt="NL";
                            }
                            else if(dd1[i1][7]==-1)
                            {
                                putshortsp[0][0]=dd1[i1][4];putshortsp[0][1]=dd1[i1][5];putshortsp[0][2]=num_f;
                                shortsp[0][0]=dd1[i1][8];shortsp[0][1]=num_s;
                                lenmm=0;lennn=1;lenll=0;lenss=1;rn0=rn[j1][0];rn0=0;rn1=0;rn2=rn[j1][0];rn3=rn[j1][1];sdt="NS";
                            }
                            else if(dd1[i1][3]==-2)
                            {
                                callshortsp[0][0]=dd1[i1][0];callshortsp[0][1]=dd1[i1][1];callshortsp[0][2]=num_f;
                                longsp[0][0]=dd1[i1][8];longsp[0][1]=num_s;
                                lenmm=1;lennn=0;lenll=1;lenss=0;rn0=rn[j1][0];rn1=rn[j1][1];rn2=0;rn3=0;sdt="ML";
                            }
                            else
                            {
                                callshortsp[0][0]=dd1[i1][0];callshortsp[0][1]=dd1[i1][1];callshortsp[0][2]=num_f;
                                shortsp[0][0]=dd1[i1][8];shortsp[0][1]=num_s;
                                lenmm=1;lennn=0;lenll=0;lenss=1;rn0=rn[j1][0];rn1=rn[j1][1];rn2=0;rn3=0;sdt="MS";
                            }

                        }
                        else if(md==16)
                        {
                            if(dd1[i1][1]!=-9999)
                                op.three_opi(tot_amt,rn[j1][0],0,0,dd1[i1][1],100, 100);
                            else if(dd1[i1][3]!=-9999)
                                op.three_opi(tot_amt,rn[j1][0],0,0,dd1[i1][3],100, 100);
                            else if(dd1[i1][5]!=-9999)
                                op.three_opi(tot_amt,rn[j1][0],0,0,dd1[i1][5],100, 100);
                            else
                                op.three_opi(tot_amt,rn[j1][0],0,0,dd1[i1][7],100, 100);

                            num_f=op.numberOf_f;
                            num_s=0;
                            num_t=0;
                            num_fr=0;

                            if(dd1[i1][1]!=-9999)
                            {
                                callsp[0][0]=dd1[i1][0];callsp[0][1]=dd1[i1][1];callsp[0][2]=num_f;
                                lencc=1;rn0=1;rn1=0;rn2=0;rn3=0;sdt="C";
                            }
                            else if(dd1[i1][3]!=-9999)
                            {
                                putsp[0][0]=dd1[i1][2];putsp[0][1]=dd1[i1][3];putsp[0][2]=num_f;
                                lenpp=1;rn0=0;rn1=1;rn2=0;rn3=0;sdt="P";
                            }
                            else if(dd1[i1][5]!=-9999)
                            {
                                callshortsp[0][0]=dd1[i1][4];callshortsp[0][1]=dd1[i1][5];callshortsp[0][2]=num_f;
                                lenmm=1;rn0=0;rn1=0;rn2=1;rn3=0;sdt="M";                   
                            }
                            else
                            {
                                putshortsp[0][0]=dd1[i1][6];putshortsp[0][1]=dd1[i1][7];putshortsp[0][2]=num_f;
                                lennn=1;rn0=0;rn1=0;rn2=0;rn3=1;sdt="N";
                            }

                        }
                        else if(md==17)
                        {
                            op.three_opi(tot_amt,rn[j1][0],rn[j1][1],0,dd1[i1][1], dd1[i1][5], 100);
                            num_f=op.numberOf_f;
                            num_s=op.numberOf_s;
                            num_t=0;
                            num_fr=0;

                            callshortsp[0][0]=dd1[i1][0];callshortsp[0][1]=dd1[i1][1];callshortsp[0][2]=num_f;
                            putshortsp[0][0]=dd1[i1][4];putshortsp[0][1]=dd1[i1][5];putshortsp[0][2]=num_s;
                            lencc=0;lenpp=0;lenll=0;lenss=0;lenmm=1;lennn=1;
                            rn0=rn[j1][0];rn1=rn[j1][1];rn2=0;rn3=0;sdt="MN";

                        }
                        else if(md==18)
                        {
                            if(dd1[i1][7]==-9999)
                            {op.three_opi(tot_amt,rn[j1][0],rn[j1][1],rn[j1][2],dd1[i1][1], dd1[i1][3],dd1[i1][5]);}
                            else
                            {op.three_opi(tot_amt,rn[j1][0],rn[j1][1],rn[j1][2],dd1[i1][3], dd1[i1][5],dd1[i1][7]);}

                            num_f=op.numberOf_f;
                            num_s=op.numberOf_s;
                            num_t=op.numberOf_t;
                            num_fr=0;
                            rn[j1][3]=0;//rn3=0;

                            if(dd1[i1][7]==-9999)
                            {
                                callsp[0][0]=dd1[i1][0];callsp[0][1]=dd1[i1][1];callsp[0][2]=num_f;
                                callsp[1][0]=dd1[i1][2];callsp[1][1]=dd1[i1][3];callsp[1][2]=num_s;
                                callshortsp[0][0]=dd1[i1][4];callshortsp[0][1]=dd1[i1][5];callshortsp[0][2]=num_t;
                                lencc=2;lenpp=0;lenmm=1;lennn=0;rn3=0;sdt="CCM";
                            }
                            else
                            {
                                putsp[0][0]=dd1[i1][2];putsp[0][1]=dd1[i1][3];putsp[0][2]=num_f;
                                putsp[1][0]=dd1[i1][4];putsp[1][1]=dd1[i1][5];putsp[1][2]=num_s;
                                putshortsp[0][0]=dd1[i1][6];putshortsp[0][1]=dd1[i1][7];putshortsp[0][2]=num_t;
                                lencc=0;lenpp=2;lenmm=0;lennn=1;rn0=0;rn1=rn[j1][0];rn2=rn[j1][1];rn3=rn[j1][2];sdt="PPN";
                            }
                        }
                        else if(md==19)
                        {
                            if(dd1[i1][3]==-9999)
                            {op.three_opi(tot_amt,rn[j1][0],rn[j1][1],rn[j1][2],dd1[i1][1], dd1[i1][5],dd1[i1][7]);}
                            else
                            {op.three_opi(tot_amt,rn[j1][0],rn[j1][1],rn[j1][2],dd1[i1][1], dd1[i1][3],dd1[i1][7]);}

                            num_f=op.numberOf_f;
                            num_s=op.numberOf_s;
                            num_t=op.numberOf_t;
                            num_fr=0;
                            rn[j1][3]=0;//rn3=0;

                            if(dd1[i1][3]==-9999)
                            {
                                callsp[0][0]=dd1[i1][0];callsp[0][1]=dd1[i1][1];callsp[0][2]=num_f;
                                callshortsp[0][0]=dd1[i1][4];callshortsp[0][1]=dd1[i1][5];callshortsp[0][2]=num_s;
                                putsp[0][0]=dd1[i1][6];putsp[0][1]=dd1[i1][7];putsp[0][2]=num_t;

                                lencc=1;lenpp=1;lenmm=1;lennn=0;rn0=rn[j1][0];rn1=0;rn2=rn[j1][1];rn3=rn[j1][2];sdt="CMP";
                            }
                            else
                            {
                                callsp[0][0]=dd1[i1][0];callsp[0][1]=dd1[i1][1];callsp[0][2]=num_f;
                                putshortsp[0][0]=dd1[i1][2];putshortsp[0][1]=dd1[i1][3];putshortsp[0][2]=num_s;
                                putsp[0][0]=dd1[i1][6];putsp[0][1]=dd1[i1][7];putsp[0][2]=num_t;

                                lencc=1;lenpp=1;lenmm=0;lennn=1;rn0=rn[j1][0];rn1=rn[j1][1];rn2=0;rn3=rn[j1][2];sdt="CNP";
                            }
                        }
                        else if(md==20)
                        {
                            if(dd1[i1][7]==-9999)
                            {op.three_opi(tot_amt,rn[j1][0],rn[j1][1],rn[j1][2],dd1[i1][1], dd1[i1][3],dd1[i1][5]);}
                            else
                            {op.three_opi(tot_amt,rn[j1][0],rn[j1][1],rn[j1][2],dd1[i1][3], dd1[i1][5],dd1[i1][7]);}

                            num_f=op.numberOf_f;
                            num_s=op.numberOf_s;
                            num_t=op.numberOf_t;
                            num_fr=0;
                            rn[j1][3]=0;//rn3=0;

                            if(dd1[i1][7]==-9999)
                            {
                                callshortsp[0][0]=dd1[i1][0];callshortsp[0][1]=dd1[i1][1];callshortsp[0][2]=num_f;
                                callshortsp[1][0]=dd1[i1][2];callshortsp[1][1]=dd1[i1][3];callshortsp[1][2]=num_s;
                                callsp[0][0]=dd1[i1][4];callsp[0][1]=dd1[i1][5];callsp[0][2]=num_t;
                                lencc=1;lenpp=0;lenmm=2;lennn=0;rn3=0;sdt="MMC";
                            }
                            else
                            {
                                putshortsp[0][0]=dd1[i1][2];putshortsp[0][1]=dd1[i1][3];putshortsp[0][2]=num_f;
                                putshortsp[1][0]=dd1[i1][4];putshortsp[1][1]=dd1[i1][5];putshortsp[1][2]=num_s;
                                putsp[0][0]=dd1[i1][6];putsp[0][1]=dd1[i1][7];putsp[0][2]=num_t;
                                lencc=0;lenpp=1;lenmm=0;lennn=2;rn0=0;rn1=rn[j1][0];rn2=rn[j1][1];rn3=rn[j1][2];sdt="NNP";
                            }
                        }
                        else if(md==21)
                        {
                            if(dd1[i1][3]==-9999)
                            {op.three_opi(tot_amt,rn[j1][0],rn[j1][1],rn[j1][2],dd1[i1][1], dd1[i1][5],dd1[i1][7]);}
                            else
                            {op.three_opi(tot_amt,rn[j1][0],rn[j1][1],rn[j1][2],dd1[i1][1], dd1[i1][3],dd1[i1][7]);}

                            num_f=op.numberOf_f;
                            num_s=op.numberOf_s;
                            num_t=op.numberOf_t;
                            num_fr=0;
                            rn[j1][3]=0;//rn3=0;

                            if(dd1[i1][3]==-9999)
                            {
                                callshortsp[0][0]=dd1[i1][0];callshortsp[0][1]=dd1[i1][1];callshortsp[0][2]=num_f;
                                callsp[0][0]=dd1[i1][4];callsp[0][1]=dd1[i1][5];callsp[0][2]=num_s;
                                putshortsp[0][0]=dd1[i1][6];putshortsp[0][1]=dd1[i1][7];putshortsp[0][2]=num_t;

                                lencc=1;lenpp=0;lenmm=1;lennn=1;rn0=rn[j1][0];rn1=0;rn2=rn[j1][1];rn3=rn[j1][2];sdt="MCN";
                            }
                            else
                            {
                                callshortsp[0][0]=dd1[i1][0];callshortsp[0][1]=dd1[i1][1];callshortsp[0][2]=num_f;
                                putsp[0][0]=dd1[i1][2];putsp[0][1]=dd1[i1][3];putsp[0][2]=num_s;
                                putshortsp[0][0]=dd1[i1][6];putshortsp[0][1]=dd1[i1][7];putshortsp[0][2]=num_t;

                                lencc=0;lenpp=1;lenmm=1;lennn=1;rn0=rn[j1][0];rn1=rn[j1][1];rn2=0;rn3=rn[j1][2];sdt="MPN";
                            }
                        }
                        else if(md==22)
                        {
                            if(dd1[i1][7]==-9999)
                            {op.three_opi(tot_amt,rn[j1][0],rn[j1][1],rn[j1][2],dd1[i1][1], dd1[i1][3],dd1[i1][5]);}
                            else
                            {op.three_opi(tot_amt,rn[j1][0],rn[j1][1],rn[j1][2],dd1[i1][1], dd1[i1][5],dd1[i1][7]);}

                            num_f=op.numberOf_f;
                            num_s=op.numberOf_s;
                            num_t=op.numberOf_t;
                            num_fr=0;
                            rn[j1][3]=0;//rn3=0;

                            if(dd1[i1][7]==-9999)
                            {
                                callsp[0][0]=dd1[i1][0];callsp[0][1]=dd1[i1][1];callsp[0][2]=num_f;
                                callsp[1][0]=dd1[i1][2];callsp[1][1]=dd1[i1][3];callsp[1][2]=num_s;
                                putshortsp[0][0]=dd1[i1][4];putshortsp[0][1]=dd1[i1][5];putshortsp[0][2]=num_t;
                                lencc=2;lenpp=0;lenmm=0;lennn=1;rn3=0;sdt="CCN";
                            }
                            else
                            {
                                callshortsp[0][0]=dd1[i1][0];callshortsp[0][1]=dd1[i1][1];callshortsp[0][2]=num_f;
                                putsp[0][0]=dd1[i1][4];putsp[0][1]=dd1[i1][5];putsp[0][2]=num_s;
                                putsp[1][0]=dd1[i1][6];putsp[1][1]=dd1[i1][7];putsp[1][2]=num_t;
                                lencc=0;lenpp=2;lenmm=1;lennn=0;rn0=rn[j1][0];rn1=0;rn2=rn[j1][1];rn3=rn[j1][2];sdt="MPP";
                            }
                        }
                        else if(md==23)
                        {
                            if(dd1[i1][7]==-9999)
                            {op.three_opi(tot_amt,rn[j1][0],rn[j1][1],rn[j1][2],dd1[i1][1], dd1[i1][3],dd1[i1][5]);}
                            else
                            {op.three_opi(tot_amt,rn[j1][0],rn[j1][1],rn[j1][2],dd1[i1][1], dd1[i1][5],dd1[i1][7]);}

                            num_f=op.numberOf_f;
                            num_s=op.numberOf_s;
                            num_t=op.numberOf_t;
                            num_fr=0;
                            rn[j1][3]=0;//rn3=0;

                            if(dd1[i1][7]==-9999)
                            {
                                callshortsp[0][0]=dd1[i1][0];callshortsp[0][1]=dd1[i1][1];callshortsp[0][2]=num_f;
                                callshortsp[1][0]=dd1[i1][2];callshortsp[1][1]=dd1[i1][3];callshortsp[1][2]=num_s;
                                putsp[0][0]=dd1[i1][4];putsp[0][1]=dd1[i1][5];putsp[0][2]=num_t;
                                lencc=0;lenpp=1;lenmm=2;lennn=0;rn3=0;sdt="MMP";
                            }
                            else
                            {
                                callsp[0][0]=dd1[i1][0];callsp[0][1]=dd1[i1][1];callsp[0][2]=num_f;
                                putshortsp[0][0]=dd1[i1][4];putshortsp[0][1]=dd1[i1][5];putshortsp[0][2]=num_s;
                                putshortsp[1][0]=dd1[i1][6];putshortsp[1][1]=dd1[i1][7];putshortsp[1][2]=num_t;
                                lencc=1;lenpp=0;lenmm=0;lennn=2;rn0=rn[j1][0];rn1=0;rn2=rn[j1][1];rn3=rn[j1][2];sdt="CNN";
                            }
                        }
                        else if(md==24)
                        {
                            if(dd1[i1][7]==-9999)
                                op.three_opi(tot_amt,rn[j1][0],rn[j1][1],0,dd1[i1][1], dd1[i1][3], 100);
                            else
                                op.three_opi(tot_amt,rn[j1][0],rn[j1][1],0,dd1[i1][5], dd1[i1][7],100);
                            num_f=op.numberOf_f;
                            num_s=op.numberOf_s;
                            num_t=0;
                            num_fr=0;

                            if(dd1[i1][7]==-9999)
                            {
                                callsp[0][0]=dd1[i1][0];callsp[0][1]=dd1[i1][1];callsp[0][2]=num_f;
                                putshortsp[0][0]=dd1[i1][2];putshortsp[0][1]=dd1[i1][3];putshortsp[0][2]=num_s;
                                lencc=1;lenpp=0;lenll=0;lenss=0;lenmm=0;lennn=1;
                                rn0=rn[j1][0];rn1=rn[j1][1];rn2=0;rn3=0;sdt="CN";
                            }
                            else if(dd1[i1][3]==-9999)
                            {
                                callshortsp[0][0]=dd1[i1][4];callshortsp[0][1]=dd1[i1][5];callshortsp[0][2]=num_f;
                                putsp[0][0]=dd1[i1][6];putsp[0][1]=dd1[i1][7];putsp[0][2]=num_s;
                                //System.out.println("1putsp is "+putsp[0][0]+","+putsp[0][1]+","+putsp[0][2]);
                                lencc=0;lenpp=1;lenll=0;lenss=0;lenmm=1;lennn=0;
                                rn0=0;rn1=0;rn2=rn[j1][0];rn3=rn[j1][1];sdt="MP";
                            }

                            //if(rn0==3&&rn1==4&&rn2==0&&rn3==0&&dd1[i1][0]==30000&&dd1[i1][2]==30400&&dd1[i1][4]==-9999&&dd1[i1][6]==-9999&&md==6)
                            //{
                            //  System.out.println("num : "+num_f+" , "+num_s+" , "+num_t+" , "+num_fr);
                            //}

                        }
                        else if(md==25)
                        {
                            if(dd1[i1][1]!=-9999)
                                op.three_opi(tot_amt,rn[j1][0],0,0,dd1[i1][1],100, 100);
                            num_f=op.numberOf_f;
                            num_s=0;
                            num_t=0;
                            num_fr=0;

                            if(dd1[i1][1]!=-9999)
                            {
                                callsp[0][0]=dd1[i1][0];callsp[0][1]=dd1[i1][1];callsp[0][2]=num_f;
                                lencc=1;rn0=1;rn1=0;rn2=0;rn3=0;sdt="C";
                            }
                        }
                        else if(md==26)
                        {
                            if(dd1[i1][3]!=-9999)
                                op.three_opi(tot_amt,rn[j1][0],0,0,dd1[i1][3],100, 100);
                            num_f=op.numberOf_f;
                            num_s=0;
                            num_t=0;
                            num_fr=0;

                            if(dd1[i1][3]!=-9999)
                            {
                                putsp[0][0]=dd1[i1][2];putsp[0][1]=dd1[i1][3];putsp[0][2]=num_f;
                                lenpp=1;rn0=0;rn1=1;rn2=0;rn3=0;sdt="P";
                            }
                        }
                        else if(md==27)
                        {
                            if(dd1[i1][1]!=-9999)
                                op.three_opi(tot_amt,rn[j1][0],0,0,dd1[i1][1],100, 100);
                            num_f=op.numberOf_f;
                            num_s=0;
                            num_t=0;
                            num_fr=0;

                            if(dd1[i1][1]!=-9999)
                            {
                                callshortsp[0][0]=dd1[i1][0];callshortsp[0][1]=dd1[i1][1];callshortsp[0][2]=num_f;
                                lenmm=1;rn0=1;rn1=0;rn2=0;rn3=0;sdt="M";
                            }
                        }
                        else if(md==28)
                        {
                            if(dd1[i1][3]!=-9999)
                                op.three_opi(tot_amt,rn[j1][0],0,0,dd1[i1][3],100, 100);
                            num_f=op.numberOf_f;
                            num_s=0;
                            num_t=0;
                            num_fr=0;

                            if(dd1[i1][3]!=-9999)
                            {
                                putshortsp[0][0]=dd1[i1][2];putshortsp[0][1]=dd1[i1][3];putshortsp[0][2]=num_f;
                                lennn=1;rn0=0;rn1=1;rn2=0;rn3=0;sdt="N";
                            }
                        }

                        /*double num_C=op.numberOfCalls;
                        double num_P=op.numberOfPuts;*/
                        //if(dd1[i1][0]==29800&&dd1[i1][4]==-9999&&dd1[i1][6]==-9999&&md==6)
                        //{
                        //System.out.println("2putsp is "+putsp[0][0]+","+putsp[0][1]+","+putsp[0][2]);
                        //System.out.println("num is "+num_f+","+num_s+","+num_t+","+num_fr);
                        //if(i1==0&&j1==0)
                        //{
                        //    System.out.println("\ninv-volt: "+interval+","+volt);
                        for(i4=0;i4<lencc;i4++)
                            callsp[i4][1] = Math.max(0, dd1[i1][8] - callsp[i4][0])+(callsp[i4][1]-Math.max(0, dd1[i1][8] - callsp[i4][0]))*(tot_interval-iinterval)/tot_interval*(vvolt)/(tot_volt/2.0);
                        for(i4=0;i4<lenpp;i4++)
                            putsp[i4][1] = Math.max(0,  putsp[i4][0] - dd1[i1][8])+(putsp[i4][1]-Math.max(0,  putsp[i4][0] - dd1[i1][8]))*(tot_interval-iinterval)/tot_interval*(vvolt)/(tot_volt/2.0);
                        for(i4=0;i4<lenmm;i4++)
                            callshortsp[i4][1] = Math.max(0, dd1[i1][8] - callshortsp[i4][0])+(callshortsp[i4][1]-Math.max(0, dd1[i1][8] - callshortsp[i4][0]))*(tot_interval-iinterval)/tot_interval*(vvolt)/(tot_volt/2.0);
                        for(i4=0;i4<lennn;i4++)
                            putshortsp[i4][1] = Math.max(0,  putshortsp[i4][0] - dd1[i1][8])+(putshortsp[i4][1]-Math.max(0,  putshortsp[i4][0] - dd1[i1][8]))*(tot_interval-iinterval)/tot_interval*(vvolt)/(tot_volt/2.0);

                        ///*
                        if(lenn30>0&&lenn30==len_dda_chk)
                        { 
                            System.out.println("dd1: "+dd1[i1][1]+","+dd1[i1][3]+","+dd1[i1][5]+","+dd1[i1][7]);
                            mai(callsp,lencc,putsp,lenpp,longsp,lenll,shortsp,lenss,callshortsp,lenmm,putshortsp,lennn
                            , dd1[i1][8],0,tot_amt,xx_time,yy_time);
                            if((profit1>0||profit2>0||profit6>0||profit7>0)&&min>max_loss&&md_max>min_prof)
                            {
                                mai(callsp,lencc,putsp,lenpp,longsp,lenll,shortsp,lenss,callshortsp,lenmm,putshortsp,lennn
                                , dd1[i1][8],1,tot_amt,xx_time,yy_time);
                                System.out.println("\n ["+sdt+"] md: "+md);
                                i1=len1+10000;j1=ratio_num+10000;j3=inv_volt_arr_len+10000;
                                stp_nw=1;
                                //System.exit(0);
                            }    
                        }
                        //*/
                        else{
                            mai(callsp,lencc,putsp,lenpp,longsp,lenll,shortsp,lenss,callshortsp,lenmm,putshortsp,lennn
                            , dd1[i1][8],0,tot_amt,xx_time,yy_time);
                        }
                        //
                        //}
                        if(min>=0)
                        {
                            dda31[lenn31][0]=dd1[i1][0];
                            dda31[lenn31][1]=dd1[i1][1];
                            dda31[lenn31][2]=rn0;//rn[j1][0];
                            dda31[lenn31][3]=dd1[i1][2];
                            dda31[lenn31][4]=dd1[i1][3];
                            dda31[lenn31][5]=rn1;//rn[j1][1];
                            dda31[lenn31][6]=dd1[i1][4];
                            dda31[lenn31][7]=dd1[i1][5];
                            dda31[lenn31][8]=rn2;//rn[j1][2];
                            dda31[lenn31][9]=dd1[i1][6];
                            dda31[lenn31][10]=dd1[i1][7];
                            dda31[lenn31][11]=rn3;//rn[j1][3];
                            dda31[lenn31][12]=lencc;
                            dda31[lenn31][13]=lenpp;
                            dda31[lenn31][14]=firstBreakeven;
                            dda31[lenn31][15]=LastBreakeven;
                            dda31[lenn31][16]=min;
                            dda31[lenn31][17]=profit1;
                            dda31[lenn31][18]=profit3;//j3;
                            dda31[lenn31][19]=profit4;
                            dda31[lenn31][20]=profit5;
                            dda31[lenn31][21]=profit2;
                            dda31[lenn31][22]=seprofit1;//dda31[lenn31][22]=j2;
                            dda31[lenn31][23]=seprofit2;
                            dda31[lenn31][24]=tot_extr;
                            dda31[lenn31][25]=max;
                            dda31[lenn31][26]=lenmm;
                            dda31[lenn31][27]=lennn;
                            dda31[lenn31][28]=md;
                            dda31[lenn31][29]=profit6;
                            dda31[lenn31][30]=profit7;
                            dda31[lenn31][31]=num_pos;
                            dda31[lenn31][32]=profbyper;
                            dda31[lenn31][33]=num_mid_pos;
                            dda31[lenn31][34]=tot_amt;
                            dda31[lenn31][35]=short_tot_extr;
                            dda31[lenn31][36]=cust_min;
                            dda31[lenn31][37]=lenll;
                            dda31[lenn31][38]=lenss;
                            dda31[lenn31][39]=lenmm;
                            dda31[lenn31][40]=lennn;
                            dda31[lenn31][41]=secombinedNetProfit;
                            dda31[lenn31][42]=far_prof1;
                            dda31[lenn31][43]=far_prof2;
                            dda31[lenn31][44]=prof_tott;
                            dda31[lenn31][45]=pos_prof_tott;
                            dda31[lenn31][46]=rat_wy;
                            dda31[lenn31][47]=md_min;
                            dda31[lenn31][48]=md_max;
                            dda31[lenn31][49]=iinterval;
                            dda31[lenn31][50]=vvolt;
                            dda31[lenn31][51]=lenn30;
                            dda31[lenn31][52]=profit8;
                            dda31[lenn31][53]=profit9;
                            dda31[lenn31][54]=tot_cr;
                            d_dt31[lenn31]=s_dt;
                            d_dts31[lenn31]=sdt;
                            x_dt31[lenn31]=x_data;
                            y_dt31[lenn31]=y_data;
                            lenn31++;
                        }
                        else if((profit1>0||profit2>0||profit6>0||profit7>0)&&min>max_loss&&md_max>min_prof)
                        {
                            dda[lenn][0]=dd1[i1][0];
                            dda[lenn][1]=dd1[i1][1];
                            dda[lenn][2]=rn0;//rn[j1][0];
                            dda[lenn][3]=dd1[i1][2];
                            dda[lenn][4]=dd1[i1][3];
                            dda[lenn][5]=rn1;//rn[j1][1];
                            dda[lenn][6]=dd1[i1][4];
                            dda[lenn][7]=dd1[i1][5];
                            dda[lenn][8]=rn2;//rn[j1][2];
                            dda[lenn][9]=dd1[i1][6];
                            dda[lenn][10]=dd1[i1][7];
                            dda[lenn][11]=rn3;//rn[j1][3];
                            dda[lenn][12]=lencc;
                            dda[lenn][13]=lenpp;
                            dda[lenn][14]=firstBreakeven;
                            dda[lenn][15]=LastBreakeven;
                            dda[lenn][16]=min;
                            dda[lenn][17]=profit1;
                            dda[lenn][18]=profit3;//j3;
                            dda[lenn][19]=profit4;
                            dda[lenn][20]=profit5;
                            dda[lenn][21]=profit2;
                            dda[lenn][22]=seprofit1;//dda[lenn][22]=j2;
                            dda[lenn][23]=seprofit2;
                            dda[lenn][24]=tot_extr;
                            dda[lenn][25]=max;
                            dda[lenn][26]=lenmm;
                            dda[lenn][27]=lennn;
                            dda[lenn][28]=md;
                            dda[lenn][29]=profit6;
                            dda[lenn][30]=profit7;
                            dda[lenn][31]=num_pos;
                            dda[lenn][32]=profbyper;
                            dda[lenn][33]=num_mid_pos;
                            dda[lenn][34]=tot_amt;
                            dda[lenn][35]=short_tot_extr;
                            dda[lenn][36]=cust_min;
                            dda[lenn][37]=lenll;
                            dda[lenn][38]=lenss;
                            dda[lenn][39]=lenmm;
                            dda[lenn][40]=lennn;
                            dda[lenn][41]=secombinedNetProfit;
                            dda[lenn][42]=far_prof1;
                            dda[lenn][43]=far_prof2;
                            dda[lenn][44]=prof_tott;
                            dda[lenn][45]=pos_prof_tott;
                            dda[lenn][46]=rat_wy;
                            dda[lenn][47]=md_min;
                            dda[lenn][48]=md_max;
                            dda[lenn][49]=iinterval;
                            dda[lenn][50]=vvolt;
                            dda[lenn][51]=lenn30;
                            dda[lenn][52]=profit8;
                            dda[lenn][53]=profit9;
                            dda[lenn][54]=tot_cr;
                            dda[lenn][55]=num_pos_nr;
                            dda[lenn][56]=conty1;
                            dda[lenn][57]=conty2;
                            dda[lenn][58]=profit10;
                            dda[lenn][59]=profit11;
                            d_dt[lenn]=s_dt;
                            d_dts[lenn]=sdt;
                            x_dt[lenn]=x_data;
                            y_dt[lenn]=y_data;
                            y1_dt[lenn]=y1_data;
                            lenn++;
                            lenn30++;
                        }
                        //mai(dd[i1][0],dd[i1][1],dd[i1][2],dd[i1][3],dd[i1][4],rn[j1][0],rn[j1][1]);
                        //mai(dd[i1][0],dd[i1][1],dd[i1][2],dd[i1][3],dd[i1][4],num_C,num_P);

                    }
                    /*
                    dd1[i1][1]=t1;
                    dd1[i1][3]=t2;
                    dd1[i1][5]=t3;
                    dd1[i1][7]=t4;
                     */
                }
                if(rat_wy==1)
                {j1=ratio_num+1000;//System.out.println("j1 is "+j1);
                }
            }
        }
        //}

        if(pntt==1)
        {
            System.out.println();//System.out.println("tot_amt in maka is "+tot_amt+" max loss is "+max_loss);
            System.out.println("dda lenn30 is "+lenn30);
        }
        lengt=lenn;
        /*
        System.out.println("\ndda lenn is "+lenn);
        for (int i1=0;i1<lenn;i1++)
        {
        System.out.printf(i1+" : ");System.out.printf("\nx: %s ",x_dt[i1]);System.out.printf("\ny: %s ",y_dt[i1]);//System.out.printf(" %s ",d_dts[i1]);
        System.out.printf("\ny1: %s ",y1_dt[i1]);
        /*
        for(j=0;j<12;j++)
        {
        System.out.printf("%.2f , ",dda[i1][j]);
        }
        for(j=17;j<22;j++)
        {
        System.out.printf("%.2f , ",dda[i1][j]);
        }
        for(j=46;j<47;j++)
        {
        System.out.printf("%.2f , ",dda[i1][j]);
        }

        System.out.println();

        }
         */
        return dda;
    }
    double dif1[]=new double[2000000];;//=new double[1000000];
    String pnt_nm[]={"0 first_blank","1 breakeven","2 first_dif","3 last_dif","4 one_dif","5 first_prof","6 last_prof","7 dob_comp_prof",
            "8 dip","9 profbydip","10 profbydif", "11 profbypositive","12 profbypositivedif","13 profgp","14 profbypositivedifsm","15 profgpsm",
            "16 profgpdif","17 diftodip","18 profsell","19 profposextr", "20 profonedip", "21 Mprofonedip", "22profoneMdip", "23profdifdip", 
            "24profposdifdip", "25profanymid", "26profpoints", "27profpointsmiddip", "28profbyper","29profendmidpoints",
            "30profdecentmidpoints","31profrrmidpoints","32profmidpoints","33profmidpointssell","34profcustmidpoints","35profjusttot",
            "36md_min","37md_max","38md_maxbymin","39profbyperbymin","40profbypertomaxbymin","41md_maxbymd_min","42profbyperbymd_min",
            "43profbypertomaxbymd_min","44cr_md_min","45cr_md_maxbymin","46cr_profbyperbymin","47cr_bymin","48cr_credit","49posfrommid"
        ,"50cr_conty12","51 0%maxbymin","52 0.5%maxbymin","53 1.5%maxbymin","54 3%maxbymin","55 4.5%maxbymin","56 7.5%maxbymin","57maxbymin"};
    public void st_prt(double dda[][], int st_i, int lenn, int i5,double spp)
    {
        int j=0;
        System.out.println("I am in prnt 1");
        //dif1=new double[1000000];
        /*
        1 double dif[]=new double[lenn];
        2 double first_dif[]=new double[lenn];
        3double last_dif[]=new double[lenn];
        4double one_dif[]=new double[lenn];
        5double first_prof[]=new double[lenn];
        6double last_prof[]=new double[lenn];
        7double dob_comp_prof[]=new double[lenn];
        8double dip[]=new double[lenn];
        9double profbydip[]=new double[lenn];
        10double profbydif[]=new double[lenn];
        11double profbypositive[]=new double[lenn];
        12double profbypositivedif[]=new double[lenn];
        13double profgp[]=new double[lenn];
        14double profbypositivedifsm[]=new double[lenn];
        15double profgpsm[]=new double[lenn];
        16double profgpdif[]=new double[lenn];
        17double diftodip[]=new double[lenn];
        18double profsell[]=new double[lenn];
        19double profposextr[]=new double[lenn];
        20double profonedip[]=new double[lenn];
        21double mprofonedip[]=new double[lenn];
        22double profonemdip[]=new double[lenn];
        23double profdifdip[]=new double[lenn];
        24double profposdifdip[]=new double[lenn];
        25double profthreemiddip[]=new double[lenn];
        26double profpoints[]=new double[lenn];
        27double profpointsmiddip[]=new double[lenn];
        28double profbyper[]=new double[lenn];
        29double profendmidpoints[]=new double[lenn];
        30double profdecentmidpoints[]=new double[lenn];
        31double profrrmidpoints[]=new double[lenn];
        32double profmidpoints[]=new double[lenn];
        33double profmidpointssell[]=new double[lenn];
        34double profcustmidpoints[]=new double[lenn];
        35double profjusttot[]=new double[lenn];
        36double md_minn[]=new double[lenn];
        37double md_maxx[]=new double[lenn];
        38double md_maxxbyminn[]=new double[lenn];
        39double profbyperbymin[]=new double[lenn];
        40double profbypertomaxbymin[]=new double[lenn];
         *///4 0,7 1, 10 1, 20 1 
        int l_dif=lenn-st_i;int i4=0;double m1=0,m2=0;
        double dif[]=new double[pnt_nm.length];
        //dif=new double[35][l_dif];
        for (int i1=st_i;i1<lenn;i1++)
        {
            /*dif[i1]=dda[i1][6]-dda[i1][7];
            if(dif[i1]>0)
            dif[i1]*=(-1);*/
            //if(dda[i1][3]>=2&&dda[i1][7]>=2)
            dif[1]=Math.abs(dda[i1][14]-dda[i1][15]);

            dif[2]=Math.abs(spp-dda[i1][14]);
            dif[3]=Math.abs(dda[i1][15]-spp);
            dif[4]=Math.min(Math.min(dif[2], dif[3]), dif[1]);

            dif[5]=dda[i1][17];
            dif[6]=dda[i1][21];
            dif[7]=Math.max(dif[5],dif[6]);

            dif[8]=dda[i1][16];
            dif[20]=Math.max(dif[5], dif[6])/(Math.pow(Math.abs(dif[8]),1));
            dif[21]=Math.pow(Math.max(dif[5], dif[6]),1.07)/(Math.pow(Math.abs(dif[8]),1));
            dif[22]=Math.max(dif[5], dif[6])/(Math.pow(Math.abs(dif[8]),1.07));
            if(dif[5]>0&&dif[6]>0)
            {dif[11]=dif[5]+dif[6];dif[19]=dda[i1][24];}
            else
            {dif[11]=-1;dif[19]=dda[i1][24]*10000;}
            if(dda[i1][18]>0&&dda[i1][20]>0)
                dif[14]=dda[i1][18]+dda[i1][20];
            else
                dif[14]=-1;
            if(dif[5]>0||dif[6]>0)
            {
                dif[13]=Math.max(dif[5], dif[6])-Math.min(dif[5], dif[6]);
                //if(profgp[i1]==0)
                //profgp[i1]=0.001;
                dif[13]=Math.max(dif[5], dif[6])-dif[13];
            }
            else
                dif[13]=-1;
            if(dda[i1][18]>0||dda[i1][20]>0)
            {
                dif[15]=Math.max(dda[i1][18], dda[i1][20])-Math.min(dda[i1][18], dda[i1][20]);
                //if(profgp[i1]==0)
                //profgp[i1]=0.001;
                dif[15]=Math.max(dda[i1][18], dda[i1][20])-dif[15];
            }
            else
                dif[15]=-1;
            if(dda[i1][22]>0&&dda[i1][23]>0)
            {
                dif[18]=(dda[i1][22]+ dda[i1][23])/Math.min(Math.min(dif[2], dif[3]), dif[1]);
                //if(profgp[i1]==0)
                //profgp[i1]=0.001;
            }
            else
                dif[18]=-1/Math.min(Math.min(dif[2], dif[3]), dif[1]);
            dif[9]=dif[11]/(Math.abs(dif[8])*dda[i1][24]);
            dif[10]=Math.max(dif[5], dif[6])/Math.min(Math.min(dif[2], dif[3]), dif[1]);
            dif[16]=dif[13]/Math.min(Math.min(dif[2], dif[3]), dif[1]);
            dif[12]=dif[11]/Math.min(Math.min(dif[2], dif[3]), dif[1]);
            dif[14]=dif[14]/Math.min(Math.min(dif[2], dif[3]), dif[1]);
            dif[17]=Math.abs(Math.min(Math.min(dif[2], dif[3]), dif[1])*dif[8]);
            dif[23]=Math.max(dif[5], dif[6])/Math.abs(dif[17]);
            dif[24]=dif[11]/Math.abs(dif[17]);
            dif[25]=Math.max(Math.max(dda[i1][29]+dda[i1][19], dda[i1][30]+dda[i1][19]),dda[i1][19]*2)/(Math.pow(Math.abs(dif[8]),1));
            dif[26]=dda[i1][31];
            dif[27]=dda[i1][31]*dif[25];
            dif[28]=dda[i1][32];//(dda[i1][32]/(Math.pow(Math.abs(dif[8]),1)))*dif[26];
            if(dda[i1][17]>0&&dda[i1][18]>0&&dda[i1][29]>0&&dda[i1][19]>0&&dda[i1][30]>0&&dda[i1][20]>0&&dda[i1][21]>0)
            {dif[29]=(Math.pow(dda[i1][33],1))*(dda[i1][29]+dda[i1][19]+dda[i1][30]+dda[i1][17]+dda[i1][21]);///(Math.pow(Math.abs(dif[8]),1));
            }
            else
            {dif[29]=-1;}
            if(dda[i1][18]>0&&dda[i1][29]>0&&dda[i1][19]>0&&dda[i1][30]>0&&dda[i1][20]>0)
            {dif[30]=(Math.pow(dda[i1][33],1))*(dda[i1][29]+dda[i1][19]+dda[i1][30]+dda[i1][18]+dda[i1][20]);///(Math.pow(Math.abs(dif[8]),1));
                m1=dda[i1][25]/Math.abs(dif[8]);}
            else
            {dif[30]=-1;m1=-1;}
            dif[31]=m1;
            if(dda[i1][29]>0&&dda[i1][19]>0&&dda[i1][30]>0)
            {dif[32]=(Math.pow(dda[i1][33],1))*(dda[i1][29]+dda[i1][19]+dda[i1][30]);///(Math.pow(Math.abs(dif[8]),1));
            }
            else
            {dif[32]=-1;}
            if(dda[i1][29]>0&&dda[i1][19]>0&&dda[i1][30]>0&&dda[i1][36]>max_loss1)
            {dif[34]=(Math.pow(dda[i1][33],1))*(dda[i1][29]+dda[i1][19]+dda[i1][30]);///(Math.pow(Math.abs(dif[8]),1));
            }
            else
            {dif[34]=-1;}
            dif[33]=dif[32]*dda[i1][41];
            dif[35]=dda[i1][44];
            dif[36]=dda[i1][47];
            dif[37]=dda[i1][48];
            dif[38]=dda[i1][48]/Math.abs(dda[i1][16]);
            dif[39]=dif[28]/Math.pow(Math.abs(dda[i1][16]),0.4);
            dif[40]=dif[28]*dda[i1][48]/Math.abs(dda[i1][16]);
            dif[41]=dda[i1][48]/Math.abs(dda[i1][47]);
            dif[42]=dif[28]/Math.pow(Math.abs(dda[i1][47]),1);
            dif[43]=dif[28]*dda[i1][48]/Math.abs(dda[i1][47]);
            if(dda[i1][54]>0)
            {
                dif[44]=dda[i1][54]/Math.abs(dda[i1][16]);
                dif[46]=dif[28]*dda[i1][54]/Math.pow(Math.abs(dda[i1][16]),0.4);
                dif[50]=Math.min(dda[i1][56],dda[i1][57])*dda[i1][54];
            }
            else
            {
                dif[44]=-100000;
                dif[46]=-100000;
                dif[50]=Math.min(dda[i1][56],dda[i1][57]);
            }
            dif[45]=dda[i1][48]*dda[i1][54]/Math.abs(dda[i1][16]);
            dif[47]=dda[i1][54]/Math.abs(dda[i1][16]);
            dif[48]=dda[i1][54];
            dif[49]=dda[i1][55];
            dif[50]=Math.min(dda[i1][56],dda[i1][57]);//*dda[i1][54];
            //int profprint1[]={42,58,17,18,29,52,19,53,30,20,21,59,43};
            dif[51]=dda[i1][19]/Math.abs(dda[i1][16]);
            dif[52]=Math.max(dda[i1][52],dda[i1][53])/Math.abs(dda[i1][16]);
            dif[53]=Math.max(dda[i1][29],dda[i1][30])/Math.abs(dda[i1][16]);
            dif[54]=Math.max(dda[i1][18],dda[i1][20])/Math.abs(dda[i1][16]);
            dif[55]=Math.max(dda[i1][17],dda[i1][21])/Math.abs(dda[i1][16]);
            dif[56]=Math.max(dda[i1][58],dda[i1][59])/Math.abs(dda[i1][16]);
            dif[57]=dda[i1][25]/Math.abs(dda[i1][16]);

            dif1[i4]=dif[i5];
            i4++;
        }
        //System.out.println("tot_amt in dif is "+tot_amt);
        //return dif;

    }
    Integer[] indices;
    //String st_prt_two_ar[][][];
    //ArrayConverter ob37=new ArrayConverter();
    int spta_l=0;
    //String pnt_nm1[];

    public   void st_prt_two(double dda[][], String dd_dt[],String dd_ss[],String xx_ss[],String yy_ss[],String yy1_ss[],int dda_pnt, int dt_pnt,int i5,int pnt1,int st_i,int lenn, double spp)
    {
        int j=0;

        System.out.println("I am in prnt 2");
        //pnt_nm1=pnt_nm;

        double fb_dif=0;int cnt=0;
        //a,b >
        int l_dif=lenn-st_i;int i8=0;int st_cnt=0,st_cnt1=0;
        indices = new Integer[l_dif];
        String[] stringArray = new String[num_to_prt];
        String[] x_stringArray = new String[num_to_prt];
        String[] y_stringArray = new String[num_to_prt];
        String[] y1_stringArray = new String[num_to_prt];

        // Convert double[][] to String[]

        // Output the String[] content
        //for (String row : stringArray) {
        //  System.out.println(row);
        //}
        //System.out.println("\nyo\n");
        // Convert String[] back to double[][]
        //double[][] convertedArray = convertToDoubleArray(stringArray);

        //st_prt_two_ar=new String[spta_l++][l_dif][colt];

        ////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////dif[13][b], dif[13][a]
        // Create an array to store the indices
        //b,a <
        //for(int i5=0;i5<pnt_len;i5++)
        //{
        i8=0;
        for (int i1 = st_i; i1 < lenn; i1++) {
            indices[i8] = i8;i8++;
        }
        int pnt_i=i5;//pnt[i5][0];
        // Sort the indices array based on the values of the original array (in descending order)
        if(pnt1==1)//if(pnt[i5][1]==1)
            Arrays.sort(indices, (a, b) -> Double.compare(dif1[b], dif1[a]));
        else
            Arrays.sort(indices, (a, b) -> Double.compare(dif1[a], dif1[b]));

        //System.out.println("\nNEW DDA in terms of i5 "+i5+" pnt_len "+pnt_len+" pnt_i "+pnt_i+" pnt[i5][1] "+pnt[i5][1]);
        System.out.println("\nNEW DDA in terms of "+pnt_nm[pnt_i]);
        System.out.printf("%.2f , ", prof_ind1);
        System.out.printf("%.2f",prof_ind2);
        System.out.println();
        fb_dif=0.0;cnt=0;
        int profprint[]={42,17,29,19,30,21,43};int cntprof=0;//29,52,30,53
        int profprint1[]={42,17,18,29,52,19,53,30,20,21,43};int cntprof1=0;//29,52,53,30
        for (int index : indices)
        {

            if(dda_pnt==1)
            {
                //System.out.printf(index+" : ");//System.out.printf((index+st_i)+" : ");
                System.out.printf("%f : ",dda[index+st_i][51]);
                //System.out.printf(" | ");
                System.out.printf("%s\t",dd_ss[index+st_i]);
                //System.out.printf("%.0f ,%.0f ,%.0f ,%.0f ,%.0f ,%.0f , ",dda[index+st_i][12],dda[index+st_i][13],dda[index+st_i][37],dda[index+st_i][38],dda[index+st_i][39],dda[index+st_i][40]);
                //System.out.printf(" | ");

                /*for(j=14;j<16;j++)
                {
                System.out.printf("%f , ",dda[index+st_i][j]);
                }*/
                //System.out.printf(" | ");
                fb_dif=spp-dda[index+st_i][14];
                double lb_dif=dda[index+st_i][15]-spp;
                double diff=dda[index+st_i][15]-dda[index+st_i][14];
                //System.out.printf("(%.2f)",fb_dif);
                //System.out.printf("(%.2f)",lb_dif);
                //System.out.printf("(%.2f) , ",diff);
                System.out.printf("%.2f | ",dda[index+st_i][54]);
                System.out.printf("%.2f ,",dda[index+st_i][16]);
                System.out.printf(" %.2f ,",dda[index+st_i][48]);
                if(Math.abs(dda[index+st_i][16])>dda[index+st_i][48])
                    System.out.printf("   1:%.1f ",Math.abs(dda[index+st_i][16])/dda[index+st_i][48]);
                else
                    System.out.printf(" %.1f:1   ",dda[index+st_i][48]/Math.abs(dda[index+st_i][16]));
                //System.out.printf(" %.2f ",dda[index+st_i][25]);
                System.out.printf(" [ ");
                for(cntprof=0;cntprof<profprint.length;cntprof++)
                    System.out.printf("|\t%.2f\t|",dda[index+st_i][profprint[cntprof]]);
                System.out.printf(" ] ");

                System.out.printf("| %.2f |",dda[index+st_i][28] );
                //System.out.printf(" %.2f ",dda[index+st_i][36] );
                //System.out.printf(" %.2f , %.2f ,",dda[index+st_i][24], dda[index+st_i][35]);
                //System.out.printf("| %.2f |",dda[index+st_i][34] );
                //System.out.printf(" %.2f ",dda[index+st_i][41] );
                System.out.printf(" %s ",dd_ss[index+st_i] );
                System.out.printf(" %.2f ",dda[index+st_i][46] );
                System.out.printf("| %.2f, %.2f | %d",dda[index+st_i][49],dda[index+st_i][50], index);
                System.out.printf(" [ ");
                for(cntprof1=0;cntprof1<profprint1.length;cntprof1++)
                    System.out.printf("|\t%.2f\t|",dda[index+st_i][profprint1[cntprof1]]);
                System.out.printf(" ] ");
                for(j=0;j<12;j++)
                {
                    System.out.printf("%.2f",dda[index+st_i][j]);
                    if((j+1)%3==0)
                        System.out.printf(" | ");
                    else
                        System.out.printf(" , ");

                }
                //System.out.printf(" %.2f ",dda[index+st_i][22]);
                //System.out.printf("<%.2f , %.2f>",prof_ind2,dda[index][10]);
            }
            System.out.println();

            if(dt_pnt==1)
            {
                for(int i=0;i<dd_dt[index+st_i].length();i++)
                {
                    if(dd_dt[index+st_i].charAt(i)=='+')
                        System.out.print("\n{");
                    else
                        System.out.print(dd_dt[index+st_i].charAt(i));
                }
            }

            //for(st_cnt=0;st_cnt<colt;st_cnt++)
            //st_prt_two_ar[spta_l][st_cnt1++][st_cnt]=dda[index+st_i][st_cnt];

            stringArray[st_cnt1] = convertToStringArra(dda,index+st_i,colt);
            x_stringArray[st_cnt1] = xx_ss[index+st_i];
            y_stringArray[st_cnt1] = yy_ss[index+st_i];
            y1_stringArray[st_cnt1] = yy1_ss[index+st_i];
            st_cnt1++;

            cnt++;
            if(cnt==num_to_prt)
                break;
        }

        //}
        indices=null;
        /////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////
        //String[] stringArray = convertToStringArray(dda,num_to_prt,colt);
        st_ar[spta_l]=stringArray;
        x_ar[spta_l]=x_stringArray;
        y_ar[spta_l]=y_stringArray;
        y1_ar[spta_l]=y1_stringArray;

        //st_ar_ldif[spta_l]=l_dif;

        spta_l++;
        st_cnt1=0;
        //System.out.println("Spta_l "+spta_l);
        //System.out.println("tot_amt in pnt 2 is "+tot_amt);
    }

    public   void t_prt_two(double dda[], String dd_ss,String xx_ss,String yy_ss)
    {
        int j=0;

        System.out.println();
        int profprint[]={42,17,29,19,30,21,43};int cntprof=0;//29,52,30,53
        int profprint1[]={42,17,18,29,52,19,53,30,20,21,43};int cntprof1=0;//29,52,53,30

        System.out.printf("%f : ",dda[51]);
        System.out.printf("%s\t",dd_ss);
        System.out.printf("%.2f | ",dda[54]);
        System.out.printf("%.2f ,",dda[16]);
        System.out.printf(" %.2f ,",dda[48]);
        if(Math.abs(dda[16])>dda[48])
            System.out.printf("   1:%.1f ",Math.abs(dda[16])/dda[48]);
        else
            System.out.printf(" %.1f:1   ",dda[48]/Math.abs(dda[16]));

        System.out.printf(" [ ");
        for(cntprof=0;cntprof<profprint.length;cntprof++)
            System.out.printf("|\t%.2f\t|",dda[profprint[cntprof]]);
        System.out.printf(" ] ");

        System.out.printf("| %.2f |",dda[28] );

        System.out.printf(" %s ",dd_ss );
        System.out.printf(" %.2f ",dda[46] );
        System.out.printf("| %.2f, %.2f ",dda[49],dda[50]);
        System.out.printf(" [ ");
        for(cntprof1=0;cntprof1<profprint1.length;cntprof1++)
            System.out.printf("|\t%.2f\t|",dda[profprint1[cntprof1]]);
        System.out.printf(" ] ");
        for(j=0;j<12;j++)
        {
            System.out.printf("%.2f",dda[j]);
            if((j+1)%3==0)
                System.out.printf(" | ");
            else
                System.out.printf(" , ");

        }

        System.out.println();

    }

    public static void main(String args[])
    {
        /*
        DefaultValuesExample obdv1 = new DefaultValuesExample();
        obdv1.mai();
         */
        ///*
        OptionProfitAnalysis ob20=new OptionProfitAnalysis();
        int mm[]={20,21,22};
        ob20.new_ratt(29000,4,200,mm,3,5,1,-1,1,0,10,5,10);
        //*/
    }

    double[][] mergedArray = new double[5000000][colt];
    public double[][] mergeArrayss(double[][] array1, double[][] array2, int l1, int l2,int coln) {
        int rows1 = l1;//array1.length;
        int rows2 = l2;//array2.length;
        int cols = coln;//2;//array1[0].length;

        for (int i = 0; i < rows1; i++) {
            System.arraycopy(array1[i], 0, mergedArray[i], 0, cols);
        }

        for (int i = 0; i < rows2; i++) {
            System.arraycopy(array2[i], 0, mergedArray[rows1 + i], 0, cols);
        }

        return mergedArray;
    }

    String[] mergedArray2;// = new String[5000000];
    public String[] s_mergeArrayss(String[] array1, String[] array2, int l1, int l2) {
        int rows1 = l1;//array1.length;
        int rows2 = l2;//array2.length;

        mergedArray2 = new String[5000000];

        // Copy elements from the first array to the merged array
        System.arraycopy(array1, 0, mergedArray2, 0, rows1);

        // Copy elements from the second array to the merged array
        System.arraycopy(array2, 0, mergedArray2, rows1, rows2);

        return mergedArray2;
    }

    public double[][] convertToDoubleArray(String[] inputArray) {
        String[] values1 = inputArray[0].split(" ");
        int ll1=inputArray.length;
        int ll2=values1.length;
        doubleArray = new double[ll1][ll2];
        for (int i = 0; i < ll1; i++) {
            String[] values = inputArray[i].split(" ");
            doubleArray[i] = new double[values.length];
            ll2=values.length;
            for (int j = 0; j < ll2; j++) {
                doubleArray[i][j] = Double.parseDouble(values[j]);
            }
        }
        return doubleArray;
    }

    // Convert double[][] to String[]
    String[] stringArray = new String[5000000];
    public String[] convertToStringArray(double[][] inputArray, int ll1, int ll2) {
        //System.out.println("\n check status 21 ");
        for (int i = 0; i < ll1; i++) {
            StringBuilder rowStringBuilder = new StringBuilder();
            for (int j = 0; j < ll2; j++) {
                if (j > 0) {
                    rowStringBuilder.append(" "); // Separate values with space
                }
                rowStringBuilder.append(inputArray[i][j]);
            }
            stringArray[i] = rowStringBuilder.toString();
        }
        //System.out.println("\n check status 22 ");
        return stringArray;
    }

    String stringArra = "";
    public String convertToStringArra(double[][] inputArray, int indexx, int ll2) {
        //System.out.println("\n check status 23 ");
        StringBuilder rowStringBuilder = new StringBuilder();
        for (int j = 0; j < ll2; j++) {
            if (j > 0) {
                rowStringBuilder.append(" "); // Separate values with space
            }
            rowStringBuilder.append(inputArray[indexx][j]);
        }
        stringArra = rowStringBuilder.toString();
        //System.out.println("\n check status 24 ");
        return stringArra;
    }

    String stringArr = "";
    public String convertToStringArr(double[] inputArray, int ll2) {
        //System.out.println("\n check status 25 ");
        StringBuilder rowStringBuilder = new StringBuilder();
        for (int j = 0; j < ll2; j++) {
            if (j > 0) {
                rowStringBuilder.append(" "); // Separate values with space
            }
            rowStringBuilder.append(inputArray[j]);
        }
        stringArr = rowStringBuilder.toString();
        //System.out.println("\n check status 26 ");
        //System.out.println("str arr: |"+stringArr+"|");
        return stringArr;
    }

    // Convert String[] back to double[][]
    double[][] doubleArray = new double[5000000][];
    public double[][] convertToDoubleArray(String[] inputArray, int ll1, int ll2) {

        for (int i = 0; i < ll1; i++) {
            String[] values = inputArray[i].split(" ");
            doubleArray[i] = new double[values.length];
            for (int j = 0; j < ll2; j++) {
                doubleArray[i][j] = Double.parseDouble(values[j]);
            }
        }
        return doubleArray;
    }

    // Convert String[] back to double[][]
    double[] doubleArra;// = new double[5000000];
    public double[] convertToDoubleArray(String inputArray) {
        String[] values = inputArray.split(" ");
        doubleArra = new double[values.length];
        for (int j = 0; j < values.length; j++) {
            doubleArra[j] = Double.parseDouble(values[j]);
        }

        return doubleArra;
    }

}
/*
690
28500
35
260
29000
20
30000
1005
15
30250
1345
10
30500
1440
10
30750
1680
5
31000
1955
5
31500
2480
 */

