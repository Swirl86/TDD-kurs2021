package com.example.rps.player;

import com.example.rps.shared.Move;

public class Computer extends TestPlayer {

    public Computer() {
        super("computer");
    }

    @Override
    public Move makeMove() {
        int randomNum = ((int) (Math.random() * 3)) + 1;     // Random generate value, 1-3
        // Rock = 1, Paper = 2, Scissors = 3
        switch (randomNum) {
            case 1 -> setMove(Move.ROCK);
            case 2 -> setMove(Move.PAPER);
            case 3 -> setMove(Move.SCISSORS);
            default -> throw new IllegalStateException("Unexpected value: " + randomNum);
        }
        return getMove();
    }
}
