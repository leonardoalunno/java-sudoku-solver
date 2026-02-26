package core;

public class SudokuSolver {

    private static final int GRID_SIZE = 9;

    /**
     * Solves the given 9x9 Sudoku board using a backtracking algorithm.
     * 0 represents empty cells.
     *
     * @param board The 9x9 2D array representing the board
     * @return true if the board was solved successfully, false if no solution exists
     */
    public boolean solve(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                
                // Find an empty cell
                if (board[row][col] == 0) {
                    
                    // Try all possible numbers for the empty cell
                    for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
                        
                        if (isValidPlacement(board, numberToTry, row, col)) {
                            board[row][col] = numberToTry; // Place the number
                            
                            // Recursively attempt to solve the rest of the board
                            if (solve(board)) {
                                return true;
                            } else {
                                // If it doesn't lead to a solution, backtrack
                                board[row][col] = 0;
                            }
                        }
                    }
                    // If we tried all numbers 1-9 and none worked, the board is unsolvable
                    return false;
                }
            }
        }
        // If we filled the entire board and found no empty cells, it's solved
        return true;
    }

    /**
     * Checks if placing a number at a specific row and column is valid.
     */
    private boolean isValidPlacement(int[][] board, int number, int row, int col) {
        return !isNumberInRow(board, number, row) &&
               !isNumberInColumn(board, number, col) &&
               !isNumberInBox(board, number, row, col);
    }

    private boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInColumn(int[][] board, int number, int col) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][col] == number) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInBox(int[][] board, int number, int row, int col) {
        int localBoxRow = row - row % 3;
        int localBoxColumn = col - col % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }
}
