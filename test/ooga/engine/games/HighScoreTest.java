package ooga.engine.games;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

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

    @Test
    void checkAddHighScore() throws IOException {

        HighScore test = new HighScore("testerHighScore1");
        test.checkAddHighScore(10);
        test.checkAddHighScore(5);
        test.checkAddHighScore(20);
        test.checkAddHighScore(30);
        test.checkAddHighScore(20);

    }

    @Test
    void checkAddHighScoreDepreicatedTime() throws IOException {

        HighScore test = new HighScore("testerHighScore2");
        test.checkAddHighScore(4);

    }
}