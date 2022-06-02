package com.task.stringerlisttest.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.task.stringerlisttest.R
import com.task.stringerlisttest.databinding.ActivityMain2Binding
import com.task.stringerlisttest.databinding.DeleteAlertBoxBinding
import com.task.stringerlisttest.fragment.AddUpdateFragment
import com.task.stringerlisttest.fragment.HomeFragment
import com.task.stringerlisttest.fragment.StringersFragment
import com.task.stringerlisttest.model.StringerResponseModel
import com.task.stringerlisttest.model.StringerViewModel

private const val TAG = "MainActivity2"

class MainActivity2 : AppCompatActivity() {

    lateinit var binding: ActivityMain2Binding
    lateinit var viewModel: StringerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val manager = supportFragmentManager.beginTransaction()
        manager.add(R.id.fragment_container, HomeFragment())
        manager.commit()
    }

    override fun onStart() {
        super.onStart()
        viewModel = ViewModelProvider(this)[StringerViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.stringer_add -> {
                val manager = supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, AddUpdateFragment())
                manager.commit()
                return true
            }
            R.id.stringer_list -> {
                val manager = supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, StringersFragment())
                manager.commit()
                return true
            }
            R.id.stringer_delete -> {
                val builder = AlertDialog.Builder(this)
                val view = DeleteAlertBoxBinding.bind(
                    layoutInflater.inflate(
                        R.layout.delete_alert_box,
                        null
                    )
                )
                builder.setView(view.root)
                val dialog = builder.create()
                dialog.show()

                view.deleteBtn.setOnClickListener {
                    if (view.stringerIdEditName.text.toString().isNotEmpty()) {
                        view.deleteBtn.visibility = View.INVISIBLE
                        view.deletePb.visibility = View.VISIBLE

                        dialog.setCancelable(false)
                        val id = view.stringerIdEditName.text.toString().toInt()
                        viewModel.deleteStringer(StringerResponseModel(id))

                        viewModel.successMutableLiveData.observe(this) {
                            Toast.makeText(this, "Stringer Removed", Toast.LENGTH_SHORT).show()

                            dialog.dismiss()
                            view.deletePb.visibility = View.INVISIBLE
                            view.deleteBtn.visibility = View.VISIBLE

                            supportFragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, StringersFragment())
                                .commit()
                        }

                        viewModel.failureMutableLiveData.observe(this) {
                            Log.e(TAG, "onOptionsItemSelected: $it")
                            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                return true
            }
        }
        return false
    }
}