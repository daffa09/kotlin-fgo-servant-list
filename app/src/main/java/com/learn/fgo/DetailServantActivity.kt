package com.learn.fgo

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailServantActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_CHARACTER = "extra_character"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_servant)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val ivPhoto: ImageView = findViewById(R.id.image_photo)
        val tvName: TextView = findViewById(R.id.tv_name)
        val tvDescription: TextView = findViewById(R.id.tv_description)
        val tvClassServant: TextView = findViewById(R.id.tv_class_servant)

        val servant = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Servant>(EXTRA_CHARACTER, Servant::class.java)
        } else {
            @Suppress("DEPRICATION")
            intent.getParcelableExtra<Servant>(EXTRA_CHARACTER)
        }

        if (servant != null) {
            Glide.with(this).load(servant.photo).into(ivPhoto)
            tvName.text = servant.name
            tvClassServant.text = "(${servant.classServant})"
            tvDescription.text = servant.description

            supportActionBar?.title = servant.name
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}