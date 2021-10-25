package com.dianmediana.progmobsqlite;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    protected DBHelper dbHelper;
    Cursor cursor;

    EditText editTextNIM, editTextNama, editTextEmail;
    Button buttonUbah, buttonKembali;
    String nimLama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editTextNIM = findViewById(R.id.editTextViewNIM);
        editTextNama = findViewById(R.id.editTextViewNama);
        editTextEmail = findViewById(R.id.editTextViewEmail);
        buttonKembali = findViewById(R.id.buttonKembali);
        buttonUbah = findViewById(R.id.editButtonUbah);

        //Menampilkan data sebelum diedit
        DBHelper dbHelper = new DBHelper(EditActivity.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nim = '" +
                getIntent().getStringExtra("MainNIM") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            editTextNIM.setText(cursor.getString(0));
            editTextNama.setText(cursor.getString(1));
            editTextEmail.setText(cursor.getString(2));
            nimLama = cursor.getString(0);
        }
    }

    //menyimpan perubahan data
    public void fungsiUbah(View view){
        try {
            boolean result = MainActivity.dbHelper.updateData(
                    nimLama,
                    editTextNIM.getText().toString().trim(),
                    editTextNama.getText().toString().trim(),
                    editTextEmail.getText().toString().trim()
            );
            if (result){
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
                EditActivity.this.finish();
            }
        }catch (Exception err){
            err.printStackTrace();
            Toast.makeText(getApplicationContext(),
                    "Please Fill In Correctly",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void fungsiKembali(View view){
        Intent intent = new Intent(EditActivity.this, MainActivity.class);
        startActivity(intent);
        EditActivity.this.finish();
    }
}