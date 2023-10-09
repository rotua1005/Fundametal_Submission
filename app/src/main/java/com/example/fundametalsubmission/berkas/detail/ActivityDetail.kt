package com.example.fundametalsubmission.berkas.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.fundametalsubmission.R
import com.example.fundametalsubmission.databinding.ActivityDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityDetail : AppCompatActivity() {

    companion object{
        const val Trx_Username = "extra_username"
        const val EXTRA_ID = "extra_id"
        const val Trx_Url = "extra_url"
    }

    private lateinit var binding: ActivityDetailBinding
    private  lateinit var  viewModel : DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val username= intent.getStringExtra(Trx_Username)
        val id = intent.getIntExtra(EXTRA_ID,0)
        val avatarUrl = intent.getStringExtra(Trx_Url)
        val bundle = Bundle()
        bundle.putString(Trx_Username,username)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        viewModel.setUserDetail(username.toString())
        viewModel.getUserDetail().observe(this,{
            if(it != null){
                binding.apply {
                    lsName.text = it.name
                    lsUsername.text = it.login
                    lsFollowers.text = "${it.follower} Followers"
                    lsFollowing.text = "${it.following}Following"
                    Glide.with(this@ActivityDetail)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(kodeProfile)
                }
            }
        })

        var isChecked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.checkUser(id)
            withContext(Dispatchers.Main){
                if(count != null){
                    if(count >0 ){
                        binding.toggleFavorite.isChecked = true
                        isChecked = true
                    }else{
                        binding.toggleFavorite.isChecked = false
                        isChecked = false
                    }
                }
            }
        }

        binding.toggleFavorite.setOnClickListener {
            isChecked = !isChecked
            if(isChecked){
                viewModel.addToFavorite(username.toString(),id, avatarUrl.toString())
            }else{
                viewModel.removeFromFavorite(id)
            }
            binding.toggleFavorite.isChecked = isChecked
        }


        val SectionAdapter = SectionAdapter(mCtx = this, supportFragmentManager,bundle)
        binding.apply {
            viewPager.adapter = SectionAdapter
            tab.setupWithViewPager(viewPager)
        }

    }
}