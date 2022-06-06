package com.mpfcoding.barcharttest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate


class MainActivity : AppCompatActivity() {

    private var barArraylist: ArrayList<BarEntry> = arrayListOf()
    private lateinit var chart: BarChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chart = findViewById(R.id.chart1)

        getData()
        val barDataSet = BarDataSet(barArraylist, "")
        val barData = BarData(barDataSet)
        chart.data = barData

        barDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
        barDataSet.valueTextColor = R.color.black
        barDataSet.valueTextSize = 22f
        //barDataSet.removeFirst()
        //barDataSet.valueFormatter = MyValueFormatter()
        chart.axisLeft.setDrawGridLines(false)
        chart.axisLeft.setDrawAxisLine(false)
        chart.axisRight.setDrawAxisLine(false)
        chart.xAxis.setDrawAxisLine(false)
        chart.axisRight.setDrawGridLines(false)
        chart.xAxis.setDrawGridLines(false)
        chart.axisLeft.setDrawLabels(false)
        chart.axisRight.setDrawLabels(false)
        chart.xAxis.setDrawLabels(false)
        chart.description.isEnabled = false
        chart.legend.isEnabled = false
    }

    private fun getData() {
        barArraylist = ArrayList()
       // barArraylist.add(BarEntry(0f, 0.000f))
        barArraylist.add(BarEntry(1f, 1.200f))
        barArraylist.add(BarEntry(2f, 1.300f))
    }
}