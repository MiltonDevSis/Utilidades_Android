package com.mpfcoding.forms_with_usecase.usecase

import org.junit.Assert
import org.junit.Before
import org.junit.Test


class ValidateEmailUseCaseTest{

    private lateinit var validateEmail: ValidateEmailUseCase

    @Before
    fun init(){
        validateEmail = ValidateEmailUseCase()
    }

    @Test
    fun testEmailBlank(){
        val result = validateEmail.execute("")
        Assert.assertEquals(result, ValidationResult(
            sucessful = false,
            errorMessage = "The email can´t be blank"
        ))
    }

    @Test
    fun testEmailNotValid(){
        val result = validateEmail.execute("example.com.br")
        Assert.assertEquals(result, ValidationResult(
            sucessful = false,
            errorMessage = "That´s not a valid email"
        ))
    }

    @Test
    fun testEmailValid(){
        val result = validateEmail.execute("teste@gmail.com")
        Assert.assertEquals(result, ValidationResult(
            sucessful = true
        ))
    }
}