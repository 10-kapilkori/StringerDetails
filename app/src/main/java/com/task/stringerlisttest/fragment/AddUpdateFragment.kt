package com.task.stringerlisttest.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.task.stringerlisttest.R
import com.task.stringerlisttest.databinding.FragmentAddUpdateBinding
import com.task.stringerlisttest.model.*

private const val TAG = "AddUpdateFragment"

class AddUpdateFragment : Fragment() {

    private lateinit var viewModel: StringerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAddUpdateBinding.inflate(inflater)
        viewModel = ViewModelProvider(this)[StringerViewModel::class.java]

        val bundle = arguments
        val nameFromBundle = bundle?.getString("name")
        val ageFromBundle = bundle?.getString("age")
        val addressFromBundle = bundle?.getString("address")
        val phoneFromBundle = bundle?.getString("number")
        val passFromBundle = bundle?.getString("pass")
        val startTimeFromBundle = bundle?.getString("start")
        val endTimeFromBundle = bundle?.getString("end")
        val idFromBundle = bundle?.getInt("id")

        with(binding) {
            nameEditName.setText(nameFromBundle)
            ageEditName.setText(ageFromBundle)
            addressEditName.setText(addressFromBundle)
            passwordEditName.setText(passFromBundle)
            phoneEditName.setText(phoneFromBundle)
            startTimeEditName.setText(startTimeFromBundle)
            endTimeEditName.setText(endTimeFromBundle)

            saveBtn.setOnClickListener {
                if (idFromBundle == null) {
                    val name = nameEditName.text.toString()
                    val age = ageEditName.text.toString()
                    val phone = phoneEditName.text.toString()
                    val address = addressEditName.text.toString()
                    val password = passwordEditName.text.toString()
                    val startTime = startTimeEditName.text.toString()
                    val endTime = endTimeEditName.text.toString()
                    val insertedBy = insertedByEditName.text.toString()

                    if (name.isEmpty() || age.isEmpty() || phone.isEmpty() || address.isEmpty() ||
                        password.isEmpty() || startTime.isEmpty() || endTime.isEmpty() || insertedBy.isEmpty()
                    ) {
                        Toast.makeText(
                            inflater.context,
                            "All fields are required",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@setOnClickListener
                    }

                    viewModel =
                        ViewModelProvider(this@AddUpdateFragment)[StringerViewModel::class.java]

                    viewModel.successMutableLiveData.observe(viewLifecycleOwner) {
                        Log.i(TAG, "onCreateView: $it")
                    }

                    viewModel.failureMutableLiveData.observe(viewLifecycleOwner) {
                        Log.e(TAG, "onCreateView: $it")
                    }

                    viewModel.addStringer(
                        StringerRequestModel(
                            address,
                            age.toInt(),
                            endTime,
                            name,
                            phone,
                            startTime,
                            "1",
                            password,
                            insertedBy
                        )
                    )

                    Toast.makeText(inflater.context, "Adding Files Not Working", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    viewModel.successMutableLiveData.observe(viewLifecycleOwner) {
                        addUpdatePb.visibility = View.INVISIBLE
                        saveBtn.visibility = View.VISIBLE
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, StringersFragment())
                            .commit()

                        Toast.makeText(
                            inflater.context, "Stringer Updated",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    val startTime = startTimeEditName.text.toString().toDouble()
                    val endTime = startTimeEditName.text.toString().toDouble()

                    if (startTime !in ((0.00)..(24.00))) {
                        startTimeEditName.error = "Invalid Time"
                        startTimeEditName.requestFocus()

                        return@setOnClickListener
                    }

                    if (endTime !in ((0.00)..(24.00))) {
                        endTimeEditName.error = "Invalid Time"
                        endTimeEditName.requestFocus()
                        return@setOnClickListener
                    }

                    addUpdatePb.visibility = View.VISIBLE
                    saveBtn.visibility = View.INVISIBLE

                    viewModel.updateStringer(
                        StringerRequestModel2(
                            addressEditName.text.toString(),
                            ageEditName.text.toString().toInt(),
                            endTimeEditName.text.toString(),
                            nameEditName.text.toString(),
                            phoneEditName.text.toString(),
                            startTimeEditName.text.toString(),
                            idFromBundle,
                            passwordEditName.text.toString(),
                            insertedByEditName.text.toString()
                        )
                    )
                }
            }
        }

        return binding.root
    }
}