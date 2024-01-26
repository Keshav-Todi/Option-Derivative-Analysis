import javax.swing.*;

public class DataProcessor {
    public static void main(String[] args) {
        // Example data for x and y values, including negative values
        double[] xValues = {-3, -2, -1, 0, 1, 2, 3};
        double[] yValues = {-4, -3, -2, -1, 0, 1, 4};

        // Create an instance of ScatterPlotExample and pass xValues and yValues
        ScatterPlotExample scatterPlot = new ScatterPlotExample("Scatter Plot Example", xValues, yValues);
        scatterPlot.setSize(800, 600);
        scatterPlot.setLocationRelativeTo(null);
        scatterPlot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scatterPlot.setVisible(true);
    }
}
