package hangman;

import java.io.IOException;

public final class Main {

    private Main() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) throws IOException {
        Game game = new Game(System.out, System.in);
        game.startGame();
    }
}
