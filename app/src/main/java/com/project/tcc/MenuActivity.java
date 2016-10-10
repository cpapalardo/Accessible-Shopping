package com.project.tcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button buttonMenuCores;
    Button buttonTermos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        buttonMenuCores = (Button) findViewById(R.id.buttonMenuCores);
        buttonTermos = (Button) findViewById(R.id.buttonTermos);
    }

    private void iniciarActivityMenuCores(View v){
        Intent intent = new Intent(this, MenuCoresActivity.class);
        startActivity(intent);
    }

    private void iniciarActivityTermos(View v){
        Intent intent = new Intent(this, TermosActivity.class);
        startActivity(intent);
    }
}
