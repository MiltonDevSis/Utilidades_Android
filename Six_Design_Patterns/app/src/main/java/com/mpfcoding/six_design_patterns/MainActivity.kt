package com.mpfcoding.six_design_patterns

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        singletonPattern()

        createDialogFactory()

        createHamburguerBuild()
    }

    private fun singletonPattern(){
        val message = "Hello word"
        val messageTwo = SingletonPattern.message

        SingletonPattern.showMessage(message)
        SingletonPattern.showMessageTwo(messageTwo)
    }

    private fun createDialogFactory(){

        val dialog = DialogFactory.createDialog(Dialogtype.DIALOG_CREATE_CHAT)
    }

    private fun createHamburguerBuild(){
        val hamburguer = HamburguerBuilderPattern.Builder()
        hamburguer.beef(true)
        hamburguer.onions(true)
        hamburguer.cheese(false)
        hamburguer.build()

        println(hamburguer.toString())
    }
}