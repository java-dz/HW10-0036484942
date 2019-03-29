package hr.fer.zemris.java.gui.charts;

/**
 * This class represents an ordered pair <tt>(x, y)</tt>. Two read-only fields
 * are provided: {@linkplain #x} and {@linkplain #y}.
 *
 * @author Mario Bobic
 */
public class XYValue {

    /** The x value. */
    public final int x;

    /** The y value. */
    public final int y;

    /**
     * Constructs an instance of {@code XYValue} with the specified
     * <tt>x</tt> and <tt>y</tt> values.
     *
     * @param x x value
     * @param y y value
     */
    public XYValue(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Parses the specified string <tt>s</tt>, expecting two integer members
     * delimited by a comma symbol. Returns the parsed instance of
     * <tt>XYValue</tt>.
     *
     * @param s string to be parsed
     * @return an instance of XYValue with the parsed x and y values
     * @throws IllegalArgumentException if s does not contain two members
     * @throws NumberFormatException if members can not be parsed as integers
     */
    public static XYValue parse(String s) {
        String[] xy = s.split(",");

        if (xy.length != 2) {
            throw new IllegalArgumentException(
                "XYValue must contain one comma delimiting two integers."
            );
        }

        return new XYValue(Integer.parseInt(xy[0].trim()), Integer.parseInt(xy[1].trim()));
    }

    @Override
    public String toString() {
        return "XYValue(" + x + ", " + y + ")";
    }

}
