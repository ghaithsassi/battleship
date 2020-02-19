package in205.game;

import java.io.Serializable;
import java.util.List;

import in205.ships.AbstractShip;

public class AIPlayer extends Player {
    /*
     * ** Attribut
     */
    private BattleShipsAI ai;

    /*
     * ** Constructeur
     */
    public AIPlayer(Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
        super(ownBoard, opponentBoard, ships);
        ai = new BattleShipsAI(ownBoard, opponentBoard);
    }

    //AIPlayer must not inherit "keyboard behavior" from player. Call ai instead 
    
    @Override
    public void putShips() {
        this.ai.putShips(this.ships);
    }

    @Override
    public Hit sendHit(int[] coords) {
        return this.ai.sendHit(coords);
    }
}
