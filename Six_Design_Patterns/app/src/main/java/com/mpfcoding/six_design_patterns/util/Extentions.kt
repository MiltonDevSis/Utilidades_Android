package com.mpfcoding.six_design_patterns.util

import com.mpfcoding.six_design_patterns.model.Address

fun Address.getAddressComplete(): String {
    val addressLineOne = this.addressLineOne?.split(",")

    val city = this.city?.trim()
    val state = this.state?.trim()
    val zipCode = this.zipCode?.trim()
    val complement = this.addressLineTwo?.trim()
    val number = if (addressLineOne?.size == 3) addressLineOne[1].trim() else null
    val street = if (addressLineOne?.size == 3) addressLineOne[0].trim() else null
    val neighborhood = if (addressLineOne?.size == 3) addressLineOne[2].trim() else null

    val stringBuilder = StringBuilder()

    if (street != null) stringBuilder.append(street)
    if (number != null) stringBuilder.append(", $number")
    if (neighborhood != null) stringBuilder.append(", $neighborhood")
    if (city != null) stringBuilder.append(", $city")
    if (state != null) stringBuilder.append(", $state")
    if (zipCode != null) stringBuilder.append(", $zipCode")
    if (complement != null) stringBuilder.append(", $complement")

    return stringBuilder.toString()
}

fun String.isEmptyExcept(execption: String) = this.replace(execption, "").isEmpty()