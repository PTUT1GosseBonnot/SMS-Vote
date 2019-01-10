package com.example.lpiem.smsvote.presentation.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.widget.RelativeLayout
import com.example.lpiem.smsvote.R
import com.example.lpiem.smsvote.base.BaseFragment
import com.example.lpiem.smsvote.presentation.presenter.ChartFragmentPresenter
import com.example.lpiem.smsvote.presentation.presenter.ChartView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.chart_fragment.*
import java.util.*


class ChartFragment: BaseFragment<ChartFragmentPresenter>(), ChartView {
    override val layoutId: Int = R.layout.chart_fragment
    override var presenter: ChartFragmentPresenter = ChartFragmentPresenter()

    private lateinit var mPieChart: PieChart
    private lateinit var mainLayout: RelativeLayout

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

        setupPieChartView()

        chartTitle.text = getString(R.string.chartDescription) + " test ?"
        chartTitle.textSize = 20f


    }

    fun setupPieChartView() {
        mPieChart = activity!!.findViewById(R.id.piechart)

        mPieChart.setUsePercentValues(false)

        mPieChart.setDrawHoleEnabled(true)
        mPieChart.setHoleRadius(7f)
        mPieChart.setTransparentCircleRadius(10f)

        mPieChart.isRotationEnabled = false
        mPieChart.setDrawSliceText(false)

        val legend: Legend? = mPieChart.legend
        legend?.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend?.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend?.direction = Legend.LegendDirection.LEFT_TO_RIGHT
        legend?.form = Legend.LegendForm.CIRCLE


        val value = Arrays.asList(1f, 0f, 3f, 9f,15f)
        val label = Arrays.asList("1", "ehcfjhvecfdehvuyg", "3","4","5","6")

        val entry = ArrayList<PieEntry>()
        for (i in value.indices) {
            entry.add(PieEntry(value.get(i), label.get(i)))
        }


        val dataSet = PieDataSet(entry, "")
        val colors = ArrayList<Int>()

        for (c in ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c)

        for (c in ColorTemplate.JOYFUL_COLORS)
            colors.add(c)

        for (c in ColorTemplate.COLORFUL_COLORS)
            colors.add(c)

        for (c in ColorTemplate.LIBERTY_COLORS)
            colors.add(c)

        for (c in ColorTemplate.PASTEL_COLORS)
            colors.add(c)

        colors.add(ColorTemplate.getHoloBlue())
        dataSet.colors = colors
        dataSet.setDrawValues(true)

        val pieData = PieData(dataSet)
        pieData.setValueFormatter(PercentFormatter())
        pieData.setValueTextSize(10f)
        pieData.setValueTextColor(Color.WHITE)
        pieData.setValueTextColor(Color.BLACK)
        dataSet.valueTextColor = Color.BLACK
        pieData.setValueTextColor(Color.BLACK)
        mPieChart.setEntryLabelColor(Color.BLUE)


        mPieChart.data = pieData
    }

}