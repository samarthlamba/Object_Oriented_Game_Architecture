## Final Team 04: Super CS307 Bros

### Team Members and Roles:

Matt Bowman (mab181) - Loader, Driver/Controller
Joe Heflin (jnh24) - View
Sam Lamba (sl562) - Game Objects, Animations
Roshni Penmatcha (rkp13) - Game Engine and Implementations

### Design Goals

One of the major goals for this project was for the implementation of features to be extremely data driven. We wanted hings like level layout, level physics, images and animations, and even block/entitiy collision behaviors to be driven entirely from data files, so that adding new implementations of games or new levels of existing games with interesting behaviors is as easy as possible. Adding new types of obstacles/entities and new games should be very simple, and should be able to use many of the existing pieces of code. These design goals led to the following features:

* Levels can be hand-crafted without writing any code through files
* Any block/entity can work in any level with any game if files are changed correctly
* Any game-level constants can be changed on a level-by-level basis in properties files
* Any physics engine constants can be changed on a level-by-level basis in properties files
* Collision behaviors for blocks can be edited in properties files
* Images used to represent entities and obstacles can be changed on a game by game and level by level basis through files
* New buttons that point to new levels can be added by adding information to files
* New animations can be added to existing or new entities only by adding a file

### High Level Design

We have a basic Model-View-Controller setup, with the controller being comprised of the Driver class, the GameController, and the Loader module which manges data. The Driver extends Application and is the runnable program. It uses classes in Loader, namely GameFactory, to load the correct Game, which it updates in time with the level in the step function. The model is encompassed in the engine module and including Game and its implementations. Game keeps track of the rules that determine how game objects (entities/obstacles) interact. There are various helper classes like Collisions.java that do part of the work here, but the core of the model is the Game. The View is interacted with through the Display class, which is an API that enables the Driver to get scenes for specific game states based on information it extracts from Game. Display instantiates a number of specific subclasses for different phases of the Game like GamePlayScreen, MainMenuScreen, etc. that perform various functions. The Display affects Driver through the GameController class, an interface between them that Driver wraps its Stage and Timeline with and passes to Display.

### Assumptions

One of the basic assumptions we made was that an implementation of Game will always be won the same way. Since the physics and display of the game could change level-by-level, we wanted the victory condition to remain fairly distinct. This meant that it's difficult in our program to implement a specific level of Mario that does not win with a goal block, for instance. This seeemed a fairly safe assumption, but it did lead to some duplicate code in that the Lost Vikings and Mario games actually end the same way.

Another assumption we made was in writing our screen wrap code. For the games we looked at, the screen kept the player at the center and always translated so that they stayed there, so this is effectively hard-coded into our relevant View class, GamePlayScreen. This is actually fairly limiting, in that it prevents us from doing things like auto-scrollers, a popular level variety in many platformers, without substantial changes.

We also assumed that the core physics engine of a platformer would stay constant across games; that is, every game would have gravity, jumping, interaction between blocks, and solid objects. With the data-driven changes we made, especially in changing block collisions, it's actually possible to have blocks that don't have a collision on a particular side, or levels without gravity, so this seemed to be a fairly innocuous assumption; the code is there and is fairly generally applied, but if constants are set in files the right way, you can basically make any kind of physics engine you desire.

### Deviations from Plan

One deviation from our plan was that the "UI" Aspect of our initial mockup diagrams was wrapped up into the View. This change was made for several reasons. Firstly, the visual component of our UI, like buttons and the HUD, needed to have a View anyway, and the JavaFX library has ready-made solutions for many of the UI problems. Secondly, things like key presses and user interactions really took up very little code and there had to be code in the View that bound key presses to the JavaFX scene anyway.

The initial API mockups for game entities/obstacles were very limited. We added quite a lot of public methods to the classes that comprised game Objects as complexities arose that we did not foresee. There are also several APIs in place that are handled by various classes like the GamePlayScreen that hide some of this added complexity from classes external to the model.

### Adding New features

* To add new levels for an existing game, create a new CSV with appropriate blocks from the GAMENAMEClasses.properties file (or add new ones, if desired). Optionally, create a FILENAME.properties for the level by copying the default values for that game and modifying as desired.

* To add new level buttons, add a new l4 = some label for new level to gamemenubuttons_en.properties in src/view/resources, then add GAME,l4 = FILENAME to  LevelFileLocations.properties  in resources.

* To add a new game, create a new implementation of Game in src/engine/games that extends Game. Also create a Bean with any constants that in src/engine/games/beans that extends GameBean and make new implementation take that in constructor. Create a default values properties file that specifies default values for all these constants as well as those required by GameBean.

* To add a level for a new game, create a GAMENAMEClasses.properties file that lists the entiites,obstacles in levels as well as their classpaths (See examples).

* To add game to menu, add a new entry g4 to  mainmenubuttons_en.properties. Also add a new GamePlayScreen implementation in src/view/screens that passes its name to the super and optionally overrides title. Also make sure to add level file locations of implemented levels to the previously mentioned LevelFileLocations.properties.

* To add a new block or entity, create a new implementation of Obstacle/Entity that sets and values of the parent class as needed. Create a properties file named NEWBLOCK.properties that specifies its collision behavior in either src/engine/obstalces/resources or src/engine/entities/resources (depending on if it's an obstacle or entity).

* To use new blocks in levels, add a symbol for them to GAMENAMEClasses.properties in the list of expected obstacles/entities, then specify their classpath as demonstrated in the existing GAMENAMEClasses.properties.

* To add visuals for new blocks, add their images to src/view/resources/images and then add the path for each implemented level to the relevant game's GAMENAMEGameCharacterImagesDefault.properties in src/view/resources as block,LEVEL = IMAGEPATH

* To add animations to new or existing entities, add a spriteSheet to src/view/resources/images, then add a MarioGameENTITYIDAnimations.properties file to src/view/properties. This file is fairly specific; the x and y whitespace constants are the number of white pixels vertically and horizontally between sprites. The frames per row are how many sprites are in each horizontal row. width and height are dimensions of each sprite. For each animationState, there should be a duration in time and a length in sprites, as well as a position of first sprite.