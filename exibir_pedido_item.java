package com.example.venda;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class exibir_pedido_item extends AppCompatActivity {
    private static final String FILE_NAME = "Cria_Pedido.txt";


    String  num,cod, cliente, vendedor, total, data;
    TextView numped,codigo, cliente_json, vendedor_json, total_json, data_json;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_pedido_item);

        numped = (TextView) findViewById(R.id.nump);
        cliente_json = (TextView) findViewById(R.id.cliente);
        codigo = (TextView) findViewById(R.id.cod);
        vendedor_json = (TextView) findViewById(R.id.vendedor);
        total_json = (TextView) findViewById(R.id.total);
        data_json = (TextView) findViewById(R.id.data);

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
           //GET informações do JSON
                 num= obj.getString("Num_pedido");
                 cod= obj.getString("Cod_Produto");
                cliente = obj.getString("Cliente");
                vendedor = obj.getString("Vendedor");
                total = obj.getString("Total_Pedido");
                data = obj.getString("Data");
           //SET informações na TEXTVIEW
                numped.setText("Pedido: " + num);
                codigo.setText("Cod Produto: " + cod);
                cliente_json.setText("Cliente: " + cliente);
                vendedor_json.setText("Vendedor: " + vendedor);
                total_json.setText("Total Pedido R$: " + total);
                data_json.setText("Data: " + data);

            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    public void voltar(View view) {
        Intent intent = new Intent(this, cadastro.class);
        startActivity(intent);

    }
    public void finalizar(View view) {
        Intent intent = new Intent(this, pagamento.class);
        startActivity(intent);
    }
    }
