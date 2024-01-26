public class OptionPriceCalculator {

    // Function to generate prices array
    public static double[] generatePriceRange(double currentPrice, double percentageRange) {
        int steps = 20; // 20 steps for -5% to +5% (inclusive of both ends)
        double[] prices = new double[steps + 1]; // +1 to include current price
        double stepSize = percentageRange * 2 * currentPrice / steps;

        for (int i = 0; i <= steps; i++) {
            prices[i] = currentPrice - percentageRange * currentPrice + (stepSize * i);
        }
        return prices;
    }

    // Placeholder function for option price calculation
    public static double calculateOptionPrice(double stockPrice, double strikePrice) {
        // Placeholder algorithm for option pricing
        return Math.abs(stockPrice - strikePrice); // Example calculation
    }

    public static void main(String[] args) {
        double currentPrice = 100.0; // Current price of the stock
        double strikePrice = 105.0; // Example strike price
        double[] prices = generatePriceRange(currentPrice, 0.05); // +/- 5% range

        for (double price : prices) {
            double optionPrice = calculateOptionPrice(price, strikePrice);
            System.out.printf("Stock Price: %.2f, Option Price: %.2f%n", price, optionPrice);
        }
    }
}
