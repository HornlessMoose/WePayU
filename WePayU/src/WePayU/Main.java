package WePayU;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        MainControl mainControl = new MainControl();
        mainControl.initialPaymentSchedule();
        mainControl.menu();

    }
}
