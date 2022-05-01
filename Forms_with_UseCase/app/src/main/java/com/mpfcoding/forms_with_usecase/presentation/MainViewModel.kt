package com.mpfcoding.forms_with_usecase.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpfcoding.forms_with_usecase.usecase.ValidateEmailUseCase
import com.mpfcoding.forms_with_usecase.usecase.ValidatePasswordUseCase
import com.mpfcoding.forms_with_usecase.usecase.ValidateRepeatedPasswordUseCase
import com.mpfcoding.forms_with_usecase.usecase.ValidateTermsUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val validateEmail: ValidateEmailUseCase = ValidateEmailUseCase(),
    private val validatePassword: ValidatePasswordUseCase = ValidatePasswordUseCase(),
    private val validateRepeatedPassword: ValidateRepeatedPasswordUseCase = ValidateRepeatedPasswordUseCase(),
    private val validateAcceTermsUseCase: ValidateTermsUseCase = ValidateTermsUseCase(),
): ViewModel() {

    var state by mutableStateOf(RegistrationFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: RegistrationFormEvent){
        when(event){
            is RegistrationFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is RegistrationFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is RegistrationFormEvent.RepeatedPasswordChanged -> {
                state = state.copy(repeatPassword = event.repeatedPassword)
            }
            is RegistrationFormEvent.AcceptedTerms -> {
                state = state.copy(acceptedTerms = event.isAccepted)
            }
            is RegistrationFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)
        val repeatedPasswordResult = validateRepeatedPassword.execute(
            state.password, state.repeatPassword
        )
        val termsResult = validateAcceTermsUseCase.execute(state.acceptedTerms)

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult,
            termsResult
        ).any { !it.sucessful }

        if(hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatPasswordError = repeatedPasswordResult.errorMessage,
                termsError = termsResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }
}