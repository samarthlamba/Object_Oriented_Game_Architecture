1) The player hits the wall
    * The physics engine recognizes the collision between objects
    * The engine stops any movement in that direction of the object.
2) The player jumps off a platform
    * The physics engine provides constant downward force
    * The platform continously would have kept the player up, but upon its disappearence, the player will start to fall
3) A user clicks an arrow associated with movement
    * Key is read and recognized 
    * Player object is triggered to move to the right based on the speed of movement 
    * Based on the new direction, front end updates to move the player object
4) The player gets a powerup to change speed
    * The powerup is absorbed by the player and it's front end object disappers (after collision)
    * The powerup triggers updating of the pre-set speed value and updates it
    * Change in x and y direction changes based on the new speed triggering faster movements
5) Life is lost
    * A collision happens triggering loss of Life
    * Life value decreases and a check is made to confirm that lives left is greater than 0
    * If lives are less than 0, game finishes. Cue end screen
    * If lives are greater than 0, the HUD updates on the front end to showcase the decrease of Life
6) Data read in to create the obstacles block
    * The data read in provides the locations of the block and the sizes
    * This triggers a block object to be created in the appropriate location in the screen
    * The block object is stationary and unmoving
7) Player reaches edge of screen
    * The Display no longer keeps the displayed location of the player constant, but keeps
    the location of background features constant and the player moves relative to them.
8) User selects game to play
    * Method in the UI/Driver interface calls a method in Driver which uses reflection to declare
    and initialize an extension of Game. 
    * Driver starts gameplay when prompted by the UI when a level is selected
9) User completes every level in the game
    * Game/UI interface used to notify display that game is won when Display checks game 
    status. 
    * Display adds cut screen to stage to indicate game is won
    * Buttons provided to navigate to specific menus or just to restart.
10) User wants to change keys used
    * Navigate from menu to settings scene by clicking button. Method called in MainMenuScreen.
    * Using drop down menus that read from properties files, set instance variables in the
    user class to the selected key.
11) Player moves to the right in middle of map
    * The Display is provided with the player's position
    * If the edge of the map isn't visible within the scope of the stage, the player's
    position on the stage is held constant and all other entities/obstacles move left with
    respect to the player.
12) User wants to play Mario game
    * Button is pressed on UI that passes String to Driver
    * String is passed to Loader to get correct implementation of Game for Mario
13) Data read in to create Entities from map
    * Level data is made with characters representing entities
    * Loader reads in character and uses properties file to convert chacacter to class name
    * Reflection is used to create new instance of correct entity from class name
14) The player finishes a level and proceeds to the next
    * The Driver checks if the level is finished and, if it is, gives the loader string for next level
    * loader loads all game objects into next Game object for Driver
15) The user wants to test their own level configuration by editing a text file
    * The user adds a CSV of the level layout to the data root
    * The user passes the string of the new file's name to the Loader
    * The loader converts those characters to Game objects
16) Player dies in a level
    * Some death animation plays
    * The player is moved in Game back to its initial "spawn" position
    * The Display wrapping Game also moves back to the player's initial "spawn" position
17) User presses escape key
    *  Key is read and recognized 
    *  Correct Screen is generated through reflection 
    *  Screen is set to primary stage with buttons to resume or go to main MainMenuScreen
18) The player jumps up in air
    * Jump key press recognized
    * Initial jump force is applied on player
    * The simultaneous physics engine provides constant downward force
    * Creates overall jump motion of player and lands in initial location
19) Player collids with enemy
    * Detects collision between enemy and player
    * Adjusts the health level appropriately for the entities
20) Player destroys obstacles
    * The physics engine recognizes the collision between player and obstacles
    * Checks if obstacle can be destroyed
    * The front end object disappers (after collision if obstacle is destroyable)
    * Player can move through now open space

