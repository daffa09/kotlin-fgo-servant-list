package com.learn.fgo

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learn.fgo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var rvCharacter: RecyclerView
    private val list = ArrayList<Servant>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvCharacter = findViewById(R.id.rv_character)
        rvCharacter.setHasFixedSize(true)

        list.addAll(getListHeroes())
        showRecyclerList()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.about_list -> {
                val intent = Intent(this, AboutActivity::class.java).apply {
                    putExtra(AboutActivity.EXTRA_NAME, "Daffa Fathan")
                    putExtra(AboutActivity.EXTRA_EMAIL, "daffa.fathan9@gmail.com")
                    putExtra(AboutActivity.EXTRA_PHOTO, R.drawable.profile)
                }
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListHeroes(): ArrayList<Servant>{
        val dataName = resources.getStringArray(R.array.data_name)
        val dataClassServant = resources.getStringArray(R.array.data_class_servant)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listHero = ArrayList<Servant>()
        for (i in dataName.indices){
            val hero = Servant(dataName[i], dataClassServant[i], dataDescription[i], dataPhoto[i])
            listHero.add(hero)
        }
        return listHero
    }

    private fun showRecyclerList() {
        rvCharacter.layoutManager = LinearLayoutManager(this)
        val listServantAdapter = ListServantAdapter(list)
        rvCharacter.adapter = listServantAdapter

        listServantAdapter.setOnItemClickCallback(object: ListServantAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Servant){
                showSelectedHero(data)
            }
        })
    }

    private fun showSelectedHero(servant: Servant){
        val character = Servant(servant.name, servant.classServant, servant.description, servant.photo)
        val intent = Intent(this@MainActivity, DetailServantActivity::class.java).apply {
            putExtra(DetailServantActivity.EXTRA_CHARACTER, character)
        }
        startActivity(intent)
    }
}