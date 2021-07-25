# Team 4: Super CS307 Bros

Matt Bowman, Roshni Penmatcha, Sam Lamba, Joe Heflin

### Current Features

### Displays for entities and objects

```java
private Map<String, ImagePattern> getImages(ResourceBundle characterImageResources) {
        Map<String,ImagePattern> map = new HashMap<>();
        for (String key : characterImageResources.keySet()) {
            Image image = new Image(characterImageResources.getString(key));
            map.put(key, new ImagePattern(image));
        }
        return map;
    }
```

### Win/Loss detection
```java
private void step() {
    if(game.hasFinished()) {
      victoryScreen();
    }
}

private void victoryScreen() {
    timeline.stop();
    display.setMainMenuScreen(this::launchGameMenu);
  }
```

### All games implemented

All code in Game, basically


### Constant configuration
```java
public Game(Collection<Unmovable> obstacles, Collection<Movable> entities, double timeElapsed, GameBean bean) {
        this.gravity = bean.getGravity();
        this.moveForce = bean.getMoveForce();
        this.obstacles = obstacles;
        this.entities = entities;
        this.jumpMax = bean.getJumpMax();
}
```

## Tests Showcase 

## Sprint Retrospective

We hit our target for features on this sprint:
* Added a lot of graphics
* Made the games to be functional and working
* Added a lot of different types of entities
* We have multiple different types of collisions
* We have constant automatic enemy movement 

One of the most biggest challenges we faced was how to organize the game in such a way that new games are easy to add. We needed enough flexiblity but still make the game interesting. In addition, we needed the backend and front end to communicate without giving too much information to each other. This fed into our decisions of working with interfaces in the front end and objects in the backend. 

One significant decision made this sprint was in regard to what all we get from property file.
* We decided to move towards reading a lot of values and parameters from the property file
* This allows us to let the user create games in the future and change attributes in the settings

Communication: Quite well! We were able to talk over a lot of design decisions together. 
* One thing we really improved on from last sprint was avoiding merges that broke master. This week, we
made sure to communicate with each other what we were changing and made sure to pull, run tests, and then merge. Master has been generally working except some tests failures as big changes were being added. 

Features for next sprint:
* Adding more front end components, high score reader, and get more assets- Joe
* Create property file reader - Roshni & Sam
* Create level editor  - Matt

General feelings: Fairly confident we can meet everything, and relatively on track at the moment. 