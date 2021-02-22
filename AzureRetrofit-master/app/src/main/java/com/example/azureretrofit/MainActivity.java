package com.example.azureretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    DictorsAdapter adapter;
    ArrayList<Dictor> dictors = new ArrayList<>();
    String token;
    ListView listView;

    // Экземпляр библиотеки и интерфейса можно использовать для всех обращений к сервису
    // формируем экземпляр библиотеки
    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create()) // ответ от сервера в виде строки
            .addConverterFactory(GsonConverterFactory.create(new Gson()))
            .baseUrl(AzureAPI.API_URL) // адрес API сервера
            .build();

    AzureAPI api = retrofit.create(AzureAPI.class); // описываем, какие функции реализованы

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);
        Call<String> call = api.getToken(); // создаём объект-вызов
        call.enqueue(new TokenCallback());
        adapter = new DictorsAdapter(this, dictors);
        listView.setAdapter(adapter);
        Button getDictors = findViewById(R.id.button);
        getDictors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ArrayList<Dictor>> dictorCall = api.getDictors("https://eastasia.tts.speech.microsoft.com/cognitiveservices/voices/list", "Bearer " + token);
                dictorCall.enqueue(new DictorsCallback());
            }
        });
    }

    class TokenCallback implements Callback<String> {

        @Override
        public void onResponse(Call<String> call, Response<String> response) {
            if (response.isSuccessful()) {
                token = response.body();
                // TODO: response.body() содержит строку-токен, сохраните его для дальнейшего использования
                Log.d("mytag", "response: " + response.body());
            } else
                Log.d("mytag", "error " + response.code());
        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {
            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            // TODO: выводить Toast в случае ошибки
            Log.d("mytag", "error " + t.getLocalizedMessage());

        }
    }

    class DictorsCallback implements Callback<ArrayList<Dictor>> {

        @Override
        public void onResponse(Call<ArrayList<Dictor>> call, Response<ArrayList<Dictor>> response) {
            if (response.isSuccessful()) {
                dictors.clear();
                dictors.addAll(response.body());
                adapter.notifyDataSetChanged();
                Log.d("mytag", "response: " + response.body());
            } else
                Log.d("mytag", "error " + response.code());
        }

        @Override
        public void onFailure(Call<ArrayList<Dictor>> call, Throwable t) {
            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            // TODO: выводить Toast в случае ошибки
            Log.d("mytag", "error " + t.getLocalizedMessage());

        }
    }
}
