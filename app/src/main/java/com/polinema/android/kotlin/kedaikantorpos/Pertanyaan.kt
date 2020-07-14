package com.polinema.android.kotlin.kedaikantorpos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_pertanyaan.*

class Pertanyaan : AppCompatActivity() {


    lateinit var db : CollectionReference


    override fun onStart() {
        super.onStart()




//        db = FirebaseFirestore.getInstance().collection("menu")
//        val L = arrayListOf<String>()
//        val y = arrayListOf<String>()
//        val u = arrayListOf<String>()
//        val t = arrayListOf<String>()
//        val z = arrayListOf<String>()
//        db.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
//            for (doc in querySnapshot!!.documents) {
//                L.add(doc["file_harga"].toString())
//                y.add((doc["kriteriaharga"].toString().toDouble() * (30.0 / 100.0)).toString())
//                u.add((doc["kriteriaukuran"].toString().toDouble() * (60.0 / 100.0)).toString())
//                t.add((doc["kriteriatoping"].toString().toDouble() * (10.0 / 100.0)).toString())
//                z.add(((doc["kriteriaharga"].toString().toDouble()
//                        * (30.0 / 100.0)) + (doc["kriteriaukuran"].toString().toDouble()
//                        * (60.0 / 100.0)) + (doc["kriteriatoping"].toString().toDouble()
//                        * (10.0 / 100.0))).toString())
//            }
//            Log.e("Coba :",L.toString())
//            if (firebaseFirestoreException!=null){
//                Log.e("firestore :",firebaseFirestoreException.message)
//            }
//
//            var max = 0.0
//            for ( i in 0..38 ) {
//                if ( z[i] > max.toString()) {
//                    max = z[i].toDouble()
//                }
//
//            }
//            Log.e("max", max.toString())
//            Log.e("Hasil", z.toString() )
//            var idx = 0
//            for (i in 0..38 ){
//                if (max == z[i].toDouble())
//                    idx = i
//            }
//            Log.e("Hasil", idx.toString() )
//        }
        txcb.setOnClickListener{

            val intent = Intent(this,
                MainActivity::class.java)
            startActivity(intent)
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pertanyaan)

        val per1 = resources.getStringArray(R.array.Pertimbangan)
        var spinner1 = findViewById<Spinner>(R.id.sp1)
        var spinner2 = findViewById<Spinner>(R.id.sp2)
        var spinner3 = findViewById<Spinner>(R.id.sp3)
        if (spinner2 != null && spinner1 != null && spinner3 != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, per1
            )
            spinner2.adapter = adapter
            spinner1.adapter = adapter
            spinner3.adapter = adapter

            spinner1.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }

            spinner2.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {


                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
            spinner3.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {


                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }


        btnRekom.setOnClickListener {

            db = FirebaseFirestore.getInstance().collection("menu")
            val L = arrayListOf<String>()
            val z = arrayListOf<String>()
            val armenu = arrayListOf<String>()
            if (sp1.selectedItem == "Harga" && sp2.selectedItem == "Harga" && sp3.selectedItem == "Harga" ){
                Toast.makeText(
                    this@Pertanyaan,
                    "Pertimbangan tidak boleh sama !!" , Toast.LENGTH_SHORT).show()
            }
            else if  (sp1.selectedItem == "Harga" && sp2.selectedItem == "Harga" && sp3.selectedItem == "Toping" ){
                Toast.makeText(
                    this@Pertanyaan,
                    "Pertimbangan tidak dapat sama !!" , Toast.LENGTH_SHORT).show()
            }
            else if  (sp1.selectedItem == "Harga" && sp2.selectedItem == "Harga" && sp3.selectedItem == "Ukuran" ){
                Toast.makeText(
                    this@Pertanyaan,
                    "Pertimbangan tidak dapat sama !!" , Toast.LENGTH_SHORT).show()
            }
            else if  (sp1.selectedItem == "Harga" && sp2.selectedItem == "Ukuran" && sp3.selectedItem == "Harga" ){
                Toast.makeText(
                    this@Pertanyaan,
                    "Pertimbangan tidak dapat sama !!" , Toast.LENGTH_SHORT).show()
            }
            else if  (sp1.selectedItem == "Harga" && sp2.selectedItem == "Ukuran" && sp3.selectedItem == "Ukuran" ){
                Toast.makeText(
                    this@Pertanyaan,
                    "Pertimbangan tidak dapat sama !!" , Toast.LENGTH_SHORT).show()
            }
            else if  (sp1.selectedItem == "Ukuran" && sp2.selectedItem == "Harga" && sp3.selectedItem == "Harga" ){
                Toast.makeText(
                    this@Pertanyaan,
                    "Pertimbangan tidak dapat sama !!" , Toast.LENGTH_SHORT).show()
            }
            else if  (sp1.selectedItem == "Toping" && sp2.selectedItem == "Harga" && sp3.selectedItem == "Harga" ){
                Toast.makeText(
                    this@Pertanyaan,
                    "Pertimbangan tidak dapat sama !!" , Toast.LENGTH_SHORT).show()
            }
            else if  (sp1.selectedItem == "Ukuran" && sp2.selectedItem == "Ukuran" && sp3.selectedItem == "Ukuran" ){
                Toast.makeText(
                    this@Pertanyaan,
                    "Pertimbangan tidak dapat sama !!" , Toast.LENGTH_SHORT).show()
            }
            else if  (sp1.selectedItem == "Ukuran" && sp2.selectedItem == "Ukuran" && sp3.selectedItem == "Toping" ){
                Toast.makeText(
                    this@Pertanyaan,
                    "Pertimbangan tidak dapat sama !!" , Toast.LENGTH_SHORT).show()
            }
            else if  (sp1.selectedItem == "Ukuran" && sp2.selectedItem == "Ukuran" && sp3.selectedItem == "Harga" ){
                Toast.makeText(
                    this@Pertanyaan,
                    "Pertimbangan tidak dapat sama !!" , Toast.LENGTH_SHORT).show()
            }
            else if  (sp1.selectedItem == "Ukuran" && sp2.selectedItem == "Toping" && sp3.selectedItem == "Ukuran" ){
                Toast.makeText(
                    this@Pertanyaan,
                    "Pertimbangan tidak dapat sama !!" , Toast.LENGTH_SHORT).show()
            }
            else if  (sp1.selectedItem == "Ukuran" && sp2.selectedItem == "Harga" && sp3.selectedItem == "Ukuran" ){
                Toast.makeText(
                    this@Pertanyaan,
                    "Pertimbangan tidak dapat sama !!" , Toast.LENGTH_SHORT).show()
            }
            else if  (sp1.selectedItem == "Toping" && sp2.selectedItem == "Ukuran" && sp3.selectedItem == "Ukuran" ){
                Toast.makeText(
                    this@Pertanyaan,
                    "Pertimbangan tidak dapat sama !!" , Toast.LENGTH_SHORT).show()
            }
            else if  (sp1.selectedItem == "Harga" && sp2.selectedItem == "Ukuran" && sp3.selectedItem == "Ukuran" ){
                Toast.makeText(
                    this@Pertanyaan,
                    "Pertimbangan tidak dapat sama !!" , Toast.LENGTH_SHORT).show()
            }
            else if  (sp1.selectedItem == "Toping" && sp2.selectedItem == "Toping" && sp3.selectedItem == "Toping" ){
                Toast.makeText(
                    this@Pertanyaan,
                    "Pertimbangan tidak dapat sama !!" , Toast.LENGTH_SHORT).show()
            }
            else if  (sp1.selectedItem == "Toping" && sp2.selectedItem == "Toping" && sp3.selectedItem == "Harga" ){
                Toast.makeText(
                    this@Pertanyaan,
                    "Pertimbangan tidak dapat sama !!" , Toast.LENGTH_SHORT).show()
            }
            else if  (sp1.selectedItem == "Toping" && sp2.selectedItem == "Toping" && sp3.selectedItem == "Ukuran" ){
                Toast.makeText(
                    this@Pertanyaan,
                    "Pertimbangan tidak dapat sama !!" , Toast.LENGTH_SHORT).show()
            }
            else if  (sp1.selectedItem == "Toping" && sp2.selectedItem == "Harga" && sp3.selectedItem == "Toping" ){
                Toast.makeText(
                    this@Pertanyaan,
                    "Pertimbangan tidak dapat sama !!" , Toast.LENGTH_SHORT).show()
            }
            else if  (sp1.selectedItem == "Toping" && sp2.selectedItem == "Ukuran" && sp3.selectedItem == "Toping" ){
                Toast.makeText(
                    this@Pertanyaan,
                    "Pertimbangan tidak dapat sama !!" , Toast.LENGTH_SHORT).show()
            }
            else if  (sp1.selectedItem == "Harga" && sp2.selectedItem == "Toping" && sp3.selectedItem == "Toping" ){
                Toast.makeText(
                    this@Pertanyaan,
                    "Pertimbangan tidak dapat sama !!" , Toast.LENGTH_SHORT).show()
            }
            else if  (sp1.selectedItem == "Ukuran" && sp2.selectedItem == "Toping" && sp3.selectedItem == "Toping" ){
                Toast.makeText(
                    this@Pertanyaan,
                    "Pertimbangan tidak dapat sama !!" , Toast.LENGTH_SHORT).show()
            }




            else if (sp1.selectedItem == "Harga" && sp2.selectedItem == "Toping" && sp3.selectedItem == "Ukuran"){
                    db.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                        for (doc in querySnapshot!!.documents) {
                            armenu.add(doc["file_name"].toString())
                            L.add(doc["file_harga"].toString())
                            z.add(((doc["kriteriaharga"].toString().toDouble()
                                    * (60.0 / 100.0)) + (doc["kriteriatoping"].toString().toDouble()
                                    * (30.0 / 100.0)) + (doc["kriteriaukuran"].toString().toDouble()
                                    * (10.0 / 100.0))).toString())
                        }
                        Log.e("Coba :",L.toString())
                        if (firebaseFirestoreException!=null){
                            Log.e("firestore :",firebaseFirestoreException.message)
                        }
                        var max = 0.0
                        for ( i in 0 until z.size ) {
                            if ( z[i] > max.toString()) {
                                max = z[i].toDouble()
                            }
                        }
                        Log.e("max", max.toString())
                        Log.e("Hasil", z.toString() )
                        var idx = 0
                        for ( i in 0 until z.size ) {
                            for ( j in i until z.size ) {
                                if ( z[i] < z[j]) {
//                                max = z[i].toDouble()
                                    var temp=z[i]
                                    z[i]=z[j]
                                    z[j]=temp
                                    var menu=armenu[i]
                                    armenu[i]=armenu[j]
                                    armenu[j]=menu
                                }
                            }
                        }
//                    for (i in 0 until z.size ){
//                        if (max == z[i].toDouble())
//                            idx = i
//                    }
                        Log.e("Array Menu", z.toString())
                        Log.e("Menu", armenu.toString())
//                    val men = armenu
                        val intent = Intent(this, menusaw::class.java)
                        intent.putExtra("xx", armenu)
                        startActivity(intent)
                        Log.e("Hasil", idx.toString() )
                    }
            }
            else if (sp1.selectedItem == "Harga" && sp2.selectedItem == "Ukuran" && sp3.selectedItem == "Toping"){

                db.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                    for (doc in querySnapshot!!.documents) {
                        armenu.add(doc["file_name"].toString())
                        L.add(doc["file_harga"].toString())
                        z.add(((doc["kriteriaharga"].toString().toDouble()
                                * (60.0 / 100.0)) + (doc["kriteriaukuran"].toString().toDouble()
                                * (30.0 / 100.0)) + (doc["kriteriatoping"].toString().toDouble()
                                * (10.0 / 100.0))).toString())
                    }
                    Log.e("Coba :",L.toString())
                    if (firebaseFirestoreException!=null){
                        Log.e("firestore :",firebaseFirestoreException.message)
                    }
                    var max = 0.0
                    for ( i in 0 until z.size ) {
                        if ( z[i] > max.toString()) {
                            max = z[i].toDouble()
                        }
                    }
                    Log.e("max", max.toString())
                    Log.e("Hasil", z.toString() )
                    var idx = 0
                    for ( i in 0 until z.size ) {
                        for ( j in i until z.size ) {
                            if ( z[i] < z[j]) {
//                                max = z[i].toDouble()
                                var temp=z[i]
                                z[i]=z[j]
                                z[j]=temp
                                var menu=armenu[i]
                                armenu[i]=armenu[j]
                                armenu[j]=menu
                            }
                        }
                    }
//                    for (i in 0 until z.size ){
//                        if (max == z[i].toDouble())
//                            idx = i
//                    }
                    Log.e("Array Menu", z.toString())
                    Log.e("Menu", armenu.toString())
//                    val men = armenu
                    val intent = Intent(this, menusaw::class.java)
                    intent.putExtra("xx", armenu)
                    startActivity(intent)
                    Log.e("Hasil", idx.toString() )
                }
            }
            else if (sp1.selectedItem == "Toping" && sp2.selectedItem == "Ukuran" && sp3.selectedItem == "Harga"){

                db.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                    for (doc in querySnapshot!!.documents) {
                        armenu.add(doc["file_name"].toString())
                        L.add(doc["file_harga"].toString())
                        z.add(((doc["kriteriatoping"].toString().toDouble()
                                * (60.0 / 100.0)) + (doc["kriteriaukuran"].toString().toDouble()
                                * (30.0 / 100.0)) + (doc["kriteriaharga"].toString().toDouble()
                                * (10.0 / 100.0))).toString())
                    }
                    Log.e("Coba :",L.toString())
                    if (firebaseFirestoreException!=null){
                        Log.e("firestore :",firebaseFirestoreException.message)
                    }
                    var max = 0.0
                    for ( i in 0 until z.size ) {
                        if ( z[i] > max.toString()) {
                            max = z[i].toDouble()
                        }
                    }
                    Log.e("max", max.toString())
                    Log.e("Hasil", z.toString() )
                    var idx = 0
                    for ( i in 0 until z.size ) {
                        for ( j in i until z.size ) {
                            if ( z[i] < z[j]) {
//                                max = z[i].toDouble()
                                var temp=z[i]
                                z[i]=z[j]
                                z[j]=temp
                                var menu=armenu[i]
                                armenu[i]=armenu[j]
                                armenu[j]=menu
                            }
                        }
                    }
//                    for (i in 0 until z.size ){
//                        if (max == z[i].toDouble())
//                            idx = i
//                    }
                    Log.e("Array Menu", z.toString())
                    Log.e("Menu", armenu.toString())
//                    val men = armenu
                    val intent = Intent(this, menusaw::class.java)
                    intent.putExtra("xx", armenu)
                    startActivity(intent)
                    Log.e("Hasil", idx.toString() )
                }
            }
            else if (sp1.selectedItem == "Toping" && sp2.selectedItem == "Harga" && sp3.selectedItem == "Ukuran"){

                db.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                    for (doc in querySnapshot!!.documents) {
                        armenu.add(doc["file_name"].toString())
                        L.add(doc["file_harga"].toString())
                        z.add(((doc["kriteriatoping"].toString().toDouble()
                                * (60.0 / 100.0)) + (doc["kriteriaharga"].toString().toDouble()
                                * (30.0 / 100.0)) + (doc["kriteriaukuran"].toString().toDouble()
                                * (10.0 / 100.0))).toString())
                    }
                    Log.e("Coba :",L.toString())
                    if (firebaseFirestoreException!=null){
                        Log.e("firestore :",firebaseFirestoreException.message)
                    }
                    var max = 0.0
                    for ( i in 0 until z.size ) {
                        if ( z[i] > max.toString()) {
                            max = z[i].toDouble()
                        }
                    }
                    Log.e("max", max.toString())
                    Log.e("Hasil", z.toString() )
                    var idx = 0
                    for ( i in 0 until z.size ) {
                        for ( j in i until z.size ) {
                            if ( z[i] < z[j]) {
//                                max = z[i].toDouble()
                                var temp=z[i]
                                z[i]=z[j]
                                z[j]=temp
                                var menu=armenu[i]
                                armenu[i]=armenu[j]
                                armenu[j]=menu
                            }
                        }
                    }
//                    for (i in 0 until z.size ){
//                        if (max == z[i].toDouble())
//                            idx = i
//                    }
                    Log.e("Array Menu", z.toString())
                    Log.e("Menu", armenu.toString())
//                    val men = armenu
                    val intent = Intent(this, menusaw::class.java)
                    intent.putExtra("xx", armenu)
                    startActivity(intent)
                    Log.e("Hasil", idx.toString() )
                }
            }
            else if (sp1.selectedItem == "Ukuran" && sp2.selectedItem == "Harga" && sp3.selectedItem == "Toping"){

                db.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                    for (doc in querySnapshot!!.documents) {
                        armenu.add(doc["file_name"].toString())
                        L.add(doc["file_harga"].toString())
                        z.add(((doc["kriteriaukuran"].toString().toDouble()
                                * (60.0 / 100.0)) + (doc["kriteriaharga"].toString().toDouble()
                                * (30.0 / 100.0)) + (doc["kriteriatoping"].toString().toDouble()
                                * (10.0 / 100.0))).toString())
                    }
                    Log.e("Coba :",L.toString())
                    if (firebaseFirestoreException!=null){
                        Log.e("firestore :",firebaseFirestoreException.message)
                    }
                    var max = 0.0
                    for ( i in 0 until z.size ) {
                        if ( z[i] > max.toString()) {
                            max = z[i].toDouble()
                        }
                    }
                    Log.e("max", max.toString())
                    Log.e("Hasil", z.toString() )
                    var idx = 0
                    for ( i in 0 until z.size ) {
                        for ( j in i until z.size ) {
                            if ( z[i] < z[j]) {
//                                max = z[i].toDouble()
                                var temp=z[i]
                                z[i]=z[j]
                                z[j]=temp
                                var menu=armenu[i]
                                armenu[i]=armenu[j]
                                armenu[j]=menu
                            }
                        }
                    }
//                    for (i in 0 until z.size ){
//                        if (max == z[i].toDouble())
//                            idx = i
//                    }
                    Log.e("Array Menu", z.toString())
                    Log.e("Menu", armenu.toString())
//                    val men = armenu
                    val intent = Intent(this, menusaw::class.java)
                    intent.putExtra("xx", armenu)
                    startActivity(intent)
                    Log.e("Hasil", idx.toString() )
                }
            }
            else if (sp1.selectedItem == "Ukuran" && sp2.selectedItem == "Toping" && sp3.selectedItem == "Harga"){

                db.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                    for (doc in querySnapshot!!.documents) {
                        armenu.add(doc["file_name"].toString())
                        L.add(doc["file_harga"].toString())
                        z.add(((doc["kriteriaukuran"].toString().toDouble()
                                * (60.0 / 100.0)) + (doc["kriteriatoping"].toString().toDouble()
                                * (30.0 / 100.0)) + (doc["kriteriaharga"].toString().toDouble()
                                * (10.0 / 100.0))).toString())
                    }
                    Log.e("Coba :",L.toString())
                    if (firebaseFirestoreException!=null){
                        Log.e("firestore :",firebaseFirestoreException.message)
                    }
                    var max = 0.0
                    for ( i in 0 until z.size ) {
                        if ( z[i] > max.toString()) {
                            max = z[i].toDouble()
                        }
                    }
                    Log.e("max", max.toString())
                    Log.e("Hasil", z.toString() )
                    var idx = 0
                    for ( i in 0 until z.size ) {
                        for ( j in i until z.size ) {
                            if ( z[i] < z[j]) {
//                                max = z[i].toDouble()
                                var temp=z[i]
                                z[i]=z[j]
                                z[j]=temp
                                var menu=armenu[i]
                                armenu[i]=armenu[j]
                                armenu[j]=menu
                            }
                        }
                    }
//                    for (i in 0 until z.size ){
//                        if (max == z[i].toDouble())
//                            idx = i
//                    }
                    Log.e("Array Menu", z.toString())
                    Log.e("Menu", armenu.toString())
//                    val men = armenu
                    val intent = Intent(this, menusaw::class.java)
                    intent.putExtra("xx", armenu)
                    startActivity(intent)
                    Log.e("Hasil", idx.toString() )
                }
            }

        var spinner1 = findViewById<Spinner>(R.id.sp1)
        var spinner2 = findViewById<Spinner>(R.id.sp2)
        var spinner3 = findViewById<Spinner>(R.id.sp3)
        if (spinner2 != null && spinner1 != null && spinner3 != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, per1
            )
            spinner2.adapter = adapter
            spinner1.adapter = adapter
            spinner3.adapter = adapter

            spinner1.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }

            spinner2.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {


                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
            spinner3.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {


                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
//        var harga = 0
//        var topping = 0
//        var ukuran = 0
//
//        hargaP.helperText = (100 - (ukuran + topping)).toString()
//        topingP.helperText = (100 - (harga + ukuran)).toString()
//        ukuranP.helperText = (100 - (harga + topping)).toString()
//
//        txHargaP.filters = arrayOf(InputFilter.LengthFilter(3))
//        txTopingP.filters = arrayOf(InputFilter.LengthFilter(3))
//        txUkuranP.filters = arrayOf(InputFilter.LengthFilter(3))
//
//        txHargaP.doOnTextChanged { text, _, _, _ ->
//            harga = if (!text.isNullOrEmpty()) text.toString().toInt() else 0
//            topping = if (!txTopingP.text.isNullOrEmpty()) txTopingP.text.toString().toInt() else 0
//            ukuran = if (!txUkuranP.text.isNullOrEmpty()) txUkuranP.text.toString().toInt() else 0
//
//            txHargaP.filters = arrayOf(MinMaxFilter(0, 100 - (ukuran + topping)), InputFilter.LengthFilter(3))
//            txTopingP.filters = arrayOf(MinMaxFilter(0, 100 - (harga + ukuran)), InputFilter.LengthFilter(3))
//            txUkuranP.filters = arrayOf(MinMaxFilter(0, 100 - (harga + topping)), InputFilter.LengthFilter(3))
//
//            hargaP.helperText = (100 - (ukuran + topping) - harga).toString()
//            topingP.helperText = (100 - (harga + ukuran) - topping).toString()
//            ukuranP.helperText = (100 - (harga + topping) - ukuran).toString()
//        }
//
//        txTopingP.doOnTextChanged { text, _, _, _ ->
//            harga = if (!txHargaP.text.isNullOrEmpty()) txHargaP.text.toString().toInt() else 0
//            topping = if (!text.isNullOrEmpty()) text.toString().toInt() else 0
//            ukuran = if (!txUkuranP.text.isNullOrEmpty()) txUkuranP.text.toString().toInt() else 0
//
//            txHargaP.filters = arrayOf(MinMaxFilter(0, 100 - (ukuran + topping)), InputFilter.LengthFilter(3))
//            txTopingP.filters = arrayOf(MinMaxFilter(0, 100 - (harga + ukuran)), InputFilter.LengthFilter(3))
//            txUkuranP.filters = arrayOf(MinMaxFilter(0, 100 - (harga + topping)), InputFilter.LengthFilter(3))
//
//            hargaP.helperText = (100 - (ukuran + topping) - harga).toString()
//            topingP.helperText = (100 - (harga + ukuran) - topping).toString()
//            ukuranP.helperText = (100 - (harga + topping) - ukuran).toString()
//        }
//
//        txUkuranP.doOnTextChanged { text, _, _, _ ->
//            harga = if (!txHargaP.text.isNullOrEmpty()) txHargaP.text.toString().toInt() else 0
//            topping = if (!txTopingP.text.isNullOrEmpty()) txTopingP.text.toString().toInt() else 0
//            ukuran = if (!text.isNullOrEmpty()) text.toString().toInt() else 0
//
//            txHargaP.filters = arrayOf(MinMaxFilter(0, 100 - (ukuran + topping)), InputFilter.LengthFilter(3))
//            txTopingP.filters = arrayOf(MinMaxFilter(0, 100 - (harga + ukuran)), InputFilter.LengthFilter(3))
//            txUkuranP.filters = arrayOf(MinMaxFilter(0, 100 - (harga + topping)), InputFilter.LengthFilter(3))
//
//            hargaP.helperText = (100 - (ukuran + topping) - harga).toString()
//            topingP.helperText = (100 - (harga + ukuran) - topping).toString()
//            ukuranP.helperText = (100 - (harga + topping) - ukuran).toString()
//        }
    }

}
}
