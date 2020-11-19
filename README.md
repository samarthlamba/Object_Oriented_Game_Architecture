Final- CS307
====

This project implements a player for multiple related games.

Names: Sam Lamba, Roshni Penmatcha, Matt Bowman, Joe Heflin


### Timeline

Start Date: October 24th 2020

Finish Date: November 18th 2020

Hours Spent: Many many hours

### Primary Roles

### Resources Used

In assisting with animation we used: https://netopyr.com/2012/03/09/creating-a-sprite-animation-with-javafx/

#### Image Citations:

arrow - http://www.minecraftguides.org/arrows/

brick/floor/question - http://pixelartmaker.com/art/31bbfa89464bc5c

bullet - https://pgideas.fandom.com/wiki/Just_A_Bullet

coin - 

coin2 - https://www.pngkey.com/maxpic/u2r5u2q8w7w7w7q8/

defaultObject - 

goal - https://www.clipartmax.com/png/middle/263-2634359_minecraft-super-mario-bros-super-mario-world-star-sprite.png

goblin - http://www.game-art-hq.com/84801/tomator-from-the-lost-vikings-series-game-art-and-an-overview/

goomba -

goomba2 -

heart -

heart2 -

ladder - https://www.pinterest.com/pin/659566307911245653/

LossScreen - https://media1.tenor.com/images/6d46c278bc8bea36adbced41f730981d/tenor.gif?itemid=12235828

marioTitle -

Metroid Title - https://i.pinimg.com/originals/a7/40/c3/a740c3ba061b8762be32c15c2ee40438.jpg

Vikings Title - https://ih1.redbubble.net/image.440353023.4832/fposter,small,wall_texture,product,750x1000.u3.jpg

metroidWall - https://www.pixilart.com/art/metroid-ground-tile-c-d629464c7d33d1b

pauseButton -

pirate - 

playerobstacle - https://classicreload.com/lost-vikings.html

shrinker - 

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

![Project Structure](doc/projectStructure.png "Project Structure")

Data files needed: 

Features implemented: Animation, high score being recorded, random level generation, character movement, automated enemy movement, property file based games and parameters input. 


### Notes/Assumptions

Assumptions or Simplifications: We assume that some of the fundamental attributes of the game such as defaultPicture are present at the minimum. 

Interesting data files:

Known Bugs: In some instances mario can get stuck in objects, but that is very rare and uncommon and can be attributed usually to extremely high values like gravity or jump capacity. The game physics tries to simulate real life and thus similar to real life if these values were to be very high, we might expect a similar reaction. 

Extra credit: capanility of animation as well as already incorporated animations and ease of animating entities (simply add property file and image), high score being recorded for each part of game automatically and weekly high scores being updated as weeks go on, random and automatic level generation, all actions being read in from property file such that any non-programmer could edit how an object interacts after collision. An example of such property file can be seen below:

![Property File](doc/propertyFileBasedCollision.png)

Implementation of above is simply editing the property file associated with an object and changing what each side of collision does (left, right, top, bottom) with any of the prewritten method to call and ID of object it will affect. Multiple methods can be called by having a ":" between calls. 


### Impressions

This project was extremely fun to work on since we had complete design control over our project. We learned a lot about design and planning. Working on a larger project allowed us to implemented new techniques to maximize our design flexibility. It was also exciting to challenge ourselves by including extra features like animation and random level generation.
