package com.example.intentscore_yeremia;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE_IMAGE_ONE = 1;
    private static final int GALLERY_REQUEST_CODE_IMAGE_TWO = 2;
    private static final String TAG = MainActivity.class.getCanonicalName();

    public Bitmap bitmap;
    private ImageView imgHome;
    private ImageView imgAway;
    private EditText textAway;
    private EditText textHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgHome = findViewById(R.id.home_logo);
        imgAway = findViewById(R.id.away_logo);

        textHome = findViewById(R.id.home_team);
        textAway = findViewById(R.id.away_team);
    }

    public void handlerNext(View view) {

        String homeText = textHome.getText().toString();
        String awayText = textAway.getText().toString();
        Intent intent = new Intent(this, MatchActivity.class);
        intent.putExtra("home_team", homeText);
        intent.putExtra("away_team", awayText);
        // past image home to next activity
        if(imgHome != null){
            imgHome.buildDrawingCache();
            Bitmap bitmap = imgHome.getDrawingCache();
            intent.putExtra("image_home_bitmap", bitmap);
        }

        if(imgAway != null){
            imgAway.buildDrawingCache();
            Bitmap bitmap = imgAway.getDrawingCache();
            intent.putExtra("image_away_bitmap", bitmap);
        }

        startActivity(intent);
    }

    public void handlerImageHome(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra("img_for", "home");
        startActivityForResult(intent, GALLERY_REQUEST_CODE_IMAGE_ONE);
    }

    public void handlerImageAway(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra("img_for", "away");
        startActivityForResult(intent, GALLERY_REQUEST_CODE_IMAGE_TWO);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            return;
        }

        // Request image dari gallery
        if (resultCode == RESULT_OK) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    // if img home / set image
                    if(requestCode == GALLERY_REQUEST_CODE_IMAGE_ONE){
                        imgHome.setImageBitmap(bitmap);
                    }

                    if(requestCode == GALLERY_REQUEST_CODE_IMAGE_TWO){
                        imgAway.setImageBitmap(bitmap);
                    }
                    // if img away / set image

                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }
}