package com.polinema.android.kotlin.kedaikantorpos

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_menusaw.*
import kotlinx.android.synthetic.main.login_dialog.view.*
import kotlinx.android.synthetic.main.row_menu.view.*
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class menusaw : AppCompatActivity(), View.OnClickListener {

    lateinit var storage : StorageReference
    lateinit var db : CollectionReference
    lateinit var db1 : FirebaseFirestore
    lateinit var adapter: CustomAdapter
    var uri = Uri.EMPTY
    val F_NAME = "file_name"
    val F_HARGA = "file_harga"
    val F_DESKRIPSI = "file_deskripsi"
    val F_TYPE = "file_type"
    val F_URL = "file_url"
    val F_IDT = "file_idt"
    val RC_OK = 100
    var fileType = ""
    var fileName = ""
    val F_TOTAL = "file_total"
    val F_QUANTITY = "file_quantity"
    val F_TANGGAL = "file_tanggal"
    val F_STATUS = "file_status"
    val F_IDMENU = "file_idmenu"

    var tahun = 0
    var bulan = 0
    var hari = 0
    val cal: Calendar = Calendar.getInstance()
    var quantity= 0
    lateinit var alFile : ArrayList<Note>
    var listNotes = ArrayList<Note>()


    var arrQ = ArrayList<String>()
    var arrH = ArrayList<String>()
    var arrI = ArrayList<String>()



    lateinit var auth: FirebaseAuth
    override fun onClick(v: View?) {

        when(v?.id){

        }
    }
    private fun getUserProfile() {
        // [START get_user_profile]
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl

//            pelanggan1.text=email
            // Check if user's email is verified
            val emailVerified = user.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            val uid = user.uid
        }
        // [END get_user_profile]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menusaw)

        alFile = ArrayList()
        uri = Uri.EMPTY

        db1 = FirebaseFirestore.getInstance()

        auth = FirebaseAuth.getInstance()

        pelanggan1.setOnClickListener{
            val currentUser = auth.currentUser
            if (currentUser != null) {
                val intent = Intent(this,HomePelanggan::class.java)
                startActivity(intent)
            }else{
                val mDialogView = LayoutInflater.from(this).inflate(R.layout.login_dialog, null)
                //AlertDialogBuilder
                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                    .setTitle("Apakah Kamu Ingin Login ??")
                //show dialog
                val  mAlertDialog = mBuilder.show()
                //login button click of custom layout
                mDialogView.dialogLoginBtn.setOnClickListener {
                    //dismiss dialog
                    mAlertDialog.dismiss()
                    val currentUser = auth.currentUser
                    if (currentUser != null) {
                        val intent = Intent(this,HomePelanggan::class.java)
                        startActivity(intent)
                    }else{
                        val intent = Intent(this,LoginActivity::class.java)
                        startActivity(intent)
                    }
                    //get text from EditTexts of custom layout

                    //set the input text in TextView
//            mainInfoTv.setText("Name:"+ name +"\nEmail: "+ email +"\nPassword: "+ password)
                }
                //cancel button click of custom layout
                mDialogView.dialogCancelBtn.setOnClickListener {
                    //dismiss dialog
                    mAlertDialog.dismiss()
                }
            }
        }

        belanja1.setOnClickListener{
            val currentUser = auth.currentUser
            if (currentUser != null) {
                updateUI()
            }else{
                val mDialogView = LayoutInflater.from(this).inflate(R.layout.login_dialog, null)
                //AlertDialogBuilder
                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                    .setTitle("Apakah Kamu Ingin Login ??")
                //show dialog
                val  mAlertDialog = mBuilder.show()
                //login button click of custom layout
                mDialogView.dialogLoginBtn.setOnClickListener {
                    //dismiss dialog
                    mAlertDialog.dismiss()
                    val currentUser = auth.currentUser
                    if (currentUser != null) {
                        updateUI()
                    }else{
                        val intent = Intent(this,LoginActivity::class.java)
                        startActivity(intent)
                    }
                    //get text from EditTexts of custom layout

                    //set the input text in TextView
//            mainInfoTv.setText("Name:"+ name +"\nEmail: "+ email +"\nPassword: "+ password)
                }
                //cancel button click of custom layout
                mDialogView.dialogCancelBtn.setOnClickListener {
                    //dismiss dialog
                    mAlertDialog.dismiss()
                }
            }

        }
    }
    val itemClick = AdapterView.OnItemClickListener { parent, view, position, id ->

        val currentUser = auth.currentUser
        if (currentUser != null) {
            updateUI()
//            getCount()
        }else{
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.login_dialog, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("Apakah Kamu Ingin Login ??")
            //show dialog
            val  mAlertDialog = mBuilder.show()
            //login button click of custom layout
            mDialogView.dialogLoginBtn.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
                val currentUser = auth.currentUser
                if (currentUser != null) {
//                    getCount
                    updateUI()
                }else{
                    val intent = Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                }
                //get text from EditTexts of custom layout

                //set the input text in TextView
//            mainInfoTv.setText("Name:"+ name +"\nEmail: "+ email +"\nPassword: "+ password)
            }
            //cancel button click of custom layout
            mDialogView.dialogCancelBtn.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
            }
        }

    }

    private fun updateUI() {
        val currentUser = auth.currentUser!!.email
        db = FirebaseFirestore.getInstance().collection("keranjang")
        db.whereEqualTo("file_name", currentUser).get()
            .addOnSuccessListener {


                var trx = SimpleDateFormat("yyyyMMddHHmmssSSS").format(Date())
                bulan =cal.get(Calendar.MONTH) + 1
                hari = cal.get(Calendar.DAY_OF_MONTH)
                tahun = cal.get(Calendar.YEAR)

                var tanggal = "$bulan - $hari - $tahun"
                var st = "Hutang"

                storage = FirebaseStorage.getInstance().reference
                db = FirebaseFirestore.getInstance().collection("keranjang")

                val hm = HashMap<String, Any>()
                hm.set(F_IDT, trx)
                hm.set(F_QUANTITY, arrQ)
                hm.set(F_IDMENU, arrI)
                hm.set(F_NAME, pelanggan1.text.toString())
                hm.set(F_TOTAL, txtotal1.text.toString())
                hm.set(F_STATUS, st)
                hm.set("file_bulan",bulan)
                hm.set(F_TANGGAL, tanggal)
                db.document(trx).set(hm).addOnSuccessListener {
                    Toast.makeText(this, "Data Berhasil Di Tambahkan", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this,TransaksiActivity::class.java)
                    startActivity(intent)
                }.addOnFailureListener { e ->
                    Toast.makeText(this, "Data Gagal Di Tambahkan : ${e.message}", Toast.LENGTH_SHORT).show()
                }

            }
    }
    override fun onStart() {
        super.onStart()
        getUserProfile()
        total()
        storage = FirebaseStorage.getInstance().reference

        db = FirebaseFirestore.getInstance().collection("menu")
        val db2 = db1.collection("menu").orderBy("file_name").limit(5)
        db2.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            if (firebaseFirestoreException!=null){
                Log.e("firestore :",firebaseFirestoreException.message)
            }
            showData()
        }
    }
    fun total() {
        var total = 0
        for (i in 0..arrH.count() -1) {
            total = total + arrH.get(i).toInt() * arrQ.get(i).toInt()
        }
        txtotal1.text = DecimalFormat("#.###").format(total)
    }
    fun showData(){
        val bundle = intent.extras
        val menu = bundle!!.getStringArrayList("xx") as ArrayList<String>
        Log.e("coba1", menu.toString())
        arrH.clear()
        arrI.clear()
        arrQ.clear()
        listNotes.clear()
//        val ar = bundle!!.getStringArray("xx")
        db1.collection("menu").get().addOnCompleteListener {
            alFile.clear()
            for (doc in it.result!!){
                menu!!.map {
                    if (doc[F_NAME].toString() == it) {
                        val menu = doc.get(F_NAME).toString()
                        val harga = doc.get(F_HARGA).toString()
                        val deskripsi = doc.get(F_DESKRIPSI).toString()
                        val url = doc.get(F_URL).toString()

                        alFile.add(Note(harga, menu, deskripsi, url,quantity))
                        arrQ.add(quantity.toString())
                        arrH.add(harga)
                        arrI.add(menu)
                    }
                }
            }
            listMenuSaw.adapter = myAdaper(this, alFile)
        }

        var myNotesAdapter = myAdaper (this, listNotes)
        listMenuSaw.adapter = myNotesAdapter

    }
    inner class Note (nodeHarga:String , nodeMenu:String, nodeDeskripsi:String, nodeUrl:String, nodeQuantity:Int){
        var nodeHarga:String?= nodeHarga
        var nodeMenu:String?= nodeMenu
        var nodeDeskripsi:String? = nodeDeskripsi
        var nodeUrl:String? = nodeUrl
        var nodeQuantity = nodeQuantity



    }
    inner class myAdaper : BaseAdapter {
        var listNoteAdapter =ArrayList<Note>()
        var context : Context?=null


        constructor(context: Context, listNoteAdapter: ArrayList<Note>) : super() {
            this.listNoteAdapter = listNoteAdapter
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myView = layoutInflater.inflate(R.layout.row_menu, null)
            val myNote = listNoteAdapter[position]
            myView.harganyo.text = myNote.nodeHarga
            myView.txMenu.text = myNote.nodeMenu
            myView.txDeskripsi.text = myNote.nodeDeskripsi
            myView.psQuantity.text = myNote.nodeQuantity.toString()

//            var fileType = myNote.nodeType.toString()
            var url = Uri.parse(myNote.nodeUrl.toString())

//            when (fileType) {
//                ".jpg" -> {
//                    Picasso.get().load(url).into(myView.imvPesan)
//                }
//            }
            Picasso.get().load(url).into(myView.Imv)


            myView.btnTambah.setOnClickListener {
                if (quantity == 100) {
                    Toast.makeText(this@menusaw, "pesanan maksimal 100", Toast.LENGTH_LONG).show()
                }
                else {
                    quantity = arrQ.get(position).toInt() + 1
                    myView.psQuantity.text = quantity.toString()
                    arrQ.set(position, quantity.toString())
//                    Log.i(psNama.toString(), arrQ[position])
                }
                total()
            }

            myView.btnKurang.setOnClickListener {
                if (quantity == 0 ) {
                    Toast.makeText(this@menusaw, "pesanan minimal 1", Toast.LENGTH_SHORT).show()
                }
                else {
                    quantity = arrQ.get(position).toInt() - 1
                    myView.psQuantity.text = quantity.toString()
                    arrQ.set(position, quantity.toString())
//                    Log.i(psNama.toString(), arrQ[position])
                }
                total()
            }
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
