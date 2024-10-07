package com.learn.fgo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide

class AboutActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_PHOTO = "extra_photo"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "About Me"

        val tvName: TextView = findViewById(R.id.tv_name)
        val tvEmail: TextView = findViewById(R.id.tv_email)
        val ivPhoto: ImageView = findViewById(R.id.image_about)

        val name = intent.getStringExtra(EXTRA_NAME) ?: "Nama Tidak Ditemukan"
        val email = intent.getStringExtra(EXTRA_EMAIL) ?: "Email Tidak Ditemukan"
        val photoResId = intent.getIntExtra(EXTRA_PHOTO, 0)

        ivPhoto.setImageResource(photoResId)
        tvName.text = name
        tvEmail.text = email

        tvEmail.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "Profile Share")
            }
            startActivity(Intent.createChooser(shareIntent, "Share My Profile"))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.about_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.action_share -> {
                shareProfile()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareProfile() {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Profile Share")
        }
        startActivity(Intent.createChooser(shareIntent, "Share My Profile"))
    }
}