package putra.carel.pendaftaranvaksin

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBOpenHelper(context: Context): SQLiteOpenHelper(context,DB_Name,null,DB_Ver) {
    companion object {
        val DB_Name = "Vaksinasi"
        val DB_Ver = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("create table peserta(_id integer primary key autoincrement, nama text, alamat text, nohp text, jeniskelamin text)")
        db?.execSQL("insert into peserta(nama,alamat,nohp,jeniskelamin)values('Andika Setyo','Jalan Melati 6','08237917373','Laki laki')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }


}