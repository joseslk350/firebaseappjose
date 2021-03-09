package org.jose.firebaseappjose

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView




class AdapterNotificaciones(private val clienteList: List<Cliente>, val context: Context) : RecyclerView.Adapter<AdapterNotificaciones.MyViewHolder>(){

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) , View.OnClickListener{
        private var bItem: Cliente? = null
        var nombr: TextView
        var telef: TextView
        var emailll: TextView

        init {
            nombr = view.findViewById<View>(R.id.renombre) as TextView
            emailll = view.findViewById<View>(R.id.reemail) as TextView
            telef = view.findViewById<View>(R.id.retelefono) as TextView
            view.setOnClickListener(this);

        }

        fun setItem(clien: Cliente) {
            bItem = clien
            nombr.text = clien.nombre
            emailll.text = clien.emailc
            telef.text = clien.telefono
        }

        override fun onClick(view: View) {
           val intent = Intent(context ,NotificacionDetalle::class.java)
            intent.putExtra("nombre", bItem!!.nombre)
            intent.putExtra("telefono", bItem!!.telefono)
            intent.putExtra("email", bItem!!.emailc)
            intent.putExtra("mensaje", bItem!!.mensaje)
            intent.putExtra("desde", bItem!!.desde)
            intent.putExtra("id", bItem!!.id)

            (context as Activity).startActivity(intent)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.filanotificaciones, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val book = clienteList[position]
        holder.setItem(book)
    }

    override fun getItemCount(): Int {
        return clienteList.size
    }
}





