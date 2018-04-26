package com.itfollowme.lesson05;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

  //View
  private AutoCompleteTextView mActvTitle;
  private Spinner mSpCats;
  private ListView mLvNumbers;

  //Model
  private List<String> titles;
  private List<Cat> cats;
  private List<Integer> numbers;

  //Controller
  private ArrayAdapter<String> adapter;
  private BaseAdapter catAdapter;
  private ArrayAdapter<Integer> numberAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second);
    //View
    mActvTitle = findViewById(R.id.actv_title);
    mSpCats = findViewById(R.id.sp_cats);
    mLvNumbers = findViewById(R.id.lv_numbers);

    //Model
    titles = Arrays.asList("President","Doctor","Police","Master");

    Cat c1 = new Cat("波斯猫");
    Cat c2 = new Cat("英国短毛猫");
    Cat c3 = new Cat("加菲猫");
    Cat c4 = new Cat("布偶猫");
    Cat c5 = new Cat("暹罗猫");
    cats = Arrays.asList(c1,c2,c3,c4,c5);
    numbers = new ArrayList<>();
    for(int i=10000;i<10100;i++) {
      numbers.add(i);
    }

    //Controller
    adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,titles);
    catAdapter = new BaseAdapter() {
      @Override
      public int getCount() {
        return cats.size();
      }

      @Override
      public Object getItem(int position) {
        return cats.get(position);
      }

      @Override
      public long getItemId(int position) {
        return position;
      }

      @Override
      public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(SecondActivity.this).inflate(R.layout.item_cat,parent,false);
        TextView textView = view.findViewById(R.id.tv_cat_type);
        textView.setText(cats.get(position).getType());
        return view;
      }
    };
    numberAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1,numbers);

    mActvTitle.setAdapter(adapter);
    mSpCats.setAdapter(catAdapter);
    mLvNumbers.setAdapter(numberAdapter);
  }
}
