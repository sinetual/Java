import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Analyzer {
    private final List<Move> playerMoves;
    private final Random random;

    public Analyzer() {
        this.playerMoves = new ArrayList<>();
        this.random = new Random();
    }

    public void recordPlayerMove(Move move) {
        playerMoves.add(move);
    }

    public Move predictNextMove() {
        if (playerMoves.isEmpty()) {
            return randomMove();
        }

        int rockCount = 0, paperCount = 0, scissorsCount = 0;
        for (Move move : playerMoves) {
            switch (move) {
                case ROCK -> rockCount++;
                case PAPER -> paperCount++;
                case SCISSORS -> scissorsCount++;
            }
        }

        if (rockCount > paperCount && rockCount > scissorsCount) return Move.PAPER;
        if (paperCount > rockCount && paperCount > scissorsCount) return Move.SCISSORS;
        if (scissorsCount > rockCount && scissorsCount > paperCount) return Move.ROCK;

        return randomMove();
    }

    public Move randomMove() {
        return Move.values()[random.nextInt(Move.values().length)];
    }
}
