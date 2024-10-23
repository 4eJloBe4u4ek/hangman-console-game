package hangman;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class Hangman {
    private final EnumMap<Difficulty, List<String>> hangmanStages;

    private static final int[] EASY_INDICES = {0, 1, 2, 3, 4, 5, 6, 7};
    private static final int[] MEDIUM_INDICES = {0, 2, 3, 4, 5, 6, 7};
    private static final int[] HARD_INDICES = {0, 1, 2, 3, 5, 7};

    public Hangman() {
        hangmanStages = new EnumMap<>(Difficulty.class);
        List<String> allStages = List.of(
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
        );

        hangmanStages.put(Difficulty.EASY, createSubList(allStages, EASY_INDICES));
        hangmanStages.put(Difficulty.MEDIUM, createSubList(allStages, MEDIUM_INDICES));
        hangmanStages.put(Difficulty.HARD, createSubList(allStages, HARD_INDICES));
    }

    private List<String> createSubList(List<String> stages, int[] indices) {
        List<String> subListStages = new ArrayList<>(indices.length);
        for (int index : indices) {
            subListStages.add(stages.get(index));
        }
        return subListStages;
    }

    public String getHangmanStage(int errorsCount, Difficulty difficulty) {
        List<String> stages = hangmanStages.get(difficulty);

        if (errorsCount < stages.size()) {
            return stages.get(errorsCount);
        } else {
            throw new IllegalArgumentException("Количество ошибок > этапов для сложности" + difficulty);
        }
    }
}
