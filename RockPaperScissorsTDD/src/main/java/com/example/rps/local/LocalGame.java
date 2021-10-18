package com.example.rps.local;

import com.example.rps.exception.IllegalArgumentException;
import com.example.rps.player.Computer;
import com.example.rps.player.Player;
import com.example.rps.player.PlayerFactory;
import com.example.rps.shared.*;

public class LocalGame {
    Player player;
    Computer computer;

    public LocalGame(String name) throws IllegalArgumentException {
        PlayerFactory playerFactory = new PlayerFactory();
        player = playerFactory.createPlayerModel("local", name);
        computer  = new Computer();
    }

    /* ---------------- For running terminal game from main -------------- */
    public void startGame() {
        while (player.getScore() != 3 && computer.getScore() != 3) {
            String winner = startGameRound();

            switch (winner) {
                case "player" -> player.addScore();
                case "computer" -> computer.addScore();
                default -> { // It is a tie
                    player.addScore();
                    computer.addScore();
                }
            }

            System.out.println("Current Score: " + player.getScore() +
                    " - " + computer.getScore());
        }

        announceWinner();
    }

    public String startGameRound() {
        Move playerMove = player.makeMove();
        Move computerMove = computer.makeMove();

        System.out.print(player.getName() + " VS " + computer.getName() + " : ");
        return getWinningMove(playerMove, computerMove);
    }

    public String getWinningMove(Move firstMove, Move secondMove) {
        boolean equalMove = (firstMove == secondMove);
        boolean winningMove = (firstMove.beats(secondMove));

        System.out.println(equalMove ? "It is a tie! - both win!" :
                (winningMove ? firstMove + " beats " + secondMove :
                        secondMove + " beats " + firstMove));

        return equalMove ? "both" : (winningMove) ? "player": "computer";
    }

    public void announceWinner() {
        switch (Utils.returnDrawWinLose(
                player.getScore(), computer.getScore())) {
            case "draw" -> System.out.println("\nIt is a " + Result.DRAW + "!");
            case "playerWin" -> System.out.println("\nYou  " + Result.WIN + "!");
            default -> System.out.println("\nYou  "+ Result.LOSE + "!");
        }
    }
}
