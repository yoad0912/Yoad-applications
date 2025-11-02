package com.katza.yoadandroidstudio;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1;
    Button btn2;

    Button btn3;

     ImageView star;


    EditText guessInput;
    Button btnSend;
    int randomNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();

        // מגריל מספר רנדומלי בין 1 ל-10
        randomNumber = (int) (Math.random() * 10) + 1;
    }

private void initViews() {
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();
            }
        });
        star = findViewById(R.id.star);
        guessInput = findViewById(R.id.guessInput);
        btnSend = findViewById(R.id.btnSend);



        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = guessInput.getText().toString().trim();

                if (input.isEmpty()) {
                    Toast.makeText(MainActivity.this, "הכנס מספר קודם!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int userGuess;
                try {
                    userGuess = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "אנא הזן רק מספרים", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (userGuess == randomNumber) {
                    Toast.makeText(MainActivity.this, "🎉 צדקת! המספר היה " + randomNumber, Toast.LENGTH_LONG).show();
                    // מגרילים מספר חדש לניחוש הבא
                    randomNumber = (int) (Math.random() * 10) + 1;
                } else {
                    Toast.makeText(MainActivity.this, "❌ טעות! נסה שוב", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn1) {
            Toast.makeText(this, "לחץ על הכפתור השני בשביל למחוק את התמונה", Toast.LENGTH_SHORT).show();
            star.setVisibility(View.VISIBLE);
        }
            else if(v==btn2) {
            Toast.makeText(this, "לחץ על הכפתור השני בשביל להראות את התמונה", Toast.LENGTH_SHORT).show();
            star.setVisibility(View.INVISIBLE);
        }
            else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }

    }

}