package nanqu.djtu.pojo;

/**
 * Created by Administrator on 2017/4/2.
 */
public class PlaceBuilding {
    private String buildingId;
    private String buildingName;
    private String distinctId;
    private String setId;
    private String hiddenBuildingNumber;

    public String getHiddenBuildingNumber() {
        return hiddenBuildingNumber;
    }

    public void setHiddenBuildingNumber(String hiddenBuildingNumber) {
        this.hiddenBuildingNumber = hiddenBuildingNumber;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getDistinctId() {
        return distinctId;
    }

    public void setDistinctId(String distinctId) {
        this.distinctId = distinctId;
    }

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    private String buildingNumber;
}
