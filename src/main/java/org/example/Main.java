package org.example;

import org.example.display.TerminalDisplayer;
import org.example.extractor.ImageBoardExtractor;
import org.example.service.BoardGameService;
import org.example.solver.QueensBacktrackingSolver;

public class Main {
    public static void main(String[] args) {
        BoardGameService gameService = new BoardGameService(
                new ImageBoardExtractor(),
                new QueensBacktrackingSolver(),
                new TerminalDisplayer()
        );

        gameService.solveGame("../screenshots/queens.png");
    }
}