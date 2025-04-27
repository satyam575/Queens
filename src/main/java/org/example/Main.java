package org.example;

import org.example.display.TerminalDisplayer;
import org.example.display.WindowPaneDisplayer;
import org.example.extractor.ImageBoardExtractor;
import org.example.service.BoardGameService;
import org.example.solver.QueensBacktrackingSolver;

import javax.swing.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        // Open file chooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Queens Game Screenshot");

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String imagePath = selectedFile.getAbsolutePath();
            System.out.println("Selected file: " + imagePath);

            try {
                String gridSizeInput = JOptionPane.showInputDialog(null,
                        "Enter grid size (example: 9 for 9x9 board):",
                        "Grid Size Input",
                        JOptionPane.QUESTION_MESSAGE);

                if (gridSizeInput == null || gridSizeInput.trim().isEmpty()) {
                    throw new RuntimeException("Grid size input was cancelled or empty.");
                }

                int gridSize = Integer.parseInt(gridSizeInput.trim());
                // Solve the board
                BoardGameService gameService = new BoardGameService(
                        new ImageBoardExtractor(),
                        new QueensBacktrackingSolver(),
                        new WindowPaneDisplayer()
                );

                gameService.solveGame(imagePath,gridSize);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error occurred: " + e.getMessage());
            }

        } else {
            System.out.println("No file selected. Exiting.");
        }
    }
}