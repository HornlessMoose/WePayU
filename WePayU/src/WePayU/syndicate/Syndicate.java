package WePayU.syndicate;

import java.util.ArrayList;

public class Syndicate {
    private float monthlyFee;
    private int employeeID, syndicateID;

    private ArrayList<SyndicateAdditionalFee> syndicateAdditionalFees = new ArrayList<>();

    public Syndicate(float monthlyFee, int employeeID, int syndicateID){
        this.monthlyFee = monthlyFee;
        this.employeeID = employeeID;
        this.syndicateID = syndicateID;
    }

    public float getMonthlyFee() {
        return monthlyFee;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public int getSyndidateID() {
        return syndicateID;
    }

    public void setMonthlyFee(float monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public void setEmployeeID(int employeeID) {

        this.employeeID = employeeID;
    }

    public void setSyndidateID(int syndidateID) {
        this.syndicateID = syndidateID;
    }

    public ArrayList<SyndicateAdditionalFee> getSyndicateAdditionalFees() {
        return syndicateAdditionalFees;
    }
}
