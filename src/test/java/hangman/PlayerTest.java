package hangman;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    public void setLetter_correctLetter() {
        Player player = new Player();

        Assertions.assertTrue(player.setLetter('q'));
        Assertions.assertTrue(player.getUsedLetters().contains('q'));
    }

    @Test
    public void setLetter_duplicateLetter() {
        Player player = new Player();

        player.setLetter('q');
            
        Assertions.assertFalse(player.setLetter('q'));
    }
}
