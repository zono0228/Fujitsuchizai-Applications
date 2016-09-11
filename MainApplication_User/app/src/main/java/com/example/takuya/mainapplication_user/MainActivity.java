package com.example.takuya.mainapplication_user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.Type;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.*;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;


public class MainActivity extends AppCompatActivity implements OnClickListener{

    private Button btn = null;
    private TextView tv = null;

    String text="Test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.button);
        tv = (TextView)findViewById(R.id.res_text);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://www.google.com", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                System.out.println(response);
            }
        });



        btn.setOnClickListener(this);    }
    @Override
    public void onClick(View v){
        if(v.getId() == R.id.button){
            exec_post();
   /*---処理---*/
        }
    }


    // POST通信を実行（AsyncTaskによる非同期処理を使わないバージョン）
    private void exec_post() {
        tv.setText("ボタンが入力されました。");





/*
        Log.d("posttest", "postします");
        String ret = "";

        // URL
        URI url = null;
        try {
            url = new URI( "http://10.0.2.2/android_post_test.php" );
            Log.d("posttest", "URLはOK");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            ret = e.toString();
        }

        // POSTパラメータ付きでPOSTリクエストを構築
        HttpPost request = new HttpPost( url );
        List<NameValuePair> post_params = new ArrayList<NameValuePair>();
        post_params.add(new BasicNameValuePair("post_1", "ユーザID"));
        post_params.add(new BasicNameValuePair("post_2", "パスワード"));
        try {
            // 送信パラメータのエンコードを指定
            request.setEntity(new UrlEncodedFormEntity(post_params, "UTF-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        // POSTリクエストを実行
        DefaultHttpClient httpClient = new DefaultHttpClient();
        try {
            Log.d("posttest", "POST開始");
            ret = httpClient.execute(request, new ResponseHandler<String>() {

                @Override
                public String handleResponse(HttpResponse response) throws IOException
                {
                    Log.d(
                            "posttest",
                            "レスポンスコード：" + response.getStatusLine().getStatusCode()
                    );

                    // 正常に受信できた場合は200
                    switch (response.getStatusLine().getStatusCode()) {
                        case HttpStatus.SC_OK:
                            Log.d("posttest", "レスポンス取得に成功");

                            // レスポンスデータをエンコード済みの文字列として取得する
                            return EntityUtils.toString(response.getEntity(), "UTF-8");

                        case HttpStatus.SC_NOT_FOUND:
                            Log.d("posttest", "データが存在しない");
                            return null;

                        default:
                            Log.d("posttest", "通信エラー");
                            return null;
                    }

                }

            });

        } catch (IOException e) {
            Log.d("posttest", "通信に失敗：" + e.toString());
        } finally {
            // shutdownすると通信できなくなる
            httpClient.getConnectionManager().shutdown();
        }

        // 受信結果をUIに表示
        tv.setText( ret );
*/
    }




}
