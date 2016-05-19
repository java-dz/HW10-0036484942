package hr.fer.zemris.java.gui.calc.components.functions;

import hr.fer.zemris.java.gui.calc.components.Display;

/**
 * This class represents the natural logarithm function <tt>ln(x)</tt> and its
 * inverse <tt>e^x</tt>.
 *
 * @author Mario Bobic
 */
public class FunctionButtonLn extends AbstractFunction {
	/** Serialization UID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an instance of {@code FunctionButtonLn} and associates the
	 * display with this button.
	 * 
	 * @param display display to be associated with this button
	 */
	public FunctionButtonLn(Display display) {
		super(display, "ln", "e^x");
	}

	@Override
	protected void executeNormal(Display display) {
		display.setValue(Math.log(display.getValue()));
	}

	@Override
	protected void executeInversed(Display display) {
		display.setValue(Math.pow(Math.E, display.getValue()));
	}

}
