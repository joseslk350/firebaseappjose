package org.jose.firebaseappjose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore


class Addactividad : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()  //conectado a la base firebase


    lateinit var botonoff: Button

    lateinit var buttonguardar: Button
    lateinit var button2recuperar: Button
    lateinit var button3cancelar: Button



    lateinit var r1 : RadioButton
    lateinit var r2 : RadioButton
    lateinit var r3 : RadioButton
    lateinit var r4 : RadioButton


    //comennto

    lateinit var textViewdireccion: TextView
    lateinit var textviewprecio: TextView
    lateinit var textURL: TextView
    lateinit var textviewhabit: TextView
    lateinit var textviewbano: TextView
    lateinit var textviewaltura: TextView
    lateinit var textviewsupp: TextView
    lateinit var textViewtipo: TextView

lateinit var radiogrupo: RadioGroup



    lateinit var cliente: Cliente
    val clientesLista = mutableListOf<Cliente>()
    lateinit var contador: String
    var n: Int = 0
    var ncontador: Int = 0
    var numero:Int=0
    var hayconexion: Boolean =false


    lateinit var pisoadd: Piso

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_actividad)




        buttonguardar = findViewById<Button>(R.id.buttonguardar)

        button3cancelar = findViewById<Button>(R.id.button3cerrar)




        textViewdireccion = findViewById<TextView>(R.id.textViewdireccion)
        textviewprecio = findViewById<TextView>(R.id.textviewprecio)
        textURL = findViewById<TextView>(R.id.textURL)
        textviewhabit = findViewById<TextView>(R.id.textviewhabit)
        textviewbano = findViewById<TextView>(R.id.textviewbano)
        textviewaltura = findViewById<TextView>(R.id.textviewaltura)
        textviewsupp = findViewById<TextView>(R.id.textviewsupp)
        textViewtipo = findViewById<TextView>(R.id.textViewtipo)


        radiogrupo=findViewById(R.id.radiogrupo)

        r1=findViewById<RadioButton>(R.id.buttonVPO)
        r2=findViewById<RadioButton>(R.id.buttonLIBRE)
        r3=findViewById<RadioButton>(R.id.buttonCHALET)
        r4=findViewById<RadioButton>(R.id.buttonTERRENO)

        r1.isChecked=true
        textViewtipo.setText("VPO")


        radiogrupo.setOnCheckedChangeListener { group, checkedId ->


            if (r1.isChecked){
                Toast.makeText(this,"VPO activo",Toast.LENGTH_SHORT).show()
                textViewtipo.setText("VPO")}

            if (r2.isChecked){textViewtipo.setText("LIBRE")
                Toast.makeText(this,"LIBRE activo",Toast.LENGTH_SHORT).show()}

            if (r3.isChecked){textViewtipo.setText("CHALET")
                Toast.makeText(this,"CHALET activo",Toast.LENGTH_SHORT).show()}

            if (r4.isChecked){ textViewtipo.setText("TERRENO")
                Toast.makeText(this,"TERRENO activo",Toast.LENGTH_SHORT).show()}



        }


//cuenta numero elementos en la base datos. para añadir el ultimo id.

        contar(hayconexion)



                buttonguardar.setOnClickListener {

                    if (textViewdireccion.text.toString()!="") {

                        db.collection("pisos").document(ncontador.toString()!!).set(

                                hashMapOf(

                                        "nombre" to textViewdireccion.text.toString(),
                                        "precio" to textviewprecio.text.toString(),
                                        "url" to textURL.text.toString(),
                                        "habitacion" to textviewhabit.text.toString(),
                                        "bano" to textviewbano.text.toString(),
                                        "altura" to textviewaltura.text.toString(),
                                        "supp" to textviewsupp.text.toString(),
                                        "tipo" to textViewtipo.text.toString(),



                                        )
                        )


                        val pisoadd = Piso().apply {

                            idpiso=""
                            actualizado=false
                            altura="0"
                            bano="0"
                            url=""
                            habitacion="0"
                            nombre=""
                            precio=""
                            supp=""
                            tipo="VPO"
                            foto=null
                        }//fin apply

                        pisoadd.idpiso=ncontador.toString()
                        pisoadd.altura=textviewaltura.text.toString()
                        pisoadd.bano=textviewbano.text.toString()
                        // pisoadd.url=textURL.text.toString()
                        //NO QUIERO QUE AÑADA FOTOS PORQUE ESCRIBIRA MAL LA URL
                        pisoadd.url=""
                        pisoadd.habitacion=textviewhabit.text.toString()
                        pisoadd.nombre=textViewdireccion.text.toString()
                        pisoadd.precio=textviewprecio.text.toString()
                        pisoadd.supp=textviewsupp.text.toString()
                        pisoadd.tipo=textViewtipo.text.toString()
                        //  pisoadd.foto=null  --> no lo estoy usando .foto en ninguna parte.




                        (application as Aplicacion).pisoList.add(pisoadd)

                        nuevo()
                        contar(hayconexion)

                        Toast.makeText(this, "Has GUARDADO un piso nuevo", Toast.LENGTH_SHORT)
                                .show()



                    }//fin if
                    else
                    {
                        Toast.makeText(this, "El piso tiene que tener alguna dirección para poder guardar",Toast.LENGTH_LONG).show()
                    }//fin else





                }//fin boton guardar






                button3cancelar.setOnClickListener {

                   // db.collection("pisos").document(ncontador.toString()).delete()

                    onBackPressed() //vuelve a la anterior como si presionas boton de volver




                }





/*guardar con sharedpreferences.


      val preferencia= getSharedPreferences("jose", Context.MODE_PRIVATE).edit()
        preferencia.putString("clave2", emailvariabl)
        preferencia.putString("3", providervariabl)
        preferencia.apply()

        crea var tipo getshared (clave, Contex.modo) y la ponemos en modo edicion. edit()
        añadimos con clave-valor. y hacemos un apply para guardar

        preferencia.apply()
        preferencia.clear()  para borrar.


        */






            }//fin oncreate






    private fun contar(contar: Boolean) {
        db.collection("pisos")
                .get()
                .addOnSuccessListener { result ->

                    ncontador=  result.size()



                    Toast.makeText(
                            this, ncontador.toString(),
                            Toast.LENGTH_SHORT
                    ).show()

                }
    }


    private fun buscar(numero: String){

        db.collection("pisos").document("$numero").get()
                .addOnSuccessListener { document ->
                    if (document != null) {


                        rellenar(document)

                    } else {
                        Toast.makeText(this, "NO hay ese documento",Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "FALLO en la recuperación de documento",Toast.LENGTH_SHORT).show()
                }



    }//fin buscar


    private fun rellenar(it: DocumentSnapshot?) {

        textViewdireccion.setText(it?.get("nombre") as String?)
        textviewprecio.setText(it?.get("precio") as String?)
        textviewhabit.setText(it?.get("habitacion") as String?)
        textviewbano.setText(it?.get("bano") as String?)
        textviewaltura.setText(it?.get("altura") as String?)
        textviewsupp.setText(it?.get("supp") as String?)
        textURL.setText(it?.get("url") as String?)
        textViewtipo.setText(it?.get("tipo") as String?)

    }



    private fun nuevo(){
        textviewaltura.text=""
        textviewbano.text=""
        textURL.text=""
        textviewhabit.text=""
        textViewdireccion.text=""
        textviewprecio.text=""
        textviewsupp.text=""
        textViewtipo.text="VPO"
        r1.isChecked=true
        //textViewtipo.setText("VPO")


    }


}



/*
    db.collection("pisos")
                        .get()
                        .addOnSuccessListener { result ->

                            for (document in result) {
                                // var S: String="${document.id} => ${document.data.get("nombre")}"
                                var nom: String = "${document.data.get("nombre")}"
                                var ema: String = "${document.data.get("email")}"
                                var tel: String = "${document.data.get("telefono")}"

                                cliente = Cliente()

                                cliente.nombre = nom
                                cliente.emailc = ema
                                cliente.telefono = tel
                                clientesLista.add(cliente)

                                //Toast.makeText(this,nom,Toast.LENGTH_SHORT).show()
                                //val arr : String = clientesLista[0].nombre.toString()
                                //Toast.makeText(this,clientesLista.size.toString(),Toast.LENGTH_SHORT).show()
                                Toast.makeText(
                                    this, clientesLista[clientesLista.size - 1].nombre.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                        }
                        .addOnFailureListener { exception ->

                            Toast.makeText(this, "Error getting documents: ", Toast.LENGTH_SHORT)
                                .show()


                        }






                botonoff.setOnClickListener {


                    db.collection("pisos")
                            .get()
                            .addOnSuccessListener { result ->

                                rellenar(   result.last() )


                                Toast.makeText(this,
                                        result.last().data.get("precio").toString(),Toast.LENGTH_SHORT).show()
                                result.elementAt(4).data.get("precio").toString()
                                Toast.makeText(this,
                                        result.elementAt(4).data.get("precio").toString(),Toast.LENGTH_SHORT).show()
                            }

                }






OnCompleteListener se llama cuando la tarea finaliza, pero no importando si realizo el proceso exitosa mente o fallo. En el caso de tu programa no fallo por esa razón aparentemente tienen el mismo funcionamiento.

En cambio OnSuccessListener determinas que no hubo ninguna falla al realizar la tarea, generalmente yo lo uso junto con OnFailureListener para determinar otra acción en caso de falla.

  docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
        @Override
        public void onSuccess(DocumentSnapshot documentSnapshot) {
            ...
            ...
        }
    });


  docRef.get().addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            ...
            ...
        }
    });


recoger documentos con condiciones

db.collection("cities")
        .whereEqualTo("capital", true)
        .get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                Log.d(TAG, "${document.id} => ${document.data}")
            }
        }
        .addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents: ", exception)
        }









Eventos para cambios locales
Las operaciones de escritura locales en tu app invocarán agentes de escucha de instantáneas de inmediato. Esto se debe a una función importante llamada “compensación de latencia”. Cuando ejecutes una operación de escritura, los objetos de escucha recibirán una notificación con los datos nuevos antes de que estos se envíen al backend.

Los documentos recuperados tienen una propiedad metadata.hasPendingWrites que indica si el documento tiene cambios locales que todavía no se escribieron en el backend. Puedes usar esta propiedad para determinar el origen de los eventos que recibe tu objeto de escucha de instantáneas:



val docRef = db.collection("cities").document("SF")
docRef.addSnapshotListener { snapshot, e ->
    if (e != null) {
        Log.w(TAG, "Listen failed.", e)
        return@addSnapshotListener
    }

    val source = if (snapshot != null && snapshot.metadata.hasPendingWrites())
        "Local"
    else
        "Server"

    if (snapshot != null && snapshot.exists()) {
        Log.d(TAG, "$source data: ${snapshot.data}")
    } else {
        Log.d(TAG, "$source data: null")
    }
}










db.disableNetwork().addOnCompleteListener {
    // Do offline things
    // ...
}
Usa el siguiente método para volver a habilitar el acceso a la red:



db.enableNetwork().addOnCompleteListener {
    // Do online things
    // ...
}

















*/
