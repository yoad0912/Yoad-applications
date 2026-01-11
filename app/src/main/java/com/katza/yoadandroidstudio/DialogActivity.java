package com.katza.yoadandroidstudio;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sp;
    Dialog d;

    EditText etUserName, etPass;

    Button btnCustomLogin , btnLogin;

    Button btnAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dialog);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { createLoginDialog();}
        });
        sp=getSharedPreferences("details1",0);

        btnAlert= (Button)findViewById(R.id.btnAlert);
        btnAlert.setOnClickListener(this);
    }
    public void createLoginDialog(){
        d = new Dialog(this);
        d.setContentView(R.layout.custom_layout);
        d.setTitle("Login");
        d.setCancelable(true);
        etUserName=(EditText) d.findViewById(R.id.etUserName);
        etPass=(EditText) d.findViewById(R.id.etPassword);
        btnCustomLogin=(Button) d.findViewById(R.id.btnDialogLogin);
        btnCustomLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        d.show();
    }
    @Override
    public void onClick(View v){
        if(v==btnAlert){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select name");
            builder.setMessage("This is a message");
            builder .setCancelable(true);
            builder.setPositiveButton("I agree for the rules", new HandleAlertDialogListener());
            builder.setNegativeButton("No, i dont agree", new HandleAlertDialogListener());
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public class HandleAlertDialogListener implements DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which){
            Toast.makeText(DialogActivity.this, "u selected" + which, Toast.LENGTH_SHORT).show();
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

