package com.itfollowme.materialdesigndemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

/**
 * Created by notre on 2018/5/31.
 */

public class ZhihuViewPagerAdapter extends FragmentPagerAdapter {

  private List<Fragment> fragments;



  public ZhihuViewPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
    super(fm);
    this.fragments = fragments;
  }


  @Override
  public Fragment getItem(int position) {
    return fragments.get(position);
  }

  @Override
  public int getCount() {
    return fragments.size();
  }
}
