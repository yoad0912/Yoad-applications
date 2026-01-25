package com.katza.yoadandroidstudio;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.graphics.Bitmap;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import android.provider.MediaStore;

public class CameraActivity extends AppCompatActivity {
    ImageView iv;
    Button btnTakePic;
    // Launcher לפתיחת המצלמה ולקבלת התוצאה
    ActivityResultLauncher<Intent> cameraLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            // מציגים את התמונה ב-ImageView
                            Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
                            iv.setImageBitmap(bitmap);

                            // מחזירים את התוצאה למסך שממנו הגיעו
                            Intent resultIntent = new Intent();
                            resultIntent.putExtras(result.getData().getExtras());
                            setResult(RESULT_OK, resultIntent);

                            // אם רוצים, סוגרים את המסך אוטומטית:
                            // finish();
                        }
                    }
            );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_camera);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        iv = findViewById(R.id.iv);
        btnTakePic = findViewById(R.id.btnTakePic);
        btnTakePic.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraLauncher.launch(intent); // <-- זה השורה החדשה
        });
    }



}