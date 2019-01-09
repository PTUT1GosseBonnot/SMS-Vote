package com.example.lpiem.smsvote.presentation.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import com.example.lpiem.smsvote.R
import com.example.lpiem.smsvote.base.BaseFragment
import com.example.lpiem.smsvote.presentation.presenter.ChartFragmentPresenter
import com.example.lpiem.smsvote.presentation.presenter.ChartView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import android.widget.Toast
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.ColorTemplate


class ChartFragment: BaseFragment<ChartFragmentPresenter>(), ChartView {
    override val layoutId: Int = R.layout.chart_fragment
    override var presenter: ChartFragmentPresenter = ChartFragmentPresenter()

    private lateinit var pieChart: PieChart
    private lateinit var mainLayout: ConstraintLayout

    // test data
    private val yData = floatArrayOf(5f, 10f, 15f, 30f, 40f)
    private val xData = arrayOf("Sony", "Huawei", "LG", "Apple", "Samsung")

    override fun displayLoader() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoader() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attach(this)
        mainLayout = activity!!.findViewById(R.id.main)
        pieChart = PieChart(context)

        //add pie chart to the layout
        mainLayout.addView(pieChart)
        mainLayout.setBackgroundColor(Color.GREEN)

        //configure pie chart
        pieChart.setUsePercentValues(true)
        pieChart.description = getString(R.string.chartDescription) as Description

        //enable/disable hole and configure
        pieChart.isDrawHoleEnabled = true
        pieChart.holeRadius = 7.0f
        pieChart.transparentCircleRadius = 10.0f

        //enable/disable rotation of the chart by touch
        pieChart.rotationAngle = 0.0f
        pieChart.isRotationEnabled = false

        // listener on chart value
        pieChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry?, h: Highlight?) {
                // display msg when value selected
                if (e == null)
                    return

                Toast.makeText(
                    context,
                    xData[e.x.toInt()] + " = " + e.toString() + "%", Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected() {

            }
        })

        // add data
        addDataToChart()

        // customize legend
        val legend: Legend = pieChart.legend
        legend.position = (Legend.LegendPosition.RIGHT_OF_CHART)
        legend.xEntrySpace = 7f
        legend.yEntrySpace = 5f


    }

    private fun addDataToChart(){
        var yValues: ArrayList<PieEntry> = ArrayList()

        for(i in 0..yData.size){
            yValues.add(PieEntry(yData[i],i as Float))
        }

        var xValues: ArrayList<String> = ArrayList()

        for(i in 0..xData.size){
            xValues.add(xData[i])
        }

        //create Pie data set
        var pieDataSet: PieDataSet = PieDataSet(yValues, "Test")
        pieDataSet.sliceSpace = 3f
        pieDataSet.selectionShift = 5f

        var colors: ArrayList<Int> = ArrayList()

        for(c in ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c)

        for(c in ColorTemplate.JOYFUL_COLORS)
            colors.add(c)

        for(c in ColorTemplate.COLORFUL_COLORS)
            colors.add(c)

        for(c in ColorTemplate.LIBERTY_COLORS)
            colors.add(c)

        for(c in ColorTemplate.PASTEL_COLORS)
            colors.add(c)

        colors.add(ColorTemplate.getHoloBlue())
        pieDataSet.colors = colors

        // instantiate pie data object
        var pieData: PieData = PieData()
        pieData.dataSet = pieDataSet
        pieData.setValueFormatter(PercentFormatter())
        pieData.setValueTextSize(10f)
        pieData.setValueTextColor(Color.WHITE)

        pieChart.data = pieData

        pieChart.highlightValue(null)

        pieChart.invalidate()

    }
}