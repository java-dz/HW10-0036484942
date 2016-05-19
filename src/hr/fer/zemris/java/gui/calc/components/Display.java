package hr.fer.zemris.java.gui.calc.components;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Locale;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.border.Border;

import hr.fer.zemris.java.gui.calc.Invertible;
import hr.fer.zemris.java.gui.calc.components.numbers.NumberButton;
import hr.fer.zemris.java.gui.calc.components.operations.Operation;

/**
 * This class represents the Display of the calculator. It contains various
 * methods for manipulating the display and the value stored in it. This class
 * is also a model, which means when the value of the number stored in the
 * Display is changed, the same class does the function of storing the value and
 * redrawing a new value onto the Display.
 *
 * @author Mario Bobic
 */
public class Display extends JTextField {
	/** Serialization UID. */
	private static final long serialVersionUID = 1L;
	
	/** Color of the display. */
	private static final Color DISPLAY_COLOR = new Color(255, 211, 32);
	
	/** Color of the display border. */
	private static final Color BORDER_COLOR = new Color(52, 101, 175);
	/** Border of the display, with the right side slightly shifted. */
	private static final Border BORDER = BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(BORDER_COLOR),
			BorderFactory.createEmptyBorder(0, 0, 0, 5)
	);
	
	/** The font used for displaying text. */
	private static final Font FONT = new Font(Font.MONOSPACED, Font.BOLD, 22);
	
	/** An object associated with DecimalFormat that specified the Locale. */
	private static final DecimalFormatSymbols LOC = new DecimalFormatSymbols(Locale.US);
	/** The formatter used for formatting decimal numbers. */
	private static final DecimalFormat FORMATTER = new DecimalFormat("#.##########", LOC);

	/** The decimal point constant. */
	private static final String DECIMAL_POINT = ".";
	
	/** Value that is currently stored in Display. */
	private double value;
	/** Left value (first operand) for executing operations. */
	private double left;
	/** Right value (second operand) for executing operations. */
	private double right;
	
	/** Indicates that the value stored has a decimal point, for better display. */
	private boolean isDecimal;
	
	/** Indicates that the value has been modded from outside and needs to be
	 * reset upon appending a new value to the display. */
	private boolean moddedFromOutside;
	
	/** Indicates that the functions have been inverted. */
	private boolean inversed;
	
	/** Indicates that the value has been internally unset.
	 * Used by the {@linkplain #executeOperation()} method. */
	private boolean valueUnset;
	
	/** The current operation that is to be executed. */
	private Operation operation;
	
	/** The stack of the display. */
	private Stack<Double> stack;
	
	/** Check box that will be unchecked in the {@linkplain #reset} method. */
	private JCheckBox invCheckBox;
	/** List of invertible objects that will be inverted when the
	 * {@linkplain #setInversed(boolean)} method is called. */
	private List<Invertible> invertibles;
	
	/**
	 * Constructs an instance of {@code Display} with value set to <tt>0</tt>.
	 */
	public Display() {
		this(0.0d);
	}
	
	/**
	 * Constructs an instance of {@code Display} with the specified initial
	 * value to be set.
	 * 
	 * @param value value to be set as initial
	 */
	public Display(double value) {
		this(doubleToString(value));
		this.value = value;
	}
	
	/**
	 * Constructs an instance of {@code Display} with the specified text to be
	 * displayed initially.
	 * 
	 * @param text text to be initially displayed
	 */
	public Display(String text) {
		super(text);
		setBorder(BORDER);
		setBackground(DISPLAY_COLOR);
		setEditable(false);
		setHorizontalAlignment(JTextField.RIGHT);
		setFont(FONT);
		
		stack = new Stack<>();
		invertibles = new ArrayList<>();
		// initialized for security reasons
		invCheckBox = new JCheckBox();
	}
	
	/**
	 * Returns a string representation of the specified <tt>double</tt> value,
	 * formatting it the next way:
	 * <ul>
	 * <li>if the double value is not finite (is NaN or infinite), the
	 * {@linkplain Double#toString()} method is called,
	 * <li>else this class' {@linkplain #FORMATTER} is used.
	 * 
	 * @param value value to be returned as a formatted string
	 * @return a string representation of the specified value
	 */
	private static String doubleToString(double value) {
		if (!Double.isFinite(value)) {
			return Double.toString(value);
		}
		
		return FORMATTER.format(value);
	}
	
	/**
	 * Returns the value of the decimal number that is currently stored in the
	 * display.
	 * 
	 * @return the value of the decimal number of the display
	 */
	public double getValue() {
		return value;
	}
	
	/**
	 * Sets the value of the decimal number that is currently stored in the
	 * display to the new <tt>value</tt>. This method updates the component
	 * text.
	 * 
	 * @param value value to be set to this display
	 */
	public void setValue(double value) {
		moddedFromOutside = true;
		setValue0(value);
	}
	
	/**
	 * Sets the value of the decimal number that is currently stored in the
	 * display to the new <tt>value</tt>. This method updates the component
	 * text.
	 * <p>
	 * The difference between this method and the public one is that the public
	 * one sets the {@linkplain #moddedFromOutside} flag.
	 * 
	 * @param value value to be set to this display
	 */
	private void setValue0(double value) {
		this.value = value;
		String text = doubleToString(value);
		setText(text);
		valueUnset = false;
	}
	
	/**
	 * Appends the specified integer <tt>value</tt> to the value that is already
	 * set to this display. This method is used by the {@linkplain NumberButton}
	 * objects.
	 * 
	 * @param value value to be appended to the end of the current value
	 */
	public void appendValue(int value) {
		checkModification();
		
		String text = doubleToString(this.value);
		
		// if decimal flag has been set, but has no decimals yet
		if (isDecimal && !text.contains(DECIMAL_POINT)) {
			text += DECIMAL_POINT;
		}
		
		text += value;
		setValue0(Double.parseDouble(text));
	}
	
	/**
	 * Checks if the value has been modded from the outside. If the value has
	 * been modded from the outside, the value stored in this display is first
	 * set to <tt>0</tt> and then the flag {@linkplain #moddedFromOutside} is
	 * then set to false.
	 */
	private void checkModification() {
		if (moddedFromOutside) {
			this.value = 0;
			setValue0(0);
			moddedFromOutside = false;
		}
	}
	
	/**
	 * Negates the value currently stored in this display.
	 */
	public void negate() {
		setValue0(-value);
	}
	
	/**
	 * Returns true if the functions (or {@linkplain Invertible} objects) have
	 * been inverted. False otherwise.
	 * 
	 * @return true if the state is inverted
	 */
	public boolean isInversed() {
		return inversed;
	}
	
	/**
	 * Sets the state of {@linkplain Invertible} objects to the specified
	 * <tt>inversed</tt> flag.
	 * 
	 * @param inversed the new state of <tt>Invertible</tt> objects
	 */
	public void setInversed(boolean inversed) {
		this.inversed = inversed;
		
		invertibles.forEach((function) -> {
			function.setInversed(inversed);
		});
	}
	
	/**
	 * Toggles the inverse.
	 * <p>
	 * If the state of {@linkplain Invertible} objects is already inverted,
	 * the state is set to not inverted. Else the state is set to inverted.
	 */
	public void toggleInverse() {
		setInversed(!inversed);
	}
	
	/**
	 * Adds the decimal point to the current value, if it is not present.
	 */
	public void addDecimalPoint() {
		if (!isDecimal) {
			isDecimal = true;
			setText(getText() + DECIMAL_POINT);
		}
	}
	
	/**
	 * Returns the current operation that is to be executed.
	 * 
	 * @return the current operation that is to be executed
	 */
	public Operation getOperation() {
		return operation;
	}
	
	/**
	 * Sets the operation that is to be executed to the specified
	 * <tt>operation</tt> and prepares values for the
	 * {@linkplain #executeOperation()} method.
	 * <p>
	 * If there is already an operation set to be executed, and the value has
	 * been set as a new one (for example, if the user clicked 2, then +, then
	 * 3, and then another operation after that), the old operation is first
	 * executed before a new one is set.
	 * 
	 * @param operation operation that is to be set
	 */
	public void setOperation(Operation operation) {
		if (this.operation == null) {
			left = value;
			isDecimal = false;
		} else {
			// if user clicks operations multiple times
			if (!valueUnset)
				executeOperation();
		}
		
		this.operation = operation;
		value = 0;
		valueUnset = true;
	}
	
	/**
	 * Executes the operation that was specified by the
	 * {@linkplain #setOperation(Operation)} method.
	 */
	public void executeOperation() {
		if (operation != null) {
			right = value;
			setValue(operation.apply(left, right));
			
			operation = null;
			left = value;
		}
	}
	
	/**
	 * Clears the currently entered value by setting it to zero.
	 */
	public void clear() {
		setValue0(0);
		isDecimal = false;
	}
	
	/**
	 * Resets the calculator state to the initial one.
	 * <p>
	 * This means that:
	 * <ul>
	 * <li>all values will be cleared,
	 * <li>all flags will be reset (set to false),
	 * <li>the current operation will be set to <tt>null</tt>,
	 * <li>the stack will be cleared,
	 * <li>the inverted state will be set to false and
	 * <li>the inverse check box is unchecked
	 * </ul>
	 */
	public void reset() {
		value = left = right = 0;
		isDecimal = valueUnset = false;
		moddedFromOutside = true;
		operation = null;
		stack.clear();
		
		setInversed(false);
		invCheckBox.setSelected(false);
		
		// update screen
		setValue0(value);
	}
	
	/**
	 * Pushes the value that is currently stored in the display to stack.
	 */
	public void push() {
		stack.push(value);
	}
	
	/**
	 * Pops the value from stack and sets it to the display.
	 * 
	 * @throws EmptyStackException if the stack is empty
	 */
	public void pop() throws EmptyStackException {
		setValue0(stack.pop());
	}
	
	/**
	 * Registers the specified {@link Invertible invertible} object to this
	 * display.
	 * 
	 * @param invertible invertible to be registered to this display
	 */
	public void registerInvertible(Invertible invertible) {
		invertibles.add(invertible);
	}
	
	/**
	 * Deregisters the specified {@link Invertible invertible} object from this
	 * display.
	 * 
	 * @param invertible invertible to be deregistered to this display
	 */
	public void deregisterInvertible(Invertible invertible) {
		invertibles.remove(invertible);
	}
	
	/**
	 * Sets the inverse check box to the specified <tt>checkBox</tt>.
	 * <p>
	 * This check box will be unchecked upon calling the {@linkplain #reset()}
	 * method.
	 * 
	 * @param checkBox check box to be set
	 */
	public void setInverseCheckBox(JCheckBox checkBox) {
		invCheckBox = checkBox;
	}

	/**
	 * Shows the specified <tt>message</tt> on the display as an error message
	 * and resets the configuration of this display by calling the
	 * {@linkplain #reset()} method.
	 * 
	 * @param message message to be display as error
	 */
	public void showError(String message) {
		reset();  // reset configuration
		setText(message);
	}
	
}
