public class Dispatcher {
    public static void main (String... args){
        Book[] allBooks = {
                new Book("Eugene book", "Eugene", "Eugene inc.", 2020, 500, 1),
                new Book("First book", "Book First", "Poor fantasy", 2022, 800, 1000),
                new Book("Second book", "Book Second", "Poor fantasy", 2050, 800, 1000),
                new Book("Third book", "Book Third", "Don't read it", 1980, 800, 1000),
                new Book("Fourth book", "Book Fourth", "Poor fantasy", 220, 800, 1000),
        };
        Model m = new Model(allBooks);
        Controller.mainProgram(m);
    }
}
