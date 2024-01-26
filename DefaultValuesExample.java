import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYLineAnnotation;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDifferenceRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class DefaultValuesExample {
    String value1, value2, value3, value4, value5, value6, value7, value8;
    String value11, value12, value13, value14, value15, value16, value17, value18;
    String value31, value32, value33, value34, value35, value36, value37, value38;
    String value21,value22;
    String def_value1="36000", def_value2="4", def_value3="200"
    , def_value4="2,3,4", def_value5="4", def_value6="5", def_value7="1", def_value8="-1";
    String def_value11="36000", def_value12="4", def_value13="200"
    , def_value14="2", def_value15="2", def_value16="4", def_value17="4", def_value18="";
    String def_value31="1", def_value32="2", def_value33="5"
    , def_value34="1", def_value35="1", def_value36="1", def_value37="1", def_value38="";
    String def_value21="20", def_value22="10", def_value23="540";
    //"29000","4","200","2,3,4","4,"5,"1"
    JComboBox<String> comboBox, comboBox1, comboBox2; // Declare JComboBox as a class field

    public static void main(String[] args) {
        DefaultValuesExample obdv = new DefaultValuesExample();
        obdv.mai();
    }

    public void mai() {
        // Create the main frame
        JFrame frame = new JFrame("Default Values Example");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold the components
        JPanel panel = new JPanel(new GridBagLayout());
        frame.add(panel);
        placeComponents(panel);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Set the frame visibility
        frame.setVisible(true);

    }
    int impl=0,impl1=0;
    String x_ar1[][];
    String y_ar1[][];
    String y1_ar1[][];
    String st_ar1[][];
    String np_x_s[]=new String[500];
    String np_y_s[]=new String[500];
    String np_y1_s[]=new String[500];
    double[][] Comb_Y=new double[1][1];
    double[][] Comb_Y1=new double[1][1];
    double[] Combb_Y=new double[1];
    double[] Combb_Y1=new double[1];
    String last_value11="";
    int p_cnt=0,max_cnt=0, p_cnt1=0,max_cnt1=0;
    int previous_index=-1,previous_index1=-1, last_s=-1,last_m=-1, index_s=0, index_m=0;
    int v4[];double value88=-1;
    int intervall=0, tot_intervall=10;
    int voltt=5, tot_voltt=10;
    int nummofdays=0,nummofsim=0,oldsim=0;
    String fnn="marketdatafl",fnn1="marketdatafl1";
    double mk_dyn[][]=new double[10][10];
    String mk_data[][]=new String[10][10];
    String mk_data_x[]=new String[10];

    TxtFileOp ob61=new TxtFileOp();
    ScatterPlotExample example2 = new ScatterPlotExample();
    private void placeComponents(JPanel panel) {
        OptionProfitAnalysis ob22=new OptionProfitAnalysis();

        StringToIntegerArray obb=new StringToIntegerArray();
        //obb.mainn("2,36,44,1");
        array_length_short ob30=new array_length_short();

        double[] xValues = {-3, -2, -1, 0, 1, 2, 3};
        double[] yValues = {-4, -3, -2, -1, 0, 1, 4};
        double[] y1Values = {3, 2, 1, 0, -1, -2, -3};
        double[] y2Values ={-5, -6, -7, 5, 6, 7, 8}; // Initial y1Values
        double[] y3Values = {-11, -5, -2, 9, 3, -4, 6}; // Initial y2Values
        double[] y4Values = {-14, -13, -12, 15, 12, 13, 14};
        double[] y5Values = {-4, 3, -1, 4, 9, 9, 10};
        double[] y6Values = {14, -13, 12, 15, -12, 13, -14};
        double[] y7Values = {-4, -3, 1, -4, 9, -9, 10};

        FileReadWriteExample obrw=new FileReadWriteExample();
        String filePath = "OptionDefaultdata.txt";
        String[] def_data = obrw.readFile(filePath);
        def_value1=def_data[0];def_value2=def_data[1];def_value3=def_data[2];
        def_value4=def_data[3];def_value5=def_data[4];def_value6=def_data[5];
        def_value7=def_data[6];def_value8=def_data[7];

        def_value11=def_value1;
        def_value12=def_value6;
        def_value13=def_value7;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Padding

        // Create text fields with default values
        JTextField textField1 = new JTextField(def_value1, 15);
        JTextField textField2 = new JTextField(def_value2, 15);
        JTextField textField3 = new JTextField(def_value3, 15);
        JTextField textField4 = new JTextField(def_value4, 15);
        JTextField textField5 = new JTextField(def_value5, 15);
        JTextField textField6 = new JTextField(def_value6, 15);
        JTextField textField7 = new JTextField(def_value7, 15);
        JTextField textField8 = new JTextField(def_value8, 15);

        JTextField textField11 = new JTextField(def_value11, 15);
        JTextField textField12 = new JTextField(def_value12, 15);
        JTextField textField13 = new JTextField(def_value13, 15);
        JTextField textField14 = new JTextField(def_value14, 15);
        JTextField textField15 = new JTextField(def_value15, 15);
        JTextField textField16 = new JTextField(def_value16, 15);
        JTextField textField17 = new JTextField(def_value17, 15);
        JTextField textField18 = new JTextField(def_value18, 15);

        JTextField textField21 = new JTextField(def_value21, 15);
        JTextField textField22 = new JTextField(def_value22, 15);
        JTextField textField23 = new JTextField(def_value23, 15);

        JTextField textField31 = new JTextField(def_value31, 15);
        JTextField textField32 = new JTextField(def_value32, 15);
        JTextField textField33 = new JTextField(def_value33, 15);
        JTextField textField34 = new JTextField(def_value34, 15);
        JTextField textField35 = new JTextField(def_value35, 15);
        JTextField textField36 = new JTextField(def_value36, 15);
        JTextField textField37 = new JTextField(def_value37, 15);
        JTextField textField38 = new JTextField(def_value38, 15);

        def_value14=""; def_value15=""; def_value16=""; def_value17=""; def_value18="";

        JLabel resultLabel11 = new JLabel("Pric ");
        JLabel resultLabel12 = new JLabel("Rati ");
        JLabel resultLabel13 = new JLabel("Amtt ");
        JLabel resultLabel14 = new JLabel("Mdck ");
        JLabel resultLabel15 = new JLabel("Loss ");
        JLabel resultLabel16 = new JLabel("Op_l ");
        JLabel resultLabel17 = new JLabel("Op_g ");
        JLabel resultLabel18 = new JLabel("l_ck ");

        JLabel resultLabel21 = new JLabel("Pric  ");
        JLabel resultLabel22 = new JLabel("Rati  ");
        JLabel resultLabel23 = new JLabel("Amtt  ");
        JLabel resultLabel24 = new JLabel("C_Buy ");
        JLabel resultLabel25 = new JLabel("P_Buy ");
        JLabel resultLabel26 = new JLabel("C_Shr ");
        JLabel resultLabel27 = new JLabel("P_Shr ");
        JLabel resultLabel28 = new JLabel("l_chk ");

        JPanel comboBoxPanel11 = new JPanel();
        comboBoxPanel11.setLayout(new BoxLayout(comboBoxPanel11, BoxLayout.X_AXIS));
        JPanel comboBoxPanel12 = new JPanel();
        comboBoxPanel12.setLayout(new BoxLayout(comboBoxPanel12, BoxLayout.X_AXIS));
        JPanel comboBoxPanel13 = new JPanel();
        comboBoxPanel13.setLayout(new BoxLayout(comboBoxPanel13, BoxLayout.X_AXIS));
        JPanel comboBoxPanel14 = new JPanel();
        comboBoxPanel14.setLayout(new BoxLayout(comboBoxPanel14, BoxLayout.X_AXIS));
        JPanel comboBoxPanel15 = new JPanel();
        comboBoxPanel15.setLayout(new BoxLayout(comboBoxPanel15, BoxLayout.X_AXIS));
        JPanel comboBoxPanel16 = new JPanel();
        comboBoxPanel16.setLayout(new BoxLayout(comboBoxPanel16, BoxLayout.X_AXIS));
        JPanel comboBoxPanel17 = new JPanel();
        comboBoxPanel17.setLayout(new BoxLayout(comboBoxPanel17, BoxLayout.X_AXIS));
        JPanel comboBoxPanel18 = new JPanel();
        comboBoxPanel18.setLayout(new BoxLayout(comboBoxPanel18, BoxLayout.X_AXIS));
        JPanel comboBoxPanel19 = new JPanel();
        comboBoxPanel19.setLayout(new BoxLayout(comboBoxPanel19, BoxLayout.X_AXIS));

        JPanel comboBoxPanel20 = new JPanel();
        comboBoxPanel20.setLayout(new BoxLayout(comboBoxPanel20, BoxLayout.X_AXIS));

        comboBoxPanel11.add(resultLabel11);
        comboBoxPanel11.add(textField1);
        comboBoxPanel11.add(resultLabel21);
        comboBoxPanel11.add(textField11);
        comboBoxPanel11.add(textField31);

        comboBoxPanel12.add(resultLabel12);
        comboBoxPanel12.add(textField2);
        comboBoxPanel12.add(resultLabel22);
        comboBoxPanel12.add(textField12);
        comboBoxPanel12.add(textField32);

        comboBoxPanel13.add(resultLabel13);
        comboBoxPanel13.add(textField3);
        comboBoxPanel13.add(resultLabel23);
        comboBoxPanel13.add(textField13);
        comboBoxPanel13.add(textField33);

        comboBoxPanel14.add(resultLabel14);
        comboBoxPanel14.add(textField4);
        comboBoxPanel14.add(resultLabel24);
        comboBoxPanel14.add(textField14);
        comboBoxPanel14.add(textField34);

        comboBoxPanel15.add(resultLabel15);
        comboBoxPanel15.add(textField5);
        comboBoxPanel15.add(resultLabel25);
        comboBoxPanel15.add(textField15);
        comboBoxPanel15.add(textField35);

        comboBoxPanel16.add(resultLabel16);
        comboBoxPanel16.add(textField6);
        comboBoxPanel16.add(resultLabel26);
        comboBoxPanel16.add(textField16);
        comboBoxPanel16.add(textField36);

        comboBoxPanel17.add(resultLabel17);
        comboBoxPanel17.add(textField7);
        comboBoxPanel17.add(resultLabel27);
        comboBoxPanel17.add(textField17);
        comboBoxPanel17.add(textField37);

        comboBoxPanel18.add(resultLabel18);
        comboBoxPanel18.add(textField8);
        //comboBoxPanel28.add(resultLabel28);
        //comboBoxPanel18.add(textField18);

        comboBoxPanel18.add(textField21);
        comboBoxPanel18.add(textField22);
        comboBoxPanel18.add(textField23);

        gbc.gridx = 1; gbc.gridy = GridBagConstraints.RELATIVE; 
        panel.add(comboBoxPanel11, gbc); gbc.gridx = 0;

        gbc.gridx = 1; gbc.gridy = GridBagConstraints.RELATIVE;
        panel.add(comboBoxPanel12, gbc); gbc.gridx = 0;

        gbc.gridx = 1; gbc.gridy = GridBagConstraints.RELATIVE; 
        panel.add(comboBoxPanel13, gbc); gbc.gridx = 0;

        gbc.gridx = 1; gbc.gridy = GridBagConstraints.RELATIVE; 
        panel.add(comboBoxPanel14, gbc); gbc.gridx = 0;

        gbc.gridx = 1; gbc.gridy = GridBagConstraints.RELATIVE; 
        panel.add(comboBoxPanel15, gbc); gbc.gridx = 0;

        gbc.gridx = 1; gbc.gridy = GridBagConstraints.RELATIVE; 
        panel.add(comboBoxPanel16, gbc); gbc.gridx = 0;

        gbc.gridx = 1; gbc.gridy = GridBagConstraints.RELATIVE; 
        panel.add(comboBoxPanel17, gbc); gbc.gridx = 0;

        gbc.gridx = 1; gbc.gridy = GridBagConstraints.RELATIVE; 
        panel.add(comboBoxPanel18, gbc); gbc.gridx = 0;

        //gbc.gridx = 1; gbc.gridy = GridBagConstraints.RELATIVE; 
        //panel.add(comboBoxPanel19, gbc); gbc.gridx = 0;

        //gbc.gridy = 0;
        //panel.add(textField1, gbc);
        //panel.add(textField2, gbc);
        //panel.add(textField3, gbc);
        //panel.add(textField4, gbc);
        //panel.add(textField5, gbc);
        //panel.add(textField6, gbc);
        //panel.add(textField7, gbc);
        //panel.add(textField8, gbc);

        // Create a dropdown list
        String[] options = {"Call","Put","CallShort","Putshort","Spread", "Straddle", "Range forward", "Iron Condor", "1 Iron Condor","Long Short","Single","All", "Other"};
        String[] options_data = {"26","27","28","29","5,6,15", "0", "21,22,23", "7,8", "24,25"
            ,"1,2,9,10,11,12,13,14","15", "0,1,2,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23", "0"};
        //String[] options1 = {"Spread", "Straddle", "Range forward", "All", "Other"};
        int[][] pnts1 = ob22.pnts;
        int pnts_len1=ob22.pnts_len;
        String pnt_nm1[]=ob22.pnt_nm;

        String[] options1= new String[pnts_len1];
        for(int i1=0;i1<pnts_len1;i1++)
            options1[i1]=pnt_nm1[pnts1[i1][0]];

        OptionStrategyAnalyzer obb1=new OptionStrategyAnalyzer();
        //obb.maain(profitsData,timeIntervalsData);
        String[] dynamic_opt = obb1.metricNames;//{"Spread", "Straddle", "Range forward", "Iron Condor", "1 Iron Condor","Long Short","All", "Other"};
        String[] dynamic_opt1 = {"26","27","28","29","5,6,15", "0", "21,22,23", "7,8", "24,25"
            ,"1,2,9,10,11,12,13,14","15", "0,1,2,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23", "0"};
        //ob30.pnt(dynamic_opt);

        x_ar1=ob22.x_ar;
        y_ar1=ob22.y_ar;
        y1_ar1=ob22.y1_ar;
        st_ar1=ob22.st_ar;
        p_cnt=-1;
        max_cnt=ob22.num_to_prt;

        comboBox = new JComboBox<>(options);
        //panel.add(comboBox, gbc);

        comboBox2 = new JComboBox<>(dynamic_opt);
        //panel.add(comboBox2, gbc);

        comboBoxPanel20.add(comboBox);
        comboBoxPanel20.add(comboBox2);

        gbc.gridx = 1; gbc.gridy = GridBagConstraints.RELATIVE; 
        panel.add(comboBoxPanel20, gbc); gbc.gridx = 0;

        // Create a fourth text field initially hidden
        //textField4.setVisible(false);
        //panel.add(textField4, gbc);

        // Create a button
        JButton button = new JButton("Get Values");
        //panel.add(button, gbc);
        JButton button6 = new JButton("See details");
        
        JPanel comboBoxPanel6 = new JPanel();
        comboBoxPanel6.setLayout(new BoxLayout(comboBoxPanel6, BoxLayout.X_AXIS));

        
        comboBoxPanel6.add(button);
        comboBoxPanel6.add(button6);

        // Add comboBoxPanel to the main panel with GridBagConstraints
        gbc.gridx = 1; // Change the gridx value to place it beside comboBox
        gbc.gridy = GridBagConstraints.RELATIVE; // Set the next component below this one
        panel.add(comboBoxPanel6, gbc);

        JButton button1 = new JButton("Save Default");
        //panel.add(button1, gbc);

        JButton button4 = new JButton("Option Chain");
        //panel.add(button1, gbc);
        
        JButton button51 = new JButton("New Mk Sim");
        JButton button55 = new JButton("Mk Sim");
        JButton button56 = new JButton("Mk Chk");
        //panel.add(button1, gbc);
        JButton button66 = new JButton("New Price");
        JButton button40 = new JButton("Single Stra");

        JPanel comboBoxPanel2 = new JPanel();
        comboBoxPanel2.setLayout(new BoxLayout(comboBoxPanel2, BoxLayout.X_AXIS));

        // Add comboBox1 to the comboBoxPanel
        comboBoxPanel2.add(button1);

        // Add button2 to the comboBoxPanel
        comboBoxPanel2.add(button4);
        // Add button2 to the comboBoxPanel
        comboBoxPanel2.add(button51);
        comboBoxPanel2.add(button55);
        comboBoxPanel2.add(button56);
        comboBoxPanel2.add(button66);
        comboBoxPanel2.add(button40);

        // Add comboBoxPanel to the main panel with GridBagConstraints
        gbc.gridx = 1; // Change the gridx value to place it beside comboBox
        gbc.gridy = GridBagConstraints.RELATIVE; // Set the next component below this one
        panel.add(comboBoxPanel2, gbc);

        gbc.gridx = 0;

        // Create a label to display the result
        JLabel resultLabel = new JLabel("Result:");
        panel.add(resultLabel, gbc);

        ///*
        comboBox1 = new JComboBox<>(options1);
        //panel.add(comboBox1, gbc);

        JButton button2 = new JButton("See Data");
        //panel.add(button2, gbc);
        JButton button52 = new JButton("Time incr");
        //*/

        JPanel comboBoxPanel1 = new JPanel();
        comboBoxPanel1.setLayout(new BoxLayout(comboBoxPanel1, BoxLayout.X_AXIS));

        // Add comboBox1 to the comboBoxPanel
        comboBoxPanel1.add(comboBox1);

        // Add button2 to the comboBoxPanel
        comboBoxPanel1.add(button2);
        comboBoxPanel1.add(button52);

        // Add comboBoxPanel to the main panel with GridBagConstraints
        gbc.gridx = 1; // Change the gridx value to place it beside comboBox
        gbc.gridy = GridBagConstraints.RELATIVE; // Set the next component below this one
        panel.add(comboBoxPanel1, gbc);

        gbc.gridx = 0;
        // Create a new panel to hold the chart
        JPanel chartPanel = new JPanel(new BorderLayout());
        panel.add(chartPanel, gbc);

        // Create the chart (similar code as provided previously)
        //double[] xValues = {-3, -2, -1, 0, 1, 2, 3};
        //double[] yValues = {-4, -3, -2, -1, 0, 1, 4};

        XYSeries series = new XYSeries("Data");
        for (int i = 0; i < xValues.length && i < yValues.length; i++) {
            series.add(xValues[i], yValues[i]);
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);

        // Create a dataset for the second line (y2Values vs xValues)
        XYSeries series2 = new XYSeries("Data2");
        for (int i = 0; i < xValues.length && i < y1Values.length; i++) {
            series2.add(xValues[i], y1Values[i]);
        }
        XYSeriesCollection dataset2 = new XYSeriesCollection(series2);

        XYSeries series3 = new XYSeries("Data3");
        for (int i = 0; i < xValues.length && i < y2Values.length; i++) {
            series3.add(xValues[i], y2Values[i]);
        }
        XYSeriesCollection dataset3 = new XYSeriesCollection(series3);

        XYSeries series4 = new XYSeries("Data4");
        for (int i = 0; i < xValues.length && i < y3Values.length; i++) {
            series4.add(xValues[i], y3Values[i]);
        }
        XYSeriesCollection dataset4 = new XYSeriesCollection(series4);

        XYSeries series5 = new XYSeries("Data5");
        for (int i = 0; i < xValues.length && i < y4Values.length; i++) {
            series5.add(xValues[i], y4Values[i]);
        }
        XYSeriesCollection dataset5 = new XYSeriesCollection(series5);

        XYSeries series6 = new XYSeries("Data6");
        for (int i = 0; i < xValues.length && i < y5Values.length; i++) {
            series6.add(xValues[i], y5Values[i]);
        }
        XYSeriesCollection dataset6 = new XYSeriesCollection(series6);

        XYSeries series7 = new XYSeries("Data7");
        for (int i = 0; i < xValues.length && i < y6Values.length; i++) {
            series7.add(xValues[i], y6Values[i]);
        }
        XYSeriesCollection dataset7 = new XYSeriesCollection(series7);

        XYSeries series8 = new XYSeries("Data8");
        for (int i = 0; i < xValues.length && i < y7Values.length; i++) {
            series8.add(xValues[i], y7Values[i]);
        }
        XYSeriesCollection dataset8 = new XYSeriesCollection(series8);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Scatter Plot",  // Chart title
                "X Axis",        // X-axis label
                "Y Axis",        // Y-axis label
                dataset,         // Dataset
                PlotOrientation.VERTICAL,
                true,            // Show legend
                true,
                false
            );

        XYPlot plot = chart.getXYPlot();
        plot.getDomainAxis().setRangeWithMargins(-10, 10); // X-axis range (-10 to 10)
        plot.getRangeAxis().setRangeWithMargins(-10, 10);  // Y-axis range (-10 to 10)

        XYLineAnnotation xAxis = new XYLineAnnotation(0, -10, 0, 10);
        XYLineAnnotation yAxis = new XYLineAnnotation(-10, 0, 10, 0);
        plot.addAnnotation(xAxis);
        plot.addAnnotation(yAxis);

        XYDifferenceRenderer renderer = new XYDifferenceRenderer(Color.GREEN, Color.RED, false);
        plot.setRenderer(renderer);

        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
        renderer2.setSeriesPaint(0, Color.BLUE); // Set color for the second line

        XYLineAndShapeRenderer renderer3 = new XYLineAndShapeRenderer();
        renderer3.setSeriesPaint(0, Color.BLACK); // Set color for the second line

        XYLineAndShapeRenderer renderer4 = new XYLineAndShapeRenderer();
        renderer4.setSeriesPaint(0, Color.YELLOW); // Set color for the second line

        XYLineAndShapeRenderer renderer5 = new XYLineAndShapeRenderer();
        renderer5.setSeriesPaint(0, Color.WHITE); // Set color for the second line

        XYLineAndShapeRenderer renderer6 = new XYLineAndShapeRenderer();
        renderer6.setSeriesPaint(0, Color.RED); // Set color for the second line

        XYLineAndShapeRenderer renderer7 = new XYLineAndShapeRenderer();
        renderer7.setSeriesPaint(0, Color.PINK); // Set color for the second line

        XYLineAndShapeRenderer renderer8 = new XYLineAndShapeRenderer();
        renderer8.setSeriesPaint(0, Color.GREEN); // Set color for the second line

        // Add the second line to the existing plot with a new renderer
        plot.setDataset(1, dataset2);
        plot.setRenderer(1, renderer2);

        plot.setDataset(2, dataset3);
        plot.setRenderer(2, renderer3);

        plot.setDataset(3, dataset4);
        plot.setRenderer(3, renderer4);

        plot.setDataset(4, dataset5);
        plot.setRenderer(4, renderer5);

        plot.setDataset(5, dataset6);
        plot.setRenderer(5, renderer6);

        plot.setDataset(6, dataset7);
        plot.setRenderer(6, renderer7);

        plot.setDataset(7, dataset8);
        plot.setRenderer(7, renderer8);
        //dataset2.removeAllSeries();
        dataset3.removeAllSeries();
        dataset4.removeAllSeries();
        dataset5.removeAllSeries();
        dataset6.removeAllSeries();
        dataset7.removeAllSeries();
        dataset8.removeAllSeries();

        chart.setBackgroundPaint(Color.WHITE);
        ChartPanel chartPanelComponent = new ChartPanel(chart);
        chartPanel.add(chartPanelComponent);

        JButton button3 = new JButton("Just for show");
        panel.add(button3, gbc);

        JButton button5 = new JButton("Just for show 2");
        panel.add(button5, gbc);
        JButton button16 = new JButton("Just for show 2");
        panel.add(button16, gbc);
        JButton button17 = new JButton("Just for show 2");
        panel.add(button17, gbc);
        JButton button18 = new JButton("Just for show 2");
        panel.add(button18, gbc);
        JButton button19 = new JButton("Just for show 2");
        panel.add(button19, gbc);
        JButton button20 = new JButton("Just for show 2");
        panel.add(button20, gbc);
        JButton button21 = new JButton("Just for show 2");
        panel.add(button21, gbc);
        JButton button22 = new JButton("Just for show 2");
        panel.add(button22, gbc);
        JButton button23 = new JButton("Just for show 2");
        panel.add(button23, gbc);
        JButton button24 = new JButton("Just for show 2");
        panel.add(button24, gbc);
        JButton button25 = new JButton("Just for show 2");
        panel.add(button25, gbc);
        //JButton button26 = new JButton("Just for show 2");
        //panel.add(button26, gbc);

        // Add action listener to the button
        button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Get values from text fields
                    value1 = textField1.getText().isEmpty() ? def_value1 : textField1.getText();
                    value2 = textField2.getText().isEmpty() ? def_value2 : textField2.getText();
                    value3 = textField3.getText().isEmpty() ? def_value3 : textField3.getText();
                    value4 = textField4.getText().isEmpty() ? def_value4 : textField4.getText();
                    value5 = textField5.getText().isEmpty() ? def_value5 : textField5.getText();
                    value6 = textField6.getText().isEmpty() ? def_value6 : textField6.getText();
                    value7 = textField7.getText().isEmpty() ? def_value7 : textField7.getText();
                    value8 = textField8.getText().isEmpty() ? def_value8 : textField8.getText();

                    value32 = textField32.getText().isEmpty() ? def_value32 : textField32.getText();
                    value33 = textField33.getText().isEmpty() ? def_value33 : textField33.getText();

                    intervall=Integer.valueOf(value32);
                    voltt=Integer.valueOf(value33);

                    // Check the selected option in the dropdown list
                    String selectedOption = (String) comboBox.getSelectedItem();

                    // If "Other" is selected, use the value from the fourth text field
                    if ("Other".equals(selectedOption)) {
                        //textField4.setVisible(true); // Show the fourth text field
                        value4 = textField4.getText().isEmpty() ? "3,4,5" : textField4.getText();
                    } else {
                        //textField4.setVisible(false); // Hide the fourth text field
                        int selectedIndex = comboBox.getSelectedIndex();
                        value4 = options_data[selectedIndex];//Integer.toString(selectedIndex);//textField4.getText().isEmpty() ? "" : textField4.getText();
                    }
                    v4=obb.mainn(value4);

                    impl=1;
                    last_m=1;last_s=0;

                    // Display the values in the console
                    /*
                    System.out.println("Value 1: " + value1);
                    System.out.println("Value 2: " + value2);
                    System.out.println("Value 3: " + value3);
                    System.out.println("Selected Option: " + selectedOption);
                    System.out.println("Value 4: " + value4);
                     */

                    // Display the values in the result label
                    resultLabel.setText("Result: " + value1 + ", " + value2 + ", " + value3 
                        + ",\n " + selectedOption + ", " + value4+ ", " + value5+ ", " + value6+ ", " + value7+", " + value8);

                    ob22.new_ratt(Double.valueOf(value1),Integer.valueOf(value2),Double.valueOf(value3),v4
                    ,Double.valueOf(value5),Integer.valueOf(value6),Double.valueOf(value7),Double.valueOf(value8),1
                    ,intervall,tot_intervall,voltt, tot_voltt);

                    /*
                    // Get the new X and Y values (e.g., from text fields)
                    double[] newXValues = ob22.xValues;// ... logic to get new X values
                    double[] newYValues = ob22.yValues;// ... logic to get new Y values

                    //newXValues[0]=newXValues[0]+1;
                    //newYValues[0]=newYValues[0]+1;

                    // Clear the existing dataset
                    dataset.removeAllSeries();

                    // Create a new series with the updated X and Y values
                    XYSeries series = new XYSeries("Data");
                    for (int i = 0; i < newXValues.length && i < newYValues.length; i++) {
                    series.add(newXValues[i], newYValues[i]);
                    }
                    dataset.addSeries(series);

                    // Notify the chart panel that the dataset has changed
                    chartPanelComponent.repaint();
                     */

                }
            });

        button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    value1 = textField1.getText().isEmpty() ? def_value1 : textField1.getText();
                    value2 = textField2.getText().isEmpty() ? def_value2 : textField2.getText();
                    value3 = textField3.getText().isEmpty() ? def_value3 : textField3.getText();
                    value4 = textField4.getText().isEmpty() ? def_value4 : textField4.getText();
                    value5 = textField5.getText().isEmpty() ? def_value5 : textField5.getText();
                    value6 = textField6.getText().isEmpty() ? def_value6 : textField6.getText();
                    value7 = textField7.getText().isEmpty() ? def_value7 : textField7.getText();
                    value8 = textField8.getText().isEmpty() ? def_value8 : textField8.getText();

                    def_data[0]=value1;def_data[1]=value2;def_data[2]=value3;
                    def_data[3]=value4;def_data[4]=value5;def_data[5]=value6;
                    def_data[6]=value7;def_data[7]=value8;

                    String filePath = "OptionDefaultdata.txt";
                    int writingMode = 0;
                    obrw.writeFile(filePath, def_data, writingMode);

                }
            });

        button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(impl==1)
                    {
                        int selectedIndex = comboBox1.getSelectedIndex();
                        int nnum=pnts1[selectedIndex][0];

                        x_ar1=ob22.x_ar;
                        y_ar1=ob22.y_ar;
                        y1_ar1=ob22.y1_ar;
                        st_ar1=ob22.st_ar;

                        String snum=pnt_nm1[nnum];

                        if(previous_index!=selectedIndex)
                            p_cnt=-1;
                        previous_index=selectedIndex;

                        p_cnt++;
                        if(p_cnt>=max_cnt)
                            p_cnt=0;
                        System.out.println("\nIndex "+selectedIndex+" nnum "+nnum+" snum "+snum);
                        System.out.println("\np_cnt "+p_cnt+" max_cnt "+max_cnt);

                        // Get the new X and Y values (e.g., from text fields)
                        String newXV=x_ar1[selectedIndex][p_cnt];
                        String newYV=y_ar1[selectedIndex][p_cnt];
                        String newY1V=y1_ar1[selectedIndex][p_cnt];
                        String newstV=st_ar1[selectedIndex][p_cnt];

                        System.out.println("\nnewXV "+newXV+"\nnewYV "+newYV+"\nnewY1V "+newY1V+"\nnewstV "+newstV);

                        double[] newXValues = convertToDoubleArray(newXV);// ... logic to get new X values
                        double[] newYValues = convertToDoubleArray(newYV);// ... logic to get new Y values
                        double[] newY1Values = convertToDoubleArray(newY1V);// ... logic to get new Y values
                        double[] dda = convertToDoubleArray(newstV);// ... logic to get new Y values
                        value88=dda[51];
                        ob22.t_prt_two(dda," ", " ", " ");

                        System.out.printf("%.2f | ",dda[54]);
                        System.out.printf("%.2f ,",dda[16]);
                        System.out.printf(" %.2f ,",dda[48]);
                        String s1="",s2="";
                        if(Math.abs(dda[16])>dda[48])
                            s1=String.format("   1:%.1f ",Math.abs(dda[16])/dda[48]);
                        else
                            s1=String.format(" %.1f:1   ",dda[48]/Math.abs(dda[16]));
                        s2=String.format("%.2f | %.2f , %.2f , %s | %.2f",dda[54],dda[16],dda[48],s1, dda[25]);
                        resultLabel.setText("Result: "+s2);
                        //newXValues[0]=newXValues[0]+1;
                        //newYValues[0]=newYValues[0]+1;

                        // Clear the existing dataset
                        dataset.removeAllSeries();
                        dataset2.removeAllSeries();
                        dataset3.removeAllSeries();
                        dataset4.removeAllSeries();
                        dataset5.removeAllSeries();
                        dataset6.removeAllSeries();
                        dataset7.removeAllSeries();
                        dataset8.removeAllSeries();

                        // Create a new series with the updated X and Y values
                        XYSeries series = new XYSeries("Data");
                        for (int i = 0; i < newXValues.length && i < newYValues.length; i++) {
                            series.add(newXValues[i], newYValues[i]);//series.add(newXValues[i], newYValues[i]);
                        }
                        dataset.addSeries(series);

                        XYSeries series2 = new XYSeries("Data2");
                        for (int i = 0; i < newXValues.length && i < newY1Values.length; i++) {
                            series2.add(newXValues[i], newY1Values[i]);//series.add(newXValues[i], newYValues[i]);
                        }
                        dataset2.addSeries(series2);

                        // Notify the chart panel that the dataset has changed
                        chartPanelComponent.repaint();
                    }
                }
            });

        button56.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(impl1==1)
                    {
                        int selectedIndex = comboBox2.getSelectedIndex();
                        max_cnt1=mk_dyn[0].length;
                        /*int nnum=pnts1[selectedIndex][0];

                        x_ar1=ob22.x_ar;
                        y_ar1=ob22.y_ar;
                        y1_ar1=ob22.y1_ar;
                        st_ar1=ob22.st_ar;

                        String snum=pnt_nm1[nnum];
                         */
                        if(previous_index1!=selectedIndex)
                            p_cnt1=-1;
                        previous_index1=selectedIndex;

                        p_cnt1++;
                        if(p_cnt1>=max_cnt1)
                            p_cnt1=0;
                        //System.out.println("\nIndex "+selectedIndex+" nnum "+nnum+" snum "+snum);

                        int mk_num=(int) mk_dyn[selectedIndex][p_cnt1];
                        System.out.println("\nIndex "+selectedIndex);
                        System.out.println("p_cnt1 "+p_cnt1+" max_cnt1 "+max_cnt1);
                        System.out.println(" mk_num "+mk_num);

                        ob30.pnt(mk_data[mk_num]);
                        ob30.pnt(mk_data_x);

                        double mk_data_d[]=ob30.copy_to_double(mk_data[mk_num]);
                        double mk_data_x_d[]=ob30.copy_to_double(mk_data_x);

                        double level_y[]=new double[1];//{0};
                        level_y[0]=mk_data_d[0];
                        int level_y_l=level_y.length;//0;
                        example2.updateChartData(mk_data_x_d, new double[][]{mk_data_d},mk_data_d[0], level_y, level_y_l, new String[]{"Combined Trend 3"});

                        /*ob61.rd_two(fnn,0);
                        mk_data=ob61.new_aa;
                        ob30.pnt(mk_data);*/

                        /*
                        // Get the new X and Y values (e.g., from text fields)
                        String newXV=x_ar1[selectedIndex][p_cnt];
                        String newYV=y_ar1[selectedIndex][p_cnt];
                        String newY1V=y1_ar1[selectedIndex][p_cnt];
                        String newstV=st_ar1[selectedIndex][p_cnt];

                        System.out.println("\nnewXV "+newXV+"\nnewYV "+newYV+"\nnewY1V "+newY1V+"\nnewstV "+newstV);

                        double[] newXValues = convertToDoubleArray(newXV);// ... logic to get new X values
                        double[] newYValues = convertToDoubleArray(newYV);// ... logic to get new Y values
                        double[] newY1Values = convertToDoubleArray(newY1V);// ... logic to get new Y values
                        double[] dda = convertToDoubleArray(newstV);// ... logic to get new Y values
                        value88=dda[51];
                        ob22.t_prt_two(dda," ", " ", " ");

                        System.out.printf("%.2f | ",dda[54]);
                        System.out.printf("%.2f ,",dda[16]);
                        System.out.printf(" %.2f ,",dda[48]);
                        String s1="",s2="";
                        if(Math.abs(dda[16])>dda[48])
                        s1=String.format("   1:%.1f ",Math.abs(dda[16])/dda[48]);
                        else
                        s1=String.format(" %.1f:1   ",dda[48]/Math.abs(dda[16]));
                        s2=String.format("%.2f | %.2f , %.2f , %s | %.2f",dda[54],dda[16],dda[48],s1, dda[25]);
                        resultLabel.setText("Result: "+s2);
                        //newXValues[0]=newXValues[0]+1;
                        //newYValues[0]=newYValues[0]+1;

                        // Clear the existing dataset
                        dataset.removeAllSeries();
                        dataset2.removeAllSeries();
                        dataset3.removeAllSeries();
                        dataset4.removeAllSeries();
                        dataset5.removeAllSeries();
                        dataset6.removeAllSeries();
                        dataset7.removeAllSeries();
                        dataset8.removeAllSeries();

                        // Create a new series with the updated X and Y values
                        XYSeries series = new XYSeries("Data");
                        for (int i = 0; i < newXValues.length && i < newYValues.length; i++) {
                        series.add(newXValues[i], newYValues[i]);//series.add(newXValues[i], newYValues[i]);
                        }
                        dataset.addSeries(series);

                        XYSeries series2 = new XYSeries("Data2");
                        for (int i = 0; i < newXValues.length && i < newY1Values.length; i++) {
                        series2.add(newXValues[i], newY1Values[i]);//series.add(newXValues[i], newYValues[i]);
                        }
                        dataset2.addSeries(series2);

                        // Notify the chart panel that the dataset has changed
                        chartPanelComponent.repaint();
                         */
                    }
                }
            });

        button4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Get values from text fields
                    value11 = textField11.getText().isEmpty() ? def_value11 : textField11.getText();
                    value12 = textField12.getText().isEmpty() ? def_value12 : textField12.getText();
                    value13 = textField13.getText().isEmpty() ? def_value13 : textField13.getText();
                    value32 = textField32.getText().isEmpty() ? def_value32 : textField32.getText();
                    value33 = textField33.getText().isEmpty() ? def_value33 : textField33.getText();

                    value1 = textField1.getText().isEmpty() ? def_value1 : textField1.getText();
                    value2 = textField2.getText().isEmpty() ? def_value2 : textField2.getText();
                    value3 = textField3.getText().isEmpty() ? def_value3 : textField3.getText();
                    value4 = textField4.getText().isEmpty() ? def_value4 : textField4.getText();
                    value5 = textField5.getText().isEmpty() ? def_value5 : textField5.getText();
                    value6 = textField6.getText().isEmpty() ? def_value6 : textField6.getText();
                    value7 = textField7.getText().isEmpty() ? def_value7 : textField7.getText();
                    value8 = textField8.getText().isEmpty() ? def_value8 : textField8.getText();

                    intervall=Integer.valueOf(value32);
                    voltt=Integer.valueOf(value33);

                    // Check the selected option in the dropdown list
                    String selectedOption = (String) comboBox.getSelectedItem();

                    // If "Other" is selected, use the value from the fourth text field
                    if ("Other".equals(selectedOption)) {
                        //textField4.setVisible(true); // Show the fourth text field
                        value4 = textField4.getText().isEmpty() ? "3,4,5" : textField4.getText();
                    } else {
                        //textField4.setVisible(false); // Hide the fourth text field
                        int selectedIndex = comboBox.getSelectedIndex();
                        value4 = options_data[selectedIndex];//Integer.toString(selectedIndex);//textField4.getText().isEmpty() ? "" : textField4.getText();
                    }
                    int v4[]=obb.mainn(value4);

                    // Display the values in the result label
                    resultLabel.setText("Result: " + value1 + ", " + value2 + ", " + value3 
                        + ",\n " + selectedOption + ", " + value4+ ", " + value5+ ", " + value6+ ", " + value7+", " + value8);

                    ob22.new_ratt(Double.valueOf(value11),Integer.valueOf(value2),Double.valueOf(value3),v4
                    ,Double.valueOf(value5),Integer.valueOf(value12),Double.valueOf(value13),Double.valueOf(value8),0
                    ,intervall,tot_intervall,voltt, tot_voltt);

                    //ob22.new_ratt(Double.valueOf(value1),Integer.valueOf(value2),Double.valueOf(value3),v4
                    //,Double.valueOf(value5),Integer.valueOf(value6),Double.valueOf(value7),Double.valueOf(value8),0
                    //,intervall,tot_intervall,voltt, tot_voltt);
                }
            });

        button6.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(value88>0)
                        ob22.new_ratt(Double.valueOf(value1),Integer.valueOf(value2),Double.valueOf(value3),v4
                        ,Double.valueOf(value5),Integer.valueOf(value6),Double.valueOf(value7),value88,1
                        ,intervall,tot_intervall,voltt, tot_voltt);

                }
            });

        // Add action listener to the button
        button40.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nummofdays=15;
                    // Get values from text fields
                    value11 = textField11.getText().isEmpty() ? def_value11 : textField11.getText();
                    value12 = textField12.getText().isEmpty() ? def_value12 : textField12.getText();
                    value13 = textField13.getText().isEmpty() ? def_value13 : textField13.getText();
                    value14 = textField14.getText().isEmpty() ? def_value14 : textField14.getText();
                    value15 = textField15.getText().isEmpty() ? def_value15 : textField15.getText();
                    value16 = textField16.getText().isEmpty() ? def_value16 : textField16.getText();
                    value17 = textField17.getText().isEmpty() ? def_value17 : textField17.getText();
                    value18 = textField18.getText().isEmpty() ? def_value18 : textField18.getText();

                    value32 = textField32.getText().isEmpty() ? def_value32 : textField32.getText();
                    value33 = textField33.getText().isEmpty() ? def_value33 : textField33.getText();
                    value34 = textField34.getText().isEmpty() ? def_value34 : textField34.getText();
                    value35 = textField35.getText().isEmpty() ? def_value35 : textField35.getText();
                    value36 = textField36.getText().isEmpty() ? def_value36 : textField36.getText();
                    value37 = textField37.getText().isEmpty() ? def_value37 : textField37.getText();

                    intervall=Integer.valueOf(value32);
                    voltt=Integer.valueOf(value33);

                    // Display the values in the result label
                    resultLabel.setText("Result: " + value11 + ", " + value12 + ", " + value13 
                        + ", " + value14+ ", " + value15+ ", " + value16+ ", " + value17+", " + value18);

                    int i3=0;
                    last_m=0;last_s=1;

                    String oopt_s[]=new String[1000];
                    double oopt_sz[]=new double[1000];
                    int os_num[]=new int[100];
                    String os_num_s[]=new String[100];
                    double os_numsz[]=new double[100];

                    int os_l=7,ossz_l=0;
                    oopt_s[0]=value11;
                    oopt_s[1]=value12;
                    oopt_s[2]=value13;
                    oopt_s[3]=Integer.toString(intervall);
                    oopt_s[4]=Integer.toString(tot_intervall);
                    oopt_s[5]=Integer.toString(voltt);
                    oopt_s[6]=Integer.toString(tot_voltt);
                    if(value14.length()>0)
                    {
                        os_num=convertStringToIntArray(value14);
                        os_num_s=convertIntToCh_StrArray(os_num,'C');
                        os_numsz=convertStringToDoubleArray(value34);
                        for(i3=0;i3<os_num_s.length;i3++)
                        { 
                            oopt_s[os_l]=os_num_s[i3];
                            oopt_sz[ossz_l]=(double) os_numsz[i3];
                            os_l++;ossz_l++;
                        }
                    }
                    if(value15.length()>0)
                    {
                        os_num=convertStringToIntArray(value15);
                        os_num_s=convertIntToCh_StrArray(os_num,'P');
                        os_numsz=convertStringToDoubleArray(value35);
                        for(i3=0;i3<os_num_s.length;i3++)
                        { 
                            oopt_s[os_l]=os_num_s[i3];
                            oopt_sz[ossz_l]=(double) os_numsz[i3];
                            os_l++;ossz_l++;
                        }
                    }
                    if(value16.length()>0)
                    {
                        os_num=convertStringToIntArray(value16);
                        os_num_s=convertIntToCh_StrArray(os_num,'M');
                        os_numsz=convertStringToDoubleArray(value36);
                        for(i3=0;i3<os_num_s.length;i3++)
                        { 
                            oopt_s[os_l]=os_num_s[i3];
                            oopt_sz[ossz_l]=(double) os_numsz[i3];
                            os_l++;ossz_l++;
                        }
                    }
                    if(value17.length()>0)
                    {
                        os_num=convertStringToIntArray(value17);
                        os_num_s=convertIntToCh_StrArray(os_num,'N');
                        os_numsz=convertStringToDoubleArray(value37);
                        for(i3=0;i3<os_num_s.length;i3++)
                        { 
                            oopt_s[os_l]=os_num_s[i3];
                            oopt_sz[ossz_l]=(double) os_numsz[i3];
                            os_l++;ossz_l++;
                        }
                        //oopt_s[os_l++]=value17;
                    }
                    oopt_s=ob30.shorti(oopt_s,os_l);
                    ob30.pnt(oopt_s);

                    oopt_sz=ob30.shorti(oopt_sz,ossz_l);
                    ob30.pnt(oopt_sz);

                    ob22.maiin(oopt_s,oopt_sz,1,0,nummofdays,nummofsim,oldsim);

                    String xx_data=ob22.x_data;
                    String yy_data=ob22.y_data;
                    String yy1_data=ob22.y1_data;

                    np_x_s[index_s]=xx_data;
                    np_y_s[index_s]=yy_data;
                    np_y1_s[index_s]=yy1_data;

                    System.out.println("\nxx_data "+xx_data+"\nyy_data "+yy_data);
                    double[] newXValues = convertToDoubleArray(xx_data);// ... logic to get new X values
                    double[] newYValues = convertToDoubleArray(yy_data);// ... logic to get new Y values
                    double[] newY1Values = convertToDoubleArray(yy1_data);// ... logic to get new Y values

                    // Clear the existing dataset
                    dataset.removeAllSeries();
                    dataset2.removeAllSeries();
                    dataset3.removeAllSeries();
                    dataset4.removeAllSeries();
                    dataset5.removeAllSeries();
                    dataset6.removeAllSeries();
                    dataset7.removeAllSeries();
                    dataset8.removeAllSeries();

                    // Create a new series with the updated X and Y values
                    XYSeries series = new XYSeries("Data");
                    for (int i = 0; i < newXValues.length && i < newYValues.length; i++) {
                        series.add(newXValues[i], newYValues[i]);
                    }
                    dataset.addSeries(series);

                    XYSeries series2 = new XYSeries("Data2");
                    for (int i = 0; i < newXValues.length && i < newY1Values.length; i++) {
                        series2.add(newXValues[i], newY1Values[i]);//series.add(newXValues[i], newYValues[i]);
                    }
                    dataset2.addSeries(series2);

                    // Notify the chart panel that the dataset has changed
                    chartPanelComponent.repaint();

                }
            });

        button66.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("In new button ");
                    if(last_s==1)
                    {
                        System.out.println("In new s button ");
                        String t_value11 = textField11.getText();//.isEmpty() ? def_value11 : textField11.getText();
                        if(last_value11.equals(t_value11))
                        {System.out.println("In new s equal button ");}
                        else
                        {
                            last_value11=t_value11;
                            index_s++;
                            System.out.println("index_s upgraded is "+index_s);
                            Comb_Y=convertToDoubleArray(np_y_s,index_s,100);
                            Comb_Y1=convertToDoubleArray(np_y1_s,index_s,100);
                            Combb_Y=ob30.add_arr_db(Comb_Y);
                            Combb_Y1=ob30.add_arr_db(Comb_Y1);
                        }
                        System.out.println("\nIn indexxxx_s is "+index_s);
                        for(int i=0;i<index_s;i++)
                        {
                            System.out.println("i is "+i);
                            System.out.println("x : "+np_x_s[i]);
                            System.out.println("y : "+np_y_s[i]);
                            System.out.println("y1 : "+np_y1_s[i]);
                            System.out.println();
                        }
                        if(index_s>0)
                        { 
                            ob30.pnt(Comb_Y);
                            ob30.pnt(Combb_Y);
                        }

                        double[] newXValues = convertToDoubleArray(np_x_s[index_s-1]);// ... logic to get new X values
                        double[] newYValues = Combb_Y;// ... logic to get new Y values
                        double[] newY1Values = Combb_Y1;// ... logic to get new Y values

                        // Clear the existing dataset
                        dataset.removeAllSeries();
                        dataset2.removeAllSeries();
                        dataset3.removeAllSeries();
                        dataset4.removeAllSeries();
                        dataset5.removeAllSeries();
                        dataset6.removeAllSeries();
                        dataset7.removeAllSeries();
                        dataset8.removeAllSeries();

                        // Create a new series with the updated X and Y values
                        XYSeries series = new XYSeries("Data");
                        for (int i = 0; i < newXValues.length && i < newYValues.length; i++) {
                            series.add(newXValues[i], newYValues[i]);
                        }
                        dataset.addSeries(series);

                        XYSeries series2 = new XYSeries("Data2");
                        for (int i = 0; i < newXValues.length && i < newY1Values.length; i++) {
                            series2.add(newXValues[i], newY1Values[i]);//series.add(newXValues[i], newYValues[i]);
                        }
                        dataset2.addSeries(series2);

                        // Notify the chart panel that the dataset has changed
                        chartPanelComponent.repaint();

                    }
                    else if(last_m==1)
                    {
                        System.out.println("In new m button ");
                        String t_value1 = textField11.getText();//.isEmpty() ? def_value1 : textField1.getText();
                        if(value1.equals(t_value1))
                        {System.out.println("In new m equal button ");}
                        else
                        {
                            value1=t_value1;
                            index_m++;
                            System.out.println("index_m is "+index_m);
                        }
                    }
                }
            });

        button51.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    impl1=1;

                    nummofdays = 15;
                    /*
                    double[] newXValues={-3, -2, -1, 0, 1, 2, 3}; // Initial xValues
                    double[] newYValues= {3, 2, 1, 0, -1, -2, -3};
                    double[] newY1Values= {-5, -6, -7, 5, 6, 7, 8}; // Initial y1Values
                    double[] newY2Values= {-11, -10, -9, 9, 10, 11, 12}; // Initial y2Values             
                    double[] newY3Values = {-11, -5, -2, 9, 3, -4, 6}; // Initial y2Values
                    double[] newY4Values = {-14, -13, -12, 15, 12, 13, 14};
                     */
                    // Get values from text fields
                    value11 = textField11.getText().isEmpty() ? def_value11 : textField11.getText();
                    value12 = textField12.getText().isEmpty() ? def_value12 : textField12.getText();
                    value13 = textField13.getText().isEmpty() ? def_value13 : textField13.getText();
                    value14 = textField14.getText().isEmpty() ? def_value14 : textField14.getText();
                    value15 = textField15.getText().isEmpty() ? def_value15 : textField15.getText();
                    value16 = textField16.getText().isEmpty() ? def_value16 : textField16.getText();
                    value17 = textField17.getText().isEmpty() ? def_value17 : textField17.getText();
                    value18 = textField18.getText().isEmpty() ? def_value18 : textField18.getText();

                    value34 = textField34.getText().isEmpty() ? def_value34 : textField34.getText();
                    value35 = textField35.getText().isEmpty() ? def_value35 : textField35.getText();
                    value36 = textField36.getText().isEmpty() ? def_value36 : textField36.getText();
                    value37 = textField37.getText().isEmpty() ? def_value37 : textField37.getText();

                    value21 = textField21.getText().isEmpty() ? def_value21 : textField21.getText();
                    value22 = textField22.getText().isEmpty() ? def_value22 : textField22.getText();

                    nummofdays=Integer.valueOf(value21);
                    nummofsim=Integer.valueOf(value22);

                    // Display the values in the result label
                    resultLabel.setText("Result: " + value11 + ", " + value12 + ", " + value13 
                        + ", " + value14+ ", " + value15+ ", " + value16+ ", " + value17+", " + value18);

                    int i3=0;
                    last_m=0;last_s=1;

                    String oopt_s[]=new String[1000];
                    double oopt_sz[]=new double[1000];
                    int os_num[]=new int[100];
                    String os_num_s[]=new String[100];
                    double os_numsz[]=new double[100];

                    int os_l=7,ossz_l=0;
                    oopt_s[0]=value11;
                    oopt_s[1]=value12;
                    oopt_s[2]=value13;
                    oopt_s[3]=Integer.toString(intervall);
                    oopt_s[4]=Integer.toString(tot_intervall);
                    oopt_s[5]=Integer.toString(voltt);
                    oopt_s[6]=Integer.toString(tot_voltt);
                    if(value14.length()>0)
                    {
                        os_num=convertStringToIntArray(value14);
                        os_num_s=convertIntToCh_StrArray(os_num,'C');
                        os_numsz=convertStringToDoubleArray(value34);
                        for(i3=0;i3<os_num_s.length;i3++)
                        { 
                            oopt_s[os_l]=os_num_s[i3];
                            oopt_sz[ossz_l]=(double) os_numsz[i3];
                            os_l++;ossz_l++;
                        }
                    }
                    if(value15.length()>0)
                    {
                        os_num=convertStringToIntArray(value15);
                        os_num_s=convertIntToCh_StrArray(os_num,'P');
                        os_numsz=convertStringToDoubleArray(value35);
                        for(i3=0;i3<os_num_s.length;i3++)
                        { 
                            oopt_s[os_l]=os_num_s[i3];
                            oopt_sz[ossz_l]=(double) os_numsz[i3];
                            os_l++;ossz_l++;
                        }
                    }
                    if(value16.length()>0)
                    {
                        os_num=convertStringToIntArray(value16);
                        os_num_s=convertIntToCh_StrArray(os_num,'M');
                        os_numsz=convertStringToDoubleArray(value36);
                        for(i3=0;i3<os_num_s.length;i3++)
                        { 
                            oopt_s[os_l]=os_num_s[i3];
                            oopt_sz[ossz_l]=(double) os_numsz[i3];
                            os_l++;ossz_l++;
                        }
                    }
                    if(value17.length()>0)
                    {
                        os_num=convertStringToIntArray(value17);
                        os_num_s=convertIntToCh_StrArray(os_num,'N');
                        os_numsz=convertStringToDoubleArray(value37);
                        for(i3=0;i3<os_num_s.length;i3++)
                        { 
                            oopt_s[os_l]=os_num_s[i3];
                            oopt_sz[ossz_l]=(double) os_numsz[i3];
                            os_l++;ossz_l++;
                        }
                        //oopt_s[os_l++]=value17;
                    }
                    oopt_s=ob30.shorti(oopt_s,os_l);
                    ob30.pnt(oopt_s);

                    oopt_sz=ob30.shorti(oopt_sz,ossz_l);
                    ob30.pnt(oopt_sz);

                    System.out.println("nummofdays : "+nummofdays);
                    ob22.maiin(oopt_s,oopt_sz,0,1,nummofdays,nummofsim,oldsim);

                    String xx_data=ob22.tm_x_data;
                    String yy_data[]=new String[nummofsim];
                    yy_data=ob22.tm_y_data;

                    int yy_data_l=nummofsim;//ob22.tm_l;
                    String ns[]=ob22.ns;

                    yy_data=ob30.shorti(yy_data,yy_data_l);
                    double yy_data_d[][]=convertToDoubleArray(yy_data);
                    System.out.println("\nIn default");
                    ob30.pnt(yy_data_d);

                    double xx_data_d[]=convertToDoubleArray(xx_data);
                    ob30.pnt(xx_data_d);

                    System.out.println("\nlrngth xx_data_d "+xx_data_d.length+" yy_data_d "+yy_data_d.length +" , "+(yy_data_d[0]).length);

                    double level_y[]=new double[1];//new double[0];
                    level_y[0]=yy_data_d[0][0];
                    int level_y_l=level_y.length;
                    
                    String nm[]=new String[yy_data.length];
                    for(int i31=0;i31<yy_data.length;i31++)
                        nm[i31]="Combo "+Integer.toString(i31)+", "+Integer.toString(nummofsim);
                    System.out.println("\n check status 30");
                    ob30.pnt(nm);
                    System.out.println("\n check status 31");
                    //example2.updateChartData(xx_data_d, yy_data_d, 0, level_y, 0, nm);
                    System.out.println("\n check status 32");
                    for(int i31=0;i31<yy_data.length;i31++)
                        nm[i31]=Integer.toString(i31);//"NewC "+Integer.toString(i31)+", "+Integer.toString(nummofsim);
                    System.out.println("\n check status 33");
                    //nm=new String[0];
                    ob30.pnt(nm);
                    System.out.println("\n check status 34");
                    ScatterPlotExample example3 = new ScatterPlotExample();
                    example3.updateChartData(xx_data_d, yy_data_d, 0, level_y, level_y_l, nm);
                    System.out.println("\n check status 35");

                    mk_dyn=ob22.mk_dyn;
                    System.out.println("\nMarket dynamics in default : ");
                    ob30.pnt(mk_dyn);

                    ob61.rd_two(fnn,0);
                    mk_data=ob61.new_aa;
                    ob30.pnt(mk_data);

                    ob61.rd(fnn1,0);
                    mk_data_x=ob61.new_a;
                    ob30.pnt(mk_data_x);
                    //np_x_s[index_s]=xx_data;
                    //np_y_s[index_s]=yy_data;
                    //np_y1_s[index_s]=yy1_data;

                    /*
                    System.out.println("\nxx_data "+xx_data+"\nyy_data "+yy_data);
                    double[] newXValues = convertToDoubleArray(xx_data);// ... logic to get new X values// ... logic to get new Y values
                    double[] newY1Values = convertToDoubleArray(yy_data[0]);
                    double[] newY2Values = convertToDoubleArray(yy_data[1]);
                    double[] newY3Values = convertToDoubleArray(yy_data[2]);
                    double[] newY4Values = convertToDoubleArray(yy_data[3]);
                    double[] newY5Values = convertToDoubleArray(yy_data[4]);
                    double[] newY6Values = convertToDoubleArray(yy_data[5]);
                    double[] newY7Values = convertToDoubleArray(yy_data[6]);

                    ob30.pnt(newXValues);
                    ob30.pnt(newY1Values);ob30.pnt(newY2Values);ob30.pnt(newY3Values);ob30.pnt(newY4Values);ob30.pnt(newY5Values);

                    // Clear the existing dataset
                    dataset.removeAllSeries();
                    dataset2.removeAllSeries();
                    dataset3.removeAllSeries();
                    dataset4.removeAllSeries();
                    dataset5.removeAllSeries();
                    dataset6.removeAllSeries();
                    dataset7.removeAllSeries();
                    dataset8.removeAllSeries();

                    // Create a new series with the updated X and Y values
                    XYSeries series2 = new XYSeries(ns[0]);
                    for (int i = 0; i < newXValues.length && i < newY1Values.length; i++) {
                    series2.add(newXValues[i], newY1Values[i]);
                    }
                    dataset2.addSeries(series2);

                    XYSeries series3 = new XYSeries(ns[1]);
                    for (int i = 0; i < newXValues.length && i < newY2Values.length; i++) {
                    series3.add(newXValues[i], newY2Values[i]);//series.add(newXValues[i], newYValues[i]);
                    }
                    dataset3.addSeries(series3);

                    XYSeries series4 = new XYSeries(ns[2]);
                    for (int i = 0; i < newXValues.length && i < newY3Values.length; i++) {
                    series4.add(newXValues[i], newY3Values[i]);//series.add(newXValues[i], newYValues[i]);
                    }
                    dataset4.addSeries(series4);

                    XYSeries series5 = new XYSeries(ns[3]);
                    for (int i = 0; i < newXValues.length && i < newY4Values.length; i++) {
                    series5.add(newXValues[i], newY4Values[i]);//series.add(newXValues[i], newYValues[i]);
                    }
                    dataset5.addSeries(series5);

                    XYSeries series6 = new XYSeries(ns[4]);
                    for (int i = 0; i < newXValues.length && i < newY5Values.length; i++) {
                    series6.add(newXValues[i], newY5Values[i]);//series.add(newXValues[i], newYValues[i]);
                    }
                    dataset6.addSeries(series6);

                    XYSeries series7 = new XYSeries(ns[5]);
                    for (int i = 0; i < newXValues.length && i < newY6Values.length; i++) {
                    series7.add(newXValues[i], newY6Values[i]);//series.add(newXValues[i], newYValues[i]);
                    }
                    dataset7.addSeries(series7);

                    XYSeries series8 = new XYSeries(ns[6]);
                    for (int i = 0; i < newXValues.length && i < newY7Values.length; i++) {
                    series8.add(newXValues[i], newY7Values[i]);//series.add(newXValues[i], newYValues[i]);
                    }
                    dataset8.addSeries(series8);

                    // Notify the chart panel that the dataset has changed
                    chartPanelComponent.repaint();
                     */
                }

            });
            
            button55.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    impl1=1;

                    nummofdays = 15;
                    
                    oldsim=1;
                    // Get values from text fields
                    value11 = textField11.getText().isEmpty() ? def_value11 : textField11.getText();
                    value12 = textField12.getText().isEmpty() ? def_value12 : textField12.getText();
                    value13 = textField13.getText().isEmpty() ? def_value13 : textField13.getText();
                    value14 = textField14.getText().isEmpty() ? def_value14 : textField14.getText();
                    value15 = textField15.getText().isEmpty() ? def_value15 : textField15.getText();
                    value16 = textField16.getText().isEmpty() ? def_value16 : textField16.getText();
                    value17 = textField17.getText().isEmpty() ? def_value17 : textField17.getText();
                    value18 = textField18.getText().isEmpty() ? def_value18 : textField18.getText();

                    value34 = textField34.getText().isEmpty() ? def_value34 : textField34.getText();
                    value35 = textField35.getText().isEmpty() ? def_value35 : textField35.getText();
                    value36 = textField36.getText().isEmpty() ? def_value36 : textField36.getText();
                    value37 = textField37.getText().isEmpty() ? def_value37 : textField37.getText();

                    value21 = textField21.getText().isEmpty() ? def_value21 : textField21.getText();
                    value22 = textField22.getText().isEmpty() ? def_value22 : textField22.getText();

                    nummofdays=Integer.valueOf(value21);
                    nummofsim=Integer.valueOf(value22);

                    // Display the values in the result label
                    resultLabel.setText("Result: " + value11 + ", " + value12 + ", " + value13 
                        + ", " + value14+ ", " + value15+ ", " + value16+ ", " + value17+", " + value18);

                    int i3=0;
                    last_m=0;last_s=1;

                    String oopt_s[]=new String[1000];
                    double oopt_sz[]=new double[1000];
                    int os_num[]=new int[100];
                    String os_num_s[]=new String[100];
                    double os_numsz[]=new double[100];

                    int os_l=7,ossz_l=0;
                    oopt_s[0]=value11;
                    oopt_s[1]=value12;
                    oopt_s[2]=value13;
                    oopt_s[3]=Integer.toString(intervall);
                    oopt_s[4]=Integer.toString(tot_intervall);
                    oopt_s[5]=Integer.toString(voltt);
                    oopt_s[6]=Integer.toString(tot_voltt);
                    if(value14.length()>0)
                    {
                        os_num=convertStringToIntArray(value14);
                        os_num_s=convertIntToCh_StrArray(os_num,'C');
                        os_numsz=convertStringToDoubleArray(value34);
                        for(i3=0;i3<os_num_s.length;i3++)
                        { 
                            oopt_s[os_l]=os_num_s[i3];
                            oopt_sz[ossz_l]=(double) os_numsz[i3];
                            os_l++;ossz_l++;
                        }
                    }
                    if(value15.length()>0)
                    {
                        os_num=convertStringToIntArray(value15);
                        os_num_s=convertIntToCh_StrArray(os_num,'P');
                        os_numsz=convertStringToDoubleArray(value35);
                        for(i3=0;i3<os_num_s.length;i3++)
                        { 
                            oopt_s[os_l]=os_num_s[i3];
                            oopt_sz[ossz_l]=(double) os_numsz[i3];
                            os_l++;ossz_l++;
                        }
                    }
                    if(value16.length()>0)
                    {
                        os_num=convertStringToIntArray(value16);
                        os_num_s=convertIntToCh_StrArray(os_num,'M');
                        os_numsz=convertStringToDoubleArray(value36);
                        for(i3=0;i3<os_num_s.length;i3++)
                        { 
                            oopt_s[os_l]=os_num_s[i3];
                            oopt_sz[ossz_l]=(double) os_numsz[i3];
                            os_l++;ossz_l++;
                        }
                    }
                    if(value17.length()>0)
                    {
                        os_num=convertStringToIntArray(value17);
                        os_num_s=convertIntToCh_StrArray(os_num,'N');
                        os_numsz=convertStringToDoubleArray(value37);
                        for(i3=0;i3<os_num_s.length;i3++)
                        { 
                            oopt_s[os_l]=os_num_s[i3];
                            oopt_sz[ossz_l]=(double) os_numsz[i3];
                            os_l++;ossz_l++;
                        }
                        //oopt_s[os_l++]=value17;
                    }
                    oopt_s=ob30.shorti(oopt_s,os_l);
                    ob30.pnt(oopt_s);

                    oopt_sz=ob30.shorti(oopt_sz,ossz_l);
                    ob30.pnt(oopt_sz);

                    System.out.println("nummofdays : "+nummofdays);
                    ob22.maiin(oopt_s,oopt_sz,0,1,nummofdays,nummofsim,oldsim);

                    String xx_data=ob22.tm_x_data;
                    String yy_data[]=new String[nummofsim];
                    yy_data=ob22.tm_y_data;

                    int yy_data_l=nummofsim;//ob22.tm_l;
                    String ns[]=ob22.ns;

                    yy_data=ob30.shorti(yy_data,yy_data_l);
                    double yy_data_d[][]=convertToDoubleArray(yy_data);
                    System.out.println("\nIn default");
                    ob30.pnt(yy_data_d);

                    double xx_data_d[]=convertToDoubleArray(xx_data);
                    ob30.pnt(xx_data_d);

                    System.out.println("\nlrngth xx_data_d "+xx_data_d.length+" yy_data_d "+yy_data_d.length +" , "+(yy_data_d[0]).length);

                    double level_y[]=new double[1];//new double[0];
                    level_y[0]=yy_data_d[0][0];
                    int level_y_l=level_y.length;
                    
                    String nm[]=new String[yy_data.length];
                    for(int i31=0;i31<yy_data.length;i31++)
                        nm[i31]="Combo "+Integer.toString(i31)+", "+Integer.toString(nummofsim);
                    System.out.println("\n check status 30");
                    ob30.pnt(nm);
                    System.out.println("\n check status 31");
                    //example2.updateChartData(xx_data_d, yy_data_d, 0, level_y, 0, nm);
                    System.out.println("\n check status 32");
                    for(int i31=0;i31<yy_data.length;i31++)
                        nm[i31]=Integer.toString(i31);//"NewC "+Integer.toString(i31)+", "+Integer.toString(nummofsim);
                    System.out.println("\n check status 33");
                    //nm=new String[0];
                    ob30.pnt(nm);
                    System.out.println("\n check status 34");
                    ScatterPlotExample example3 = new ScatterPlotExample();
                    example3.updateChartData(xx_data_d, yy_data_d, 0, level_y, level_y_l, nm);
                    System.out.println("\n check status 35");

                    mk_dyn=ob22.mk_dyn;
                    System.out.println("\nMarket dynamics in default : ");
                    ob30.pnt(mk_dyn);

                    ob61.rd_two(fnn,0);
                    mk_data=ob61.new_aa;
                    ob30.pnt(mk_data);

                    ob61.rd(fnn1,0);
                    mk_data_x=ob61.new_a;
                    ob30.pnt(mk_data_x);
                    
                }

            });

        /*
        // Add item listener to the combo box to handle the "Other" option
        comboBox.addItemListener(e -> {
        if ("Other".equals(e.getItem())) {
        textField4.setVisible(true); // Show the fourth text field
        } else {
        textField4.setVisible(false); // Hide the fourth text field
        }
        });
         */

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

    double[][] doubleArray;// = new double[5000000][];
    public double[][] convertToDoubleArray(String[] inputArray, int ll1, int ll2) {

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

    public static int[] convertStringToIntArray(String numbers) {
        String[] strArray = numbers.split(",\\s*"); // Regex to handle spaces after commas
        int[] intArray = new int[strArray.length];

        for (int i = 0; i < strArray.length; i++) {
            intArray[i] = Integer.parseInt(strArray[i]);
        }

        return intArray;

    }

    public static double[] convertStringToDoubleArray(String numbers) {
        String[] strArray = numbers.split(",\\s*"); // Regex to handle spaces after commas
        double[] dbArray = new double[strArray.length];

        for (int i = 0; i < strArray.length; i++) {
            dbArray[i] = Double.parseDouble(strArray[i]);
        }

        return dbArray;

    }

    public static String[] convertIntToCh_StrArray(int numm[], char ch) {
        String[] strArray = new String[numm.length];

        for (int i = 0; i < numm.length; i++) {
            strArray[i] = ch+Integer.toString(numm[i]);
        }

        return strArray;
    }

    public static double findCloserNumber(double number, double num1, double num2) {
        // Calculate the absolute differences
        double diff1 = Math.abs(number - num1);
        double diff2 = Math.abs(number - num2);

        // Compare and return the closer number
        if (diff1 < diff2) {
            return num1;
        } else {
            return num2;
        }
    }

}
