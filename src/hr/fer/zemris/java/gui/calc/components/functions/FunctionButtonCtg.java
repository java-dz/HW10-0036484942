package hr.fer.zemris.java.gui.calc.components.functions;

import hr.fer.zemris.java.gui.calc.components.Display;

/**
 * This class represents the cotangens function <tt>ctg(x)</tt> and its inverse
 * <tt>arcctg(x)</tt>.
 *
 * @author Mario Bobic
 */
public class FunctionButtonCtg extends AbstractFunction {
    /** Serialization UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an instance of {@code FunctionButtonCtg} and associates the
     * display with this button.
     *
     * @param display display to be associated with this button
     */
    public FunctionButtonCtg(Display display) {
        super(display, "ctg", "arcctg");
    }

    @Override
    protected void executeNormal(Display display) {
        display.setValue(1 / Math.tan(display.getValue()));
    }

    @Override
    protected void executeInversed(Display display) {
        display.setValue(1 / Math.atan(display.getValue()));
    }

}
