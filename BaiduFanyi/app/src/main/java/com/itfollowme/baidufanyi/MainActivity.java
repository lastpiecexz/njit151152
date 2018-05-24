package com.itfollowme.baidufanyi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.itfollowme.baidufanyi.api.ApiManager;
import com.itfollowme.baidufanyi.api.FanyiApis;
import com.itfollowme.baidufanyi.model.FanyiResponse;
import com.itfollowme.baidufanyi.util.Utils;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

  private EditText mEtSrc;
  private TextView mTvResult;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mEtSrc = findViewById(R.id.et_src);
    mTvResult = findViewById(R.id.tv_result);

  }

  public void translate(View view) {
    /*
    String jsonString = "{\n"
        + "from: \"en\",\n"
        + "to: \"zh\",\n"
        + "trans_result: [\n"
        + "{\n"
        + "src: \"apple\",\n"
        + "dst: \"苹果\"\n"
        + "}\n"
        + "]\n"
        + "}";
    FanyiResponse response = JSON.parseObject(jsonString, FanyiResponse.class);
    Log.i("Fanyi",response.getTransResult().get(0).getDst());
    */
    FanyiApis fanyiApis = ApiManager.getInstance().getFanyiApis();
    //创建发送的参数
    //q=apple&
    // from=en&
    // to=zh&
    // appid=2015063000000001&
    // salt=1435660288&
    // sign=f89f9594663708c1605f3d736d01d2d4
    Map<String,String> map = new HashMap<>();
    String q = mEtSrc.getText().toString();
    String appid = "20180425000150246";
    map.put("q",q);
    map.put("from","en");
    map.put("to","zh");
    map.put("appid",appid);
    String salt = String.valueOf(System.currentTimeMillis());
    Log.i("salt",salt+"");
    //sign是通过q appid salt 密钥共同生成的
    String securityKey = "";
    String sign = appid+q+salt+securityKey;
    sign = Utils.MD5(sign);
    map.put("salt",salt);
    map.put("sign",sign);
    Flowable<FanyiResponse> flowable = fanyiApis.translate(map);
    flowable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<FanyiResponse>() {
          @Override
          public void accept(FanyiResponse fanyiResponse) throws Exception {
            mTvResult.setText(fanyiResponse.getTransResult().get(0).getDst());
          }
        });
  }
}
