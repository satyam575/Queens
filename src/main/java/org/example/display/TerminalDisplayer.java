package org.example.display;

import org.example.model.Board;

public class TerminalDisplayer implements Displayer {
    @Override
    public void display(Board board, boolean[][] matrix) {
        final String RESET = "\u001B[0m";
        final String GREEN = "\u001B[32m";

        for (boolean[] row : matrix) {
            for (boolean cell : row) {
                if (cell) {
                    System.out.printf(GREEN + "%-6s" + RESET, "true");
                } else {
                    System.out.printf("%-6s", "false");
                }
            }
            System.out.println();
        }
    }
}
