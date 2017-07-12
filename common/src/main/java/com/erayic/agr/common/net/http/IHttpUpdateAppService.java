package com.erayic.agr.common.net.http;

import com.erayic.agr.common.view.update.ProgressResponseBody;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：APP更新下载相关
 */

public interface IHttpUpdateAppService {

    @GET
    Call<ResponseBody> asyncGet(
            @Url String url
    );

    @POST
    Call<ResponseBody> asyncPost(
            @Url String url,
            @QueryMap Map<String, String> maps
    );

    @Streaming
    @GET
    Call<ResponseBody> download(
            @Url String url
    );
}
