package WePayU.employees;

public class Salaried extends Employee{
    private float salary;

    public Salaried(String name, String address, int ID,String paymentMethod, float salary) {
        super(name, address, ID, paymentMethod);
        this.salary = salary;
    }

    public float getSalary() {
        return salary;
    }

    public float calculateSalary(){
       return salary;
    }
}
