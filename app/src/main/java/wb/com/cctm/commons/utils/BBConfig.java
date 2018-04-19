package wb.com.cctm.commons.utils;

import android.os.Environment;

import org.xutils.http.RequestParams;

/**
 * Created by wb on 2018/4/19.
 */

public class BBConfig {
    //图片本地
    public static String YYW_FILE_PATH = Environment.getExternalStorageDirectory()
            + "/com.wb.cctm/";
    // 服务器返回成功标志
    public static final String SUCCEED="01";
    public static RequestParams getRequestParams(String uri){
        RequestParams requestParams=new RequestParams(uri);
        requestParams.setConnectTimeout(150000);
        return requestParams;
    }

}
