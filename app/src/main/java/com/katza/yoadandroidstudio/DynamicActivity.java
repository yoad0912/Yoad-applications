package com.katza.yoadandroidstudio;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DynamicActivity extends AppCompatActivity {

    LinearLayout linearLayout;

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dynamic);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        linearLayout = findViewById(R.id.main);
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(this);
        LinearLayout.LayoutParams hsParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        horizontalScrollView.setLayoutParams(hsParams);

        LinearLayout llScroll = new LinearLayout(this);
        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llScroll.setLayoutParams(llParams);
        llScroll.setOrientation(LinearLayout.HORIZONTAL);




        for(int i=1;i<=100;i++) {
            imageView = new ImageView(this);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200,200);
            layoutParams.setMargins(5, 5, 5, 5);
            imageView.setLayoutParams(layoutParams);
            int imagekey= getResources().getIdentifier("img" + i%3, "drawable", getPackageName());
            imageView.setImageResource(imagekey);

            llScroll.addView(imageView);

        }
        horizontalScrollView.addView(llScroll);
        linearLayout.addView(horizontalScrollView);


        ScrollView scrollView = new ScrollView(this);
        LinearLayout.LayoutParams svParams= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        scrollView.setLayoutParams(svParams);

        LinearLayout llVertical = new LinearLayout(this);
        llVertical.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams llParamsV = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llVertical.setLayoutParams(llParamsV);



        for(int i=-1;i <=50;i++){
            ImageView imageView1= new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200, 200);
            layoutParams.setMargins(5,5,5,5);
            imageView1.setLayoutParams(layoutParams);
            int imageKey = getResources().getIdentifier("img" + (i%3), "drawable", getPackageName());
            imageView1.setImageResource(imageKey);
            llVertical.addView(imageView1);
        }
        scrollView.addView(llVertical);
        linearLayout.addView(scrollView);
    }
}