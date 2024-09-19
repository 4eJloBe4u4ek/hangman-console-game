package hangman;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DictionaryTest {
    @Test
    public void getRandomWordFromCategory() throws IOException {
        String testFile = "testFile";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile))) {
            writer.write("спорт; easy\n");
            writer.write("волейбол: мяч часто летает над головой\n");
            writer.write("гольф: каждый удар имеет значение\n");
        }

        Dictionary dictionary = new Dictionary(testFile);
        Word word = dictionary.getRandomWordFromCategory("спорт", Difficulty.EASY);

        Assertions.assertFalse(dictionary.getWords().isEmpty());
        Assertions.assertTrue(word.getWord().equals("волейбол") || word.getWord().equals("гольф"));
        new java.io.File(testFile).delete();
    }

    @Test
    void getRandomWordFromCategory_throwsException() {
        Dictionary dictionary = new Dictionary("testFile");

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            dictionary.getRandomWordFromCategory("category", Difficulty.EASY);
        });
    }

    @Test
    public void getRandomWord() throws IOException {
        String testFile = "testFile";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile))) {
            writer.write("спорт; easy\n");
            writer.write("волейбол: мяч часто летает над головой\n");
            writer.write("гольф: каждый удар имеет значение\n");
        }

        Dictionary dictionary = new Dictionary(testFile);
        Word word = dictionary.getRandomWord(Difficulty.EASY);

        Assertions.assertFalse(dictionary.getWords().isEmpty());
        Assertions.assertTrue(word.getWord().equals("волейбол") || word.getWord().equals("гольф"));
        new java.io.File("testFile").delete();
    }

    @Test
    public void getRandomWord_throwsException() {
        Dictionary dictionary = new Dictionary("testFile");

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            dictionary.getRandomWord(Difficulty.EASY);
        });
    }

}
