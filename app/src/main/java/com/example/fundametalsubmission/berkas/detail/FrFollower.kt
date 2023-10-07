package com.example.fundametalsubmission.berkas.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fundametalsubmission.R
import com.example.fundametalsubmission.berkas.ListAdapter
import com.example.fundametalsubmission.databinding.FragmentFrFollowerBinding


class FrFollower : Fragment(R.layout.fragment_fr_follower){

        private var fragmentBinding: FragmentFrFollowerBinding? = null
        private  lateinit var  viewModel: FrFollowerModel
        private  lateinit var  adapter: ListAdapter
        private lateinit var username : String

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            fragmentBinding = FragmentFrFollowerBinding.bind(view)


            val args = arguments
            username = args?.getString(ActivityDetail.Trx_Username).toString()

            adapter = ListAdapter()
            adapter.notifyDataSetChanged()

            fragmentBinding?.apply {
                ryUser.setHasFixedSize(true)
                ryUser.layoutManager = LinearLayoutManager(activity)
                ryUser.adapter = adapter

            }

            showLoadind(true)

            viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(FrFollowerModel::class.java)
            viewModel.setListFollower(username)
            viewModel.getListFollowers().observe(viewLifecycleOwner,{
                if(it!=null){
                    adapter.setList(it)
                    showLoadind(false)
                }
            })

        }

    override  fun onDestroyView(){
        super.onDestroyView()
        fragmentBinding = null
    }

    private fun showLoadind(state: Boolean) {
        fragmentBinding?.let {
            it.proses.visibility = if (state) View.VISIBLE else View.GONE
        }
    }}
