package com.example.uu119632.jsontutorial;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by uu119632 on 2017/08/24.
 */

public class AsyncTaskJSON extends AsyncTask<String, Void, List<String>> {

    private MainActivity myActivity;
    private static final String REQUEST_METHOD = "GET";
    private static final String CHAR_CODE = "UTF-8";
    private static final String BLANK = "";
    private static final String FAILED_SEARCH = "検索に失敗しました。";
    private static final int REQUEST_SUCCESS_CODE = 200;

    private static final String GRAPH_KEY = "@graph";
    private static final String ITEMS_KEY = "items";
    private static final String TITLE_KEY = "title";


    /**
     * コンストラクタ
     *
     * @param myActivity Activity
     */
    AsyncTaskJSON(MainActivity myActivity) {
        this.myActivity = myActivity;
    }

    /**
     * 非同期で行う処理
     *
     * @param uri uri
     * @return 処理結果
     */
    @Override
    protected List<String> doInBackground(String... uri) {
        List<String> result = null;

        try {
            // connection生成
            HttpURLConnection connection;
            // http経由でアクセス
            URL url = new URL(uri[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(REQUEST_METHOD);

            //リクエスト送信
            connection.connect();

            //レスポンスコードのチェック
            if (connection.getResponseCode() != REQUEST_SUCCESS_CODE) {
                result.add(FAILED_SEARCH);
                return result;
            }

            //結果の取得
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), CHAR_CODE));

            // 取得した結果を文字列に変換
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }

            // 取得した文字列からjsonobjectを作成
            JSONObject jsonObject = new JSONObject(sb.toString());

            // キー配下の配列を取得
            JSONObject resultsJsonObject = jsonObject.getJSONObject("results");
            JSONArray largeAreaJsonArray = resultsJsonObject.getJSONArray("large_area");

            StringBuilder builder = new StringBuilder();
            List<String> list = new ArrayList<>();
            // 配列の内容を順に取得し、titleキーを読み込み
            for (int i = 0; i < largeAreaJsonArray.length(); i++) {
                JSONObject item = largeAreaJsonArray.getJSONObject(i);
                list.add(item.getString("name"));
            }

            return list;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * doInBackgroundの後の処理
     *
     * @param result 非同期処理の結果
     */
    @Override
    protected void onPostExecute(List<String> result) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(myActivity, android.R.layout.simple_list_item_1, result);
        ListView listView = myActivity.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}
