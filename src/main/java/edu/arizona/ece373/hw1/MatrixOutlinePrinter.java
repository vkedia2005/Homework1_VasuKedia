//------------------------------------------------------
// Homework 1
// Written by: Vasu Kedia 23778601
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
//------------------------------------------------------
package edu.arizona.ece373.hw1;

/**
 * Prints the outline (the frame) of a matrix and leaves the inside blank.
 *
 * <p>A 2x4 matrix must render as</p>
 *
 * <pre>
 * +----+
 * |    |
 * |    |
 * +----+
 * </pre>
 *
 * <p>with a {@code '\n'} after every line, including the last one.</p>
 */
public class MatrixOutlinePrinter implements MatrixPrinter {

    /** The character drawn at the four corners of the frame. */
    public static final char CORNER = '+';

    /** The character drawn along the top and bottom edges of the frame. */
    public static final char HORIZONTAL = '-';

    /** The character drawn along the left and right edges of the frame. */
    public static final char VERTICAL = '|';

    /**
     * Renders {@code matrix} as a framed block of text.
     *
     * @param matrix the matrix to render, must not be {@code null}
     * @return the framed rendering of {@code matrix}
     * @throws IllegalArgumentException if {@code matrix} is {@code null}, or if
     *                                  {@link #printRow(Matrix, int)} rejects a row
     */
    @Override
    public String print(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Cannot print a null matrix");
        }
        String border = CORNER + String.valueOf(HORIZONTAL).repeat(matrix.getColumns()) + CORNER;

        StringBuilder out = new StringBuilder();
        out.append(border).append('\n');
        for (int row = 0; row < matrix.getRows(); row++) {
            out.append(VERTICAL).append(printRow(matrix, row)).append(VERTICAL).append('\n');
        }
        out.append(border).append('\n');
        return out.toString();
    }

    /**
     * Renders the inside of one row, without the surrounding {@link #VERTICAL} characters.
     *
     * <p>The returned string must be exactly {@code matrix.getColumns()} characters long,
     * otherwise the frame will not line up. Subclasses override this method.</p>
     *
     * @param matrix the matrix being printed, never {@code null}
     * @param row    the index of the row to render
     * @return the characters that belong between the two border characters of this line
     */
    protected String printRow(Matrix matrix, int row) {
        return " ".repeat(matrix.getColumns());

    }
}
