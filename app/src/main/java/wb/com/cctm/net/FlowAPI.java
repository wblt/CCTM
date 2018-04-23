package wb.com.cctm.net;

import org.xutils.http.RequestParams;

/**
 * Created by wb on 2018/4/19.
 */

public class FlowAPI {
    // 服务器返回成功标志
    public static final String SUCCEED="1000";
    public static RequestParams getRequestParams(String uri){
        RequestParams requestParams=new RequestParams(uri);
        requestParams.setConnectTimeout(150000);
        return requestParams;
    }
    public static final String TX_FILE_ID = "1251679641";
    public static final String TX_PRES_ID = null;

    public static String SERVER_IP = "http://139.196.225.206:8082";

    // USER - 注册短信验证码
    public static String register_code = SERVER_IP + "/app/user/sysendMess";

    // USER - 用户注册
    public static String register = SERVER_IP + "/app/user/syreg";

    // USER - 用户登录
    public static String login = SERVER_IP + "/app/user/login";

    // USER - 忘记密码短信验证码
    public static String forgotpwd_code = SERVER_IP + "/app/user/sysendMessFG";

    // USER - 忘记密码
    public static String forgotpwd = SERVER_IP + "/app/user/forget";

    // INDEX - 公告
    public static String notice = SERVER_IP + "/app/index/notice";

    // INDEX - 我的好友
    public static String friends = SERVER_IP + "/app/index/friends";

    // INDEX - 记录步数
    public static String dayStep = SERVER_IP + "/app/index/dayStep";

    // INDEX - 邀请好友
    public static String invitation = SERVER_IP + "/app/index/invitation";

    // INDEX - 零钱钱包转入算力钱包
    public static String transferred = SERVER_IP + "/app/index/transferred";

    // INDEX - 首页
    public static String homePage = SERVER_IP + "/app/index/homePage";

}
