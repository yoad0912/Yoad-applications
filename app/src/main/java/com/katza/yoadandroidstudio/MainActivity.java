package com.katza.yoadandroidstudio;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1;
    Button btn2;
    Button btn3;

    ImageView star;
    ImageView camera;
    Switch sw;

    SeekBar sb;
    EditText guessInput;
    Button btnSend;
    int randomNumber;

    TextView tv;

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

        tv = (TextView) findViewById(R.id.tv);
        registerForContextMenu(tv);
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
        camera = findViewById(R.id.camera);
        camera.setVisibility(View.INVISIBLE);

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
                    randomNumber = (int) (Math.random() * 10) + 1;
                } else {
                    Toast.makeText(MainActivity.this, "❌ טעות! נסה שוב", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sw = findViewById(R.id.sw);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "click switch to show pic", Toast.LENGTH_SHORT).show();
                    camera.setVisibility(View.VISIBLE);
                } else {
                    camera.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this, "click switch to unshow pic", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sb = findViewById(R.id.sb);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float alpha = progress / 100f;
                star.setAlpha(alpha);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn1) {
            Toast.makeText(this, "לחץ על הכפתור השני בשביל למחוק את התמונה", Toast.LENGTH_SHORT).show();
            star.setVisibility(View.VISIBLE);
        } else if (v == btn2) {
            Toast.makeText(this, "לחץ על הכפתור השני בשביל להראות את התמונה", Toast.LENGTH_SHORT).show();
            star.setVisibility(View.INVISIBLE);
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();

        if (id == R.id.action_login) {
            Toast.makeText(this, "You selected login", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(this,DialogActivity.class);
            startActivity(intent);
            finish();
        } else if (R.id.action_register == id) {
            Toast.makeText(this, "You selected register", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(this,DynamicActivity.class);
            startActivity(intent);
            finish();
        } else if (R.id.action_start == id) {
            Toast.makeText(this, "You selected start", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(this,SharedPreferencesActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextmenu_main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.firstline) {
            Toast.makeText(this, "You selected first line", Toast.LENGTH_LONG).show();
            return true;
        } else if (item.getItemId() == R.id.secondline) {
            Toast.makeText(this, "You selected second line", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

}