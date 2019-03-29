package hr.fer.zemris.java.gui.calc.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

import hr.fer.zemris.java.gui.calc.MousePressedListener;

/**
 * This class is an abstract representation of a calculator button which extends
 * the {@linkplain JButton} class.
 * <p>
 * It holds some default methods that are common to all calculator buttons and
 * has color and border constants.
 *
 * @author Mario Bobic
 */
public abstract class AbstractButton extends JButton {
    /** Serialization UID. */
    private static final long serialVersionUID = 1L;

    /** Color of the button. */
    private static final Color BUTTON_COLOR = new Color(114, 159, 207);

    /** Color of the button border. */
    private static final Color BORDER_COLOR = new Color(52, 101, 175);
    /** Line border of buttons with color specified by the BORDER_COLOR. */
    private static final Border BORDER = BorderFactory.createLineBorder(BORDER_COLOR);

    /** The font used in button text. */
    private static final Font FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 17);

    /**
     * Constructs an instance of {@code AbstractButton} with the specified
     * parameters.
     *
     * @param display the display to be associated with this button
     * @param name name of the button
     */
    public AbstractButton(Display display, String name) {
        super(name);

        addMouseListener((event) -> {
            try {
                execute(display);
            } catch (Exception ex) {
                display.showError(ex.getMessage());
            }
        });

        setBorder(BORDER);
        setBackground(BUTTON_COLOR);
        setFont(FONT);
    }

    /**
     * Adds an instance of {@linkplain MousePressedListener} to this button.
     *
     * @param l an instance of MoustListener2
     */
    protected void addMouseListener(MousePressedListener l) {
        super.addMouseListener(l);
    }

    /**
     * Executes the button function and informs the specified <tt>display</tt>
     * of the change.
     *
     * @param display display to be notified of the change
     */
    protected abstract void execute(Display display);
}
