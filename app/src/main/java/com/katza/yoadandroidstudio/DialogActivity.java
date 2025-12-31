package com.katza.yoadandroidstudio;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
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




}