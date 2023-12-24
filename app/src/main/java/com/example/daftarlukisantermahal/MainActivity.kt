package com.example.daftarlukisantermahal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvGallerys: RecyclerView
    private val list = ArrayList<Gallery>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvGallerys = findViewById(R.id.rv_gallery)
        rvGallerys.setHasFixedSize(true)

        list.addAll(getListGalleries())
        showRecyclerList()
    }

    private fun getListGalleries(): ArrayList<Gallery> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataYear = resources.getStringArray(R.array.data_year)
        val dataAuthor = resources.getStringArray(R.array.data_author)
        val dataStory = resources.getStringArray(R.array.data_story)
        val dataUrl = resources.getStringArray(R.array.data_url)

        val listGallery = ArrayList<Gallery>()
        for (i in dataName.indices) {
            val gallery = Gallery(dataName[i], dataDescription[i],  dataPhoto.getResourceId(i, -1), dataStory[i], dataAuthor[i], dataYear[i], dataUrl[i])
            listGallery.add(gallery)
        }
        return listGallery
    }

    private fun showRecyclerList() {
        rvGallerys.layoutManager = LinearLayoutManager(this)
        val listGalleryAdapter = ListGalleryAdapter(list)
        rvGallerys.adapter = listGalleryAdapter

        listGalleryAdapter.setOnItemClickCallback(object : ListGalleryAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Gallery) {
                showSelectedGallery(data)
                val moveIntent = Intent(this@MainActivity, DetailActivity::class.java)
                moveIntent.putExtra(DetailActivity.EXTRA_GALLERY, data)
                startActivity(moveIntent)
            }
        })
    }

    private fun showSelectedGallery(gallery: Gallery) {
        Toast.makeText(this, "Kamu memilih " + gallery.name, Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                openAboutPageActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openAboutPageActivity() {
        val moveProfile = Intent(this@MainActivity, AboutPageActivity::class.java)
        startActivity(moveProfile)
    }
}