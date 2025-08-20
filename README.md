
# Tetravex Solver

Java implementation of the **Tetravex** edge‑matching puzzle and a solving algorithm for the game. You play and/or solve from a **console driver** called `PlayTetravex`.  
Pieces are **not rotated** in this version of the game.

---

## How to run (interactive console)

### Option A — plain `javac/java`
```bash
# From the project root (where pom.xml, src/ live)
javac -d target/classes src/main/java/*.java src/main/java/org/**/*.java 2>/dev/null || true
javac -d target/classes src/main/java/*.java

# Run the interactive console
java -cp target/classes PlayTetravex
```

### Option B — Maven (for tests + compile)
```bash
# Run unit tests
mvn -q test

# Compile classes, then run PlayTetravex
mvn -q -DskipTests package
java -cp target/classes PlayTetravex
```

When `PlayTetravex` starts, it will **prompt for a puzzle file path**. Use one of the included samples (e.g., `test4.txt`) or your own file (see *Input file format*).

---

## Input file format

Two parts (space‑separated integers):

1) **Grid size:** `width height` on one line.  
2) **Pieces:** one line per piece with five integers:  
   `name top right bottom left` (edges in **counter‑clockwise** order starting at **top**).

**Example**
```
2 2
4 7 11 12 5
1 5 14 8 11
2 2 3 5 4
3 6 4 7 4
```

> Sample files are in the project root (e.g., `test4.txt`, `test5.txt`, …).

---

## Using the console (commands)

After loading a puzzle, you can enter commands:

| Command | Syntax | What it does |
|---|---|---|
| **help** | `help` | Show available commands and syntax. |
| **print** | `print` | Print the current board (placed pieces) and the list of unplaced pieces. |
| **place** | `place <pieceId> <x> <y>` | Attempt to place a piece by **name** at column **x**, row **y** (both **1‑indexed**). Fails if occupied/incompatible with neighbors. |
| **remove** | `remove <x> <y>` | Remove the piece at (**x**, **y**) and return it to the unplaced list (if any). |
| **solve** | `solve` | Clear the board and run the automatic solver. Prints whether a solution was found, then `print()` the board. |
| **quit** | `quit` | Exit the program. |

**Coordinate system:** leftmost column is **x = 1**; bottom row is **y = 1**. (Coordinates are mapped internally as in the assignment spec.)

### Example session
```
$ java -cp target/classes PlayTetravex
Enter the file name that contains the puzzle to load
test4.txt
# (puzzle loads)

help
# lists: print, place, remove, solve, quit

print
# shows a grid of xxx (empty) and the full unplaced list

place 4 1 2
# tries to put piece 4 at column 1, row 2

print
# see the updated grid and remaining unplaced pieces

solve
# clears the board, attempts a full solve, then prints the solved grid if found

quit
```

---

## Algorithm overview (high level)

- **Backtracking** search with **edge‑constraint checking**.
- At each cell, try only pieces whose **top/left** edges match existing neighbors; backtrack on conflicts.
- **Pruning/heuristics**: by filtering candidates early (based on placed neighbors) the search space is greatly reduced, allowing typical puzzles to be solved efficiently.
---

## Unit tests

Yes — there are **JUnit 5** tests to demonstrate correctness and edge cases.

- **Where:** `src/test/java/`
    - `PieceTest.java`, `BoardTest.java`, `TetravexTest.java`
- **How to run:** `mvn -q test`

Tests cover: loading puzzles, placement validity, printing empty/partial/complete boards, and solving on small boards (plus error cases).

---

## Public API (for reference)

The main solver lives in `Tetravex`:

```java
boolean loadPuzzle(BufferedReader stream);
boolean solve();
boolean placePiece(int pieceName, int x, int y); // x: column, y: row (1‑indexed)
boolean removePiece(int x, int y);
String  print();  // placed grid, dashed line, unplaced list
```

- `print()` renders the placed grid as **tab‑separated** entries (or `xxx` for empty), one line per board row (top to bottom), followed by a dashed line and a list of unplaced pieces in the form `name\ttop\tright\tbottom\tleft`.
- `solve()` uses backtracking with constraint checks; the board is cleared before solving so the search starts from a clean state.

---

## Repository layout

```
Tetravex/
├─ src/
│  ├─ main/java/
│  │  ├─ PlayTetravex.java
│  │  ├─ Tetravex.java
│  │  ├─ Board.java
│  │  └─ Piece.java
│  └─ test/java/
│     ├─ PieceTest.java
│     ├─ BoardTest.java
│     └─ TetravexTest.java
├─ test4.txt  # sample inputs (others: test1.txt ... test14.txt)
├─ pom.xml
└─ README.md
```

---

## License / academic integrity

Generative AI **not used** in source code or unit test development but used for test case and documentation (this file) creation. This repository is for **educational purposes**. Please don’t submit it as your own work if you’re taking a similar course.  

## Author
Maxime Bouclin  
Bachelor of Computer Science and Mathematics, Dalhousie University  