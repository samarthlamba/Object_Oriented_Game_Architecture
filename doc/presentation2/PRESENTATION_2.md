## Team 4: Super CS307 Bros

Matt Bowman, Roshni Penmatcha, Sam Lamba, Joe Heflin

### Current Features

#### Starting the Game

```java
@Override
  public void start(Stage initialStage) throws Exception {
    display = new Display(initialStage);
    gameFactory = new GameFactory();
    initialStage.show();
    display.setMainMenuScreen(this::launchGameMenu);
  }
```
```java
private void launchGameMenu(String gameLabel) {
    gameTitle = gameLabel;
    display.setGameMenuScreen(gameTitle, this::launchGame);
  }
```
```java
private void launchGame(String gameLevel) {
    String filePath = LEVEL_FILE_LOCATIONS.getString(gameTitle+","+gameLevel);
    game = gameFactory.makeCorrectGame(filePath);
    display.setGameDisplay(game);
    startTimeline();
}
```

#### Data Input Reading
```java
public T makeGameObject(String every, double x, double y) {
    try{
      Class c = Class.forName(gameBundle.getString(every));
      Constructor constr = c.getDeclaredConstructor(int.class, int.class, double.class, double.class);
      return (T) constr.newInstance(width,height,x,y);
    } catch (Exception e) {
      throw new FactoryException(String.format("Symbol %s not present in this game",every),e);
    }
  }
```

[Example Data File](src/resources/mariolevel1.csv)


#### Physics Engine/Game Play




First reflection code
```java
  for(String side : collisionTypes){
    Class gameSuperClass = this.getClass().getSuperclass();
    Method findCollisionSide = gameSuperClass.getDeclaredMethod(side + "Collision", Moveables.class, Node.class);
    //method returns true if side has collision
    if((boolean) findCollisionSide.invoke(this, entity, object)){
      collisionSide.add(side); //adds side (ex. right, top) to List
    }
  }
```

Second reflection code
```java
//loop through all sides of obstacle collided with (list determined during first reflection)
for(String side : collisionSide){
  Class collideableInterface = Class.forName("ooga.engine.obstacles.Collideable");
  Method actionOnCollision = collideableInterface.getDeclaredMethod(side + "Collideable", Moveables.class);
  //calls method in interface that preforms action on obstacle (can be overidden in subclasses depending on type of obstacle)
  actionOnCollision.invoke(obstacle, entity);
}
```

#### Entities Broad Overview
![Overview Of Entities Layout](doc/presentation2/OverviewOfEntities.png)

#### Enemy Edge Detection
Obstacle edge detection code:
```java
    private void simulateFall(Moveables entity, Collideable object){
        Rectangle simulate = new Rectangle(entity.getNode().getBoundsInParent().getMinX(), entity.getMaxY(), 0.1, 0.1);
        if (simulate.intersects(object.getNodeObject().getBoundsInParent())){
            leftOver = true;

        }
        simulate = new Rectangle(entity.getNode().getBoundsInParent().getMaxX(), entity.getMaxY(),0.1, 0.1);
        if (simulate.intersects(object.getNodeObject().getBoundsInParent())) {
            rightOver = true;
        }

    }
```

## Sprint Retrospective

We hit our target for features on this sprint:
* Physics of platformer
* Start Game from Main Menu
* Read in level configuration from file
* Distinguish between type/implementation of objects from data

The physics engine proved to be the most work here, as expected. It took a lot of troubleshooting
and work to make it work elegantly; now that it is implemented, future games should be trivial in
comparison.

One significant decision made this sprint was in regard to what class handled collision.
* Game, implementation of game, or obstacles
* In the end, our decision was based on the open-closed principle

Communication: Great! We were regularly able to talk over messages and had multiple meetings.
* One thing we could do better is communicate when we're making changes and where we make them, because we had several issues where
merges to master would cause tests to fial

Features for next sprint:
* Graphical displays ("sprites") for blocks - Joe
* Implementations of Metroid and Lost Vikings Game classes - Roshni
* Implementations of more game-specific obstacles, entities - Sam
* Switching between multiple levels of game when level is finished - Matt