package com.dicoding.fgo_servant_info

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvServants: RecyclerView
    private val list = ArrayList<Servant>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvServants = findViewById(R.id.rv_servants)
        rvServants.setHasFixedSize(true)

        list.addAll(getListServants())
        showRecyclerlist()
    }

    private fun getListServants(): ArrayList<Servant> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataNoblePhantasm = resources.getStringArray(R.array.data_noble_phantasm)
        val dataNpDescription = resources.getStringArray(R.array.data_np_description)
        val dataPhotoIcon = resources.obtainTypedArray(R.array.data_photo_icon)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)

        val listServant = ArrayList<Servant>()
        for (i in dataName.indices) {
            val servant = Servant(
                dataName[i],
                dataDescription[i],
                dataNoblePhantasm[i],
                dataNpDescription[i],
                dataPhotoIcon.getResourceId(i, -1),
                dataPhoto.getResourceId(i, -1)
            )
            listServant.add(servant)
        }

        return listServant
    }

    private fun showRecyclerlist() {
        rvServants.layoutManager = LinearLayoutManager(this)
        val listServantAdapter = ListServantAdapter(list)
        rvServants.adapter = listServantAdapter
    }
}