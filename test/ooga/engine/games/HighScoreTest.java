package ooga.engine.games;

import com.sun.tools.javac.Main;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class HighScoreTest {



    @Test
    void getGlobalHighScores() throws IOException {

        HighScore test = new HighScore("testerHighScore");
        HighScoreObject []  results = test.getGlobalHighScores();
        HighScoreObject [] initializedHighScore = new HighScoreObject[5];
        for (int i = 0; i < 5; i++){
            initializedHighScore[i] = new HighScoreObject(0,0);
        }
        System.out.println(results.length);
        for (int i = 0; i < 5; i++){
            assertEquals(initializedHighScore[i].getScore(), results[i].getScore());
            assertEquals(initializedHighScore[i].getTime(), results[i].getTime());
        }
    }

    @Test
    void getWeeklyHighScores() throws IOException {

        HighScore test = new HighScore("testerHighScore");
        HighScoreObject []  results = test.getWeeklyHighScores();
        HighScoreObject [] initializedHighScore = new HighScoreObject[5];
        for (int i = 0; i < 5; i++){
            initializedHighScore[i] = new HighScoreObject(0,0);
        }
        for (int i = 0; i < 5; i++){
            assertEquals(initializedHighScore[i].getScore(), results[i].getScore());
            assertEquals(initializedHighScore[i].getTime(), results[i].getTime());
        }

    }


    @Test
    void getAllScores() throws IOException {

        HighScore test = new HighScore("testerHighScore");
        HighScoreObject []  results = test.getAllScores();
        HighScoreObject [] initializedHighScore = new HighScoreObject[10];
        for (int i = 0; i < 10; i++){
            initializedHighScore[i] = new HighScoreObject(0,0);
        }
        for (int i = 0; i < 10; i++){
            assertEquals(initializedHighScore[i].getScore(), results[i].getScore());
            assertEquals(initializedHighScore[i].getTime(), results[i].getTime());
        }

    }
    File myObj2 = new File("src/resources/testerHighScore.txt");
    Boolean x2 = myObj2.delete();
    @Test
    void checkAddHighScore() throws IOException {
        File myObj = new File("src/resources/testerHighScore1.txt");
        Boolean x = myObj.delete();
            HighScore test = new HighScore("testerHighScore1");
            int[] scores = new int[]{5, 10, 20, 20, 30};

            test.checkAddHighScore(10);
            test.checkAddHighScore(5);
            test.checkAddHighScore(20);
            test.checkAddHighScore(30);
            test.checkAddHighScore(20);
            HighScoreObject[] results = test.getAllScores();

            for (int i = 0; i < 5; i++) {
                assertEquals(scores[i], results[i].getScore());
                assertTrue(System.currentTimeMillis() >= results[i].getTime());
            }

    }


    @Test
    void checkAddHighScoreDepreicatedTime() {

        //PrintWriter prw = new PrintWriter();
       // prw.close();
        //HighScore test = new HighScore("testerHighScore2");
        //test.checkAddHighScore(4);
      //  test.getAllScores();

    }
}