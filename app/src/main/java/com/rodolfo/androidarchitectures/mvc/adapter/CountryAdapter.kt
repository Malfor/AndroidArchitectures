package com.rodolfo.androidarchitectures.mvc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.rodolfo.androidarchitectures.databinding.RowLayoutBinding

class CountryAdapter : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    private lateinit var listValue: List<String>

    class ViewHolder(binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val textName : TextView = binding.listText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val value = listValue[position]
        holder.textName.text = value
        holder.textName.setOnClickListener {
            Toast.makeText(
                holder.textName.context,
                "You clicked ${listValue[position]}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int = listValue.size

    fun setList(values: List<String>) {
        listValue = values
        notifyDataSetChanged()
    }
}