package com.itfollowme.materialdesigndemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.itfollowme.materialdesigndemo.adapter.MeinvAdapter;
import com.itfollowme.materialdesigndemo.model.MeinvResult;
import com.itfollowme.materialdesigndemo.model.MeinvResult.MeinvPhoto;
import com.itfollowme.materialdesigndemo.presenter.IPresenterMeinv;
import com.itfollowme.materialdesigndemo.ui.IMeinvView;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FuliFragment extends Fragment implements IMeinvView{

  private IPresenterMeinv presenterMeinv;
  //View  recyclerView
  private RecyclerView mRecyclerView;
  //Controller Adapter
  private MeinvAdapter mMeinvAdapter;
  //Model 数据
  private List<MeinvPhoto> meinvPhotos;
  private StaggeredGridLayoutManager mLayoutManager;
  public FuliFragment() {
    // Required empty public constructor
  }

  public void setPresenterMeinv(IPresenterMeinv presenterMeinv) {
    this.presenterMeinv = presenterMeinv;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_fuli, container, false);
    mRecyclerView = view.findViewById(R.id.rv_photos);
    meinvPhotos = new ArrayList<>();
    mMeinvAdapter = new MeinvAdapter(getContext(),meinvPhotos);

    int spanCount = 2;
    mLayoutManager = new StaggeredGridLayoutManager(
        spanCount,
        StaggeredGridLayoutManager.VERTICAL);
    mRecyclerView.setAdapter(mMeinvAdapter);
    mLayoutManager.setItemPrefetchEnabled(false);
    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerView.setAdapter(mMeinvAdapter);
    presenterMeinv.loadPhotos();
    return view;
  }


  @Override
  public void showPhotos(MeinvResult meinvResult) {
    List<MeinvPhoto> list = meinvResult.getResults();
    for(int i=0;i<list.size();i++) {
      MeinvPhoto photo = list.get(i);
      meinvPhotos.add(photo);
    }
    mMeinvAdapter.notifyDataSetChanged();
  }
}
