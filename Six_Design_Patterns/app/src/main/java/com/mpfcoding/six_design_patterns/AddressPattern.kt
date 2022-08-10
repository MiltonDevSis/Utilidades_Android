package com.mpfcoding.six_design_patterns

import com.mpfcoding.six_design_patterns.model.Address
import com.mpfcoding.six_design_patterns.util.Builder

class AddressBuilder(
    block: AddressBuilder.() -> Unit
) : Builder<Address> {

    private var address: Address = Address()

    init {
        block.invoke(this)
    }

    fun city(city: String) = address.apply { this.city = city }

    fun state(state: String) = address.apply { this.state = state }

    fun addressComplete(addressComplete: String) = address.apply { this.addressComplete = addressComplete }

    fun addressLineOne(addressLineOne: String) = address.apply { this.addressLineOne = addressLineOne }

    fun addressLineTwo(addressLineTwo: String) = address.apply { this.addressLineTwo = addressLineTwo }

    fun zipCode(zipCode: String) = address.apply { this.zipCode = zipCode }

    override fun build() = address.apply {
        val errorMessage =  StringBuilder().apply {
            appendLine()
//            StringValidations {
//                isNullOrBlank(city, "City")
//                isNullOrBlank(state, "State")
//            }.buildMessage()
        }
        require(errorMessage.isBlank()) { errorMessage }
    }
}