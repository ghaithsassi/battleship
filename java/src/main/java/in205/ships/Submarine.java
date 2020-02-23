package in205.ships;
/**
 * Submarine
 */
public class Submarine extends AbstractShip {

    private static final long serialVersionUID = 1L;
    public Submarine(Orientation orientation) {
        super('S',"Submarine",orientation,3);
    }
    public Submarine(){
        this(Orientation.EAST);
    }
}