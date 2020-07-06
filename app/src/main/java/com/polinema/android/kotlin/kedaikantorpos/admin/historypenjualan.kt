package com.polinema.android.kotlin.kedaikantorpos.admin

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.polinema.android.kotlin.kedaikantorpos.MainActivity
import com.polinema.android.kotlin.kedaikantorpos.R
import kotlinx.android.synthetic.main.activity_historypenjualan.*
import kotlinx.android.synthetic.main.activity_pembayaranadmin.*
import java.text.NumberFormat
import java.util.*

class historypenjualan : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historypenjualan)

        alFile = ArrayList()
        uri = Uri.EMPTY

        back_history_penjualan_admin.setOnClickListener {
                val intent = Intent(this,
                    AdminActivity::class.java)
                startActivity(intent)

        }


    }

    override fun onStart() {
        super.onStart()

        storage = FirebaseStorage.getInstance().reference
        db = FirebaseFirestore.getInstance().collection("keranjang")
        db.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            if (firebaseFirestoreException!=null){
                Log.e("firestore :",firebaseFirestoreException.message)
            }

            showData()

            val bulanspin = resources.getStringArray(R.array.Bulan)

            var spb = findViewById<Spinner>(R.id.spBulan)
            if (spb != null) {
                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item, bulanspin
                )
                spb.adapter = adapter

                spb.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View, position: Int, id: Long
                    ) {
                        Toast.makeText(
                            this@historypenjualan,
                            getString(R.string.selected_item) + " " +
                                    "" + bulanspin[position], Toast.LENGTH_SHORT
                        ).show()
                        if  (spb.selectedItem == "ALL"){
                            showData()
                        }
                        else if (spb.selectedItem == "Januari"){
                            showData2()
                        }
                        else if (spb.selectedItem == "Februari"){
                            showData3()
                        }
                        else if (spb.selectedItem == "Maret"){
                            showData4()
                        }
                        else if (spb.selectedItem == "April"){
                            showData5()
                        }
                        else if (spb.selectedItem == "Mei"){
                            showData6()
                        }
                        else if (spb.selectedItem == "Juni"){
                            showData7()
                        }
                        else if (spb.selectedItem == "Juli"){
                            showData8()
                        }
                        else if (spb.selectedItem == "Agustus"){
                            showData9()
                        }
                        else if (spb.selectedItem == "September"){
                            showData10()
                        }
                        else if (spb.selectedItem == "November"){
                            showData11()
                        }
                        else if (spb.selectedItem == "Oktober"){
                            showData12()
                        }
                        else if (spb.selectedItem == "Desember"){
                            showData13()
                        }

                    }
                    override fun onNothingSelected(parent: AdapterView<*>) {
                        // write code to perform some action
                    }
                }
            }
        }


    }
    override fun onClick(v: View?) {
        when(v?.id){
//            R.id.mlebu->{
//                val intent = Intent(this,detail_transaksi::class.java)
//                intent.putExtra("doc", tsTotal.toString())
//                startActivity(intent)
//                Log.e("ada", "doc")
//
//            }

        }
    }
    lateinit var thisParent : MainActivity
    lateinit var v : View
//    var model: communicator? = null

    lateinit var adapter: AdapterDetailHistory
    lateinit var alFile : ArrayList<HashMap<String, Any>>
    lateinit var db : CollectionReference
    lateinit var storage : StorageReference
    var uri = Uri.EMPTY

    val F_HARGA  = "file_harga"
    val F_NAME = "file_name"
    val F_TYPE = "file_type"
    val F_URL = "file_url"
    val F_IDT = "file_idt"
    val F_NAMA = "file_nama"
    val F_TANGGAL = "file_tanggal"
    val F_PEMBAYARAN = "file_status"
    val F_TOTAL = "file_total"
    val F_QUANTITY = "file_quantity"



    fun showData(){
        db.whereEqualTo("file_status" , "Lunas").get().addOnSuccessListener { result ->
            alFile.clear()
            var x = 0
            for (doc in result){
                val hm = HashMap<String,Any>()
                hm.put(F_IDT, doc.get(F_IDT).toString())
                hm.put(F_TANGGAL, doc.get(F_TANGGAL).toString())
                hm.put(F_TOTAL, doc.get(F_TOTAL).toString())
                hm.put(F_PEMBAYARAN, doc.get(F_PEMBAYARAN).toString())
                x += doc.get(F_TOTAL).toString().toInt()
//                hm.put(F_URL, doc.get(F_URL).toString())
                alFile.add(hm)
            }

            txtotalpendapatan.text = "Rp " + x.toString()
            adapter = AdapterDetailHistory(this,alFile)
            ListPenjualan.adapter = adapter
        }
    }
    fun showData2(){
        db.whereEqualTo("file_status" , "Lunas").whereEqualTo("file_bulan", 1).get().addOnSuccessListener { result ->
            alFile.clear()
            var x = 0
            for (doc in result){
                val hm = HashMap<String,Any>()
                hm.put(F_IDT, doc.get(F_IDT).toString())
                hm.put(F_TANGGAL, doc.get(F_TANGGAL).toString())
                hm.put(F_TOTAL, doc.get(F_TOTAL).toString())

                hm.put(F_PEMBAYARAN, doc.get(F_PEMBAYARAN).toString())
                x += doc.get(F_TOTAL).toString().toInt()
//                hm.put(F_URL, doc.get(F_URL).toString())
                alFile.add(hm)
            }
            txtotalpendapatan.text = "Rp " + x.toString()
            adapter = AdapterDetailHistory(this,alFile)
            ListPenjualan.adapter = adapter
        }
    }
    fun showData3(){
        db.whereEqualTo("file_status" , "Lunas").whereEqualTo("file_bulan", 2).get().addOnSuccessListener { result ->
            alFile.clear()
            for (doc in result){
                val hm = HashMap<String,Any>()
                hm.put(F_IDT, doc.get(F_IDT).toString())
                hm.put(F_TANGGAL, doc.get(F_TANGGAL).toString())
                hm.put(F_TOTAL, doc.get(F_TOTAL).toString())

                hm.put(F_PEMBAYARAN, doc.get(F_PEMBAYARAN).toString())
//                hm.put(F_URL, doc.get(F_URL).toString())
                alFile.add(hm)
            }
            adapter = AdapterDetailHistory(this,alFile)
            ListPenjualan.adapter = adapter
        }
    }
    fun showData4(){
        db.whereEqualTo("file_status" , "Lunas").whereEqualTo("file_bulan", 3).get().addOnSuccessListener { result ->
            alFile.clear()
            for (doc in result){
                val hm = HashMap<String,Any>()
                hm.put(F_IDT, doc.get(F_IDT).toString())
                hm.put(F_TANGGAL, doc.get(F_TANGGAL).toString())
                hm.put(F_TOTAL, doc.get(F_TOTAL).toString())

                hm.put(F_PEMBAYARAN, doc.get(F_PEMBAYARAN).toString())
//                hm.put(F_URL, doc.get(F_URL).toString())
                alFile.add(hm)
            }
            adapter = AdapterDetailHistory(this,alFile)
            ListPenjualan.adapter = adapter
        }
    }
    fun showData5(){
        db.whereEqualTo("file_status" , "Lunas").whereEqualTo("file_bulan", 4).get().addOnSuccessListener { result ->
            alFile.clear()
            for (doc in result){
                val hm = HashMap<String,Any>()
                hm.put(F_IDT, doc.get(F_IDT).toString())
                hm.put(F_TANGGAL, doc.get(F_TANGGAL).toString())
                hm.put(F_TOTAL, doc.get(F_TOTAL).toString())

                hm.put(F_PEMBAYARAN, doc.get(F_PEMBAYARAN).toString())
//                hm.put(F_URL, doc.get(F_URL).toString())
                alFile.add(hm)
            }
            adapter = AdapterDetailHistory(this,alFile)
            ListPenjualan.adapter = adapter
        }
    }
    fun showData6(){
        db.whereEqualTo("file_status" , "Lunas").whereEqualTo("file_bulan", 5).get().addOnSuccessListener { result ->
            alFile.clear()
            for (doc in result){
                val hm = HashMap<String,Any>()
                hm.put(F_IDT, doc.get(F_IDT).toString())
                hm.put(F_TANGGAL, doc.get(F_TANGGAL).toString())
                hm.put(F_TOTAL, doc.get(F_TOTAL).toString())

                hm.put(F_PEMBAYARAN, doc.get(F_PEMBAYARAN).toString())
//                hm.put(F_URL, doc.get(F_URL).toString())
                alFile.add(hm)
            }
            adapter = AdapterDetailHistory(this,alFile)
            ListPenjualan.adapter = adapter
        }
    }
    fun showData7(){
        db.whereEqualTo("file_status" , "Lunas").whereEqualTo("file_bulan", 6).get().addOnSuccessListener { result ->
            alFile.clear()
            var x = 0
            for (doc in result){
                val hm = HashMap<String,Any>()
                hm.put(F_IDT, doc.get(F_IDT).toString())
                hm.put(F_TANGGAL, doc.get(F_TANGGAL).toString())
                hm.put(F_TOTAL, doc.get(F_TOTAL).toString())

                hm.put(F_PEMBAYARAN, doc.get(F_PEMBAYARAN).toString())
                x += doc.get(F_TOTAL).toString().toInt()
//                hm.put(F_URL, doc.get(F_URL).toString())
                alFile.add(hm)
            }
            txtotalpendapatan.text = "Rp " + x.toString()
            adapter = AdapterDetailHistory(this,alFile)
            ListPenjualan.adapter = adapter
        }
    }
    fun showData8(){
        db.whereEqualTo("file_status" , "Lunas").whereEqualTo("file_bulan", 7).get().addOnSuccessListener { result ->
            alFile.clear()
            for (doc in result){
                val hm = HashMap<String,Any>()
                hm.put(F_IDT, doc.get(F_IDT).toString())
                hm.put(F_TANGGAL, doc.get(F_TANGGAL).toString())
                hm.put(F_TOTAL, doc.get(F_TOTAL).toString())

                hm.put(F_PEMBAYARAN, doc.get(F_PEMBAYARAN).toString())
//                hm.put(F_URL, doc.get(F_URL).toString())
                alFile.add(hm)
            }
            adapter = AdapterDetailHistory(this,alFile)
            ListPenjualan.adapter = adapter
        }
    }
    fun showData9(){
        db.whereEqualTo("file_status" , "Lunas").whereEqualTo("file_bulan", 8).get().addOnSuccessListener { result ->
            alFile.clear()
            for (doc in result){
                val hm = HashMap<String,Any>()
                hm.put(F_IDT, doc.get(F_IDT).toString())
                hm.put(F_TANGGAL, doc.get(F_TANGGAL).toString())
                hm.put(F_TOTAL, doc.get(F_TOTAL).toString())

                hm.put(F_PEMBAYARAN, doc.get(F_PEMBAYARAN).toString())
//                hm.put(F_URL, doc.get(F_URL).toString())
                alFile.add(hm)
            }
            adapter = AdapterDetailHistory(this,alFile)
            ListPenjualan.adapter = adapter
        }
    }
    fun showData10(){
        db.whereEqualTo("file_status" , "Lunas").whereEqualTo("file_bulan", 9).get().addOnSuccessListener { result ->
            alFile.clear()
            for (doc in result){
                val hm = HashMap<String,Any>()
                hm.put(F_IDT, doc.get(F_IDT).toString())
                hm.put(F_TANGGAL, doc.get(F_TANGGAL).toString())
                hm.put(F_TOTAL, doc.get(F_TOTAL).toString())

                hm.put(F_PEMBAYARAN, doc.get(F_PEMBAYARAN).toString())
//                hm.put(F_URL, doc.get(F_URL).toString())
                alFile.add(hm)
            }
            adapter = AdapterDetailHistory(this,alFile)
            ListPenjualan.adapter = adapter
        }
    }
    fun showData11(){
        db.whereEqualTo("file_status" , "Lunas").whereEqualTo("file_bulan", 10).get().addOnSuccessListener { result ->
            alFile.clear()
            for (doc in result){
                val hm = HashMap<String,Any>()
                hm.put(F_IDT, doc.get(F_IDT).toString())
                hm.put(F_TANGGAL, doc.get(F_TANGGAL).toString())
                hm.put(F_TOTAL, doc.get(F_TOTAL).toString())

                hm.put(F_PEMBAYARAN, doc.get(F_PEMBAYARAN).toString())
//                hm.put(F_URL, doc.get(F_URL).toString())
                alFile.add(hm)
            }
            adapter = AdapterDetailHistory(this,alFile)
            ListPenjualan.adapter = adapter
        }
    }
    fun showData12(){
        db.whereEqualTo("file_status" , "Lunas").whereEqualTo("file_bulan", 11).get().addOnSuccessListener { result ->
            alFile.clear()
            for (doc in result){
                val hm = HashMap<String,Any>()
                hm.put(F_IDT, doc.get(F_IDT).toString())
                hm.put(F_TANGGAL, doc.get(F_TANGGAL).toString())
                hm.put(F_TOTAL, doc.get(F_TOTAL).toString())

                hm.put(F_PEMBAYARAN, doc.get(F_PEMBAYARAN).toString())
//                hm.put(F_URL, doc.get(F_URL).toString())
                alFile.add(hm)
            }
            adapter = AdapterDetailHistory(this,alFile)
            ListPenjualan.adapter = adapter
        }
    }
    fun showData13(){
        db.whereEqualTo("file_status" , "Lunas").whereEqualTo("file_bulan", 12).get().addOnSuccessListener { result ->
            alFile.clear()
            for (doc in result){
                val hm = HashMap<String,Any>()
                hm.put(F_IDT, doc.get(F_IDT).toString())
                hm.put(F_TANGGAL, doc.get(F_TANGGAL).toString())
                hm.put(F_TOTAL, doc.get(F_TOTAL).toString())

                hm.put(F_PEMBAYARAN, doc.get(F_PEMBAYARAN).toString())
//                hm.put(F_URL, doc.get(F_URL).toString())
                alFile.add(hm)
            }
            adapter = AdapterDetailHistory(this,alFile)
            ListPenjualan.adapter = adapter
        }
    }
    //    inner class List(nodeIdt : String,nodeNama: String, nodeTotal: String, nodeTanggal: String){
//        var nodeIdt:String?= nodeIdt
//        var nodeNama:String?= nodeNama
//        var nodeTotal:String = nodeTotal
//        var nodeTanggal : String? = nodeTanggal
//
//    }
    fun rupiah(number: String): String{
        val localeID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number.toDouble()).toString()
    }

//    inner class MyNotesAdapter : BaseAdapter {
//        var listAdapter = ArrayList<List>()
//        var context: Context? = null
//
//        constructor(context: Context, listAdapter: ArrayList<List>) : super() {
//            this.listAdapter = listAdapter
//            this.context = context
//        }
//
//        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//            val e = position + 1
//            var myView = layoutInflater.inflate(R.layout.item_transaksi, null)
//            var myList = listAdapter[position]
//
//            myView.tsNomer.text = e.toString()
//            myView.tsIDT.text = myList.nodeIdt
//            myView.tsNama.text = myList.nodeNama
//            myView.tsTotal.text = rupiah(myList.nodeTotal)
//            myView.tsTanggal.text = myList.nodeTanggal
//
//            return myView
//        }
//
//        override fun getItem(position: Int): Any {
//            return listAdapter[position]
//        }
//
//        override fun getItemId(position: Int): Long {
//            return position.toLong()
//        }
//
//        override fun getCount(): Int {
//            return listAdapter.size
//        }
//    }



//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        var mnuInflater = menuInflater
//        mnuInflater.inflate(R.menu.menu_activity, menu)
//        return super.onCreateOptionsMenu(menu)
//
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item?.itemId){
////            R.id.MTambah -> {
////                val intent = Intent(this,MenuActivity::class.java)
////                startActivity(intent)
////            }
//            R.id.Mpesan -> {
//                val intent = Intent(this,MejaActivity::class.java)
//                startActivity(intent)
//            }
//            R.id.Mtransaksi ->{
//                val intent = Intent(this,TransaksiActivity::class.java)
//                startActivity(intent)
//            }
//            R.id.Mlogout ->{
//                FirebaseAuth.getInstance().signOut()
//                val intent = Intent(this,LoginActivity::class.java)
//                startActivity(intent)
//            }
//        }
//
//        return super.onOptionsItemSelected(item)
//    }
}
