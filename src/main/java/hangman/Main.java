package hangman;

import java.io.IOException;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class Main {
    public static void main(String[] args) throws IOException {
        Game game = new Game(System.out, System.in);
        game.startGame();
    }
}
