package org.jose.firebaseappjose

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore


class Addactividad : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()  //conectado a la base firebase


    lateinit var botonoff: Button

    lateinit var buttonguardar: Button
    lateinit var button2recuperar: Button
    lateinit var button3eliminar: Button

    lateinit var buttondelante: Button
    lateinit var buttondetras: Button

    lateinit var r1 : RadioButton
    lateinit var r2 : RadioButton
    lateinit var r3 : RadioButton
    lateinit var r4 : RadioButton



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
    var n: Int = 1
    var ncontador: Int = 0

   lateinit var pisoadd: Piso

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_actividad)


        botonoff = findViewById<Button>(R.id.botcerrar)

        buttonguardar = findViewById<Button>(R.id.buttonguardar)
        button2recuperar = findViewById<Button>(R.id.button2recuperar)
        button3eliminar = findViewById<Button>(R.id.button3eliminar)

        buttondelante = findViewById<Button>(R.id.buttondelante)
        buttondetras = findViewById<Button>(R.id.buttonatras)


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



        db.collection("pisos")
            .get()
            .addOnSuccessListener { result ->

                for (document in result) {
                    ncontador++

                }




                Toast.makeText(
                    this, ncontador.toString(),
                    Toast.LENGTH_SHORT
                ).show()






                buttonguardar.setOnClickListener {
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

                    ncontador++




                    val pisoadd = Piso().apply {

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
                    }

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




                }

                button2recuperar.setOnClickListener {

                    db.collection("pisos").document(ncontador.toString()).get()
                        .addOnSuccessListener {

                            textViewdireccion.setText(it.get("nombre") as String?)

                            textviewprecio.setText(it.get("telefono") as String?)

                        }


                }

                buttondelante.setOnClickListener {

                    db.collection("pisos").document(n.toString()).get().addOnSuccessListener {

                        textViewdireccion.setText(it.get("nombre") as String?)

                        textviewprecio.setText(it.get("telefono") as String?)

                    }
                    n++


                }

                buttondetras.setOnClickListener {


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


                }


                button3eliminar.setOnClickListener {

                   // db.collection("pisos").document(ncontador.toString()).delete()



                }


/*  un for para recorrer

        for (postSnapshot in dataSnapshot.getChildren()) {
            val comida: Comida = dataSnapshot.getValue(Comida::class.java)
            comida.setId(dataSnapshot.getKey())
            if (!comidas.contains(comida)) {
                comidas.add(comida)
            }
        }

*/


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




                botonoff.setOnClickListener {

                    onBackPressed() //vuelve a la anterior como si presionas boton de volver
                }


            }


    }
}



/*
si no tenemos tablas creadas.





*/
