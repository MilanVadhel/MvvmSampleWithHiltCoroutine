package com.example.mvvmsamplewithhiltandcoroutine.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmsamplewithhiltandcoroutine.databinding.ItemCharacterBinding
import com.example.mvvmsamplewithhiltandcoroutine.model.Result

class CharacterListAdapter(val onItemClick: (result: Result) -> Unit) :
    RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder>() {

    private val itemList = ArrayList<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun submitList(itemList: ArrayList<Result>) {
        this.itemList.apply {
            clear()
            addAll(itemList)
        }
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int){
        this.itemList.apply {
            removeAt(position)
        }
        notifyItemRemoved(position)
    }

    inner class CharacterViewHolder(private val itemCharacterBinding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(itemCharacterBinding.root) {
        fun bind(result: Result) {
            itemCharacterBinding.character = result
            itemCharacterBinding.cvItem.setOnClickListener {
                onItemClick.invoke(result)
            }
        }
    }
}