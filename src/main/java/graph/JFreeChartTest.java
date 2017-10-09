package graph;
import data.VO.MonthlyStockQuote;
import data.VO.StockPrice;
import data.consumer.HTTP;
import data.http.AlphaAdvantage;
import data.parser.AlphaAdvantageParser;
import database.MongoDB;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import static data.utils.Constants.MICROSFT_TICKER;


/**
 * Created by Ameer on 10/6/17.
 */
// example taken from https://www.javascan.com/622/jfreechart-time-series-chart-example
public class JFreeChartTest extends ApplicationFrame {
    public JFreeChartTest(String title) {
        super(title);
        XYDataset dataset = createDataset2();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanel.setMouseZoomable(true, false);
        setContentPane(chartPanel);
    }

    private static JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Open and Close for Microsoft",  // title
                "Monthy",             // x-axis label
                "Price in Dollars",   // y-axis label
                dataset,            // data
                true,               // create legend?
                true,               // generate tooltips?
                false               // generate URLs?
        );

        chart.setBackgroundPaint(Color.white);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(1.0, 1.0, 1.0, 1.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
        return chart;

    }


    public static XYDataset createDataset2() {
        TimeSeries s1 = new TimeSeries("Open", Month.class);
        TimeSeries s2 = new TimeSeries("Close", Month.class);
        AlphaAdvantage a = new AlphaAdvantage("demo", new HTTP(), new AlphaAdvantageParser(), new MongoDB());
        MonthlyStockQuote msq = a.getMonthlyStockQuote(MICROSFT_TICKER);
        for (Map.Entry<Date, StockPrice> entry : msq.getQuotes().entrySet()) {
            Date date =entry.getKey(); // your date
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            System.out.println(date.toString());
            System.out.println("Month = " + month + " Year "+ year);
            s1.add(new Month(month+1, year), new Double(entry.getValue().getOpen()));
            s2.add(new Month(month+1, year), new Double(entry.getValue().getClose()));

        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);
        dataset.setDomainIsPointsInTime(true);
        return dataset;
    }

    private static XYDataset createDataset() {

        TimeSeries s1 = new TimeSeries("L&G European Index Trust", Month.class);
        s1.add(new Month(2, 2001), 181.8);
        s1.add(new Month(3, 2001), 167.3);
        s1.add(new Month(4, 2001), 153.8);
        s1.add(new Month(5, 2001), 167.6);
        s1.add(new Month(6, 2001), 158.8);
        s1.add(new Month(7, 2001), 148.3);
        s1.add(new Month(8, 2001), 153.9);
        s1.add(new Month(9, 2001), 142.7);
        s1.add(new Month(10, 2001), 123.2);
        s1.add(new Month(11, 2001), 131.8);
        s1.add(new Month(12, 2001), 139.6);
        s1.add(new Month(1, 2002), 142.9);
        s1.add(new Month(2, 2002), 138.7);
        s1.add(new Month(3, 2002), 137.3);
        s1.add(new Month(4, 2002), 143.9);
        s1.add(new Month(5, 2002), 139.8);
        s1.add(new Month(6, 2002), 137.0);
        s1.add(new Month(7, 2002), 132.8);

        TimeSeries s2 = new TimeSeries("L&G UK Index Trust", Month.class);
        s2.add(new Month(2, 2001), 129.6);
        s2.add(new Month(3, 2001), 123.2);
        s2.add(new Month(4, 2001), 117.2);
        s2.add(new Month(5, 2001), 124.1);
        s2.add(new Month(6, 2001), 122.6);
        s2.add(new Month(7, 2001), 119.2);
        s2.add(new Month(8, 2001), 116.5);
        s2.add(new Month(9, 2001), 112.7);
        s2.add(new Month(10, 2001), 101.5);
        s2.add(new Month(11, 2001), 106.1);
        s2.add(new Month(12, 2001), 110.3);
        s2.add(new Month(1, 2002), 111.7);
        s2.add(new Month(2, 2002), 111.0);
        s2.add(new Month(3, 2002), 109.6);
        s2.add(new Month(4, 2002), 113.2);
        s2.add(new Month(5, 2002), 111.6);
        s2.add(new Month(6, 2002), 108.8);
        s2.add(new Month(7, 2002), 101.6);

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);
        dataset.setDomainIsPointsInTime(true);
        return dataset;

    }




    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {

        JFreeChartTest demo = new JFreeChartTest(
                "Time Series Chart Demo 1"
        );
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
