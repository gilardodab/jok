package putra.carel.pendaftaranvaksin

import android.app.AlertDialog
import android.content.ContentValues
import android.content.DialogInterface
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.SimpleCursorAdapter
import kotlinx.android.synthetic.main.activity_daftar.*

class DaftarActivity : AppCompatActivity() {

    var idPeserta : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar)

        var db = DBOpenHelper(this).writableDatabase
        var rs = db.rawQuery("select * from peserta order by nama",null)

        var adapter = SimpleCursorAdapter(this,R.layout.item_data_peserta,rs,
            arrayOf("nama","alamat","nohp","jeniskelamin"),
            intArrayOf(R.id.txtNama, R.id.txtAlamat, R.id.txtNohp, R.id.txtJeniskelamin),0)
        listView.adapter = adapter
        listView.setOnItemClickListener(itemClick)

        btnInsert.setOnClickListener {
            var cv = ContentValues()
            cv.put("nama",edNama.text.toString())
            cv.put("alamat",edAlamat.text.toString())
            cv.put("nohp",edNohp.text.toString())
            cv.put("jeniskelamin",edJenisKelamin.text.toString())
            db.insert("peserta",null,cv)
            rs.requery()

            var ad = AlertDialog.Builder(this)
            ad.setTitle("Tambah Data Peserta??")
            ad.setMessage("Data Peserta Telah Berhasil Bertambah :)")
            ad.setIcon(android.R.drawable.ic_dialog_info)
            ad.setPositiveButton("OK", DialogInterface.OnClickListener{ dialogInterface, i ->
                edNama.setText("")
                edAlamat.setText("")
                edNohp.setText("")
                edJenisKelamin.setText("")
                edNama.requestFocus()
            })
            ad.show()
        }

        btnUpdate.setOnClickListener {
            var cv = ContentValues()
            cv.put("nama",edNama.text.toString())
            cv.put("alamat",edAlamat.text.toString())
            cv.put("nohp",edNohp.text.toString())
            cv.put("jeniskelamin",edJenisKelamin.text.toString())
            db.update("peserta",cv,"_id = ?", arrayOf(rs.getString(0)))
            rs.requery()

            var ad = AlertDialog.Builder(this)
            ad.setTitle("Merubah Data Peserta??")
            ad.setMessage("Data Peserta Telah Berhasil Berubah :)")
            ad.setIcon(android.R.drawable.ic_dialog_info)
            ad.setPositiveButton("OK",DialogInterface.OnClickListener{ dialogInterface, i ->
                if (rs.moveToFirst()){
                    edNama.setText(rs.getString(1))
                    edAlamat.setText(rs.getString(2))
                    edNohp.setText(rs.getString(3))
                    edJenisKelamin.setText(rs.getString(4))
                }
            })
            ad.show()
        }

        btnDelete.setOnClickListener {
            db.delete("peserta","_id = ?", arrayOf(rs.getString(0)))
            rs.requery()

            var ad = AlertDialog.Builder(this)
            ad.setTitle("Menghapus Data Peserta??")
            ad.setMessage("Data Peserta Telah Berhasil Dihapus :)")
            ad.setIcon(android.R.drawable.ic_dialog_info)
            ad.setPositiveButton("OK",DialogInterface.OnClickListener{ dialogInterface, i ->
                if (rs.moveToFirst()){
                    edNama.setText(rs.getString(1))
                    edAlamat.setText(rs.getString(2))
                    edNohp.setText(rs.getString(3))
                    edJenisKelamin.setText(rs.getString(4))
                }
                else{
                    edNama.setText("Data Tidak Ditemukan")
                    edAlamat.setText("Data Tidak Ditemukan")
                    edNohp.setText("Data Tidak Ditemukan")
                    edJenisKelamin.setText("Data Tidak Ditemukan")
                }
            })
            ad.show()
        }

        btnView.setOnClickListener {
            //View semua data
            adapter.notifyDataSetChanged()
            listView.visibility = View.VISIBLE
        }

        btnClear.setOnClickListener {
            edNama.setText("")
            edAlamat.setText("")
            edNohp.setText("")
            edJenisKelamin.setText("")
            edNama.requestFocus()
        }
    }

    val itemClick = AdapterView.OnItemClickListener { parent, view, position, id ->
        val c: Cursor = parent.adapter.getItem(position) as Cursor
        idPeserta = c.getString(c.getColumnIndexOrThrow("_id"))
        edNama.setText(c.getString(c.getColumnIndexOrThrow("nama")))
        edAlamat.setText(c.getString(c.getColumnIndexOrThrow("alamat")))
        edNohp.setText(c.getString(c.getColumnIndexOrThrow("nohp")))
        edJenisKelamin.setText(c.getString(c.getColumnIndexOrThrow("jeniskelamin")))
    }

}