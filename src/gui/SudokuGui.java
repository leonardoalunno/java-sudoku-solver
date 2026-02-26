package gui;

import core.SudokuSolver;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SudokuGui extends JFrame {

    private static final int GRID_SIZE = 9;
    private JTextField[][] cells = new JTextField[GRID_SIZE][GRID_SIZE];
    private SudokuSolver solver;

    public SudokuGui() {
        solver = new SudokuSolver();

        setTitle("Sudoku Solver (Backtracking)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout());

        // Top Panel: Title
        JLabel titleLabel = new JLabel("Java Sudoku Solver", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Center Panel: Sudoku Grid
        JPanel gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        gridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        Font font = new Font("SansSerif", Font.BOLD, 20);

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cells[row][col] = new JTextField();
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                cells[row][col].setFont(font);

                // Add Document Filter to restrict input to 1 digit (1-9)
                ((AbstractDocument) cells[row][col].getDocument()).setDocumentFilter(new DigitFilter());

                // Enhance borders to make 3x3 sub-grids visible
                int top = (row % 3 == 0) ? 2 : 1;
                int left = (col % 3 == 0) ? 2 : 1;
                int bottom = (row == 8) ? 2 : 1;
                int right = (col == 8) ? 2 : 1;
                Border border = new MatteBorder(top, left, bottom, right, Color.BLACK);

                // Add soft generic background
                cells[row][col].setBorder(new CompoundBorder(border, BorderFactory.createEmptyBorder(2, 2, 2, 2)));

                gridPanel.add(cells[row][col]);
            }
        }
        add(gridPanel, BorderLayout.CENTER);

        // Bottom Panel: Controls
        JPanel buttonPanel = new JPanel();
        JButton solveButton = new JButton("Solve");
        JButton clearButton = new JButton("Clear");

        solveButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));

        solveButton.addActionListener(this::handleSolve);
        clearButton.addActionListener(e -> clearBoard());

        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Reads the current UI inputs, calls the Solver, and updates the UI if a
     * solution is found.
     */
    private void handleSolve(ActionEvent e) {
        int[][] board = new int[GRID_SIZE][GRID_SIZE];

        // 1. Read input from text fields into the integer matrix
        try {
            for (int row = 0; row < GRID_SIZE; row++) {
                for (int col = 0; col < GRID_SIZE; col++) {
                    String text = cells[row][col].getText().trim();
                    if (text.isEmpty()) {
                        board[row][col] = 0;
                    } else {
                        board[row][col] = Integer.parseInt(text);
                    }
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input detected. Please enter numbers 1-9 only.");
            return;
        }

        // 2. Attempt to solve
        if (solver.solve(board)) {
            // 3. Update the UI with the solved numbers
            for (int row = 0; row < GRID_SIZE; row++) {
                for (int col = 0; col < GRID_SIZE; col++) {
                    cells[row][col].setText(String.valueOf(board[row][col]));
                    cells[row][col].setForeground(Color.BLUE); // Solved numbers are blue
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Unsolvable Sudoku! Please check your initial numbers.");
        }
    }

    /**
     * Clears all fields and resets colors.
     */
    private void clearBoard() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cells[row][col].setText("");
                cells[row][col].setForeground(Color.BLACK);
            }
        }
    }

    /**
     * Helper DocumentFilter to restrict JTextField input to single digits (1-9)
     */
    private static class DigitFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            if (string == null)
                return;
            if (isValid(fb.getDocument().getLength(), string)) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (text == null)
                return;
            if (isValid(fb.getDocument().getLength() - length, text)) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        private boolean isValid(int currentLength, String text) {
            // Maximum length of 1, and string must be a digit between 1 and 9
            if (currentLength + text.length() > 1)
                return false;
            return text.matches("[1-9]");
        }
    }

    // Main Entry Point
    public static void main(String[] args) {
        // Run GUI on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            try {
                // Look and feel for better UI on Mac and Windows
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new SudokuGui();
        });
    }
}
