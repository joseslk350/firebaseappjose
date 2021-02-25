package org.jose.firebaseappjose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton


class MainActivity : AppCompatActivity() {

    lateinit var emaill: EditText
    lateinit var pass: EditText
    lateinit var registrar: Button
    lateinit var botonmensajes:ImageButton
    lateinit var botonlistapisos:ImageButton
    lateinit var clientesLista1: MutableList<Cliente>
    lateinit var botonagenda:ImageButton
    lateinit var botonaddpropiedad:ImageButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        botonmensajes=findViewById(R.id.botonmensajes)
        botonlistapisos=findViewById(R.id.botonlistapisos)
        botonagenda=findViewById(R.id.botonagenda)
        botonaddpropiedad=findViewById((R.id.botonaddpropiedad))

        clientesLista1 = (application as Aplicacion).clientelistaappli

botonmensajes.setOnClickListener {

  //  Toast.makeText(this,clientesLista1[1].nombre.toString(),Toast.LENGTH_SHORT).show()
    var intent= Intent(this, NotificacionActividad::class.java)

    startActivity(intent)
}

        botonlistapisos.setOnClickListener {


            var intento= Intent(this, PisoRecycleViewActivity::class.java)
            startActivity(intento)
        }




        botonagenda.setOnClickListener {


            var intento= Intent(this, Agenda::class.java)
            startActivity(intento)
        }


        botonaddpropiedad.setOnClickListener {
            var intento= Intent(this, Addactividad::class.java)
            startActivity(intento)
        }

    }







}













