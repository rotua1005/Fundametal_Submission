package com.example.fundametalsubmission.berkas.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.fundametalsubmission.R
import com.example.fundametalsubmission.databinding.ActivityDetailBinding

class ActivityDetail : AppCompatActivity() {

    companion object{
        const val Trx_Username = "extra_username"
    }

    private lateinit var binding: ActivityDetailBinding
    private  lateinit var  viewModel : DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val username= intent.getStringExtra(Trx_Username)
        val bundle = Bundle()
        bundle.putString(Trx_Username,username)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)

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
        val SectionAdapter = SectionAdapter(mCtx = this, supportFragmentManager,bundle)
        binding.apply {
            viewPager.adapter = SectionAdapter
            tab.setupWithViewPager(viewPager)
        }

    }
}