package in205.game;
import in205.game.exceptions.doubleStrikeException;
import in205.ships.*;
/**
 * ShipState
 */
public class ShipState {
    private AbstractShip ship;
    private boolean struck = false;
    private Integer addStrikeCallCounter = 0;
    ShipState(){}
    ShipState(AbstractShip aShip){
        this.ship = aShip;
    }
    void addStrike()throws doubleStrikeException{
        if(this.addStrikeCallCounter < 1){
            this.struck = true;
            ++(this.addStrikeCallCounter);
        }else{
            throw new doubleStrikeException();
        }
        
    }
    public boolean isStruck(){
        return this.struck;
    }
    public String toString(){
        if(this.struck){    
            return ColorUtil.colorize(String.valueOf(this.ship.getLabel()),ColorUtil.Color.RED); 
        }else{
            return String.valueOf(this.ship.getLabel());
        }
    }
    public boolean isSunk(){
        return this.ship.isSunk();
    }
    public AbstractShip getShip(){
        return this.ship;
    }
        
}