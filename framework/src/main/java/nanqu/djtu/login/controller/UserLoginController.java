package nanqu.djtu.login.controller;

import com.google.common.base.Optional;
import nanqu.djtu.login.service.UserLoginServiceI;
import nanqu.djtu.pojo.AdminUser;
import nanqu.djtu.pojo.PlaceBuilding;
import nanqu.djtu.pojo.User;
import nanqu.djtu.utils.ConstantFields;
import nanqu.djtu.utils.PasswordUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class UserLoginController {
    private static final Logger LOG = LoggerFactory.getLogger(UserLoginController.class);

    @Autowired
    private UserLoginServiceI userLoginService;

    /**
     * 路由到用户登录页面
     *
     * @return 用户登录页面地址
     */
    @RequestMapping("/route")
    public String routeLogin() {
        return "admin/login/index";
    }

    /**
     * 用户登录的get方法处理
     *
     * @return 如果已经登陆就定向到home, else定向到登陆页面
     */
    @RequestMapping(value = "/do", method = RequestMethod.GET)
    public String getLogin(HttpSession session) {
        AdminUser loginUser = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        if (Optional.fromNullable(loginUser).isPresent()) {
            return "redirect:/admin/home/index.action";
        } else {
            return "redirect:/login/route.action";
        }
    }

    /**
     * 用户登录系统,登陆信息同时在shiro的session中和request的session保存
     *
     * @param user 用户登录信息
     * @param session 当前会话信息
     * @param redirectAttributes 登陆错误重定向信息提示
     * @param bindingResult 数据绑定结果
     * @return 登陆成功到home,else 重新登陆
     */
    @RequestMapping(value = "/do", method = RequestMethod.POST)
    public String login(User user, HttpSession session, RedirectAttributes redirectAttributes,
                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/login/route.action";
        }

        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(new UsernamePasswordToken(user.getUsername(), PasswordUtils.encrypt(user.getPassword())));

            User loginUser = (User) currentUser.getSession().getAttribute(ConstantFields.LOGIN_KEY);
            Optional<User> optional = Optional.fromNullable(loginUser);

            if (!optional.isPresent()) {
                return "redirect:/login/route.action";
            } else {
                AdminUser adminUser = userLoginService.queryAdminUser(loginUser.getAdminUserId());
                session.setAttribute(ConstantFields.SESSION_LOGIN_KEY, adminUser);
            }

            if (LOG.isInfoEnabled())
                LOG.info("[LOGIN] {} login system at {} .", loginUser.getUsername(), DateTime.now());

            return "redirect:/admin/home/index.action";
        } catch (AuthenticationException e) {
            redirectAttributes.addFlashAttribute("message", "用户名或者密码错误");

            return "redirect:/login/route.action";
        }
    }

    /**
     * 用户退出登录
     *
     * @param session request会话信息
     * @return 重登向到登陆页面请求
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
      public String logout(HttpSession session) {
        session.removeAttribute(ConstantFields.SESSION_LOGIN_KEY);

        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();

        return "redirect:/login/route.action";
    }
    /**
     * 用户修改密码
     *
     * @param session request会话信息
     * @return 重登向到登陆页面请求
     */
    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public ModelAndView changePassword(HttpSession session) {

        AdminUser user = (AdminUser)session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);
        AdminUser adminUser = userLoginService.queryUserId(user.getAdminUserId());
        ModelAndView modelAndView = new ModelAndView("admin/login/changePassword");
        modelAndView.addObject("adminUser", adminUser);
        return modelAndView;
    }

    /**
     * 保存密码修改
     *
     * @param adminUser 用户信息
     * @param redirectAttributes 修改操作提示信息
     * @return 保存成功返回list, else edit page
     */
   @RequestMapping(value = "/changePassword/do", method = RequestMethod.POST)
    public String saveEditPlaceDistinct(AdminUser adminUser, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        boolean update = userLoginService.updatePassword(adminUser, user);

        if (update) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/home/index.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/changePassword" + adminUser.getUserId() + ".action";
        }
    }
}
