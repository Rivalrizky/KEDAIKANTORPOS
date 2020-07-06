package com.polinema.android.kotlin.kedaikantorpos.admin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.polinema.android.kotlin.kedaikantorpos.R
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.android.synthetic.main.activity_detail_history_admin.*
import kotlinx.android.synthetic.main.activity_detail_transaksi.*
import kotlinx.android.synthetic.main.activity_detail_transaksi_admin.*
import kotlinx.android.synthetic.main.activity_detail_transaksi_admin.id_trans
import kotlinx.android.synthetic.main.activity_detail_transaksi_admin.list_detail_trans
import kotlinx.android.synthetic.main.activity_detail_transaksi_admin.tanggal_trans
import kotlinx.android.synthetic.main.list_detail_trans.view.*
import kotlinx.android.synthetic.main.list_detail_trans.view.jumlah
import kotlinx.android.synthetic.main.list_detail_trans.view.nama_menu
import kotlinx.android.synthetic.main.list_detail_trans_admin.view.*

class DetailHistoryAdmin : AppCompatActivity() {

    lateinit var db : FirebaseFirestore
    var listNotes = ArrayList<Note>()
    lateinit var alFile : ArrayList<Note>


    val F_NAMAMENU = "file_idmenu"
    val F_QUAN = "file_quantity"
    var id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_history_admin)

        alFile = ArrayList()
        db = FirebaseFirestore.getInstance()
        id = intent.extras!!.get("doc").toString()
        Log.e("id", id)

        back_detail_history_admin.setOnClickListener {
            val intent = Intent(this,
                historypenjualan::class.java)
            startActivity(intent)
        }
    }
    override fun onStart() {
        super.onStart()
        showData()

    }
    fun showData(){
        db.collection("keranjang").document(id).get().addOnCompleteListener {
            listNotes.clear()
            id_trans1.text = it.result!!.get("file_idt").toString()
            tanggal_trans1.text = it.result!!.get("file_tanggal").toString()
            historytotal.text = it.result!!.get("file_total").toString()
            val menu = it.result!!.get("file_idmenu") as ArrayList<String>
            val q = it.result!!.get("file_quantity") as ArrayList<String>
            for ((y) in menu.withIndex()) {
                var x = 0
                db.collection("menu").whereEqualTo("file_name", menu[y]).get()
                    .addOnCompleteListener { it2 ->
                        for (r in it2.result!!.documents) {
                            x = q[y].toInt() * r.get("file_harga").toString().toInt()
                            listNotes.add(Note(menu[y],q[y],x.toString()))
                        }
                        list_history_trans.adapter = myAdaper(this, listNotes)
                    }
            }
        }
        var myNotesAdapter = myAdaper (this, listNotes)
        list_history_trans.adapter = myNotesAdapter

    }

    inner class Note (nodeNamaMenu:String , nodeJumlah:String, nodeHarga:String){
        var nodeNamaMenu:String?= nodeNamaMenu
        var nodeJumlah:String? = nodeJumlah
        var nodeHarga:String? = nodeHarga
    }
    inner class myAdaper : BaseAdapter {
        var listNoteAdapter =ArrayList<Note>()
        var context : Context?=null


        constructor(context: Context, listNoteAdapter: ArrayList<Note>) : super() {
            this.listNoteAdapter = listNoteAdapter
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myView = layoutInflater.inflate(R.layout.list_detail_trans_admin, null)
            val myNote = listNoteAdapter[position]

            myView.nama_menu.text = myNote.nodeNamaMenu
            myView.jumlah.text = myNote.nodeJumlah
            myView.hasil.text = myNote.nodeHarga
            return myView
        }

        override fun getItem(position: Int): Any {
            return listNoteAdapter[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listNoteAdapter.size
        }
    }
}
