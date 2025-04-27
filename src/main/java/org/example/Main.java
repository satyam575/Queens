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
                // Solve the board
                BoardGameService gameService = new BoardGameService(
                        new ImageBoardExtractor(),
                        new QueensBacktrackingSolver(),
                        new WindowPaneDisplayer()
                );

                gameService.solveGame(imagePath);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error occurred: " + e.getMessage());
            }

        } else {
            System.out.println("No file selected. Exiting.");
        }
    }
}