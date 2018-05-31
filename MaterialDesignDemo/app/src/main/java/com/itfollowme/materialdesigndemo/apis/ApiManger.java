package com.itfollowme.materialdesigndemo.apis;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Created by notre on 2018/5/31.
 */

public class ApiManger {
  private static ApiManger apiManger;
  static {
    if(apiManger == null) {
      synchronized (ApiManger.class) {
        if(apiManger == null) {
          apiManger = new ApiManger();
        }
      }
    }
  }
  public static ApiManger getInstance(){
    return apiManger;
  }
  private ApiManger(){}

  private MeiziApis meiziApis;
  public MeiziApis getMeiziService(){
    if (meiziApis == null) {
      synchronized (this) {
        if(meiziApis == null) {

          Retrofit retrofit = new Retrofit.Builder()
              .baseUrl(MeiziApis.MEIZI_URL)
              .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
              .addConverterFactory(FastJsonConverterFactory.create())
              .build();

          meiziApis = retrofit.create(MeiziApis.class);
          return meiziApis;

        }
      }
    }
    return meiziApis;
  }
}
