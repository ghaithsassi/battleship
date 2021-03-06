package in205.game;

import java.io.Serializable;

import in205.game.exceptions.*;
import in205.ships.*;

class Board implements IBoard,Serializable{
    
    private static final long serialVersionUID = 1L;
    private String name;
    private int size;
    private ShipState[][] ships;
    private Boolean[][] hits;

    void setSize(int n) {
        this.size = n;
    }

    public int getSize() {
        return this.size;
    }

    void SetName(String name) {
        this.name = name;
    }

    String getName() {
        return this.name;
    }
    public Boolean[][] getHit(){
        return this.hits.clone();
    }

    /**
     * constructor of class Board
     * 
     * @param name the name of the Board
     * @param size the size of the Board
     */
    Board(String name, int size) {
        this.name = name;
        setSize(size);

        size = getSize();
        ships = new ShipState[size][size];
        hits = new Boolean[size][size];
    }

    /**
     * constructor - Default size is 10
     * 
     * @param name the name of the Board
     */
    Board(String name) {
        this(name, 10);
    }

    /**
     * display the coloum name above the Board
     * 
     * @param nbrsColoum the total number of coloums
     */
    private void printColoumName(int nbrsColoum) {

        int counter = 0;
        String coloumName = "";
        String tmp;

        char alphabet = 'A';
        while (counter <= nbrsColoum) {
            System.out.print(coloumName);
            System.out.print('\t');
            tmp = nbrsColoum > 26 ? String.valueOf((counter / 26) + 1) : "";
            coloumName = alphabet + tmp;
            alphabet = (alphabet == 'Z' ? 'A' : ++alphabet);
            ++counter;
        }
    }

    /**
     * display the boards containning the ships and the hits
     */
    public void print() {
        int n = getSize();
        System.out.println("ships:");
        printColoumName(n);

        System.out.print('\n');
        for (int i = 0; i < n; i++) {
            System.out.print(i + 1);
            System.out.print('\t');
            for (int j = 0; j < n; j++) {
                if (ships[i][j] == null) {
                    System.out.print('.');
                } else {
                    System.out.print(ships[i][j]);
                }
                System.out.print('\t');
            }
            System.out.print('\n');
        }
        System.out.print('\n');

        System.out.println("hits:");
        printColoumName(n);
        System.out.print('\n');

        for (int i = 0; i < n; i++) {
            System.out.print(i + 1);
            System.out.print('\t');
            for (int j = 0; j < n; j++) {
                if (hits[i][j] == null) {
                    System.out.print('.');
                } else if(hits[i][j] ==  false ){
                    System.out.print(ColorUtil.colorize("X",ColorUtil.Color.WHITE));
                }else{
                    System.out.print(ColorUtil.colorize("X",ColorUtil.Color.RED));
                }
                System.out.print('\t');
            }
            System.out.print('\n');
        }
        System.out.print('\n');

    }


    /**
     * place a ship in the board
     * @param ship a ship 
     * @param row the row position, integer between 0 and (sizeof the board -1)
     * @param col the coloum position, integer between 0 and (sizeof the board -1)
     * @throws outOfBoardException,orientaionException,shipsOverlapException
     */
    public void putShip(AbstractShip ship, int row, int col) throws outOfBoardException,orientaionException,shipsOverlapException {
        if (row >= size || row < 0 || col >= size || col < 0)
            throw new outOfBoardException();
        switch (ship.getOrientation()){
                case SOUTH:
                    if ((row + ship.getSize()) > size)
                        throw new outOfBoardException();
                    for(int i =0 ; i<ship.getSize();i++){
                        if(hasShip(row+i, col)){
                            for(int j=i-1;j>=0;--j){
                                this.ships[(row + j)][col] = null;
                            }
                            throw new shipsOverlapException();
                        }
                        ShipState myShipstate = new ShipState(ship); 
                        this.ships[(row + i)][col] = myShipstate;
                    }
                    break;
                case NORTH:
                    if ((row - ship.getSize()+1) < 0)
                        throw new outOfBoardException();
                    for(int i =0 ; i<ship.getSize();i++){
                        if(hasShip(row-i, col)){
                            for(int j = i-1;j>=0;--j){
                                this.ships[(row - j)][col] = null;
                            }
                            throw new shipsOverlapException();
                        }
                        ShipState myShipstate = new ShipState(ship);
                        this.ships[(row - i)][col] = myShipstate;
                    }
                    break;
                case EAST:
                    if ((col + ship.getSize()) > size)
                        throw new outOfBoardException();
                    for(int i =0 ; i<ship.getSize();i++){
                        if(hasShip(row, col+i)){
                            for(int j=i-1;j>=0;--j){
                                this.ships[row ][(col + j) ] = null;
                            }
                            throw new shipsOverlapException();
                        }
                        ShipState myShipstate = new ShipState(ship);
                        this.ships[row ][(col + i) ] = myShipstate;
                    }
                    break;
                case WEST:
                    if ((col - ship.getSize()+1) < 0)
                        throw new outOfBoardException();
                    for(int i =0 ; i<ship.getSize();i++){
                        if(hasShip(row, col-i)){
                            for(int j = i-1;j>=0;--j){
                                this.ships[row ][(col - j) ] = null;
                            }
                            throw new shipsOverlapException();
                        }
                        ShipState myShipstate = new ShipState(ship);
                        this.ships[row ][(col - i) ] = myShipstate;
                    }
                    break;
                default:
                    throw new orientaionException(); 
        
        }

    }

    /**
     * check if there is a ship in a given position
     * @param row
     * @param col
     * @return flase if there is no ship in position (row,col) otherwise true 
     * @throws outOfBoardException
     */
    public boolean hasShip(int row, int col) throws outOfBoardException{
        if (row >= size || row < 0 || col >= size || col < 0)
            throw new outOfBoardException();
        return this.ships[row][col] != null;
    }

    /**
     * mark a hit
     * @param hit boolean for hit or not
     * @param row the row position, integer between 0 and (sizeof the board -1)
     * @param col the coloum position, integer between 0 and (sizeof the board -1)
     */
    public void setHit(boolean hit, int row, int col) throws outOfBoardException{
        if (row >= size || row < 0 || col >= size || col < 0)
            throw new outOfBoardException();
        this.hits[row][col] = hit;
    }

    /**
     * check if there is a hit in given position
     * @param row the row position, integer between 0 and (sizeof the board -1)
     * @param col the coloum position, integer between 0 and (sizeof the board -1)
     * @return Boolean null if there is no hit,false if there is a miss,true if there is a strike
     * @throws outOfBoardException
     */
    public Boolean getHit(int row, int col)throws outOfBoardException {
        if (row >= size || row < 0 || col >= size || col < 0)
            throw new outOfBoardException();
        return this.hits[row][col];
    }

    /**
     * this getter only used in testing
     * @param row
     * @param col
     * @return the Shipstate object
     */
    ShipState getShipState(int row,int col){
        return this.ships[row][col];
    }


    /**
     * try to hit a ship at given position
     * 
     * @param row the row position, integer between 0 and (sizeof the board -1)
     * @param col the coloum position, integer between 0 and (sizeof the board -1)
     * @return Hit an object indicate the status of the hit
     * @throws doubleStrikeException,outOfBoardException
     */
    public Hit sendHit(int row, int col) throws outOfBoardException, doubleStrikeException {
        if(this.hasShip(row, col)){
            this.ships[row][col].addStrike();
            if(this.ships[row][col].isSunk()){
                return Hit.fromInt(this.ships[row][col].getShip().getSize());
            }else{
                return Hit.STIKE;
            }
        }else{
            return Hit.MISS;
        }
    }

}