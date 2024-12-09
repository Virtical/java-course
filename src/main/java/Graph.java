import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Graph extends JFrame {

    public Graph(Map<String, Integer> map){
        init(map);
    }

    private void init(Map<String, Integer> map){
        CategoryDataset dataset = createDataset(map);
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        int DEFAULT_PADDING = 15;
        chartPanel.setBorder(BorderFactory.createEmptyBorder(DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING));
        chartPanel.setBackground(Color.WHITE);
        add(chartPanel);

        pack();
        setTitle("Количество книг по типам");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

    private JFreeChart createChart(CategoryDataset dataset){
        JFreeChart chart = ChartFactory.createBarChart(
                "Количество книг по типам",
                "",
                "Количество книг",
                dataset);

        var plot = chart.getCategoryPlot();

        var renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(0, 128, 0));
        plot.setBackgroundPaint(Color.WHITE);

        return chart;
    }

    private CategoryDataset createDataset(Map<String, Integer> map)
    {
        var dataset = new DefaultCategoryDataset();
        map.forEach((key, value) -> {
            dataset.setValue(value, "Brands", key);
        });

        return dataset;
    }
}
