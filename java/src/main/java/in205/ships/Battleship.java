package in205.ships;
/**
 * battleship
 */
public class Battleship extends AbstractShip {
    public Battleship(Orientation orientation){
        super('B',"Battleship",orientation,4);
    }
    public Battleship(){
        this(Orientation.EAST);
    }

}