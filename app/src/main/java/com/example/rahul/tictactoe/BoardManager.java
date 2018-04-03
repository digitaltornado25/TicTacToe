package com.example.rahul.tictactoe;


import java.util.ArrayList;

public class BoardManager {

    /**
     * Current state of the board
     */
    public static String board[] = new String[9];

    /**
     * Stores whether its X turn or O
     */
    public static String chance = "X";

    /**
     * Stores if its first launch or no
     */
    public static boolean isFirstLaunch = true;

    /**
     * Stores the current winner
     */
    public static String winner = null;

    /**
     * stores if Player 1 is X or no
     */
    public static boolean isP1X = true;

    /*ArrayList of all the places available to tap */
    public static ArrayList<Integer> emptySpace = new ArrayList<>();


    /**
     * When you know it's safe to put X or O at a pos calling this function would
     * manage the changes in Board
     *
     * @param pos : the pos which is safe to put
     */
    public static void putValue(int pos) {
        //Setting board[pos] to current chance
        board[pos] = chance;

        //Remove the pos from the ArrayList
        emptySpace.remove(new Integer(pos));

        //Swap the chance
        if (chance == "X") {
            chance = "O";
        } else {
            chance = "X";
        }

        //Now that we have put a new value we check if any winner has been declared
        winner = Utility.hasWin(board);
    }


    /**
     * Calling this will reset all variables to its default value
     */
    public static void resetBoard() {
        //Clear the emptySpace
        emptySpace.clear();

        //Setting board i to empty
        for (int i = 0; i < 9; i++) {
            board[i] = " ";
            emptySpace.add(i);
        }

        winner = null;

        chance = "X";

        isFirstLaunch = true;


    }


    public static void setP1X() {
        isP1X = true;
    }


    public static void setP1O() {
        isP1X = false;
    }

}
