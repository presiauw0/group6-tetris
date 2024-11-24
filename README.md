# TCSS305-Group6-Tetris

## TCSS 305 Tetris - SPRINT 1

Autumn 2024

Group 6 Members:
-  Balkirat Singh
-  Khalid Rashid
-  Preston Sia
-  Abdulrahman Hassan

Link to Google Doc on Meeting Notes for Sprint 0:
https://docs.google.com/document/d/1G0hNd6BlmNTyfMC1TWxJMaIVKSBLcyC-dv-69-_FAz0/edit?usp=sharing

Roles:

 - Bal - Information panel
 - Abdul - Menu bar and GUI window
 - Khalid - Next piece panel
 - Preston - Tetris game board

### Summary of changes
**Bal**:


**Abdul**:


**Khalid**:


**Preston**: 
I created the Tetris game board panel, contained in TetrisBoardPanel class.
This class creates a JPanel with a red background, and draws all 7 Tetris
pieces across the panel by creating new MovableTetrisPiece objects.
I also created a color scheme class so that our group can standardize the
colors we use for our Tetris pieces. Currently the panel is sized
200x400 pixels for right now with each block being 20x20, though I have
constructors set up so this can be altered if desired for a future feature.


#
#



Updated from sprint0 to include:

- Classes for the following GUI panels (see "view" package):
  * Next Piece (NextPeice)
  * Information and scoreboard (ScoreBoard)
  * Tetris game board (TetrisBoardPanel)
  * Main window with a menu bar and panels (TetrisGUI)

Updated from initial clone to include:

 - Interfaces for Three classes
   * Board
   * Point
   * MovableTetris piece
 - Loggers in the SandBox class to replace System.out.print messages
   Warnings in classes resolved and suppressed according to requirements


Initially Included:

- Checkstyle rule structure
- IntelliJ Clean-code rules
- Package structure
- Driver class with LOGGER object and examples
- Unit test folder
- .gitignore and README.md
