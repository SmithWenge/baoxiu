package nanqu.djtu.shiro.manage.controller;

import nanqu.djtu.pojo.Permission;
import nanqu.djtu.pojo.Role;
import nanqu.djtu.pojo.User;
import nanqu.djtu.shiro.manage.service.RoleManageServiceI;
import nanqu.djtu.utils.ConstantFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/role")
public class RoleManageController {
    private static final Logger LOG = LoggerFactory.getLogger(RoleManageController.class);

    @Autowired
    private RoleManageServiceI roleManageService;

    /**
     * 查询系统里面所有角色
     *
     * @return 返回到角色Listy页面,包含数据
     */
    @RequestMapping("/list")
    public ModelAndView listAllRoles() {
        List<Role> roles = roleManageService.queryAllRoles();

        ModelAndView mav = new ModelAndView("admin/role/index");

        mav.addObject("roles", roles);

        return mav;
    }

    /**
     * 路由到角色添加页面
     *
     * @return 包含权限信息的角色添加页面
     */
    @RequestMapping("/routeAdd")
    public ModelAndView routeAddRole() {
        List<Permission> permissions = roleManageService.queryPermissions();

        List<Permission> distinctList = new ArrayList<>();
        List<Permission> buildingList = new ArrayList<>();
        List<Permission> roomList = new ArrayList<>();
        List<Permission> setList = new ArrayList<>();
        List<Permission> equipmentList = new ArrayList<>();
        List<Permission> printerList = new ArrayList<>();
        List<Permission> workerInfoList = new ArrayList<>();
        List<Permission> workerTypeList = new ArrayList<>();
        List<Permission> repairGroupList = new ArrayList<>();
        List<Permission> maintenanceListList = new ArrayList<>();

        for (Permission premission : permissions) {
            String premissionName = premission.getPermissionName();
            if (premissionName.substring(0, 3).equals("set")) {
                setList.add(premission);
            } else if (premissionName.substring(0, 4).equals("room")) {
                roomList.add(premission);
            } else if (premissionName.substring(0, 8).equals("distinct")) {
                distinctList.add(premission);
            } else if (premissionName.substring(0, 8).equals("building")) {
                buildingList.add(premission);
            } else if (premissionName.substring(0, 7).equals("printer")) {
                printerList.add(premission);
            } else if (premissionName.substring(0, 9).equals("equipment")) {
                equipmentList.add(premission);
            } else if (premissionName.substring(0, 10).equals("workerInfo")) {
                workerInfoList.add(premission);
            } else if (premissionName.substring(0, 10).equals("workerType")) {
                workerTypeList.add(premission);
            } else if (premissionName.substring(0, 11).equals("repairGroup")) {
                repairGroupList.add(premission);
            } else if (premissionName.substring(0, 15).equals("maintenanceList")) {
                maintenanceListList.add(premission);
            }
        }

        ModelAndView mav = new ModelAndView("admin/role/add");

        mav.addObject("distinct", distinctList);
        mav.addObject("building", buildingList);
        mav.addObject("room", roomList);
        mav.addObject("printer", printerList);
        mav.addObject("equipment", equipmentList);
        mav.addObject("set", setList);
        mav.addObject("workerInfo", workerInfoList);
        mav.addObject("workerType", workerTypeList);
        mav.addObject("repairGroup", repairGroupList);
        mav.addObject("maintenanceList", maintenanceListList);

        return mav;
    }

    /**
     * 添加新的角色
     *
     * @param addPermissions 这个角色的所有权限
     * @return 重定向到list请求
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNewRole(@RequestParam String[] addPermissions, Role role, HttpSession session,
                             RedirectAttributes redirectAttributes) {
        if (addPermissions.length <= 0) {
            return "redirect:/admin/role/routeAdd.action";
        }

        User user = (User) session.getAttribute(ConstantFields.LOGIN_KEY);
        String logUser = user.getUsername();

        if (roleManageService.addNewRole(addPermissions, role, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} add new role {} and permissions are {}.", logUser, role.getRoleName(), addPermissions);

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);
        }

        return "redirect:/admin/role/list.action";
    }

    /**
     * 编辑用户角色
     *
     * @param adminId 管理员用户Id
     * @return 管理员用户角色更改页面
     */
    @RequestMapping("/user/edit/route/{adminId}")
    public ModelAndView editUserRole(@PathVariable String adminId) {
        List<Role> userRoles = roleManageService.queryUserRoles(adminId);

        List<Role> allRoles = roleManageService.queryRoles();
        User user = roleManageService.queryUserInfo(adminId);

        ModelAndView mav = new ModelAndView("admin/role/userRoleEdit");

        mav.addObject("userRoles", userRoles);
        mav.addObject("allRoles", allRoles);
        mav.addObject("user", user);

        return mav;
    }

    /**
     * 保存新的用户的角色
     *
     * @param roles 新的角色
     * @param user 用户
     * @return 路由到用户角色编辑页面
     */
    @RequestMapping(value = "/user/edit", method = RequestMethod.POST)
    public String saveEditUserRole(@RequestParam String[] roles, User user, HttpSession session,
                                   RedirectAttributes redirectAttributes) {
        if (roles.length <= 0) {
            return "redirect:/admin/role/user/edit/route/" + user.getUserId() + ".action";
        }

        User shiroUser = (User) session.getAttribute(ConstantFields.LOGIN_KEY);
        String logUser = shiroUser.getUsername();

        if (roleManageService.updateUserRole(roles, user, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} update user role with userId is {} and roles are {}.", logUser, user.getUserId(), roles);

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);
        }

        return "redirect:/admin/role/user/edit/route/" + user.getUserId() + ".action";
    }

    /**
     * 确定新添加角色名的唯一
     *
     * @param role 包含角色名的角色对象
     * @return 如果不重复返回true, else false
     */
    @ResponseBody
    @RequestMapping(value = "/unique/roleName", method = RequestMethod.POST)
    public boolean uniqueRoleName(Role role) {
        boolean isUnique = roleManageService.query4RoleNameUnique(role.getRoleName());

        return isUnique;
    }

    /**
     * 编辑角色
     *
     * @param roleId 角色Id
     * @return 返回到角色编辑页面
     */
    @RequestMapping("/edit/route/{roleId}")
    public ModelAndView route2RoleEdit(@PathVariable String roleId) {
        ModelAndView mav = new ModelAndView("admin/role/edit");

        Role role = roleManageService.queryRoleInfo(roleId);
        List<Permission> checkedPermissions = roleManageService.queryRoleCheckPermissions(roleId);
        List<Permission> uncheckedPermissions = roleManageService.queryRoleUnckeckedPermissions(roleId);

        List<Permission> hadDistinctList = new ArrayList<>();
        List<Permission> hadBuildingList = new ArrayList<>();
        List<Permission> hadRoomList = new ArrayList<>();
        List<Permission> hadSetList = new ArrayList<>();
        List<Permission> hadEquipmentList = new ArrayList<>();
        List<Permission> hadPrinterList = new ArrayList<>();
        List<Permission> hadWorkerInfoList = new ArrayList<>();
        List<Permission> hadWorkerTypeList = new ArrayList<>();
        List<Permission> hadRepairGroupList = new ArrayList<>();
        List<Permission> hadMaintenanceListList = new ArrayList<>();

        for (Permission premission : checkedPermissions) {
            String premissionName = premission.getPermissionName();
            if (premissionName.substring(0, 3).equals("set")) {
                hadSetList.add(premission);
            } else if (premissionName.substring(0, 4).equals("room")) {
                hadRoomList.add(premission);
            } else if (premissionName.substring(0, 8).equals("distinct")) {
                hadDistinctList.add(premission);
            } else if (premissionName.substring(0, 8).equals("building")) {
                hadBuildingList.add(premission);
            } else if (premissionName.substring(0, 7).equals("printer")) {
                hadPrinterList.add(premission);
            } else if (premissionName.substring(0, 9).equals("equipment")) {
                hadEquipmentList.add(premission);
            } else if (premissionName.substring(0, 10).equals("workerInfo")) {
                hadWorkerInfoList.add(premission);
            } else if (premissionName.substring(0, 10).equals("workerType")) {
                hadWorkerTypeList.add(premission);
            } else if (premissionName.substring(0, 11).equals("repairGroup")) {
                hadRepairGroupList.add(premission);
            } else if (premissionName.substring(0, 15).equals("maintenanceList")) {
                hadMaintenanceListList.add(premission);
            }
        }

        List<Permission> distinctList = new ArrayList<>();
        List<Permission> buildingList = new ArrayList<>();
        List<Permission> roomList = new ArrayList<>();
        List<Permission> setList = new ArrayList<>();
        List<Permission> equipmentList = new ArrayList<>();
        List<Permission> printerList = new ArrayList<>();
        List<Permission> workerInfoList = new ArrayList<>();
        List<Permission> workerTypeList = new ArrayList<>();
        List<Permission> repairGroupList = new ArrayList<>();
        List<Permission> maintenanceListList = new ArrayList<>();

        for (Permission premission : uncheckedPermissions) {
            String premissionName = premission.getPermissionName();
            if (premissionName.substring(0, 3).equals("set")) {
                setList.add(premission);
            } else if (premissionName.substring(0, 4).equals("room")) {
                roomList.add(premission);
            } else if (premissionName.substring(0, 8).equals("distinct")) {
                distinctList.add(premission);
            } else if (premissionName.substring(0, 8).equals("building")) {
                buildingList.add(premission);
            } else if (premissionName.substring(0, 7).equals("printer")) {
                printerList.add(premission);
            } else if (premissionName.substring(0, 9).equals("equipment")) {
                equipmentList.add(premission);
            } else if (premissionName.substring(0, 10).equals("workerInfo")) {
                workerInfoList.add(premission);
            } else if (premissionName.substring(0, 10).equals("workerType")) {
                workerTypeList.add(premission);
            } else if (premissionName.substring(0, 11).equals("repairGroup")) {
                repairGroupList.add(premission);
            } else if (premissionName.substring(0, 15).equals("maintenanceList")) {
                maintenanceListList.add(premission);
            }
        }

        mav.addObject("role", role);

        mav.addObject("checkedDistinct", hadDistinctList);
        mav.addObject("checkedBuilding", hadBuildingList);
        mav.addObject("checkedRoom", hadRoomList);
        mav.addObject("checkedPrinter", hadPrinterList);
        mav.addObject("checkedEquipment", hadEquipmentList);
        mav.addObject("checkedSet", hadSetList);
        mav.addObject("checkedWorkerInfo", hadWorkerInfoList);
        mav.addObject("checkedWorkerType", hadWorkerTypeList);
        mav.addObject("checkedRepairGroup", hadRepairGroupList);
        mav.addObject("checkedMaintenanceList", hadMaintenanceListList);

        mav.addObject("uncheckedPermissions", uncheckedPermissions);
        mav.addObject("distinct", distinctList);
        mav.addObject("building", buildingList);
        mav.addObject("room", roomList);
        mav.addObject("printer", printerList);
        mav.addObject("equipment", equipmentList);
        mav.addObject("set", setList);
        mav.addObject("workerInfo", workerInfoList);
        mav.addObject("workerType", workerTypeList);
        mav.addObject("repairGroup", repairGroupList);
        mav.addObject("maintenanceList", maintenanceListList);

        return mav;
    }

    /**
     * 编辑角色
     *
     * @param editPermissions 角色的心权限
     * @param role 角色的其他信息
     * @return 编辑成功返回定向到list请求,编辑失败返回编辑页面
     */
    @RequestMapping("/edit/save")
    public String saveRoleEdit(@RequestParam String[] editPermissions, Role role, HttpSession session,
                               RedirectAttributes redirectAttributes) {
        if (editPermissions.length <= 0) {
            return "redirect:/admin/role/edit/route/" + role.getRoleId() + ".action";
        }

        User shiroUser = (User) session.getAttribute(ConstantFields.LOGIN_KEY);
        String logUser = shiroUser.getUsername();

        if (roleManageService.updateRolePermissions(editPermissions, role, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} update role permission with permissions is {} and roleId is {}.", logUser, editPermissions, role.getRoleId());

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/role/list.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/role/edit/route/" + role.getRoleId() + ".action";
        }
    }
}
