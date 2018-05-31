package com.itfollowme.materialdesigndemo.apis;

import com.itfollowme.materialdesigndemo.model.MeinvResult;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by notre on 2018/5/31.
 */

public interface MeiziApis {
  //http://gank.io/api/data/福利/10/1
  String MEIZI_URL = "http://gank.io/";
  @GET("api/data/福利/{pageSize}/{pageNum}")
  Flowable<MeinvResult> getMeinvPhotos(@Path("pageSize") int pageSize,@Path("pageNum") int pageNum);
}
