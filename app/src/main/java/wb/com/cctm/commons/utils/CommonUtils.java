package wb.com.cctm.commons.utils;

import android.content.Context;
import android.view.View;

import java.text.SimpleDateFormat;

/**
 * Created by jingbin on 2016/11/22.
 * 获取原生资源
 */
public class CommonUtils {
    public static String getCurrentTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(System.currentTimeMillis());
    }
}
