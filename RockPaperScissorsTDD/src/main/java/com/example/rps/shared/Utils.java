package com.example.rps.shared;

public interface Utils {
    static String returnDrawWinLose(int playerScore, int computerScore){
        boolean equalMove = (playerScore == computerScore);
        boolean winningMove = (playerScore > computerScore);

        return equalMove ? "draw" : (winningMove) ? "playerWin": "computerWin";
    }
}