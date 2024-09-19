package hangman;

import java.util.HashSet;
import java.util.Set;

public class HiddenWord {
    private Word hiddenWord;
    private Set<Character> guessedLetters;

    public HiddenWord() {
        guessedLetters = new HashSet<>();
    }

    public void setHiddenWord(Word hiddenWord) {
        this.hiddenWord = hiddenWord;
    }

    public Word getHiddenWord() {
        return hiddenWord;
    }

    public Set<Character> getGuessedLetters() {
        return guessedLetters;
    }

    public boolean setLetter(char letter) {
        letter = Character.toLowerCase(letter);

        if (hiddenWord.getWord().indexOf(letter) != -1) {
            guessedLetters.add(letter);
            return true;
        }

        return false;
    }

    public String hiddenWordStatus() {
        StringBuilder sb = new StringBuilder();
        for (char letter : hiddenWord.getWord().toCharArray()) {
            if (guessedLetters.contains(letter)) {
                sb.append(" ").append(letter);
            } else {
                sb.append("  _");
            }
        }

        return sb.toString().trim();
    }

    public boolean isCorrectWord() {
        for (char letter : hiddenWord.getWord().toCharArray()) {
            if (!guessedLetters.contains(letter)) {
                return false;
            }
        }

        return true;
    }
}
