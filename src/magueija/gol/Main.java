package magueija.gol;

/**
 * Main class.
 *
 * @author Pedro Magueija (pedromagueija@gmail.com)
 */
public class Main {

    public static void main(String[] args) {
        Game game = new Game();

        //blinkerSeed(game);
        //toadSeed(game);
        gliderSeed(game);

        for (int i = 0; i < 100; i++) {
            game.evolve();
            game.show();
        }

    }

    private static void gliderSeed(Game game) {
        game.add(2, 0);
        game.add(0, 1);
        game.add(2, 1);
        game.add(1, 2);
        game.add(2, 2);
    }

    private static void toadSeed(Game game) {
        game.add(2, 1);
        game.add(3, 1);
        game.add(4, 1);

        game.add(1, 2);
        game.add(2, 2);
        game.add(3, 2);
    }

    private static void blinkerSeed(Game game) {
        game.add(0, 1);
        game.add(1, 1);
        game.add(2, 1);
    }
}
