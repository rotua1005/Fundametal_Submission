package com.example.fundametalsubmission.berkas.saveFavorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fundametalsubmission.R
import com.example.fundametalsubmission.berkas.ListAdapter
import com.example.fundametalsubmission.berkas.detail.ActivityDetail
import com.example.fundametalsubmission.berkas.model.User
import com.example.fundametalsubmission.berkas.room.FavoriteUser
import com.example.fundametalsubmission.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityFavoriteBinding
    private  lateinit var adapter: ListAdapter
    private lateinit var viewModel: FavoriteModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ListAdapter()
        adapter.notifyDataSetChanged()


        viewModel = ViewModelProvider(this).get(FavoriteModel::class.java)

        adapter = ListAdapter()
        adapter.notifyDataSetChanged()
        adapter.setOnItemClickCallback(object : ListAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                Intent(this@FavoriteActivity, ActivityDetail::class.java).also {
                    it.putExtra(ActivityDetail.Trx_Username,data.login)
                    it.putExtra(ActivityDetail.EXTRA_ID,data.id)
                    it.putExtra(ActivityDetail.Trx_Url,data.avatar_url)
                    startActivity(it)
                }
            }

        })

        binding.apply {
            ryUser.setHasFixedSize(true)
            ryUser.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            ryUser.adapter = adapter
        }
        viewModel.getFavoriteUser()?.observe(this,{
            if(it!=null){
                val list = mapList(it)
                adapter.setList(list)
        }
        })
    }

    private fun mapList(users: List<FavoriteUser>): ArrayList<User> {
        val listUsers = ArrayList<User>()
        for(user in users){
            val userMapped = User(
                user.login,
                user.id,
                user.avatar_url
            )
            listUsers.add(userMapped)
        }
        return listUsers

    }


}