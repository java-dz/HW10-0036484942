package hr.fer.zemris.java.gui.calc.components.functions;

import hr.fer.zemris.java.gui.calc.components.Display;

/**
 * This class represents the tangent function <tt>tan(x)</tt> and its
 * inverse <tt>arctan(x)</tt>.
 *
 * @author Mario Bobic
 */
public class FunctionButtonTan extends AbstractFunction {
	/** Serialization UID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an instance of {@code FunctionButtonTan} and associates the
	 * display with this button.
	 * 
	 * @param display display to be associated with this button
	 */
	public FunctionButtonTan(Display display) {
		super(display, "tan", "arctan");
	}

	@Override
	protected void executeNormal(Display display) {
		display.setValue(Math.tan(display.getValue()));
	}

	@Override
	protected void executeInversed(Display display) {
		display.setValue(Math.atan(display.getValue()));
	}

}
