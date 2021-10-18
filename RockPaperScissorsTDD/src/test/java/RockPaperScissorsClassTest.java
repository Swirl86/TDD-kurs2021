import com.example.rps.*;
import com.example.rps.exception.IllegalArgumentException;

import com.example.rps.player.Computer;
import com.example.rps.player.Player;
import com.example.rps.player.PlayerFactory;
import com.example.rps.player.TestPlayer;
import com.example.rps.shared.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RockPaperScissorsClassTest {
    Game game;
    Player player;
    Computer computer;

    @BeforeEach
    public void init() {
        player = mock(TestPlayer.class);
        computer = mock(Computer.class);
        game = new Game(player, computer);
    }

    @Test
    void test_mock_game_outcome_win() {
        // Given
        when(player.getScore()).thenReturn(2);
        // When
        Result result = game.getGameOutcome();
        //Then
        assertEquals(Result.WIN, result);
    }

    @Test
    void test_mock_game_outcome_draw() {
        // Given
        when(player.getScore()).thenReturn(0);
        // When
        Result result = game.getGameOutcome();
        //Then
        assertEquals(Result.DRAW, result);
    }

    @Test
    void test_mock_game_outcome_lose() {
        // Given
        when(computer.getScore()).thenReturn(1);
        // When
        Result result = game.getGameOutcome();
        //Then
        assertEquals(Result.LOSE, result);
    }

    @Test
    void test_mock_PlayerFactory_exception() throws IllegalArgumentException {
        PlayerFactory playerFactory = mock(PlayerFactory.class);
        when(playerFactory.createPlayerModel(anyString(), anyString()))
                .thenThrow(IllegalArgumentException.class);
    }

    @Test
    void test_get_mock_data() {
        // Given
        when(player.getMove()).thenReturn(MockData.getPlayerMove(0));
        when(computer.getMove()).thenReturn(MockData.getComputerMove(0));
        // When
        boolean result = player.getMove().beats(computer.getMove());
        //Then
        assertTrue(result);
    }
}
