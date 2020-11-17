package ooga.engine.games;

import java.io.Serializable;

public class HighScoreObject implements Serializable{

    private static final long serialVersionUID = 1L;
    private int score;
    private long time;
    public HighScoreObject(int score){
        time = System.currentTimeMillis();
        this.score = score;
    }
    public HighScoreObject(int score, long time){
        this.time = time;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public long getTime() {
        return time;
    }

    public boolean greaterThan(HighScoreObject obj){
        if(this.score > obj.getScore()){
            return true;
        }
        else return this.score == obj.getScore() && this.time > obj.getTime();
    }
    @Override
    public String toString(){
        return this.getScore() + "," + this.getTime();
    }

    public static HighScoreObject toHighScoreObject(String s){
        String [] split = s.split(",");
        return new HighScoreObject(Integer.parseInt(split[0]), Long.parseLong(split[1]));
    }
}
