package com.itfollowme.materialdesigndemo.presenter.impl;

import com.itfollowme.materialdesigndemo.apis.ApiManger;
import com.itfollowme.materialdesigndemo.model.MeinvResult;
import com.itfollowme.materialdesigndemo.presenter.IPresenterMeinv;
import com.itfollowme.materialdesigndemo.ui.IMeinvView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by notre on 2018/6/7.
 */

public class MeinvPresenter implements IPresenterMeinv {

  private IMeinvView meinvView;

  public MeinvPresenter(IMeinvView meinvView) {
    this.meinvView = meinvView;
  }

  @Override
  public void loadPhotos() {

    Flowable<MeinvResult> flowable = ApiManger.getInstance().getMeiziService().getMeinvPhotos(5,1);
    flowable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<MeinvResult>() {
          @Override
          public void accept(MeinvResult meinvResult) throws Exception {
            meinvView.showPhotos(meinvResult);
          }
        });
  }
}
