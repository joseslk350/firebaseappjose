package org.jose.firebaseappjose


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore




class NotificacionActividad : AppCompatActivity() {





    private val db= FirebaseFirestore.getInstance()  //conectado a la base firebase




    lateinit var cliente: Cliente

    lateinit var clientesLista: MutableList<Cliente>

    private var mAdapter: AdapterNotificaciones? = null
    private var bAdapter: AdapterNotificaciones? = null
    private var reciclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notificacionactividad)




        clientesLista = (application as Aplicacion).clientelistaappli




       // rellenar()


        mAdapter = AdapterNotificaciones(clientesLista, this)

        this.reciclerView = findViewById(R.id.recicleviewcliente)
        reciclerView!!.layoutManager = LinearLayoutManager(applicationContext)
        reciclerView!!.itemAnimator = DefaultItemAnimator()

        reciclerView!!.adapter = mAdapter
        prepareClienteData()




    }


    private fun rellenar(){



        db.collection("users")
            .get()
            .addOnSuccessListener { result ->

                for (document in result) {
                    // var S: String="${document.id} => ${document.data.get("nombre")}"
                    var nom: String="${document.data.get("nombre")}"
                    var ema: String="${document.data.get("email")}"
                    var tel: String="${document.data.get("telefono")}"

                    cliente = Cliente()

                    cliente.nombre=nom
                    cliente.emailc=ema
                    cliente.telefono=tel
                    clientesLista.add(cliente)


                }
            }
            .addOnFailureListener { exception ->

                Toast.makeText(this,"Error getting documents: ",Toast.LENGTH_SHORT).show()




            }





    }






    private fun prepareClienteData() {
        mAdapter!!.notifyDataSetChanged()
    }








}
