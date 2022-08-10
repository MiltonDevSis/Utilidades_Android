package com.mpfcoding.six_design_patterns

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    @Named("TextMain")
    lateinit var hamburguer: String

    @Inject
    @Named("TextMainTwo")
    lateinit var chesse: String

    private lateinit var textMain: TextView
    private lateinit var textMainTwo: TextView
    private lateinit var textMainClass: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textMain = findViewById(R.id.textMain)
        textMainTwo = findViewById(R.id.textMainTwo)
        textMainClass = findViewById(R.id.textMainClass)

        createHamburguerBuild()

        singletonPattern()

        createDialogFactory()

        populateAddress().also {
            println(it.state)
        }
    }

    private fun singletonPattern() {
        val message = "Hello word"
        val messageTwo = SingletonPattern.message

        SingletonPattern.showMessage(message)
        SingletonPattern.showMessageTwo(messageTwo)
    }

    private fun createDialogFactory() {

        val dialog = DialogFactory.createDialog(Dialogtype.DIALOG_CREATE_CHAT)
    }

    private fun createHamburguerBuild() {
        textMain.text = hamburguer

        textMainTwo.text = chesse
    }

    fun populateAddress() = AddressBuilder {
        city("São paulo")
        state("Santa Catarina")
        zipCode("88840123")
    }.build()
}