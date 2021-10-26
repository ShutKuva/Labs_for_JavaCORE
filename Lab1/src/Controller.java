import java.util.Comparator;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {
    public static Shape[] generateShapes(){
        int randomNum;
        Shape[] result = new Shape[10];
        for (int i = 0; i < 10; i++){
            randomNum = (int)(Math.random() * 10);
            if (randomNum < 4){
                result[i] = new Circle(randomNum > 0 ? randomNum : 1);
            } else if (randomNum < 8){
                result[i] = new Rectangle(randomNum > 0 ? randomNum : 1, randomNum + 1);
            } else {
                result[i] = new Triangle(randomNum > 0 ? randomNum : 1, randomNum + 1);
            }
        }
        return result;
    }

    public static Model calculateOutput (Shape[] arr){
        ArrayList<String> types = new ArrayList<String>();
        ArrayList<Shape> compareByArea = new ArrayList<Shape>(Arrays.asList(arr));
        ArrayList<Shape> compareByColor = new ArrayList<Shape>(Arrays.asList(arr));
        HashMap<String, Integer> areasOfTypes = new HashMap<String, Integer>();
        int summOfAreas = 0;
        for (Shape temp : arr){
            summOfAreas += temp.calcArea();
            String className = temp.getClass().getName();
            if (!types.contains(className)){
                types.add(className);
                areasOfTypes.put(className, temp.calcArea());
            } else {
                areasOfTypes.put(className, areasOfTypes.get(className) + temp.calcArea());
            }
        }
        compareByColor.sort(new Comparator<Shape>() {
        @Override
        public int compare(Shape o1, Shape o2) {
            if (o1.shapeColor.equals(o2.shapeColor)){
                return 0;
            } else {
                if (o1.shapeColor.equals("green")){
                    return -1;
                } else if (o2.shapeColor.equals("green")){
                    return 1;
                } else {
                    if (o1.shapeColor.equals("yellow")){
                        return -1;
                    } else if (o2.shapeColor.equals("yellow")){
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        }
        });
        compareByArea.sort(new Comparator<Shape>() {
            @Override
            public int compare (Shape o1, Shape o2){
                return o1.calcArea() - o2.calcArea();
            }
        });
        return new Model(arr, summOfAreas, areasOfTypes, compareByArea, compareByColor);
    }
}

