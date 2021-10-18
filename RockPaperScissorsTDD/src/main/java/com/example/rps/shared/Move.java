package com.example.rps.shared;

public enum Move {
    ROCK, PAPER, SCISSORS;

    public boolean beats(Move otherMove) {
        return switch (this) {
            case ROCK -> otherMove == SCISSORS;
            case PAPER -> otherMove == ROCK;
            case SCISSORS -> otherMove == PAPER;
        };
    }
}
