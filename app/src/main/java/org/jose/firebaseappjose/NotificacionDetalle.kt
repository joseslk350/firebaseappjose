package org.jose.firebaseappjose

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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

            checkPermission()





        }


        fabescribir.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()



            val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "$email", null))
            startActivity(Intent( emailIntent))





        }











    }




    fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.CALL_PHONE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.CALL_PHONE),
                        42)
            }
        } else {
            // Permission has already been granted
            callPhone()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 42) {
            // If request is cancelled, the result arrays are empty.
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // permission was granted, yay!
                callPhone()
            } else {
                // permission denied, boo! Disable the
                // functionality
            }
            return
        }
    }

    fun callPhone(){
      //  val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+34 656 66 14 78"))
       // startActivity(intent)

        val numerotelefono: String = texttelefono.getText().toString()
        if (!TextUtils.isEmpty(numerotelefono)) {
            Toast.makeText(this, numerotelefono, Toast.LENGTH_SHORT).show()
              val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" +  "${numerotelefono.toString()}"))
             startActivity(intent)


        } else {
            Toast.makeText(this, "Mete un número de Teléfono", Toast.LENGTH_SHORT).show()
        }

    }











/*

        val phoneNo: String = texttelefono.getText().toString()
        if (!TextUtils.isEmpty(phoneNo)) {
            val dial = "$phoneNo"
            startActivity(Intent(Intent.ACTION_CALL, Uri.parse(dial)))
        } else {
            Toast.makeText(this, "Enter a phone number", Toast.LENGTH_SHORT).show()
        }
 */















}

