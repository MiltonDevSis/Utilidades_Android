package com.mpfcoding.barcharttest

import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ViewPortHandler
import java.text.DecimalFormat


class MyValueFormatter : ValueFormatter() {
    private val mFormat: DecimalFormat = DecimalFormat("###,###,###,##0.0")
    fun getFormattedValue(
        value: Float,
        entry: Map.Entry<*, *>?,
        dataSetIndex: Int,
        viewPortHandler: ViewPortHandler?
    ): String {
        return mFormat.format(value).toString() + " $"
    }

}