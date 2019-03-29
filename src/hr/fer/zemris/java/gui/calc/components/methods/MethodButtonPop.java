package hr.fer.zemris.java.gui.calc.components.methods;

import java.util.EmptyStackException;

import hr.fer.zemris.java.gui.calc.components.AbstractButton;
import hr.fer.zemris.java.gui.calc.components.Display;

/**
 * This class represents a <tt>pop</tt> method of the calculator.
 *
 * @author Mario Bobic
 */
public class MethodButtonPop extends AbstractButton {
    /** Serialization UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an instance of {@code MethodButtonPop} and associates the
     * display with this button.
     *
     * @param display display to be associated with this button
     */
    public MethodButtonPop(Display display) {
        super(display, "pop");
    }

    @Override
    protected void execute(Display display) {
        try {
            display.pop();
        } catch (EmptyStackException e) {
            display.showError("Stack empty");
        }
    }

}
