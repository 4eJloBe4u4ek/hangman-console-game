package hangman;

import java.util.HashSet;
import java.util.Set;

public class HiddenWord {
    private Word hiddenWord;
    private final Set<Character> guessedLetters;

    public HiddenWord() {
        guessedLetters = new HashSet<>();
    }

    public void setHiddenWord(Word hiddenWord) {
        this.hiddenWord =
            new Word(hiddenWord.word(), hiddenWord.hint(), hiddenWord.category(), hiddenWord.difficulty());
    }

    public Word getHiddenWord() {
        return new Word(hiddenWord.word(), hiddenWord.hint(), hiddenWord.category(),
            hiddenWord.difficulty());
    }

    public Set<Character> getGuessedLetters() {
        return new HashSet<>(guessedLetters);
    }

    public boolean setLetter(char letter) {
        char suggestedLetter = Character.toLowerCase(letter);

        if (hiddenWord.word().indexOf(suggestedLetter) != -1) {
            guessedLetters.add(suggestedLetter);
            return true;
        }

        return false;
    }

    public String hiddenWordStatus() {
        StringBuilder sb = new StringBuilder();
        for (char letter : hiddenWord.word().toCharArray()) {
            if (guessedLetters.contains(letter)) {
                sb.append(' ').append(letter);
            } else {
                sb.append(" _");
            }
        }

        return sb.toString().trim();
    }

    public boolean isCorrectWord() {
        for (char letter : hiddenWord.word().toCharArray()) {
            if (!guessedLetters.contains(letter)) {
                return false;
            }
        }

        return true;
    }
}
