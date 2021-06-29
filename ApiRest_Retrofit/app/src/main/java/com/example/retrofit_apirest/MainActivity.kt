package com.example.retrofit_apirest

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit_apirest.data.adapter.PhoneAdapter
import com.example.retrofit_apirest.data.util.ApiState
import com.example.retrofit_apirest.data.util.Listener
import com.example.retrofit_apirest.data.util.showMsg
import com.example.retrofit_apirest.databinding.ActivityMainBinding
import com.example.retrofit_apirest.databinding.OpenDialogBinding
import com.example.retrofit_apirest.ui.PhoneViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Listener {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val phoneViewModel: PhoneViewModel by viewModels()

    private lateinit var phoneAdapter: PhoneAdapter

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
                        }.collect {
                            showMsg("Dados adicionados com sucesso!")
                            getPhone()
                        }
                    }
                } else {
                    showMsg("Todos os campos são obrigatórios")
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
        phoneAdapter = PhoneAdapter(this)
        binding.apply {
            recyclerView.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = phoneAdapter
            }
        }
    }

    override fun deleteOnClick(position: Int, userId: Int) {
        lifecycleScope.launchWhenStarted {
            phoneViewModel.deletePhone(userId)
                .catch { e ->
                    Log.d("main", "${e.message}")
                }.collect {
                    showMsg("Deletado com sucesso!")
                    getPhone()
                }
        }
    }

    override fun updateOnClick(position: Int, userId: Int, name: String, phoneNo: Long) {
        val alertDialog = AlertDialog.Builder(this)
        val binding = OpenDialogBinding.inflate(LayoutInflater.from(this))
        val dialog = alertDialog.create()
        dialog.setView(binding.root)
        binding.apply {
            edtNomeUpdate.setText(name)
            edtNumeroUpdate.setText("$phoneNo")
            btnSalvar.setOnClickListener {
                if (!TextUtils.isEmpty(edtNomeUpdate.text.toString()) && !TextUtils.isEmpty(edtNumeroUpdate.text.toString())) {
                    lifecycleScope.launchWhenStarted {
                        phoneViewModel.updatePhone(
                            userId, edtNomeUpdate.text.toString(),
                            edtNumeroUpdate.text.toString().toLong()
                        )
                            .catch { e ->
                                Log.d("main", "${e.message}")
                            }.collect {
                                showMsg("Atualizados com sucesso!")
                                getPhone()
                            }
                        dialog.dismiss()
                    }
                } else {
                    showMsg("Todos os dados são obrigatórios")
                }
            }
        }
        dialog.show()
    }
}