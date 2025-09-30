package academy;

public class GameStats {
    private int gamesPlayed;
    private int wins;

    public GameStats() {
        this.gamesPlayed = 0;
        this.wins = 0;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void incrementGamesPlayed() {
        this.gamesPlayed++;
    }

    public int getWins() {
        return wins;
    }

    public void incrementWins() {
        this.wins++;
    }

    public int getWinPercentage() {
        if (gamesPlayed == 0) {
            return 0;
        }
        return (int) ((double) wins / gamesPlayed * 100);
    }
}
