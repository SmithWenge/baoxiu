package nanqu.djtu.pojo;

public class PlaceDistinct {
    private String distinctId;
    private String distinctName;
    private String hiddenDistinctName;
    private String distinctNumber;
    private String hiddenDistinctNumber;

    public void setHiddenDistinctName(String hiddenDistinctName) {
        this.hiddenDistinctName = hiddenDistinctName;
    }

    public void setHiddenDistinctNumber(String hiddenDistinctNumber) {
        this.hiddenDistinctNumber = hiddenDistinctNumber;
    }

    public String getHiddenDistinctName() {

        return hiddenDistinctName;
    }

    public String getHiddenDistinctNumber() {
        return hiddenDistinctNumber;
    }

    public void setDistinctNumber(String distinctNumber) {
        this.distinctNumber = distinctNumber;
    }

    public String getDistinctNumber() {

        return distinctNumber;
    }

    public void setDistinctId(String distinctId) {
        this.distinctId = distinctId;
    }

    public void setDistinctName(String distinctName) {
        this.distinctName = distinctName;
    }

    public String getDistinctId() {

        return distinctId;
    }

    public String getDistinctName() {
        return distinctName;
    }
}
