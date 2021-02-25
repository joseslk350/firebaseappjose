package org.jose.firebaseappjose

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.net.URL
import java.util.*


class Aplicacion : Application(){

   //
var urldeimagen: String=""
    var mas: String="?"
    var urlImage:URL = URL("https://static.wixstatic.com/media/b25591_99f5cd6c9b6b4899aef6c2e486e291c7~mv2_d_3000_2930_s_4_2.jpg/v1/fill/w_655,h_640,al_c,q_85,usm_0.66_1.00_0.01/b25591_99f5cd6c9b6b4899aef6c2e486e291c7~mv2_d_3000_2930_s_4_2.webp?" +
            "auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
     var clientelistaappli: MutableList<Cliente> = mutableListOf()

    var pisoList: MutableList<Piso> = mutableListOf()
    val bitmap : Bitmap?=null

    override fun onCreate() {




        super.onCreate()
        Log.d("app:Aplication","onCreate")

      //  FirebaseApp.initializeApp(this)
        val db= FirebaseFirestore.getInstance()
     //   loadData()


        db.collection("users")
            .get()
            .addOnSuccessListener { result ->

                for (document in result) {
                    // var S: String="${document.id} => ${document.data.get("nombre")}"
                    var nom: String="${document.data.get("nombre")}"
                    var ema: String="${document.data.get("email")}"
                    var tel: String="${document.data.get("telefono")}"
                    var men: String="${document.data.get("mensaje")}"

                    var cliente = Cliente()

                    cliente.nombre=nom
                    cliente.emailc=ema
                    cliente.telefono=tel
                    cliente.mensaje=men
                    clientelistaappli.add(cliente)


                }
            }
            .addOnFailureListener { exception ->

                Toast.makeText(this,"Error cargando documentos: ", Toast.LENGTH_SHORT).show()

            }




        db.collection("pisos")
            .get()
            .addOnSuccessListener { result ->

                for (document in result) {
                    // var S: String="${document.id} => ${document.data.get("nombre")}"
                    var nom: String="${document.data.get("nombre")}"
                    var pre: String="${document.data.get("precio")}"
                    var url: String="${document.data.get("url")}"
                    var alt: String="${document.data.get("altura")}"
                    var ban: String="${document.data.get("bano")}"
                    var hab: String="${document.data.get("habitacion")}"
                    var sup: String="${document.data.get("supp")}"
                    var tip: String="${document.data.get("tipo")}"


                    var piso = Piso()

                    piso.nombre=nom
                    piso.precio=pre
                    piso.url=url
                    piso.altura=alt
                    piso.bano=ban
                    piso.habitacion=hab
                    piso.supp=sup
                    piso.tipo=tip
                    urldeimagen= piso.url!!

               /* HECHO LA CARGA IMAGEN CON LA BIBLIOTECA DE PICASSO EN
               PISODETALLE Y PISORECICLEVIEW

               esto seria con asincronas pero va mas rapido "el codigo" que la descarga de imagenes
               y no fija el bitmap a la variable. REVISAR motivo. funciona cambiando esto por la forma
               de hacerlo con picasso.

                    urlImage=URL(urldeimagen + "auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
                    // async task to get / download bitmap from url
                    val result: Deferred<Bitmap?> = GlobalScope.async {
                        urlImage.toBitmap()
                    }

                    GlobalScope.launch(Dispatchers.Main) {
                        // get the downloaded bitmap
                        val bitmap : Bitmap? = result.await()


                        // if downloaded then saved it to internal storage
                        bitmap?.apply {
                            // get saved bitmap internal storage uri
                           // val savedUri : Uri? = saveToInternalStorage(context)
                           piso.foto=bitmap

                            // display saved bitmap to image view from internal storage
                           // imageViewfoto?.setImageURI(savedUri)

                            // show bitmap saved uri in text view
                           // tvSaved?.text = savedUri.toString()
                        }


                    }
                    */



                    pisoList.add(piso)

                }
            }
            .addOnFailureListener { exception ->

                Toast.makeText(this,"Error cargando documentos: ", Toast.LENGTH_SHORT).show()




            }















    }

    fun findDataByTitle(nom1: String):Piso{
        for (item in pisoList){
            if (item.nombre.toString() == nom1){
                return item
            }
        }
        return Piso()
    }


    // extension function to get / download bitmap from url
    fun URL.toBitmap(): Bitmap?{
        return try {
            BitmapFactory.decodeStream(openStream())
        }catch (e: IOException){
            null
        }
    }




    // extension function to save an image to internal storage
    fun Bitmap.saveToInternalStorage(context : Context):Uri?{
        // get the context wrapper instance
        val wrapper = ContextWrapper(context)

        // initializing a new file
        // bellow line return a directory in internal storage
        var file = wrapper.getDir("images", Context.MODE_PRIVATE)

        // create a file to save the image
        file = File(file, "${UUID.randomUUID()}.jpg")

        return try {
            // get the file output stream
            val stream: OutputStream = FileOutputStream(file)

            // compress bitmap
            compress(Bitmap.CompressFormat.JPEG, 100, stream)

            // flush the stream
            stream.flush()

            // close stream
            stream.close()

            // return the saved image uri
            Uri.parse(file.absolutePath)
        } catch (e: IOException){ // catch the exception
            e.printStackTrace()
            null
        }

    }









    }



