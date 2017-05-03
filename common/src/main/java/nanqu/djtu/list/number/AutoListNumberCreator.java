package nanqu.djtu.list.number;

public class AutoListNumberCreator {
    private static final String DISTINCT_PREFIX = "A";
    private static final String BUILDING_PREFIX = "B";
    private static final String ROOM_PREFIX = "C";
    private static final String EQUIPMENT_PREFIX = "D";

    public static String createDistinctNumber(int count) {
        return DISTINCT_PREFIX + String.valueOf(count);
    }

    public static String createBuildingNumber(int count) {
        return BUILDING_PREFIX + String.valueOf(count);
    }

    public static String createRoomNumber(int count) {
        return ROOM_PREFIX + String.valueOf(count);
    }

    public static String createEquipmentNumber(int count) {
        return EQUIPMENT_PREFIX + String.valueOf(count);
    }
}
