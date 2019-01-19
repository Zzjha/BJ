package soexample.umeng.com.month1.view;

/**
 * author:author${朱佳华}
 * data:2019/1/14
 */
public interface IView<T> {
    void success(T success);
    void error(T error);
}
