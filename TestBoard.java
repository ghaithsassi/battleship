class TestBorad {
    public static void main(String[] args) {
        String name = "hello";
        Board b = new Board(name, 8);
        b.print();

        Board b2 = new Board("b2");
        b2.print();
    }
}