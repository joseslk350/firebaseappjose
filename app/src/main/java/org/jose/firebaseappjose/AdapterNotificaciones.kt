package org.jose.firebaseappjose

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView




class AdapterNotificaciones(private val bookList: List<Cliente>, val context: Context) : RecyclerView.Adapter<AdapterNotificaciones.MyViewHolder>(){

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) , View.OnClickListener{
        private var bItem: Cliente? = null
        var title: TextView
        var pages: TextView
        var author: TextView

        init {
            title = view.findViewById<View>(R.id.renombre) as TextView
            author = view.findViewById<View>(R.id.reemail) as TextView
            pages = view.findViewById<View>(R.id.retelefono) as TextView
            view.setOnClickListener(this);

        }

        fun setItem(book: Cliente) {
            bItem = book
            title.text = book.nombre
            author.text = book.emailc
            pages.text = book.telefono
        }

        override fun onClick(view: View) {
           val intent = Intent(context ,NotificacionDetalle::class.java)
            intent.putExtra("nombre", bItem!!.nombre)
            intent.putExtra("telefono", bItem!!.telefono)
            intent.putExtra("email", bItem!!.emailc)
            intent.putExtra("mensaje", bItem!!.mensaje)
            (context as Activity).startActivity(intent)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.filanotificaciones, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val book = bookList[position]
        holder.setItem(book)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }
}





