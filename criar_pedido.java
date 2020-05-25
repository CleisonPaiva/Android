package com.example.venda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import java.util.Random;

public class criar_pedido extends AppCompatActivity {

    private static final String FILE_NAME = "Cria_Pedido.txt";
    private static final String FILE_NAME_2 = "client_register.txt";


    EditText mEditText1;
    EditText mEditText2;
    EditText mEditText3;
    EditText mEditText4;
    EditText mEditText5;

    EditText mytext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_pedido);

        Random rand = new Random();
        int number = rand.nextInt(99999) + 11111;
        mytext = (EditText) findViewById(R.id.editText16);
        String mystring = String.valueOf(number);
        mytext.setText(mystring);

        mEditText1 = findViewById(R.id.editText10);
        mEditText2 = findViewById(R.id.editText12);
        mEditText3 = findViewById(R.id.editText13);
        mEditText4 = findViewById(R.id.editText14);
        mEditText5 = findViewById(R.id.editText15);


    }



    public void cria_pedido(View view) throws JSONException {
        if (mEditText1.getText().toString().equals("")) {
            mEditText1.setError("Este campo é obrigatório");
        }
        if (mEditText2.getText().toString().equals("")) {
            mEditText2.setError("Este campo é obrigatório");
        }
        if (mEditText3.getText().toString().equals("")) {
            mEditText3.setError("Este campo é obrigatório");
        }
        if (mEditText4.getText().toString().equals("")) {
            mEditText4.setError("Este campo é obrigatório");
        }
        if (mEditText5.getText().toString().equals("")) {
            mEditText5.setError("Este campo é obrigatório");
        }
        String text1 = mEditText1.getText().toString();
        String text2 = mEditText2.getText().toString();
        String text3 = mEditText3.getText().toString();
        String text4 = mEditText4.getText().toString();
        String text5 = mEditText5.getText().toString();
        String text6 = mytext.getText().toString();

        String json;
        String text7 = mEditText2.getText().toString();

        try {

            InputStream is = openFileInput(FILE_NAME_2);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, StandardCharsets.UTF_8);

            JSONObject objj = new JSONObject(json);
            JSONArray jsonArray = objj.getJSONArray("dados");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.getString("Nome").equals(text7)) {
                    // numberlist.add(obj.getString("Num_pedido"));
                    Toast.makeText(this, "Pedido Efetuado",
                            Toast.LENGTH_LONG).show();
                    try {
                        JSONObject jsonObject = new JSONObject();
                        JSONArray dados = new JSONArray();

                        JSONObject obej = new JSONObject();
                        obej.put("Cod_Produto", text1);
                        obej.put("Cliente", text2);
                        obej.put("Vendedor", text3);
                        obej.put("Total_Pedido", text4);
                        obej.put("Data", text5);
                        obej.put("Num_pedido", text6);


                        dados.put(obej);
                        jsonObject.put("dados", dados);
                        FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
                        //  FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_APPEND);
                        PrintWriter writer = new PrintWriter(fos);
                        writer.println(jsonObject.toString());
                        writer.flush();
                        writer.close();
                        fos.close();


                        Toast.makeText(this, "Pedido Efetuado",
                                Toast.LENGTH_LONG).show();

                    } catch (IOException e) {
                        Log.e("Erro", e.getMessage());


                    }
                    Intent intent = new Intent(this, exibir_pedido_item.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(this, " Cliente não encontrado",
                            Toast.LENGTH_LONG).show();
                }
            }
            //Toast.makeText(getApplicationContext(), numberlist.toString(), Toast.LENGTH_LONG).show();


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }


    }


}
