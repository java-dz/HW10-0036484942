package hr.fer.zemris.java.gui.calc.components.methods;

import hr.fer.zemris.java.gui.calc.components.AbstractButton;
import hr.fer.zemris.java.gui.calc.components.Display;

/**
 * This class represents a <tt>clear</tt> method of the calculator.
 *
 * @author Mario Bobic
 */
public class MethodButtonClr extends AbstractButton {
	/** Serialization UID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an instance of {@code MethodButtonClr} and associates the
	 * display with this button.
	 * 
	 * @param display display to be associated with this button
	 */
	public MethodButtonClr(Display display) {
		super(display, "clr");
	}

	@Override
	protected void execute(Display display) {
		display.clear();
	}

}
