package hr.fer.zemris.java.gui.calc.components.methods;

import javax.swing.JCheckBox;

import hr.fer.zemris.java.gui.calc.components.AbstractButton;
import hr.fer.zemris.java.gui.calc.components.Display;

/**
 * This class represents a <tt>inverse</tt> method of the calculator.
 * <p>
 * It contains a {@linkplain JCheckBox} object that is checked if the function
 * inverse is on, and unchecked if the inverse is off.
 *
 * @author Mario Bobic
 */
public class MethodButtonInv extends AbstractButton {
	/** Serialization UID. */
	private static final long serialVersionUID = 1L;
	
	/** The check box used for indicating the inverse. */
	private JCheckBox checkBox;
	
	/**
	 * Constructs an instance of {@code MethodButtonInv} and associates the
	 * display with this button.
	 * 
	 * @param display display to be associated with this button
	 */
	public MethodButtonInv(Display display) {
		super(display, "Inv");
		
		checkBox = initializeCheckBox();
		add(checkBox);
		
		display.setInverseCheckBox(checkBox);
	}

	/**
	 * Initializes the check box by adding a mouse listener to it and removing
	 * the default background.
	 * 
	 * @return an initialized check box
	 */
	private JCheckBox initializeCheckBox() {
		checkBox = new JCheckBox();
		checkBox.addMouseListener(getMouseListeners()[1]);
		
		checkBox.setBackground(null);
		
		return checkBox;
	}

	@Override
	protected void execute(Display display) {
		display.toggleInverse();
		checkBox.doClick();
	}
}
