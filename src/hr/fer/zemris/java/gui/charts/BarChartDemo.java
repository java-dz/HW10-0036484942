package hr.fer.zemris.java.gui.charts;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * This class is a {@linkplain JFrame} which demonstrates the usage of
 * {@linkplain BarChart} in association with the {@linkplain BarChartComponent}.
 * <p>
 * The program expects one command-line argument: path to file containing bar
 * chart arguments.
 *
 * @author Mario Bobic
 */
public class BarChartDemo extends JFrame {
	/** Serialization UID. */
	private static final long serialVersionUID = 1L;
	
	/** Expected number of lines in a file. */
	private static final int NUM_LINES = 6;
	
	/**
	 * Constructs and initializes this frame with GUI components.
	 * 
	 * @param path path to file from where the chart arguments were read
	 * @param chart chart to be drawn
	 */
	public BarChartDemo(BarChart chart, Path path) {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Bar Chart Demo");
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screen.width / 3, screen.height / 2);
		
		initGUI(chart, path);
		
		setLocationRelativeTo(null);
	}
	
	/**
	 * Initializes the GUI by adding the <tt>BorderLayout</tt> manager to the
	 * content pane and adding the <tt>chart</tt>.
	 * 
	 * @param path path to file from where the chart arguments were read
	 * @param chart chart to be drawn
	 */
	private void initGUI(BarChart chart, Path path) {
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		JLabel pathLabel = new JLabel(path.toString(), JLabel.CENTER);
		BarChartComponent chartComponent = new BarChartComponent(chart);
		
		cp.add(pathLabel, BorderLayout.PAGE_START);
		cp.add(chartComponent, BorderLayout.CENTER);
	}
	
	/**
	 * Program entry point.
	 * 
	 * @param args not used in this program
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Expected one argument: Path to file.");
			return;
		}
		
		// possibility of InvalidPathException on Windows.
		Path path = Paths.get(args[0]);
		if (!Files.isRegularFile(path)) {
			System.err.println("File " + path + " can not be found.");
			return;
		}
		
		List<String> arguments = readFileArguments(path);
		
		BarChart chart = createChart(arguments);
		
		SwingUtilities.invokeLater(() -> {
			new BarChartDemo(chart, path).setVisible(true);
		});
	}

	/**
	 * Reads all lines as the arguments from file specified by the <tt>path</tt>
	 * parameter and returns a list of lines as strings.
	 * 
	 * @param path path to file
	 * @return list of lines as strings
	 */
	private static List<String> readFileArguments(Path path) {
		try {
			return Files.readAllLines(path, StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.exit(-1);
			return null;
		}
	}

	/**
	 * Creates a {@linkplain BarChart} object using the specified list of
	 * <tt>arguments</tt>.
	 * 
	 * @param arguments arguments that will be parsed and passed to BarChart
	 * @return a BarChart object with the specified <tt>arguments</tt>
	 */
	private static BarChart createChart(List<String> arguments) {
		if (arguments.size() < NUM_LINES) {
			System.err.println(
					"Chart expected " + NUM_LINES + " arguments. "
					+ "You provided: " + arguments.size());
			System.exit(-1);
		}
		
		String descX = arguments.get(0);
		String descY = arguments.get(1);
		try {
			List<XYValue> xyValues = getXYValues(arguments.get(2));
			int ymin = Integer.parseInt(arguments.get(3));
			int ymax = Integer.parseInt(arguments.get(4));
			int ygap = Integer.parseInt(arguments.get(5));

			return new BarChart(xyValues, descX, descY, ymin, ymax, ygap);
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
			System.exit(-1);
			return null;
		}
	}

	/**
	 * Returns a list of {@linkplain XYValue} objects parsed from the specified
	 * string <tt>s</tt>. The XYValues in the string must be delimited with a
	 * whitespace character. For further parsing, read the
	 * {@linkplain XYValue#parse(String)} documentation.
	 * 
	 * @param s string to be parsed as a list of XYValue objects
	 * @return a list of XYValue objects parsed from the specified string s
	 * @throws IllegalArgumentException if s does not contain two members
	 * @throws NumberFormatException if members can not be parsed as integers
	 */
	private static List<XYValue> getXYValues(String s) {
		List<XYValue> list = new ArrayList<>();
		
		String[] values = s.split("\\s+");
		for (String value : values) {
			list.add(XYValue.parse(value));
		}
		
		return list;
	}

}
