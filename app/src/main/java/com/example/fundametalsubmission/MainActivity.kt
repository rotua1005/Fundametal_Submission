package com.example.fundametalsubmission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fundametalsubmission.berkas.ListAdapter
import com.example.fundametalsubmission.berkas.model.GithubViewModel
import com.example.fundametalsubmission.databinding.ActivityMainBinding
import android.view.KeyEvent
import android.view.View

class MainActivity : AppCompatActivity() {
    private lateinit var  binding : ActivityMainBinding
    private  lateinit var viewModel: GithubViewModel
    private  lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ListAdapter()
        adapter.notifyDataSetChanged()
        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(GithubViewModel::class.java)

        binding.apply {
            ryUser.layoutManager = LinearLayoutManager(this@MainActivity)
            ryUser.setHasFixedSize(true)
            ryUser.adapter = adapter

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
        viewModel.getSearchUser().observe(this,{
            if (it!=null){
                adapter.setList(it)
            showLoadind(false)
        }
        })
    }


    private  fun searchUser(){
        binding.apply {
            val query = editQuery.text.toString()
            if(query.isEmpty()) return
            showLoadind(true)
            viewModel.setSearchUsers(query)
        }
    }

    private fun showLoadind(state: Boolean){
        if(state){
            binding.proses.visibility = View.VISIBLE
        }else{
            binding.proses.visibility = View.GONE
        }
    }
}