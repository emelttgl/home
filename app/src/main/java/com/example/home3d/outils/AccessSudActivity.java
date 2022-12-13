package com.example.home3d.outils;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.home3d.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AccessSudActivity extends AppCompatActivity {

    protected ImageView sud;
    protected SurfaceView surfaceView;
    protected SurfaceHolder surfaceHolder;
    protected DrawRectangle dr;
    protected int x0, y0, x1, y1;
    protected RelativeLayout relativeLayout;
    protected Button valider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acces_sud);

        sud=findViewById(R.id.imageViewSud);
        surfaceView = findViewById(R.id.surfaceViewSud);
        relativeLayout=findViewById(R.id.relativeL);
        valider=findViewById(R.id.valider4);
        surfaceHolder = surfaceView.getHolder();
        surfaceView.setZOrderOnTop(true);
        surfaceHolder.setFormat(PixelFormat.TRANSPARENT);



        sud.setOnTouchListener((v, event) -> {
            relativeLayout.removeView(dr);
            if (event.getPointerCount() == 2) {
                if (event.getActionIndex() == MotionEvent.ACTION_DOWN) {
                    x0 = (int) event.getX(0);
                    y0 = (int) event.getY(0);

                    Log.i("SelectActivity", "x0 : " + x0);
                    Log.i("SelectActivity", "y0 : " + y0);
                }
                x1 = (int) event.getX(1);
                y1 = (int) event.getY(1);
                Log.i("SelectActivity", "x1 : " + x1);
                Log.i("SelectActivity", "y1 : " + y1);

            }
            //Log.i("SelectActivity", "rect " + rect.toString());
            Canvas canvas = new Canvas();
            Paint paint = new Paint();

            dr = new DrawRectangle(AccessSudActivity.this, paint, canvas, x0,y0,x1,y1);
            relativeLayout.addView(dr);
            return true;
        });
        valider.setOnClickListener(v -> {
            finish();


        });

        recupImageS();
    }

    public void recupImageS() {
        FileInputStream fis = null;
        try {
            fis = openFileInput("image3.data");
            Bitmap bm = BitmapFactory.decodeStream(fis);
            this.sud.setImageBitmap(bm);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}