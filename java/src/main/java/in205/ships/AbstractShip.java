package in205.ships;
/**
 * Abstractship is an abstract class to represent the ships
 */
public abstract class AbstractShip{

    protected Character label;
    protected String name;
    protected Integer size;
    protected Orientation orientation;
    protected Integer strikeCount = 0;

    void setLabel(final Character label){
        this.label = label;
    }
    public Character getLabel(){
        return this.label;
    }
    void setName(final String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    } 
    public void setOrientation(final Orientation orientation){
        this.orientation = orientation;
    }
    public Orientation getOrientation(){
        return this.orientation;
    }
    void setSize(final Integer size){
        this.size = size;
    }
    public Integer getSize(){
        return this.size;
    }
    AbstractShip(Character label,String name,Orientation orientation,Integer size){
        setLabel(label);
        setName(name);
        setOrientation(orientation); 
        setSize(size);
    }
    public String toString(){
        String output = "";
        output+=getLabel();
        return output;
    }
    public void addStrike(){
        if(!isSunk()){
            ++strikeCount;
        }
    }
    public void addStrike(Integer k){
        if(!isSunk()){
            strikeCount += k;
        } 
    }
    public boolean isSunk(){
        return strikeCount >= this.size;
    }
}