package com.example.brendon.nutrientes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class inicioActivity extends AppCompatActivity {
    Spinner estilo;
    EditText editname;
    TextView txt_altura, txt_peso, txt_tmb, nesc_caloricas, view_peso, view_altura, txt_get;
    Button detalhes;
    double valor_tmb, valor_peso, valor_altura, calorias_total;
    public static final int REQUEST_CODE = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        estilo = findViewById(R.id.estilos);
        editname = findViewById(R.id.nome);
        txt_peso = findViewById(R.id.peso);
        txt_altura = findViewById(R.id.altura);
        txt_tmb = findViewById(R.id.tmb);
        nesc_caloricas = findViewById(R.id.calorias);
        detalhes = findViewById(R.id.detalhes);
        view_altura = findViewById(R.id.texto_altura);
        view_peso = findViewById(R.id.texto_peso);
        txt_get = findViewById(R.id.txt_gasto_energetico);


        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.estilo_vida, R.layout.spinner_item);
        estilo.setAdapter(adapter);
        AdapterView.OnItemSelectedListener escolha = new AdapterView.OnItemSelectedListener() {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item_escolhido = estilo.getSelectedItem().toString();
                calcula_calorias(item_escolhido);
                nesc_caloricas.setText(String.format("%.1f Cal", calorias_total));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        estilo.setOnItemSelectedListener(escolha);
    }

    public void vai_para_dados(View view) {
        String nome = editname.getText().toString();
        Intent intent = new Intent(this, DadosActivity.class);
        intent.putExtra("nome", nome);
        startActivityForResult(intent, REQUEST_CODE);

    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                valor_peso = data.getDoubleExtra("peso", 0.0);
                valor_altura = data.getDoubleExtra("altura", 0.0);
                valor_tmb = data.getDoubleExtra("tmb", 0.0);

                txt_peso.setText(valor_peso + " Kg");
                txt_peso.setVisibility(View.VISIBLE);
                view_peso.setVisibility(View.VISIBLE);

                txt_altura.setText(valor_altura + " m");
                txt_altura.setVisibility(View.VISIBLE);
                view_altura.setVisibility(View.VISIBLE);

                txt_tmb.setText("TMB = " + String.format("%.1f", valor_tmb) + " Cal");
                txt_tmb.setVisibility(View.VISIBLE);
                estilo.setVisibility(View.VISIBLE);
            }
        }
    }

    public void vai_para_detalhes(View view) {
        String nome = editname.getText().toString();
        Intent intent = new Intent(this, DetalhesActivity.class);
        intent.putExtra("nome", nome);
        intent.putExtra("calorias", calorias_total);
        startActivity(intent);
    }

    public void calcula_calorias(String escolha){
        switch (escolha) {
            case "Sedentário":
                calorias_total = valor_tmb * 1.00;
                nesc_caloricas.setVisibility(View.VISIBLE);
                detalhes.setVisibility(View.VISIBLE);
                txt_get.setVisibility(View.VISIBLE);
                break;

            case "Levemente ativo":
                calorias_total = valor_tmb * 1.11;
                nesc_caloricas.setVisibility(View.VISIBLE);
                detalhes.setVisibility(View.VISIBLE);
                txt_get.setVisibility(View.VISIBLE);
                break;

            case "Moderadamente ativo":
                calorias_total = valor_tmb * 1.25;
                nesc_caloricas.setVisibility(View.VISIBLE);
                detalhes.setVisibility(View.VISIBLE);
                txt_get.setVisibility(View.VISIBLE);
                break;

            case "Muito ativo":
                calorias_total = valor_tmb * 1.48;
                nesc_caloricas.setVisibility(View.VISIBLE);
                detalhes.setVisibility(View.VISIBLE);
                txt_get.setVisibility(View.VISIBLE);
                break;

            case "Selecione um estilo de vida:":
                nesc_caloricas.setVisibility(View.INVISIBLE);
                detalhes.setVisibility(View.INVISIBLE);
                txt_get.setVisibility(View.INVISIBLE);
                calorias_total = 0.0;
                break;
        }
    }
}