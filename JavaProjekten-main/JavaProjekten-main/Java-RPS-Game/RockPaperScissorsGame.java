import java.util.Scanner;

public class RockPaperScissorsGame {
    private static final int NUM_ROUNDS_BEFORE_ANALYSIS = 2;
    private static final int MAX_ROUNDS = 7;
    private final Analyzer aiAnalyzer;
    private final GameResult gameResult;
    private final Scanner scanner;

    public RockPaperScissorsGame() {
        this.aiAnalyzer = new Analyzer();
        this.gameResult = new GameResult();
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        System.out.println("Welcome to Rock, Paper, Scissors with AI Analysis!");

        while (gameResult.getTotalRounds() < MAX_ROUNDS) {
            System.out.println("\nChoose your move: (rock, paper, scissors)");
            String playerInput = scanner.nextLine().toLowerCase();

            Move playerMove = parseMove(playerInput);
            if (playerMove == null) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            Move aiMove = (gameResult.getTotalRounds() >= NUM_ROUNDS_BEFORE_ANALYSIS)
                    ? aiAnalyzer.predictNextMove()
                    : aiAnalyzer.randomMove();

            System.out.println("AI chose: " + aiMove);
            resolveRound(playerMove, aiMove);

            gameResult.printScore();
        }

        announceFinalWinner();
    }

    private void resolveRound(Move playerMove, Move aiMove) {
        gameResult.incrementRounds();
        aiAnalyzer.recordPlayerMove(playerMove);

        if (playerMove == aiMove) {
            System.out.println("It's a tie!");
        } else if ((playerMove == Move.ROCK && aiMove == Move.SCISSORS) ||
                (playerMove == Move.PAPER && aiMove == Move.ROCK) ||
                (playerMove == Move.SCISSORS && aiMove == Move.PAPER)) {
            System.out.println("You win this round!");
            gameResult.incrementPlayerScore();
        } else {
            System.out.println("AI wins this round!");
            gameResult.incrementAIScore();
        }
    }

    private Move parseMove(String input) {
        return switch (input) {
            case "rock" -> Move.ROCK;
            case "paper" -> Move.PAPER;
            case "scissors" -> Move.SCISSORS;
            default -> null;
        };
    }

    private void announceFinalWinner() {
        System.out.println("\nGame Over! Final Score:");
        gameResult.printScore();

        if (gameResult.getPlayerScore() > gameResult.getAIScore()) {
            System.out.println("You are the overall winner!");
        } else if (gameResult.getPlayerScore() < gameResult.getAIScore()) {
            System.out.println("AI is the overall winner!");
        } else {
            System.out.println("It's a tie overall!");
        }
    }

    public static void main(String[] args) {
        RockPaperScissorsGame game = new RockPaperScissorsGame();
        game.startGame();
    }
}
