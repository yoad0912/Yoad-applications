package com.katza.yoadandroidstudio;



import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class ActivityFirst extends AppCompatActivity {

    EditText etName, etAge;
    Switch switchMale;
    Button btnSubmit, btnEditAge;

    private final ActivityResultLauncher<Intent> editAgeLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    int newAge = result.getData().getIntExtra("newAge", 0);
                    etAge.setText(String.valueOf(newAge));
                    Toast.makeText(this, "הגיל עודכן ל-" + newAge, Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // חיבור ל-XML
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        switchMale = findViewById(R.id.switchMale);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnEditAge = findViewById(R.id.btnEditAge);

        // שלח
        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String ageStr = etAge.getText().toString().trim();
            boolean isMale = switchMale.isChecked();

            if (name.isEmpty()) {
                etName.setError("יש להזין שם");
                return;
            }
            if (ageStr.isEmpty()) {
                etAge.setError("יש להזין גיל");
                return;
            }

            int age = Integer.parseInt(ageStr);

            Intent intent = new Intent(ActivityFirst.this, ActivitySecond.class);
            intent.putExtra("name", name);
            intent.putExtra("age", age);
            intent.putExtra("isMale", isMale);
            startActivity(intent);
        });

        // ערוך גיל
        btnEditAge.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String ageStr = etAge.getText().toString().trim();
            boolean isMale = switchMale.isChecked();

            if (name.isEmpty() || ageStr.isEmpty()) {
                Toast.makeText(this, "אנא מלא שם וגיל לפני עריכה", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(ActivityFirst.this, ActivitySecond.class);
            intent.putExtra("name", name);
            intent.putExtra("age", Integer.parseInt(ageStr));
            intent.putExtra("isMale", isMale);
            editAgeLauncher.launch(intent);
        });
    }
}