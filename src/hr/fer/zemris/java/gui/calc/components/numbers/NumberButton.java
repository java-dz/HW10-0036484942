package hr.fer.zemris.java.gui.calc.components.numbers;

import hr.fer.zemris.java.gui.calc.components.AbstractButton;
import hr.fer.zemris.java.gui.calc.components.Display;

/**
 * This class represents a numerical value that is appended to the
 * {@linkplain Display} object when clicked.
 *
 * @author Mario Bobic
 */
public class NumberButton extends AbstractButton {
    /** Serialization UID. */
    private static final long serialVersionUID = 1L;

    /** Numerical value of the button. */
    private final int value;

    /**
     * Constructs an instance of {@code NumberButton} and associates the display
     * and the numerical value with this button.
     *
     * @param display display to be associated with this button
     * @param value value to be set to this button
     * @throws IllegalArgumentException if value &lt; 0 || value &gt; 9
     */
    public NumberButton(Display display, int value) {
        super(display, Integer.toString(value));

        checkValue(value);
        this.value = value;
    }

    /**
     * Throws an {@linkplain IllegalArgumentException} if the specified
     * <tt>value</tt> is invalid.
     *
     * @param value value to be checked
     * @throws IllegalArgumentException if value &lt; 0 || value &gt; 9
     */
    private static void checkValue(int value) {
        if (value < 0 || value > 9) {
            throw new IllegalArgumentException(
                "Illegal value: " + value
                + ", value must be an integer in range 0-9.");
        }
    }

    @Override
    protected void execute(Display display) {
        display.appendValue(value);
    }
}
