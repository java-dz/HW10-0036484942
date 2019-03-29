package hr.fer.zemris.java.gui.calc.components.functions;

import hr.fer.zemris.java.gui.calc.Invertible;
import hr.fer.zemris.java.gui.calc.components.AbstractButton;
import hr.fer.zemris.java.gui.calc.components.Display;

/**
 * This class is an abstract representation of a calculator function which extends
 * the {@linkplain AbstractButton} class.
 * <p>
 * It holds some default methods that are common to all calculator functions and
 * has an invertible property.
 *
 * @author Mario Bobic
 */
public abstract class AbstractFunction extends AbstractButton implements Invertible {
    /** Serialization UID. */
    private static final long serialVersionUID = 1L;

    /** Default name of the function. */
    private String normalName;
    /** Name of the inversed function. */
    private String inversedName;

    /** Indicates that the function is inverted. */
    private boolean inversed;

    /**
     * Constructs an instance of {@code AbstractFunction} with the specified
     * parameters.
     *
     * @param display display to be set to this function
     * @param normalName default name of the function
     * @param inversedName name of the inversed function
     */
    public AbstractFunction(Display display, String normalName, String inversedName) {
        super(display, normalName);

        this.normalName = normalName;
        this.inversedName = inversedName;
        display.registerInvertible(this);
    }

    @Override
    protected void execute(Display display) {
        if (display.isInversed()) {
            executeInversed(display);
        } else {
            executeNormal(display);
        }
    }

    /**
     * Executes the normal (non-inverse) function and informs the specified
     * display of the change.
     *
     * @param display display to be notified of the change
     */
    protected abstract void executeNormal(Display display);

    /**
     * Executes the inverse function and informs the specified display of the
     * change.
     *
     * @param display display to be notified of the change
     */
    protected abstract void executeInversed(Display display);

    @Override
    public void setInversed(boolean inversed) {
        this.inversed = inversed;
        setText(inversed ? inversedName : normalName);
    }

    @Override
    public boolean getInverted() {
        return inversed;
    }

}
