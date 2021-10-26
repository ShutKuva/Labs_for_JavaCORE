import java.util.HashMap;
import java.util.ArrayList;

public class Model {
    static int PRICE_OF_TRIP = 8;
    static HashMap<String, Integer[]> SUCCES_FAIL = new HashMap<String, Integer[]>();
    static{
        Integer[] empty = {0,0};
        SUCCES_FAIL.put("student", empty.clone());
        SUCCES_FAIL.put("school", empty.clone());
        SUCCES_FAIL.put("regular", empty.clone());
    }
    HashMap<String, Ticket> base = new HashMap<String, Ticket>();
    Model(){

    }
    Model(ArrayList<Ticket> arr){
        for (Ticket temp : arr){
            this.base.put(temp.identificator, temp);
        }
    }
    Model(Ticket[] arr){
        for (Ticket temp : arr){
            this.base.put(temp.identificator, temp);
        }
    }

    public void generateNewTicket(Ticket ticket){
        this.base.put(ticket.identificator, ticket);
    }
}

class Ticket{
    String identificator;
    String type;
    Ticket(){

    }
    Ticket(String type){
        this.type= type;
        this.identificator = Controller.generateIdentify(type);
    }

    @Override
    public String toString(){
        return this.identificator + " " + this.type;
    }
}

class TicketWithValidity extends Ticket{
    String validity;

    TicketWithValidity(){

    }
    TicketWithValidity(String type, String validity){
        super(type);
        this.validity = validity;
    }

    public int[] getValidity(){
        return Controller.transformDate(this.validity);
    }
}

class TicketWithNumOfTrip extends Ticket{
    int numOfTrip;
    TicketWithNumOfTrip(){

    }
    TicketWithNumOfTrip(String type, int numOfTrip){
        super(type);
        this.numOfTrip = numOfTrip;
    }
}

class TicketWithMoney extends Ticket{
    int balance;
    TicketWithMoney(){

    }
    TicketWithMoney(int balance){
        super("regular moneyCard");
        this.balance = balance;
    }

    public void topUpBalance (int numOfMoney){
        if(numOfMoney > 0){
            balance += numOfMoney;
        }
    }
}