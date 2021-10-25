package com.dianmediana.progmobsqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    Context mContext;
    public static String DATABASE_NAME = "mahasiswa"; //buat database
    public static int VERSION = 1;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
        mContext = context;
    }

    //fungsi buat tabel mahasiswa
    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            String sql = "create table biodata (nim integer primary key,"+"nama text, email text);";
            db.execSQL(sql);
            Toast.makeText(mContext, "Table Created Succesfull", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.e("error", "Table Creation Error");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //fungsi insert data ke database
    public boolean tambahData(int nim, String nama, String email){
        try{
            String sql = "insert into biodata values ("+nim+", '"+nama+"', '"+email+"');";
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(sql);
            Toast.makeText(mContext, "Add Data Successfull", Toast.LENGTH_SHORT).show();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //menampilkan semua data pada list data
    public Cursor tampilSemuaDataSiswa(){
        try{
            String sql = "select * from biodata";
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            return cursor;
        }catch (Exception e){
            return null;
        }
    }

    //fungsi update data
    public boolean updateData(String nimLama, String nimBaru, String nama, String email){
        try{
            String sql = "update biodata set " +
                    "nim=" +nimBaru+ ", nama = ' "+nama+" ', "+ "email='"+email+"' where nim = "+nimLama+";";
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(sql);
            Toast.makeText(mContext, "Update Data Successful", Toast.LENGTH_SHORT).show();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //fungsi delete data
    public boolean deleteSiswa(String nimLama){
        try {
            String sql = "delete from biodata where nim="+nimLama+";";
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(sql);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
