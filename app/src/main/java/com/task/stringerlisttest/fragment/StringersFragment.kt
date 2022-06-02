package com.task.stringerlisttest.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.stringerlisttest.R
import com.task.stringerlisttest.adapter.ListAdapter
import com.task.stringerlisttest.databinding.FragmentStringersBinding
import com.task.stringerlisttest.model.StringerModel
import com.task.stringerlisttest.model.StringerViewModel

private const val TAG = "StringersFragment"

class StringersFragment : Fragment(), ListAdapter.OnClickEvent {

    lateinit var viewModel: StringerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStringersBinding.inflate(inflater)
        viewModel = ViewModelProvider(this)[StringerViewModel::class.java]

        val list = ArrayList<StringerModel>()
        val adapter = ListAdapter(list, this)
        binding.listRv.layoutManager = LinearLayoutManager(inflater.context)
        binding.listRv.adapter = adapter

        viewModel.listMutableLiveData.observe(viewLifecycleOwner) {
            Log.i(TAG, "onCreateView: $it")
            binding.listProgressBar.visibility = View.GONE
            adapter.updatedList(it)
        }

        viewModel.errorMutableLiveData.observe(viewLifecycleOwner) {
            Log.e(TAG, "onCreateView: $it")
            binding.listProgressBar.visibility = View.GONE
        }

        viewModel.getList()
        return binding.root
    }

    override fun onClicked(list: List<StringerModel>, position: Int) {
        val trans = parentFragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putString("name", list[position].name)
        bundle.putString("age", list[position].age)
        bundle.putString("address", list[position].address)
        bundle.putString("pass", list[position].password)
        bundle.putString("number", list[position].phoneNumber)
        bundle.putString("start", list[position].startTiming)
        bundle.putString("end", list[position].closeTiming)
        bundle.putInt("id", list[position].stringerId)
        val frag = AddUpdateFragment()
        frag.arguments = bundle
        trans.replace(R.id.fragment_container, frag)
        trans.commit()
    }
}