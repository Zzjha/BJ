package soexample.umeng.com.month1.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author:author${朱佳华}
 * data:2019/1/15
 */
public class CheckUtils {
    public static boolean check(String telStr){
        Pattern pattern = Pattern.compile("^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$");
        Matcher matcher = pattern.matcher(telStr);
        return matcher.matches();
    }
}
