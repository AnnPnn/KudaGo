package com.anpn.kudago;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import java.util.List;
import java.util.Objects;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {


    public static String BASE_URL = "https://kudago.com/";
    private RecyclerView rcView;
    List<Events> postList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Objects.requireNonNull(getSupportActionBar()).hide();
        rcView = findViewById(R.id.rcView);
        rcView.setHasFixedSize(true);
        rcView.setLayoutManager(new LinearLayoutManager(this));


        //инициализация библиотеки для взаимодействия с веб службой,
        // сборка и создание обьекта
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        JSONPlaceholder jsonPlaceholder = retrofit.create(JSONPlaceholder.class);
        Call<Event> call = jsonPlaceholder.getData();
        call.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(@NonNull Call<Event> call, @NonNull Response<Event> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_LONG).show();
                    return;

                }
                Event post = response.body();
                assert post != null;
                postList = post.getList();
                EventAdapter cityAdapter = new EventAdapter(MainActivity.this, postList, MainActivity.this);
                rcView.setAdapter(cityAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<Event> call, @NonNull Throwable t) {

                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, DescriptionActivity.class);

        intent.putExtra("TITLE", postList.get(position).getTitle());
        intent.putExtra("DESCRIPTION", postList.get(position).getDescription().replaceAll("\\<.*?>", ""));

        String dateStart = null;
        JsonArray jsonArrayDate = postList.get(position).getDates();
        for (int i = 0; i < jsonArrayDate.size(); i++) {
            JsonObject json = jsonArrayDate.get(i).getAsJsonObject();
            dateStart = json.get("start_date").getAsString();
        }
        intent.putExtra("DATE", dateStart);

        JsonArray jsonArray = postList.get(position).getImages();
        String value = null;
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject json = jsonArray.get(i).getAsJsonObject();
            value = json.get("image").getAsString();
        }
        intent.putExtra("IMAGE", value);
        startActivity(intent);
    }


}
