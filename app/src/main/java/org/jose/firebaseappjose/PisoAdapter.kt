package org.jose.firebaseappjose

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class PisoAdapter(private val pisoList: List<Piso>, val context: Context) : RecyclerView.Adapter<PisoAdapter.MyViewHolder>(){

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) , View.OnClickListener{
        private var pItem: Piso? = null
        var nombre: TextView
        var precio: TextView
        var url: TextView
        var foto: ImageView






        init {
            nombre = view.findViewById<View>(R.id.nombre) as TextView
            url = view.findViewById<View>(R.id.precio) as TextView
            precio = view.findViewById<View>(R.id.url) as TextView
            foto = view.findViewById(R.id.imagenpiso) as ImageView
            view.setOnClickListener(this);

        }

        fun setItem(piso: Piso) {
            pItem = piso
            nombre.text = piso.nombre
            url.text = piso.url
            precio.text = piso.precio
            //foto.setImageBitmap(piso.foto)
            Picasso.get().load(piso.url).into(foto)



        }

        override fun onClick(view: View) {
            val intent = Intent(context ,PisoDetalle::class.java)
            intent.putExtra("nombre", pItem!!.nombre)
            (context as Activity).startActivity(intent)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.piso_row, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val piso = pisoList[position]
        holder.setItem(piso)
    }

    override fun getItemCount(): Int {
        return pisoList.size
    }
}