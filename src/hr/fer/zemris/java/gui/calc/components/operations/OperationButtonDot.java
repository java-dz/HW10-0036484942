package hr.fer.zemris.java.gui.calc.components.operations;

import hr.fer.zemris.java.gui.calc.components.AbstractButton;
import hr.fer.zemris.java.gui.calc.components.Display;

/**
 * This class represents a dot "operation" of the calculator. It adds a decimal
 * point to the value currently stored in the Display object.
 * 
 * @author Mario Bobic
 */
public class OperationButtonDot extends AbstractButton {
	/** Serialization UID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an instance of {@code OperationButtonDot} and associates the
	 * display with this button.
	 * 
	 * @param display display to be associated with this button
	 */
	public OperationButtonDot(Display display) {
		super(display, ".");
	}

	@Override
	protected void execute(Display display) {
		display.addDecimalPoint();
	}

}
