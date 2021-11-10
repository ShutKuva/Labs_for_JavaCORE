import java.util.Map;
import java.util.ArrayList;

public class Model {
    Shape[] collection;
    int summOfAreas;
    ArrayList<Shape> collectionSortedByArea;
    ArrayList<Shape> collectionSortedByColor;
    Map areasOfTypes;
    Model(Shape[] collection, int summOfAreas, Map areasOfTypes, ArrayList<Shape> collectionSortedByArea, ArrayList<Shape> collectionSortedByColor){
        this.collection = collection;
        this.summOfAreas = summOfAreas;
        this.collectionSortedByArea = collectionSortedByArea;
        this.collectionSortedByColor = collectionSortedByColor;
        this.areasOfTypes = areasOfTypes;
    }
}

interface Drawable{
    void draw();
}

abstract class Shape implements Drawable{
    String shapeColor;
    abstract int calcArea();
    @Override
    public String toString(){
        return "Color is " + shapeColor;
    }
}

class Rectangle extends Shape{
    int width;
    int heigth;
    Rectangle(){

    }
    Rectangle(int width, int heigth){
        this.width = width;
        this.heigth = heigth;
        if (width > heigth){
            this.shapeColor = "green";
        } else {
            if (width > heigth){
                this.shapeColor = "yellow";
            } else {
                this.shapeColor = "red";
            }
        }
    }
    @Override
    public String toString(){
        return "Color is " + shapeColor + " and type is rectangle";
    }
    @Override
    int calcArea() {
        return width * heigth;
    }
    @Override
    public void draw(){
        System.out.println("Drawing of rectangle");
    }
}

class Triangle extends Shape{
    int width;
    int heigth;
    Triangle(){

    }
    Triangle(int width, int heigth){
        this.width = width;
        this.heigth = heigth;
        if (width > heigth){
            this.shapeColor = "green";
        } else {
            if (width > heigth){
                this.shapeColor = "yellow";
            } else {
                this.shapeColor = "red";
            }
        }
    }
    @Override
    public String toString(){
        return "Color is " + shapeColor + " and type is triangle";
    }
    @Override
    int calcArea() {
        return width * heigth / 2;
    }
    @Override
    public void draw(){
        System.out.println("Drawing of triangle");
    }
}

class Circle extends Shape{
    int radius;
    Circle(){

    }
    Circle(int radius){
        this.radius = radius;
        if (this.radius > 20){
            this.shapeColor = "yellow";
        } else {
            this.shapeColor = "red";
        }
    }
    @Override
    int calcArea() {
        return (int) (Math.pow(Math.PI, this.radius));
    }
    @Override
    public String toString(){
        return "Color is " + shapeColor + " and type is circle";
    }
    @Override
    public void draw(){
        System.out.println("Drawing of circle");
    }
}