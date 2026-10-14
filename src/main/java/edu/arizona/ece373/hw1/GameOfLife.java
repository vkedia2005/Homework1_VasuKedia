//------------------------------------------------------
// Homework 1
// Written by: Vasu Kedia 23778601
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
//------------------------------------------------------
package edu.arizona.ece373.hw1;

/**
 * A Conway's Game of Life field.
 *
 * <p>Task 4 of the homework. The field <em>is a</em> {@link Matrix} in which {@code 1} means the
 * cell is alive and {@code 0} means it is dead. Cells outside the field always count as dead, so
 * the field does not wrap around at the edges.</p>
 *
 * <p>The rules for one generation are:</p>
 * <ul>
 *   <li>a live cell with fewer than two live neighbours dies;</li>
 *   <li>a live cell with two or three live neighbours survives;</li>
 *   <li>a live cell with more than three live neighbours dies;</li>
 *   <li>a dead cell with exactly three live neighbours becomes alive.</li>
 * </ul>
 */
public class GameOfLife extends Matrix {

    /** Value of a cell that is alive. */
    public static final int ALIVE = 1;

    /** Value of a cell that is dead. */
    public static final int DEAD = 0;

    /**
     * Creates an empty game field of the given size; every cell starts dead.
     *
     * @param rows    the height of the field, must be at least 1
     * @param columns the width of the field, must be at least 1
     * @throws IllegalArgumentException if {@code rows} or {@code columns} is less than 1
     */
    public GameOfLife(int rows, int columns) {
        super(rows, columns);
    }

    /**
     * Stamps a shape onto the field with its top-left cell at the given coordinate.
     *
     * @param shape  the shape to place, must not be {@code null}
     * @param row    the field row that receives row 0 of the shape
     * @param column the field column that receives column 0 of the shape
     * @throws IllegalArgumentException if {@code shape} is {@code null}, if the coordinate is
     *                                  negative, or if the shape does not fit entirely on the field
     */
    public void addShape(Shape shape, int row, int column) {
        if (shape == null) {
            throw new IllegalArgumentException("Cannot add a null shape");
        }
        if (row < 0 || column < 0) {
            throw new IllegalArgumentException(
                    "Position (" + row + ", " + column + ") must not be negative");
        }
        if (row + shape.getRows() > getRows() || column + shape.getColumns() > getColumns()) {
            throw new IllegalArgumentException(shape.getName() + " at (" + row + ", " + column
                    + ") does not fit on a " + getRows() + "x" + getColumns() + " field");
        }
        for (int r = 0; r < shape.getRows(); r++) {
            for (int c = 0; c < shape.getColumns(); c++) {
                set(row + r, column + c, shape.get(r, c));
            }
        }
    }

    /**
     * Advances the field by one generation.
     *
     * <p>Compute the whole next generation into a local two-dimensional array first, and only
     * then install it with the inherited {@link Matrix#setData(int[][])}.</p>
     */
    public void step() {
        int[][] next = new int[getRows()][getColumns()];
        for (int row = 0; row < getRows(); row++) {
            for (int column = 0; column < getColumns(); column++) {
                int neighbours = countLiveNeighbours(row, column);
                if (get(row, column) == ALIVE) {
                    if (neighbours == 2 || neighbours == 3) {
                        next[row][column] = ALIVE;
                    } else {
                        next[row][column] = DEAD;
                    }
                } else {
                    if (neighbours == 3) {
                        next[row][column] = ALIVE;
                    } else {
                        next[row][column] = DEAD;
                    }
                }
            }
        }
        setData(next);
    }

    /**
     * Counts how many of the eight cells around a coordinate are alive.
     *
     * @param row    the row of the cell whose neighbours are counted
     * @param column the column of the cell whose neighbours are counted
     * @return a number between 0 and 8
     */
    public int countLiveNeighbours(int row, int column) {
        int count = 0;
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = column - 1; c <= column + 1; c++) {
                if (r == row && c == column) {
                    continue;
                }
                if (r < 0 || r >= getRows() || c < 0 || c >= getColumns()) {
                    continue;
                }
                if (get(r, c) == ALIVE) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Showcase for Task 4: builds a field holding a blinker and a glider and steps it a fixed
     * number of generations.
     *
     * @param args optional; args[0] is the number of generations to run, default 20
     * @throws InterruptedException if the animation is interrupted while sleeping between frames
     */
    public static void main(String[] args) throws InterruptedException {
        int generations = 20;
        if (args.length > 0) {
            generations = Integer.parseInt(args[0]);
        }

        GameOfLife game = new GameOfLife(12, 12);
        game.addShape(Shape.blinker(), 1, 1);
        game.addShape(Shape.glider(), 5, 5);

        MatrixPrinter printer = new BoolMatrixPrinter();
        for (int generation = 0; generation < generations; generation++) {
            System.out.println("Generation " + generation + ":");
            System.out.print(printer.print(game));
            game.step();
            Thread.sleep(200);
        }
    }
}
