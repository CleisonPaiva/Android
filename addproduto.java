package com.example.venda;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class addproduto extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String FILE_NAME = "Produto.txt";


    EditText mEditText1;
    EditText mEditText2;
    EditText mEditText3;
    EditText mEditText4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduto);

        Spinner spinner = (Spinner) findViewById(R.id.options);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.unidade, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner2 = (Spinner) findViewById(R.id.opc);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.estado, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);

        mEditText1 = findViewById(R.id.editText8);
        mEditText2 = findViewById(R.id.editText9);
        mEditText3 = findViewById(R.id.editText11);
        mEditText4 = findViewById(R.id.editText17);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void voltar_cad(View view) {
        Intent intent = new Intent(this, cadastro.class);
        startActivity(intent);

    }

    public void salva_1(View view) throws JSONException {

        if (mEditText1.getText().toString().equals("")) {
            mEditText1.setError("Este campo é obrigatório");
        }
        if (mEditText2.getText().toString().equals("")) {
            mEditText2.setError("Este campo é obrigatório");
        }
        if (mEditText3.getText().toString().equals("")) {
            mEditText3.setError("Este campo é obrigatório");
        }

        String text1 = mEditText1.getText().toString();
        String text2 = mEditText2.getText().toString();
        String text3 = mEditText3.getText().toString();
        String text4 = mEditText4.getText().toString();


        try {
            JSONObject jsonObject = new JSONObject();
            JSONArray dados = new JSONArray();

            JSONObject obj = new JSONObject();
            obj.put("Cod_Produto", text1);
            obj.put("Produto", text2);
            obj.put("Quantidade", text3);
            obj.put("Preco", text4);


            dados.put(obj);
            jsonObject.put("dados", dados);
            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_APPEND);
            PrintWriter writer = new PrintWriter(fos);
            writer.println(jsonObject.toString());
            writer.flush();
            writer.close();
            fos.close();


           /* Toast.makeText(this, "Salvo em " + getFilesDir() + "/" + FILE_NAME,
                    Toast.LENGTH_LONG).show(); */

            Toast.makeText(this, "Salvo cm Sucesso " ,
                    Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            Log.e("Erro", Objects.requireNonNull(e.getMessage()));


        }
    }


}
