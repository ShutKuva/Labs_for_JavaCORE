public class View {
    public static void showAll (){
        Model resultM = Controller.calculateOutput(Controller.generateShapes());
        for (Shape temp : resultM.collection){
            System.out.println(temp.toString());
        }
        System.out.println("\n"+resultM.summOfAreas);
        resultM.areasOfTypes.forEach((k, v) -> {
            System.out.println("\n" + k + " " + v);
        });
        System.out.println();
        for (Shape temp : resultM.collectionSortedByArea){
            System.out.print(temp.toString());
            System.out.println(" " + temp.calcArea());
        }
        System.out.println();
        for (Shape temp : resultM.collectionSortedByColor){
            System.out.println(temp.toString());
        }
    }
}
