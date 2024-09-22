package hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private static final int BASE_MAX_ERRORS = 7;
    private static final String SEPARATOR = " - ";

    private PrintStream out;
    private BufferedReader reader;
    private Dictionary dictionary;
    private Player player;
    private HiddenWord hiddenWord;
    private Hangman hangman;
    private Difficulty difficulty;

    private SecureRandom secureRandom;
    private int errorsCount;
    private int maxErrors;
    private String category;

    public Game(PrintStream out, InputStream in) {
        dictionary = Dictionary.create("src/main/resources/words.txt");
        player = new Player();
        hiddenWord = new HiddenWord();
        hangman = new Hangman();

        this.out = out;
        this.reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));

        secureRandom = new SecureRandom();
        errorsCount = 0;
        maxErrors = 0;
    }

    private void getCategoryFromPlayer(BufferedReader reader) throws IOException {
        List<String> categoryList = dictionary.getWords().stream()
            .map(Word::getCategory)
            .distinct()
            .toList();

        out.println("Выберите категорию: ");
        for (int i = 0; i < categoryList.size(); ++i) {
            out.println(i + 1 + SEPARATOR + categoryList.get(i));
        }

        while (true) {
            String input = reader.readLine();
            if (input == null) {
                throw new IllegalArgumentException();
            }
            category = input.toLowerCase().trim();

            if (category.matches("\\d+")) {
                int selectCategory = Integer.parseInt(category);

                if (selectCategory >= 1 && selectCategory <= categoryList.size()) {
                    category = categoryList.get(selectCategory - 1).toLowerCase();
                    break;
                } else {
                    out.println("Неверный номер категории. Попробуйте еще раз.");
                }
            } else if (categoryList.contains(category) || category.isEmpty()) {
                break;
            } else {
                out.println("Неверная категория. Попробуйте еще раз.");
            }
        }

        if (!category.isEmpty()) {
            hiddenWord.setHiddenWord(dictionary.getRandomWordFromCategory(category, difficulty));
        } else {
            hiddenWord.setHiddenWord(dictionary.getRandomWord(difficulty));
            category = hiddenWord.getHiddenWord().getCategory();
        }
        out.println("Категория: " + category);
    }

    private char getLetterFromPlayer(BufferedReader reader) throws IOException {
        String input;
        char letter;

        out.println("Введите букву или введите '?' для получения подсказки:");
        while (true) {
            input = reader.readLine();
            if (input == null) {
                throw new IllegalArgumentException();
            }
            input = input.toLowerCase().trim();

            if ("?".equals(input)) {
                out.println("Подсказка: " + hiddenWord.getHiddenWord().getHint());
                out.println("Введите букву:");
                continue;
            }

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                out.println("Ошибка: введите одну букву.");
            } else if (Character.UnicodeBlock.of(input.charAt(0)) != Character.UnicodeBlock.CYRILLIC) {
                out.println("Ошибка: введите русскую букву");
            } else {
                letter = input.charAt(0);
                if (player.setLetter(letter)) {
                    return letter;
                } else {
                    out.println("Эта буква уже была введена. Попробуйте другую.");
                }
            }
        }
    }

    private void getDifficultyFromPlayer(BufferedReader reader) throws IOException {
        out.println("Выберите сложность игры: ");
        for (int i = 0; i < Difficulty.values().length; ++i) {
            out.println((i + 1) + SEPARATOR + Difficulty.values()[i].toString().toLowerCase());
        }

        String input = reader.readLine();
        if (input == null) {
            throw new IllegalArgumentException();
        }
        input = input.toLowerCase().trim();

        while (true) {
            switch (input) {
                case "easy":
                case "1":
                    difficulty = Difficulty.EASY;
                    break;
                case "medium":
                case "2":
                    difficulty = Difficulty.MEDIUM;
                    break;
                case "hard":
                case "3":
                    difficulty = Difficulty.HARD;
                    break;
                case "":
                    difficulty = Difficulty.values()[secureRandom.nextInt(Difficulty.values().length)];
                    break;
                default:
                    out.println("Неверная сложность. Попробуйте еще раз");
                    input = reader.readLine();
                    if (input == null) {
                        throw new IllegalArgumentException();
                    }
                    input = input.toLowerCase().trim();
                    continue;
            }
            break;
        }

        out.println("Сложность игры: " + difficulty.toString().toLowerCase());
        maxErrors = BASE_MAX_ERRORS - difficulty.ordinal();
    }

    private void displayGameState() {
        out.println(hangman.getHangmanStage(errorsCount, difficulty));
        out.println(hiddenWord.hiddenWordStatus());
        out.println("Кол-во оставшихся попыток: " + (maxErrors - errorsCount));

        List<Character> sortedLetters = new ArrayList<>(player.getUsedLetters());
        Collections.sort(sortedLetters);
        StringBuilder sb = new StringBuilder("Использованные буквы: ");
        for (char letter : sortedLetters) {
            sb.append(letter).append(' ');
        }
        out.println(sb.toString().trim());
    }

    private void play() throws IOException {
        while (errorsCount < maxErrors && !hiddenWord.isCorrectWord()) {
            char letter = getLetterFromPlayer(reader);

            if (!hiddenWord.setLetter(letter)) {
                errorsCount++;
            }

            displayGameState();
        }

        end();
    }

    private void end() throws IOException {
        if (hiddenWord.isCorrectWord()) {
            out.println("Поздравляю! Слово угадано.");
        } else {
            out.println("Вы проиграли :(");
            out.println("Загаданное слово: " + hiddenWord.getHiddenWord().getWord());
        }

        reader.close();
        out.close();
    }

    public void startGame() throws IOException {
        getDifficultyFromPlayer(reader);

        getCategoryFromPlayer(reader);

        displayGameState();

        play();
    }
}
