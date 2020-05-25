package com.example.venda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Random;

public class client_register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String FILE_NAME = "client_register.txt";


    EditText mEditText2;
    EditText mEditText3;

    TextView mytext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_register);

        Random rand = new Random();
        int number = rand.nextInt(999) + 1;
        mytext = (TextView) findViewById(R.id.editcod);
        String mystring = String.valueOf(number);
        mytext.setText(mystring);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        mEditText2 = findViewById(R.id.nam);
        mEditText3 = findViewById(R.id.cpfcnpj);

    }
    public void register(View view) throws JSONException {


        if (mEditText2.getText().toString().equals("")) {
            mEditText2.setError("Este campo é obrigatório");
        }
        if (mEditText3.getText().toString().equals("")) {
            mEditText3.setError("Este campo é obrigatório");
        }

        String text1 = mytext.getText().toString();
        String text2 = mEditText2.getText().toString();
        String text3 = mEditText3.getText().toString();



        try {
            JSONObject jsonObject = new JSONObject();
            JSONArray dados = new JSONArray();

            JSONObject obj = new JSONObject();
            obj.put("Cod_Cliente", text1);
            obj.put("Nome", text2);
            obj.put("CPF_CNPJ", text3);



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

           /* Toast.makeText(this, "Salvo em " + getFilesDir() + "/" + FILE_NAME,
                    Toast.LENGTH_LONG).show();
*/
        } catch (IOException e) {
            Log.e("Erro", Objects.requireNonNull(e.getMessage()));


        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




    public void voltar(View view) {
        Intent intent = new Intent(this, cadastro.class);
        startActivity(intent);
    }
}
