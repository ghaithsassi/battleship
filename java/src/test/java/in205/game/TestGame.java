package in205.game;


/**
 * TestGame
 */
public class TestGame {

    public static void main(String[] args) {
        Game game = new Game();
        game.init();
        game.run();

    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}