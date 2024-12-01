# TCSS305-Group6-Tetris-Sprint2

## Sprint 2 Contribution

- **Abdulrahman Hassan**  - Worked in updating the GUI to add things such as PropertyChangeListener and Timer that ticks every 500 milliseconds with an ActionListener that calls to the Board.step() method.


- **Khalid Rashid**  - I worked on the Model update, I implemented property Change Support in the Board and Board interface. I reviewed the Board Class, determining when the state was changed, and what state changed, and then fired Property changes Accordingly. I also altered the Board class to implement the singleton design pattern. I dealt with issues that arose in the sandbox class, and as a result, I edited it to retrieve the Instance of the Board. I also implemented the Property Change Listener in the NextPiece Panel, adjusting it so that it draws the Next Tetris Piece.   


- **Preston Sia**  - Focused on the Tetris Board, implementing PropertyChangeListener
to listen for events from the board model. There was initally some miscommunication and
ambiguity with the the property changes, which we eventually sorted out after working
with the Tetris JPanel and Board class a little bit. The board listens for events
when the list of frozen pieces changes, when the active pieces change, and when
the state of the game (game over) changes. This panel acts on property changes to
draw the Tetris board and draws the board for actual gameplay. Additionally, I also
fixed a few issues with TetrisGUI.


- **Balkirat Singh**  - I worked on implementing the functionality for the primary model object (Board) by adding a factory method and making sure everything followed the interface defined in the Model. I added a KeyListener to handle piece movement (left, right, down, drop, and rotate), making sure it only interacted with the Board API and didn’t directly change the GUI. I also added a listener to the "New Game" menu button so it resets the Board and starts the timer when selected. I tinkered with checkstyle validation in my branch, though we ultimately decided to go with another group member’s version. On top of that, I recorded the minutes for the first meeting and created the template for the readme.md.

## Sprint 2 Meetings

### Meeting Minutes
Link to Google Doc on Meeting Notes for Sprint 2:
https://docs.google.com/document/d/18yKiRCG6CYQYDxzpUA03e5I1bip3m8ceQb8LAv9O7XE/edit?usp=sharing

### Other Meetings
- **When**: 2:15 pm - 3:15 pm, November 29, 2024
- **Where**: Discord (Online)
- **How**: Voice Call
- **What**: We discussed changes we made to the GUI, TetrisPanel, and suggested any improvements we can make. 

### Sprint 2 Communication

- **Discord Group Chat/Calls**: Used for quick updates and discussions about task progress and challenges.  
- **GitHub**: Facilitated code reviews, pull request discussions, and task management.  
- **In-Person**: Our first major meeting was done in person to assign a rough outline of the roles

## Sprint 2 Comments
- **Issue 1**: In the TetrisBoardPanel we have a small checkstyle warning in which we have 12 exectuable statments instead of the 10. We will look to fix this in the 3rd sprint.
- **Idea 1**: Khalid would like to tinker around with the round edges that we see in the mac version when playing tetris (just something cosmetic).
- **Code Weirdness 1**: N/A
