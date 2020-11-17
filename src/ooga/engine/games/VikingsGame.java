package ooga.engine.games;

import java.util.*;

import ooga.engine.entities.object.PlayerObstacle;
import ooga.engine.entities.player.Player;
import ooga.engine.games.beans.VikingsBean;
import ooga.engine.entities.weapon.Arrow;
import ooga.engine.entities.Movable;
import ooga.engine.obstacles.Unmovable;

public class VikingsGame extends Game{
  private final static int PRECISION = 0;
  private final int arrowWidth;
  private final int arrowHeight;
  private double arrowVelocityX;
  private Collection<Movable> arrows = new ArrayList<>();
  private List<Movable> playerOrder = new ArrayList<>();
  private double dt;
  private List<Stack<Movable>> waterfalls = new ArrayList<>();
  private List<Stack<Movable>> removedWaterfall = new ArrayList<>();
  private List<Movable> addWater = new ArrayList<>();
  private boolean waterCollision = false;
  private boolean firstStep = true;
  int startTime = 0;


  public VikingsGame(Player player,Collection<Unmovable> obstacles,
                     Collection<Movable> entities, double timeElapsed, VikingsBean bean) {
    super(player,obstacles, entities, timeElapsed, bean);
    this.arrowWidth = bean.getArrowWidth();
    this.arrowHeight = bean.getArrowHeight();
    this.arrowVelocityX = bean.getArrowVelocityX();
    dt = timeElapsed;
    getPlayerObstacle();
    findWaterfallOrder();
  }

  private void getPlayerObstacle(){
    int i = 0;
    for(Movable entity : entities){
      if(entity.getId().equals("playerobstacle")){
        playerOrder.add(i, entity);
        i++;
      }
    }
  }



  private void findWaterfallOrder(){
    for(Movable entity : entities){
      if(entity.getId().equals("watersource")){
        Stack<Movable> waterfall = new Stack<>();
        removedWaterfall.add(new Stack<>());
        waterfall.add(entity);
        waterfalls.add(waterfall);
      }
    }
    Iterator<Movable> entitiesCollection = entities.iterator();
    while(entitiesCollection != null && entitiesCollection.hasNext()){
      Movable currentEntity = entitiesCollection.next();
      int i = 0;
      for(Stack<Movable> waterfall : waterfalls){
        findWaterFallBlocks(currentEntity, i, waterfall);
      }
    }
  }

  private void findWaterFallBlocks(Movable currentEntity, int i, Stack<Movable> waterfall) {
    if(currentEntity.getId().equals("waterfall")){
      double waterfallYPosition = waterfall.peek().getMaxY();
      double currentEntityYPosition = currentEntity.getMaxY() - currentEntity.getEntityHeight();
      double waterfallXPosition = waterfall.peek().getCenterX() + currentEntity.getEntityWidth()/2;
      double currentEntityXPosition = currentEntity.getCenterX() - currentEntity.getEntityWidth()/2;
      checkConnected(currentEntity, waterfall, waterfallYPosition, currentEntityYPosition, waterfallXPosition, currentEntityXPosition);
    }
  }

  private void checkConnected(Movable currentEntity, Stack<Movable> waterfall, double waterfallYPosition, double currentEntityYPosition, double waterfallXPosition, double currentEntityXPosition) {
    if((areEqualDouble(currentEntityYPosition, waterfallYPosition,PRECISION) &&
            areEqualDouble(waterfall.peek().getCenterX(), currentEntity.getCenterX(), PRECISION))||
            (areEqualDouble(currentEntityXPosition, waterfallXPosition,PRECISION) &&
            areEqualDouble(waterfall.peek().getMaxY(), currentEntity.getMaxY(),PRECISION)) ||
            (areEqualDouble(currentEntityXPosition + currentEntity.getEntityWidth(), waterfallXPosition - currentEntity.getEntityWidth(),PRECISION) &&
                    areEqualDouble(waterfall.peek().getMaxY(), currentEntity.getMaxY(),PRECISION))){
      addWaterfallToStack(currentEntity, waterfall);
    }
  }

  private void addWaterfallToStack(Movable currentEntity, Stack<Movable> waterfall) {
    if(!waterfall.contains(currentEntity)) {
      waterfall.add(currentEntity);
    }
  }



  @Override
  protected void removeEntity(Movable entity){
    for(Movable playerObstacle: playerOrder) {
      if (entity.getId().equals("waterfall") &&
              playerObstacle.getNode().getBoundsInParent().intersects(entity.getNode().getBoundsInParent())) {
        int i = 0;
        for (Stack<Movable> waterfall : waterfalls) {
          searchAndRemoveWaterfall(waterfall, entity, i);
          i++;
        }
      }
    }
    if(!entity.getStatusAlive()){
      entitiesToRemove.add(entity);
      setPoints(entity);
    }
  }



  private void searchAndRemoveWaterfall(Stack<Movable> waterfall, Movable entity, int index){
    int entityPosition = waterfall.search(entity);
    int stackPosition = 1;
    while(stackPosition < entityPosition){
      Movable removed = waterfall.pop();
      removedWaterfall.get(index).add(removed);
      entitiesToRemove.add(removed);
      stackPosition++;
    }
  }

  @Override
  protected void updateMovable(){
    waterCollision = false;
    for (Movable entity : entities) {
      setPoints(entity);
      moveMovable(entity);
      if(entity.getId().equals("enemy")){
        generateArrows(entity);
      }
      waterPlayerObstacleCollision(entity);
    }
    addBackWater();
    for(Movable arrow : arrows){
      entities.add(arrow);
    }
    viewable.remove(entitiesToRemove);
    entities.removeAll(entitiesToRemove);
    entitiesToRemove.clear();
    viewable.spawn(entitiesToAdd);
    entitiesToAdd.clear();
    arrows.clear();
    addWater.clear();
  }

  private void waterPlayerObstacleCollision(Movable entity) {
    for(Movable playerObstacle : playerOrder){
      if(entity.getId().equals("waterfall") && playerObstacle.getNode().getBoundsInParent().intersects(entity.getNode().getBoundsInParent())){
        waterCollision = true;
      }
    }
  }

  private void addBackWater() {
    if(!waterCollision){
      int i = 0;
      for(Stack<Movable> waterfall : removedWaterfall) {
        if (!waterfall.empty()) {
          Movable water = waterfall.pop();
          waterfalls.get(i).add(water);
          entitiesToAdd.add(water);
          addWater.add(water);
        }
        i++;
      }
    }
    for(Movable water : addWater){
      entities.add(water);
    }
  }

  private void generateArrows(Movable entity){
    if(entity.getId().equals("enemy")){
      makeArrow(entity);
    }
  }

  private void makeArrow(Movable enemy){
    double arrowStartX = enemy.getCenterX() - enemy.getEntityWidth();
    double arrowStartY = enemy.getMaxY() - enemy.getEntityHeight()/2;
    if(enemy.getFacing()) {
      arrowStartX = enemy.getCenterX() + enemy.getEntityWidth()/2;
      arrowVelocityX *= NEGATIVE_DIRECTION;
    }
    Arrow arrow = new Arrow(arrowWidth, arrowHeight, arrowStartX, arrowStartY);
    arrow.setVelocityX(arrowVelocityX);
    Random rand = new Random();
    double arrowFrequency = rand.nextInt(15);
    if(arrowFrequency == 1) {
      arrows.add(arrow);
      entitiesToAdd.add(arrow);
    }
  }

  @Override
  public void playerAction() {
    Movable entity = super.getActivePlayer();
    double startY = entity.getMaxY() - entity.getEntityHeight();
    double startX = entity.getCenterX() - entity.getEntityWidth()/2;
    PlayerObstacle block = new PlayerObstacle((int) entity.getEntityWidth(), (int) entity.getEntityHeight(), startX, startY);
    Movable nextPlayer = playerOrder.get(0);
    entity.setCenterX(nextPlayer.getCenterX());
    entity.setMaxY(nextPlayer.getMaxY());
    playerOrder.remove(0);
    playerOrder.add(1, block);
    entitiesToRemove.add(nextPlayer);
    entitiesToAdd.add(block);
    entities.add(block);
    entities.remove(nextPlayer);
  }

  @Override
  public void setPoints(Movable entity){
    if(firstStep) {
      startTime = (int) System.currentTimeMillis();
      firstStep = false;
    }
    totalPoints = (int) System.currentTimeMillis() - startTime;
  }

}
