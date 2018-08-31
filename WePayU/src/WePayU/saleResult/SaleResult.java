package WePayU.saleResult;

public class SaleResult {
    private String saleDate;
    private float value;

    public SaleResult(String saleDate, float value){
        this.saleDate = saleDate;
        this.value = value;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public float getValue() {
        return value;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
