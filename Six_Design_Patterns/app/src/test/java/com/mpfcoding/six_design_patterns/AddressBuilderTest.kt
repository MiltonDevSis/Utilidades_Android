package com.mpfcoding.six_design_patterns

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class AddressBuilderTest{

    @Test
    fun `When create address builder show city`(){
        val address = AddressBuilder{
            city("Urussanga")
            state("Santa Catarina")
        }.build()

        assertEquals(address.city, "Urussanga")
    }

    @Test
    fun `When create address valid not null`(){
        val address = AddressBuilder{
            city("Urussanga")
            state("Santa Catarina")
        }.build()

        assertNotNull(address)
    }
}