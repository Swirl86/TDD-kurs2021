import com.example.rps.*;

import com.example.rps.player.Computer;
import com.example.rps.player.Player;
import com.example.rps.player.TestPlayer;
import com.example.rps.shared.Move;
import com.example.rps.shared.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RockPaperScissorsParameterizedTest {
    Game game;
    Player player;
    Computer computer;

    @BeforeEach
    public void init() {
        player = mock(TestPlayer.class);
        computer = mock(Computer.class);
        game = new Game(player, computer);
    }

    @ParameterizedTest
    @MethodSource("provideExpressions")
    void test_standard_game_round_success(Result expectedResult, Move playerMove, Move computerMove) {
        Result result = playerMove == computerMove ?
                Result.DRAW : playerMove.beats(computerMove) ?
                Result.WIN : Result.LOSE;

        assertEquals(expectedResult, result);
    }

    private static Stream<Arguments> provideExpressions() {
        return Stream.of(
                Arguments.of(Result.WIN, Move.ROCK, Move.SCISSORS),
                Arguments.of(Result.DRAW, Move.SCISSORS, Move.SCISSORS),
                Arguments.of(Result.LOSE, Move.PAPER, Move.SCISSORS)
        );
    }

}
