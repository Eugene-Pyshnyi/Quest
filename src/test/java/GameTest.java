import game.Game;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.*;

public class GameTest {
    private Game game;
    @Before
    public void setUp() {
        game = new Game();
    }
    @Test
    public void startGameTest() {
        String message = game.startGame();
        assertEquals("You have lost memory. Accept the UFO call?", message);
    }
    @Test
    public void acceptUFOCallTest() {
        game.startGame();
        String result = game.processAnswer("Accept the UFO call");
        assertEquals("You accepted the UFO call. Are you going to climb up to the captain?", result);
    }
    @Test
    public void climbToCaptainTest() {
        game.startGame();
        game.processAnswer("Accept the UFO call");
        String result = game.processAnswer("Climb up to the captain");
        assertEquals("You have climbed up to the captain. Who are you?", result);
    }
    @Test
    public void tellTruthTest() {
        game.startGame();
        game.processAnswer("Accept the UFO call");
        game.processAnswer("Climb up to the captain");
        String result = game.processAnswer("Tell truth about yourself");
        assertEquals("You have been returned home!", result);
        assertTrue(game.isVictory());
    }
    @Test
    public void declineUFOCallTest() {
        game.startGame();
        String result = game.processAnswer("Decline the UFO call");
        assertEquals("You declined the UFO call...", result);
        assertTrue(game.isDefeated());
    }
    @Test
    public void refuseCaptainTest() {
        game.startGame();
        game.processAnswer("Accept the UFO call");
        String result = game.processAnswer("Refuse to climb up to the captain");
        assertEquals("You refused to climb up to the captain...", result);
        assertTrue(game.isDefeated());
    }
    @Test
    public void tellLieTest() {
        game.startGame();
        game.processAnswer("Accept the UFO call");
        game.processAnswer("Climb up to the captain");
        String result = game.processAnswer("Lie about yourself");
        assertEquals("Your lie has been revealed...", result);
        assertTrue(game.isDefeated());
    }
    @Test
    public void testResetGame() {
        game.startGame();
        game.processAnswer("Decline the UFO call");
        game.resetGame();
        assertFalse(game.isDefeated());
        assertFalse(game.isVictory());
        assertEquals("You have lost memory. Accept the UFO call?", game.startGame());
    }
}
