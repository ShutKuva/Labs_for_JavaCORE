import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Model {
    ArrayList<Book> allBooks;
    Model(){

    }
    Model(ArrayList<Book> arr){
        this.allBooks = arr;
    }
    Model(Book[] arr){
        this.allBooks = new ArrayList<Book>(Arrays.asList(arr));
    }
}

class Book {
    String name;
    String autor;
    String pubHouse;
    int year;
    int numOfSheets;
    int price;

    Book(){

    }
    Book (String name, String autor, String pubHouse, int year, int numOfSheets, int price){
        this.name = name;
        this.autor = autor;
        this.pubHouse = pubHouse;
        this.year = year;
        this.numOfSheets = numOfSheets;
        this.price = price;
    }

    // Getters/Setters
    public String getName (){
        return name;
    }
    public String getAutor (){
        return autor;
    }
    public String getPubHouse (){
        return pubHouse;
    }
    public int getNumOfSheets() {
        return numOfSheets;
    }
    public int getPrice() {
        return price;
    }
    public int getYear() {
        return year;
    }

    public void setAutor(String autor) {
        if(autor.length() > 0){
            this.autor = autor;
        }
    }
    public void setName(String name) {
        if(name.length() > 0){
            this.name = name;
        }
    }
    public void setPubHouse(String pubHouse) {
        if(pubHouse.length() > 0){
            this.pubHouse = pubHouse;
        }
    }
    public void setNumOfSheets(int numOfSheets) {
        if(numOfSheets > 0){
            this.numOfSheets = numOfSheets;
        }
    }
    public void setPrice(int price) {
        if(price > 0){
            this.price = price;
        }
    }
    public void setYear(int year) {
        if(year > 0){
            this.year = year;
        }
    }
    //

    @Override
    public String toString(){
        return this.name + " " + this.autor + " " + this.year + " published by " + this.pubHouse + " (" + this.numOfSheets + " sheets, " + this.price + " USD)";
    }
}
