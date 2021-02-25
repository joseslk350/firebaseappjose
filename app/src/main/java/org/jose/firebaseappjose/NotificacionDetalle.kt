package org.jose.firebaseappjose

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

            val phoneNo: String = texttelefono.getText().toString()
            if (!TextUtils.isEmpty(phoneNo)) {
                val dial = "$phoneNo"
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse(dial)))
            } else {
                Toast.makeText(this, "Enter a phone number", Toast.LENGTH_SHORT).show()
            }

        }


        fabescribir.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()



            val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "$email", null))
            startActivity(Intent( emailIntent))





        }











    }
}

