package com.task.stringerlisttest.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.task.stringerlisttest.R
import com.task.stringerlisttest.databinding.ListAdapterBinding
import com.task.stringerlisttest.model.StringerModel

class ListViewHolder(itemView: ListAdapterBinding) : RecyclerView.ViewHolder(itemView.root) {
    val binding = itemView

    @SuppressLint("SetTextI18n")
    fun bind(list: List<StringerModel>, position: Int) {
        with(binding) {
            stringerId.text = "Stringers ID: " + list[position].stringerId.toString()
            name.text = "Name: " + list[position].name
            age.text = "Age: " + list[position].age
            address.text = "Address: " + list[position].address
            number.text = "Phone Number: " + list[position].phoneNumber
            startTime.text = "Starting Time: " + list[position].startTiming
            endTime.text = "Ending Time: " + list[position].closeTiming
            password.text = "Password: " + list[position].password
        }
    }
}