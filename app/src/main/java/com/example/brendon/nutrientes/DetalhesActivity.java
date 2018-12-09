package com.example.brendon.nutrientes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class DetalhesActivity extends AppCompatActivity {
    TextView nome, detalhes_calorias;
    double percent_proteinas = 0.15;
    double percent_carboidratos = 0.60;
    double percent_gorduras = 0.25;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        nome = findViewById(R.id.nome_detalhe);
        detalhes_calorias = findViewById(R.id.calorias_detalhe);

        Intent intent = getIntent();
        String txt_nome = intent.getStringExtra("nome");
        double calorias =intent.getDoubleExtra("calorias",0.0);
        nome.setText(txt_nome);
        @SuppressLint("DefaultLocale") String res_calorias = String.format("%.1f", calorias);
        detalhes_calorias.setText(res_calorias + " cal");
        calcular_distribuicao(calorias);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public void calcular_distribuicao(double calorias){

        TextView proteina_calorias = findViewById(R.id.proteina_caloria);
        TextView proteina_gramas = findViewById(R.id.proteina_gramas);
        TextView carboidrato_calorias = findViewById(R.id.carboidrato_caloria);
        TextView carboidrato_gramas = findViewById(R.id.carboidrato_grama);
        TextView gordura_calorias = findViewById(R.id.gordura_caloria);
        TextView gordura_gramas = findViewById(R.id.gordura_gramas);

        double valor_Prot_calorias = calorias * percent_proteinas;
        proteina_calorias.setText(String.format("%.1f", valor_Prot_calorias) + " Cal" );
        double valor_Prot_gramas = valor_Prot_calorias  / 4;
        proteina_gramas.setText(String.format("%.1f",valor_Prot_gramas) + "g" );

        double valor_Carbo_calorias = calorias * percent_carboidratos;
        carboidrato_calorias.setText(String.format("%.1f",valor_Carbo_calorias) + " Cal" );
        double valor_Carbo_gramas = valor_Carbo_calorias / 4;
        carboidrato_gramas.setText(String.format("%.1f",valor_Carbo_gramas) + "g" );

        double valor_Gord_calorias = calorias * percent_gorduras;
        gordura_calorias.setText(String.format("%.1f",valor_Gord_calorias) + " Cal" );
        double valor_Gord_gramas = valor_Gord_calorias / 9;
        gordura_gramas.setText(String.format("%.1f",valor_Gord_gramas) + "g" );
    }
}
