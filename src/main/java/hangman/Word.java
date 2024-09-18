package hangman;

public class Word {
    private String word;
    private String hint;
    private String category;
    private Difficulty difficulty;

    public Word(String word, String hint, String category, Difficulty difficulty) {
        this.word = word;
        this.hint = hint;
        this.category = category;
        this.difficulty = difficulty;
    }

    public String getWord() {
        return word;
    }

    public String getHint() {
        return hint;
    }

    public String getCategory() {
        return category;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

}
