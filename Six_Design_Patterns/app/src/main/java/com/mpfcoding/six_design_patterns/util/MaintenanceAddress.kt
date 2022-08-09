package com.mpfcoding.six_design_patterns.util

class MaintenanceAddress(
    var zipCode: String?,
    var state: String?,
    var city: String?,
    var neighborhood: String?,
    var street: String?,
    var number: String?,
    var complement: String?,
    var tagValueState: String? = "",
    var addressComplete: String? = ""
) {
    fun getValue(type: String?): String?{
        when(type){
            AddressTypes.ADDRESS_ZIP_CODE.type -> {
                return zipCode
            }
            AddressTypes.ADDRESS_STATE.type -> {
                return state
            }
            AddressTypes.ADDRESS_COMPLEMENT.type -> {
                return complement
            }
            AddressTypes.ADDRESS_CITY.type -> {
                return city
            }
            AddressTypes.ADDRESS_NEIGHBOR.type -> {
                return neighborhood
            }
            AddressTypes.ADDRESS_STREET.type -> {
                return street
            }
            AddressTypes.ADDRESS_NUMBER.type -> {
                return number
            }
            AddressTypes.ADDRESS_COMPLETE.type -> {
                return addressComplete
            }
        }
        return null
    }

    override fun toString() =
        "$street, " +
                "$number, " +
                "$neighborhood, " +
                "$city, " +
                "$state, " +
                "$zipCode, " +
                "$complement"
}
