package com.katza.yoadandroidstudio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SharedPreferencesActivity extends AppCompatActivity {

    SharedPreferences sp;
    Button btnSave;

    EditText etFname, etLname;

    TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shared_preferences);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();

        sp = getSharedPreferences("YoadsSPFile", MODE_PRIVATE);
        String strFname = sp.getString("fname", null);
        String strLname = sp.getString("lname", null);
        if(strFname!= null && strLname != null)
            tvDisplay.setText("welcome" + strFname + " " +  strLname);
    }
    private void initViews(){
        btnSave =findViewById(R.id.btnSubmit);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("fname", etFname.getText().toString());
                editor.putString("lname", etLname.getText().toString());
                editor.commit();
            }
        });
        //old : etFname = (EditText) findViewById(R.id.etFname);
        etFname = findViewById(R.id.etFname);
        etLname = findViewById(R.id.etLname);
        tvDisplay = findViewById(R.id.tvDisplay);
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
