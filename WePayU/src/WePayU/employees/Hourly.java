package WePayU.employees;


import WePayU.timeCard.TimeCard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;

public class Hourly extends Employee{

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


    private ArrayList<TimeCard> timeCards = new ArrayList<>();

    private float hourlySalary;

    public Hourly(String name, String address, int ID, String paymentMethod, float hourlySalary) {
        super(name, address, ID, paymentMethod);
        this.hourlySalary = hourlySalary;

    }


    public ArrayList<TimeCard> getTimeCards() {
        return timeCards;
    }



    public float calculateSalary() throws ParseException {
        float salary = 0;
        long hours;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        for(TimeCard timeCard:timeCards){
             c1.setTime(sdf.parse(timeCard.getStart()));
             c2.setTime(sdf.parse(timeCard.getEnd()));

             hours = ChronoUnit.HOURS.between(c1.toInstant(),c2.toInstant());

             if(hours <= 8){
                salary += hours * hourlySalary;
             }
             else {
                salary += (8 * hourlySalary + ((hours - 8) * 1.5 * hourlySalary));
             }
        }

        return salary;
    }
}
