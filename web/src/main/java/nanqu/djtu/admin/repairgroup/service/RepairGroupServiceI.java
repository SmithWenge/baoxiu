package nanqu.djtu.admin.repairgroup.service;

import nanqu.djtu.pojo.AdminUser;
import nanqu.djtu.pojo.RepairGroup;

import java.util.List;

/**
 * Created by DELL on 2017/4/3.
 */
public interface RepairGroupServiceI {
    List<RepairGroup> query4List();
    boolean saveNewRepairGroup(RepairGroup group, AdminUser user);
    boolean deleteDistinct(String repairGroupId, AdminUser user);
    RepairGroup query4Edit(String repairGroupId);
    boolean updateRepairGroup(RepairGroup group, AdminUser user);
    boolean query4RepairGroupNumberUnique(String groupNumber);
}
