package com.jony.mazaadytask.presentation.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jony.mazaadytask.data.model.response.GetOptionsChildResponse
import com.jony.mazaadytask.data.model.response.PropertiesCatResponse
import com.jony.mazaadytask.databinding.Card1Binding
import com.jony.mazaadytask.databinding.DropdownItemBinding
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PropertiesAdapter @Inject constructor(@ApplicationContext val context: Context):
    RecyclerView.Adapter<PropertiesAdapter.ViewHolder>() {
    private lateinit var binding : DropdownItemBinding
    private var channelsList:ArrayList<GetOptionsChildResponse.Data.Option> = ArrayList()

    inner class ViewHolder (binding: DropdownItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GetOptionsChildResponse.Data.Option) {
            binding.apply {
                text1.text = item.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DropdownItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(channelsList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return channelsList.size
    }

    fun setData(list: List<GetOptionsChildResponse.Data.Option>) {
        if (this.channelsList.isNotEmpty())
            this.channelsList.clear()
        this.channelsList.addAll(list)
        notifyDataSetChanged()
    }
}