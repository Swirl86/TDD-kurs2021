package com.example.rps.local;

import com.example.rps.player.Player;
import com.example.rps.player.TestPlayer;
import com.example.rps.shared.*;

import java.util.Scanner;

public class LocalPlayer extends TestPlayer implements Player {
    private Scanner input;

    public LocalPlayer(String name) {
        super(name);
        input = new Scanner(System.in);
    }

    /* ---------------- For running terminal game from main -------------- */
    public Move makeMove() {
        System.out.print("\nValid options are ROCK PAPER SCISSORS");
        return getValidMove();
    }

    public Move getValidMove() {
        System.out.print("\nEnter your move: ");
        String move = input.nextLine();

        while (!isValidMove(move)) {
            System.out.println("\nInvalid option! Valid options are ROCK PAPER SCISSORS");
            System.out.print("Enter your move: ");
            move = input.nextLine();
        }
        return stringToMoveObject(move);
    }

    public boolean isValidMove(String value) {
        return value.equals("ROCK") || value.equals("PAPER")
                || value.equals("SCISSORS");
    }

    // Keep right text format for prints and checks
    public Move stringToMoveObject(String value) {
        switch (value) {
            case "ROCK" -> setMove(Move.ROCK);
            case "PAPER" -> setMove(Move.PAPER);
            case "SCISSORS" -> setMove(Move.SCISSORS);
            default -> throw new IllegalStateException("Invalid option! Valid options are ROCK PAPER SCISSORS");
        }
        return getMove();
    }

}
