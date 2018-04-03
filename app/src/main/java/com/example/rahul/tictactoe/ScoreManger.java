package com.example.rahul.tictactoe;


public class ScoreManger {

    public static int scoreP1 = 0;
    public static int scoreP2 = 0;

    public static void setWinner(String winner) {

        if (winner == "X") {
            if (BoardManager.isP1X) {    //If X wins and P1 is X
                scoreP1++;
            } else {
                scoreP2++;
            }
        }

        if (winner == "O") {
            if (BoardManager.isP1X) {     //If O wins and P1 is X
                scoreP2++;
            } else {
                scoreP1++;
            }
        }
    }

    /**
     * Calling this function restores the score of both players to 0
     */
    public static void resetScore() {
        scoreP1 = 0;
        scoreP2 = 0;
    }
}
