package soexample.umeng.com.month1.model;

import android.util.Log;

import com.google.gson.Gson;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import soexample.umeng.com.month1.callback.MyCallBack;
import soexample.umeng.com.month1.utils.Contrats;
import soexample.umeng.com.month1.utils.RetrofitUtils;

/**
 * author:author${朱佳华}
 * data:2019/1/14
 */
public class MyModel implements IModel {
    @Override
    public void getData(String url, final Class cla, Map<String, String> map, final MyCallBack myCallBack) {
        RetrofitUtils.getInstance().get(url, map, new RetrofitUtils.CallBacks() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson = new Gson();
                Object o = gson.fromJson(jsonStr, cla);
                myCallBack.setSuccess(o);
            }

            @Override
            public void onError(String jsonStr) {
myCallBack.setError(jsonStr);
            }
        });
    }

    @Override
    public void postImage(String url, final Class cla, Map<String, String> map, MultipartBody.Part file, final MyCallBack myCallBack) {
        RetrofitUtils.getInstance().upLoadImage(url, map, file, new RetrofitUtils.CallBacks() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson = new Gson();
                Object o = gson.fromJson(jsonStr, cla);
                myCallBack.setSuccess(o);
            }

            @Override
            public void onError(String jsonStr) {

            }
        });
    }
}
