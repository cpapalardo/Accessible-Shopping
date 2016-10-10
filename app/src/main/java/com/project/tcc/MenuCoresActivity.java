package com.project.tcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuCoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cores);
    }

    public void onButtonClick(View v){
        Intent intent = new Intent(this, DescricaoCorActivity.class);
        String nome = v.getTag().toString();

        switch (nome){
            case "AMARELO":
                intent.putExtra("EnumCores", EnumCores.AMARELO);
                break;
            case "AZUL":
                intent.putExtra("EnumCores", EnumCores.AZUL);
                break;
            case "BRANCO":
                intent.putExtra("EnumCores", EnumCores.BRANCO);
                break;
            case "LARANJA":
                intent.putExtra("EnumCores", EnumCores.LARANJA);
                break;
            case "MARROM":
                intent.putExtra("EnumCores", EnumCores.MARROM);
                break;
            case "PRETO":
                intent.putExtra("EnumCores", EnumCores.PRETO);
                break;
            case "ROSA":
                intent.putExtra("EnumCores", EnumCores.ROSA);
                break;
            case "ROXO":
                intent.putExtra("EnumCores", EnumCores.ROXO);
                break;
            case "VERDE":
                intent.putExtra("EnumCores", EnumCores.VERDE);
                break;
            case "VERMELHO":
                intent.putExtra("EnumCores", EnumCores.VERMELHO);
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}
