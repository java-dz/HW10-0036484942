package hr.fer.zemris.java.gui.layouts;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * A calc layout lays out a container, arranging and resizing its components to
 * fit in <tt>5*7 - 4 = 31</tt> regions.
 * <p>
 * This layout represents a calculator layout, with 30 places for calculator
 * "button" and one place for the calculator display.
 *
 * @author Mario Bobic
 */
public class CalcLayout implements LayoutManager2 {
	
	/** Horizontal gap between components, expressed in pixels. */
	private int hgap;
	
	/** Vertical gap between components, expressed in pixels. */
	private int vgap;

	/** The fixed amount of rows in the layout. */
	private static final int rows = 5;

	/** The fixed amount of columns in the layout. */
	private static final int cols = 7;
	
	/** Width of the first element in the layout. */
	private static final int FIRST_WIDTH = 5;
	
	/** Map of components as keys having RCPositions as values. */
	private Map<Component, RCPosition> compMap;
	
	/** Maximum dimension of a component, for determining ratio. */
	private Dimension maxDimension;
	
	/**
	 * Constructs an instance of CalcLayout with no gaps between components.
	 */
	public CalcLayout() {
		this(0, 0);
	}

	/**
	 * Constructs an instance of CalcLayout with the specified gap between
	 * components.
	 * <p>
	 * Both horizontal and vertical gaps are specified by the <tt>gap</tt>
	 * parameter, expressed in pixels.
	 * 
	 * @param gap the horizontal and vertical gap in pixels
	 */
	public CalcLayout(int gap) {
		this(gap, gap);
	}

	/**
	 * Constructs an instance of CalcLayout with the specified gaps between
	 * components.
	 * <p>
	 * The horizontal gap is specified by <tt>hgap</tt> and the vertical gap is
	 * specified by <tt>vgap</tt>, both are expressed in pixels.
	 * 
	 * @param hgap the horizontal gap in pixels
	 * @param vgap the vertical gap in pixels
	 */
	public CalcLayout(int hgap, int vgap) {
		this.hgap = hgap;
		this.vgap = vgap;

		compMap = new HashMap<>();
		maxDimension = new Dimension();
	}

	@Override
	public void addLayoutComponent(String name, Component comp) {
	}

	@Override
	public void removeLayoutComponent(Component comp) {
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		return layoutSize(parent, (component) -> component.getPreferredSize());
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		return layoutSize(parent, (component) -> component.getMinimumSize());
	}
	
	/**
	 * Calculates the minimum, maximum or preferred size dimensions for the
	 * specified container, given the components it contains, where the size
	 * type is determined by the specified <tt>function</tt>.
	 * 
	 * @param parent the parent container
	 * @param function function that determines the size type to fetch
	 * @return the minimum, maximum or preferred size dimension
	 */
	private Dimension layoutSize(Container parent, Function<Component, Dimension> function) {
		Insets insets = parent.getInsets();
		int ncomponents = parent.getComponentCount();

		int w = 0;
		int h = 0;
		for (int i = 0; i < ncomponents; i++) {
			Component comp = parent.getComponent(i);
			Dimension d = function.apply(comp); // get min, max, preferred size
			if (w < d.width) {
				w = d.width;
			}
			if (h < d.height) {
				h = d.height;
			}
		}
		return new Dimension(insets.left + insets.right + cols * w + (cols - 1) * hgap,
							 insets.top + insets.bottom + rows * h + (rows - 1) * vgap);
	}

	@Override
	public void layoutContainer(Container parent) {
		Insets insets = parent.getInsets();
		
		// idea from JDK GridLayout.class, starting line 421
		int totalGapsWidth = (cols - 1) * hgap;
		int widthWOInsets = parent.getWidth() - (insets.left + insets.right);
		int widthOnComponent = (widthWOInsets - totalGapsWidth) / cols;

		int totalGapsHeight = (rows - 1) * vgap;
		int heightWOInsets = parent.getHeight() - (insets.top + insets.bottom);
		int heightOnComponent = (heightWOInsets - totalGapsHeight) / rows;
		
		compMap.forEach((component, position) -> {
			int y = (position.row-1) * (heightOnComponent + vgap) + insets.top;
			int x = (position.column-1) * (widthOnComponent + hgap) + insets.left;
			if (position.row == 1 && position.column == 1) {
				component.setBounds(x, y, widthOnComponent * FIRST_WIDTH + hgap * (FIRST_WIDTH-1), heightOnComponent);
			} else {
				component.setBounds(x, y, widthOnComponent, heightOnComponent);
			}
			
		});
	}

	@Override
	public void addLayoutComponent(Component comp, Object constraints) {
		RCPosition rcPosition;
		
		if (constraints instanceof RCPosition) {
			rcPosition = (RCPosition) constraints;
		} else if (constraints instanceof String) {
			rcPosition = RCPosition.parse((String) constraints);
		} else {
			throw new IllegalArgumentException(
				"Unable to add " + comp + " to layout: "
				+ "constraints must be either a RCPosition or String."
			);
		}
		
		validateConstraints(rcPosition);
		compMap.put(comp, rcPosition);
		determineMaxDimension(comp.getPreferredSize());
	}
	
	/**
	 * Determines the max dimension by comparing the specified dimension
	 * <tt>dim</tt> with the class field <tt>maxDimension</tt>.
	 * 
	 * @param dim dimension to be compared to current max dimension
	 */
	private void determineMaxDimension(Dimension dim) {
		if (dim.width > maxDimension.width) {
			maxDimension.width = maxDimension.height = dim.width;
		}
		if (dim.height > maxDimension.height) {
			maxDimension.height = maxDimension.width = dim.height;
		}
	}

	/**
	 * Validates the specified <tt>constraints</tt> by testing if the RC
	 * position is valid. If the map already contains the specified constraints,
	 * an {@linkplain IllegalArgumentException} is thrown.
	 * 
	 * @param constraints constraints to be validated
	 */
	private void validateConstraints(RCPosition constraints) {
		if (constraints.row <= 0 || constraints.column <= 0
			|| constraints.row > rows || constraints.column > cols) {
			throw new IllegalArgumentException("Invalid constraints: " + constraints);
		}
		
		if (constraints.row == 1 && (constraints.column >= 2 && constraints.column <= FIRST_WIDTH)) {
			throw new IllegalArgumentException("Cannot add component on positions 2 to " + FIRST_WIDTH + " in row 1.");
		}
		
		if (compMap.containsValue(constraints)) {
			throw new IllegalArgumentException("Component at " + constraints + " already exists: " + compMap.get(constraints));
		}
	}

	@Override
	public Dimension maximumLayoutSize(Container target) {
		return layoutSize(target, (component) -> component.getMaximumSize());
	}

	@Override
	public float getLayoutAlignmentX(Container target) {
		return 0.5f;
	}

	@Override
	public float getLayoutAlignmentY(Container target) {
		return 0.5f;
	}

	@Override
	public void invalidateLayout(Container target) {
	}

}
