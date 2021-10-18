package com.example.rps;

import com.example.rps.exception.IllegalArgumentException;
import com.example.rps.player.Computer;
import com.example.rps.player.Player;
import com.example.rps.player.PlayerFactory;
import com.example.rps.shared.Result;
import com.example.rps.shared.Utils;

public class Game {
    Player player;
    Computer computer;

    // For unit test
    public Game() throws IllegalArgumentException {
        PlayerFactory playerFactory = new PlayerFactory();
        player = playerFactory.createPlayerModel("test", "player");
        computer  = new Computer();
    }

    // For mock test
    public Game(Player player, Computer computer) {
        this.player = player;
        this.computer = computer;
    }

    public Player getPlayer() {
        return player;
    }

    public Computer getComputer() {
        return computer;
    }

    public Result getGameOutcome() {
        return switch (Utils.returnDrawWinLose(
                player.getScore(), computer.getScore())) {
            case "draw" -> Result.DRAW;
            case "playerWin" -> Result.WIN;
            default -> Result.LOSE;
        };
    }
}
