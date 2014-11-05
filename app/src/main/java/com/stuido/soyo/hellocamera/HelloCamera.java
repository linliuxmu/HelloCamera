package com.stuido.soyo.hellocamera;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.IOException;


public class HelloCamera extends Activity {

    private Camera mFrontCamera = null;
    private Camera mBackCamera = null;

    private SurfaceView mFrontCameraView = null;
    private SurfaceView mBackCameraView = null;

    private SurfaceHolder mFrontHolder = null;
    private SurfaceHolder mBackHolder = null;

    private Button mStart = null;
    private Button mStop = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_camera);

        mStart = (Button) findViewById(R.id.startButton);
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                try {
                    mFrontCamera = Camera.open(1);
                    Camera.Parameters mCamParam = mFrontCamera.getParameters();
                    mCamParam.setPreviewSize(640, 480);
                    mFrontCamera.setParameters(mCamParam);
                    mFrontCamera.setPreviewDisplay(mFrontHolder);
                    mFrontCamera.startPreview();
                } catch (IOException e) {
                    e.printStackTrace();
                }

/*
                mBackCamera = Camera.open();
                if (mBackCamera == null)
                    return;

                    Camera.Parameters mCamParam = mBackCamera.getParameters();
                    mCamParam.setPreviewSize(640, 480);
                    mBackCamera.setParameters(mCamParam);
                    mBackCamera.startPreview();
                try {
                    mBackCamera.setPreviewDisplay(mBackHolder);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
        });

        mStop = (Button) findViewById(R.id.stopButton);
        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                if (mFrontCamera != null) {
                    mFrontCamera.stopPreview();
                    mFrontCamera.release();
                }

                if (mBackCamera != null) {
                    mBackCamera.stopPreview();
                    mBackCamera.release();
                }
            }
        });

        mFrontCameraView = (SurfaceView) findViewById(R.id.frontCameraView);
        mFrontCameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                mFrontHolder = surfaceHolder;
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                mFrontHolder = null;
            }
        });

        mBackCameraView = (SurfaceView) findViewById(R.id.frontCameraView);
        mFrontCameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                mBackHolder = surfaceHolder;
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                mBackHolder = null;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hello_camera, menu);
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
}
