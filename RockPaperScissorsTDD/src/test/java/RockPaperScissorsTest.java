import com.example.rps.*;
import com.example.rps.exception.IllegalArgumentException;
import com.example.rps.player.Computer;
import com.example.rps.player.Player;
import com.example.rps.player.PlayerFactory;
import com.example.rps.shared.Move;
import com.example.rps.shared.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RockPaperScissorsTest {
    Game game;
    Player player;
    Computer computer;

    @BeforeEach
    public void init() throws IllegalArgumentException {
        game = new Game();
        player = game.getPlayer();
        computer = game.getComputer();
    }

    @Test
    void test_win_rockVsRock_fail() {
        player.setMove(Move.ROCK);
        computer.setMove(Move.ROCK);

        assertFalse(player.getMove().beats(computer.getMove()));
        assertFalse(computer.getMove().beats(player.getMove()));
    }

    @Test
    void test_win_paperVsRock_success() {
        player.setMove(Move.ROCK);
        computer.setMove(Move.PAPER);

        assertFalse(player.getMove().beats(computer.getMove()));
        assertTrue(computer.getMove().beats(player.getMove()));
    }

    @Test
    void test_win_scissorsVsRock_success() {
        player.setMove(Move.SCISSORS);
        computer.setMove(Move.ROCK);

        assertFalse(player.getMove().beats(computer.getMove()));
        assertTrue(computer.getMove().beats(player.getMove()));
    }

    @Test
    void test_game_outcome_win() {
        player.addScore();
        assertEquals(Result.WIN, game.getGameOutcome());
        assertEquals(1, player.getScore());
    }

    @Test
    void test_game_outcome_draw() {
        assertEquals(Result.DRAW, game.getGameOutcome());
        assertEquals(0, player.getScore());
    }

    @Test
    void test_game_outcome_lose() {
        computer.setScore(2);
        assertEquals(Result.LOSE, game.getGameOutcome());
        assertEquals(0, player.getScore());
        assertEquals(2, computer.getScore());
    }

    @Test
    public void test_addendum_illegal_argument_exception() {
        PlayerFactory playerFactory = new PlayerFactory();

        IllegalArgumentException illegalArgumentException =
                assertThrows(IllegalArgumentException.class,
                        () -> playerFactory.createPlayerModel("False Model", "False Name"));

        assertEquals("No such model!", illegalArgumentException.getMessage());
    }


    @Test
    void check_full_game_success() {
        Move[] playerMoves = { Move.PAPER, Move.SCISSORS, Move.ROCK };

        int nrOfRounds = 3;
        for(int i = 0; i < nrOfRounds; i++){
            player.setMove(playerMoves[i]);
            computer.makeMove();

            // Check who wins round
            if(computer.getMove().equals(player.getMove())){
                player.addScore();
                computer.addScore();
            } else {
                if(computer.getMove().beats(player.getMove())) {
                    computer.addScore();
                } else {
                    player.addScore();
                }
            }
        }
        // Check that result exists
        assertNotNull(game.getGameOutcome());
    }
}
