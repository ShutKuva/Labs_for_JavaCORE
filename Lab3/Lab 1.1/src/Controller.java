import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Controller {
    public static ArrayList<Book> getListByAutor (String autor, Model m){
        ArrayList<Book> result = new ArrayList<Book>();
        for (Book temp : m.allBooks){
            if (temp.autor.equals(autor)) {
                result.add(temp);
            }
        }
        return result;
    }
    public static ArrayList<Book> getListByPubHouse (String pubHouse, Model m){
        ArrayList<Book> result = new ArrayList<Book>();
        for (Book temp : m.allBooks){
            if (temp.pubHouse.equals(pubHouse)) {
                result.add(temp);
            }
        }
        return result;
    }
    public static ArrayList<Book> getListAfterThisYear (int year, Model m){
        ArrayList<Book> result = new ArrayList<Book>();
        for (Book temp : m.allBooks){
            if (temp.year > year) {
                result.add(temp);
            }
        }
        return result;
    }
    public static ArrayList<Book> getListByPubHouse (Model m){
        ArrayList<Book> result = (ArrayList<Book>) m.allBooks.clone();
        result.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                if(!o1.pubHouse.equals(o2.pubHouse)){
                    if (o1.pubHouse.length() > o2.pubHouse.length()){
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    return 0;
                }
            }
        });
        return result;
    }
    public static void mainProgram (Model m){
        Scanner sc = new Scanner(System.in);
        String command = "";
        String tempName = "";
        while (true){
            command = sc.nextLine();
            if (command.equals("end")){
                break;
            }
            switch(command){
                case "autor":
                    tempName = sc.nextLine();
                    for (Book temp : Controller.getListByAutor(tempName, m)){
                        System.out.println(temp.toString());
                    }
                    break;
                case "publish house":
                    tempName = sc.nextLine();
                    if (tempName.equals("sort")){
                        for (Book temp : Controller.getListByPubHouse(m)){
                            System.out.println(temp.toString());
                        }
                    } else {
                        tempName = sc.nextLine();
                        for (Book temp : Controller.getListByPubHouse(tempName, m)){
                            System.out.println(temp.toString());
                        }
                    }
                    break;
                case "year":
                    tempName = sc.nextLine();
                    for (Book temp : Controller.getListAfterThisYear(Integer.parseInt(tempName), m)){
                        System.out.println(temp.toString());
                    }
                    break;
                default:
                    System.out.println("Unknown command");
                    break;
            }
        }
        sc.close();
    }
}
