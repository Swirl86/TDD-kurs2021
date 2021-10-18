package com.example.rps;

import com.example.rps.exception.IllegalArgumentException;
import com.example.rps.local.LocalGame;

import java.util.Scanner;

public class RockPaperScissors {

    public static void main(String[] args) throws IllegalArgumentException {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to a game of Rock[ROCK] Paper[PAPER] Scissors[SCISSORS]");

        String  name =  getValidName(input);

        System.out.println("\nPlayer VS Computer, Game on!");
        LocalGame localGame = new LocalGame(name);
        localGame.startGame();
    }

    static String getValidName(Scanner input) {
        System.out.println("Enter your name: ");
        String name = input.nextLine();
        if (name.trim().length() < 1 ) {
            name = "player";
            System.out.print("Invalid name, default name player given!");
        }
        return name;
    }
}
