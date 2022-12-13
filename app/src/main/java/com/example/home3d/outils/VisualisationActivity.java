package com.example.home3d.outils;

import static com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.home3d.R;
import com.example.home3d.monde.Piece;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VisualisationActivity extends AppCompatActivity implements SensorEventListener{

    protected ImageButton bouttonGauche, bouttonDroit;
    protected ImageView image;
    protected TextView temp;
    protected double longitude, latitude;
    protected float[] gravity=new float[3];
    protected float[] magneto=new float[3];
    protected float[] orientation=new float[3];
    protected float[] matrix=new float[9];
    protected boolean acc=false;
    protected boolean magn=false;
    protected long time = 0;
    protected float degre = 0f;
    protected SensorManager sensorManager,mSensorManager2;
    protected Sensor accelerometre,magnetometre,mAccelerometer2;
    protected ImageView imageView;
    private double accelerationCurrentValue;
    private double accelerationPreviousValue;
    private float[] floatGravity = new float[3];
    private float[] floatMagnetic = new float[3];
    private float[] floatOrientation = new float[3];
    private float[] floatRotationMatrix = new float[9];
    private int pointsPlotted=5;


    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            accelerationCurrentValue = Math.sqrt(x * x + y * y + z * z);
            double changeInAccelleration = Math.abs(accelerationCurrentValue - accelerationPreviousValue);
            accelerationPreviousValue = accelerationCurrentValue;

            floatGravity = sensorEvent.values;
            mSensorManager2.getRotationMatrix(floatRotationMatrix, null, floatGravity, floatMagnetic);
            mSensorManager2.getOrientation(floatRotationMatrix, floatOrientation);
            imageView.setRotation((float) (-floatOrientation[0] * 180 / 3.14159));
            //update graph
            pointsPlotted++;
            if (pointsPlotted > 1000) {
                pointsPlotted = 1;
            }

            double rotation = -floatOrientation[0] * 180 / 3.14159;
        }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
        SensorEventListener sensorEventListenerMagneticField = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                floatMagnetic = sensorEvent.values;
                mSensorManager2.getRotationMatrix(floatRotationMatrix,null,floatGravity,floatMagnetic);
                mSensorManager2.getOrientation(floatRotationMatrix,floatOrientation);
                imageView.setRotation((float) (-floatOrientation[0]*180/3.14159));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_visualisation);
        bouttonGauche = findViewById(R.id.bouttonG);
        bouttonDroit = findViewById(R.id.bouttonD);
        image = findViewById(R.id.imageView4);
        temp = findViewById(R.id.textView11);
        imageView=findViewById(R.id.imageBoussole);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometre = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometre=sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);


        mSensorManager2 =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer2 = mSensorManager2.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometre=mSensorManager2.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mSensorManager2.registerListener(sensorEventListener,mAccelerometer2,mSensorManager2.SENSOR_DELAY_NORMAL);
        mSensorManager2.registerListener(sensorEventListenerMagneticField,magnetometre,mSensorManager2.SENSOR_DELAY_NORMAL);


       /* pieces = new ArrayList<Piece>();
        launcher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            assert result.getData() != null;
            if (result.getResultCode()== Activity.RESULT_OK)
               textView.setText((String) result.getData().getSerializableExtra(BATIMENT_KEY));
        });

       image.setImageBitmap(stringToBitmap(textView.getText().toString()));
       // textView.setText(message);*/


        bouttonDroit.setOnClickListener(v -> {
            FileInputStream fis = null;
            try {
                fis = openFileInput("image2.data");
                Bitmap bm = BitmapFactory.decodeStream(fis);
                this.image.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        bouttonGauche.setOnClickListener(v -> {
            FileInputStream fis = null;
            try {
                fis = openFileInput("image3.data");
                Bitmap bm = BitmapFactory.decodeStream(fis);
                this.image.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        Runnable runnable = () -> {
            FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            fusedLocationClient.getCurrentLocation(PRIORITY_HIGH_ACCURACY, null).addOnSuccessListener(this, location -> {
                if (location != null) {
                    Log.i("MeteoActivity", "Location différent de null");
                    longitude = location.getLongitude();
                    latitude = location.getLatitude();
                    ExecutorService service = Executors.newSingleThreadExecutor();
                    service.execute(() -> {
                        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&units=metric&appid=9aedc52bd825205c51c7a3297e4d212c";
                        try {
                            InputStream in = new java.net.URL(url).openStream();
                            JSONObject json = readStream(in);
                            JSONObject j = json.getJSONObject("main");
                            temp.setText("" + j.get("temp") + "°C");
                        } catch (IOException | JSONException e) {
                            Log.i("MeteoActivity", "url marche pas");
                            throw new RuntimeException(e);
                        }
                    });

                    runOnUiThread(() -> {
                        Toast.makeText(VisualisationActivity.this, "Longitude : " + longitude + " Latitude : " + latitude, Toast.LENGTH_SHORT).show();
                    });
                }
            });
        };
        ExecutorService service = Executors.newSingleThreadExecutor();
        temp.setOnClickListener(view -> service.execute(runnable));


        String msg = (String) getIntent().getSerializableExtra(AccesOuestActivity.Message);
        //Log.i("MESSAGE", msg.toString());
     //   image.setImageBitmap(stringToBitmap(msg));

         recupImageN();
    }


    private JSONObject readStream(InputStream is) throws IOException, JSONException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is), 1000);
        for (String line = r.readLine(); line != null; line = r.readLine()) {
            sb.append(line);
        }
        is.close();
        return new JSONObject(sb.toString());
    }


    public void recupImageN() {
        FileInputStream fis = null;
        try {
            fis = openFileInput("image1.data");
            Bitmap bm = BitmapFactory.decodeStream(fis);
            this.image.setImageBitmap(bm);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Bitmap stringToBitmap(String picture) {
        try {
            byte[] encodeByte = Base64.decode(picture, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}








