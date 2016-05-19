package hr.fer.zemris.java.gui.calc.components.operations;

/**
 * This interface represents an operation that may be applied to two double
 * values to return a new double value.
 *
 * @author Mario Bobic
 */
public interface Operation {

	/**
	 * Applies the function to the two double values and returns a new double
	 * value.
	 * 
	 * @param left the first operand
	 * @param right the second operand
	 * @return an executed function on the two operands
	 */
	double apply(double left, double right);
	
}
