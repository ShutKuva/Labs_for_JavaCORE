import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Controller {
    public static String generateIdentify (String type){
        StringBuffer result = new StringBuffer();
        result.append(type.substring(0, 5) + " ");
        result.append(Double.toString(Math.random()).substring(2, 7) + " ");
        result.append(Double.toString(Math.random()).substring(2, 7));
        return result.toString();
    }

    public static int[] transformDate (String time){
        int[] result = new int[3];
        result[0] = Integer.parseInt(time.substring(0,2).substring(0, 1).equals("0") ? time.substring(1,2) : time.substring(0,2));
        result[1] = Integer.parseInt(time.substring(3,5).substring(0, 1).equals("0") ? time.substring(4,5) : time.substring(3,5));
        return result;
    }

    public static void mainProgram (Model m){
        Scanner sc = new Scanner(System.in);
        String request = "";
        while(true){
            request = sc.nextLine();
            if (request.equals("end")){
                break;
            }
            switch(request){
                case "generate":
                    System.out.println("Enter type and specific properties");
                    String[] newTicketProp = sc.nextLine().split(", ");
                    String[] typeArr = newTicketProp[0].split(" ");
                    Ticket newTicket;
                    switch (typeArr[1]){
                        case "validity":
                            newTicket = new TicketWithValidity(newTicketProp[0], newTicketProp[1]);
                            m.generateNewTicket(newTicket);
                        case "numOfTrips":
                            newTicket = new TicketWithNumOfTrip(newTicketProp[0], Integer.parseInt(newTicketProp[1]));
                            m.generateNewTicket(newTicket);
                        case "moneyCard":
                            newTicket = new TicketWithMoney(Integer.parseInt(newTicketProp[1]));
                            m.generateNewTicket(newTicket);
                        default: System.out.println("Bad type");
                    }
                    break;
                case "show":
                    String typeOfOutput = "";
                    switch (sc.nextLine()){
                        case "log":
                            typeOfOutput = sc.nextLine();
                            if (typeOfOutput.equals("all")){
                                int failed = 0;
                                int success = 0;
                                for (Integer[] temp : Model.SUCCES_FAIL.values()){
                                    success += temp[0];
                                    failed += temp[1];
                                }
                                System.out.println("Success access " + success);
                                System.out.println("Failed access " + failed);
                            } else {
                                if (Model.SUCCES_FAIL.containsKey(typeOfOutput)){
                                    System.out.println("Success access " + Model.SUCCES_FAIL.get(typeOfOutput)[0]);
                                    System.out.println("Failed access " + Model.SUCCES_FAIL.get(typeOfOutput)[1]);
                                } else {
                                    System.out.println("Unknown field");
                                }
                            }
                            break;
                        case "negative":
                            typeOfOutput = sc.nextLine();
                            if (typeOfOutput.equals("all")){
                                int failed = 0;
                                for (Integer[] temp : Model.SUCCES_FAIL.values()){
                                    failed += temp[1];
                                }
                                System.out.println("Failed access " + failed);
                            } else {
                                if (Model.SUCCES_FAIL.containsKey(typeOfOutput)){
                                    System.out.println("Failed access " + Model.SUCCES_FAIL.get(typeOfOutput)[1]);
                                } else {
                                    System.out.println("Unknown field");
                                }
                            }
                            break;
                        case "success":
                            typeOfOutput = sc.nextLine();
                            if (typeOfOutput.equals("all")){
                                int success = 0;
                                for (Integer[] temp : Model.SUCCES_FAIL.values()){
                                    success += temp[0];
                                }
                                System.out.println("Success access " + success);
                            } else {
                                if (Model.SUCCES_FAIL.containsKey(typeOfOutput)){
                                    System.out.println("Success access " + Model.SUCCES_FAIL.get(typeOfOutput)[0]);
                                } else {
                                    System.out.println("Unknown field");
                                }
                            }
                            break;
                        case "all":
                            for (Ticket temp : m.base.values()){
                                System.out.println(temp.toString());
                            }
                            break;
                        default: System.out.println("Unknown field");
                            break;
                    }
                    break;
                default:
                    System.out.println(accessDecision(m, request));
                    break;
            }
        }
        sc.close();
    }
    public static String accessDecision (Model m, String identificator){
        Ticket target;
        if (m.base.containsKey(identificator)){
            target = m.base.get(identificator);
        } else {
            return "Nonregistered identificator";
        }
        String[] typeArr = target.type.split(" ");
        switch (typeArr[1]){
            case "validity":
                int[] targetValidity = ((TicketWithValidity)target).getValidity();
                DateTimeFormatter timeFormer = DateTimeFormatter.ofPattern("dd MM");
                LocalDateTime time = LocalDateTime.now();
                int[] formatedDate = Controller.transformDate(timeFormer.format(time));
                if (targetValidity[1] > formatedDate[1]){
                    Model.SUCCES_FAIL.get(typeArr[0])[1]++;
                    return "Your ticket is expired";
                } else if (targetValidity[1] == formatedDate[1]){
                    if (targetValidity[0] > formatedDate[0]){
                        Model.SUCCES_FAIL.get(typeArr[0])[1]++;
                        return "Your ticket is expired";
                    } else {
                        Model.SUCCES_FAIL.get(typeArr[0])[0]++;
                        return "Good luck!";
                    }
                } else {
                    Model.SUCCES_FAIL.get(typeArr[0])[0]++;
                    return "Good luck!";
                }
            case "numOfTrips":
                if (((TicketWithNumOfTrip)target).numOfTrip != 0){
                    Model.SUCCES_FAIL.get(typeArr[0])[0]++;
                    ((TicketWithNumOfTrip)target).numOfTrip--;
                    return "Good luck!";
                } else {
                    Model.SUCCES_FAIL.get(typeArr[0])[1]++;
                    return "Your ticket has been out of trips";
                }
            case "moneyCard":
                if (((TicketWithMoney)target).balance >= Model.PRICE_OF_TRIP){
                    Model.SUCCES_FAIL.get(typeArr[0])[0]++;
                    ((TicketWithMoney)target).balance -= Model.PRICE_OF_TRIP;
                    return "Good luck!";
                } else {
                    Model.SUCCES_FAIL.get(typeArr[0])[1]++;
                    return "Out of money";
                }
            default:
                return "Unknown type";
        }
    }
}
