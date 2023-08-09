package com.anpn.kudago;



import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceholder {
    @GET("public-api/v1.2/events/?expand=dates&location=spb&fields=dates,title,images,description")//конечная точка
    Call<Event> getData();//функция для полчения строки ответа
}
