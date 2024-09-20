package hangman;

import java.util.HashMap;
import java.util.Map;

public class Hangman {
    private Map<Difficulty, String[]> hangmanStages;

    public Hangman() {
        hangmanStages = new HashMap<>();

        hangmanStages.put(Difficulty.EASY, new String[] {
            """
                              ————————————————
                                |/
                                |
                                |
                                |
                                |
                               /|
                              ——————""",
            """
                              ————————————————
                                |/     |
                                |
                                |
                                |
                                |
                               /|
                              ——————""",
            """
                              ————————————————
                                |/     |
                                |      0
                                |
                                |
                                |
                               /|
                              ——————""",
            """
                              ————————————————
                                |/     |
                                |      0
                                |      |
                                |
                                |
                               /|
                              ——————""",
            """
                              ————————————————
                                |/     |
                                |      0
                                |     /|
                                |
                                |
                               /|
                              ——————""",
            """
                              ————————————————
                                |/     |
                                |      0
                                |     /|\\
                                |
                                |
                               /|
                              ——————""",
            """
                              ————————————————
                                |/     |
                                |      0
                                |     /|\\
                                |     /
                                |
                               /|
                              ——————""",
            """
                              ————————————————
                                |/     |
                                |      0
                                |     /|\\
                                |     / \\
                                |
                               /|
                              ——————"""
        });

        hangmanStages.put(Difficulty.MEDIUM, new String[] {
            """
                              ————————————————
                                |/
                                |
                                |
                                |
                                |
                               /|
                              ——————""",
            """
                              ————————————————
                                |/     |
                                |      0
                                |
                                |
                                |
                               /|
                              ——————""",
            """
                              ————————————————
                                |/     |
                                |      0
                                |      |
                                |
                                |
                               /|
                              ——————""",
            """
                              ————————————————
                                |/     |
                                |      0
                                |     /|
                                |
                                |
                               /|
                              ——————""",
            """
                              ————————————————
                                |/     |
                                |      0
                                |     /|\\
                                |
                                |
                               /|
                              ——————""",
            """
                              ————————————————
                                |/     |
                                |      0
                                |     /|\\
                                |     /
                                |
                               /|
                              ——————""",
            """
                              ————————————————
                                |/     |
                                |      0
                                |     /|\\
                                |     / \\
                                |
                               /|
                              ——————"""
        });

        hangmanStages.put(Difficulty.HARD, new String[] {
            """
                              ————————————————
                                |/
                                |
                                |
                                |
                                |
                               /|
                              ——————""",
            """
                              ————————————————
                                |/     |
                                |
                                |
                                |
                                |
                               /|
                              ——————""",
            """
                              ————————————————
                                |/     |
                                |      0
                                |
                                |
                                |
                               /|
                              ——————""",
            """
                              ————————————————
                                |/     |
                                |      0
                                |      |
                                |
                                |
                               /|
                              ——————""",
            """
                              ————————————————
                                |/     |
                                |      0
                                |     /|\\
                                |
                                |
                               /|
                              ——————""",
            """
                              ————————————————
                                |/     |
                                |      0
                                |     /|\\
                                |     / \\
                                |
                               /|
                              ——————"""
        });
    }

    public String getHangmanStage(int errorsCount, Difficulty difficulty) {
        String[] stages = hangmanStages.get(difficulty);

        if (errorsCount < stages.length) {
            return stages[errorsCount];
        } else {
            return null;
        }
    }
}
