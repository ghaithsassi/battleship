package in205.game;
import java.util.Scanner;
/**
 * TestGame
 */
public class TestGame {

    public static void main(String[] args) {
        Game game;
        Scanner sin = new Scanner(System.in);
        System.out.println("welcome in Battleship game");
        if(!Game.loadSave()){
            while(true){
                try {
                    System.out.println("");
                    System.out.println("[1] single player");
                    System.out.println("[2] 2 player");
                    System.out.println("");
                    System.out.print("Select mode:");
                    String read =  sin.nextLine();
                    int playmode = Integer.parseInt(read);
                    if(playmode==1){
                        game = new Game();
                        break;
                    }else if(playmode == 2){
                        game = new GameTwoPlayerMode();
                        break;
                    }else{
                        System.out.println("please choose a valid mode");
                    }
                } catch (Exception e) {
                    System.out.println("please choose a valid mode");
                }
    
            }
        }else{
            game = new Game();
        }
        game=game.init();
        game.run();
        sin.close();

    }
    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}