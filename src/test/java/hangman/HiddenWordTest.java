package hangman;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HiddenWordTest {
    @Test
    public void setLetter_correctLetter() {
        HiddenWord hiddenWord = new HiddenWord();
        Word word = new Word("hangman", "a game", "games", Difficulty.EASY);
        hiddenWord.setHiddenWord(word);

        Assertions.assertTrue(hiddenWord.setLetter('h'));
        Assertions.assertTrue(hiddenWord.getGuessedLetters().contains('h'));
    }

    @Test
    public void setLetter_incorrectLetter() {
        HiddenWord hiddenWord = new HiddenWord();
        Word word = new Word("hangman", "a game", "games", Difficulty.EASY);
        hiddenWord.setHiddenWord(word);

        Assertions.assertFalse(hiddenWord.setLetter('q'));
        Assertions.assertFalse(hiddenWord.getGuessedLetters().contains('q'));
    }

    @Test
    public void isCorrectWord_guessedAllLetters() {
        HiddenWord hiddenWord = new HiddenWord();
        Word word = new Word("hangman", "a game", "games", Difficulty.EASY);
        hiddenWord.setHiddenWord(word);

        hiddenWord.setLetter('h');
        hiddenWord.setLetter('a');
        hiddenWord.setLetter('n');
        hiddenWord.setLetter('g');
        hiddenWord.setLetter('m');
        hiddenWord.setLetter('a');
        hiddenWord.setLetter('n');

        Assertions.assertTrue(hiddenWord.isCorrectWord());
    }

    @Test
    public void hiddenWordStatus_incompleteWord() {
        HiddenWord hiddenWord = new HiddenWord();
        Word word = new Word("hangman", "a game", "games", Difficulty.EASY);
        hiddenWord.setHiddenWord(word);

        hiddenWord.setLetter('h');
        hiddenWord.setLetter('a');

        Assertions.assertEquals("h a _ _ _ a _", hiddenWord.hiddenWordStatus());
    }
}
