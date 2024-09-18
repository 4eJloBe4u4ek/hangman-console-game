package hangman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class Dictionary {
    private List<Word> words;

    public Dictionary(String filename) {
        words = new ArrayList<>();
        loadWordsFromFile(filename);
    }

    public void loadWordsFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            String category = null;
            Difficulty difficulty = null;

            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }

                line = line.trim();

                if (line.contains(";")) {
                    String[] categoryAndDifficulty = line.split(";");
                    category = categoryAndDifficulty[0].trim().toLowerCase();
                    difficulty = Difficulty.valueOf(categoryAndDifficulty[1].trim().toUpperCase());
                } else {
                    String[] wordAndHint = line.split(":", 2);
                    String word = wordAndHint[0].trim().toLowerCase();
                    String hint = wordAndHint[1].trim().toLowerCase();
                    words.add(new Word(word, hint, category, difficulty));

                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    public List<Word> getWords() {
        return words;
    }

    public Word getRandomWordFromCategory(String category, Difficulty difficulty) {
        List<Word> filterWords = new ArrayList<>();

        for (Word word : words) {
            if (word.getCategory().equalsIgnoreCase(category) && word.getDifficulty() == difficulty) {
                filterWords.add(word);
            }
        }

        if (filterWords.isEmpty()) {
            throw new NoSuchElementException(
                "Нет слов с такой категорией: " + category + " и сложностью: " + difficulty);
        } else {
            Random random = new Random();
            return filterWords.get(random.nextInt(filterWords.size()));
        }
    }

    public Word getRandomWord(Difficulty difficulty) {
        List<Word> filterWords = new ArrayList<>();

        for (Word word : words) {
            if (word.getDifficulty() == difficulty) {
                filterWords.add(word);
            }
        }

        if (filterWords.isEmpty()) {
            throw new NoSuchElementException("Нет слов с такой сложностью: " + difficulty);
        } else {
            Random random = new Random();
            return filterWords.get(random.nextInt(filterWords.size()));
        }
    }
}
