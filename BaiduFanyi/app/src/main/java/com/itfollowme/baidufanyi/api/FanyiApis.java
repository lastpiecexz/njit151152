package com.itfollowme.baidufanyi.api;

import com.itfollowme.baidufanyi.model.FanyiResponse;
import io.reactivex.Flowable;
import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by notre on 2018/5/24.
 */

public interface FanyiApis {
  String BAIDU_FANYI_URL = "http://api.fanyi.baidu.com/";
  @GET("/api/trans/vip/translate")
  Flowable<FanyiResponse> translate(@QueryMap Map<String,String> queryMap);
  //?q=apple&from=en&to=zh&appid=2015063000000001&salt=1435660288&sign=f89f9594663708c1605f3d736d01d2d4
}
