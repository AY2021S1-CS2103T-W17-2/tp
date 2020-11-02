package seedu.address.ui;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class CaloriesGraph extends UiPart<Region> {
    private static final int DAY_TO_DISPLAY = 7;
    private static final String FXML = "CaloriesGraph.fxml";

    private HashMap<String, Integer> dayCalories;

    @FXML
    private Pane pane;

    /**
     * Creates a {@code CaloriesGraph} with the given {@code HashMap}.
     */
    public CaloriesGraph(HashMap<String, Integer> dayCalories) {
        super(FXML);
        this.dayCalories = dayCalories;
        generateGraph();
    }

    /**
     * Generates the graph based on this HashMap that is assigned during initialisation.
     */
    public void generateGraph() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);

        Series<String, Number> series = new Series<String, Number>();
        String[] dates = generatePastDates();
        String date;
        Integer calories;
        for (int i = 0; i < DAY_TO_DISPLAY; i++) {
            //Get X,Y cordinates for a point which corresponds to
            //the date, and the amount of calories burnt on that date
            date = dates[i];
            calories = dayCalories.get(date);
            if (Objects.isNull(calories)) {
                calories = 0;
            }
            series.getData().add(new Data(date, calories));
        }

        lineChart.getData().add(series);
        pane.getChildren().clear();
        pane.getChildren().add(lineChart);
    }

    private String[] generatePastDates() {
        String[] dates = new String[DAY_TO_DISPLAY];
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (int i = 0; i < DAY_TO_DISPLAY; i++) {
            //the dates should be in the order of earlies to latest
            dates[i] = today.minus(Period.ofDays(DAY_TO_DISPLAY - 1 - i)).format(formatter);
        }
        return dates;
    }
}
