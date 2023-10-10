package com.example.fundametalsubmission

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fundametalsubmission.berkas.ListAdapter
import com.example.fundametalsubmission.berkas.model.GithubViewModel
import com.example.fundametalsubmission.databinding.ActivityMainBinding
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.fundametalsubmission.berkas.ResUser
import com.example.fundametalsubmission.berkas.detail.ActivityDetail
import com.example.fundametalsubmission.berkas.detail.DetailRes
import com.example.fundametalsubmission.berkas.model.User
import com.example.fundametalsubmission.berkas.saveFavorite.FavoriteActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: GithubViewModel
    private lateinit var adapter: ListAdapter
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        adapter = ListAdapter()
        adapter.notifyDataSetChanged()
        adapter.setOnItemClickCallback(object : ListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                Intent(this@MainActivity, ActivityDetail::class.java).also {
                    it.putExtra(ActivityDetail.Trx_Username, data.login)
                    it.putExtra(ActivityDetail.EXTRA_ID, data.id)
                    it.putExtra(ActivityDetail.Trx_Url, data.avatar_url)
                    startActivity(it)
                }
            }
        })

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(GithubViewModel::class.java)

        binding.apply {
            ryUser.layoutManager = LinearLayoutManager(this@MainActivity)
            ryUser.setHasFixedSize(true)
            ryUser.adapter = adapter

            viewModel.setSearchUsers("q")

            btnSearch.setOnClickListener {
                searchUser()
            }

            editQuery.setOnKeyListener { view, i, keyEvent ->
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    searchUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }

        viewModel.getSearchUser().observe(this, {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            }
        })

        viewModel.getUsersLiveData().observe(this, { users ->
            if (users != null) {
                adapter.setList(users)
                showLoading(false)
            }
        })


        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val isDarkModeEnabled = sharedPreferences.getBoolean("isDarkModeEnabled", false)
        if (isDarkModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun searchUser() {
        binding.apply {
            val query = editQuery.text.toString()
            if (query.isEmpty()) return
            showLoading(true)
            viewModel.setSearchUsers(query)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.proses.visibility = View.VISIBLE
        } else {
            binding.proses.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite_menu -> {
                Toast.makeText(this, "Menu Favorite diklik", Toast.LENGTH_SHORT).show()
                Intent(this, FavoriteActivity::class.java).also {
                    startActivity(it)
                }
            }
            R.id.light -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

                sharedPreferences.edit().putBoolean("isDarkModeEnabled", false).apply()
                return true
            }
            R.id.dark -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

                sharedPreferences.edit().putBoolean("isDarkModeEnabled", true).apply()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }
}