package com.example.fundametalsubmission.berkas.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fundametalsubmission.R
import com.example.fundametalsubmission.databinding.FragmentFrFollowerBinding


class FrFollowing : Fragment(R.layout.fragment_fr_follower){


        private var fragmentBinding: FragmentFrFollowerBinding? = null

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            fragmentBinding = FragmentFrFollowerBinding.bind(view)
        }
    }
