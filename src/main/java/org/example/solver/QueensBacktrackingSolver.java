package org.example.solver;

import org.example.model.Board;

/*
        RULES :
            1. one * per row
            2. one * per column
            3. no two *s diagonally adjacent
            4. one * per color region

        SAMPLE INPUT :
        int[][] board = {
                {1,1,1,1,1,1,2,2,2},
                {1,1,3,3,3,3,3,4,2},
                {1,1,3,5,5,5,3,4,4},
                {1,1,3,5,6,5,3,7,4},
                {1,1,3,6,6,5,3,7,4},
                {1,1,3,5,5,5,3,7,7},
                {1,8,3,3,3,3,3,9,7},
                {1,8,3,3,3,3,3,9,9},
                {8,8,8,8,8,9,9,9,9}
        };
*/
public class QueensBacktrackingSolver implements BoardSolver {

    int[][] board;
    boolean[][] crowns;
    boolean[] colorUsed;
    int n = 9;

    @Override
    public boolean[][] solve(Board board) {
        this.board = board.getMatrix();
        this.crowns = new boolean[9][9];
        this.colorUsed = new boolean[10]; // Colors from 1 to 9
        helper(0);
        return crowns;
    }
    boolean helper(int row){
        if(row == 9) return true;
        for(int col = 0; col < n ;col++){
            if(isValid(row,col)){
                crowns[row][col] = true;
                colorUsed[board[row][col]] = true;

                if(helper(row+1))
                    return true;

                crowns[row][col] = false;
                colorUsed[board[row][col]] = false;
            }
        }
        return false;
    }

    boolean isValid(int row, int col){
        // check if there is a crown in the same row
        for(boolean present : crowns[row])
            if(present) return false;

        // check if there is a crown in the same column
        for(int i =0 ; i <n ; i++)
            if(crowns[i][col]) return false;

        // Check diagonally adjacent (all 4 directions)
        int[] dx = {-1, -1, 1, 1};
        int[] dy = {-1, 1, -1, 1};

        for(int d = 0; d < 4; d++){
            int newRow = row + dx[d];
            int newCol = col + dy[d];
            if(newRow >= 0 && newRow < n && newCol >= 0 && newCol < n){
                if(crowns[newRow][newCol]) return false;
            }
        }

        // Check color region
        int color = board[row][col];
        if(colorUsed[color]) return false;

        return true;
    }
}
