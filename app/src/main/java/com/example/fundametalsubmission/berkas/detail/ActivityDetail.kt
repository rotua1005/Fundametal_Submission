package com.example.fundametalsubmission.berkas.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fundametalsubmission.R
import com.example.fundametalsubmission.databinding.ActivityDetailBinding

class ActivityDetail : AppCompatActivity() {

    companion object{
        const val Trx_Username = "extra_username"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}