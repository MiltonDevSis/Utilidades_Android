package com.mpfcoding.testhilt

import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.mpfcoding.testhilt.glide.GlideImageLoaderImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var testeString: String

    private val viewModel: MainViewModel by viewModels()

    private lateinit var buttonTest: Button
    private lateinit var editUm: EditText
    private lateinit var editDois: EditText
    private lateinit var buttonSoma: Button
    private lateinit var buttonMultiplica: Button
    private lateinit var txtResultado: TextView

    private lateinit var imageGlide: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapComponents()
        setListeners()
        observers()
    }

    private fun mapComponents() {
        buttonTest = findViewById(R.id.buttonTest)
        imageGlide = findViewById(R.id.imageFromGlide)
        editUm = findViewById(R.id.editNumeroUm)
        editDois = findViewById(R.id.editNumeroDois)
        buttonSoma = findViewById(R.id.buttonSoma)
        buttonMultiplica = findViewById(R.id.buttonMultiplica)
        txtResultado = findViewById(R.id.textResultado)
    }

    private fun setListeners() {
        buttonTest.setOnClickListener {
            Toast.makeText(this, testeString, Toast.LENGTH_LONG).show()
        }

        GlideImageLoaderImpl()
            .load(imageGlide, "https://miro.medium.com/max/1400/1*o8Q_O-C6yGZQqW_2cdafoQ.png")

        buttonSoma.setOnClickListener {
            sendDataToViewModel(1)
        }

        buttonMultiplica.setOnClickListener {
            sendDataToViewModel(2)
        }
    }

    private fun sendDataToViewModel(tipo: Int) {
        val numeroUm = editUm.text.toString()
        val numeroDois = editDois.text.toString()
        if (numeroUm.isNotEmpty() && numeroDois.isNotEmpty()) {
            viewModel.operation(numeroUm.toInt(), numeroDois.toInt(), tipo)
        }
    }

    private fun observers() {
        viewModel.liveData.observe(this) { result ->
            txtResultado.text = getString(
                R.string.o_resultado_e, result.toString(),
                (result + 2).toString(),
                (result + 3).toString(),
                (result + 4).toString()
            )
        }
    }
}