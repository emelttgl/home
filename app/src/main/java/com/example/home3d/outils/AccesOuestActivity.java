package com.example.home3d.outils;

import static com.example.home3d.outils.ConstructionActivity.BATIMENT_KEY;
import static com.example.home3d.outils.ConstructionActivity.PIECES_KEY;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.home3d.R;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AccesOuestActivity extends AppCompatActivity {
    public static final String Message="message";
    protected ImageView ouest;
    protected SurfaceView surfaceView;
    protected SurfaceHolder surfaceHolder;
    protected DrawRectangle dr;
    protected int x0, y0, x1, y1;
    protected RelativeLayout relativeLayout;
    protected Button valider;
    protected TextView textView;
    protected String Souest;
    protected ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acces);

        ouest = findViewById(R.id.imageViewOuest);
        surfaceView = findViewById(R.id.surfaceViewOuest);
        relativeLayout=findViewById(R.id.relativeL);

        surfaceHolder = surfaceView.getHolder();
        surfaceView.setZOrderOnTop(true);
        surfaceHolder.setFormat(PixelFormat.TRANSPARENT);
        valider=findViewById(R.id.valider);
        textView=findViewById(R.id.textView8);
        Souest="";
        textView.setVisibility(View.INVISIBLE);

        ouest.setOnTouchListener((v, event) -> {
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

            dr = new DrawRectangle(AccesOuestActivity.this, paint, canvas, x0,y0,x1,y1);
            relativeLayout.addView(dr);
            return true;
        });

        /*launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                    this.Souest = (String) result.getData().getSerializableExtra(PIECES_KEY);
                });*/

            valider.setOnClickListener(v -> {
            Bitmap bitmap=((BitmapDrawable) ouest.getDrawable()).getBitmap();
            Souest=bitmapToString(bitmap);
            textView.setText(bitmapToString(bitmap));
            textView.setVisibility(View.INVISIBLE);

            Log.i("message",bitmapToString(bitmap));

            Intent intent = new Intent(this, VisualisationActivity.class);
            intent.putExtra(Message,Souest);

            finish();
        });

            recupImageO();
    }

    private String bitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,50,stream);
        byte[] bytes=stream.toByteArray();
        return Base64.encodeToString(bytes,0);

    }


    public void recupImageO() {
        FileInputStream fis = null;
        try {
            fis = openFileInput("image2.data");
            Bitmap bm = BitmapFactory.decodeStream(fis);
            this.ouest.setImageBitmap(bm);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}