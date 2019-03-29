package hr.fer.zemris.java.gui.calc.components.functions;

import hr.fer.zemris.java.gui.calc.components.Display;

/**
 * This class represents the sine function <tt>sin(x)</tt> and its
 * inverse <tt>arcsin(x)</tt>.
 *
 * @author Mario Bobic
 */
public class FunctionButtonSin extends AbstractFunction {
    /** Serialization UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an instance of {@code FunctionButtonSin} and associates the
     * display with this button.
     *
     * @param display display to be associated with this button
     */
    public FunctionButtonSin(Display display) {
        super(display, "sin", "arcsin");
    }

    @Override
    protected void executeNormal(Display display) {
        display.setValue(Math.sin(display.getValue()));
    }

    @Override
    protected void executeInversed(Display display) {
        display.setValue(Math.asin(display.getValue()));
    }

}
