package com.example.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kerry on 19/11/13
 */

public class NullQueryMap {

    interface NullClient {
        /**
         * test
         *
         * @param map
         * @return
         */
        @GET("/test")
        Call<ResponseBody> test(@QueryMap(nullAction = QueryMap.NullAction.DELETE_PARAM) Map<String, Object> map);
    }

    public static void main(String[] args) throws Exception {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:60000")
                .build();
        final NullClient nullClient = retrofit.create(NullClient.class);

        Map<String, Object> queryMap = new HashMap<>();

        queryMap.put("name", "kerryzhang");
        queryMap.put("age", 1);
        queryMap.put("sex", null);

        final Call<ResponseBody> test = nullClient.test(queryMap);
        final Response<ResponseBody> execute = test.execute();
        System.out.println(execute.body().string());
    }

}
