package com.distribuidos.uagrm.android;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.distribuidos.uagrm.android.entities.ModeloResponse;
import com.distribuidos.uagrm.android.network.ApiService;
import com.distribuidos.uagrm.android.network.RetrofitBuilder;

public class ModeloActivity extends AppCompatActivity {

    private static final String TAG = "ModeloActivity";

    TextView title;
    Button btn_getModelo;

    ApiService service;
    TokenManager tokenManager;
    Call<ModeloResponse> call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modelo);

        cargar();

        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        if(tokenManager.getToken() == null){
            startActivity(new Intent(ModeloActivity.this, LoginActivity.class));
            finish();
        }

        service = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);

        btn_getModelo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call = service.modelos();
                call.enqueue(new Callback<ModeloResponse>() {
                    @Override
                    public void onResponse(Call<ModeloResponse> call, Response<ModeloResponse> response) {
                        Log.w(TAG, "onResponse: " + response );

                        if(response.isSuccessful()){
                            title.setText(response.body().getData().get(0).getNombre());
                        }else {
                            tokenManager.deleteToken();
                            startActivity(new Intent(ModeloActivity.this, LoginActivity.class));
                            finish();

                        }
                    }

                    @Override
                    public void onFailure(Call<ModeloResponse> call, Throwable t) {
                        Log.w(TAG, "onFailure: " + t.getMessage() );
                    }
                });
            }
        });
    }

    private void cargar(){
        title = (TextView) findViewById(R.id.post_title);
        btn_getModelo = (Button) findViewById(R.id.btn_getModelo);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(call != null){
            call.cancel();
            call = null;
        }
    }
}