package hr.fer.zemris.java.gui.calc.components.functions;

import hr.fer.zemris.java.gui.calc.components.Display;

/**
 * This class represents the logarithm function <tt>log(x)</tt> and its inverse
 * <tt>10^x</tt>.
 *
 * @author Mario Bobic
 */
public class FunctionButtonLog extends AbstractFunction {
    /** Serialization UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an instance of {@code FunctionButtonLog} and associates the
     * display with this button.
     *
     * @param display display to be associated with this button
     */
    public FunctionButtonLog(Display display) {
        super(display, "log", "10^x");
    }

    @Override
    protected void executeNormal(Display display) {
        display.setValue(Math.log10(display.getValue()));
    }

    @Override
    protected void executeInversed(Display display) {
        display.setValue(Math.pow(10, display.getValue()));
    }

}
