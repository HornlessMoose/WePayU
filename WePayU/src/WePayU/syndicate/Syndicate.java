package WePayU.syndicate;

public class Syndicate {
    float monthlyFee, additionalFee;
    int employeeID, syndidateID;

    public Syndicate(float monthlyFee, float additionalFee, int employeeID, int syndidateID){
        this.monthlyFee = monthlyFee;
        this.additionalFee = additionalFee;
        this.employeeID = employeeID;
        this.syndidateID = syndidateID;
    }

    public float getMonthlyFee() {
        return monthlyFee;
    }

    public float getAdditionalFee() {
        return additionalFee;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public int getSyndidateID() {
        return syndidateID;
    }

    public void setMonthlyFee(float monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public void setAdditionalFee(float additionalFee) {
        this.additionalFee += additionalFee;
    }

    public void setEmployeeID(int employeeID) {

        this.employeeID = employeeID;
    }

    public void setSyndidateID(int syndidateID) {
        this.syndidateID = syndidateID;
    }
}
