package hr.fer.zemris.java.gui.calc.components.functions;

import hr.fer.zemris.java.gui.calc.components.Display;

/**
 * This class represents the negating function <tt>-x</tt>.
 *
 * @author Mario Bobic
 */
public class FunctionButtonNegate extends AbstractFunction {
	/** Serialization UID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an instance of {@code FunctionButtonNegate} and associates the
	 * display with this button.
	 * 
	 * @param display display to be associated with this button
	 */
	public FunctionButtonNegate(Display display) {
		super(display, "+/-", "+/-");
	}

	@Override
	protected void executeNormal(Display display) {
		display.negate();
	}

	@Override
	protected void executeInversed(Display display) {
		executeNormal(display);
	}

}
