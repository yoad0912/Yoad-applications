package com.katza.yoadandroidstudio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivitySecond extends AppCompatActivity {

    TextView tvResult;
    EditText etYear;
    Button btnClose, btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvResult = findViewById(R.id.tvResult);
        etYear = findViewById(R.id.etYear);
        btnClose = findViewById(R.id.btnClose);
        btnOk = findViewById(R.id.btnOk);

        // נתונים מה-Intent
        String name = getIntent().getStringExtra("name");
        int age = getIntent().getIntExtra("age", 0);
        boolean isMale = getIntent().getBooleanExtra("isMale", false);

        String genderText = isMale ? "זכר" : "לא זכר";

        tvResult.setText(
                "שלום " + name + "\n" +
                        "הגיל שלך הוא " + age + "\n" +
                        "מין: " + genderText
        );

        // סגור
        btnClose.setOnClickListener(v -> finish());

        // OK - חישוב גיל חדש
        btnOk.setOnClickListener(v -> {
            String yearStr = etYear.getText().toString().trim();
            if (yearStr.isEmpty()) {
                Toast.makeText(this, "אנא הכנס שנה", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int birthYear = Integer.parseInt(yearStr);
                int currentAge = 2026 - birthYear;
                if (currentAge < 0 || currentAge > 150) {
                    Toast.makeText(this, "שנה לא תקינה", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent resultIntent = new Intent();
                resultIntent.putExtra("newAge", currentAge);
                setResult(RESULT_OK, resultIntent);
                finish();

            } catch (NumberFormatException e) {
                Toast.makeText(this, "אנא הכנס מספר תקין", Toast.LENGTH_SHORT).show();
            }
        });
    }
}