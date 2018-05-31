package com.itfollowme.materialdesigndemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.itfollowme.materialdesigndemo.adapter.MeinvAdapter;
import com.itfollowme.materialdesigndemo.apis.ApiManger;
import com.itfollowme.materialdesigndemo.model.MeinvResult;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class FuliFragment extends Fragment {

  //View  recyclerView
  private RecyclerView mRecyclerView;
  //Controller Adapter
  private MeinvAdapter mMeinvAdapter;
  //Model 数据
  private MeinvResult meinvResult;

  public FuliFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_fuli, container, false);
    mRecyclerView = view.findViewById(R.id.rv_photos);


    init();

    return view;
  }

  private void init() {



    meinvResult = new MeinvResult();
    mMeinvAdapter = new MeinvAdapter(getContext(),meinvResult);


    Flowable<MeinvResult> flowable = ApiManger.getInstance().getMeiziService().getMeinvPhotos(5,1);
    flowable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<MeinvResult>() {
          @Override
          public void accept(MeinvResult meinvResult) throws Exception {
            mMeinvAdapter = new MeinvAdapter(getContext(),meinvResult);
            Log.i("url",meinvResult.getResults().get(0).getUrl());
            mRecyclerView.setAdapter(mMeinvAdapter);
          }
        });
  }

}
