package org.jose.firebaseappjose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class PisoDetalle : AppCompatActivity() {

    lateinit var item:Piso
    lateinit var varnombre: TextView
    lateinit var varaltura: TextView
    lateinit var varbano: TextView
    lateinit var varurl: TextView
    lateinit var varhabitacion: TextView
    lateinit var varprecio: TextView
    lateinit var varsupp: TextView
    lateinit var vartipo: TextView
    lateinit var imageVifoto: ImageView







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_piso_detalle)

        varnombre=findViewById<TextView>(R.id.tnombre)
        varaltura=findViewById<TextView>(R.id.taltura)
        varbano=findViewById<TextView>(R.id.tbano)
        varurl=findViewById<TextView>(R.id.turl)
        varhabitacion=findViewById<TextView>(R.id.thabitacion)
        varprecio=findViewById<TextView>(R.id.tprecio)
        varsupp=findViewById<TextView>(R.id.tsupp)
        vartipo=findViewById<TextView>(R.id.ttipo)
        imageVifoto=findViewById(R.id.imageViewfoto)


        val nombre= intent.extras?.get("nombre")
       // val telefono = intent.extras?.get("telefono")
       // val email= intent.extras?.get("email")
       // val mensaje = intent.extras?.get("mensaje")
        item=(application as Aplicacion).findDataByTitle(nom1 = nombre.toString())

        varnombre.text= item.nombre.toString()


        varaltura.text= item.altura.toString()
        varbano.text= item.bano.toString()
        varurl.text= item.url.toString()
        varhabitacion.text= item.habitacion.toString()
        varprecio.text= item.precio.toString()
        varsupp.text= item.supp.toString()
        vartipo.text= item.tipo.toString()
        imageVifoto.setImageBitmap(item.foto)


    }
}