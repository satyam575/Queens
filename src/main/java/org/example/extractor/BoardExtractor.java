package org.example.extractor;


import org.example.model.Board;

public interface BoardExtractor {
    Board extractBoard(String inputSource, int gridSize);
}