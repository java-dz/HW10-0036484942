package hr.fer.zemris.java.gui.calc.components.functions;

import hr.fer.zemris.java.gui.calc.components.Display;

/**
 * This class represents the cosine function <tt>cos(x)</tt> and its inverse
 * <tt>arccos(x)</tt>.
 *
 * @author Mario Bobic
 */
public class FunctionButtonCos extends AbstractFunction {
    /** Serialization UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an instance of {@code FunctionButtonCos} and associates the
     * display with this button.
     *
     * @param display display to be associated with this button
     */
    public FunctionButtonCos(Display display) {
        super(display, "cos", "arccos");
    }

    @Override
    protected void executeNormal(Display display) {
        display.setValue(Math.cos(display.getValue()));
    }

    @Override
    protected void executeInversed(Display display) {
        display.setValue(Math.acos(display.getValue()));
    }
}
