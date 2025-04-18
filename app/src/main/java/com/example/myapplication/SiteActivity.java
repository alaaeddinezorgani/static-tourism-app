package com.example.myapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.Manifest;
import android.widget.Toast;


import com.example.myapplication.databinding.ActivityMainBinding;


public class SiteActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CALL_PERMISSION = 1;
    TextView tv1, tv2;
    Button btn1, btn2, btn3;
    ImageView img;
    String phone, website;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_site);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tv1 = this.findViewById(R.id.big_title);
        tv2 = this.findViewById(R.id.big_description);
        btn1 = this.findViewById(R.id.call_btn);
        btn2 = this.findViewById(R.id.message_btn);
        btn3 = this.findViewById(R.id.website_btn);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        img = this.findViewById(R.id.image_slider);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        int name = intent.getIntExtra("name", -1);
        int description = intent.getIntExtra("description", -1);
        phone = intent.getStringExtra("phone");
        website = intent.getStringExtra("website");
        int imgv = intent.getIntExtra("image", -1);

        tv1.setText(name);
        tv2.setText(description);
        img.setImageResource(imgv);
    }

    @Override
    public void onClick(View v){
        if(v.getId()==R.id.call_btn){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
            } else {
                makePhoneCall();
            }
            Uri r1 = Uri.parse("tel:" + phone);
            Intent i1 = new Intent(Intent.ACTION_CALL, r1);
            startActivity(i1);
        }else if(v.getId()==R.id.message_btn){
            Uri r2 = Uri.parse("sms:" + phone);
            Intent i2 = new Intent(Intent.ACTION_VIEW, r2);
            i2.putExtra("sms_body", "message");
            startActivity(i2);
        }else if(v.getId()==R.id.website_btn){
            Uri websiteUri = Uri.parse(website);
            Intent i3 = new Intent(Intent.ACTION_VIEW, websiteUri);
            startActivity(i3);
        }
    }
    private void makePhoneCall() {
        Uri callUri = Uri.parse("tel:" + phone);
        Intent callIntent = new Intent(Intent.ACTION_CALL, callUri);
        startActivity(callIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Call Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

}