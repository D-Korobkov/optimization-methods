package chart;

import interfaces.MathFunction;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.resources.JFreeChartResources;
import org.jfree.chart.ui.RectangleAnchor;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.LinkedList;
import java.util.List;

import java.awt.*;
import java.util.Random;

public class Basic {
    private final static Random RANDOMIZER = new Random();
    private final XYSeriesCollection dataset;
    private final List<ValueMarker> markers;
    private final UserPanel userPanel;

    public Basic(UserPanel userPanel) {
        dataset = new XYSeriesCollection();
        markers = new LinkedList<>();
        this.userPanel = userPanel;
    }

    public void addNewSection(double l, double r, int iteration) {
        Color color = new Color(
                RANDOMIZER.nextInt(255),
                RANDOMIZER.nextInt(255),
                RANDOMIZER.nextInt(255),
                255
        );
        ValueMarker lMark = createMarker(l, "left " + iteration, color);
        ValueMarker rMark = createMarker(r, "right " + iteration, color);
        markers.add(lMark);
        markers.add(rMark);
    }

    private ValueMarker createMarker(double x, String name, Color color) {
        ValueMarker mark = new ValueMarker(x);
        mark.setLabel(name);
        mark.setLabelAnchor(RectangleAnchor.CENTER);
        mark.setStroke(new BasicStroke(1.5f));
        mark.setPaint(color);
        return mark;
    }

    public void drawChart(MathFunction function, double leftBorder, double rightBorder) {
        dataset.addSeries(createFunctionSeries(function, leftBorder, rightBorder));
        addNewSection(leftBorder, rightBorder, 0);
        userPanel.showChart(createChart(dataset));
    }

    private JFreeChart createChart(XYSeriesCollection dataset) {
        JFreeChart chart = ChartFactory
                .createXYLineChart("y = 10 * x * ln(x) - x^2 / 2", "x", "y", dataset);
        XYPlot plot = chart.getXYPlot();
        for (ValueMarker marker : markers) {
            plot.addDomainMarker(marker);
        }
        return chart;
    }

    private static XYSeries createFunctionSeries(MathFunction function, double leftBorder, double rightBorder) {
        return createFunctionSeries(function, leftBorder, rightBorder, "function");
    }

    public void addParabol(double a0, double a1, double a2, double x1, double x2, double left, double right, int number) {
        MathFunction function = x -> a0 + a1 * (x - x1) + a2 * (x - x1) * (x - x2);
        dataset.addSeries(createFunctionSeries(function, left, right, Integer.toString(number)));
    }

    private static XYSeries createFunctionSeries(MathFunction function, double left, double right, String key) {
        XYSeries series = new XYSeries(key);
        double step = (right - left) / 100;
        for (double x = left; x < right + step; x += step) {
            series.add(x, function.run(x));
        }
        return series;
    }
}
