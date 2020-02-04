class Board{
    private String name;
    private int size;
    private Character[][] ships;
    private boolean[][] hits;
    void setSize(int n){
        this.size = n;
    }
    public int getSize(){
        return this.size;
    }
    void SetName(String name){
        this.name = name;
    }
    String getName(){
        return this.name;
    }
    Board(String name,int size){
        this.name = name;
        setSize(size);

        size = getSize();
        ships = new Character[size][size];
        hits = new boolean[size][size];  
    }
    Board(String name){
        this(name,10);
    }
    public void print(){
    
    int n = getSize();
    System.out.println("ships:");
    int counter = 0;
    String coloumName = "";
    char alphabet = 'A';
    while(counter<=n){
        System.out.print(coloumName);
        System.out.print('\t');
        coloumName = ""+alphabet;
        alphabet++;
        counter++;
    }

    System.out.print('\n');
        for(int i = 0;i<n;i++){
            System.out.print(i+1);
            System.out.print('\t');
            for(int j = 0;j<n;j++){
                if(ships[i][j]==null){
                    System.out.print('.');
                }else{
                    System.out.print(ships[i][j]);
                }
                System.out.print('\t');
            }
            System.out.print('\n');
        }
    System.out.print('\n');    


    System.out.println("hits:");
    
    counter = 0;
    coloumName = "";
    alphabet = 'A';
    while(counter<=n){
        System.out.print(coloumName);
        System.out.print('\t');
        coloumName = ""+alphabet;
        alphabet++;
        counter++;
    }
    System.out.print('\n');

        for(int i = 0;i<n;i++){
            System.out.print(i+1);
            System.out.print('\t');
            for(int j = 0;j<n;j++){
                if(hits[i][j]==false){
                    System.out.print('.');
                }else{
                    System.out.print('x');
                }
                System.out.print('\t');
        }
            System.out.print('\n');
        }
        System.out.print('\n');  
    
    }

}