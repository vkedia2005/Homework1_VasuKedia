//------------------------------------------------------
// Homework 1
// Written by: Vasu Kedia 23778601
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
//------------------------------------------------------
package edu.arizona.ece373.hw1;

/**
 * A named pattern of live and dead cells that can be stamped onto a {@link GameOfLife} field.
 *
 * <p>Task 3 of the homework. A shape <em>is a</em> {@link Matrix} whose cells are only 0 or 1,
 * plus a name such as {@code "Beehive"}.</p>
 */
public class Shape extends Matrix {

    /** The name of this shape, never null or blank. */
    private String name;
    /**
     * Creates a named shape from a pattern of zeroes and ones.
     *
     * @param name the name of the shape, must not be {@code null} or blank
     * @param data the pattern, must be a legal matrix body containing only 0 and 1
     * @throws IllegalArgumentException if the name is missing, if {@code data} is not a legal
     *                                  matrix, or if any cell is not 0 or 1
     */
    public Shape(String name, int[][] data) {
        super(data);
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("A shape needs a non-blank name");
        }
        for (int row = 0; row < getRows(); row++) {
            for (int column = 0; column < getColumns(); column++) {
                int value = get(row, column);
                if (value != 0 && value != 1) {
                    throw new IllegalArgumentException("Cell (" + row + ", " + column
                            + ") holds " + value + "; a shape may only contain 0 and 1");
                }
            }
        }
        this.name = name;
    }

    /**
     * Returns the name of this shape.
     *
     * @return the shape name
     */
    public String getName() {
       return name;
    }

    /**
     * Renames this shape.
     *
     * @param name the new name, must not be {@code null} or blank
     * @throws IllegalArgumentException if the name is {@code null} or blank
     */
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("A shape needs a non-blank name");
        }
        this.name = name;
    }

    /**
     * Returns the classic still-life "beehive", a 3x4 pattern that never changes.
     *
     * @return a new beehive shape
     */
    public static Shape beehive() {
        return new Shape("Beehive", new int[][] {
                {0, 1, 1, 0},
                {1, 0, 0, 1},
                {0, 1, 1, 0},
        });
    }

    /**
     * Returns the classic still-life "boat", a 3x3 pattern that never changes.
     *
     * @return a new boat shape
     */
    public static Shape boat() {
        return new Shape("Boat", new int[][] {
                {1, 1, 0},
                {1, 0, 1},
                {0, 1, 0},
        });
    }

    /**
     * Returns the horizontal phase of the oscillator "blinker", a 1x3 row of live cells.
     *
     * @return a new blinker shape
     */
    public static Shape blinker() {
        return new Shape("Blinker", new int[][]{
                {1, 1, 1},
        });

    }

    /**
     * Returns the classic spaceship "glider", a 3x3 pattern that travels one cell down and one
     * cell to the right every four generations.
     *
     * @return a new glider shape
     */
    public static Shape glider() {
        return new Shape("Glider", new int[][] {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
        });
    }

    /**
     * Returns the still-life "block", a 2x2 square of live cells.
     *
     * @return a new block shape
     */
    public static Shape block() {
        return new Shape("Block", new int[][] {
                {1, 1},
                {1, 1},
        });
    }

    /**
     * Returns one phase of the period-two oscillator "toad", a 2x4 pattern.
     *
     * @return a new toad shape
     */
    public static Shape toad() {
        return new Shape("Toad", new int[][]{
                {0, 1, 1, 1},
                {1, 1, 1, 0},
        });
    }


    /**
     * Showcase for Task 3: builds a beehive and a boat and prints both with a
     * {@link BoolMatrixPrinter}.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        MatrixPrinter printer = new BoolMatrixPrinter();

        Shape beehive = Shape.beehive();
        System.out.println(beehive.getName() + ":");
        System.out.print(printer.print(beehive));


        Shape boat = Shape.boat();
        System.out.println(boat.getName() + ":");
        System.out.print(printer.print(boat));
    }
}
