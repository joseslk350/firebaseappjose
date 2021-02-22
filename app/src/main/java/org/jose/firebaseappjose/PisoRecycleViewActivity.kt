package org.jose.firebaseappjose


import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class PisoRecycleViewActivity : AppCompatActivity() {
    lateinit var pisoList: MutableList<Piso>
    private var bAdapter: PisoAdapter? = null
    private var reciclerView: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_piso_recycle_view)

        pisoList = (application as Aplicacion).pisoList

        bAdapter = PisoAdapter(pisoList, this)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        this.reciclerView = findViewById(R.id.piso_rv)
        reciclerView!!.layoutManager = mLayoutManager
        reciclerView!!.itemAnimator = DefaultItemAnimator()
        reciclerView!!.adapter = bAdapter

        preparePisosData()
    }

    override fun onResume() {
        super.onResume()
        bAdapter!!.notifyDataSetChanged()
        Log.d("app:RecyclerView", "onResume")
    }

    private fun preparePisosData() {
        bAdapter!!.notifyDataSetChanged()
    }




}