package ooga.view;

import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import ooga.GameController;
import ooga.engine.games.Game;
import ooga.engine.games.HighScore;
import ooga.util.DukeApplicationTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.stream.Stream;

public class HighScoreScreenTest extends DukeApplicationTest {

    HighScoreScreen screen;

    private Stage myStage;


    @Override
    public void start(Stage stage) throws Exception {
        myStage = stage;
        myStage.show();
        Timeline testTimeline = new Timeline();
        testTimeline.setCycleCount(5);
        GameController testController = new GameController(stage,testTimeline,this::setMyGame);
        screen = new HighScoreScreen(testController,"Mario");
        stage.setScene(screen.getView());
        ComboBox scope = lookup("#scope").query();
        select(scope,"All");
    }

    @Test
    public void runTest() {
        assertEquals(screen.getInfo(),"MarioLevel1All");
    }

//    private ComboBox getScope(Scene view) {
////        ObservableList<Node> children = view.getRoot().getChildrenUnmodifiable();
//        return lookup("#scope").query();
//    }

    public void setMyGame(Game myGame) {}

}
