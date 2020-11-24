package ooga.engine.games.beans;

/**
 * Mario beans to pass constants into game through game constructor
 */
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

    /**
     * return size of coin object
     * @return int coin size int (width/height)
     */
    public int getCoinSize() {
        return coinSize;
    }

    /**
     * return upper bounds range of random number of coins generated
     * @return int upper bounds of random coins generated
     */
    public int getRandomCoinMax() {
        return randomCoinMax;
    }

    /**
     * return lower bounds range of random number of coins generated
     * @return int lower bounds of random coins generated
     */
    public int getRandomCoinMin() {
        return randomCoinMin;
    }

    /**
     * returns constant offset needed to simulate fall
     * @return return double simulate fall offset
     */
    public double getSimulateFallOffset(){return simulateFallOffset; }

    /**
     * Numerator of fraction to determine fraction of coins with - velocity direction
     * @return int numerator of ratio
     */
    public int getCoinDirectionNumerator(){return coinDirectionNumerator; }

    /**
     * Denominator of fraction to determine fraction of coins with + or - velocity direction
     * @return int denominator of ratio
     */
    public int getCoinDirectionDenominator(){return coinDirectionDenominator; }

    /**
     * Returns upper bounds of random number generated for velocity in x direction
     * @return int random coin x velocity max bounds
     */
    public int getRandCoinVelocityXMax(){return randCoinVelocityXMax; }

    /**
     * Returns lower bounds of random number generated for velocity in x direction
     * @return int random coin x velocity min bounds
     */
    public int getRandCoinVelocityXMin(){return randCoinVelocityXMin; }

    /**
     * Returns upper bounds of random number generated for velocity in y direction
     * @return int random coin y velocity max bounds
     */
    public int getRandCoinVelocityYMax(){return randCoinVelocityYMax; }

    /**
     * Returns lower bounds of random number generated for velocity in y direction
     * @return int random coin y velocity min bounds
     */
    public int getRandCoinVelocityYMin(){return randCoinVelocityYMin; }

    /**
     * Set random coin upper bounds value
     * @param randomCoinMax read in from properties file
     */
    public void setRandomCoinMax(String randomCoinMax) {
        this.randomCoinMax = Integer.valueOf(randomCoinMax);
    }

    /**
     * Set random coin lower bounds value
     * @param randomCoinMin read in from properties file
     */
    public void setRandomCoinMin(String randomCoinMin) {
        this.randomCoinMin = Integer.valueOf(randomCoinMin);
    }

    /**
     * Set coin size
     * @param coinSize read in from properties file
     */
    public void setCoinSize(String coinSize) {
        this.coinSize = Integer.valueOf(coinSize);
    }

    /**
     * Set simulate fall offset
     * @param simulateFallOffset read in from properties file
     */
    public void setSimulateFallOffset(String simulateFallOffset){this.simulateFallOffset = Double.valueOf(simulateFallOffset);}

    /**
     * Set coin direction numerator of ratio
     * @param coinDirectionNumerator read in from properties file
     */
    public void setCoinDirectionNumerator(String coinDirectionNumerator){this.coinDirectionNumerator = Integer.valueOf(coinDirectionNumerator);}

    /**
     * Set coin direction denominator of ratio
     * @param coinDirectionDenominator read in from properties file
     */
    public void setCoinDirectionDenominator(String coinDirectionDenominator){this.coinDirectionDenominator = Integer.valueOf(coinDirectionDenominator);}

    /**
     * Set random x velocity upper bounds of coin
     * @param randCoinVelocityXMax read in from properties file
     */
    public void setRandCoinVelocityXMax(String randCoinVelocityXMax){this.randCoinVelocityXMax = Integer.valueOf(randCoinVelocityXMax);}

    /**
     * Set random x velocity lower bounds of coin
     * @param randCoinVelocityXMin read in from properties file
     */
    public void setRandCoinVelocityXMin(String randCoinVelocityXMin){this.randCoinVelocityXMin = Integer.valueOf(randCoinVelocityXMin);}

    /**
     * Set random y velocity upper bounds of coin
     * @param randCoinVelocityYMax read in from properties file
     */
    public void setRandCoinVelocityYMax(String randCoinVelocityYMax){this.randCoinVelocityYMax = Integer.valueOf(randCoinVelocityYMax);}

    /**
     * Set random y velocity lower bounds of coin
     * @param randCoinVelocityYMin read in from properties file
     */
    public void setRandCoinVelocityYMin(String randCoinVelocityYMin){this.randCoinVelocityYMin = Integer.valueOf(randCoinVelocityYMin);}
}
