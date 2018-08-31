package WePayU.employees;

import WePayU.saleResult.SaleResult;

import java.util.ArrayList;

public class Commissioned extends Salaried{
    private float commission;
    private ArrayList<SaleResult>  saleResults = new ArrayList<>();


    public Commissioned(String name, String address, int ID, String paymentMethod, float salary, float commission) {
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

    @Override
    public float calculateSalary() {
        float salary = 0;

        for (SaleResult saleResult:saleResults) {
            salary +=  saleResult.getValue();
        }

        return getSalary()/2 + salary * commission;

    }
}
