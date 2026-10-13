//------------------------------------------------------
// Homework 1
// Written by: Vasu Kedia 23778601
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
//------------------------------------------------------
package edu.arizona.ece373.hw1;

/**
 * Prints a matrix whose cells are only {@code 0} or {@code 1}, values and all.
 *
 * <p>A 2x4 matrix whose two rows both hold {@code 0, 0, 1, 0} must render as</p>
 *
 * <pre>
 * +----+
 * |0010|
 * |0010|
 * +----+
 * </pre>
 *
 * <p>Only the row hook is overridden; the frame is inherited from
 * {@link MatrixOutlinePrinter}.</p>
 */
public class BoolMatrixPrinter extends MatrixOutlinePrinter {

    /** The character printed for a cell that holds {@code 0}. */
    public static final char DEAD = '0';

    /** The character printed for a cell that holds {@code 1}. */
    public static final char ALIVE = '1';

    /**
     * Renders one row as its cell values.
     *
     * @param matrix the matrix being printed, never {@code null}
     * @param row    the index of the row to render
     * @return one character per column, {@code '0'} or {@code '1'}
     * @throws IllegalArgumentException if any cell of the row holds a value other than 0 or 1
     */
    @Override
    protected String printRow(Matrix matrix, int row) {
        StringBuilder line = new StringBuilder();
        for (int column = 0; column < matrix.getColumns(); column++) {
            int value = matrix.get(row, column);
            if (value == 0) {
                line.append(DEAD);
            } else if (value == 1) {
                line.append(ALIVE);
            } else {
                throw new IllegalArgumentException("Cell (" + row + ", " + column + ") holds "
                        + value + "; only 0 and 1 can be printed");
            }
        }
        return line.toString();
    }
}
