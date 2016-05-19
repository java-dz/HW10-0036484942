package hr.fer.zemris.java.gui.calc.components.operations;

import hr.fer.zemris.java.gui.calc.components.AbstractButton;
import hr.fer.zemris.java.gui.calc.components.Display;

/**
 * This class implements the {@linkplain Operation} interface and represents the
 * multiplication operation of the calculator.
 * 
 * @author Mario Bobic
 */
public class OperationButtonMul extends AbstractButton implements Operation {
	/** Serialization UID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an instance of {@code OperationButtonMul} and associates the
	 * display with this button.
	 * 
	 * @param display display to be associated with this button
	 */
	public OperationButtonMul(Display display) {
		super(display, "*");
	}

	@Override
	protected void execute(Display display) {
		display.setOperation(this);
	}
	
	@Override
	public double apply(double left, double right) {
		return left * right;
	}

}
