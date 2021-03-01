package chart;

import interfaces.MathFunction;
import functions.FuncForLab1;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

public class Basic {
    public static void main(String[] args) {
        MathFunction f = new FuncForLab1();

        XYSeries series = new XYSeries("function");

        for (double x = 0.0001; x < 3; x += 0.001) {
            series.add(x, f.run(x));
        }

        XYDataset xyDataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory
                .createXYLineChart("y = 10 * x * ln(x) - x^2 / 2", "x", "y",
                        xyDataset,
                        PlotOrientation.VERTICAL,
                        true, true, true);

        JFrame frame = new JFrame("Chart");
        // Помещаем график на фрейм
        frame.getContentPane().add(new ChartPanel(chart));
        frame.setMinimumSize(new Dimension(600, 400));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
