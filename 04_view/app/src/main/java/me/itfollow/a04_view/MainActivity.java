package me.itfollow.a04_view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    private TextView tv_demo;
    private EditText et_input;
    private AutoCompleteTextView auto;


    private static final String[] COUNTRIES = new String[]{
            "Belgium", "France", "Italy", "Germany", "Spain"
    };
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        

        tv_demo = (TextView) findViewById(R.id.tv_demo);
        et_input = (EditText) findViewById(R.id.et_input);
        auto = (AutoCompleteTextView) findViewById(R.id.autotext);

        auto = (AutoCompleteTextView) this.findViewById(R.id.autotext);
        String[] autoStrings = new String[]{"联合国", "联合国安理会", "联合国五个常任理事国",
                "Google", "Google Map"};
        // 第二个参数表示适配器的下拉风格
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, autoStrings);
        auto.setAdapter(adapter);


        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        MultiAutoCompleteTextView textView = (MultiAutoCompleteTextView) findViewById(R.id.edit);
        textView.setAdapter(adapter2);
        textView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public int getResourceId(String name) {
        try {
            // 根据资源的ID的变量名获得Field的对象,使用反射机制来实现的
            Field field = R.drawable.class.getField(name);
            // 取得并返回资源的id的字段(静态变量)的值，使用反射机制
            return Integer.parseInt(field.get(null).toString());
        } catch (Exception e) {
            // TODO: handle exception
        }
        return 0;
    }

    public void change(View view) {
        String html = "图像1<img src='a1'/><p>图像2<img src='a2'/>";
        html += "<a href='http://www.baidu.com'>访问百度</a>";

        CharSequence charSequence = Html.fromHtml(html, new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                Drawable drawable = getResources().getDrawable(getResourceId(source));
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth() / 2,
                        drawable.getIntrinsicHeight() / 2);
                return drawable;
            }
        }, null);

        tv_demo.setText(charSequence);

    }

    public void change2(View view) {
        Drawable drawable = getResources().getDrawable(R.drawable.a1);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        SpannableString spannable = new SpannableString(et_input.getText().toString() + "#");
        ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        spannable.setSpan(span, et_input.getText().toString().length(),
                et_input.getText().toString().length() + "#".length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        et_input.setText(spannable);

    }


    public void change3(View view) {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rg);
        int rbId = radioGroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(rbId);

        Toast.makeText(this, radioButton.getText(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://me.itfollow.a04_view/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://me.itfollow.a04_view/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public void jump2Second(View view) {
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);

    }

    public void jump2Third(View view) {
        Intent intent = new Intent(this,ThirdActivity.class);
        startActivity(intent);

    }

}
