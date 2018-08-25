package WePayU.employees;

public class Salaried extends Employee{
    float salary;

    public Salaried(String name, String address, int ID,String paymentMethod, float salary) {
        super(name, address, ID, paymentMethod);
        this.salary = salary;
    }

    public float getSalary() {
        return salary;
    }
}
