package WePayU.employees;


import java.text.ParseException;


public class Employee {
    private String name, address, paymentMethod, paymentSchedule;
    private int ID;


    public Employee(String name, String address, int ID, String paymentMethod){
        this.name = name;
        this.address = address;
        this.ID = ID;
        this.paymentMethod = paymentMethod;
    }


    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getID() {
        return ID;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentSchedule(String paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public String getPaymentSchedule() {
        return paymentSchedule;
    }

    @Override
    public String toString() {
        return  "\nID: " + getID() + "\nNome: " + getName() + "\nEndereço: "+ getAddress()
                + "\nMetodo de pagamento: " + getPaymentMethod();
    }

    public float calculateSalary() throws ParseException {
        return 0;
    }
}
