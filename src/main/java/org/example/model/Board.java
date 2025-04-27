package org.example.model;

public class Board {

    int size;
    private int[][] matrix;

    public Board(int size, int[][] matrix) {
        this.size = size;
        this.matrix = matrix;
    }

    public int getSize() {
        return size;
    }

    public int[][] getMatrix() {
        return matrix;
    }
}
