package WePayU.syndicate;

public class SyndicateAdditionalFee {
    private String description;
    private float additionalFee;

    public SyndicateAdditionalFee(String description, float additionalFee){
        this.description = description;
        this.additionalFee = additionalFee;
    }

    public String getDescription() {
        return description;
    }

    public float getAdditionalFee() {
        return additionalFee;
    }
}
