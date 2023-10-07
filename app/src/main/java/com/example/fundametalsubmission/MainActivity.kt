package com.example.fundametalsubmission

import android.content.Intent
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
import com.example.fundametalsubmission.berkas.ResUser
import com.example.fundametalsubmission.berkas.detail.ActivityDetail
import com.example.fundametalsubmission.berkas.detail.DetailRes
import com.example.fundametalsubmission.berkas.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

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
        adapter.setOnItemClickCallback(object : ListAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                Intent(this@MainActivity,ActivityDetail::class.java).also {
                    it.putExtra(ActivityDetail.Trx_Username,data.login)
                    startActivity(it)
                }
            }

        })
        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(GithubViewModel::class.java)

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

        viewModel.getSearchUser().observe(this,{
            if (it!=null){
                adapter.setList(it)
                showLoadind(false)
            }
        })

        viewModel.getUsersLiveData().observe(this, { users ->
            if (users != null) {
                adapter.setList(users)
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