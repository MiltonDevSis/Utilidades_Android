package com.example.retrofit_apirest

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit_apirest.data.adapter.PhoneAdapter
import com.example.retrofit_apirest.data.util.ApiState
import com.example.retrofit_apirest.data.util.showMsg
import com.example.retrofit_apirest.databinding.ActivityMainBinding
import com.example.retrofit_apirest.ui.PhoneViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val phoneViewModel: PhoneViewModel by viewModels()

    @Inject
    lateinit var phoneAdapter: PhoneAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRecyclerView()

        getPhone()
        setPhone()
    }

    private fun setPhone() {
        binding.apply {
            btnSalvar.setOnClickListener {
                if (!TextUtils.isEmpty(edtNome.text.toString()) && !TextUtils.isEmpty(edtNumero.text.toString())) {
                    lifecycleScope.launchWhenStarted {
                        phoneViewModel.setPhone(
                            edtNome.text.toString(),
                            edtNumero.text.toString().toLong()
                        ).catch { e ->
                            showMsg("${e.message}")
                        }.collect { data ->
                            Log.d("main", "$data")
                            showMsg("data added successfully..")
                            getPhone()
                        }
                    }
                } else {
                    showMsg("please fill all the fields..")
                }
            }
        }
    }

    private fun getPhone() {
        phoneViewModel.getPhone()
        lifecycleScope.launchWhenStarted {
            binding.apply {
                phoneViewModel.apiState.collect {
                    when (it) {
                        is ApiState.Success -> {
                            recyclerView.isVisible = true
                            progressBar.isVisible = false
                            phoneAdapter.submitList(it.data)
                        }
                        is ApiState.Failure -> {
                            recyclerView.isVisible = false
                            progressBar.isVisible = false
                            Log.d("main", "getPhone: ")
                        }
                        is ApiState.loading -> {
                            recyclerView.isVisible = false
                            progressBar.isVisible = true
                        }
                        is ApiState.Empty -> {

                        }
                    }
                }
            }
        }
    }


    private fun initRecyclerView() {
        binding.apply {
            recyclerView.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = phoneAdapter
            }
        }
    }
}