package com.mpfcoding.differents_flows

class Constants(){
    companion object {
        val descriptionNormalFlow: String = """ 
            Se rotacionar a tela depois de clicar em Normal flow o texto voltará ao estado normal,
             pois o (Flow) não contém estado.
        """.trimIndent()

        val descriptionSharedFlow: String = """ 
            Com SharedFlow a Snackbar aparecerá apenas no click do botão, mesmo rotacionando a tela,
             caso fosse com stateflow, toda vez que a activity for criada a Snackbar será mostrada.
        """.trimIndent()
    }
}
