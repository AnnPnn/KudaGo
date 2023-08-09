package com.anpn.kudago;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        String title = getIntent().getStringExtra("TITLE");
        String date = getIntent().getStringExtra("DATE");
        String description = getIntent().getStringExtra("DESCRIPTION");
        String image = getIntent().getStringExtra("IMAGE");

        TextView tvTitle = findViewById(R.id.tvTitleD);
        TextView tvDate = findViewById(R.id.tvDate);
        TextView tvDescription = findViewById(R.id.tvDescription);
        ImageView im = findViewById(R.id.im);

        tvTitle.setText(title);
        tvDate.setText(date);
        tvDescription.setText(description);

        Picasso.with(this).load(image).into(im);


    }
}




