package soexample.umeng.com.month1.callback;

/**
 * author:author${朱佳华}
 * data:2019/1/14
 */
public interface MyCallBack<T> {
    void setSuccess(T successData);
    void setError(T errorData);
}
