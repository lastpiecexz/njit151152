package com.itfollowme.materialdesigndemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.itfollowme.materialdesigndemo.model.Dog;
import com.itfollowme.materialdesigndemo.model.Person;
import com.itfollowme.materialdesigndemo.presenter.IPresenterMeinv;
import com.itfollowme.materialdesigndemo.presenter.impl.MeinvPresenter;
import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  //标签
  private TabLayout mTabLayout;
  private String[] tabTitle = new String[]{"日报","福利","翻译","天气","摄影"};

  //分页  MVC
  private ViewPager mViewPager;  //V
  private ZhihuViewPagerAdapter zhihuViewPagerAdapter; //C
  private List<Fragment> fragments; //M




  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);



    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    //初始化tabs
    mTabLayout = findViewById(R.id.tab_zhihu_main);
    for(int i = 0;i<tabTitle.length;i++){
      mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[i]));
    }

    //初始化ViewPager
    mViewPager = findViewById(R.id.vp_zhihu_main);
    fragments = new ArrayList<>();
    fragments.add(new RealmFragment());

    FuliFragment fuliFragment = new FuliFragment();
    IPresenterMeinv presenterMeinv = new MeinvPresenter(fuliFragment);
    fuliFragment.setPresenterMeinv(presenterMeinv);

    fragments.add(fuliFragment);
    fragments.add(new ZhihuFragment());
    fragments.add(new ZhihuFragment());
    fragments.add(new ZhihuFragment());
    FragmentManager fm = getSupportFragmentManager();
    zhihuViewPagerAdapter = new ZhihuViewPagerAdapter(fm,fragments);


    mViewPager.setAdapter(zhihuViewPagerAdapter);

    mTabLayout.setupWithViewPager(mViewPager);

    //解决标签上文字不显示的问题
    for(int i = 0;i<tabTitle.length;i++){
      mTabLayout.getTabAt(i).setText(tabTitle[i]);
    }

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
      }
    });

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_camera) {
      // Handle the camera action
    } else if (id == R.id.nav_gallery) {

    } else if (id == R.id.nav_slideshow) {

    } else if (id == R.id.nav_manage) {

    } else if (id == R.id.nav_share) {

    } else if (id == R.id.nav_send) {

    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  public void saveData(View view) {
    // Use them like regular java objects
    Dog dog = new Dog();
    dog.setName("Rex");
    dog.setAge(1);



// Get a Realm instance for this thread
    Realm realm = Realm.getDefaultInstance();

// Query Realm for all dogs younger than 2 years old
    final RealmResults<Dog> puppies = realm.where(Dog.class).lessThan("age", 2).findAll();
    puppies.size(); // => 0 because no dogs have been added to the Realm yet

// Persist your data in a transaction
    realm.beginTransaction();
    final Dog managedDog = realm.copyToRealm(dog); // Persist unmanaged objects
    Person person = realm.createObject(Person.class); // Create managed objects directly
    person.getDogs().add(managedDog);
    realm.commitTransaction();

// Listeners will be notified when data changes
    puppies.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<Dog>>() {
      @Override
      public void onChange(RealmResults<Dog> results, OrderedCollectionChangeSet changeSet) {
        // Query results are updated in real time with fine grained notifications.
        changeSet.getInsertions(); // => [0] is added.
      }
    });

// Asynchronously update objects on a background thread
    realm.executeTransactionAsync(new Realm.Transaction() {
      @Override
      public void execute(Realm bgRealm) {
        Dog dog = bgRealm.where(Dog.class).equalTo("age", 1).findFirst();
        dog.setAge(3);
      }
    }, new Realm.Transaction.OnSuccess() {
      @Override
      public void onSuccess() {
        // Original queries and Realm objects are automatically updated.
        puppies.size(); // => 0 because there are no more puppies younger than 2 years old
        managedDog.getAge();   // => 3 the dogs age is updated
      }
    });
  }
}
