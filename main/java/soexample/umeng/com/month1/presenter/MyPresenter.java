package soexample.umeng.com.month1.presenter;

import android.util.Log;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import soexample.umeng.com.month1.callback.MyCallBack;
import soexample.umeng.com.month1.model.MyModel;
import soexample.umeng.com.month1.view.IView;

/**
 * author:author${朱佳华}
 * data:2019/1/14
 */
public class MyPresenter implements IPresenter {
    private MyModel myModel;
    private IView iView;

    public MyPresenter(IView iView) {
        this.iView = iView;
        myModel = new MyModel();
    }

    @Override
    public void getRequest(String url, Class cla, Map<String, String> map) {
        myModel.getData(url, cla, map, new MyCallBack() {
            @Override
            public void setSuccess(Object successData) {
                iView.success(successData);
            }

            @Override
            public void setError(Object errorData) {
                iView.error(errorData);
            }
        });
    }

    @Override
    public void postImage(String url, Class cla, Map<String, String> map, MultipartBody.Part file) {
        myModel.postImage(url, cla, map, file, new MyCallBack() {
            @Override
            public void setSuccess(Object successData) {
                iView.success(successData);
            }

            @Override
            public void setError(Object errorData) {
                iView.error(errorData);
            }
        });
    }
}
