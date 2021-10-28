import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Controller {
    public static void mainProgram (Model m){
        Scanner sc = new Scanner(System.in);
        String request = "";
        while (true){
            request = sc.nextLine();
            if (request.equals("shot down")){
                break;
            }
            switch (request){
                case "generate new ski pass":
                    System.out.println("Please enter type of ski pass, validity and specify information (type, validity, ...)");
                    String[] information = sc.nextLine().split(", ");
                    switch (information[0]){
                        case "SP weekend withNum":
                        case "SP workDay withNum":
                            m.addNewSkiPass(new SkiWithNum(information[0], information[1], false, Integer.parseInt(information[2])));
                            break;
                        case "SP weekend withoutNum":
                        case "SP workDay withoutNum":
                            if (information[0].contains(" by days")){
                                m.addNewSkiPass(new SkiWithoutNum(information[0], information[1], Integer.parseInt(information[2]), false));
                            } else {
                                m.addNewSkiPass(new SkiWithoutNum(information[0], information[1], false, Integer.parseInt(information[2]), Integer.parseInt(information[3])));
                            }
                            break;
                        case "SP season pass":
                            m.addNewSkiPass(new SkiSeasonPass(information[1], false));
                            break;
                        default:
                            System.out.println("Bad type");
                            break;
                    }
                    break;
                case "show":
                    String whatShow = sc.nextLine();
                    switch (whatShow){
                        case "active":
                            for (SkiPass temp : m.fullBase.values()){
                                System.out.println(temp.toString());
                            }
                            break;
                        case "blocked":
                            for (SkiPass temp : m.blockedPasses.values()){
                                System.out.println(temp.toString());
                            }
                            break;
                        case "passes":
                            switch (sc.nextLine()){
                                case "success":
                                    System.out.println(Tourniquet.success);
                                    break;
                                case "failed":
                                    System.out.println(Tourniquet.negative);
                                    break;
                                default: System.out.println("Unknown command");
                                break;
                            }
                            break;
                        default: System.out.println("Unknown command");
                        break;
                    }
                    break;
                case "":
                    System.out.println("Please enter your command");
                    break;
                default: System.out.println(Controller.decisionOfAdmission(m, request));
            }
        }
        sc.close();
    }

    public static String generateIdentify (String type){
        StringBuffer result = new StringBuffer();
        result.append(type.substring(3, 7) + " ");
        result.append(Double.toString(Math.random()).substring(2, 7) + " ");
        result.append(Double.toString(Math.random()).substring(2, 7));
        return result.toString();
    }

    public static int[] transformDate (String time){
        int[] result = new int[2];
        result[0] = Integer.parseInt(time.substring(0,2).substring(0, 1).equals("0") ? time.substring(1,2) : time.substring(0,2));
        result[1] = Integer.parseInt(time.substring(3,5).substring(0, 1).equals("0") ? time.substring(4,5) : time.substring(3,5));
        return result;
    }

    public static String decisionOfAdmission(Model m, String identificator){
        SkiPass target = m.fullBase.get(identificator);
        if (target == null){
            target = m.blockedPasses.get(identificator);
            if (target != null){
                Tourniquet.negative++;
                return "Your pass has been blocked by reason of " + target.reason;
            } else {
                Tourniquet.negative++;
                return "Your pass is unregistered";
            }
        }
        int[] targetValidity = target.getValidity();
        DateTimeFormatter timeFormer = DateTimeFormatter.ofPattern("dd MM");
        LocalDateTime time = LocalDateTime.now();
        int[] formatedDate = Controller.transformDate(timeFormer.format(time));
        if (targetValidity[1] < formatedDate[1]){
            m.blockSkiPass(target, "expired");
            Tourniquet.negative++;
            return "Your pass has been blocked by reason of expired";
        } else if (targetValidity[1] == formatedDate[1]){
            if (targetValidity[0] < formatedDate[0]){
                m.blockSkiPass(target, "expired");
                Tourniquet.negative++;
                return "Your pass has been blocked by reason of expired";
            }
        }
        switch (target.type) {
            case "SP weekend withNum":
            case "SP workDay withNum":
                return proccesWithNum((SkiWithNum) target, m);
            case "SP weekend withoutNum":
            case "SP workDay withoutNum":
                return proccesWithoutNum((SkiWithoutNum) target, m);
            case "SP season pass":
                Tourniquet.success++;
                return "Good luck!";
            default:
                m.blockSkiPass(target, "unknown type");
                return "Your pass has been blocked by reason of unknown type";
        }
    }

    private static String proccesWithNum (SkiWithNum target, Model m){
        if (target.numberOfLifts == 0){
            m.blockSkiPass(target, "out of number of lifts");
            Tourniquet.negative++;
            return "Your pass has been blocked by reason of out of number of lifts";
        } else {
            target.numberOfLifts--;
            Tourniquet.success++;
            return "Good luck!";
        }
    }
    private static String proccesWithoutNum (SkiWithoutNum target, Model m){
        DateTimeFormatter timeFormer = DateTimeFormatter.ofPattern("hh");
        LocalDateTime time = LocalDateTime.now();
        int formatedTime = Integer.parseInt(timeFormer.format(time).substring(0,2).substring(0, 1).equals("0") ? timeFormer.format(time).substring(1,2) : timeFormer.format(time).substring(0,2));
        if (target.startHour > formatedTime){
            m.blockSkiPass(target, "out of number of lifts");
            Tourniquet.negative++;
            return "Your time will start only after " + (target.startHour - formatedTime) + ((target.startHour - formatedTime) > 1 ? "hours" : "hour");
        } else if (target.endHour < formatedTime){
            m.blockSkiPass(target, "times out");
            Tourniquet.negative++;
            return "Your pass has been blocked by reason of out of number of times out";
        } else {
            Tourniquet.success++;
            return "Good luck!";
        }
    }
}
