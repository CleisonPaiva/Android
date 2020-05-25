package com.example.venda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class cadastro<textView> extends AppCompatActivity {
    private static final String FILE_NAME = "Cria_Pedido.txt";

    TextView mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        mEditText = findViewById(R.id.editText7);

    }

    public void get_json(View view) {

        if (mEditText.getText().toString().equals("")) {
            mEditText.setError("Este campo é obrigatório");
        }
        String json;
        String text1 = mEditText.getText().toString();

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
                if (obj.getString("Num_pedido").equals(text1)) {

                    Toast.makeText(this, "Pedido Encontrado",
                            Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(this, exibir_pedido_item.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(this, "Pedido Não Encontrado",
                            Toast.LENGTH_LONG).show();
                }
            }
            //Toast.makeText(getApplicationContext(), numberlist.toString(), Toast.LENGTH_LONG).show();


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }


    }


    public void criar(View view) {
        Intent intent = new Intent(this, criar_pedido.class);
        startActivity(intent);
    }

    public void adicionar(View view) {
        Intent intent = new Intent(this, addproduto.class);
        startActivity(intent);
    }
    public void client(View view) {
        Intent intent = new Intent(this, client_register.class);
        startActivity(intent);
    }


}

