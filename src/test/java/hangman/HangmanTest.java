package hangman;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HangmanTest {
    @Test
    public void getHangmanStage_throwsException() {
        Hangman hangman = new Hangman();

        Assertions.assertAll(
            () -> Assertions.assertThrows(IllegalArgumentException.class,
                () -> hangman.getHangmanStage(8, Difficulty.EASY), "EASY difficulty failed"),
            () -> Assertions.assertThrows(IllegalArgumentException.class,
                () -> hangman.getHangmanStage(7, Difficulty.MEDIUM), "MEDIUM difficulty failed"),
            () -> Assertions.assertThrows(IllegalArgumentException.class,
                () -> hangman.getHangmanStage(6, Difficulty.HARD), "HARD difficulty failed")
        );
    }
}
