package tn.esprit.pidev.views;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.pidev.entities.Planning;
import tn.esprit.pidev.services.PlanningService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class AdminStatsPlanningForm extends Form {
    Form current;
    PlanningService planningService = new PlanningService();

    public AdminStatsPlanningForm(Form previous) {
        current = this;
        setTitle("Stats Planning");
        setLayout(BoxLayout.y());
        int showNumber = 0;
        int movieNumber = 0;

        ArrayList<Planning> planningArrayList = planningService.showAll();
        ;
        for (Planning planning : planningArrayList) {
            if (planning.getTypeEvent().equals("Spectacle")) {
                showNumber++;
            /*else if(planning.getTypeEvent().equals("Film")){
                movieNumber++;
            }*/
            } else {
                movieNumber++;
            }
        }

        double[] values = new double[]{showNumber, movieNumber};
        // Set up the renderer
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(160);
        renderer.setChartTitle("Statistique");
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer simpleSeriesRenderer = renderer.getSeriesRendererAt(0);
        simpleSeriesRenderer.setGradientEnabled(true);
        simpleSeriesRenderer.setGradientStart(0, ColorUtil.CYAN);
        simpleSeriesRenderer.setGradientStop(0, ColorUtil.YELLOW);
        simpleSeriesRenderer.setHighlighted(true);
        // Create the chart ... pass the values and renderer to the chart object.
        PieChart pieChart = new PieChart(buildCategoryDataset("Project budget", values), renderer);
        // Wrap the chart in a Component so we can add it to a form
        ChartComponent chartComponent = new ChartComponent(pieChart);
        add(chartComponent);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(48  );
        //renderer.setLegendTextSize(64);
        renderer.setLabelsColor(ColorUtil.BLUE);
        renderer.setAxesColor(ColorUtil.BLUE);

        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
        series.add("Spectacle", values[0]);
        series.add("Films", values[1]);
        return series;
    }
}
