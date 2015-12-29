package com.gorobets.bowlingGame;

import java.util.Random;

/**
 * Created by invincible_g_d on 12/29/15.
 */
public class GameTry {

    private Frame[] frames = new Frame[10];
    private int currentRoll = 0;

    public void roll(int firstPins,int secondPins) {

        frames[currentRoll++] = new Frame(new Roll(firstPins),new Roll(secondPins));
    }


    public int score() {
        int score = 0;
        Random randomGenerator = new Random(10);

        for (int frameN = 0; frameN < 10; frameN++) {

            roll(randomGenerator.nextInt(),randomGenerator.nextInt());

            if (frames[frameN].isStrike()) {
                score += 10 + strikeBonus(frameN);

            } else if (frames[frameN].isSpare()) {
                score += 10 + spareBonus(frameN);

            } else {
                score += frames[frameN].sumOfBallsInFrame();

            }
        }




        return score;
    }





    private int spareBonus(int frameN) {
        return frames[frameN+1].getFirstRoll().getPins();
    }

    private int strikeBonus(int frameN) {
        return frames[frameN+1].getFirstRoll().getPins() +  frames[frameN+1].getSecondRoll().getPins();
    }


}

