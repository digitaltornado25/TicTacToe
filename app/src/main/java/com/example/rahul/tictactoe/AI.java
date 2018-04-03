package com.example.rahul.tictactoe;

import java.util.Random;


public class AI {


    public int makeEasyMove() {
        Random r = new Random();

        return BoardManager.emptySpace.get(r.nextInt(BoardManager.emptySpace.size()));
    }
}
