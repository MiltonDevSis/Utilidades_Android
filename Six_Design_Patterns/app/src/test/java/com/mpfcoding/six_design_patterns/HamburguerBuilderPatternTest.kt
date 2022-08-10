package com.mpfcoding.six_design_patterns

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class HamburguerBuilderPatternTest{

    @Test
    fun `When pass data assert not null`(){
        val hamburguer = HamburguerBuilderPattern.Builder()
        hamburguer.beef(true)
        hamburguer.cheese(false)
        hamburguer.onions(false)
        hamburguer.build()

        assertNotNull(hamburguer)
    }

    @Test
    fun `When pass onion true verify this condition`(){
        val hamburguer = HamburguerBuilderPattern.Builder().apply {
            beef(false)
            cheese(false)
            onions(true)
        }.also {
            it.build()
        }

        assertTrue(hamburguer.onion())
    }
}