package com.example.venda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Cadastro_user extends AppCompatActivity {

    private static final String FILE_NAME = "cadastro.txt";


    EditText mEditText1;
    EditText mEditText2;
    EditText mEditText3;
    EditText mEditText4;
    TextView mytext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_user);

        Random rand = new Random();
        int number = rand.nextInt(99) + 100;
        mytext = (TextView) findViewById(R.id.editText3);
        String mystring = String.valueOf(number);
        mytext.setText(mystring);


        mEditText2 = findViewById(R.id.editText4);
        mEditText3 = findViewById(R.id.editText5);
        mEditText4 = findViewById(R.id.editText6);

    }
    public void returning(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }


    public void salve(View view) throws JSONException {

        String text1 = mytext.getText().toString();
        String text2 = mEditText2.getText().toString();
        String text3 = mEditText3.getText().toString();
        String text4 = mEditText4.getText().toString();



        try {
            JSONObject jsonObject = new JSONObject();
            JSONArray dados = new JSONArray();

            JSONObject obj = new JSONObject();
            obj.put("ID", text1);
            obj.put("NOME", text2);
            obj.put("SENHA", text3);
            obj.put("SENHA_2", text4);
            dados.put(obj);
            jsonObject.put("dados", dados);
            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(fos);
            writer.println(jsonObject.toString());
            writer.flush();
            writer.close();
            fos.close();


            Toast.makeText(this, "Salvo com Sucesso",
                    Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            Log.e("Erro", e.getMessage());


        }
    }

}



