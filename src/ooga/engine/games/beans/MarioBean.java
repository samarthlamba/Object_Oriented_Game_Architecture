package ooga.engine.games.beans;

public class MarioBean extends GameBean{
    private int coinSize;
    private int randomCoinMax;
    private int randomCoinMin;
    private double simulateFallOffset;
    private int coinDirectionNumerator;
    private int coinDirectionDenominator;
    private int randCoinVelocityXMax;
    private int randCoinVelocityXMin;
    private int randCoinVelocityYMax;
    private int randCoinVelocityYMin;

    public int getCoinSize() {
        return coinSize;
    }

    public int getRandomCoinMax() {
        return randomCoinMax;
    }

    public int getRandomCoinMin() {
        return randomCoinMin;
    }

    public double getSimulateFallOffset(){return simulateFallOffset; }

    public int getCoinDirectionNumerator(){return coinDirectionNumerator; }

    public int getCoinDirectionDenominator(){return coinDirectionDenominator; }

    public int getRandCoinVelocityXMax(){return randCoinVelocityXMax; }

    public int getRandCoinVelocityXMin(){return randCoinVelocityXMin; }

    public int getRandCoinVelocityYMax(){return randCoinVelocityYMax; }

    public int getRandCoinVelocityYMin(){return randCoinVelocityYMin; }

    public void setRandomCoinMax(String randomCoinMax) {
        this.randomCoinMax = Integer.valueOf(randomCoinMax);
    }

    public void setRandomCoinMin(String randomCoinMin) {
        this.randomCoinMin = Integer.valueOf(randomCoinMin);
    }

    public void setCoinSize(String coinSize) {
        this.coinSize = Integer.valueOf(coinSize);
    }

    public void setSimulateFallOffset(String simulateFallOffset){this.simulateFallOffset = Double.valueOf(simulateFallOffset);}

    public void setCoinDirectionNumerator(String coinDirectionNumerator){this.coinDirectionNumerator = Integer.valueOf(coinDirectionNumerator);}

    public void setCoinDirectionDenominator(String coinDirectionDenominator){this.coinDirectionDenominator = Integer.valueOf(coinDirectionDenominator);}

    public void setRandCoinVelocityXMax(String randCoinVelocityXMax){this.randCoinVelocityXMax = Integer.valueOf(randCoinVelocityXMax);}

    public void setRandCoinVelocityXMin(String randCoinVelocityXMin){this.randCoinVelocityXMin = Integer.valueOf(randCoinVelocityXMin);}

    public void setRandCoinVelocityYMax(String randCoinVelocityYMax){this.randCoinVelocityYMax = Integer.valueOf(randCoinVelocityYMax);}

    public void setRandCoinVelocityYMin(String randCoinVelocityYMin){this.randCoinVelocityYMin = Integer.valueOf(randCoinVelocityYMin);}
}
