package com.gorobets.bowlingGame;

/**
 *
 */
public class Frame {
    public Roll getFirstRoll() {
        return firstRoll;
    }

    public void setFirstRoll(Roll firstRoll) {
        this.firstRoll = firstRoll;
    }

    public Roll getSecondRoll() {
        return secondRoll;
    }

    public void setSecondRoll(Roll secondRoll) {
        this.secondRoll = secondRoll;
    }

    private   Roll firstRoll ;
   private Roll secondRoll ;

    public Frame(Roll firstRoll, Roll secondRoll) {
        this.firstRoll = firstRoll;
        this.secondRoll = secondRoll;
    }
//  Frame prevFrame = new Frame();

    //    Roll curRoll = new Roll();
//    Roll nextRoll = new Roll();
//
//    Roll rolls[] = new Roll[21];
//    private int currentRoll = 0;
//

//    public int score() {
//        int score = 0;
//
//        if (isStrike()) {
//            score += 10 + strikeBonus();
//
//        } else if (isSpare()) {
//            score += 10 + spareBonus();
//
//        } else {
//            score += sumOfBallsInFrame();
//
//        }
//        return score;
//    }

  public boolean isStrike() {
        return firstRoll.getPins() == 10;
    }

    public int sumOfBallsInFrame() {
        return firstRoll.getPins() + secondRoll.getPins();
    }


    public boolean isSpare() {
        return firstRoll.getPins() + secondRoll.getPins() == 10;
    }
}
