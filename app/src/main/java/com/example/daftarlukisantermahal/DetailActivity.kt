package com.example.daftarlukisantermahal

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var detailImage: ImageView
    private lateinit var detailJudul: TextView
    private lateinit var detailDescription: TextView
    private lateinit var detailYear: TextView
    private lateinit var detailAuthor: TextView
    private lateinit var detailStory: TextView
    private lateinit var dataUrl: String
    private lateinit var buttonDetailData: Button

    companion object {
        const val EXTRA_GALLERY = "extra_gallery"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailImage = findViewById(R.id.image_data).
        detailJudul = findViewById(R.id.txt_judul)
        detailDescription = findViewById(R.id.txt_description)
        detailYear = findViewById(R.id.txt_year)
        detailAuthor = findViewById(R.id.txt_author)
        detailStory = findViewById(R.id.txt_story)
        buttonDetailData = findViewById(R.id.action_share)
        buttonDetailData.setOnClickListener(this)

        val gallery = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Gallery>(EXTRA_GALLERY, Gallery::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Gallery>(EXTRA_GALLERY)
        }

        if (gallery != null) {
            detailImage.setImageResource(gallery.photo)
            detailJudul.text = gallery.name
            val descriptionTv = "Deskripsi: \n${gallery.description.toString()}"
            detailDescription.text = descriptionTv
            detailYear.text = gallery.year
            detailAuthor.text = gallery.author
            val storyTv = "Story: \n${gallery.story.toString()}"
            detailStory.text = storyTv
            dataUrl = gallery.url
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.action_share -> {
                val moveToUrl = Intent(Intent.ACTION_VIEW, Uri.parse(dataUrl))
                startActivity(moveToUrl)
            }
        }
    }
}