package com.example.uu119632.jsontutorial;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        http://webservice.recruit.co.jp/hotpepper/large_area/v1?key=8928fba69a934d6e&format=json
        //URIを生成
        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.scheme("http");
        uriBuilder.authority("webservice.recruit.co.jp");
        uriBuilder.path("hotpepper/large_area/v1");
        uriBuilder.appendQueryParameter("key", "8928fba69a934d6e");
        uriBuilder.appendQueryParameter("format", "json");
//        uriBuilder.appendQueryParameter("name", prefName);

        String utiStr = uriBuilder.toString();

        AsyncTaskJSON task = new AsyncTaskJSON(this);
        task.execute(utiStr);
    }

}