package soexample.umeng.com.month1.model;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import soexample.umeng.com.month1.callback.MyCallBack;

/**
 * author:author${朱佳华}
 * data:2019/1/14
 */
public interface IModel {
    void getData(String url, Class cla, Map<String,String> map, MyCallBack myCallBack);
    void postImage(String url, Class cla, Map<String, String> map, MultipartBody.Part file, MyCallBack myCallBack);

}
