package hr.fer.zemris.java.gui.calc.components.operations;

import hr.fer.zemris.java.gui.calc.Invertible;
import hr.fer.zemris.java.gui.calc.components.AbstractButton;
import hr.fer.zemris.java.gui.calc.components.Display;

/**
 * This class implements the {@linkplain Operation} and {@linkplain Invertible}
 * interfaces and represents the power operation of the calculator.
 *
 * @author Mario Bobic
 */
public class OperationButtonPower extends AbstractButton implements Operation, Invertible {
    /** Serialization UID. */
    private static final long serialVersionUID = 1L;

    /** Default name of the function. */
    // HTML could be used: <html>x<sup>n</sup></html>
    private static final String NORMAL_NAME = "x^n";
    /** Name of the inversed function. */
    private static final String INVERSED_NAME = "n\u221Ax";

    /** Indicates that the function is inverted. */
    private boolean inversed;

    /**
     * Constructs an instance of {@code OperationButtonPower} and associates the
     * display with this button.
     *
     * @param display display to be associated with this button
     */
    public OperationButtonPower(Display display) {
        super(display, NORMAL_NAME);

        display.registerInvertible(this);
    }

    @Override
    protected void execute(Display display) {
        display.setOperation(this);
    }

    @Override
    public double apply(double left, double right) {
        return !inversed ? Math.pow(left, right) : Math.pow(right, 1/left);
    }

    @Override
    public void setInversed(boolean inversed) {
        this.inversed = inversed;
        setText(inversed ? INVERSED_NAME : NORMAL_NAME);
    }

    @Override
    public boolean getInverted() {
        return inversed;
    }

}
