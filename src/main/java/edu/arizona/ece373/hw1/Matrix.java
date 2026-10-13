//------------------------------------------------------
// Homework 1
// Written by: Vasu Kedia 23778601
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
//------------------------------------------------------
package edu.arizona.ece373.hw1;

/**
 * A rectangular, mutable matrix of {@code int} values.
 *
 * <p>Task 1 of the homework. The array of cells must stay {@code private}: everything outside
 * this class reaches the cells through the methods below.</p>
 */
public class Matrix {

    private int[][] data;


    /**
     * Creates a matrix of the given size whose cells are all zero.
     *
     * @param rows    the number of rows, must be at least 1
     * @param columns the number of columns, must be at least 1
     * @throws IllegalArgumentException if {@code rows} or {@code columns} is less than 1
     */
    public Matrix(int rows, int columns) {
        if (rows < 1 || columns < 1) {
            throw new IllegalArgumentException(
                    "Matrix must be at least 1x1, got " + rows + "x" + columns);
        }
        this.data = new int[rows][columns];
    }

    /**
     * Creates a matrix that holds the given two-dimensional array of cells.
     *
     * @param data the cells, must be non-null, rectangular and at least 1x1
     * @throws IllegalArgumentException if {@code data} is {@code null}, has no rows, has no
     *                                  columns, has a {@code null} row, or is jagged
     */
    public Matrix(int[][] data) {
        if (data == null) {
            throw new IllegalArgumentException("Matrix data must not be null");
        }
        if (data.length == 0) {
            throw new IllegalArgumentException("Matrix data must have at least one row");
        }
        if (data[0] == null || data[0].length == 0) {
            throw new IllegalArgumentException("Matrix data must have at least one column");
        }
        for (int r = 0; r < data.length; r++) {
            if (data[r] == null || data[r].length != data[0].length) {
                throw new IllegalArgumentException("Row " + r + " is null or has the wrong length");
            }
        }
        this.data = new int[data.length][];
        for (int r = 0; r < data.length; r++) {
            this.data[r] = data[r].clone();
        }
    }

    /**
     * Returns the cells of this matrix.
     *
     * @return the current values, {@code result[row][column]}
     */
    public int[][] getData() {
        return data;
    }

    /**
     * Replaces all cells of this matrix.
     *
     * <p>{@code GameOfLife.step()} uses this method to install the next generation.</p>
     *
     * @param data the new cells, must be non-null, rectangular and at least 1x1
     * @throws IllegalArgumentException if {@code data} is not a legal matrix body
     */
    public void setData(int[][] data) {
        if (data == null) {
            throw new IllegalArgumentException("Matrix data must not be null");
        }
        if (data.length == 0) {
            throw new IllegalArgumentException("Matrix data must have at least one row");
        }
        if (data[0] == null || data[0].length == 0) {
            throw new IllegalArgumentException("Matrix data must have at least one column");
        }
        for (int r = 0; r < data.length; r++) {
            if (data[r] == null || data[r].length != data[0].length) {
                throw new IllegalArgumentException("Row " + r + " is null or has the wrong length");
            }
        }
        this.data = data;
    }

    /**
     * Returns the number of rows of this matrix, i.e. the first dimension of the array.
     *
     * @return the row count, always at least 1
     */
    public int getRows() {
        return data.length;
    }

    /**
     * Returns the number of columns of this matrix, i.e. the second dimension of the array.
     *
     * @return the column count, always at least 1
     */
    public int getColumns() {
        return data[0].length;
    }

    /**
     * Returns the value of a single cell.
     *
     * @param row    the row index, {@code 0 <= row < getRows()}
     * @param column the column index, {@code 0 <= column < getColumns()}
     * @return the value stored in that cell
     * @throws IndexOutOfBoundsException if the coordinate is outside the matrix
     */
    public int get(int row, int column) {
        if (row < 0 || row >= getRows() || column < 0 || column >= getColumns()) {
            throw new IndexOutOfBoundsException("(" + row + ", " + column + ") is outside the matrix");
        }
        return data[row][column];
    }

    /**
     * Stores a value in a single cell.
     *
     * @param row    the row index, {@code 0 <= row < getRows()}
     * @param column the column index, {@code 0 <= column < getColumns()}
     * @param value  the value to store
     * @throws IndexOutOfBoundsException if the coordinate is outside the matrix
     */
    public void set(int row, int column, int value) {
        if (row < 0 || row >= getRows() || column < 0 || column >= getColumns()) {
            throw new IndexOutOfBoundsException(
                    "(" + row + ", " + column + ") is outside the matrix");
        }
        data[row][column] = value;
    }
}
