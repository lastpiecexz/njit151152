package com.itfollowme.materialdesigndemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.itfollowme.materialdesigndemo.R;
import com.itfollowme.materialdesigndemo.adapter.MeinvAdapter.MeinvViewHolder;
import com.itfollowme.materialdesigndemo.model.MeinvResult.MeinvPhoto;
import java.util.List;

/**
 * Created by notre on 2018/5/31.
 */

public class MeinvAdapter extends RecyclerView.Adapter<MeinvViewHolder> {

  private List<MeinvPhoto> meinvPhotos;
  private Context context;



  public MeinvAdapter(Context context,List<MeinvPhoto> meinvPhotos) {
    this.meinvPhotos = meinvPhotos;
    this.context = context;
  }

  @NonNull
  @Override
  public MeinvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.layout,parent,false);
    return new MeinvViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull MeinvViewHolder holder, int position) {
    String url = meinvPhotos.get(position).getUrl();
    Log.i("meinv","----"+url);
    Glide.with(context).load(url).into(holder.photo);
  }

  @Override
  public int getItemCount() {

    return meinvPhotos.size();
  }

  public static class MeinvViewHolder extends ViewHolder {
    public ImageView photo;
    public MeinvViewHolder(View itemView) {
      super(itemView);
      photo = itemView.findViewById(R.id.iv_photo);
    }
  }


}
