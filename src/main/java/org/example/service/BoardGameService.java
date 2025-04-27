package org.example.service;

import org.example.display.Displayer;
import org.example.display.TerminalDisplayer;
import org.example.extractor.BoardExtractor;
import org.example.model.Board;
import org.example.solver.BoardSolver;

public class BoardGameService {

    private final BoardExtractor extractor;
    private final BoardSolver solver;
    private final Displayer resultDisplayer;

    public BoardGameService(BoardExtractor extractor, BoardSolver solver, Displayer resultDisplayer) {
        this.extractor = extractor;
        this.solver = solver;
        this.resultDisplayer = resultDisplayer;
    }

    public void solveGame(String inputSource, int gridSize) {
        Board board = extractor.extractBoard(inputSource,gridSize);
        boolean[][] solution = solver.solve(board);
        resultDisplayer.display(board, solution);
    }
}
