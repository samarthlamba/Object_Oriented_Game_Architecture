package ooga.engine.games;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HighScore{
    private final String fileName;
    private static final int NUMBER_OF_RECORDS = 5;
    private static final long TIME_WEEK_AGO = 1000*60*60*24*7;
    private static final String EXTENSION = ".txt";
    private static final String INITIALIZED_SCORE = "0,0";
    private static final int TOTAL_NUMBER_RECORDS = NUMBER_OF_RECORDS*2;
    private static final String PATH_TO_RESOURCES = "src/resources/";

    public HighScore(String game){
        fileName = PATH_TO_RESOURCES + game + EXTENSION;
        checkFileExistence();
    }

    private void checkFileExistence(){
        if(! new File(fileName).exists()){
            createFile();
        }
    }

    private void createFile(){
        String [] initializedHighScore = new String[TOTAL_NUMBER_RECORDS];
        for (int i = 0; i < TOTAL_NUMBER_RECORDS; i++){
            initializedHighScore[i] = INITIALIZED_SCORE;
        }

        try{
            FileWriter writer = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(writer);

            bw.write(String.join("\n", initializedHighScore));
            bw.close();
        }
        catch (Exception e) {
            throw new CouldNotWriteToFileException("Could not write to file and add new high score");
        }
    }

    private HighScoreObject[] getSubListGlobal(HighScoreObject[] list, boolean global){
        if(global){
            return Arrays.copyOfRange(list, 0, NUMBER_OF_RECORDS);
        }
        return Arrays.copyOfRange(list, NUMBER_OF_RECORDS, list.length);
    }

    private HighScoreObject[] getFileContent() {
        File file = new File(fileName);

        HighScoreObject[] highScore = new HighScoreObject[TOTAL_NUMBER_RECORDS];
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int pos = 0;
            while (((st = br.readLine()) != null && pos != highScore.length)) {
                highScore[pos] = (HighScoreObject.toHighScoreObject(st));
                pos = pos + 1;
            }
            return highScore;
        } catch (Exception e) {
            for (int i = 0; i < highScore.length; i++){
                highScore[i] = new HighScoreObject(0,0);
            }
            return highScore;
        }
    }
    public HighScoreObject[] getGlobalHighScores() {
        checkFileExistence();

            HighScoreObject[] list= getFileContent();
            return  getSubListGlobal(list, true);
    }


    public HighScoreObject[] getWeeklyHighScores() {
        checkFileExistence();

        HighScoreObject[] list= getFileContent();
        return  getSubListGlobal(list, false);
    }

    public HighScoreObject[] getAllScores() {
        checkFileExistence();

        return getFileContent();


    }

    private boolean deprecatedTimes(HighScoreObject current){
        return current.getTime() < (System.currentTimeMillis() - (TIME_WEEK_AGO));
    }

    private HighScoreObject[] updateWeeklyTimes( HighScoreObject [] listOfScores){
        List<HighScoreObject> newListOfWeeklyScores = new ArrayList<>();
        for(int i = NUMBER_OF_RECORDS; i < listOfScores.length; i ++) {
            if (!deprecatedTimes(listOfScores[i])) {
                newListOfWeeklyScores.add(listOfScores[i]);
            }
        }
        for(int i = 0; i < newListOfWeeklyScores.size(); i++){ //2
            listOfScores[listOfScores.length-newListOfWeeklyScores.size()+i] = newListOfWeeklyScores.get(i);
        }
        for(int i = 0; i < NUMBER_OF_RECORDS -newListOfWeeklyScores.size(); i++){ //2
            listOfScores[NUMBER_OF_RECORDS +i] = new HighScoreObject(0,0);
        }

        return listOfScores;
    }

    private String[] convertHighScoreArrayToString(HighScoreObject [] current){
        String [] answerString = new String[10];
        for (int i = 0; i<answerString.length; i++){
            answerString[i] = current[i].toString();
        }
        return answerString;
    }

    public void checkAddHighScore(int score) {
        HighScoreObject current = new HighScoreObject(score);
        HighScoreObject [] listOfScores = getAllScores();
        listOfScores = updateWeeklyTimes(listOfScores);
        listOfScores = shiftAndAddScore(current, listOfScores, 0, NUMBER_OF_RECORDS-1);
        listOfScores = shiftAndAddScore(current, listOfScores, NUMBER_OF_RECORDS, listOfScores.length-1);  //ignord error as doing changes to same variable
        try
        {
            FileWriter writer = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(writer);
            String [] stringListOfScores = convertHighScoreArrayToString(listOfScores);
            bw.write(String.join("\n", stringListOfScores));
            bw.close();
        } catch (Exception e) {
            throw new CouldNotWriteToFileException("Could not write to file and add new high score");
        }

    }

    private HighScoreObject[] shiftAndAddScore(HighScoreObject current, HighScoreObject[] listOfScores, int start, int end) {
        for(int i = start; i < end; i ++) {
            if (current.greaterThan(listOfScores[i+1])) {
                listOfScores[i] = listOfScores[i + 1];
            } else if (current.greaterThan(listOfScores[i])){
                listOfScores[i] = current;
            }
        }
        if(current.greaterThan(listOfScores[end])){
            listOfScores[end] = current;
        }
        return listOfScores;
    }
}

