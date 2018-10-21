package com.distribuidos.uagrm.android.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.distribuidos.uagrm.android.R;

public class AsignacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignacion);
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

    }
}
