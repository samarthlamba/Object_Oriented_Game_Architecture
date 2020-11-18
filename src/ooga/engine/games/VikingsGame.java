package ooga.engine.games;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import ooga.engine.entities.object.PlayerObstacle;
import ooga.engine.entities.player.Player;
import ooga.engine.games.beans.VikingsBean;
import ooga.engine.entities.weapon.Arrow;
import ooga.engine.entities.Movable;
import ooga.engine.obstacles.Unmovable;

public class VikingsGame extends Game{
  private final static int PRECISION = 0;
  private final static int MILLISECONDS = 1000;
  private final int arrowWidth;
  private final int arrowHeight;
  private double arrowVelocityX;
  private final Collection<Movable> arrows = new ArrayList<>();
  private final List<Movable> playerOrder = new ArrayList<>();
  private final List<Stack<Movable>> percolations = new ArrayList<>();
  private final List<Stack<Movable>> removedPercolationBlock = new ArrayList<>();
  private final List<Movable> addPercolationBlock = new ArrayList<>();
  private boolean obstacleCollision = false;
  private boolean firstStep = true;
  private int startTime = 0;


  public VikingsGame(Player player,Collection<Unmovable> obstacles,
                     Collection<Movable> entities, double timeElapsed, VikingsBean bean) {
    super(player,obstacles, entities, timeElapsed, bean);
    this.arrowWidth = bean.getArrowWidth();
    this.arrowHeight = bean.getArrowHeight();
    this.arrowVelocityX = bean.getArrowVelocityX();
    getPlayerObstacle();
    findPercolationBlockOrder();
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

  private void findPercolationBlockOrder(){
    for(Movable entity : entities){
      if(entity.isSource()){
        Stack<Movable> percolation = new Stack<>();
        removedPercolationBlock.add(new Stack<>());
        percolation.add(entity);
        percolations.add(percolation);
      }
    }
    for (Movable currentEntity : entities) {
      for (Stack<Movable> percolation : percolations) {
        findPercolationBlocks(currentEntity, percolation);
      }
    }
  }

  private void findPercolationBlocks(Movable currentEntity, Stack<Movable> percolation) {
    if(currentEntity.isPercolate()){
      double yPosition = percolation.peek().getMaxY();
      double nextYPosition = currentEntity.getMaxY() - currentEntity.getEntityHeight();
      double xPosition = percolation.peek().getCenterX() + currentEntity.getEntityWidth()/2;
      double nextXPosition = currentEntity.getCenterX() - currentEntity.getEntityWidth()/2;
      checkConnected(currentEntity, percolation, yPosition, nextYPosition, xPosition, nextXPosition);
    }
  }

  private void checkConnected(Movable currentEntity, Stack<Movable> percolation, double yPosition, double nextYPosition, double xPosition, double nextXPosition) {
    double leftXPosition = xPosition - currentEntity.getEntityWidth();
    double rightXPosition = nextXPosition + currentEntity.getEntityWidth();
    if(connected(yPosition, nextYPosition, percolation.peek().getCenterX(), currentEntity.getCenterX()) ||
            (connected(percolation.peek().getMaxY(), currentEntity.getMaxY(), xPosition, nextXPosition)) ||
            (connected(percolation.peek().getMaxY(), currentEntity.getMaxY(), leftXPosition, rightXPosition))){
      addPercolationBlockToStack(currentEntity, percolation);
    }
  }

  private boolean connected(double yPosition, double nextYPosition, double xPosition, double nextXPosition) {
    return areEqualDouble(nextYPosition, yPosition, PRECISION) &&
            areEqualDouble(xPosition, nextXPosition, PRECISION);
  }

  private void addPercolationBlockToStack(Movable currentEntity, Stack<Movable> percolation) {
    if(!percolation.contains(currentEntity)) {
      percolation.add(currentEntity);
    }
  }



  @Override
  protected void removeEntity(Movable entity){
    for(Movable playerObstacle: playerOrder) {
      if (entity.isPercolate() &&
              playerObstacle.getNode().getBoundsInParent().intersects(entity.getNode().getBoundsInParent())) {
        int i = 0;
        for (Stack<Movable> waterfall : percolations) {
          removePercolationBlock(waterfall, entity, i);
          i++;
        }
      }
    }
    if(!entity.getStatusAlive()){
      entitiesToRemove.add(entity);
      setPoints(entity);
    }
  }


  private void removePercolationBlock(Stack<Movable> percolation, Movable entity, int index){
    int entityPosition = percolation.search(entity);
    int stackPosition = 1;
    while(stackPosition < entityPosition){
      Movable removed = percolation.pop();
      removedPercolationBlock.get(index).add(removed);
      entitiesToRemove.add(removed);
      stackPosition++;
    }
  }

  @Override
  protected void updateMovable(){
    obstacleCollision = false;
    normalForce(entities, obstacles);
    for (Movable entity : entities) {
      setPoints(entity);
      moveMovable(entity);
      if(entity.doesShoot()){
        generateArrows(entity);
      }
      checkPercolationBlocked(entity);
    }
    fallingDeath();
    repercolate();
    entities.addAll(arrows);
    viewable.remove(entitiesToRemove);
    entities.removeAll(entitiesToRemove);
    entitiesToRemove.clear();
    viewable.spawn(entitiesToAdd);
    entitiesToAdd.clear();
    arrows.clear();
    addPercolationBlock.clear();
  }

  private void checkPercolationBlocked(Movable entity) {
    for(Movable playerObstacle : playerOrder){
      if(entity.isPercolate() && playerObstacle.getNode().getBoundsInParent().intersects(entity.getNode().getBoundsInParent())){
        obstacleCollision = true;
      }
    }
  }

  private void repercolate() {
    if(!obstacleCollision){
      int i = 0;
      for(Stack<Movable> percolation : removedPercolationBlock) {
        if (!percolation.empty()) {
          Movable block = percolation.pop();
          percolations.get(i).add(block);
          entitiesToAdd.add(block);
          addPercolationBlock.add(block); }
        i++;
      }
    }
    entities.addAll(addPercolationBlock);
  }

  private void generateArrows(Movable entity){
    if(entity.doesShoot()){
      makeArrow(entity);
    }
  }

  private void makeArrow(Movable entity){
    double arrowStartX = entity.getCenterX() - entity.getEntityWidth();
    double arrowStartY = entity.getMaxY() - entity.getEntityHeight()/2;
    if(entity.getFacing()) {
      arrowStartX = entity.getCenterX() + entity.getEntityWidth()/2;
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
    specialActionDelayFlag = 0;
    entity.setSpecialAction(true);
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
    totalPoints = (int) (System.currentTimeMillis() - startTime);
  }

}
