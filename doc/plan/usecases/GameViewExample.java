import ooga.view.Viewer;

//Use case 3: Visualize some Game or level
public class GameView implements Viewer{
  private Game game;

  //This example GameView just serves as a visual wrapper for Game, so it takes Game in constructor
  public GameView(Game game){
    this.game=game;
  }

  //To get the scene, this GameView just adds all game objects to the root of a new scene and returns
  //that scene
  public Scene getView() {
    Group root = new Group();
    group.addAll(game.getBackground());
    group.addAll(game.getEntities());
    return new Scene(root);
  }

}