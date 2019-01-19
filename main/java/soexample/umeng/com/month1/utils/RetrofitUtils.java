package soexample.umeng.com.month1.utils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author:author${朱佳华}
 * data:2019/1/14
 */
public class RetrofitUtils {
    private MyApiService myApiService;
    private OkHttpClient okHttpClient;

    public RetrofitUtils() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Contrats.BASEURL)
                .client(okHttpClient)
                .build();

        myApiService = retrofit.create(MyApiService.class);
    }

    public static RetrofitUtils getInstance(){
        return RetrfitHolder.retrofitUtils;
    }

    private static class RetrfitHolder{
        private static final RetrofitUtils retrofitUtils = new RetrofitUtils();
    }

    public void get(String url, Map<String,String> map, final CallBacks callBacks){
        Observer observer = new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if(callBacks!=null){
                    callBacks.onError(e.getMessage());
                }
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                if(callBacks!=null){
                    try {
                        callBacks.onSuccess(responseBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        myApiService.get(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    public void upLoadImage(String url, Map<String,String> map, MultipartBody.Part file, final CallBacks callBacks){
        Observer observer = new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if(callBacks!=null){
                    callBacks.onError(e.getMessage());
                }
            }

            @Override
            public void onNext(ResponseBody o) {
                if(callBacks!=null){
                    try {
                        callBacks.onSuccess(o.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        myApiService.upLoadImage(url,map,file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    public interface CallBacks{
        void onSuccess(String jsonStr);
        void onError(String jsonStr);
    }
}
