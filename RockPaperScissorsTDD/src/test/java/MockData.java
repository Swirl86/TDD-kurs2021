import com.example.rps.shared.Move;

public interface MockData {
    static Move getPlayerMove(int i) {
        Move[] playerMove = { Move.PAPER, Move.ROCK, Move.SCISSORS, Move.PAPER };
        return playerMove[i];
    }

    static Move getComputerMove(int i) {
        Move[] computerMove = { Move.ROCK, Move.SCISSORS, Move.ROCK, Move.ROCK };
        return computerMove[i];
    }
}
