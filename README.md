Object Oriented Game Architecture
====

This project implements a general architecture that can be used to create many different types of platformer type games. 3 games were created using the architecture: Mario, Lost Viking, Metroid. The architecture uses a low-code solution and a new game can be created entirely of data files.

### Basic Demo of Games Created

Mario:

https://user-images.githubusercontent.com/48296928/126908575-9fb70438-bf4c-421b-8ad6-569be76bdc00.mp4

Lost Vikings:

https://user-images.githubusercontent.com/48296928/126908583-482927d1-2722-4240-9fa9-4bfda5fa07cc.mp4


Metroid:

https://user-images.githubusercontent.com/48296928/126908586-5231a174-3eff-44c7-804e-c13b5ac25940.mp4



### Timeline

Discussion of Data Driven components:

https://user-images.githubusercontent.com/48296928/126907176-8abd7abf-8c3c-4c4b-b510-72f01208bbdd.mp4

Mario Game In-Depth Discussion:

https://user-images.githubusercontent.com/48296928/126907181-dc17b789-54f7-467b-85f9-0892a87b87bc.mp4


Metroid Game In_Depth Discussion:

https://user-images.githubusercontent.com/48296928/126907183-e2f70dee-753b-415d-b750-6115a1cae76b.mp4


Viking Game In-Depth Discussion:

https://user-images.githubusercontent.com/48296928/126907187-35af016f-ed2e-446b-8921-cf6a9040eba0.mp4


Level Creation Demo:
https://user-images.githubusercontent.com/48296928/126907190-32af3dd2-331e-466e-acb3-7d2543957327.mp4


Start Date: October 24th 2020

Finish Date: November 18th 2020


### Primary Roles

Sam Lamba: Worked primarily with backend and with simulation. Worked on backend components like Entity, Enemy, game etc (with Roshni) and animation components like Animation.

Roshni: Worked primarily with backend components and lead the incorporation of backend with each other and with front end's needs. Worked extensively on different objects and games. 

Matt: Assisted overall and worked extensively with data and data reading. Created multiple Beans and data readers/factories and worked on driver.

Joe: Worked extensively on front end and the different screens on the front end. 


### Resources Used

In assisting with animation we used: https://netopyr.com/2012/03/09/creating-a-sprite-animation-with-javafx/

#### Image Citations:

arrow - http://www.minecraftguides.org/arrows/

brick/floor/question - http://pixelartmaker.com/art/31bbfa89464bc5c

bullet - https://pgideas.fandom.com/wiki/Just_A_Bullet

coin2 - https://www.pngkey.com/maxpic/u2r5u2q8w7w7w7q8/

goal - https://www.clipartmax.com/png/middle/263-2634359_minecraft-super-mario-bros-super-mario-world-star-sprite.png

goblin - http://www.game-art-hq.com/84801/tomator-from-the-lost-vikings-series-game-art-and-an-overview/

ladder - https://www.pinterest.com/pin/659566307911245653/

LossScreen - https://media1.tenor.com/images/6d46c278bc8bea36adbced41f730981d/tenor.gif?itemid=12235828

Metroid Title - https://i.pinimg.com/originals/a7/40/c3/a740c3ba061b8762be32c15c2ee40438.jpg

Vikings Title - https://ih1.redbubble.net/image.440353023.4832/fposter,small,wall_texture,product,750x1000.u3.jpg

metroidWall - https://www.pixilart.com/art/metroid-ground-tile-c-d629464c7d33d1b

playerobstacle - https://classicreload.com/lost-vikings.html

spikes - https://www.pixilart.com/art/mini-spikes-c6478d0244c463f

VictoryScreen - https://art.pixilart.com/1f2a40229452e24.gif

vikingladder - https://classicreload.com/lost-vikings.html

vikingwall - https://classicreload.com/lost-vikings.html

waterfall - https://www.clipartkey.com/view/Tibobb_transparent-waterfall-png-waterfall-pixel-art/

Mario Sprite Sheet - https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSQ7BZJqqRoQLEqdlporChLCeuNXcTe4OtF9w&usqp=CAU

Metroid Sprite Sheet -  https://www.google.com/url?sa=i&url=https%3A%2F%2Ftwitter.com%2Fomegachainoboy%2Fstatus%2F1003698364842762240&psig=AOvVaw2w3clZgGywJlNAg_s9F-qo&ust=1605744741840000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCKie-rnniu0CFQAAAAAdAAAAABAU

Lost Vikings Sprite Sheet- https://www.spriters-resource.com/snes/lostvikings/sheet/8335/

All Music - https://downloads.khinsider.com/game-soundtracks


### Running the Program

Main class: Driver. To run everything correctly, please ensure the project structure looks as below:

![Project Structure](doc/projectStructure.png "Project Structur")

Data files needed: 
Everything in any directory marked as a source directory. Especially need all the property files (for the game to function as it does right now with the same interaction), as well as images. We also need the csv files representing the levels. We don't need any txt files (for example the highscore file and they have been as such added to gitignore)

Features implemented: Animation, high score being recorded, random level generation, character movement, automated enemy movement, property file based games and parameters input, sound tracks, collisions defined in data files, splash screens, display themes, rebindable keys


### Notes/Assumptions

Assumptions or Simplifications: We assume that some of the fundamental attributes of the game such as defaultPicture are present at the minimum. In addition we need the basic property files for the game to function. 

Interesting data files:
The data files that control the levels are all GameNameLevelNumber.csv; each of them may have an associated properties file that is used to define constants for that level. The data files that control level generation are GameNameClasses.properties; they list width and height of block and what obstacles and entities can be in the game. The data files that control random level generation are RandomGameName.properties; they specify special first and last chunk blocks, the number of chunks horizontally and vertically to generate, and what blocks to generate at what weights in the rest of the random level.

Known Bugs: In some instances mario can get stuck in objects, but that is very rare and uncommon and can be attributed usually to extremely high values like gravity or jump capacity. The game physics tries to simulate real life and thus similar to real life if these values were to be very high, we might expect a similar reaction. 

Extra credit: cabability of animation as well as already incorporated animations and ease of animating entities (simply add property file and image), high score being recorded for each part of game automatically and weekly high scores being updated as weeks go on, random level generation, all actions being read in from property file such that any non-programmer could edit how an object interacts after collision (an example of such property file can be seen below), splash screens, music.

![Property File](doc/propertyFileBasedCollision.png)

Implementation of above is simply editing the property file associated with an object and changing what each side of collision does (left, right, top, bottom) with any of the prewritten method to call and ID of object it will affect. Multiple methods can be called by having a ":" between calls. 


### Impressions

This project was extremely fun to work on since we had complete design control over our project. We learned a lot about design and planning. Working on a larger project allowed us to implemented new techniques to maximize our design flexibility. It was also exciting to challenge ourselves by including extra features like animation and random level generation.
