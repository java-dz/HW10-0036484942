package hr.fer.zemris.java.gui.calc.components.methods;

import hr.fer.zemris.java.gui.calc.components.AbstractButton;
import hr.fer.zemris.java.gui.calc.components.Display;

/**
 * This class represents a <tt>reset</tt> method of the calculator.
 *
 * @author Mario Bobic
 */
public class MethodButtonRes extends AbstractButton {
    /** Serialization UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an instance of {@code MethodButtonRes} and associates the
     * display with this button.
     *
     * @param display display to be associated with this button
     */
    public MethodButtonRes(Display display) {
        super(display, "res");
    }

    @Override
    protected void execute(Display display) {
        display.reset();
    }

}
