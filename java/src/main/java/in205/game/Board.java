package in205.game;

import in205.game.exceptions.*;
import in205.ships.*;

class Board implements IBoard {
    private String name;
    private int size;
    private Character[][] ships;
    private boolean[][] hits;

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

    /**
     * just for testing must be deleted later
     * @return the ships
     */
    public Character[][] getShips(){
        return this.ships;
    }
    public boolean[][] getHit(){
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
        ships = new Character[size][size];
        hits = new boolean[size][size];
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
                if (hits[i][j] == false) {
                    System.out.print('.');
                } else {
                    System.out.print('x');
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
     */
    public void putShip(AbstractShip ship, int row, int col) throws outOfBoardException,orientaionException,shipsOverlapException {
        if (row >= size || row < 0 || col >= size || col < 0)
            throw new outOfBoardException();

        switch (ship.getOrientation()){
                case SOUTH:
                    if ((row + ship.getSize()) > size)
                        throw new outOfBoardException();
                    for(int i =0 ; i<ship.getSize();i++){
                        if(hasShip(row+i, col))
                            throw new shipsOverlapException();
                        this.ships[(row + i)][col] = ship.getLabel();
                    }
                    break;
                case NORTH:
                    if ((row - ship.getSize()+1) < 0)
                        throw new outOfBoardException();
                    for(int i =0 ; i<ship.getSize();i++){
                        if(hasShip(row-i, col))
                            throw new shipsOverlapException();
                        this.ships[(row - i)][col] = ship.getLabel();
                    }
                    break;
                case EAST:
                    if ((col + ship.getSize()) > size)
                        throw new outOfBoardException();
                    for(int i =0 ; i<ship.getSize();i++){
                        if(hasShip(row, col+i))
                            throw new shipsOverlapException();
                        this.ships[row ][(col + i) ] = ship.getLabel();
                    }
                    break;
                case WEST:
                    if ((col - ship.getSize()+1) < 0)
                        throw new outOfBoardException();
                    for(int i =0 ; i<ship.getSize();i++){
                        if(hasShip(row, col-i))
                            throw new shipsOverlapException();
                        this.ships[row ][(col - i) ] = ship.getLabel();
                    }
                    break;
                default:
                    throw new orientaionException(); 
        
        }

    }

    public boolean hasShip(int row, int col) throws outOfBoardException{
        if (row >= size || row < 0 || col >= size || col < 0)
            throw new outOfBoardException();

        return this.ships[row][col] != null;
    }

    public void setHit(boolean hit, int row, int col) throws outOfBoardException{
        if (row >= size || row < 0 || col >= size || col < 0)
            throw new outOfBoardException();
        this.hits[row][col] = hit;
    }

    public Boolean getHit(int row, int col)throws outOfBoardException {
        if (row >= size || row < 0 || col >= size || col < 0)
            throw new outOfBoardException();
        return this.hits[row][col];
    }
}