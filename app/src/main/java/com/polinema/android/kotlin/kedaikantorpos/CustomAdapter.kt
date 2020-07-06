package com.polinema.android.kotlin.kedaikantorpos

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class   CustomAdapter(val context : Context,
                    arrayList: ArrayList<HashMap<String,Any>>) : BaseAdapter() {

    val F_NAME  = "file_name"
    val F_HARGA  = "file_harga"
    val F_DESKRIPSI  = "file_deskripsi"
    val F_TYPE  = "file_type"
    val F_URL  = "file_url"
    val list  = arrayList
    var uri = Uri.EMPTY

    inner class ViewHolder(){
        var txFileName : TextView? = null
        var txFIleHarga : TextView? = null
        var txDeskripsi : TextView? = null
        var hapus : Button? = null
        var imv : ImageView? = null
    }
    override fun getView(postion: Int, convertView: View?, parent : ViewGroup?): View {
        var holder = ViewHolder()
        var view:View? = convertView
        if(convertView == null ){
            var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.row_pesanan,null,true)

            holder.txFileName = view!!.findViewById(R.id.txMenu) as TextView
            holder.txFIleHarga = view!!.findViewById(R.id.rego1) as TextView
            holder.txDeskripsi = view!!.findViewById(R.id.txDeskripsi) as TextView
            holder.hapus = view!!.findViewById(R.id.btnTambah) as Button
            holder.imv = view!!.findViewById(R.id.Imv) as ImageView

            view.tag = holder
        }else{
            holder = view!!.tag as ViewHolder
        }

        var fileType = list.get(postion).get(F_TYPE).toString()
        uri = Uri.parse(list.get(postion).get(F_URL).toString())

        holder.txFileName!!.setText(list.get(postion).get(F_NAME).toString())
        holder.txFIleHarga!!.setText(list.get(postion).get(F_HARGA).toString())
        holder.txDeskripsi!!.setText(list.get(postion).get(F_DESKRIPSI).toString())

        holder.hapus!!.setOnClickListener {
            FirebaseFirestore.getInstance().collection("menu").whereEqualTo("file_name", list.get(postion).get(F_NAME).toString()).get()
                .addOnCompleteListener {
                    for (doc in it.result!!.documents) {
                        FirebaseFirestore.getInstance().collection("menu").document(doc.id).delete()
                            .addOnCompleteListener {
                                Toast.makeText(context, "hapus sukses", Toast.LENGTH_SHORT).show()
                            }
                            .addOnFailureListener {
                                Toast.makeText(context, "hapus gagal", Toast.LENGTH_SHORT).show()

                            }
                    }
                }
        }

        Picasso.get().load(uri).into(holder.imv)

        return view!!
    }

    override fun getItem(position : Int): Any {
        return list.get(position)
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return list.size
    }
}