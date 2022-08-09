package com.mpfcoding.six_design_patterns.util

class StringValidations(block: StringValidations.() -> Unit) {
    private var validationsResult = arrayListOf<Pair<Boolean, String>>()
    private var messageError: StringBuilder = StringBuilder()

    init {
        block.invoke(this)
    }

    fun isNull(value: String?, fieldToMessage: String = "") = with(fieldToMessage) {
        if (value == null) {
            validationsResult.add(mountErrorMessage(fieldToMessage))
        }
        validationsResult.add(false to "")
    }

    fun isNullOrBlank(value: String?, fieldToMessage: String = "") = with(fieldToMessage) {
        if (value.isNullOrBlank()) {
            validationsResult.add(mountErrorMessage(fieldToMessage))
        }
        validationsResult.add(false to "")
    }

    private fun mountErrorMessage(fieldToMessage: String) = with(fieldToMessage) {
        if (isEmptyExcept("&")) {
            messageError.appendLine("Field is null or empty")
            true to "Field is null or empty"
        } else {
            messageError.appendLine("$fieldToMessage is null or empty")
            true to "$fieldToMessage is null or empty"
        }
    }

    fun buildMessage() = messageError.toString()

    fun build() = validationsResult

    fun buildOneResult() = validationsResult[0]
}