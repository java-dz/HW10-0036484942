package hr.fer.zemris.java.gui.calc.components.functions;

import hr.fer.zemris.java.gui.calc.components.Display;

/**
 * This class represents the inverse function <tt>1/x</tt>.
 *
 * @author Mario Bobic
 */
public class FunctionButtonInverse extends AbstractFunction {
    /** Serialization UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an instance of {@code FunctionButtonInverse} and associates the
     * display with this button.
     *
     * @param display display to be associated with this button
     */
    public FunctionButtonInverse(Display display) {
        super(display, "1/x", "1/x");
    }

    @Override
    protected void executeNormal(Display display) {
        display.setValue(1 / display.getValue());
    }

    @Override
    protected void executeInversed(Display display) {
        executeNormal(display);
    }

}
