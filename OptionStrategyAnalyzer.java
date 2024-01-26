import java.util.*;
import java.lang.reflect.Field;

public class OptionStrategyAnalyzer {
    array_length_short ob30=new array_length_short();

    static class StrategyMetrics {
        String name;
        double totalProfit;
        int positiveProfitCount;
        double maxProfit;
        double minProfit;
        double earlyProfit;
        double middleProfit;
        double lateProfit;
        int maxContinuousPositive;
        double volatility;
        double riskToRewardRatio; // New metric
        double shortTermVsLongTermProfit; // New metric
        // ... other metrics ...
    }

    double arr_d[][];
    public double[][] DoubleToDoubleArr(double arr[], int l)
    {
        arr_d=new double[l][arr.length];
        for(int i1=0;i1<l;i1++)
        {
            for(int j1=0;j1<arr.length;j1++)
            {
                arr_d[i1][j1]=arr[j1];
            }
        }
        //ob30.pnt(arr_d);
        return arr_d;
    }

    public static void main(String[] args) {
        // Sample data for eight strategies
        double[][] profitsData = {
                { -100, 50, 120, 200, 250, -50, 30 }, // Call Buy
                { 100, -50, -120, -200, -250, 50, -30 }, // Call Sell
                { -50, 20, 30, 10, -20, 15, -10 }, // Put Spread
                { 30, -15, 10, 5, -10, 20, -5 }, // Iron Condor
                { -70, 40, 60, -30, 100, -20, 50 }, // Straddle
                { 10, -5, 15, -10, 5, -15, 10 }, // Butterfly Spread
                { -120, 60, -80, 100, -50, 30, -40 }, // Naked Call
                { 80, -40, 70, -35, 60, -25, 50 } // Naked Put
            };

        // Sample time intervals (days) for each strategy
        /*double[][] timeIntervalsData = {
        { 1, 2, 3, 4, 5, 6, 7 },
        { 1, 2, 3, 4, 5, 6, 7 },
        { 1, 2, 3, 4, 5, 6, 7 },
        { 1, 2, 3, 4, 5, 6, 7 },
        { 1, 2, 3, 4, 5, 6, 7 },
        { 1, 2, 3, 4, 5, 6, 7 },
        { 1, 2, 3, 4, 5, 6, 7 },
        { 1, 2, 3, 4, 5, 6, 7 }
        };
         */
        double[] timeIntervalsData ={ 1, 2, 3, 4, 5, 6, 7 };

        OptionStrategyAnalyzer obb=new OptionStrategyAnalyzer();
        obb.maain(profitsData,timeIntervalsData);

    }

    String[] metricNames = {
            "totalProfit", "positiveProfitCount", "maxProfit", "minProfit",
            "earlyProfit", "middleProfit", "lateProfit",
            "maxContinuousPositive", "volatility", "riskToRewardRatio",
            "shortTermVsLongTermProfit"
        };

    int l1=0;
    public void maain(double profitsData[][],double timeIntervals[])
    {

        double timeIntervalsData[][]= DoubleToDoubleArr(timeIntervals,profitsData.length);

        // Strategy names corresponding to the profitsData
        String[] strategyNames = { "Call Buy", "Call Sell", "Put Spread", "Iron Condor", "Straddle", "Butterfly Spread", "Naked Call", "Naked Put" };

        //if(strategyNames.length>profitsData.length)
        //ob30.shorti(strategyNames,profitsData.length);
        strategyNames=new String[profitsData.length];

        //if(strategyNames.length<profitsData.length)
        for(int i2=0;i2<profitsData.length;i2++)
            strategyNames[i2]=Integer.toString(i2);//"strategy "+Integer.toString(i2);

        List<StrategyMetrics> strategies = new ArrayList<>();
        for (int i = 0; i < profitsData.length; i++) {
            strategies.add(calculateMetrics(profitsData[i], timeIntervalsData[i], strategyNames[i]));
        }
        // Metrics names for sorting and displaying

        for (String metricName : metricNames)
            l1++;
        ar=new double[l1][10000];
        vr=new double[l1][10000];
        for (String metricName : metricNames) {
            displaySortedStrategies(strategies, metricName);
        }
        ob30.pnt(ar);
        ob30.pnt(vr);
        
        Statistics ob=new Statistics();
        ob.maiin(vr);
    }

    double ar[][]=new double[10][10000];
    double vr[][]=new double[10][10000];
    int cnt1=0,cnt2=0;
    private void displaySortedStrategies(List<StrategyMetrics> strategies, String metricName) {
        System.out.println("Sorting by " + metricName + ":");

        int i2=0;
        for (StrategyMetrics strategy : strategies)
            i2++;
        cnt2=0;
        ar=ob30.shorti(ar,l1,i2);
        vr=ob30.shorti(vr,l1,i2);

        strategies.sort((s1, s2) -> compareByMetric(s1, s2, metricName));
        for (StrategyMetrics strategy : strategies) {
            System.out.println("Strategy: " + strategy.name + ", " + metricName + ": " + getMetricValue(strategy, metricName));
            ar[cnt1][cnt2]=Integer.valueOf(strategy.name);
            vr[cnt1][cnt2]=Double.valueOf(getMetricValue(strategy, metricName));
            cnt2++;
        }
        cnt1++;
        System.out.println();
        //ob30.pnt(ar);
    }

    private static int compareByMetric(StrategyMetrics s1, StrategyMetrics s2, String metricName) {
        try {
            Field field = StrategyMetrics.class.getDeclaredField(metricName);
            field.setAccessible(true);
            Comparable val1 = (Comparable) field.get(s1);
            Comparable val2 = (Comparable) field.get(s2);
            return val2.compareTo(val1); // Reversed comparison for ascending order
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Error accessing metric: " + metricName, e);
        }
    }

    private static String getMetricValue(StrategyMetrics strategy, String metricName) {
        try {
            Field field = StrategyMetrics.class.getDeclaredField(metricName);
            field.setAccessible(true);
            return String.valueOf(field.get(strategy));
        } catch (ReflectiveOperationException e) {
            return "N/A";
        }
    }

    // ... (calculateMetrics method) ...

    private static StrategyMetrics calculateMetrics(double[] profits, double[] timeIntervals, String name) {
        StrategyMetrics metrics = new StrategyMetrics();
        double sum = 0;
        int positiveCount = 0;
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        double earlyProfit = 0, middleProfit = 0, lateProfit = 0;
        int continuousPositiveCount = 0, maxContinuousPositive = 0;
        double volatility = 0;
        double previousProfit = profits[0];

        for (int i = 0; i < profits.length; i++) {
            double profit = profits[i];
            sum += profit;

            // Early, middle, late profit calculation
            if (i < profits.length / 3) {
                earlyProfit += profit;
            } else if (i < 2 * profits.length / 3) {
                middleProfit += profit;
            } else {
                lateProfit += profit;
            }

            // Profit retention (continuous positive intervals)
            if (profit > 0) {
                continuousPositiveCount++;
            } else {
                continuousPositiveCount = 0;
            }
            maxContinuousPositive = Math.max(maxContinuousPositive, continuousPositiveCount);

            // Volatility calculation
            volatility += Math.abs(profit - previousProfit);
            previousProfit = profit;

            if (profit > max) max = profit;
            if (profit < min) min = profit;
            if (profit > 0) positiveCount++;
        }

        metrics.totalProfit = sum;
        metrics.positiveProfitCount = positiveCount;
        metrics.maxProfit = max;
        metrics.minProfit = min;
        metrics.name = name;
        metrics.earlyProfit = earlyProfit;
        metrics.middleProfit = middleProfit;
        metrics.lateProfit = lateProfit;
        metrics.maxContinuousPositive = maxContinuousPositive;
        metrics.volatility = volatility / (profits.length - 1); // Average volatility
        // Calculate risk to reward ratio and short-term vs long-term profit
        metrics.riskToRewardRatio = calculateRiskToRewardRatio(profits);
        metrics.shortTermVsLongTermProfit = calculateShortTermVsLongTermProfit(profits, timeIntervals);

        return metrics;
    }

    // Method to calculate risk to reward ratio
    private static double calculateRiskToRewardRatio(double[] profits) {
        double totalLoss = 0;
        double totalGain = 0;
        for (double profit : profits) {
            if (profit > 0) totalGain += profit;
            else totalLoss += Math.abs(profit);
        }
        return totalLoss != 0 ? totalGain / totalLoss : 0;
    }

    // Method to compare short-term and long-term profits
    private static double calculateShortTermVsLongTermProfit(double[] profits, double[] timeIntervals) {
        // Assuming short-term is the first half and long-term is the second half
        int midPoint = profits.length / 2;
        double shortTermProfit = sumArray(profits, 0, midPoint);
        double longTermProfit = sumArray(profits, midPoint, profits.length);
        return shortTermProfit - longTermProfit; // Positive value indicates higher short-term profit
    }

    private static double sumArray(double[] array, int start, int end) {
        double sum = 0;
        for (int i = start; i < end; i++) sum += array[i];
        return sum;
    }

}

