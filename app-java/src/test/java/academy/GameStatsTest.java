package academy;

import academy.domain.GameStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class GameStatsTest {
    private GameStats stats;

    @BeforeEach
    void setUp() {
        stats = new GameStats();
    }

    @Test
    @DisplayName("Должен корректно создавать статистику")
    void shouldCreateStats() {
        assertEquals(0, stats.getGamesPlayed());
        assertEquals(0, stats.getWins());
        assertEquals(0, stats.getWinPercentage());
    }

    @Test
    @DisplayName("Должен увеличивать количество сыгранных игр")
    void shouldIncrementGamesPlayed() {
        stats.incrementGamesPlayed();
        assertEquals(1, stats.getGamesPlayed());
        assertEquals(0, stats.getWins());

        stats.incrementGamesPlayed();
        stats.incrementGamesPlayed();
        assertEquals(3, stats.getGamesPlayed());
    }

    @Test
    @DisplayName("Должен увеличивать количество побед")
    void shouldIncrementWins() {
        stats.incrementWins();
        assertEquals(1, stats.getWins());
        assertEquals(0, stats.getGamesPlayed());

        stats.incrementWins();
        assertEquals(2, stats.getWins());
    }

    @Test
    @DisplayName("Должен корректно рассчитывать процент побед")
    void shouldCalculateWinPercentage() {
        assertEquals(0, stats.getWinPercentage());

        stats.incrementGamesPlayed();
        stats.incrementWins();
        assertEquals(100, stats.getWinPercentage());

        stats.incrementGamesPlayed();
        assertEquals(50, stats.getWinPercentage());

        stats.incrementGamesPlayed();
        assertEquals(33, stats.getWinPercentage());

        stats.incrementWins();
        stats.incrementGamesPlayed();
        assertEquals(50, stats.getWinPercentage());
    }

    @Test
    @DisplayName("Должен округлять процент побед в меньшую сторону")
    void shouldRoundWinPercentageDown() {
        stats.incrementGamesPlayed();
        stats.incrementGamesPlayed();
        stats.incrementGamesPlayed();
        stats.incrementWins();

        assertEquals(33, stats.getWinPercentage());
    }
}
