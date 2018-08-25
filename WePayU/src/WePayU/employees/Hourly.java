package WePayU.employees;

import WePayU.timeCard.TimeCard;

import java.util.ArrayList;


public class Hourly extends Employee{

    ArrayList<TimeCard> timeCards = new ArrayList<>();


    public Hourly(String name, String address, int ID, String paymentMethod) {
        super(name, address, ID, paymentMethod);

    }

    public ArrayList<TimeCard> getTimeCards() {
        return timeCards;
    }
}
