# TCSS305-Group6-Tetris-Sprint3

## Sprint 3 Contribution

- **Abdulrahman Hassan**  - 

- **Khalid Rashid**  - For sprint 3 I prioritized working on the Tetris GUI and visual displays. I modified the layout to create Borders on each panel. I added A new class Called PauseEndPanel that displays An overlay panel with a message for when the Game is over or paused. I also added an extra credit feature called "Hard Mode". I created a TetrisMenuBuilder class that resolved the "too many Methods" warning but this would require that the TetrisGUI has public methods so this class was removed and is currently within the sp3-Khalid-RefactorTetrisGUI Branch. I also Modified the Menus in the Tetris GUI to fix all checkstyle warnings and added JavaDoc. I created an Interface for the PauseEndPanel as well.

- **Preston Sia**  - For Sprint 3, I focused on adding the ghost piece and its functionality, reworking much of the code for the score panel, and refining the high score classes. I also contributed minor fixes throughout the project, assisted with Git management, found a new music track to use, and updated the Tetris game’s color scheme 
  
- **Balkirat Singh**  - For Sprint 3, I focused on managing the Sprint 3 meetings and implementing sound features for our Tetris game. I organized the meeting sheet and created a README template to keep everything aligned with the submission guidelines. I also added background music as an extra credit feature and introduced a mute/unmute function that can be toggled with the "M" key. Another feature I added was the ability to toggle sound on and off through the options menu on the interface. These updates made the game more user friendly while keeping our work well organized for this sprint. Also I looked thorugh any checkstyles issues we might face.

## Sprint 3 Meetings

### Meeting Minutes
Link to Google Doc on Meeting Notes for Sprint 3:
https://docs.google.com/document/d/1t1GBRbn3ie9kqzf1RISrKNz63RstirhpWDuxcXZ7i9c/edit?usp=sharing

### Other Meetings
- **When**: Friday, December 5, 10:45-11:30
- **Where**: Online
- **How**: Through Voice Call
- **What**: Balkirat wanted to push a couple changes that he made do his Music class and Preston assisted him in making a pull request in which others could aprove on his changes before merging. Also talked about thing the like the ghost peice and diffent sound files.

### Sprint 3 Communication

- **Discord Group Chat/Calls**: Used for quick updates and discussions about task progress and challenges.  
- **GitHub**: Facilitated code reviews, pull request discussions, and task management.  
- **In-Person**: Our first major meeting was done in person to assign a rough outline of the roles

## Sprint 3 Comments
### Location of Implementation -
Code that calculates the amount of line to reach the next level -
- Class - view/score/ScoringSystem
- Line # - 197

Location of scoring algorithm -
- Class - view/score/ScoringSystem

### Issues -
- **Issue 1**: - Linting - 'public' method 'paintComponent()' is not exposed via an interface - result of swing so we are choosing to ignore (it is an overridden class)
- **Issue 2**: - Law of Demeter - Choosing to ignore
- **Issue 3**: - Too many Fields/Methods - so we tried to fix this issue by spitting up the TetrisGUI into multiple classes but that ended up causing more problems then expected with little time to resolve so we choose to revert to original with said warnings.
- **Idea 1**: N/A
- **Code Weirdness 1**: Linting Tool settings were overridden - we attempted to restore the settings based on the original configuration file but if there are any unforeseen warnings it is likely the result of the differences between our linting tool settings and the original configuration.

**Required Extra Feature**

- JAR File - A runnable JAR file of the Tetris game was implemented. It is in the JAR folder in the root.

**Extra Credit Features**

- Save Highscore - Users can save their scores at the end of each game and have the option to clear the leaderboard whenever they choose
- Ghost Piece - The ghost piece feature displays an outline of where the current piece will land and can be toggled on or off in the options menu.
- Background Music - Added background music to the Tetris game that starts automatically when a new game begins and stops when the game is paused or ends. Implemented functionality where pressing the M key toggles the music on and off, allowing players to control the background music easily.
- Toggle Music ON/OFF - Added an option in the game’s settings menu that allows players to toggle the background music on and off directly through the interface. This feature provides an additional, user-friendly way to control the music alongside the M key functionality.
- Hard Mode - An option to enter "Hard Mode". This disables the grid Lines and Ghost Piece. It also limits the number of times a user can Rotate a piece to 4 times (achieving a full piece rotation).








  
