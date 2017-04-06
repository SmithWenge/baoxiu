package nanqu.djtu.utils;

public class ConstantFields {
    /* 分页中每页数据数 */
    public static final int DEFAULT_PAGE_SIZE = 2;
    /* ajax分页中数据的KEY */
    public static final String PAGE_KEY = "page";

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

    /* 日志查询条件存储 */
    public static final String LOG_QUERY = "logQuery";

    /* 操作提示信息 */
    public static final String OPERATION_MESSAGE_KEY = "opsKey";
    public static final String SUCCESS_MESSAGE = "操作成功";
    public static final String FAILURE_MESSAGE = "操作失败";

    /* 所有未关联保修组设备的默认的维修组Id */
    public static final String DEFAULT_GROUP_ID = "0";

    /* 定义各个编号的最小长度 */
    public static final int MIN_NUMBER_LENGTH = 6;

    /* 定义各个编号的前面补充 */
    public static final char PAD_NUMBER_CHAR = '0';
}
