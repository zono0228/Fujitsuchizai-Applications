package com.example.takuya.qrcodereader_switch;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback,View.OnClickListener {

    private SurfaceView mSurfaceView;
    private CameraSource mCameraSource;
    TextView textView;
    int CameraID = CameraSource.CAMERA_FACING_FRONT;   //初期カメラ


    BarcodeDetector barcodeDetector;
    CameraSource.Builder cameraSourceBuilder;
    BarcodeProcessorFactory barcodeProcessorFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();  //バーコードリーダー初期化

        mSurfaceView = (SurfaceView)findViewById(R.id.surfaceview); //表示部分設定
        mSurfaceView.getHolder().addCallback(this);

        Button btn =  (Button) findViewById(R.id.button1);  //カメラ切り替え
        btn.setOnClickListener(this);
    }

    public void onClick(View v){  //カメラ切替部
        mCameraSource.stop();  //カメラ関連の停止
        mCameraSource.release();

        mSurfaceView.getHolder().removeCallback(this);  //コールバックの停止


        barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();  //再度バーコード関連の設定
        barcodeProcessorFactory = new BarcodeProcessorFactory();
        barcodeDetector.setProcessor(new MultiProcessor.Builder<>(barcodeProcessorFactory).build());

        cameraSourceBuilder = new CameraSource.Builder(this, barcodeDetector);  //カメラソースの入れ替え
        if(CameraID == CameraSource.CAMERA_FACING_FRONT) {
            cameraSourceBuilder.setFacing(CameraSource.CAMERA_FACING_BACK).setRequestedFps(20f)
                    .setAutoFocusEnabled(true);
            CameraID = CameraSource.CAMERA_FACING_BACK;
        }else{
            cameraSourceBuilder.setFacing(CameraSource.CAMERA_FACING_FRONT).setRequestedFps(20f)
                    .setAutoFocusEnabled(true);
            CameraID = CameraSource.CAMERA_FACING_FRONT;
        }
        mCameraSource = cameraSourceBuilder.build();


        try {  //表示部分に割り当て
            mCameraSource.start(mSurfaceView.getHolder());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroy() {  //アプリ終了時
        super.onDestroy();
        mCameraSource.stop();
        mCameraSource.release();

        mSurfaceView.getHolder().removeCallback(this);
    }

    private void initialize() {  //初期化関連
        barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();
        barcodeProcessorFactory = new BarcodeProcessorFactory();
        barcodeDetector.setProcessor(new MultiProcessor.Builder<>(barcodeProcessorFactory).build());

        cameraSourceBuilder = new CameraSource.Builder(this, barcodeDetector);
        cameraSourceBuilder.setFacing(CameraID).setRequestedFps(20f)
                .setAutoFocusEnabled(true);
        mCameraSource = cameraSourceBuilder.build();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {  //表示部分
        try {
            mCameraSource.start(mSurfaceView.getHolder()); //表示部分に割り当て
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    private class BarcodeProcessorFactory implements MultiProcessor.Factory<Barcode> {

        public BarcodeProcessorFactory() {
            // BarcodeProcessorFactory
        }

        @Override
        public Tracker<Barcode> create(Barcode barcode) {
            return new BarcodeTracker();
        }
    }

    private class BarcodeTracker extends Tracker<Barcode> {
        public BarcodeTracker() {
            // BarcodeTracker
        }

        @Override
        public void onNewItem(final int id, final Barcode item) {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "" + id + " : " + item.rawValue,
                            Toast.LENGTH_LONG).show();  //取得文字の表示
                }
            });
        }
    }
}