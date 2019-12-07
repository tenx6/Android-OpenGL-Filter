package com.yeliang;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.yeliang.widget.CommonSurfaceView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CommonSurfaceView mSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏，隐藏状态
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        mSurfaceView = findViewById(R.id.surfaceview);
        findViewById(R.id.btn_open_camera).setOnClickListener(this);
        findViewById(R.id.btn_close_camera).setOnClickListener(this);

        requestPermission();
    }

    private void requestPermission() {
        int CameraPermissionResult = PermissionChecker.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (CameraPermissionResult == -1) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1000);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_open_camera:
                mSurfaceView.openCamera();
                break;
            case R.id.btn_close_camera:
                mSurfaceView.closeCamera();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSurfaceView.closeCamera();
    }
}