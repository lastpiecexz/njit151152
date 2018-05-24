package com.itfollowme.baidufanyi.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Created by notre on 2018/5/24.
 */

public final class ApiManager {
  private static ApiManager apiManager = null;
  private Object lock = new Object();
  static {
    if(apiManager == null) {
      synchronized (ApiManager.class) {
        if(apiManager == null) {
          apiManager = new ApiManager();
        }
      }
    }
  }
  private ApiManager(){};
  public static final ApiManager getInstance(){
    return apiManager;
  }

  private FanyiApis fanyiApis;
  public FanyiApis getFanyiApis(){
    if(fanyiApis == null) {
      synchronized (lock) {
        if(fanyiApis == null){
          Retrofit retrofit = new Retrofit.Builder()
              .baseUrl(FanyiApis.BAIDU_FANYI_URL)
              .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
              .addConverterFactory(FastJsonConverterFactory.create())
              .build();
          fanyiApis = retrofit.create(FanyiApis.class);
        }
      }

    }
    return fanyiApis;
  }
}
