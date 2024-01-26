import java.util.*;

public class OptionSorter {
    public void main(String[] args) {
        // Sample data for call and put options with breakeven point and maximum loss
        List<Option> options = new ArrayList<>();
        options.add(new Option("Call1", 100, 105, 10)); // Sample data (name, breakeven, current spot price, maximum loss)
        options.add(new Option("Put1", 95, 105, 12));
        options.add(new Option("Call2", 102, 105, 15));
        options.add(new Option("Put2", 96, 105, 11));

        // Calculate risk-reward ratio for each option and store it in a map
        Map<Option, Double> riskRewardMap = new HashMap<>();
        for (Option option : options) {
            double riskRewardRatio = (option.getBreakeven() - option.getCurrentSpotPrice()) / option.getMaximumLoss();
            riskRewardMap.put(option, riskRewardRatio);
        }

        // Sort options based on their risk-reward ratios in descending order
        List<Option> sortedOptions = new ArrayList<>(options);
        sortedOptions.sort((o1, o2) -> Double.compare(riskRewardMap.get(o2), riskRewardMap.get(o1)));

        // Print the sorted options
        for (Option option : sortedOptions) {
            System.out.println(option);
        }
    }
}

class Option {
    private String name;
    private double breakeven;
    private double currentSpotPrice;
    private double maximumLoss;

    public Option(String name, double breakeven, double currentSpotPrice, double maximumLoss) {
        this.name = name;
        this.breakeven = breakeven;
        this.currentSpotPrice = currentSpotPrice;
        this.maximumLoss = maximumLoss;
    }

    public String getName() {
        return name;
    }

    public double getBreakeven() {
        return breakeven;
    }

    public double getCurrentSpotPrice() {
        return currentSpotPrice;
    }

    public double getMaximumLoss() {
        return maximumLoss;
    }

    @Override
    public String toString() {
        return name + ": Breakeven=" + breakeven + ", Risk-Reward Ratio=" + ((breakeven - currentSpotPrice) / maximumLoss);
    }
}

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
    95.7
    1750
    0.5
    47
    1800
    1.5
    22
    1825
    3.9
    6.4
    1850
    14.2
    1.9
    1875
    36.7
    1
    1900
    62.1
    0.6
    1925
    85.5
    0.6
    1950
    112.5
    0.1
    1975
    138.5
    0.1
    2000
    164.2
     */
    /*
    113.9
    1750
    0.2
    53
    1800
    0.5
    30.1
    1825
    1
    5.5
    1850
    6.3
    0.7
    1875
    33.6
    0.5
    1900
    55.7
    0.3
    1925
    80
    0.1
    1950
    118.6
    0.1
    1975
    147.1
    0.1
    2000
    176
     */
    /*
    725
    28500
    5
    180
    29000
    30
    35
    29250
    140
    10
    29500
    445
    19
    29750
    725
    10
    30000
    995
    5
    30250
    1325
    5
    30500
    1370
    5
    31000
    2190
    5
    31500
    2765
     */
    /*
    117.8
    1750
    0.2
    55.6
    1800
    0.4
    32.2
    1825
    0.5
    5.1
    1850
    3.2
    0.4
    1875
    25.7
    0.4
    1900
    56.3
    0.4
    1925
    76.4
    0.1
    1950
    114
    0.1
    1975
    142.8
    0.1
    2000
    171.7
     */
    /*
     158.4
     1700
     0.3
     105.2
     1750
     0.8
     55.8
     1800
     2
     33.1
     1825
     4.3
     12.8
     1850
     11.2
     3.9
     1875
     27.9
     1.5
     1900
     60.2
     1
     1925
     77.2
     0.6
     1950
     111.2
     0.3
     2000
     154.8
     */
    /*
     158.4
     1700
     0.2
     105.4
     1750
     0.7
     54.8
     1800
     2.0
     31.1
     1825
     4.2
     12.1
     1850
     11.6
     3.9
     1875
     28.4
     1.5
     1900
     60.2
     0.9
     1925
     78
     0.5
     1950
     111.2
     0.1
     2000
     154.8
     */