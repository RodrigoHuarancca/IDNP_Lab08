package com.example.idnp_lab08;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "university.db";
    private static final int DATABASE_VERSION = 1;
    private static final String USUARIO_TABLE = "USUARIOS";
    public static final String ID_USUARIO = "ID_usuario";
    public static final String USUARIO = "usuario";
    public static final String CONTRASEÑA = "contraseña";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String usuarioTable = "CREATE TABLE " + USUARIO_TABLE + "(" + ID_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " + " TEXT, " + CONTRASEÑA + " TEXT)";
        db.execSQL(usuarioTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addUser(User usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        if (usuario != null) {
            cv.put(USUARIO, usuario.getUsuario());
            cv.put(CONTRASEÑA, usuario.getContraseña());

            long insert = db.insert(USUARIO_TABLE, null, cv);
            if (insert == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    public int checkUsuario(String usuario, String contraseña) {
        SQLiteDatabase db = this.getReadableDatabase();
        String check = "Select " + ID_USUARIO + ", " + USUARIO + ", " + CONTRASEÑA + " from " + USUARIO_TABLE + " where " + USUARIO + "= '" + usuario + "' and " + CONTRASEÑA + "='" + contraseña+ "'";
        Cursor cursor = db.rawQuery(check, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int id = cursor.getInt(0);
            return id;
        }
        else
            return -1;

    }

    public ArrayList<String> listUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> list = new ArrayList<String>();
        String query = "Select * from " + USUARIO_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        //añadirlo a la lista
        if (cursor.moveToFirst()) {
            do {
                int index =cursor.getColumnIndex(USUARIO);
                list.add(cursor.getString(index));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;
    }
}