# Design Plan
## Names: Sam, Roshni, Joe, Matt


## Final Project

### Introduction
For the final project, Super CS307 bros will be creating a scrolling platformer game capable of handing multiple different variations on the platformer genre with minimum edits to the basic infrastructure of the game. The primary design goals are to have a single player game architecture such that any new game only needs to add fundamental attributes of the game to start (ie: things like rules, sizes, and restrictions). Gravity and physics engine will be constant throughout the different types of games. Engine classes (enemy, player) will have certain information open such as the coordinates of the object. The view will be mostly closed. The loader will provide the ncessary information so will be open. At a high level, we will have a User  Interface capable of taking in decisions by the user (such as what game to play, what direction to move, pause and such), a backend that will keep track of the different objects and incite their motion, a physics engine that will act uniformally on all objects, and a connection between the back and front end. By the usage of abstact classes we hope to achieve flexiblity to add new games easily. Overall, the final product will be closed for modification, but open for extension. We will have the opportunity to extend various classes such as entities, game, and such to allow for new types of games. We also hope that the main addition needed to create a new game is the "rules" aspect of the game. The additions will be needed to add very specific details to the game. 

### Overview

The project has been divided up into a couple a distinct parts. We will have a loader that gets information from Data files, reads parameters, and gets the initialization information. We need this to have the flexibiity to change and update levels and also to change what objects are being displayed. Next, we have the Engine.  The Engine is going to be handling the physics of the project and also take care of the game entities (player and enemy). This allows us the flexibility to change the type of players and their attributes for different games. We will lso have obstacles which will be the constant obstacle objects. These are distinctly different from the entitities as they act only as a stopper rather than an interactable element. Finally we will have a view. The view will have all the front end: buttons, splash screen, main menu, etc. 

The Driver class will act as the main of the project The loader will get information from data files and provide them to the Driver. The driver will use the information to provide the input parameters and create the necessary view. The view will take in user items and will let the driver know what game to create. Based on this the driver will create the requested game items (using the aforementioned loaded parameter value). The game will create the different objects and the front end will display the said objects. 

Below is a picture of the basic class layout:

![Basic Class Layout](doc/BasicClassLayout.png)


As can be seen above, we will have the entitity model taking care of the physical objects that are moving around. A games module taking care of the games, obstacles taking care of the obstacles, loader and view taking care of input and UI. Below is the connections between these modules:

![Desing Overview](doc/Super307_uml.png)



### Design Details

##### Engine
The engine will have a couple of sub packages. It will contain the games, obstacles, and entitities. The engine will control the overall game play. It will control the characteristics of the game components (player, obstacles, enemy). It will also have the updated information for the HUD. It will also contain the overall physics engine. 

The reason for this is simply because it controls the main components of the game. It has players and other important metrics. We are also considering having submodule to combine all the information together in the game engine and thus by create a seperation. As can be seen in the above UML diagram, engine will collobarate with the game extensively.

The engine will create specific entities, hold it's coordinates and attributes, and act as the information hub for the object. It will need information from Game to determine the sizes and positions of the object. It can be extended by keeping Entity as an abstract and allowing for different implemetation of the characters. 

We wanted to create this module so all the brain work is in one place and seperate from the front end. We won't have to have JavaFX attributes here and that would be a good thing. In addition, we can easily create new types of chracters by having one abstraction that can be extended. 
##### Games
 Games will help control the game play invovled in the game. It will be in charge of updating the level, getting the entitites, the background etc. It will have some subclasses to aid in the processing and will act as the overall game (without the front end).This will interact extensively with Driver. 

 Games will use the information from loader to determine how many of each object to create, where to put the object and what attributes to give to the object. In addition, this can be extended by having Games be an abstract so that different games can implement different specific rules and implemetation. 

Games will also have information regarding PowerUps. This will be an abstract class that can be extended to have different types of PowerUps. Depending on the game different PowerUps can then be implemented.
 We wanted to create this so that our game can be as extendable as possible. One of our game is to create a platform on which multiple types of platformer games can be easily built. This allows for just that. 

##### Obstacles

The next module we have is obstacles. The obstacles will be items that act as a blockage to player. It will mostly be stationary but we might have some moving obstacles. It will have a couple of subclasses for different types of obstacles (Wall, Moving Wall etc). This will interact extensively with the Game to create the game.


#### Loader
Loader will load the initial configuration from the property file. We will have a class for CSV loader and also a class that creates the "infinite" map and provides that information. This will interact with the Driver. 

This can be extended by having an abstract class that highlights the main public methods that are needed. Things like get objects etc can be in these public method. 
We wanted to have a module for loader so that we can have multiple inputs in the future. We might want to read from xml or html or a different type of documentation and a Loader package puts everything in the same place. 
#### View
Uses the Display class to place things on stage. Extensions of the abstract screen class are scenes to add to stages at different points in the game. Screen extensions will include both cut screens, menu screens, and the gameplay environment. This will interact with the Driver and will get some information from the Loader. 

We can extend view by having the interface for view and then creating multiple classes that can be implemented in the view. To have some of the additional requirements like HUD, we can create a HUD class that gets implemented and gets information from the backend regarding the object characterisitics.

We created this module so that all the front end objects are together. Having them together means that JavaFX items will be isoalted from the rest of the objects. 

#### Driver
The Driver will act as the middle ground between the backend games and engine and the front end loader and view. It will provide each side the necessary information and will be the main of the entire process. 

The main purpose of driver is to stay constant even as games change. This will be closed for modification and would not need to be changed. 


### Example Games

* Super Mario: This is the essence of the entire platformer game and probably one of the most popular platformer game. The game has a basic centered character that traverses the map, avoids obstacles, and has a life. It will have some powerups, and some predefined behavior for enemies. 

* Doodle Jump: Doodle jump is a different implemetation of the above game in that the user only moves up. Their objective is to keep moving up rather than left-right as was the case above. Another interesting component is that game needs to be able to create the map in an nonstop fashion. 

* Lost Vikings: This is a puzzle type platform game that has multiple different chracters with different attributes. They change the map as they move around resulting in ability to solve the puzzle and move to the end and win.

The difference between the games are that super mario is the classic platform game. The player goes from left to right and has enemies to fight. Doodle jump is a variation where there aren't enemies and the player only moves upwards. Finally Lost Vikings, has different characters that the player controls and can switch between. As such they change the attributes of the game and the different variations of the characters can interact with each other. 

Based on this we need abstraction for the player, the enemy, the orientation. The Physics Engine will stay constant throughout the different games. 


### Design Considerations

We made a couple of important design consideration. We were debating having different enemy and player abstracts versus having an entity class as a whole. The benefit of the former was that we can define more characterisitics of enemy and player in the abtract and won't have to rewrite parts. The alternate was to have enemy and player fall under the same category. Enemy and player are essentially similar: they follow physics rules, the have to move, the have hitpoints, they interact with objects etc. Putting them under the same abstraction means we define base rules but all extensions will be in individual extended subclass. This is what we opted to do simply because we realized that A) different games will have quite different enemies and players so writing a base code will get increasingly unusued as time goes on, B) they have similar charactersitics that can be bumped together. 


The next interesting part that we considered was having constant physics gravity force versus having a predefined interaction that is attributes of object that should be falling. The benfit of the former is that anything new we add, will act as we expect. It creates a realisitic sense in the world of the game. The benefit of the later is that we don't have to worry about things we don't want to fall, falling. We have essentially stopped any mishap happening. We ended up choosing the former method to better imitate the real world, not having to worry about any future additions (such as powerups), and sticking to the theme of platformer games.

We are still looking into determining how to pass collections around. We could pass the entire collections or have it extend itterable. Right now we are leaning towards to the later to stick with encapsualtion and open close principle and stop exposing unnecessary information. 



