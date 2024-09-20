package hangman;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private Set<Character> usedLetters;

    public Player() {
        usedLetters = new HashSet<>();
    }

    public Set<Character> getUsedLetters() {
        return usedLetters;
    }

    public boolean setLetter(char letter) {
        letter = Character.toLowerCase(letter);

        if (!usedLetters.contains(letter)) {
            usedLetters.add(letter);
            return true;
        }

        return false;
    }
}
