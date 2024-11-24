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

### Sprint Roles:

 - Bal - Information panel
 - Abdul - Menu bar and GUI window
 - Khalid - Next piece panel
 - Preston - Tetris game board

### Summary of changes
**Bal**:
For Sprint 1, my main task was to create the ScoreBoard class and make sure
the background was green. One issue that came up during testing was that the
green background wasn’t showing because the panels layered on top of it had a
different color. To fix this, the panels were set to be opaque so the background
could show through. Initially, a BorderLayout was used to organize the controls 
and score panels, but it didn’t scale well when resizing the window. Switching to
a GridLayout solved this problem by keeping the layout proportional, no matter the
window size. To make the scoreboard more useful and more aesthetic, placeholder text
was added for the score and level, and also added text instructions for the game 
controls. The text was centered within the panels to give it a cleaner and more polished
look. These small improvements went a bit beyond the basic requirements and helped make
the scoreboard more user friendly while setting it up for future features.

**Abdul**:
I created the Tetris game GUI in the TetrisGUI class and also a menu bar.
The TetrisGUI class combines and displays every other class that was created in
one window. The menu bar I created has options for starting a new game,
pausing/resuming gameplay, exiting the application, gameplay instructions, and
viewing an "About" section. 

**Khalid**:
I created the Next-piece panel. This panel has a blue background and as it stands
displays the "T" tetromino centered within the panel. The Next-Piece class retrieves
the Points that represent a piece's position on a grid and creates a Rectangle
according to those points. I centered these Tetrominos by adjusting the rectangle's
location in relation to the distance from the x and Y axis. All Tetrimnos except
the "I" Tetomino are centered using the original format. Due to issues that I 
encountered when centering the "I" Tetromino, there is a slightly modified code
to handle this specific case

**Preston**: 
I created the Tetris game board panel, contained in TetrisBoardPanel class.
This class creates a JPanel with a red background, and draws all 7 Tetris
pieces across the panel by creating new MovableTetrisPiece objects.
I also created a color scheme class so that our group can standardize the
colors we use for our Tetris pieces. Currently the panel is sized
200x400 pixels for right now with each block being 20x20, though I have
constructors set up so this can be altered if desired for a future feature.


#
### Problems
One issue we've had was sizing everything correctly. Using border layout
we ran into issues of having gaps or inconsistent sizing. I think we've
mitigated these issues for the most part, but we will probably meet
to discuss refinements to the layout sometime next week.

#
### Summary of updates



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
