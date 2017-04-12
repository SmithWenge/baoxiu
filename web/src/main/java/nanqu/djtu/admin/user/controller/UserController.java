package nanqu.djtu.admin.user.controller;

import com.google.common.base.Optional;
import nanqu.djtu.admin.user.service.UserServiceI;
import nanqu.djtu.pojo.AdminUser;

import nanqu.djtu.pojo.PlaceBuilding;
import nanqu.djtu.utils.ConstantFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zhangwenyue
 */
@Controller
@RequestMapping("/admin/userInfo")
public class UserController {
    @Autowired
    private UserServiceI userService;

    /**
     * 管理员用户信息
     * @return 用户信息页面
     */
    @RequestMapping("/list")
    public ModelAndView list() {
        List<AdminUser> users = userService.queryAdminUserList();

        ModelAndView mav = new ModelAndView("admin/user/list");

        mav.addObject("user",users);

        return  mav;
    }

    /**
     * 路由到管理员添加页面
     *
     * @return 管理员添加页面地址
     */
    @RequestMapping("/add/router")
    public ModelAndView routeAdd() {
        ModelAndView modelAndView = new ModelAndView("admin/user/add");
        return modelAndView;
    }

    /**
     * 保存添加新的管理员
     *
     * @param adminUser 新管理员
     * @param redirectAttributes 添加操作提示信息
     * @return 添加成功返回list页面,else add页面
     */
    @RequestMapping(value = "/add/do", method = RequestMethod.POST)
    public String save(AdminUser adminUser, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);
        boolean save = userService.saveNewAdminUser(adminUser, user);

        if (save) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/userInfo/list.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/userInfo/add/router.action";
        }
    }

    /**
     * 验证用户名的唯一
     *
     * @param adminUser 管理员的对象
     * @return 唯一返回true, else false
     */
    @ResponseBody
    @RequestMapping(value = "/unique/username", method = RequestMethod.POST)
    public boolean usernameUnique(AdminUser adminUser) {
        String username = adminUser.getUsername();
        String hiddenUsername =adminUser.getHiddenUsername();


        return username.equalsIgnoreCase(hiddenUsername) || userService.query4UsernameUnique(username);

    }
    /**
     * 验证工号编号的唯一
     *
     * @param adminUser 管理员的对象
     * @return 唯一返回true, else false
     */
    @ResponseBody
    @RequestMapping(value = "/unique/adminNumber", method = RequestMethod.POST)
    public boolean adminNumberUnique(AdminUser adminUser) {
        String adminNumber = adminUser.getAdminNumber();
        String hiddenAdminNumber =adminUser.getHiddenAdminNumber();
        boolean select = userService.query4AdminNumberUnique(adminNumber);
        return adminNumber.equalsIgnoreCase(hiddenAdminNumber) ||
                userService.query4AdminNumberUnique(adminNumber);
    }

    /**
     * 路由到管理员用户编辑页面
     *
     * @param adminUserId, 地点Id
     * @return 管理员用户编辑页面和相对用户信息
     */
    @RequestMapping("/edit/route/{adminUserId}")
    public ModelAndView routeEdit(@PathVariable String adminUserId ) {
        AdminUser adminUser= userService.query4Edit(adminUserId);

        if (Optional.fromNullable(adminUser).isPresent()) {
            ModelAndView mav = new ModelAndView("/admin/user/edit");
            mav.addObject("adminUser", adminUser);
            return mav;

        } else {
            return new ModelAndView("redirect:/admin/userInfo/list.action");
        }
    }

    /**
     * 保存用户信息修改
     *
     * @param adminUser 用户信息
     * @param redirectAttributes 修改操作提示信息
     * @return 保存成功返回list, else edit page
     */
    @RequestMapping(value = "/edit/do", method = RequestMethod.POST)
    public String saveEditAdminUser(AdminUser adminUser, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        boolean update = userService.updateAdminUser(adminUser, user);

        if (update) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/userInfo/list.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/userInfo/edit/route/" +adminUser.getAdminUserId() + ".action";
        }
    }

    /**
     * 删除管理员用户信息
     *
     * @param adminUserId 管理员用户Id
     * @param redirectAttributes 删除操作提示信息
     * @return 返回到list页面
     */
    @RequestMapping("/delete/{adminUserId}")
    public String delete(@PathVariable String adminUserId, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);
        AdminUser adminUser = userService.selectUserIdByAdminUserId(adminUserId);
        adminUser.setAdminUserId(adminUserId);
        boolean delete = userService.deleteAdminUser(adminUser, user);

        if (delete) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/userInfo/list.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/userInfo/list.action";
        }
    }
    /**
     * 管理员用户信息
     * @return 用户信息页面
     */
    @RequestMapping("/changePassword/list")
    public ModelAndView changePasswordList() {
        List<AdminUser> users = userService.queryAdminUserList();

        ModelAndView mav = new ModelAndView("admin/password/list");

        mav.addObject("user", users);

        return  mav;
    }
    /**
     * 路由到管理员用户密码编辑页面
     *
     * @param adminUserId, 用户Id
     * @return 管理员用户密码编辑页面和相对用户信息
     */
    @RequestMapping("/changePassword/edit/route/{adminUserId}")
    public ModelAndView routeChangePasswordEdit(@PathVariable String adminUserId) {
        AdminUser adminUser= userService.query4Edit(adminUserId);

        if (Optional.fromNullable(adminUser).isPresent()) {
            ModelAndView mav = new ModelAndView("/admin/password/edit");
            mav.addObject("adminUser", adminUser);
            return mav;

        } else {
            return new ModelAndView("redirect:/admin/userInfo/list.action");
        }
    }

    /**
     * 保存用户密码信息修改
     *
     * @param adminUser 用户信息
     * @param redirectAttributes 修改操作提示信息
     * @return 保存成功返回list, else edit page
     */
    @RequestMapping(value = "/changePassword/edit/do", method = RequestMethod.POST)
    public String saveEditPassword(AdminUser adminUser, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        boolean update = userService.updatePassword(adminUser, user);

        if (update) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/userInfo/changePassword/list.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/userInfo/changePassword/edit/route/" +adminUser.getAdminUserId() + ".action";
        }
    }

}
