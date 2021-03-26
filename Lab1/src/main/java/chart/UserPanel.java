package chart;

import interfaces.MathFunction;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import search.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class UserPanel {
    private final MathFunction function;
    private final JFrame frame;
    private final JMenu methods;
    private final JTextField leftBorderField;
    private final JTextField rightBorderField;
    private final JTextField epsilonField;
    private final JTextField deltaField;
    private final JButton runButton;
    private final JTextField resultField;
    private ChartPanel chartPanel = null;
    private String method = null;

    public UserPanel(MathFunction function) {
        this.function = function;
        frame = new JFrame();
        methods = new JMenu("методы");

        JMenuBar menuBar = new JMenuBar();

        createMethodsMenu();
        menuBar.add(methods);

        leftBorderField = field("left border");
        menuBar.add(leftBorderField);

        rightBorderField = field("right border");
        menuBar.add(rightBorderField);

        epsilonField = field("epsilon");
        menuBar.add(epsilonField);

        deltaField = field("delta");
        menuBar.add(deltaField);

        runButton = new JButton("run");
        addListenerToRunButton();
        menuBar.add(runButton);

        resultField = field("result");
        resultField.setEnabled(false);
        menuBar.add(resultField);

        // Подключаем меню к интерфейсу приложения
        frame.setJMenuBar(menuBar);
    }

    private void createMethodsMenu() {
        JMenuItem dichotomy = new JMenuItem("метод дихотомии");
        dichotomy.addActionListener(arg -> {
            method = "DichotomySearch";
            methods.setText("метод дихотомии");
        });

        JMenuItem gold = new JMenuItem("метод золотого сечения");
        gold.addActionListener(arg -> {
            method = "GoldenRatioSearch";
            methods.setText("метод золотого сечения");
        });

        JMenuItem fibonacci = new JMenuItem("метод Фибоначчи");
        fibonacci.addActionListener(arg -> {
            method = "FibonacciSearch";
            methods.setText("метод Фибоначчи");
        });

        JMenuItem parabol = new JMenuItem("метод парабол");
        parabol.addActionListener(arg -> {
            method = "ParabolSearch";
            methods.setText("метод парабол");
        });

        JMenuItem brent = new JMenuItem("метод Брента");
        brent.addActionListener(arg -> {
            method = "BrentSearch";
            methods.setText("метод Брента");
        });

        methods.add(dichotomy);
        methods.addSeparator();
        methods.add(gold);
        methods.addSeparator();
        methods.add(fibonacci);
        methods.addSeparator();
        methods.add(parabol);
        methods.addSeparator();
        methods.add(brent);
    }

    private JTextField field(String name) {
        JTextField field = new JTextField(name);
        field.setMaximumSize(new Dimension(200, 50));
        field.setToolTipText(name);
        field.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                field.setText("");
            }

            public void focusLost(FocusEvent e) {
                if (!field.getText().isEmpty()) {
                    isValidDouble(field);
                } else {
                    field.setText(name);
                }
            }
        });
        return field;
    }

    private void addListenerToRunButton() {
        runButton.addActionListener(actionEvent -> {
            if (method != null && isValidDouble(leftBorderField) && isValidDouble(rightBorderField) && isValidDouble(epsilonField)) {
                double left = Double.parseDouble(leftBorderField.getText());
                double right = Double.parseDouble(rightBorderField.getText());
                double epsilon = Double.parseDouble(epsilonField.getText());

                if (epsilon <= 0) {
                    epsilonField.setBackground(Color.RED);
                } else if (method.equals("DichotomySearch") && isValidDouble(deltaField)) {
                    double delta = Double.parseDouble(deltaField.getText());
                    if (delta <= 0) {
                        deltaField.setBackground(Color.RED);
                    } else {
                        resultField.setText(Double.toString(
                                new DichotomySearch(function, left, right, epsilon, delta, this).searchMinimum()
                        ));
                    }
                } else if (method.equals("GoldenRatioSearch")) {
                    resultField.setText(Double.toString(
                            new GoldenRatioSearch(function, left, right, epsilon, this).searchMinimum()
                    ));
                } else if (method.equals("FibonacciSearch")) {
                    resultField.setText(Double.toString(
                            new FibonacciSearch(function, left, right, epsilon, this).searchMinimum()
                    ));
                } else if (method.equals("ParabolSearch")) {
                    resultField.setText(Double.toString(
                            new ParabolSearch(function, left, right, epsilon, this).searchMinimum()
                    ));
                } else {
                    resultField.setText(Double.toString(
                            new BrentSearch(function, left, right, epsilon, this).searchMinimum()
                    ));
                }
            }
        });
    }

    public void showChart(JFreeChart chart) {
        if (chartPanel == null) {
            chartPanel = new ChartPanel(chart);
            chartPanel.setBackground(Color.BLACK);
            frame.add(chartPanel);
            frame.setVisible(true);
        } else {
            chartPanel.setChart(chart);
        }
    }

    public void show() {
        frame.setMinimumSize(new Dimension(600, 400));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    private boolean isValidDouble(JTextField field) {
        try {
            Double.parseDouble(field.getText());
            field.setBackground(Color.WHITE);
            return true;
        } catch (NumberFormatException e) {
            field.setBackground(Color.RED);
            return false;
        }
    }

}
