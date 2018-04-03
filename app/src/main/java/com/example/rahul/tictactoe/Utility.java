package com.example.rahul.tictactoe;

public class Utility {

    public static int winnerLine1, winnerLine2, winnerLine3;


    /**
     * @param board: The String array containing the current setting of the board
     * @return the winner X or O
     * if none is winner returns null
     */
    public static String hasWin(String[] board) {
        if (hasWonInLine(0, 1, 2, board)) {
            setWinningLine(0, 1, 2);
            return board[0];
        } else if (hasWonInLine(0, 4, 8, board)) {
            setWinningLine(0, 4, 8);
            return board[0];
        } else if (hasWonInLine(0, 3, 6, board)) {
            setWinningLine(0, 3, 6);
            return board[0];
        } else if (hasWonInLine(1, 4, 7, board)) {
            setWinningLine(1, 4, 7);
            return board[1];
        } else if (hasWonInLine(2, 5, 8, board)) {
            setWinningLine(2, 5, 8);
            return board[2];
        } else if (hasWonInLine(3, 4, 5, board)) {
            setWinningLine(3, 4, 5);
            return board[3];
        } else if (hasWonInLine(6, 7, 8, board)) {
            setWinningLine(6, 7, 8);
            return board[6];
        } else if (hasWonInLine(6, 4, 2, board)) {
            setWinningLine(6, 4, 2);
            return board[6];
        } else {
            return null;
        }


    }


    /**
     * @param first:The first position to check
     * @param second:   The second position to check
     * @param third:The third position to check
     * @param board:The board containing current state
     * @return: True or false depending on the first, second and third position is a winning line
     */
    public static boolean hasWonInLine(int first, int second, int third, String[] board) {
        if (board[first] == board[second] && board[second] == board[third] && board[first] != " ") {
            return true;
        }
        return false;
    }


    //this method will save the winning line for future refrence
    public static void setWinningLine(int first, int second, int third) {
        winnerLine1 = first;
        winnerLine2 = second;
        winnerLine3 = third;
    }
}

