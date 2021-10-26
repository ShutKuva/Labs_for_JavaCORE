public class Dispatcher {
    public static void main (String... args){
        Ticket[] allTickets = {
                new TicketWithValidity("student validity", "11 11"),
                new TicketWithValidity("school validity", "11 10"),
                new TicketWithValidity("regular validity", "23 10"),
                new TicketWithNumOfTrip("student numOfTrips", 11),
                new TicketWithNumOfTrip("school numOfTrips", 0),
                new TicketWithNumOfTrip("regular numOfTrips", 2),
                new TicketWithMoney(120),
        };
        Model m = new Model(allTickets);
        Controller.mainProgram(m);
    }
}
