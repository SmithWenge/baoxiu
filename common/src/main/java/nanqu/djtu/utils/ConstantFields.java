package nanqu.djtu.utils;

public class ConstantFields {
    /* 分页中每页数据数 */
    public static final int DEFAULT_PAGE_SIZE = 10;
    /* ajax分页中数据的KEY */
    public static final String PAGE_KEY = "page";
    /* 消息操作key*/
    public static final String OPERATION_MESSAGE = "message";

    public static final String SESSION_ROOM_SEARCH_KEY = "placeRoomSearchKey";

    /**
     * 默认邮件配置项
     */
    public static final String MAIL_DEFAULT_HOST_KEY = "mail.smtp.host";
    public static final String MAIL_DEFAULT_AUTH_ENABLE_KEY = "mail.smtp.auth";
    public static final String MAIL_DEFAULT_PORT_KEY = "mail.smtp.port";
    public static final String MAIL_DEFAULT_USER_KEY = "mail.smtp.user";
    public static final String MAIL_DEFAULT_USER_PASS_KEY = "mail.smtp.pass";
    public static final String MAIL_DEFAULT_STARTTLS_ENABLE_KEY = "mail.smtp.starttls.enable";

    /* shiro登录状态Key */
    public static final String LOGIN_KEY = "shiroLogin";
    /* 用户登录后信息 */
    public static final String SESSION_LOGIN_KEY = "adminInfo";
    /* 工人登录后信息 */
    public static final String SESSION_WORKER_LOGIN_KEY = "workerInfo";

    /* 日志查询条件存储 */
    public static final String LOG_QUERY = "logQuery";

    /* 操作提示信息 */
    public static final String OPERATION_MESSAGE_KEY = "opsKey";
    public static final String SUCCESS_MESSAGE = "操作成功";
    public static final String FAILURE_MESSAGE = "操作失败";

    /* 所有未关联保修组设备的默认的维修组Id */
    public static final String DEFAULT_GROUP_ID = "0";

    /* 定义各个编号的最小长度 */
    public static final int MIN_NUMBER_LENGTH = 4;

    /* 定义各个编号的前面补充 */
    public static final char PAD_NUMBER_CHAR = '0';

    /* 定义保修单其他选项的默认值*/
    public static final String OTHER_SELECT = "0";

    /*定义其他选项的中文名*/
    public static final String QI_TA ="其他";
    public static final String DEFAULT_WORKER_PASS = "000000";

    /* 报修单已提交状态的前台圆点颜色 */
    public static final String MAINTENANCELIST_COMMIT_STATE_COLOR = "#cc00ff";
    /* 报修单已接单状态的前台圆点颜色 */
    public static final String MAINTENANCELIST_ACCEPT_STATE_COLOR = "#3394ff";
    /* 报修单已完成的前台圆点颜色 */
    public static final String MAINTENANCELIST_DONE_STATE_COLOR = "green";
    /* 报修单信息中包含其他的前台圆点颜色 */
    public static final String MAINTENANCELIST_MODIFY_STATE_COLOR = "#0000FF";
    /* 报修单延期的前台圆点颜色 */
    public static final String MAINTENANCELIST_DELAY_STATE_COLOR = "#fff000";

    /* 添加报修单中有其他选项无法派单的状态 */
    public static final String MAINTENANCELIST_ADD_ADMIN_MODIFY_STATE = "5";
    /* 报修单的状态为正常的添加->已提交 */
    public static final int MAINTENANCELIST_ADD_COMMITTED_STATE = 1;

    /* 报修单状态 */
    public static final int MAINTENANCELIST_STATE_ACCEPT = 2;
    public static final int MAINTENANCELIST_STATE_SUCCESS = 3;
    public static final int MAINTENANCELIST_STATE_DELAY_PROCESS = 6;
}
