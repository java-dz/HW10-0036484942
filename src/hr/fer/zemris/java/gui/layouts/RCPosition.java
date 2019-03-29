package hr.fer.zemris.java.gui.layouts;

/**
 * This class defines constraints for the {@linkplain CalcLayout} class. Two
 * read-only fields are provided: {@linkplain #row} and {@linkplain #column}.
 *
 * @author Mario Bobic
 */
public class RCPosition {

    /** Row of the position. */
    public final int row;


    /** Column of the position. */
    public final int column;

    /**
     * Constructs an instance of {@code RCPosition} with the specified
     * <tt>row</tt> and <tt>column</tt>.
     *
     * @param row row of the position
     * @param column column of the position
     */
    public RCPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Parses the specified string <tt>s</tt>, expecting two integer members
     * delimited by a comma symbol. Returns the parsed instance of
     * <tt>RCPosition</tt>.
     *
     * @param s string to be parsed
     * @return an instance of RCPosition with the parsed row and column
     * @throws IllegalArgumentException if s does not contain two members
     * @throws NumberFormatException if members can not be parsed as integers
     */
    public static RCPosition parse(String s) {
        String[] rc = s.split(",");

        if (rc.length != 2) {
            throw new IllegalArgumentException(
                "RCPosition must contain one comma delimiting two integers."
            );
        }

        return new RCPosition(Integer.parseInt(rc[0].trim()), Integer.parseInt(rc[1].trim()));
    }

    @Override
    public String toString() {
        return "RCPosition(" + row + ", " + column + ")";
    }

}
