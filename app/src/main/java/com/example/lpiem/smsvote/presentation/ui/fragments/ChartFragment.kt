package com.example.lpiem.smsvote.presentation.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.widget.RelativeLayout
import com.example.lpiem.smsvote.R
import com.example.lpiem.smsvote.base.BaseFragment
import com.example.lpiem.smsvote.domain.VoteManager
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

class ChartFragment : BaseFragment<ChartFragmentPresenter>(), ChartView {
    override val layoutId: Int = R.layout.chart_fragment
    override var presenter: ChartFragmentPresenter = ChartFragmentPresenter()
    val voteManager: VoteManager = VoteManager.instance

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

        chartTitle.text = getString(R.string.chartDescription, voteManager.vote.question)
        chartTitle.textSize = 20f
    }


    fun setupPieChartView() {
        mPieChart = activity!!.findViewById(R.id.piechart)

        mPieChart.setUsePercentValues(false)

        mPieChart.description.isEnabled = false

        mPieChart.isDrawHoleEnabled = true
        mPieChart.holeRadius = 7f
        mPieChart.transparentCircleRadius = 10f

        mPieChart.isRotationEnabled = false
        mPieChart.setDrawSliceText(false)

        val legend: Legend? = mPieChart.legend
        legend?.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend?.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend?.direction = Legend.LegendDirection.LEFT_TO_RIGHT
        legend?.form = Legend.LegendForm.CIRCLE


        val values = ArrayList<Float>()
        val labels = ArrayList<String>()
        for (response in voteManager.vote.responses) {
            values.add(response.second.toFloat())
            labels.add(response.first.response!!)
        }

        val entry = ArrayList<PieEntry>()
        for (i in values.indices) {
            entry.add(PieEntry(values[i], labels[i]))
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