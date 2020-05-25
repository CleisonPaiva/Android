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

public class MainActivity extends AppCompatActivity {
    private static final String FILE_NAME = "cadastro.txt";
    TextView mEditText;
    TextView mEditText1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = findViewById(R.id.editText);
        mEditText1 = findViewById(R.id.editText2);
    }


    public void proximaTela(View view){

        Intent intent = new Intent(this, Cadastro_user.class);
        startActivity(intent);
    }






    public void entrar(View view) {

        if (mEditText.getText().toString().equals("Digite o Login")) {
            mEditText.setError("Este campo é obrigatório");
        }
        if (mEditText1.getText().toString().equals("")) {
            mEditText1.setError("Este campo é obrigatório");
        }
       /* if (mEditText.getText().toString().equals("")) {
            mEditText.setError("Este campo é obrigatório");
        }*/
        String json;
        String text1 = mEditText.getText().toString();
        String text2 = mEditText1.getText().toString();
        //  FileInputStream fis = null;
        try {
            // fis = openFileInput(FILE_NAME);
            // InputStreamReader is = new InputStreamReader(fis);
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
                if (obj.getString("NOME").equals(text1)&&obj.getString("SENHA").equals(text2)) {
                    // numberlist.add(obj.getString("Num_pedido"));
                    Toast.makeText(this, "Autorizado",
                            Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(this, cadastro.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(this, "Usuario Não Encontrado",
                            Toast.LENGTH_LONG).show();
                }
            }
            //Toast.makeText(getApplicationContext(), numberlist.toString(), Toast.LENGTH_LONG).show();


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }


    }

}
