public class Dispatcher {
    public static void main(String... args){
        SkiPass[] arr = {
              new SkiWithoutNum("SP workDay withoutNum", "11 10", false, 9, 11 ),
              new SkiWithoutNum("SP weekend withoutNum", "22 10", 9, false),
              new SkiWithNum("SP weekend withNum", "20 10", false, 50),
              new SkiSeasonPass("11 11", false)
        };
        Model m = new Model(arr);
        Controller.mainProgram(m);
    }
}
