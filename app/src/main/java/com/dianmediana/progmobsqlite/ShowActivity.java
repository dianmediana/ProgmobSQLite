package com.dianmediana.progmobsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    protected Cursor cursor;
    DBHelper dbHelper;
    TextView textViewNIM, textViewNama, textViewEmail;
    Button buttonKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        textViewNIM = findViewById(R.id.lihatTextViewNIM);
        textViewNama = findViewById(R.id.lihatTextViewNama);
        textViewEmail = findViewById(R.id.lihatTextViewEmail);
        buttonKembali = findViewById(R.id.lihatButtonKembali);

        //nampilin data di halaman view
        DBHelper dbHelper = new DBHelper(ShowActivity.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nim = '" +
                getIntent().getStringExtra("MainNIM") + "'", null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            textViewNIM.setText(cursor.getString(0).toString());
            textViewNama.setText(cursor.getString(1).toString());
            textViewEmail.setText(cursor.getString(2).toString());
        }
    }

    public void fungsiKembali(View view){
        Intent intent = new Intent(ShowActivity.this, MainActivity.class);
        startActivity(intent);
        ShowActivity.this.finish();
    }
}