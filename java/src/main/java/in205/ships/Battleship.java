package in205.ships;
/**
 * battleship
 */
public class Battleship extends AbstractShip {
    private static final long serialVersionUID = 1L;
    public Battleship(Orientation orientation) {
        super('B',"Battleship",orientation,4);
    }
    public Battleship(){
        this(Orientation.EAST);
    }

}