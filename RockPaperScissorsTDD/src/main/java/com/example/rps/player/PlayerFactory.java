package com.example.rps.player;

import com.example.rps.exception.IllegalArgumentException;
import com.example.rps.local.LocalPlayer;

public class PlayerFactory {
    public Player createPlayerModel(String model, String name) throws IllegalArgumentException {
        return switch (model.toLowerCase()) {
            case "test" -> new TestPlayer(name);
            case "local" -> new LocalPlayer(name);
            default -> throw new IllegalArgumentException("No such model!");
        };
    }
}