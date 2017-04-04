package nanqu.djtu.pojo;

public class EquipmentSet {
    private String setId;
    private String setName;
    private String setNumber;
    private String hiddenSetNumber;

    public void setHiddenSetNumber(String hiddenSetNumber) {
        this.hiddenSetNumber = hiddenSetNumber;
    }

    public String getHiddenSetNumber() {

        return hiddenSetNumber;
    }

    public void setSetNumber(String setNumber) {
        this.setNumber = setNumber;
    }

    public String getSetNumber() {

        return setNumber;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getSetId() {

        return setId;
    }

    public String getSetName() {
        return setName;
    }
}
