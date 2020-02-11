package in205.ships;
/**
 * Submarine
 */
public class Submarine extends AbstractShip {
    public Submarine(Orientation orientation){
        super('S',"Submarine",orientation,3);
    }
    public Submarine(){
        this(Orientation.EAST);
    }
}