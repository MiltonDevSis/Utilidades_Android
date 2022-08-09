package com.mpfcoding.six_design_patterns.util

enum class AddressTypes(val type: String) {
    ADDRESS_ZIP_CODE("address_zip_code"),
    ADDRESS_STATE("address_state"),
    ADDRESS_CITY("address_city"),
    ADDRESS_NEIGHBOR("address_district"),
    ADDRESS_STREET("address_street"),
    ADDRESS_NUMBER("address_number"),
    ADDRESS_COMPLEMENT("address_complement"),
    ADDRESS_COMPLETE("address_complete")
}
