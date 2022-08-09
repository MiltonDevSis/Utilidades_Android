package com.mpfcoding.six_design_patterns.model

import com.mpfcoding.six_design_patterns.util.MaintenanceAddress
import com.mpfcoding.six_design_patterns.util.getAddressComplete

const val ADDRESS_CACHE_KEY = "AddressModelKey"

data class Address(
    var addressComplete: String = "",
    var addressLineOne: String? = null,
    var addressLineTwo: String? = null,
    var city: String? = null,
    var state: String? = null,
    var zipCode: String? = null
) {

    override fun toString(): String {
        return this.getAddressComplete()
    }

    fun toMaintenanceAddress(): MaintenanceAddress {
        val addressLineOne = this.addressLineOne?.split(",")
        return MaintenanceAddress(
            zipCode = this.zipCode?.trim(),
            state = this.state?.trim(),
            city = this.city?.trim(),
            neighborhood = if (addressLineOne?.size == 3) addressLineOne[2].trim() else "",
            street = if (addressLineOne?.size == 3) addressLineOne[0].trim() else "",
            number = if (addressLineOne?.size == 3) addressLineOne[1].trim() else "",
            complement = this.addressLineTwo?.trim(),
            addressComplete = this.toString()
        )
    }
}