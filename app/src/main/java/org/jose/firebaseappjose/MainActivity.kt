package org.jose.firebaseappjose

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText



class MainActivity : AppCompatActivity() {

    lateinit var emaill: EditText
    lateinit var pass: EditText
    lateinit var registrar: Button
    lateinit var login:Button
    lateinit var pisos:Button
    lateinit var clientesLista1: MutableList<Cliente>
    lateinit var botonagenda:Button

    @SuppressLint("InvalidAnalyticsName")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login=findViewById<Button>(R.id.botonmensajes)
        pisos=findViewById(R.id.botonpisos)
        botonagenda=findViewById(R.id.botonagenda)

        clientesLista1 = (application as Aplicacion).clientelistaappli

login.setOnClickListener {

  //  Toast.makeText(this,clientesLista1[1].nombre.toString(),Toast.LENGTH_SHORT).show()
    var intent= Intent(this, NotificacionActividad::class.java)

    startActivity(intent)
}

        pisos.setOnClickListener {


            var intento= Intent(this, PisoRecycleViewActivity::class.java)
            startActivity(intento)
        }




        botonagenda.setOnClickListener {


            var intento= Intent(this, Agenda::class.java)
            startActivity(intento)
        }


    }







}













