package hr.fer.zemris.java.gui.charts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.AffineTransform;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 *
 * @author Mario Bobic
 */
public class BarChartComponent extends JComponent {
    /** Serialization UID. */
    private static final long serialVersionUID = 1L;

    /** Gap between axis description and values. */
    private static final int GAP_DESC_VALUE = 10;
    /** Gap between values and axes line. */
    private static final int GAP_VALUE_AXIS = 6;

    /** Orange color of the filled chart. */
    private static final Color CHART_COLOR = new Color(244, 119, 72);
    /** Beige color of the graph borders. */
    private static final Color BORDER_COLOR = new Color(240, 224, 194);

    /** Border to be set on grid for drawing the chart. */
    private static final Border MATTE_BORDER = BorderFactory.createMatteBorder(0, 0, 0, 1, BORDER_COLOR);
    /** Border to be set on grid where the chart is not present. */
    private static final Border LINE_BORDER = BorderFactory.createLineBorder(BORDER_COLOR, 1);

    /** The chart model. */
    private BarChart chart;

    /** Number of x values. */
    private int amountX;
    /** Number of y values. */
    private int amountY;

    /** The updated information on the ending value on y axis. */
    private int ymax;

    /**
     * Constructs an instance of {@code BarChartComponent} with the specified
     * <tt>chart</tt> as model.
     *
     * @param chart chart to be set as model
     */
    public BarChartComponent(BarChart chart) {
        if (chart == null) {
            throw new IllegalArgumentException("Chart must not be null.");
        }

        this.chart = chart;
        amountX = chart.getList().size();
        setYComponents(chart);

        initComponent();
    }

    /**
     * Sets the <tt>ymax</tt> and <tt>amountY</tt> components to the updated
     * value. The value considers gap size and minimum y of the chart model.
     *
     * @param chart the chart model
     */
    private void setYComponents(BarChart chart) {
        int ymax = chart.getYmax();
        int ymin = chart.getYmin();
        int ygap = chart.getYgap();

        while ((ymax - ymin) % ygap != 0) {
            ymax++;
        }

        this.ymax = ymax;
        amountY = (ymax - ymin) / ygap + 1;
    }

    /**
     * Initializes this component with axes and grid.
     */
    private void initComponent() {
        setLayout(new BorderLayout(GAP_VALUE_AXIS, GAP_VALUE_AXIS));

        JPanel xAxis = getXAxis();
        JPanel yAxis = getYAxis();
        JPanel grid = getGrid();

        add(xAxis, BorderLayout.PAGE_END);
        add(yAxis, BorderLayout.LINE_START);
        add(grid, BorderLayout.CENTER);
    }

    /**
     * Returns a JPanel containing all information of the x axis.
     *
     * @return a JPanel containing all information of the x axis
     */
    private JPanel getXAxis() {
        JPanel onTop = new JPanel(new BorderLayout(33, 0));
        JPanel panel = new JPanel(new GridLayout(2, 1, 0, GAP_DESC_VALUE));

        onTop.add(new JPanel(), BorderLayout.LINE_START);
        onTop.add(panel, BorderLayout.CENTER);

        List<XYValue> xyList = chart.getList();
        JPanel valuesPanel = new JPanel(new GridLayout(1, amountX));

        for (XYValue value : xyList) {
            JLabel valueLabel = new JLabel(String.valueOf(value.x), JLabel.CENTER);
            valuesPanel.add(valueLabel);
        }

        JLabel xName = new JLabel(chart.getDescX(), JLabel.CENTER);

        panel.add(valuesPanel);
        panel.add(xName);

        return onTop;
    }

    /**
     * Returns a JPanel containing all information of the y axis.
     *
     * @return a JPanel containing all information of the y axis
     */
    private JPanel getYAxis() {
        JPanel panel = new JPanel(new GridLayout(1, 2, GAP_DESC_VALUE, 0));

        JComponent yName = new JComponent() {
            /** Serialization UID. */
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                AffineTransform defaultTransform = g2d.getTransform();

                AffineTransform at = AffineTransform.getQuadrantRotateInstance(3);
                g2d.setTransform(at);

                Dimension dim = getSize();
                FontMetrics fm = g.getFontMetrics();
                int w = fm.stringWidth(chart.getDescY());
                int h = fm.getAscent();

                g2d.drawString(chart.getDescY(), -(dim.height - (dim.height-w)/2), h);
                g2d.setTransform(defaultTransform);
            }
        };

        int ymax = chart.getYmax();
        int ymin = chart.getYmin();
        int ygap = chart.getYgap();

        JPanel valuesPanel = new JPanel(new GridLayout(amountY, 1));

        for (int y = ymax; y >= ymin; y -= ygap) {
            JLabel valueLabel = new JLabel(String.valueOf(y), JLabel.CENTER);
            valuesPanel.add(valueLabel);
        }

        panel.add(yName);
        panel.add(valuesPanel);

        return panel;
    }

    /**
     * Returns a JPanel containing the grid, with borders set to
     * {@linkplain #LINE_BORDER} for empty graph parts and
     * {@linkplain #MATTE_BORDER} for filled graph parts.
     *
     * @return a JPanel containing the grid
     */
    private JPanel getGrid() {
        JPanel panel = new JPanel(new GridLayout(amountY, amountX));

        List<XYValue> xyList = chart.getList();
        int ygap = chart.getYgap();
        int y = ymax;
        for (int row = 0; row < amountY; row++) {
            for (int column = 0; column < amountX; column++) {
                XYValue value = xyList.get(column);
                JPanel p = new JPanel();

                if (value.y > y) {
                    p.setOpaque(true);
                    p.setBackground(CHART_COLOR);
                    p.setBorder(MATTE_BORDER);
                } else {
                    p.setBorder(LINE_BORDER);
                }

                panel.add(p);
            }
            y -= ygap;
        }

        return panel;
    }

}
