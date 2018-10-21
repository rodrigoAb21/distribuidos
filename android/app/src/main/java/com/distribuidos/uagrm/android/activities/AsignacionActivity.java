package com.distribuidos.uagrm.android.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.distribuidos.uagrm.android.R;
import com.distribuidos.uagrm.android.helpers.TokenManager;
import com.distribuidos.uagrm.android.network.ApiService;
import com.distribuidos.uagrm.android.network.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsignacionActivity extends AppCompatActivity {

    Call<String> call_logout;
    ApiService service;
    TokenManager tokenManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignacion);

        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        if(tokenManager.getToken() == null){
            startActivity(new Intent(AsignacionActivity.this, LoginActivity.class));
            finish();
        }

        service = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_menu_logout:
                logout();
                break;

            case R.id.item_menu_sync:
                sincronizar();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sincronizar(){

    }

    private void logout(){
        call_logout = service.logout();
        call_logout.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                tokenManager.deleteToken();
                startActivity(new Intent(AsignacionActivity.this, LoginActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.w("Log-out FAIL", t.getMessage());
            }
        });
    }
}
