# 🧩 Java Sudoku Solver (Backtracking & Swing)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Algorithms](https://img.shields.io/badge/Algorithm-Backtracking-blue?style=for-the-badge)
![UI](https://img.shields.io/badge/UI-Java_Swing-lightgrey?style=for-the-badge)

A sleek, Java-based Desktop application that solves any valid 9x9 Sudoku puzzle instantly using a recursive **Backtracking Algorithm**. The project features a clean Graphical User Interface (GUI) built with **Java Swing**.

## 📖 Overview

This project highlights core Computer Science and Software Engineering principles:
1. **Algorithmic Efficiency:** Implementation of a classic recursive backtracking algorithm to explore and prune decision trees.
2. **Object-Oriented Design (OOP):** Clear separation of concerns between the core logic engine (`SudokuSolver.java`) and the visual presentation layer (`SudokuGui.java`).
3. **Event-Driven Programming:** Handling user interactions and state changes via ActionListeners in Java Swing.

<div align="center">
  <img src="docs/gifs/screenshot.gif" alt="Sudoku Solver GUI in Action" width="450"/>
</div>

## 🧠 The Algorithm: How it Works

The core of this solver is a **Recursive Backtracking Algorithm**, a classic depth-first search approach to constraint satisfaction problems. 

1. **Find an empty cell:** The algorithm scans the 9x9 grid to find the first cell that doesn't contain a number (0).
2. **Guess a number (1-9):** It attempts to place a digit from 1 to 9 in that cell.
3. **Validate the guess:** It checks if the digit violates any Sudoku rules (is it already in the same row, column, or 3x3 subgrid?).
4. **Recursion:** If valid, it recursively calls itself to solve the *next* empty cell.
5. **Backtrack:** If a guess leads to a dead end (no valid numbers can be placed in subsequent cells), it "backtracks", resetting the current cell to 0 and trying the *next* possible digit.

This systematic exploration guarantees a solution (if one exists) by pruning invalid paths early in the decision tree.

## ⚙️ Engineering Highlights

*   **Optimized Backtracking:** The solver (`src/core/SudokuSolver.java`) intelligently checks constraints (row, column, and 3x3 sub-grid) before placing a number, significantly reducing the algorithmic search space.
*   **Input Validation:** The GUI employs a custom `DocumentFilter` (`DigitFilter`) to strictly allow only single-digit inputs (1-9) at the keystroke level, ensuring engine stability.
*   **Intuitive UI / UX:** Built with `JFrame` and `JPanel`, featuring dynamic compound borders that mathematically render the thick 3x3 Sudoku sub-grids natively without external graphical assets.

## 📁 Directory Structure

```text
java-sudoku-solver/
├── src/
│   ├── core/
│   │   └── SudokuSolver.java    # Core recursive logic
│   └── gui/
│       └── SudokuGui.java       # Swing user interface
├── bin/                         # Compiled .class files (git-ignored)
├── docs/                        # Screenshots and documentation
├── .gitignore                   # Standard Java ignore rules
└── README.md                    # Project documentation
```

## 🚀 How to Run

1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/leonardoalunno/java-sudoku-solver.git
   ```
2. Navigate to the root directory and compile the Java source files:
   ```bash
   javac -d bin src/core/*.java src/gui/*.java
   ```
3. Run the application:
   ```bash
   java -cp bin gui.SudokuGui
   ```
4. **Usage:** Enter your known Sudoku numbers into the grid and click **Solve**. The algorithm will fill in the remaining blue numbers instantly. Click **Clear** to reset the board.

## 👨‍💻 Author

**Leonardo Alunno**  
*Aspiring Computer Engineer*  
🔗 [LinkedIn](https://www.linkedin.com/in/leonardo-alunno-3095922b7)
