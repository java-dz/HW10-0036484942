package hr.fer.zemris.java.gui.calc.components.operations;

import hr.fer.zemris.java.gui.calc.components.AbstractButton;
import hr.fer.zemris.java.gui.calc.components.Display;

/**
 * This class represents an equal "operation" of the calculator. It executes the
 * operation that was last set for execution.
 *
 * @author Mario Bobic
 */
public class OperationButtonEqual extends AbstractButton {
	/** Serialization UID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an instance of {@code OperationButtonEqual} and associates the
	 * display with this button.
	 * 
	 * @param display display to be associated with this button
	 */
	public OperationButtonEqual(Display display) {
		super(display, "=");
	}

	@Override
	protected void execute(Display display) {
		display.executeOperation();
	}

}
