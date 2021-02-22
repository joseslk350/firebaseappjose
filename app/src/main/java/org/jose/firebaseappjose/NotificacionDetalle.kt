package org.jose.firebaseappjose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class NotificacionDetalle : AppCompatActivity() {


    lateinit var item:Cliente
lateinit var textnombre: TextView
lateinit var textemail: TextView
lateinit var texttelefono: TextView
    lateinit var textmensaje: TextView
    lateinit var fabescribir: FloatingActionButton
    lateinit var fabllamar: FloatingActionButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notificacion_detalle)

        textnombre=findViewById<TextView>(R.id.textnombre)
        textemail=findViewById<TextView>(R.id.textemail)
        texttelefono=findViewById<TextView>(R.id.texttelefono)
        textmensaje=findViewById<TextView>(R.id.textmensaje)
fabescribir=findViewById(R.id.fabescribir)
        fabllamar=findViewById(R.id.fabllamar)



        val nombre= intent.extras?.get("nombre")
        val telefono = intent.extras?.get("telefono")
        val email= intent.extras?.get("email")
        val mensaje = intent.extras?.get("mensaje")
      //  item=(application as Aplicacion).findDataByTitle(title = title.toString())
        textnombre.text= nombre.toString()
        texttelefono.text = telefono.toString()
        textemail.text=email.toString()
        textmensaje.text=mensaje.toString()



        fabllamar.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }


        fabescribir.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }











    }
}

