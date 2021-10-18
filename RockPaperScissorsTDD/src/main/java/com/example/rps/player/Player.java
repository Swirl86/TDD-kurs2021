package com.example.rps.player;

import com.example.rps.shared.Move;

public interface Player {
    void setMove(Move move);
    Move getMove();
    Move makeMove();

    int getScore();
    void addScore();

    String getName();
}
