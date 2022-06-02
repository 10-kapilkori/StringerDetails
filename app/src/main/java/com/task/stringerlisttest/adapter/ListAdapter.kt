package com.task.stringerlisttest.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.stringerlisttest.R
import com.task.stringerlisttest.databinding.ListAdapterBinding
import com.task.stringerlisttest.model.StringerModel

private const val TAG = "ListAdapter"

class ListAdapter(private var list: List<StringerModel>, private var listener: OnClickEvent) :
    RecyclerView.Adapter<ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListAdapterBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.list_adapter, parent, false)
        )

        return ListViewHolder(binding)
    }

    fun updatedList(list: List<StringerModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder: $list")
        holder.bind(list, position)
        holder.binding.root.setOnClickListener {
            listener.onClicked(list, position)
        }
    }

    override fun getItemCount(): Int = list.size

    interface OnClickEvent {
        fun onClicked(list: List<StringerModel>, position: Int)
    }
}