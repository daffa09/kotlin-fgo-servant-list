package com.learn.fgo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.learn.fgo.databinding.ItemRowCharacterBinding

class ListServantAdapter(private val listServant: ArrayList<Servant>) : RecyclerView.Adapter<ListServantAdapter.ListViewHolder>() {

    override fun getItemCount(): Int = listServant.size
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemRowCharacterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowCharacterBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, classServant, description, photo) = listServant[position]
        Glide.with(holder.itemView.context).load(photo).into(holder.binding.imgItemPhoto)
        holder.binding.tvItemName.text = name
        holder.binding.tvItemClassName.text = "(${classServant})"

        val descriptionWord = description.split(" ")
        val limitedDescription = if (descriptionWord.size > 15) {
            descriptionWord.take(15).joinToString(" ") + "..."
        } else {
            description
        }
        holder.binding.tvItemDescription.text = limitedDescription

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listServant[holder.adapterPosition])
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Servant)
    }
}
