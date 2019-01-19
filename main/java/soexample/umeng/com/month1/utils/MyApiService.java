package soexample.umeng.com.month1.utils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * author:author${朱佳华}
 * data:2019/1/14
 */
public interface MyApiService {
    @GET
    Observable<ResponseBody> get(@Url String url, @QueryMap Map<String,String> map);

    @Multipart
    @POST
    Observable<ResponseBody> upLoadImage(@Url String url, @QueryMap Map<String,String> map, @Part MultipartBody.Part file);
}
