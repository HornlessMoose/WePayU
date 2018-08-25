package WePayU.employees;

/**
 * Created by alunoic on 24/08/18.
 */
public class Employee {
    String name, address, paymentMethod;
    int ID;

    public Employee(String name, String address, int ID, String paymenteMethod){
        this.name = name;
        this.address = address;
        this.ID = ID;
        this.paymentMethod = paymenteMethod;
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

    @Override
    public String toString() {
        return "\nNome: " + getName() + "\nEndereço: "+ getAddress()
                + "\nMetodo de pagamento: " + getPaymentMethod();
    }
}
