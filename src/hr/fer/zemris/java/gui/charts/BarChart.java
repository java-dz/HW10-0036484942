package hr.fer.zemris.java.gui.charts;

import java.util.List;

/**
 * The bar chart model. This class is used as a model to
 * {@linkplain BarChartComponent}.
 *
 * @author Mario Bobic
 */
public class BarChart {
	
	/** A list of XY Values. */
	private List<XYValue> list;
	
	/** Description on x axis. */
	private String descX;
	/** Description on y axis. */
	private String descY;

	/** The starting value on y axis. */
	private int ymin;
	/** The ending value on y axis. */
	private int ymax;
	/** The gap between two y values. */
	private int ygap;
	
	/**
	 * Constructs an instance of {@code BarChart} with the specified values.
	 * 
	 * @param list list of XY Values
	 * @param descX description on x axis
	 * @param descY description on y axis
	 * @param ymin the starting value on y axis
	 * @param ymax the ending value on y axis
	 * @param ygap gap between two y values
	 */
	public BarChart(List<XYValue> list, String descX, String descY, int ymin, int ymax, int ygap) {
		if (ymin > ymax) {
			int tmp = ymin;
			ymin = ymax;
			ymax = tmp;
		}
		
		checkArguments(ymin, ymax, ygap);
		
		this.list = validatelist(list);
		this.descX = descX;
		this.descY = descY;
		this.ymin = ymin;
		this.ymax = ymax;
		this.ygap = ygap;
	}
	
	/**
	 * Checks the arguments of the <tt>y axis</tt>. The <tt>ygap</tt> must be
	 * greater than zero and <tt>ymax - ymin</tt> must be less than
	 * <tt>ygap</tt>.
	 * <p>
	 * If any of the criteria is not satisfied, an
	 * {@linkplain IllegalArgumentException} is thrown.
	 * 
	 * @param ymin the starting value on y axis
	 * @param ymax the ending value on y axis
	 * @param ygap gap between two y values
	 */
	private static void checkArguments(int ymin, int ymax, int ygap) {
		if (ygap <= 0) {
			throw new IllegalArgumentException("ygap must be a positive integer.");
		}
		
		if (ygap > ymax - ymin) {
			throw new IllegalArgumentException("Gap cannot be greater than ymax-ymin.");
		}
	}

	/**
	 * Validates that the specified <tt>list</tt> is not <tt>null</tt> and that
	 * the list does not contain <tt>null</tt> references.
	 * <p>
	 * If the list is <tt>null</tt> or contains <tt>null</tt> references, an
	 * {@linkplain IllegalArgumentException} is thrown.
	 * 
	 * @param list list to be validated
	 * @return the same <tt>list</tt>
	 */
	private static List<XYValue> validatelist(List<XYValue> list) {
		if (list == null) {
			throw new IllegalArgumentException("List must not be null.");
		}
		
		for (XYValue value : list) {
			if (value == null) {
				throw new IllegalArgumentException("List elements must be non-null.");
			}
		}
		
		return list;
	}

	/**
	 * Returns the list of XY Values.
	 * 
	 * @return the list of XY Values
	 */
	public List<XYValue> getList() {
		return list;
	}

	/**
	 * Returns the description on x axis.
	 * 
	 * @return the description on x axis
	 */
	public String getDescX() {
		return descX;
	}

	/**
	 * Returns the description on y axis.
	 * 
	 * @return the description on y axis
	 */
	public String getDescY() {
		return descY;
	}

	/**
	 * Returns the starting value on y axis.
	 * 
	 * @return the starting value on y axis
	 */
	public int getYmin() {
		return ymin;
	}

	/**
	 * Returns the ending value on y axis.
	 * 
	 * @return the ending value on y axis
	 */
	public int getYmax() {
		return ymax;
	}

	/**
	 * Returns the gap between two y values.
	 * 
	 * @return the gap between two y values
	 */
	public int getYgap() {
		return ygap;
	}
	
}
