package hangman;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private final Set<Character> usedLetters;

    public Player() {
        usedLetters = new HashSet<>();
    }

    public Set<Character> getUsedLetters() {
        return new HashSet<>(usedLetters);
    }

    public boolean setLetter(char letter) {
        char suggestedLetter = Character.toLowerCase(letter);
        return usedLetters.add(suggestedLetter);
    }
}
