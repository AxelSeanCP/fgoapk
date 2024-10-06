package com.dicoding.fgo_servant_info

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {
    private lateinit var rvServants: RecyclerView
    private val list = ArrayList<Servant>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val topAppBar: MaterialToolbar = findViewById(R.id.topAppBar)
        setSupportActionBar(topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val aboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(aboutIntent)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}