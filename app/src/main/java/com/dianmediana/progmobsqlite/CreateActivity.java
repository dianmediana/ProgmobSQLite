package com.dianmediana.progmobsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {

    EditText editTextNim, editTextNama, editTextEmail;
    Button buttonSimpan, buttonKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        editTextNim = findViewById(R.id.tambahTextViewNIM);
        editTextNama = findViewById(R.id.tambahTextViewNama);
        editTextEmail = findViewById(R.id.tambahTextViewEmail);
        buttonSimpan = findViewById(R.id.tambahButtonSimpan);
        buttonKembali = findViewById(R.id.buttonKembali);

    }

    //nyimpen data ke database
    public void fungsiSimpan(View view){
        try{
            boolean result = MainActivity.dbHelper.tambahData(
                    Integer.parseInt(editTextNim.getText().toString().trim()),
                    editTextNama.getText().toString().trim(), editTextEmail.getText().toString().trim());
            if (result) {
                Intent intent = new Intent(CreateActivity.this, MainActivity.class);
                startActivity(intent);
                CreateActivity.this.finish();
            }
        }catch (Exception err){
            Toast.makeText(getApplicationContext(), "PleaseEntry All Data", Toast.LENGTH_SHORT).show();
        }
    }

    public void fungsiKembali (View view){
        Intent intent = new Intent(CreateActivity.this, MainActivity.class);
        startActivity(intent);
        CreateActivity.this.finish();
    }
}