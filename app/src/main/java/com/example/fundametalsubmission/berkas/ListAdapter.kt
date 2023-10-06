package com.example.fundametalsubmission.berkas

<<<<<<< HEAD
=======
import android.graphics.drawable.Drawable
>>>>>>> 98e431471e114ec9cf3b598dd07ac66e44f81c05
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.fundametalsubmission.berkas.model.User
import com.example.fundametalsubmission.databinding.ItemListBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.UserViewHolder>() {

    private  val list = ArrayList<User>()
<<<<<<< HEAD
    fun setList(users: MutableList<User>){
=======
    fun setList(users : ArrayList<User>){
>>>>>>> 98e431471e114ec9cf3b598dd07ac66e44f81c05
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user : User){
            binding.apply {
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(kodeUser)
                lsUsername.text = user.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
       holder.bind(list[position])
    }
}