package WePayU.employees;

import WePayU.saleResult.SaleResult;

import java.util.ArrayList;

public class Commisioned extends Salaried{
    float commission;
    private ArrayList<SaleResult>  saleResults = new ArrayList<>();


    public Commisioned(String name, String address, int ID, String paymentMethod, float salary, float commission) {
        super(name, address, ID, paymentMethod, salary);
        this.commission = commission;

    }

    public float getCommission() {
        return commission;
    }

    public ArrayList<SaleResult> getSaleResults() {
        return saleResults;
    }

    public void setCommission(float commission) {
        this.commission = commission;
    }

    public float getPaymentValue(){
        return commission * getSalary() + (getSalary()/2);
    }
}
