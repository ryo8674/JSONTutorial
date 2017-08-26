package com.example.uu119632.jsontutorial;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

/**
 * メイン画面
 *
 * @author :ryo.yamada
 * @since :1.0 :2017/08/25
 */
public class MainActivity extends Activity {

    private static final String HTTP_PROTOCOL = "http";
    private static final String AUTHORITY = "webservice.recruit.co.jp";
    private static final String PATH = "hotpepper/large_area/v1";
    private static final String USER_PARAMETER = "key";
    private static final String USER_KEY = "8928fba69a934d6e";
    private static final String FORMAT_PARAMETER = "format";
    private static final String FORMAT_KEY = "json";

    /**
     * onCreate
     * @param savedInstanceState saveInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //URIを生成
        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.scheme(HTTP_PROTOCOL);
        uriBuilder.authority(AUTHORITY);
        uriBuilder.path(PATH);
        uriBuilder.appendQueryParameter(USER_PARAMETER, USER_KEY);
        uriBuilder.appendQueryParameter(FORMAT_PARAMETER, FORMAT_KEY);

        String utiStr = uriBuilder.toString();

        AsyncTaskJSON task = new AsyncTaskJSON(this);
        task.execute(utiStr);
    }

}