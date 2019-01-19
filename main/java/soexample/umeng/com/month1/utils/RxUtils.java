package soexample.umeng.com.month1.utils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * author:author${朱佳华}
 * data:2019/1/18
 */

public class RxUtils {

    public static RequestBody toRequestBodyOfImage(File pFile) {
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), pFile);
        return fileBody;
    }

}
