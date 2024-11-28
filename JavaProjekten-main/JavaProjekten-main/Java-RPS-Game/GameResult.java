class GameResult {
    private int playerScore;
    private int aiScore;
    private int totalRounds;

    public void incrementPlayerScore() { playerScore++; }
    public void incrementAIScore() { aiScore++; }
    public void incrementRounds() { totalRounds++; }

    public int getPlayerScore() { return playerScore; }
    public int getAIScore() { return aiScore; }
    public int getTotalRounds() { return totalRounds; }

    public void printScore() {
        System.out.printf("Score - Player: %d | AI: %d%n", playerScore, aiScore);
    }
}
