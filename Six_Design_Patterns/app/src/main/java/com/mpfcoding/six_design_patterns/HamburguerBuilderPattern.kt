package com.mpfcoding.six_design_patterns

class HamburguerBuilderPattern private constructor(
    val chesse: Boolean,
    val beef: Boolean,
    val onions: Boolean
) {
    class Builder {
        private var cheese: Boolean = true
        private var beef: Boolean = true
        private var onions: Boolean = false

        fun cheese(value: Boolean) = apply { cheese = value }

        fun beef(value: Boolean) = apply { beef = value }

        fun onions(value: Boolean) = apply { onions = value }

        fun build() = HamburguerBuilderPattern(cheese, beef, onions)
    }

    override fun toString(): String {
        return "cheese -> $chesse"
    }
}