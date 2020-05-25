package com.example.venda;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Random;

public class pagamento extends AppCompatActivity {

    private static final String FILE_NAME = "Cria_Pedido.txt";
    private static final String FILE_NAME_2 = "Pagamento.txt";

    String num, val;
    TextView numped, toter;
    EditText mytext;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        toter = findViewById(R.id.payment);
        Random rand = new Random();
        int number = rand.nextInt(99999) + 11111;
        mytext = (EditText) findViewById(R.id.editText20);
        String mystring = String.valueOf(number);
        mytext.setText(mystring);

        numped = (TextView) findViewById(R.id.nump);
        toter = (TextView) findViewById(R.id.payment);


        String json;

        try {
            InputStream is = openFileInput(FILE_NAME);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, StandardCharsets.UTF_8);

            JSONObject objj = new JSONObject(json);

            JSONArray jsonArray = objj.getJSONArray("dados");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                num = obj.getString("Num_pedido");
                val = obj.getString("Total_Pedido");



                numped.setText("PEDIDO: " + num);
                toter.setText("R$:  " + val);
                //valor.setText("Valor: " + val);


            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

    }

    public void voltar(View view) {
        Intent intent = new Intent(this, exibir_pedido_item.class);
        startActivity(intent);

    }

    public void save(View view) {
        String text1 = numped.getText().toString();

        String text2 = mytext.getText().toString();
        String text3 = toter.getText().toString();

        try {
            JSONObject jsonObject = new JSONObject();
            JSONArray dados = new JSONArray();

            JSONObject obj = new JSONObject();
            obj.put("Nº", text1);
            obj.put("Protocolo", text2);
            obj.put("Valor_payment", text3);


            dados.put(obj);
            jsonObject.put("dados", dados);
            FileOutputStream fos = openFileOutput(FILE_NAME_2, Context.MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(fos);
            writer.println(jsonObject.toString());
            writer.flush();
            writer.close();
            fos.close();


            Toast.makeText(this, "Salvo com Sucesso " ,
                    Toast.LENGTH_LONG).show();

        } catch (IOException | JSONException e) {
            Log.e("Erro", Objects.requireNonNull(e.getMessage()));


        }

    }
}

