package com.example.orionstark.barberros.controllers.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import com.example.orionstark.barberros.R;
import com.example.orionstark.barberros.models.Barber;

public class BarberInfoActivity extends AppCompatActivity {

    Context context;
    TextView namaBarber;
    ImageView barberImg;
    ImageView mapImg;
    Button btnBook;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        namaBarber = findViewById(R.id.nama_barber);
        setContentView(R.layout.activity_more_info);
        if ( getSupportActionBar() != null ) {
            getSupportActionBar().setElevation(0);
        }
        init();

        Intent intent = getIntent();
        namaBarber.setText(Barber.barbers.get(getIntent().getExtras().getInt("barber_info")).getBarber_name());
        barberImg.setBackground(new BitmapDrawable(Barber.barbers.get(getIntent().getExtras().getInt("barber_img")).getImage()));


        Picasso.with(getBaseContext())
                .load("http://maps.google.com/maps/api/staticmap?center=" + Barber.barbers.get(getIntent().getExtras().getInt("xxx")).getLatitude() + "," + Barber.barbers.get(getIntent().getExtras().getInt("xxx")).getLongitude() + "&zoom=15&size=300x200&sensor=false")
                .into(mapImg);

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getBaseContext(), MakeAppointmentActivity.class));
            }
        });
    }
    public  void init(){
        namaBarber = findViewById(R.id.nama_barber);
        barberImg = findViewById(R.id.barber_img);
        btnBook = findViewById(R.id.btn_book);
        mapImg= findViewById(R.id.mapImage);
    }

}
