package com.example.brendon.nutrientes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DadosActivity extends AppCompatActivity {
    TextView nome;
    private EditText valorPeso;
    private EditText valorAltura;
    Button confirmar;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados);
        nome = findViewById(R.id.mostra_nome);
        valorAltura = findViewById(R.id.valor_altura);
        valorPeso = findViewById(R.id.valor_peso);
        confirmar = findViewById(R.id.enviar_dados);

        Intent intent = getIntent();
        String pacote = intent.getStringExtra("nome");
        nome.setText(pacote);


    }

    public void confirmar(View view) {

        String val_peso = valorPeso.getText().toString();
        String val_altura = valorAltura.getText().toString();

        if (!val_peso.equals("") && !val_altura.equals("")){
            double peso = Double.parseDouble(val_peso);
            double altura = Double.parseDouble(val_altura);
            double tmb = tmb(peso, altura);

            Intent intent = new Intent();
            intent.putExtra("peso", peso);
            intent.putExtra("altura", altura);
            intent.putExtra("tmb", tmb);

            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast t = Toast.makeText(this, "Dados incompletos!! ", Toast.LENGTH_SHORT);
            t.show();
        }
    }

    public double tmb(double peso, double altura){
        return  (11.3 * peso) + (16 * altura) + 901;

    }


}
