package hr.fer.zemris.java.gui.calc;

/**
 * This interface represents Invertible objects. An invertible object is an
 * object that can be inverted. For an example, the inverse function of sin(x)
 * is arcsin(x).
 *
 * @author Mario Bobic
 */
public interface Invertible {

	/**
	 * Sets the state of this object to the specified boolean value.
	 * 
	 * @param inversed the state of the object to be set
	 */
	void setInversed(boolean inversed);
	
	/**
	 * Returns <tt>true</tt> if state of the <tt>Invertible</tt> object is
	 * <tt>inverted</tt>.
	 * 
	 * @return state of the <tt>Invertible</tt> object 
	 */
	boolean getInverted();
	
}
