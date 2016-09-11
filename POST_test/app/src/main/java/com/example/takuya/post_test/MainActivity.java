package com.example.takuya.post_test;

import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.loopj.android.http.*;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://www.google.com", new AsyncHttpResponseHandler() {

            public void onStart() {
                // called before request is started
            }

            public void onSuccess(int statusCode, PreferenceActivity.Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
            }

            public void onFailure(int statusCode, PreferenceActivity.Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }

            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });








    }
}
