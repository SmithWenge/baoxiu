package nanqu.djtu.app.util;

import nanqu.djtu.utils.ConstantFields;

public class MaintenanceListStateToStringUtil {
    public static String stateNumberToColorString(String listState) {
        switch (listState) {
            case "1":
                return ConstantFields.MAINTENANCELIST_COMMIT_STATE_COLOR;
            case "2":
                return ConstantFields.MAINTENANCELIST_ACCEPT_STATE_COLOR;
            case "3":
                return ConstantFields.MAINTENANCELIST_DONE_STATE_COLOR;
            case ConstantFields.MAINTENANCELIST_ADD_ADMIN_MODIFY_STATE:
                return ConstantFields.MAINTENANCELIST_MODIFY_STATE_COLOR;
            case "6":
                return ConstantFields.MAINTENANCELIST_DELAY_STATE_COLOR;
            default:
                return ConstantFields.MAINTENANCELIST_COMMIT_STATE_COLOR;
        }
    }
}
