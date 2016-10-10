package com.project.tcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DescricaoCorActivity extends AppCompatActivity {

    TextView textViewColorTitle;
    TextView textViewColorText;
    EnumCores e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_cor);

        textViewColorText = (TextView)findViewById(R.id.textViewColorText);
        textViewColorTitle = (TextView)findViewById(R.id.textViewColorTitle);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b != null){
            e = (EnumCores) b.get("EnumCores");
        }

        switch (e){
            case BRANCO:
                textViewColorTitle.setText(R.string.titulo_branco);
                textViewColorText.setText(R.string.descricao_branco);
                break;
            case PRETO:
                textViewColorTitle.setText(R.string.titulo_preto);
                textViewColorText.setText(R.string.descricao_preto);
                break;
            case AMARELO:
                textViewColorTitle.setText(R.string.titulo_amarelo);
                textViewColorText.setText(R.string.descricao_amarelo);
                break;
            case VERDE:
                textViewColorTitle.setText(R.string.titulo_verde);
                textViewColorText.setText(R.string.descricao_verde);
                break;
            case AZUL:
                textViewColorTitle.setText(R.string.titulo_azul);
                textViewColorText.setText(R.string.descricao_azul);
                break;
            case ROXO:
                textViewColorTitle.setText(R.string.titulo_roxo);
                textViewColorText.setText(R.string.descricao_roxo);
                break;
            case ROSA:
                textViewColorTitle.setText(R.string.titulo_rosa);
                textViewColorText.setText(R.string.descricao_rosa);
                break;
            case MARROM:
                textViewColorTitle.setText(R.string.titulo_marrom);
                textViewColorText.setText(R.string.descricao_marrom);
                break;
            case LARANJA:
                textViewColorTitle.setText(R.string.titulo_laranja);
                textViewColorText.setText(R.string.descricao_laranja);
                break;
            case VERMELHO:
                textViewColorTitle.setText(R.string.titulo_vermelho);
                textViewColorText.setText(R.string.descricao_vermelho);
                break;
            default:
        }
    }
}
