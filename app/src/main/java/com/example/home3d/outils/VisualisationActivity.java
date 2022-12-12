package com.example.home3d.outils;

import static com.example.home3d.outils.ConstructionActivity.BATIMENT_KEY;
import static com.example.home3d.outils.ConstructionActivity.PIECES_KEY;
import static com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.home3d.R;
import com.example.home3d.monde.Piece;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VisualisationActivity extends AppCompatActivity {

    protected ImageButton bouttonGauche, bouttonDroit;
    protected ImageView image;
    protected TextView temp;
    protected double longitude, latitude;
    protected ArrayList<Piece> pieces;
    protected ActivityResultLauncher<Intent> launcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualisation);
        bouttonGauche = findViewById(R.id.bouttonG);
        bouttonDroit = findViewById(R.id.bouttonD);
        image = findViewById(R.id.imageView4);
        temp = findViewById(R.id.textView11);

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


        String msg = getIntent().getStringExtra(AccesOuestActivity.Message);
        image.setImageBitmap(stringToBitmap(msg));

        // recupImageN();
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
}



